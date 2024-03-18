package fr.mcgcorp.managers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.json.JSONObject;

public class JsonFile {

  public static void main(String[] args) {
    JsonFile file = new JsonFile("path/to/file.json");
    System.out.println(file.getInt("key"));
  }

  private final String path;

  public JsonFile(String path) {
    this.path = path;
    ObjectMapper mapper = new ObjectMapper();

    try {
      Stream<String> lines = Files.lines(
          Paths.get(getResource("path/to/file.json").toURI()));

    }

    JsonNode node = mapper.readTree(SourceData.asString());
  }

  //read the json file and return the value of the key
  public int getInt(String key) {
    JSONNode node = new JSONNode(new JSONObject(new FileReader(path)));
  }

}
