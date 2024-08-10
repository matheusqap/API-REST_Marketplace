package com.github.qualquercoisavinteconto.responses;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiErrorResponse {
    private List<ApiError> errors;
}
