package br.com.gabrielmotta.modules.cliente.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@ExtendWith(SpringExtension.class)
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository repository;

    @Test
    void existsByCpf_true_quandoExistir() {
        assertThat(repository.existsByCpf("12345678900")).isTrue();
    }

    @Test
    void existsByCpf_false_quandoNaoExistir() {
        assertThat(repository.existsByCpf("98765432100")).isFalse();
    }
}