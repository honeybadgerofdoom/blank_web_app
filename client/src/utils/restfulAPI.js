import Ajv from 'ajv';
import * as configSchema from "../../schemas/ConfigResponse";
import * as boardSchema from "../../schemas/BoardResponse";
import * as loginSchema from "../../schemas/LoginResponse";
import * as legalMovesSchema from "../../schemas/LegalMovesResponse";
import * as registerSchema from "../../schemas/RegisterResponse";
import { LOG } from "./constants";

const SCHEMAS = {
    config: configSchema,
    board: boardSchema,
    legalMoves: legalMovesSchema,
    login: loginSchema,
    register: registerSchema
};

export async function sendAPIRequest(requestBody) {
    const response = await sendRequest(requestBody); 

    if (!Object.keys(SCHEMAS).includes(requestBody.requestType)) {
        throw new Error(`sendAPIRequest() does not have support for type: ${requestBody.requestType}. Please add the schema to 'SCHEMAS'.`);
    }
    if (isJsonResponseValid(response, SCHEMAS[requestBody.requestType])) {
        return response;
    }
    LOG.error(`Server ${requestBody.requestType} response json is invalid. Check the Server.`);
    return null;
}

export async function sendRequest(requestBody) { 
    const fetchOptions = {
        method: "POST",
        body: JSON.stringify(requestBody)
    };

    try {
        const response = await fetch(`${getOriginalServerUrl()}/api/${requestBody.requestType}`, fetchOptions);
        if (response.ok) {
            return response.json();
        } else {
            LOG.error(`Request to server ${getOriginalServerUrl()} failed: ${response.status}: ${response.statusText}`);
        }

    } catch (err) {
        LOG.error(`Request to server failed : ${err}`);
    }

    return null;
}

export function getOriginalServerUrl() {
    const serverProtocol = location.protocol;
    const serverHost = location.hostname;
    const serverPort = location.port;
    const alternatePort = process.env.SERVER_PORT;
    return `${serverProtocol}\/\/${serverHost}:${(!alternatePort ? serverPort : alternatePort)}`;
}

export function isJsonResponseValid(object, schema) {
    if (object && schema) {
        const anotherJsonValidator = new Ajv();
        const validate = anotherJsonValidator.compile(schema);
        return validate(object);
    }
    LOG.error(`bad arguments - isJsonResponseValid(object: ${object}, schema: ${schema})`);
    return false;
}
