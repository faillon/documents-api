package org.faillon.documentsapi.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.faillon.documentsapi.application.port.in.document.DocumentUseCase;
import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import org.faillon.documentsapi.common.dto.document.CreateDocumentRequest;
import org.faillon.documentsapi.common.dto.document.DocumentResponse;
import org.faillon.documentsapi.common.dto.document.UpdateDocumentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DocumentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private DocumentUseCase documentUseCase;

    List<DocumentResponse> documentResponseList;

    DocumentResponse documentResponse;

    CreateDocumentRequest createDocumentRequest;

    UpdateDocumentRequest updateDocumentRequest;

    DocumentResponse updatedDocumentResponse;

    @BeforeEach
    void setUp() {
        documentResponseList = List.of(DocumentResponse.builder()
                .id(1L)
                .title("The Universe")
                .body("The Universe is Magical")
                .authors(List.of(AuthorResponse.builder()
                        .id(1L)
                        .name("Felipe")
                        .lastName("Aillon").build()))
                .references("References").build());

        documentResponse = DocumentResponse.builder()
                .id(1L)
                .title("The Universe")
                .body("The Universe is Magical")
                .authors(List.of(AuthorResponse.builder()
                        .id(1L)
                        .name("Felipe")
                        .lastName("Aillon").build()))
                .references("References").build();

        createDocumentRequest = CreateDocumentRequest.builder()
                .title("The Universe")
                .body("The Universe is Magical")
                .authors(List.of(AuthorResponse.builder()
                        .id(1L)
                        .name("Felipe")
                        .lastName("Aillon").build()))
                .references("References").build();

        updateDocumentRequest = UpdateDocumentRequest.builder()
                .title("The Universe")
                .body("The Universe is Magical")
                .authors(List.of(AuthorResponse.builder()
                        .id(1L)
                        .name("Felipe Ignacio")
                        .lastName("Aillon Sanhueza").build()))
                .references("References").build();

        updatedDocumentResponse = DocumentResponse.builder()
                .id(1L)
                .title("The Universe")
                .body("The Universe is Magical")
                .authors(List.of(AuthorResponse.builder()
                        .id(1L)
                        .name("Felipe Ignacio")
                        .lastName("Aillon Sanhueza").build()))
                .references("References").build();

    }

    @Test
    void whenGetAllDocuments_success() throws Exception {

        Mockito.when(documentUseCase.getAllDocuments())
                .thenReturn(documentResponseList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/documents/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("The Universe")))
                .andExpect(jsonPath("$[0].authors[0].last_name", is("Aillon")));

    }

    @Test
    void whenGetDocument_success() throws Exception {
        Mockito.when(documentUseCase.getDocument(1L))
                .thenReturn(documentResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/documents/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("The Universe")))
                .andExpect(jsonPath("$.authors[0].last_name", is("Aillon")));
    }

    @Test
    void whenCreateDocument_success() throws Exception {
       Mockito.when(documentUseCase.createDocument(createDocumentRequest))
                .thenReturn(documentResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/documents/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDocumentRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("The Universe")))
                .andExpect(jsonPath("$.authors[0].last_name", is("Aillon")));
    }

    @Test
    void whenUpdateDocument_success() throws Exception {
        Mockito.when(documentUseCase.updateDocument(1L, updateDocumentRequest) )
                .thenReturn(updatedDocumentResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/documents/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDocumentRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authors[0].name", is("Felipe Ignacio")))
                .andExpect(jsonPath("$.authors[0].last_name", is("Aillon Sanhueza")));
    }

    @Test
    void whenDeleteAuthor_success() throws Exception {
       doNothing().when(documentUseCase).deleteDocument(1L);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/documents/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}