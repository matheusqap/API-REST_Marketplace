package com.github.qualquercoisavinteconto.exceptions.factories;

import java.util.List;

import com.github.qualquercoisavinteconto.responses.ApiError;
import com.github.qualquercoisavinteconto.responses.ApiErrorResponse;

public class ApiErrorResponseFactory {
    public static ApiErrorResponse createApiErrorResponse(String code, String message) {
        var error = ApiError.builder()
                .code(code)
                .message(message)
                .build();

        return ApiErrorResponse
                .builder()
                .errors(List.of(error))
                .build();
    }
}
