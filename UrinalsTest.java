import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

class UrinalsTest {
    InputStream sysInBackup;
    ByteArrayInputStream in;

    @BeforeEach
    void setUp() {
        sysInBackup = System.in;
        in = new ByteArrayInputStream("False String".getBytes());
        System.setIn(in);
    }

    @AfterEach
    void tearDown() {
        System.setIn(sysInBackup);
    }

    @Test
    void testUserInput() {
        System.out.println("====== Checks if the function covers an input which isn't expected =======");
        Assertions.assertEquals(1, Urinals.getChoice());
    }
}