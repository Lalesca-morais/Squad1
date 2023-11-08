package br.com.zup.squad1.service.converter;

import br.com.zup.squad1.exceptions.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GramTest {

    private Gram gram;

    @BeforeEach
    void setUp(){
        gram = new Gram();
    }

    @Test
    @DisplayName("Testa se o 'converter' para litros retorna true")
    void t1(){
        String result = gram.converter(10,1);
        assertEquals("0 litro(s)",result);
    }

    @Test
    @DisplayName("Testa se o 'converter' para xícara retorna true")
    void t2(){
        String result = gram.converter(10,2);
        assertEquals("0 xícara(s)",result);
    }

    @Test
    @DisplayName("Testa se o 'converter' para colher de sopa retorna true")
    void t3(){
        String result = gram.converter(10,3);
        assertEquals("1 colher de sopa(s)",result);
    }

    @Test
    @DisplayName("Testa se o 'converter' lança execeção")
    void t4() throws BadRequestException {
        assertThrows(BadRequestException.class, () -> gram.converter(10,5));
    }
}