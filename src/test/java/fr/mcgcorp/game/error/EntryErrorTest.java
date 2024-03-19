package fr.mcgcorp.game.error;

import fr.mcgcorp.Tests;
import fr.mcgcorp.game.Coord;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Cette classe étend la classe Tests et représente un ensemble spécifique de tests pour les erreurs d'entrée.
 * @author Le Luët Hôa
 */
public class EntryErrorTest extends Tests {

    /**
     * Cette méthode est exécutée une fois avant tous les tests de cette classe.
     * Elle appelle la méthode printNameAtStart de la classe parente Tests pour afficher le nom de la classe testée.
     */
    @BeforeAll
    public static void initAll() {
        printNameAtStart(EntryErrorTest.class);
    }

    /**
     * Cette méthode est exécutée à la fin de tous les tests de cette classe.
     * Elle appelle la méthode printNameAtEnd de la classe parente Tests pour afficher le nom de la classe testée.
     */
    @AfterAll
    public static void endAll() {
        printNameAtEnd(EntryErrorTest.class);
    }

    /**
     * Ce test vérifie que les variables sont nulles lors de l'initialisation du premier constructeur de EntryError.
     */
    @Test
    void InitializeBaseConstructor_ShouldVariablesHaveNull() {
        EntryError entryError = new EntryError();
        assertNull(entryError.getCoord());
        assertNull(entryError.getError());
    }

    /**
     * Ce test vérifie que la coordonnée a une valeur lors de l'initialisation du deuxième constructeur de EntryError.
     */
    @Disabled
    @Test
    void InitializeConstructorCoord_ShouldCoordHaveValue() {
        //Kakuro game = Kakuro.createGame(10,10);
        Coord coord = new Coord(1,1);
        EntryError entryError = new EntryError(coord);
        assertEquals(coord, entryError.getCoord());
        assertNull(entryError.getError());
    }

    /**
     * Ce test vérifie que l'erreur a une valeur lors de l'initialisation du troisième constructeur de EntryError.
     */
    @Test
    void InitializeConstructorError_ShouldErrorHaveValue() {
        EntryError entryError = new EntryError(TypeEntryError.NO_ERR);
        assertNull(entryError.getCoord());
        assertEquals(TypeEntryError.NO_ERR, entryError.getError());
    }

    /**
     * Ce test vérifie que la coordonnée et l'erreur ont une valeur lors de l'initialisation du quatrième constructeur de EntryError.
     */
    @Disabled
    @Test
    void InitializeConstructorAll_ShouldCoordAndErrorHaveValue() {
        Coord coord = new Coord(1,1);
        EntryError entryError = new EntryError(coord, TypeEntryError.NO_ERR);
        assertEquals(coord, entryError.getCoord());
        assertEquals(TypeEntryError.NO_ERR, entryError.getError());
    }

    /**
     * Ce test vérifie que la méthode getCoord retourne la bonne coordonnée.
     */
    @Disabled
    @Test
    void GetCoord_ShouldReturnCorrectCoord() {
        Coord coord = new Coord(1,1);
        EntryError entryError = new EntryError(coord);
        assertTrue(coord.getLine() == entryError.getCoord().getLine() &&
                coord.getColumn() == entryError.getCoord().getColumn());
    }

    /**
     * Ce test vérifie que la méthode setCoord définit correctement la coordonnée.
     */
    @Disabled
    @Test
    void SetCoord_ShouldSetCoord() {
        Coord coord = new Coord(1,1);
        EntryError entryError = new EntryError();
        entryError.setCoord(coord);
        assertEquals(coord, entryError.getCoord());
    }

    /**
     * Ce test vérifie que la méthode getError retourne la bonne erreur.
     */
    @Test
    void GetError_ShouldReturnCorrectError() {
        EntryError entryError = new EntryError(TypeEntryError.NO_ERR);
        assertEquals(TypeEntryError.NO_ERR, entryError.getError());
    }

    /**
     * Ce test vérifie que la méthode setError définit correctement l'erreur.
     */
    @Test
    void SetError_ShouldSetError() {
        EntryError entryError = new EntryError();
        entryError.setTypeEntryError(TypeEntryError.NO_ERR);
        assertEquals(TypeEntryError.NO_ERR, entryError.getError());
    }
}