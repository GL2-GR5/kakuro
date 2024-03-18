package fr.mcgcorp.managers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mcgcorp.Main;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JsonFile {

  private final JsonNode root;

  public JsonFile(String pathFile) {
    URL url = Main.class.getResource(pathFile);
    ObjectMapper mapper = new ObjectMapper();

    if (url == null) {
      throw new RuntimeException("File not found");
    }

    String sourceData;

    try {
      sourceData = Files.lines(Paths.get(url.toURI())).collect(Collectors.joining());
    } catch (IOException e) {
      throw new RuntimeException("Error reading file", e);
    } catch (URISyntaxException e) {
      throw new RuntimeException("Invalid URL format", e);
    }

    try {
      this.root = mapper.readTree(sourceData);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private List<String> getPath(String path) {
    if (!path.matches("^[a-zA-Z0-9.]+$")) {
      throw new RuntimeException("Invalid path (" + path + "). Must be [a-zA-Z0-9.]+ (node1.node2.node3...)");
    }
    return Arrays.asList(path.split("\\."));
  }

  private JsonNode getNode(String path) {
    List<String> paths = getPath(path);
    JsonNode node = root;

    for (String p : paths) {
      node = node.get(p);
    }

    return node;
  }

  public int getInt(String path) {
    return getNode(path).asInt();
  }

  public String getString(String path) {
    return getNode(path).asText();
  }
}