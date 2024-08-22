package org.faillon.documentsapi.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.faillon.documentsapi.application.port.in.document.DocumentUseCase;
import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import org.faillon.documentsapi.common.dto.author.CreateAuthorRequest;
import org.faillon.documentsapi.common.dto.document.CreateDocumentRequest;
import org.faillon.documentsapi.common.dto.document.DocumentResponse;
import org.faillon.documentsapi.common.dto.document.UpdateDocumentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
@Tag(name = "Documents")
public class DocumentController {

    private final DocumentUseCase documentUseCase;
    Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @GetMapping("/")
    @Operation(description = "Get All Documents",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = DocumentResponse.class))
                            ))
            } )
    private ResponseEntity<List<DocumentResponse>> getAllDocuments(){
        logger.info("Getting all documents");
        return new ResponseEntity<>(documentUseCase.getAllDocuments(), HttpStatus.OK);
    }

    @GetMapping("/{documentId}")
    @Operation(description = "Get A Specific Document By Id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DocumentResponse.class)
                            ))
            } )
    private ResponseEntity<DocumentResponse> getDocument(@PathVariable Long documentId) {
        logger.info("Getting Document with ID " + documentId);
        return new ResponseEntity<>(documentUseCase.getDocument(documentId), HttpStatus.OK);
    }

    @PutMapping("/{documentId}")
    @Operation(description = "Update A Specific Document By Id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DocumentResponse.class)
                            ))
            } )
    private ResponseEntity<DocumentResponse> updateDocument(@PathVariable Long documentId, @Valid @RequestBody UpdateDocumentRequest request) {
        logger.info("Updating Document with ID " + documentId);
        return new ResponseEntity<>(documentUseCase.updateDocument(documentId, request), HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(description = "Create A Document",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateDocumentRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DocumentResponse.class)
                            ))
            } )
    private ResponseEntity<DocumentResponse> createDocument(@RequestBody CreateDocumentRequest request) {
        logger.info("Creating Document with data " + request.toString());
        return new ResponseEntity<>(documentUseCase.createDocument(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{documentId}")
    @Operation(description = "Deletes A Document By Id",
            parameters = @Parameter(description = "Document Id",
                    required = true,
                    name = "documentId",
                    in = ParameterIn.PATH))
    private void deleteDocument(@PathVariable Long documentId) {
        logger.info("Deleting Document with ID " + documentId);
        documentUseCase.deleteDocument(documentId);
    }


}
