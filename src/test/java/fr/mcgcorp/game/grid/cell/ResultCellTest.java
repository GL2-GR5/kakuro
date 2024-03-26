package fr.mcgcorp.game.grid.cell;

import fr.mcgcorp.Tests;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la classe ResultCell.
 * Cette classe contient des méthodes de test pour vérifier le comportement de la classe ResultCell.
 *
 * @author Le Luët Hôa
 */
public class ResultCellTest extends Tests {

    /**
     * Méthode exécutée avant tous les tests.
     * Affiche le nom de la classe de test au début de l'exécution.
     */
    @BeforeAll
    public static void initAll(){
        printNameAtStart(ResultCellTest.class);
    }

    /**
     * Méthode exécutée après tous les tests.
     * Affiche le nom de la classe de test à la fin de l'exécution.
     */
    @AfterAll
    public static void tearDownAll(){
        printNameAtEnd(ResultCellTest.class);
    }

    /**
     * Test pour vérifier que l'initialisation de ResultCell ne retourne pas null.
     * La valeur attendue est un objet non null car nous avons initialisé ResultCell avec les lignes 1 et 2.
     */
    @Test
    public void InitializeResultCell_ShouldNotReturnNull(){
        ResultCell resultCell = new ResultCell(1, 2);
        assertNotNull(resultCell);
    }

    /**
     * Test pour vérifier que la méthode getLine() de la classe ResultCell retourne la bonne valeur de ligne.
     * La valeur attendue est 1, car nous avons initialisé ResultCell avec la ligne 1.
     */
    @Test
    public void getLine_ShouldReturnCorrectLineValue(){
        ResultCell resultCell = new ResultCell(1, 2);
        assertEquals(1, resultCell.getLine());
    }

    /**
     * Test pour vérifier que la méthode getColumn() de la classe ResultCell retourne la bonne valeur de colonne.
     * La valeur attendue est 2, car nous avons initialisé ResultCell avec la colonne 2.
     */
    @Test
    public void getColumn_ShouldReturnCorrectColumnValue(){
        ResultCell resultCell = new ResultCell(1, 2);
        assertEquals(2, resultCell.getColumn());
    }
}