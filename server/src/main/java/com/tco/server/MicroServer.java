package com.tco.server;

import com.tco.misc.BadRequestException;
import com.tco.misc.JSONValidator;
import com.tco.misc.UnauthorizedRequestException;
import com.tco.requests.*;
import com.tco.requests.LoginRegister.LoginRequest;
import com.tco.requests.LoginRegister.RegisterRequest;
import com.tco.requests.Users.UpdateUserInfoRequest;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.tco.requests.Users.UsersRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import static spark.Spark.*;

class MicroServer {
    private final Logger log = LoggerFactory.getLogger(MicroServer.class);
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private final int HTTP_OK = 200;
    private final int HTTP_BAD_REQUEST = 400;
    private final int HTTP_SERVER_ERROR = 500;
    private final int HTTP_UNAUTHORIZED = 401;

    MicroServer(int serverPort) {
        configureServer(serverPort);
        processRestfulAPIrequests();
    }

    /* Configure MicroServices Here. */

    private void processRestfulAPIrequests() {
        path("/api", () -> {
            before("/*", (req, res) -> logRequest(req));
            post("/config", (req, res) -> processHttpRequest(req, res, ConfigRequest.class));
            post("/login", (req, res) -> processHttpRequest(req, res, LoginRequest.class));
            post("/register", (req, res) -> processHttpRequest(req, res, RegisterRequest.class));
            post("/board", (req, res) -> processHttpRequest(req, res, BoardRequest.class));
            post("/legalMoves", (req, res) -> processHttpRequest(req, res, LegalMovesRequest.class));
            post("/game", (req, res) -> processHttpRequest(req, res, GameRequest.class));
            post("/move", (req, res) -> processHttpRequest(req, res, MoveRequest.class));
            post("/users", (req, res) -> processHttpRequest(req, res, UsersRequest.class));
            post("/myInvites", (req, res) -> processHttpRequest(req, res, MyInvitesRequest.class));
            post("/myProfile", (req, res) -> processHttpRequest(req, res, MyProfileRequest.class));
            post("/updateUserInfo", (req, res) -> processHttpRequest(req, res, UpdateUserInfoRequest.class));
            post("/newInvite", (req, res) -> processHttpRequest(req, res, NewInviteRequest.class));
            post("/declineInvite", (req, res) -> processHttpRequest(req, res, DeclineInviteRequest.class));
        });
    }

    /* You shouldn't need to change what is found below. */

    private String processHttpRequest(spark.Request httpRequest, spark.Response httpResponse, Type requestType) {
        setupResponse(httpResponse);
        String jsonString = httpRequest.body();
        try {
            JSONValidator.validate(jsonString, requestType);
            Request requestObj = new Gson().fromJson(jsonString, requestType);
            return buildJSONResponse(requestObj);
        } catch (IOException | BadRequestException e) {
            log.info("Bad Request - {}", e.getMessage());
            httpResponse.status(HTTP_BAD_REQUEST);
        } catch (UnauthorizedRequestException e) {
            log.info("User is unauthorized - ", e);
            httpResponse.status(HTTP_UNAUTHORIZED);
        } catch (Exception e) {
            log.info("Server Error - ", e);
            httpResponse.status(HTTP_SERVER_ERROR);
        }
        return jsonString;
    }

    private void setupResponse(spark.Response response) {
        response.type("application/json");
        response.header("Access-Control-Allow-Origin", "*");
        response.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        response.status(HTTP_OK);
    }

    private String buildJSONResponse(Request request) throws Exception {
        request.buildResponse();
        String responseBody = new Gson().toJson(request);
        log.trace("Response - {}", responseBody);
        return responseBody;
    }

    private void logRequest(spark.Request request) {
        String message = "Request - "
                + "[" + dateTimeFormat.format(LocalDateTime.now()) + "] "
                + request.ip() + " "
                + "\"" + request.requestMethod() + " "
                + request.pathInfo() + " "
                + request.protocol() + "\" "
                + "[" + request.body() + "]";
        log.info(message);
    }

    private void configureServer(int serverPort) {
        port(serverPort);
        String keystoreFile = System.getenv("KEYSTORE_FILE");
        String keystorePassword = System.getenv("KEYSTORE_PASSWORD");
        if (keystoreFile != null && keystorePassword != null) {
            secure(keystoreFile, keystorePassword, null, null);
            log.info("MicroServer running using HTTPS on port {}.", serverPort);
        } else {
            log.info("MicroServer running using HTTP on port {}.", serverPort);
        }

        // To Serve Static Files (SPA)

        staticFiles.location("/public/");
        redirect.get("/", "/index.html");
    }
}