package fr.mcgcorp.game.grid.cell;

import fr.mcgcorp.Tests;
import fr.mcgcorp.game.Game;
import fr.mcgcorp.game.grid.cell.WhiteCell;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour WhiteCell.
 * Cette classe contient des méthodes de test pour tester le comportement de la classe WhiteCell.
 * @author Hôa Le Luët
 */
public class WhiteCellTest extends Tests {

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

    /**MoveNotes
     * Teste si après l'initialisation de WhiteCell, les notes nulles.
     */
    @Test
    void InitializeWhiteCell_ShouldNotesHaveNull() {
        WhiteCell whiteCell = new WhiteCell(1);
        assertEquals(0, whiteCell.getNotes().size());
    }

    /**
     * Teste si la méthode setValue permet l'ajout d'une valeur possible.
     */
    @Disabled
    @Test
    void SetValue_ShouldValueHavePossibleValue() {
        WhiteCell whiteCell = new WhiteCell(1);
        whiteCell.setValue(1);
        assertEquals(1, whiteCell.getValue());
        whiteCell.setValue(Game.getMaxValue() + 1);
        assertNotEquals(Game.getMaxValue() + 1, whiteCell.getValue());
    }

    /**
     * Teste si la méthode SetNote permet d'ajouter une valeur possible dans les notes.
     */
    @Disabled
    @Test
    void SetNote_ShouldNotesHavePossibleValue() {
        WhiteCell whiteCell = new WhiteCell(1);
        whiteCell.setNotes(new HashSet<Integer>(Arrays.asList(1, 2, 3)));
        assertEquals(3, whiteCell.getNotes().size());
        assertTrue(whiteCell.getNotes().containsAll(Arrays.asList(1, 2, 3)));
        Set<Integer> testSet = whiteCell.getNotes();
        testSet.add(15);
        assertNotEquals(whiteCell.getNotes(), testSet);
        whiteCell.setNotes(testSet);
        assertFalse(whiteCell.getNotes().contains(15));
    }

    /**
     * Teste si la méthode ClearAll supprime toutes les valeurs.
     */
    @Disabled
    @Test
    void ClearAll_ShouldValueAndNotesHaveNull() {
        WhiteCell whiteCell = new WhiteCell(1);
        whiteCell.setValue(1);
        whiteCell.setNotes(new HashSet<Integer>(Arrays.asList(1, 2, 3)));
        //whiteCell.;
        assertEquals(0, whiteCell.getValue());
        assertEquals(0, whiteCell.getNotes().size());
    }

    /**
     * Teste si la méthode IsCorrect retourne vrai.
     */
    @Disabled
    @Test
    void IsCorrect_ShouldReturnTrue() {
        WhiteCell whiteCell = new WhiteCell(1);
        whiteCell.setValue(1);
        assertTrue(whiteCell.isCorrect());
    }

}