package br.com.gabrielmotta.modules.pedido.service;

import br.com.gabrielmotta.modules.comum.exception.NotFoundException;
import br.com.gabrielmotta.modules.pedido.enums.EStatusPedido;
import br.com.gabrielmotta.modules.pedido.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.gabrielmotta.modules.helper.ClienteHelper.clientePedidoResponse;
import static br.com.gabrielmotta.modules.helper.ClienteHelper.enderecoResponse;
import static br.com.gabrielmotta.modules.helper.PedidoHelper.pedido;
import static br.com.gabrielmotta.modules.helper.ProdutoHelper.produtoResponse;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @InjectMocks
    private PedidoService service;
    @Mock
    private PedidoRepository repository;

    @Test
    void findAll_retornarListaDePedidos_seExistir() {
        when(repository.findAll())
            .thenReturn(List.of(pedido(1), pedido(2)));

        assertThat(service.findAll())
            .hasSize(2)
            .flatExtracting("id", "dataCriacao", "cliente", "produtos", "enderecoEntrega", "status", "valorTotal")
            .containsExactly(
                1, LocalDateTime.of(2022, 1, 15, 8, 0), clientePedidoResponse(), List.of(produtoResponse(1)), enderecoResponse(),
                EStatusPedido.CRIADO, 2000.0,
                2, LocalDateTime.of(2022, 1, 15, 8, 0), clientePedidoResponse(), List.of(produtoResponse(2)), enderecoResponse(),
                EStatusPedido.CRIADO, 2000.0
            );
    }

    @Test
    void findAll_retornarListaVazia_seNaoExistirPedidos() {
        when(repository.findAll())
            .thenReturn(Collections.emptyList());

        assertThat(service.findAll()).isEmpty();
    }

    @Test
    void detalhar_deveRetornarPedidoResponse_seExistirPedido() {
        when(repository.findById(1))
            .thenReturn(Optional.of(pedido(1)));

        assertThat(service.detalhar(1))
            .extracting("id", "dataCriacao", "cliente", "produtos", "enderecoEntrega", "status", "valorTotal")
            .containsExactly(
                1, LocalDateTime.of(2022, 1, 15, 8, 0), clientePedidoResponse(), List.of(produtoResponse(1)), enderecoResponse(),
                EStatusPedido.CRIADO, 2000.0);
    }

    @Test
    void detalhar_deveLancarExcecao_seNaoExistirPedido() {
        when(repository.findById(1))
            .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.detalhar(1))
            .hasMessage("Pedido não encontrado.")
            .isExactlyInstanceOf(NotFoundException.class);
    }

    @Test
    void delete_deveExcluirPedido_seExistirPedido() {
        when(repository.existsById(1))
            .thenReturn(true);

        assertThatCode(() -> service.delete(1))
            .doesNotThrowAnyException();

        verify(repository).deleteById(1);
    }

    @Test
    void delete_deveLancarExcecao_seNaoExistirPedido() {
        when(repository.existsById(1))
            .thenReturn(false);

        assertThatThrownBy(() -> service.delete(1))
            .hasMessage("Pedido não encontrado.")
            .isExactlyInstanceOf(NotFoundException.class);
    }
}