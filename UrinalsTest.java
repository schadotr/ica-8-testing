import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class UrinalsTest {
    InputStream inputStream;
    ByteArrayInputStream byteArrayInputStream;

    @BeforeEach
    void setUp() {
        inputStream = System.in;
    }

    @AfterEach
    void tearDown() {
        System.setIn(inputStream);
    }

    @Test
    void testInvalidUserInput() {
        byteArrayInputStream = new ByteArrayInputStream("False String".getBytes());
        System.setIn(byteArrayInputStream);
        Assertions.assertEquals(-1, Urinals.getChoice());
    }

    @Test
    void testInputFromUserInput(){
        byteArrayInputStream = new ByteArrayInputStream("1".getBytes());
        System.setIn(byteArrayInputStream);
        Assertions.assertEquals(1, Urinals.getChoice());
    }

    @Test
    void testInputFromFileInput(){
        byteArrayInputStream = new ByteArrayInputStream("2".getBytes());
        System.setIn(byteArrayInputStream);
        Assertions.assertEquals(2, Urinals.getChoice());
    }

    @Test
    void testExitInput(){
        byteArrayInputStream = new ByteArrayInputStream("3".getBytes());
        System.setIn(byteArrayInputStream);
        Assertions.assertEquals(3, Urinals.getChoice());
    }

    @Test
    void testLetterString(){
        Assertions.assertEquals(false, Urinals.validateString("abcd"));
    }
}