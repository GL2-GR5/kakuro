package fr.mcgcorp.profile;

// Options propres à chaque profil
public class SettingsProfile {
  // Mode papier : aucune détection d’infraction des règles
  private Boolean paperMode;

  /* Différents types de saisie des chiffres dans les cases :
   * clavier
   * souris
   */
  private Boolean activateKeyboard;
  private Boolean activateMouse;

  // Mode plein écran
  private Boolean fullscreen;

  // Constructeur des options de profil par défaut
  SettingsProfile() {
    this.paperMode = false;
    this.activateKeyboard = true;
    this.activateMouse = true;
    this.fullscreen = false;
  }

  // Getters des options
  /**
   * Getter.
   */
  public Boolean getPaperMode() {
    return this.paperMode;
  }

  /**
   * Getter.
   */
  public Boolean getKeyboardState() {
    return this.activateKeyboard;
  }

  /**
   * Getter.
   */
  public Boolean getMouseState() {
    return this.activateMouse;
  }

  /**
   * Getter.
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
