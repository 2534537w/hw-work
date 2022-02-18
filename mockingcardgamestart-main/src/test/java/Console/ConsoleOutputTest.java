package Console;

import Console.ConsoleOutput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleOutputTest {

    ConsoleOutput consoleOutput = new ConsoleOutput();

    @Test
    void outputOne(){
        consoleOutput.output("Test One");
        assertEquals("Test One", consoleOutput.getStoreOutput().get(0));
    }

    @Test
    void outputTwo(){
        consoleOutput.output("Test One");
        consoleOutput.output("Test Two");
        assertEquals("Test Two", consoleOutput.getStoreOutput().get(1));
    }

    @Test
    void getStoreOutput(){
        consoleOutput.output("Test One");
        consoleOutput.output("Test Two");
        assertEquals(2, consoleOutput.getStoreOutput().size());
    }

}