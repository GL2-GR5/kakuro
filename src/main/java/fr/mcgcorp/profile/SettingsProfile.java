package fr.mcgcorp.profile;

/**
 * Options propres à chaque profil.
 * 
 * @author HOUGET Julien
 */
public class SettingsProfile {
  /**
   * Mode papier : aucune détection d’infraction des règles.
   */
  private Boolean paperMode;
  
  /**
   * Différents types de saisie des chiffres dans les cases.
   * Clavier
   */
  private Boolean activateKeyboard;

  /**
   * Différents types de saisie des chiffres dans les cases.
   * Souris
   */
  private Boolean activateMouse;

  /**
   * Mode plein écran.
   */
  private Boolean fullscreen;
  
  /** 
   * Constructeur des options de profil par défaut.
   */
  protected SettingsProfile() {
    this.paperMode = false;
    this.activateKeyboard = true;
    this.activateMouse = true;
    this.fullscreen = false;
  }

  /**
   * Méthode pour connaître l'état de l'option mode papier.
   * 
   * @return l'option demandée
   */
  public Boolean getPaperMode() {
    return this.paperMode;
  }

  /**
   * Méthode pour connaître l'état de l'option clavier.
   * 
   * @return l'option demandée
   */
  public Boolean getKeyboardState() {
    return this.activateKeyboard;
  }

  /**
   * Méthode pour connaître l'état de l'option souris.
   * 
   * @return l'option demandée
   */
  public Boolean getMouseState() {
    return this.activateMouse;
  }

  /**
   * Méthode pour connaître l'état de l'option plein écran.
   * 
   * @return l'option demandée
   */
  public Boolean getFullscreen() {
    return this.fullscreen;
  }

  // Setters des options
  /**
   * Méthode pour activer le mode papier.
   * en mode papier, aucune détection d’infraction des règles.
   * 
   * @param paperMode valeur de l'option à modifier
   */
  public void setPaperMode(Boolean paperMode) {
    this.paperMode = paperMode;
  }

  /**
   * Méthode pour activer le clavier.
   * 
   * @param activateKeyboard valeur de l'option à modifier
   */
  public void setKeyboardState(Boolean activateKeyboard) {
    this.activateKeyboard = activateKeyboard;
  }

  /**
   * Méthode pour activer la souris.
   * 
   * @param activateMouse valeur de l'option à modifier
   */
  public void setMouseState(Boolean activateMouse) {
    this.activateMouse = activateMouse;
  }

  /**
   * Méthode pour activer le mode plein écran.
   * 
   * @param fullscreen valeur de l'option à modifier
   */
  public void setFullscreen(Boolean fullscreen) {
    this.fullscreen = fullscreen;
  }
}