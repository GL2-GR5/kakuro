package fr.mcgcorp;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Cette classe étend la classe Tests et représente un ensemble spécifique de tests pour les erreurs d'entrée.
 * @author Hôa Le Luët
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
        assertNull(entryError.coord);
        assertNull(entryError.error);
    }

    /**
     * Ce test vérifie que la coordonnée a une valeur lors de l'initialisation du deuxième constructeur de EntryError.
     */
    @Test
    void InitializeConstructorCoord_ShouldCoordHaveValue() {
        Coord coord = Coord.createCoord_graphical(1, 1);
        EntryError entryError = new EntryError(coord);
        assertEquals(coord, entryError.coord);
        assertNull(entryError.error);
    }

    /**
     * Ce test vérifie que l'erreur a une valeur lors de l'initialisation du troisième constructeur de EntryError.
     */
    @Test
    void InitializeConstructorError_ShouldErrorHaveValue() {
        EntryError entryError = new EntryError(TypeEntryError.NOERR);
        assertNull(entryError.coord);
        assertEquals(TypeEntryError.NOERR, entryError.error);
    }

    /**
     * Ce test vérifie que la coordonnée et l'erreur ont une valeur lors de l'initialisation du quatrième constructeur de EntryError.
     */
    @Test
    void InitializeConstructorAll_ShouldCoordAndErrorHaveValue() {
        Coord coord = Coord.createCoord_graphical(1, 1);
        EntryError entryError = new EntryError(coord, TypeEntryError.NOERR);
        assertEquals(coord, entryError.coord);
        assertEquals(TypeEntryError.NOERR, entryError.error);
    }

    /**
     * Ce test vérifie que la méthode getCoord retourne la bonne coordonnée.
     */
    @Test
    void GetCoord_ShouldReturnCorrectCoord() {
        Coord coord = Coord.createCoord_graphical(1, 1);
        EntryError entryError = new EntryError(coord);
        assertEquals(coord, entryError.getCoord());
    }

    /**
     * Ce test vérifie que la méthode setCoord définit correctement la coordonnée.
     */
    @Test
    void SetCoord_ShouldSetCoord() {
        Coord coord = Coord.createCoord_graphical(1, 1);
        EntryError entryError = new EntryError();
        entryError.setCoord(coord);
        assertEquals(coord, entryError.getCoord());
    }

    /**
     * Ce test vérifie que la méthode getError retourne la bonne erreur.
     */
    @Test
    void GetError_ShouldReturnCorrectError() {
        EntryError entryError = new EntryError(TypeEntryError.NOERR);
        assertEquals(TypeEntryError.NOERR, entryError.error);
    }

    /**
     * Ce test vérifie que la méthode setError définit correctement l'erreur.
     */
    @Test
    void SetError_ShouldSetError() {
        EntryError entryError = new EntryError();
        entryError.setTypeEntryError(TypeEntryError.NOERR);
        assertEquals(TypeEntryError.NOERR, entryError.error);
    }
}