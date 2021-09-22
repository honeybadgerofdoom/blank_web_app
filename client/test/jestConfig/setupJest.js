
global.fetch = require('jest-fetch-mock');

// Subdue Console Messages in Jest Output (Console.error WILL get through for Testing!)

console.info = jest.fn();
console.debug = jest.fn();
console.trace = jest.fn();
console.warn = jest.fn();

