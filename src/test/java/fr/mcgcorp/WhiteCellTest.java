package fr.mcgcorp;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour WhiteCell.
 * Cette classe contient des méthodes de test pour tester le comportement de la classe WhiteCell.
 * @author Hôa Le Luët
 */
public class WhiteCellTest extends Tests{

    /**
     * Cette méthode est exécutée une fois avant tous les tests de cette classe.
     * Elle appelle la méthode printNameAtStart de la classe parente Tests pour afficher le nom de la classe testée.
     */
    @BeforeAll
    public static void initAll() {
        printNameAtStart(WhiteCellTest.class);
    }

    /**
     * Cette méthode est exécutée à la fin de tous les tests de cette classe.
     * Elle appelle la méthode printNameAtEnd de la classe parente Tests pour afficher le nom de la classe testée.
     */
    @AfterAll
    public static void endAll() {
        printNameAtEnd(WhiteCellTest.class);
    }

    /**
     * Teste si après l'initialisation de WhiteCell, correctValue donne la valeur donnée à la création.
     */
    @Test
    void InitializeWhiteCell_ShouldCorrectValueHaveValue() {
        WhiteCell whiteCell = new WhiteCell(1);
        assertEquals(1, whiteCell.getCorrectValue());
    }

    /**
     * Teste si après l'initialisation de WhiteCell, value donne 0.
     */
    @Test
    void InitializeWhiteCell_ShouldValueHaveNull() {
        WhiteCell whiteCell = new WhiteCell(1);
        assertEquals(0, whiteCell.getValue());
    }

    /**
     * Teste si après l'initialisation de WhiteCell, les notes nulles.
     */
    @Test
    void InitializeWhiteCell_ShouldNotesHaveNull() {
        WhiteCell whiteCell = new WhiteCell(1);
        assertEquals(0, whiteCell.getNotes().length);
    }

    /**
     * Teste si la méthode setValue permet l'ajout d'une valeur possible.
     */
    @Test
    void SetValue_ShouldValueHavePossibleValue() {
        WhiteCell whiteCell = new WhiteCell(1);
        whiteCell.setValue(1);
        assertEquals(1, whiteCell.getValue());
        whiteCell.setValue(Kakuro.MAX_VALUE + 1);
        assertNotEquals(Kakuro.MAX_VALUE + 1, whiteCell.getValue());
    }

    /**
     * Teste si la méthode SetNote permet d'ajouter une valeur possible dans les notes.
     */
    @Test
    void SetNote_ShouldNotesHavePossibleValue() {
        /*
        Attente de la modif d'Erwan
         */
    }

    /**
     * Teste si la méthode ClearAll supprime toutes les valeurs.
     */
    @Test
    void ClearAll_ShouldValueAndNotesHaveNull() {
        WhiteCell whiteCell = new WhiteCell(1);
        whiteCell.setValue(1);
        whiteCell.setNotes(new int[]{1, 2, 3});
        whiteCell.clearAll();
        assertEquals(0, whiteCell.getValue());
        assertEquals(0, whiteCell.getNotes().length);
    }

    /**
     * Teste si la méthode IsCorrect retourne vrai.
     */
    @Test
    void IsCorrect_ShouldReturnTrue() {
        WhiteCell whiteCell = new WhiteCell(1);
        whiteCell.setValue(1);
        assertTrue(whiteCell.isCorrect());
    }

}