package fr.mcgcorp.profile;

import java.util.HashSet;
import java.util.Set;

public class ProfileManager {

  private static final ProfileManager instance = new ProfileManager();
  private final Set<Profile> profiles = new HashSet<>();

  public static ProfileManager getInstance() {
    return instance;
  }

  public void addProfile(Profile profile) {
    profiles.add(profile);
  }

  public void removeProfile(Profile profile) {
    profiles.remove(profile);
  }

  public Set<Profile> getProfiles() {
    return profiles;
  }

  public Profile getProfile(String name) {
    for (Profile profile : profiles) {
      if (profile.getName().equals(name)) {
        return profile;
      }
    }
    return null;
  }

  public boolean isEmpty() {
    return profiles.isEmpty();
  }

  public boolean exists(String name) {
    for (Profile profile : profiles) {
      if (profile.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }
}
