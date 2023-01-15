package br.com.gabrielmotta.modules.produto.controller;

import br.com.gabrielmotta.modules.comum.exception.NotFoundException;
import br.com.gabrielmotta.modules.produto.dto.ProdutoRequest;
import br.com.gabrielmotta.modules.produto.dto.ProdutoResponse;
import br.com.gabrielmotta.modules.produto.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

import static br.com.gabrielmotta.modules.helper.ProdutoHelper.produto;
import static br.com.gabrielmotta.modules.helper.ProdutoHelper.produtoRequest;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProdutoControllerTest {

    private static final String URL = "/produtos";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProdutoService service;

    @Test
    @SneakyThrows
    void save_ok_seRequestValido() {
        mvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(produtoRequest())))
            .andExpect(status().isOk());

        verify(service).save(produtoRequest());
    }

    @Test
    @SneakyThrows
    void save_badRequest_seRequestVazio() {
        mvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(new ProdutoRequest("", null))))
            .andExpect(status().isBadRequest());

        verify(service, never()).save(new ProdutoRequest("", null));
    }

    @Test
    @SneakyThrows
    void getAll_Ok_quandoChamado() {
        when(service.getAll())
            .thenReturn(List.of(ProdutoResponse.of(produto(1)), ProdutoResponse.of(produto(2))));

        mvc.perform(get(URL))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].descricao").value("Produto 01"))
            .andExpect(jsonPath("$[0].preco").value(2000.0))
            .andExpect(jsonPath("$[1].id").value(2))
            .andExpect(jsonPath("$[1].descricao").value("Produto 02"))
            .andExpect(jsonPath("$[1].preco").value(2000.0));

        verify(service).getAll();
    }

    @Test
    @SneakyThrows
    void getById_ok_seProdutoExiste() {
        when(service.getById(1))
            .thenReturn(ProdutoResponse.of(produto(1)));

        mvc.perform(get(URL + "/{id}", 1))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.descricao").value("Produto 01"))
            .andExpect(jsonPath("$.preco").value(2000.0));

        verify(service).getById(1);
    }

    @Test
    @SneakyThrows
    void getById_notFound_seProdutoNaoExiste() {
        doThrow(new NotFoundException("Produto não encontrado!"))
            .when(service).getById(1);

        mvc.perform(get(URL + "/{id}", 1))
            .andExpect(status().isNotFound())
            .andExpect(content().string("{\"message\":\"Produto nÃ£o encontrado!\",\"field\":null}"));

        verify(service).getById(1);
    }

    @Test
    @SneakyThrows
    void update_ok_seRequestValido() {
        mvc.perform(put(URL + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(produtoRequest())))
            .andExpect(status().isOk());

        verify(service).update(1, produtoRequest());
    }

    @Test
    @SneakyThrows
    void update_badRequest_seRequestInvalido() {
        mvc.perform(put(URL + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(ProdutoRequest.builder().build())))
            .andExpect(status().isBadRequest());

        verify(service, never()).update(1, ProdutoRequest.builder().build());
    }

    @Test
    @SneakyThrows
    void delete_ok_seProdutoExiste() {
        mvc.perform(delete(URL + "/{id}", 1))
            .andExpect(status().isOk());

        verify(service).delete(1);
    }

    @Test
    @SneakyThrows
    void delete_notFound_seProdutoNaoExiste() {
        doThrow(new NotFoundException("Produto não encontrado!"))
            .when(service).delete(1);

        mvc.perform(delete(URL + "/{id}", 1))
            .andExpect(status().isNotFound())
            .andExpect(content().string("{\"message\":\"Produto nÃ£o encontrado!\",\"field\":null}"));

        verify(service).delete(1);
    }

    private static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.writeValueAsBytes(object);
    }
}