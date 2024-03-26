package fr.mcgcorp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Classe principale de Test.
 * <img src="doc-files/Main.png" alt="Logo"/>
 */
@ExtendWith(VerifTest.class)
public class TestMain {


    /**
     * Cette méthode est exécutée une fois avant tous les tests de cette classe.
     */
    @BeforeAll
    public static void initAll() {
        System.out.println("\033[31;1m================================ DÉBUT TestMain ================================\033[0m");
    }

    /**
     * Cette méthode est exécutée à la fin de tous les tests de cette classe.
     */
    @AfterAll
    public static void endAll() {
        System.out.println("\033[31;1m================================  FIN TestMain  ================================\033[0m");
    }

    /**
     * Test 1.
     */
    @Test
    public void test1() {

    }
    
}
