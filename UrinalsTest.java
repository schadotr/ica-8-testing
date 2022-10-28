import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class UrinalsTest {
    InputStream inputStream;
    ByteArrayInputStream byteArrayInputStream;

    @BeforeEach
    void setUp() {
        System.out.println("Setting up the Input Stream for Input Tests");
        inputStream = System.in;
    }

    @AfterEach
    void tearDown() {
        System.out.println("Setting up the Input Stream to it original state");
        System.setIn(inputStream);
    }

    @Test
    @Disabled
    void testInvalidUserInput() {
        System.out.println("Tests an invalid User Input for the User Menu");
        byteArrayInputStream = new ByteArrayInputStream("False String".getBytes());
        System.setIn(byteArrayInputStream);
        Assertions.assertEquals(-1, Urinals.getChoice());
    }

    @Test
    @Disabled
    void testInputFromUserInput() {
        System.out.println("Tests a valid User Input for the User Menu");
        byteArrayInputStream = new ByteArrayInputStream("1".getBytes());
        System.setIn(byteArrayInputStream);
        Assertions.assertEquals(1, Urinals.getChoice());
    }

    @Test
    @Disabled
    void testInputFromFileInput() {
        System.out.println("Tests a valid User Input for the User Menu");
        byteArrayInputStream = new ByteArrayInputStream("2".getBytes());
        System.setIn(byteArrayInputStream);
        Assertions.assertEquals(2, Urinals.getChoice());
    }

    @Test
    @Disabled
    void testExitInput() {
        System.out.println("Tests exit User Input for the User Menu");
        byteArrayInputStream = new ByteArrayInputStream("3".getBytes());
        System.setIn(byteArrayInputStream);
        Assertions.assertEquals(3, Urinals.getChoice());
    }

    @Test
    void testLetterString() {
        System.out.println("Tests an incorrect Urinal String Entered which contains characters other than 0 or 1");
        Assertions.assertFalse(Urinals.validateString("abcd"));
    }

    @Test
    void testNumberStringExceptExpected() {
        System.out.println("Tests an incorrect Urinal String Entered which contains numbers other than 0 or 1");
        Assertions.assertFalse(Urinals.validateString("0102"));
    }

    @Test
    void testInValidUrinalSequence() {
        System.out.println("Tests an incorrect Urinal String Sequence where we have consecutive 1s");
        Assertions.assertFalse(Urinals.validateString("1101"));
    }

    @Test
    void testInvalidStringLength(){
        System.out.println("Tests an incorrect Urinal String Length where length is > 20");
        Assertions.assertFalse(Urinals.validateString("0000000000000000000000"));
    }

    @Test
    void testValidateValidUrinalCount() {
        System.out.println("Tests a correct Urinal String Sequence");
        Assertions.assertEquals(0, Urinals.countUrinals("1001"));
    }

    @Test
    void testCountOneUrinal() {
        System.out.println("Tests a Urinal String where only 1 urinal is present");
        Assertions.assertEquals(1, Urinals.countUrinals("0"));
    }

    @Test
    void testFileNotFound() {
        System.out.println("Tests File Not found");
        Assertions.assertLinesMatch(new ArrayList<>(), Urinals.getInputFromFile("urinalss.dat"));
    }

    @Test
    void testCheckFileContent(){
        System.out.println("Tests File Contents are proper");
        List<String> testString = new ArrayList<>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("testUrinals.dat"));
            String line = bufferedReader.readLine();
            while (line != null) {
                if(line.equals("-1")) break;
                testString.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException ioException) {
            System.out.println("I/O Exception occurred");
        }
        Assertions.assertLinesMatch(testString, Urinals.getInputFromFile("urinals.dat"));
    }

    @Test
    void testCheckFileIncrement(){
        System.out.println("Tests is file is incremented if a given file exists");
        Assertions.assertEquals("rule3.txt", Urinals.getRecentFileName());
    }
}