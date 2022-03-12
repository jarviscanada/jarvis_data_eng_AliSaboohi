package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterService implements Service {

  private CrdDao dao;

  @Autowired
  public TwitterService(CrdDao dao) { this.dao = dao; }

  @Override
  public Tweet postTweet(Tweet tweet) {

    validatePostTweet(tweet);
    return (Tweet) dao.create(tweet);
  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    if (!id.matches("^[0-9]+$")){
      throw new IllegalArgumentException("ID Format Is Wrong!!");
    }
    if (fields.length == 0) {
      throw new IllegalArgumentException("No Fields Entered");
    }
    String[] requiredFields = {"created_at", "id", "id_str", "text", "entities", "coordinates", "retweet_count", "favorite_count", "favorited", "retweeted"};

    for(String f : fields){
      if(!Arrays.asList(requiredFields).contains(f)){
        throw new IllegalArgumentException("Fields Entered Are Unknown!!");
      }
    }
    Tweet tweet = (Tweet) dao.findById(id);
    if (!Arrays.asList(fields).contains("created_at")){ tweet.setCreated_at(null); }
    if (!Arrays.asList(fields).contains("text")){ tweet.setText(null); }
    if (!Arrays.asList(fields).contains("entities")){ tweet.setEntities(null); }
    if (!Arrays.asList(fields).contains("coordinates")){ tweet.setCoordinates(null); }
    if (!Arrays.asList(fields).contains("retweet_count")){ tweet.setRetweet_count(0); }
    if (!Arrays.asList(fields).contains("favorite_count")){ tweet.setFavorite_count(0); }
    if (!Arrays.asList(fields).contains("favorited")){ tweet.setFavorited(false); }
    if (!Arrays.asList(fields).contains("retweeted")){ tweet.setRetweeted(false); }

    return tweet;
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    for(String id : ids){
      if (!id.matches("^[0-9]+$")){
      throw new IllegalArgumentException("ID Format Is Wrong!!");
      }
    }

    List<Tweet> deletedTweets = new ArrayList<>();
    for (String id : ids){
      deletedTweets.add((Tweet) dao.deleteById(id));
    }
    return deletedTweets;
  }

  private void validatePostTweet(Tweet tweet) {
    if(tweet.getText().length() > 140) {
      throw new IllegalArgumentException("Tweet Length Cannot Be More Than 140 Characters!!");
    }
    if (tweet.getCoordinates() != null) {
      float lat = tweet.getCoordinates().getCoordinates().get(0);
      float lon = tweet.getCoordinates().getCoordinates().get(1);
      if (lon > 180 || lon < -180 || lat > 90 || lat < -90){
        throw new IllegalArgumentException("Latitude And Longitude Out Of Range!!");
      }
    }
  }
}
