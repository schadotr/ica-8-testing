import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    void testInputFromUserInput() {
        byteArrayInputStream = new ByteArrayInputStream("1".getBytes());
        System.setIn(byteArrayInputStream);
        Assertions.assertEquals(1, Urinals.getChoice());
    }

    @Test
    void testInputFromFileInput() {
        byteArrayInputStream = new ByteArrayInputStream("2".getBytes());
        System.setIn(byteArrayInputStream);
        Assertions.assertEquals(2, Urinals.getChoice());
    }

    @Test
    void testExitInput() {
        byteArrayInputStream = new ByteArrayInputStream("3".getBytes());
        System.setIn(byteArrayInputStream);
        Assertions.assertEquals(3, Urinals.getChoice());
    }

    @Test
    void testLetterString() {
        Assertions.assertEquals(false, Urinals.validateString("abcd"));
    }

    @Test
    void testNumberStringExceptExpected() {
        Assertions.assertEquals(false, Urinals.validateString("0102"));
    }

    @Test
    void testValidateValidUrinalCount() {
        Assertions.assertEquals(0, Urinals.countUrinals("1001"));
    }

    @Test
    void testCountOneUrinal() {
        Assertions.assertEquals(1, Urinals.countUrinals("0"));
    }

    @Test
    void testFileNotFound() {
        Assertions.assertLinesMatch(new ArrayList<>(), Urinals.getInputFromFile("urinalss.dat"));
    }

    @Test
    void checkFileContent(){
        List<String> testString = new ArrayList<>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("testUrinals.dat"));
            String line = bufferedReader.readLine();
            while (line != null) {
                if(line.equals("-1")) break;
                testString.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException ioException) {
            System.out.println("I/O Exception occurred");
        }
        Assertions.assertLinesMatch(testString, Urinals.getInputFromFile("urinals.dat"));
    }
}