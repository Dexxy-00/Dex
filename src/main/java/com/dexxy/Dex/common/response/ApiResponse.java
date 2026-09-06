package com.dexxy.Dex.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponse<T> {
    private HttpStatus statusCode;
    private String message;
    private T data;
}
