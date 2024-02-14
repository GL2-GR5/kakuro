package fr.mcgcorp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.invoke.MethodHandles;

/**
 * Classe principale de Tests.
 * @author Hôa Le Luët
 */
@ExtendWith(VerifTest.class)
public abstract class Tests {


    public static void printNameAtStart(Class<? extends Tests> T){
        System.out.println("\033[31;1m================================ DÉBUT " + T.getSimpleName() + " ================================\033[0m");
    }


    public static void printNameAtEnd(Class<? extends Tests> T){
        System.out.println("\033[31;1m================================  FIN " + T.getSimpleName() + "  ================================\033[0m");
    }
    
}
