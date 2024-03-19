package fr.mcgcorp.fxml.controllers;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;

public class TutoController extends GameController {

  List tipsList;

  public TutoController() {
    super();
    tipsList = new ArrayList<String>();
    tipsList.add("Bienvenue sur Kakuro. Ton but est de remplir toute la grille avec des chiffres de 1 à 9.");
    tipsList.add("Les résultats indiquent le montant total de la ligne ou la colonne");
    tipsList.add("Autre règle importante : Il est impossible de mettre le même chiffre sur une même ligne ou colonne");
    tipsList.add("Essayons ensemble, Cette case est soumise à deux résulats. Le résultat horizontal vaut 4 et le vertical vaut 3");
    tipsList.add("Rentre 1 dans la case\n3 equivaut à l'addition entre 1 et 2.\n4 equivaut à l'addition entre 1 et 3.\n"
        + "La seule possibilité est donc 1.");
    tipsList.add("Bien joué !\nTu peux maintenant remplir cette case avec un 3 pour completer la ligne.");
    tipsList.add("Bien joué !\nTu peux maintenant remplir cette case avec un 2 pour completer la colonne.");
    tipsList.add("Bravo, tu as maintenant compris comment jouer à Kakuro");


  }

  public void showPopup(String text) {


  }

}
