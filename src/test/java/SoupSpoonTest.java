import org.example.service.converter.SoupSpoon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SoupSpoonTest {
    private SoupSpoon soupSpoon;

    @BeforeEach
    void setUp(){
        soupSpoon = new SoupSpoon();
    }

    @Test
    @DisplayName("Testa se o 'converter' para grama retorna true")
    void t1(){
        String result = soupSpoon.converter(10,1);
        assertEquals("150 grama(s)",result);
    }

    @Test
    @DisplayName("Testa se o 'converter' para litro retorna true")
    void t2(){
        String result = soupSpoon.converter(10,2);
        assertEquals("0 litro(s)",result);
    }

    @Test
    @DisplayName("Testa se o 'converter' para xícara retorna true")
    void t3(){
        String result = soupSpoon.converter(10,3);
        assertEquals("1 xícara(s)",result);
    }

    @Test
    @DisplayName("Testa se o 'converter' lança execeção")
    void t4() throws IllegalArgumentException{
        assertThrows(IllegalArgumentException.class, () -> soupSpoon.converter(10,5));
    }
}