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
  SettingsProfile() {
    this.paperMode = false;
    this.activateKeyboard = true;
    this.activateMouse = true;
    this.fullscreen = false;
  }
  
  // Getters des options
  /**
   * Getter.
   * @return l'option demandée
   */
  public Boolean getPaperMode() {
    return this.paperMode;
  }

  /**
   * Getter.
   * @return l'option demandée
   */
  public Boolean getKeyboardState() {
    return this.activateKeyboard;
  }

  /**
   * Getter.
   * @return l'option demandée
   */
  public Boolean getMouseState() {
    return this.activateMouse;
  }

  /**
   * Getter.
   * @return l'option demandée
   */
  public Boolean getFullscreen() {
    return this.fullscreen;
  }

  // Setters des options
  /**
   * Setter.
   * @param paperMode valeur de l'option à modifier
   */
  public void setPaperMode(Boolean paperMode) {
    this.paperMode = paperMode;
  }

  /**
   * Setter.
   * @param activateKeyboard valeur de l'option à modifier
   */
  public void setKeyboardState(Boolean activateKeyboard) {
    this.activateKeyboard = activateKeyboard;
  }

  /**
   * Setter.
   * @param activateMouse valeur de l'option à modifier
   */
  public void setMouseState(Boolean activateMouse) {
    this.activateMouse = activateMouse;
  }

  /**
   * Setter.
   * @param fullscreen valeur de l'option à modifier
   */
  public void setFullscreen(Boolean fullscreen) {
    this.fullscreen = fullscreen;
  }

}
