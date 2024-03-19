package fr.mcgcorp.game.move;

import fr.mcgcorp.Tests;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * La classe MoveTest étend la classe Tests. Cette classe est utilisée pour tester les fonctionnalités des classes Move, MoveValue et MoveNotes.
 * Elle contient des méthodes de test pour vérifier le bon fonctionnement des méthodes de ces classes.
 * Chaque méthode de test vérifie une fonctionnalité spécifique de la classe correspondante.
 *
 * @author Le Luët Hôa
 */
public class MoveTest extends Tests {

    /**
     * Cette méthode est exécutée une fois avant tous les tests de cette classe.
     * Elle appelle la méthode printNameAtStart de la classe parente Tests pour afficher le nom de la classe testée.
     */
    @BeforeAll
    public static void initAll() {printNameAtStart(MoveTest.class);}

    /**
     * Cette méthode est exécutée à la fin de tous les tests de cette classe.
     * Elle appelle la méthode printNameAtEnd de la classe parente Tests pour afficher le nom de la classe testée.
     */
    @AfterAll
    public static void endAll() {printNameAtEnd(MoveTest.class);}

    /**
     * Teste l'initialisation de la classe MoveNotes sans valeurs.
     */
    @Test
    public void InitializeMoveNotes_ShouldBeInitializedWithoutValues() {
        MoveNotes move = new MoveNotes(null, null);
        assertNotNull(move);
        assertNull(move.getOld());
        assertNull(move.getNew());
    }

    /**
     * Teste la méthode getOld de la classe MoveNotes.
     */
    @Test
    public void MoveNotes_GetOld_ShouldReturnOldNotes() {
        Set<Integer> notesOld = new HashSet<>(Arrays.asList(1, 2, 3));
        MoveNotes move = new MoveNotes(notesOld, null);
        assertEquals(notesOld, move.getOld());
    }

    /**
     * Teste la méthode getNew de la classe MoveNotes.
     */
    @Test
    public void MoveNotes_GetNew_ShouldReturnNewNotes() {
        Set<Integer> notesNew = new HashSet<>(Arrays.asList(1, 2, 3));
        MoveNotes move = new MoveNotes(null, notesNew);
        assertEquals(notesNew, move.getNew());
    }

    /**
     * Teste l'initialisation de la classe MoveValue sans valeurs.
     */
    @Test
    public void InitializeMoveValue_ShouldBeInitializedWithoutValues() {
        MoveValue move = new MoveValue(0, 0);
        assertNotNull(move);
        assertEquals(0, move.getOld());
        assertEquals(0, move.getNew());
    }

    /**
     * Teste la méthode getOld de la classe MoveValue.
     */
    @Test
    public void MoveValue_GetOld_ShouldReturnOldValue() {
        MoveValue move = new MoveValue(1, 0);
        assertEquals(1, move.getOld());
    }

    /**
     * Teste la méthode getNew de la classe MoveValue.
     */
    @Test
    public void MoveValue_GetNew_ShouldReturnNewValue() {
        MoveValue move = new MoveValue(0, 1);
        assertEquals(1, move.getNew());
    }
}
