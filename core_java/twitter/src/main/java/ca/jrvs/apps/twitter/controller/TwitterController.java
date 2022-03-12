package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller{

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private Service service;

  @Autowired
  public TwitterController(Service service) { this.service = service; }


  @Override
  public Tweet postTweet(String[] args) {
    if(args.length != 3) {
      throw new IllegalArgumentException("USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude");
    }

    String tweet_txt = args[1];
    String coord = args[2];
    String[] coordArray = coord.split(COORD_SEP);
    if (coordArray.length != 2 || tweet_txt.isEmpty()) {
      throw new IllegalArgumentException("Invalid Format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude");
    }
    Float lat = null;
    Float lon = null;
    try {
      lat = Float.parseFloat(coordArray[0]);
      lon = Float.parseFloat(coordArray[1]);
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid Location Format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude", e);
    }
    Tweet toPost = new Tweet();
    List<Float> coordFloat = new ArrayList<>();
    coordFloat.add(lat);
    coordFloat.add(lon);
    Coordinates finalCoord = new Coordinates();
    finalCoord.setCoordinates(coordFloat);

    toPost.setText(tweet_txt);
    toPost.setCoordinates(finalCoord);

    return service.postTweet(toPost);
  }

  @Override
  public Tweet showTweet(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: TwitterCLIApp show \"tweet_id\" \"field,field,...");
    }
    String id = args[1];
    String fields = args[2];
    String[] fieldsArray = fields.split(COMMA);

    Tweet toShow = service.showTweet(id, fieldsArray);
    return toShow;
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length != 2){
      throw new IllegalArgumentException("USAGE: TwitterCLIApp delete \"id,id,id,...");
    }
    String[] ids = args[1].split(COMMA);

    return service.deleteTweets(ids);
  }
}
