package Structure;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

class LoadConfigTest {

    LoadConfig loadConfig = new LoadConfig();

    @Test
    void populatePropertyData() {
        loadConfig.populatePropertyData();
        assertTrue(loadConfig.getConfig().size() > 2);
    }


    @Test
    void getConfig() {
        populatePropertyData();
    }
}