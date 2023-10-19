import br.com.zup.squad1.service.converter.Liters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LitersTest {

    private Liters liters;

    @BeforeEach
    void setUp(){
        liters = new Liters();
    }

    @Test
    @DisplayName("Testa se o 'converter' para grama retorna true")
    void t1(){
        String result = liters.converter(10,1);
        assertEquals("10000 grama(s)",result);
    }

    @Test
    @DisplayName("Testa se o 'converter' para xícara retorna true")
    void t2(){
        String result = liters.converter(10,2);
        assertEquals("42 xícara(s)",result);
    }

    @Test
    @DisplayName("Testa se o 'converter' para colher de sopa retorna true")
    void t3(){
        String result = liters.converter(10,3);
        assertEquals("670 colher de sopa(s)",result);
    }

    @Test
    @DisplayName("Testa se o 'converter' lança execeção")
    void t4() throws IllegalArgumentException{
        assertThrows(IllegalArgumentException.class, () -> liters.converter(10,5));
    }
}