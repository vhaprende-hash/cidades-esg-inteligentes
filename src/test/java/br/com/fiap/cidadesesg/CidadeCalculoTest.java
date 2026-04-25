package br.com.fiap.cidadesesg;

import br.com.fiap.cidadesesg.model.Cidade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CidadeCalculoTest {

    @Test
    void deveCalcularIndiceEsgComoMediaDasNotas() {
        Cidade cidade = new Cidade("Guarulhos", "sp", 80, 70, 90);

        cidade.calcularIndice();

        assertEquals(80.0, cidade.getIndiceEsg());
        assertEquals("SP", cidade.getEstado());
    }
}
