package org.faillon.documentsapi.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.faillon.documentsapi.application.port.in.author.*;
import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import org.faillon.documentsapi.common.dto.author.CreateAuthorRequest;
import org.faillon.documentsapi.common.dto.author.UpdateAuthorRequest;
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
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthorController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private GetAllAuthorsUseCase getAllAuthorsUseCase;

    @MockBean
    private GetAuthorUseCase getAuthorUseCase;

    @MockBean
    private CreateAuthorUseCase createAuthorUseCase;

    @MockBean
    private DeleteAuthorUseCase deleteAuthorUseCase;

    @MockBean
    private UpdateAuthorUseCase updateAuthorUseCase;

    List<AuthorResponse> authorResponseList;

    AuthorResponse authorResponse;

    CreateAuthorRequest createAuthorRequest;

    UpdateAuthorRequest updateAuthorRequest;

    AuthorResponse updatedAuthorResponse;

    @BeforeEach
    public void setUp() {
        authorResponseList = List.of(
                AuthorResponse.builder()
                        .id(10L)
                        .name("Felipe")
                        .lastName("Aillon").build(),
                AuthorResponse.builder()
                        .id(20L)
                        .name("Felix")
                        .lastName("Perez").build());


        authorResponse = AuthorResponse.builder()
                .id(10L)
                .name("Felipe")
                .lastName("Aillon").build();

        createAuthorRequest = CreateAuthorRequest.builder()
                .name("Felipe")
                .lastName("Aillon").build();

        updateAuthorRequest = UpdateAuthorRequest.builder()
                .name("Felipe Ignacio")
                .lastName("Aillon").build();

        updatedAuthorResponse = AuthorResponse.builder()
                .id(1L)
                .name("Felipe Ignacio")
                .lastName("Aillon").build();
    }

    @Test
    void whenGetAllAuthors_success() throws Exception {

        Mockito.when(getAllAuthorsUseCase.getAllAuthors())
                .thenReturn(authorResponseList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/authors/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Felipe")))
                .andExpect(jsonPath("$[0].last_name", is("Aillon")));

    }

    @Test
    void whenGetAuthor_success() throws Exception {
        Mockito.when(getAuthorUseCase.getAuthor(10L))
                .thenReturn(authorResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/authors/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.name", is("Felipe")))
                .andExpect(jsonPath("$.last_name", is("Aillon")));
    }

    @Test
    void whenCreateAuthor_success() throws Exception {
        Mockito.when(createAuthorUseCase.createAuthor(createAuthorRequest))
                .thenReturn(authorResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/authors/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createAuthorRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.name", is("Felipe")))
                .andExpect(jsonPath("$.last_name", is("Aillon")));
    }

    @Test
    void whenUpdateAuthor_success() throws Exception {
        Mockito.when(updateAuthorUseCase.updateAuthor(1L, updateAuthorRequest) )
                .thenReturn(updatedAuthorResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateAuthorRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Felipe Ignacio")));
    }

    @Test
    void whenDeleteAuthor_success() throws Exception {
        doNothing().when(deleteAuthorUseCase).deleteAuthor(1L);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}