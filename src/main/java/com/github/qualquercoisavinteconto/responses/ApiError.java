package com.github.qualquercoisavinteconto.responses;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiError {
    private String code;
    private String message;
}
