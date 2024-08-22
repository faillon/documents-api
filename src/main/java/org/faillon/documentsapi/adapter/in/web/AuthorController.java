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
import lombok.extern.slf4j.Slf4j;
import org.faillon.documentsapi.application.port.in.author.*;
import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import org.faillon.documentsapi.common.dto.author.CreateAuthorRequest;
import org.faillon.documentsapi.common.dto.author.UpdateAuthorRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Authors")
public class AuthorController {

    Logger logger = LoggerFactory.getLogger(AuthorController.class);

    private final GetAllAuthorsUseCase getAllAuthorsUseCase;
    private final GetAuthorUseCase getAuthorUseCase;
    private final UpdateAuthorUseCase updateAuthorUseCase;
    private final CreateAuthorUseCase createAuthorUseCase;
    private final DeleteAuthorUseCase deleteAuthorUseCase;

    @GetMapping("/")
    @Operation(description = "Get All Authors",
            responses = {
                @ApiResponse(responseCode = "200",
                content = @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = AuthorResponse.class))
                ))
            } )
    private ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        logger.info("Getting All Authors");

        return ResponseEntity.status(HttpStatus.OK).
                body(getAllAuthorsUseCase.getAllAuthors());
    }

    @GetMapping("/{authorId}")
    @Operation(description = "Get A Specific Author By Id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthorResponse.class)
                            ))
            } )
    private ResponseEntity<AuthorResponse> getAuthor(@PathVariable Long authorId) {
        logger.info("Getting Author with ID: " + authorId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(getAuthorUseCase.getAuthor(authorId));
    }

    @PutMapping("/{authorId}")
    @Operation(description = "Update A Specific Author By Id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthorResponse.class)
                            ))
            } )
    private ResponseEntity<AuthorResponse> updateAuthor(@PathVariable Long authorId,@Valid @RequestBody UpdateAuthorRequest request) {
        logger.info("Updating Author with ID: " + authorId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(updateAuthorUseCase.updateAuthor(authorId, request));
    }

    @PostMapping("/")
    @Operation(description = "Create An Author",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateAuthorRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthorResponse.class)
                            ))
            } )
    private ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody CreateAuthorRequest request) {
        logger.info("Creating Author with data: " + request.toString());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createAuthorUseCase.createAuthor(request));
    }

    @DeleteMapping("/{authorId}")
    @Operation(description = "Deletes An Author By Id",
    parameters = @Parameter(description = "Author Id",
            required = true,
            name = "authorId",
            in = ParameterIn.PATH))
    private void deleteAuthor(@PathVariable Long authorId) {
        logger.info("Deleting Author with ID:" + authorId);
        deleteAuthorUseCase.deleteAuthor(authorId);
    }
}
