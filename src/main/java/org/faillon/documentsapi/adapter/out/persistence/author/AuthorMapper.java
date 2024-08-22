package org.faillon.documentsapi.adapter.out.persistence.author;

import org.faillon.documentsapi.common.dto.author.AuthorRequest;
import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import org.faillon.documentsapi.common.dto.author.CreateAuthorRequest;
import org.faillon.documentsapi.common.dto.author.UpdateAuthorRequest;

public class AuthorMapper {

    static AuthorEntity requestToEntity(AuthorRequest authorRequest){
        AuthorEntity entity = AuthorEntity.builder()
                .id(authorRequest.getId())
                .name(authorRequest.getName())
                .lastName(authorRequest.getLastName())
                .build();

        return entity;
    }

    static AuthorRequest entityToRequest() {
        //TODO
        return null;
    }

    static AuthorResponse entityToResponse(AuthorEntity authorEntity) {

        AuthorResponse response = AuthorResponse.builder()
                .id(authorEntity.getId())
                .name(authorEntity.getName())
                .lastName(authorEntity.getLastName())
                .build();

        return response;

    }

    static AuthorEntity updateRequestToEntity(UpdateAuthorRequest request) {
        AuthorEntity entity = AuthorEntity.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .build();

        return entity;
    }

    static AuthorEntity createRequestToEntity(CreateAuthorRequest request) {
        AuthorEntity entity = AuthorEntity.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .build();

        return entity;
    }


}
