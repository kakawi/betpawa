package com.hlebon.betpawa.controller;

public class OperationResult<T> {

    private T result;

    private OperationError error;

    public T getResult() {
        return result;
    }

    public OperationResult<T> setResult(final T result) {
        this.result = result;
        return this;
    }

    public OperationError getError() {
        return error;
    }

    public OperationResult<T> setError(final OperationError error) {
        this.error = error;
        return this;
    }

    public static class OperationError {
        private final String message;

        public OperationError(final String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
