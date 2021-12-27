import { beforeEach, describe, expect, it } from "@jest/globals";
import { LOG } from '../../src/utils/constants';
import { getOriginalServerUrl } from '../../src/utils/restfulAPI';

describe('restfulAPI', () => {
    beforeEach(() => {
        jest.clearAllMocks();
        fetch.resetMocks();
        jest.spyOn(LOG, 'error').mockImplementation(() => {});
    });

    it('getOriginalServerUrl', async () => {
        process.env.SERVER_PORT = '3113';
        expect(getOriginalServerUrl()).toEqual('http://localhost:3113');
    });
});