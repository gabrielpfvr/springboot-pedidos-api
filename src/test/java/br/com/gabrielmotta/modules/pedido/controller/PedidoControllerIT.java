package br.com.gabrielmotta.modules.pedido.controller;

import br.com.gabrielmotta.modules.pedido.enums.EStatusPedido;
import br.com.gabrielmotta.modules.pedido.repository.PedidoRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static br.com.gabrielmotta.modules.helper.PedidoHelper.pedidoRequest;
import static br.com.gabrielmotta.modules.helper.TestHelper.convertObjectToJsonBytes;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@Transactional
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PedidoControllerIT {

    private static final String URL = "/pedidos";

    @Autowired
    private MockMvc mvc;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private EntityManager em;

    @Test
    @Order(1)
    @Commit
    @SneakyThrows
    void create_deveCriarPedido_quandoReceberRequest() {
        mvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(pedidoRequest(1, List.of(3, 4), 1))))
            .andExpect(status().isOk());
        em.flush();
        em.clear();

        var pedidoSalvo = pedidoRepository.findById(1).orElseThrow();

        assertThat(pedidoSalvo)
            .extracting("id", "enderecoEntregaId", "statusPedido")
            .containsExactly(1, 1, EStatusPedido.CRIADO
            );
        assertThat(pedidoSalvo.getCliente())
            .extracting("id", "nome", "cpf", "email", "telefone")
            .containsExactly(1, "Gabriel Motta", "12345678900", "gabriel@email.com", "43988776655");
        assertThat(pedidoSalvo.getCliente().getEnderecosEntrega())
            .flatExtracting("id", "estado", "cidade", "logradouro", "numero", "cep", "bairro")
            .containsExactly(1, "PR", "Londrina", "Rua ABCD", 60, "88000000", "Centro");
        assertThat(pedidoSalvo.getProdutos())
            .extracting("id", "descricao", "preco")
            .containsExactly(
                tuple(3, "Headset wireless", 700.0),
                tuple(4, "Teclado mecanico", 500.0)
            );
    }

    @Test
    @Order(2)
    @SneakyThrows
    void update_deveAtualizarPedido_quandoReceberRequest() {
        mvc.perform(put(URL + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(pedidoRequest(1, List.of(3, 7), 1))))
            .andExpect(status().isOk());
        em.flush();
        em.clear();

        var pedidoSalvo = pedidoRepository.findById(1).orElseThrow();

        assertThat(pedidoSalvo)
            .extracting("id", "enderecoEntregaId", "statusPedido")
            .containsExactly(1, 1, EStatusPedido.CRIADO
            );
        assertThat(pedidoSalvo.getCliente())
            .extracting("id", "nome", "cpf", "email", "telefone")
            .containsExactly(1, "Gabriel Motta", "12345678900", "gabriel@email.com", "43988776655");
        assertThat(pedidoSalvo.getCliente().getEnderecosEntrega())
            .flatExtracting("id", "estado", "cidade", "logradouro", "numero", "cep", "bairro")
            .containsExactly(1, "PR", "Londrina", "Rua ABCD", 60, "88000000", "Centro");
        assertThat(pedidoSalvo.getProdutos())
            .extracting("id", "descricao", "preco")
            .containsExactly(
                tuple(3, "Headset wireless", 700.0),
                tuple(7, "Fender Stratocaster", 2500.0)
            );
    }
}