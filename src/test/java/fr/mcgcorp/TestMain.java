package fr.mcgcorp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Classe principale de Test.
 * @author Hôa Le Luët
 */
@ExtendWith(VerifTest.class)
public class TestMain {

    @BeforeAll
    public static void initAll() {
        System.out.println("\033[31;1m================================ DÉBUT TestMain ================================\033[0m");
    }

    @AfterAll
    public static void endAll() {
        System.out.println("\033[31;1m================================  FIN TestMain  ================================\033[0m");
    }

    @Test
    public void test1() {

    }
    
}
