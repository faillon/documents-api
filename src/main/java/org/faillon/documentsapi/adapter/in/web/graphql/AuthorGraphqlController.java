package org.faillon.documentsapi.adapter.in.web.graphql;

import lombok.RequiredArgsConstructor;
import org.faillon.documentsapi.application.port.in.author.GetAllAuthorsUseCase;
import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorGraphqlController {

    private final GetAllAuthorsUseCase getAllAuthorsUseCase;

    @QueryMapping
    public List<AuthorResponse> getAllAuthorsList() {
        return getAllAuthorsUseCase.getAllAuthors();
    }
}
