package fr.mcgcorp.save.profil;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Profil {

    private UUID profilId;
    private String name;
    private ArrayList<UUID> ids;

    public Profil(String name) {
        this.name = name;
        this.ids = new ArrayList<UUID>();
        do{
            this.profilId = UUID.randomUUID();
        }while(ids.contains(profilId));
    }

    public void saveProfil() {
        // Create a JSONObject
        JSONObject profilObject = new JSONObject();

        // Add profil information to the JSONObject
        profilObject.put("name", this.name);
        profilObject.put("profilId", this.profilId.toString());

        // Write the JSONObject to the profils.json file
        try (FileWriter file = new FileWriter("profils.json")) {
            file.write(profilObject.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + profilObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject loadProfils() {
        JSONObject profilObject = null;
        try (FileReader reader = new FileReader("profils.json")) {
            JSONTokener tokener = new JSONTokener(reader);
            profilObject = new JSONObject(tokener);
            System.out.println("Loaded JSON Object: " + profilObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profilObject;
    }

    public static void main(String[] args) {

        Profil p = new Profil("Hôa");

        System.out.println("Profil:\n\tNom: " + p.name + "\n\tId: " + p.profilId);
        JSONObject profilObject = new JSONObject();
        profilObject.getJSONObject();
        p.saveProfil();

    }
}
