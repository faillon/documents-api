package org.faillon.documentsapi.common.exception;

import jakarta.websocket.server.ServerEndpoint;
import lombok.*;

import java.util.Map;

@Getter @Setter
@AllArgsConstructor
@Builder
public class ValidationErrorResponse {

    private int statusCode;
    private Map<String, String> errors;
}
