package com.cpujob.crud.leitura.leitura.controller;


import com.cpujob.crud.leitura.leitura.builder.LivroDTOBuilder;
import com.cpujob.crud.leitura.leitura.dto.LivroDTO;
import com.cpujob.crud.leitura.leitura.model.Livro;
import com.cpujob.crud.leitura.leitura.service.LivroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.patch;
import static com.cpujob.crud.leitura.leitura.utils.JsonConvertionUtils.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class LivroControllerTest {

    private static final String LIVRO_API_URL_PATH = "/api/v1/livros";
    private static final long VALID_LIVRO_ID = 1L;
    private static final long INVALID_LIVRO_ID = 2L;

    private MockMvc mockMvc;

    @Mock
    private LivroService livroService;

    @InjectMocks
    private LivroController livroController;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(livroController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThemABeerIsCreated() throws Exception {
        //given
        LivroDTO livroDTO = LivroDTOBuilder.builder().build().toLivroDTO();

        //when
        when(livroService.createLivro(livroDTO)).thenReturn(livroDTO);

        //them
        mockMvc.perform(post(LIVRO_API_URL_PATH)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(livroDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(livroDTO.getName())))
                .andExpect(jsonPath("$.autor", is(livroDTO.getAutor())))
                .andExpect(jsonPath("$.dataLeitura", is(livroDTO.getDataLeitura())))
                .andExpect(jsonPath("$.status", is(livroDTO.getStatus())))
                .andExpect(jsonPath("$.descricao", is(livroDTO.getDescricao())));
    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {
        //given
        LivroDTO livroDTO = LivroDTOBuilder.builder().build().toLivroDTO();
        livroDTO.setAutor(null);

        //then
        mockMvc.perform(post(LIVRO_API_URL_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(livroDTO)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void whenGETIsCalledWithValidNameThenOkStatusIsReturned() throws Exception {
        //given
        LivroDTO livroDTO = LivroDTOBuilder.builder().build().toLivroDTO();

        //when
        when(livroService.findByName(livroDTO.getName())).thenReturn(livroDTO);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(LIVRO_API_URL_PATH + "/" + livroDTO.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name", is(livroDTO.getName())))
                        .andExpect(jsonPath("$.autor", is(livroDTO.getAutor())));
    }
}
