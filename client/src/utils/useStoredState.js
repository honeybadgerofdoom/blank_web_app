import { useState } from "react";

export function useStoredState(key, defaultValue) {
    const [value, setValue] = useState(getInitialValue());

    function setStoredValue(object) {
        localStorage.setItem(key, JSON.stringify(object));
        setValue(object);
    }

    function deleteStorage() {
        localStorage.removeItem(key);
        setValue(null);
    }

    function getInitialValue() {
        const storageValue = JSON.parse(localStorage.getItem(key));
        if (!storageValue) {
            return defaultValue;
        }
        return storageValue;
    }

    return [value, setStoredValue, deleteStorage];
}