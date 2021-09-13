import '../jestConfig/enzyme.config.js';
import React from 'react';
import { render, waitFor } from '@testing-library/react';
import App from "../../src/components/App";
import { describe, it } from "@jest/globals";

describe('App', () => {
    it('bad connection shows snackbar', async () => {
        fetch.resetMocks();
        fetch.mockReject();
        const { findByText } = render(<App />);
    });
});
