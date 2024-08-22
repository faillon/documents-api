package org.faillon.documentsapi.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private int statusCode;
    private String message;
}
