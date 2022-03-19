package Console;

import Console.ConsoleInput;
import Console.ConsoleOutput;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.junit.jupiter.api.Assertions.*;

class ConsoleInputTest {

    ConsoleOutput consoleOutput = new ConsoleOutput();
    ConsoleInput consoleInput = new ConsoleInput(consoleOutput);

    @Test
    void getString() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("yes");
        consoleInput.setUserInput(mockScanner);
        assertEquals("yes", consoleInput.getString());
    }

    @Test
    void convertToInteger() {
        assertEquals(3 , consoleInput.convertToInteger("3"));
    }

    @Test
    void convertToIntegerWithString(){
        NumberFormatException thrown = assertThrows(NumberFormatException.class, ()  -> {consoleInput.convertToInteger("one");},  "NumberFormatException was expected");
    }

    @Test
    void getInteger() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("3");
        consoleInput.setUserInput(mockScanner);
        assertEquals(3, consoleInput.getInteger());
    }

    @Test
    void getIntegerFirstString(){
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("one").thenReturn("25");
        consoleInput.setUserInput(mockScanner);
        assertEquals(25, consoleInput.getInteger());
    }


}