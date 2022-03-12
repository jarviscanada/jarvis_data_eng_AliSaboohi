package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Coordinates {

  private List<Float> coordinates;
  private String type;

  public List<Float> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(List<Float> coordinates) {
    this.coordinates = coordinates;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
