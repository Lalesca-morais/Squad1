package br.com.zup.squad1.service.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CupTest {
    private Cup cup;

    @BeforeEach
    void setUp(){
        cup = new Cup();
    }

    @Test
    @DisplayName("Testa se o 'converter' para grama retorna true")
    void t1(){
        String result = cup.converter(10,1);
        assertEquals("2400 grama(s)",result);
    }

    @Test
    @DisplayName("Testa se o 'converter' para litro retorna true")
    void t2(){
        String result = cup.converter(10,2);
        assertEquals("2 litro(s)",result);
    }

    @Test
    @DisplayName("Testa se o 'converter' para colher de sopa retorna true")
    void t3(){
        String result = cup.converter(10,3);
        assertEquals("160 colher de sopa(s)",result);
    }

    @Test
    @DisplayName("Testa se o 'converter' lança execeção")
    void t4() throws IllegalArgumentException{
        assertThrows(IllegalArgumentException.class, () -> cup.converter(10,5));
    }
}