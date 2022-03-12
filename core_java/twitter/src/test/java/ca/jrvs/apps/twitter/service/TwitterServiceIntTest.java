package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {

  private TwitterDao dao;
  private TwitterService service;

  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey+"|"+consumerSecret+"|"+accessToken+"|"+tokenSecret);

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    this.dao = new TwitterDao(httpHelper);
    this.service = new TwitterService(dao);
  }

  @Test
  public void postTweet() {
    String text = "Int_test postTweet service";
    Tweet tweet = new Tweet();
    tweet.setText(text);

    Tweet result = service.postTweet(tweet);
    assertTrue(result.getText().equalsIgnoreCase(text));
  }

  @Test
  public void showTweet() {
    String id = "1500900565852823552";
    String value = "hello";
    String createTime = "Mon Mar 07 18:25:54 +0000 2022";
    String[] requiredFields = {"created_at", "id", "id_str", "text"};
    Tweet tweet = service.showTweet(id, requiredFields);

    assertTrue(tweet.getText().equalsIgnoreCase(value));
    assertTrue(tweet.getId_str().equalsIgnoreCase(id));
    assertTrue(tweet.getCreated_at().equalsIgnoreCase(createTime));
  }

  @Test
  public void deleteTweets() {
    String[] ids = {"1500941899556872198", "1500944034562551817", "1500946769458380800"};
    String[] texts = {"hello from ide2", "hello from ide3", "hello from ide4"};

    List<Tweet> deletedTweets = service.deleteTweets(ids);

    assertTrue(deletedTweets.get(0).getText().equalsIgnoreCase(texts[0]));
    assertTrue(deletedTweets.get(1).getText().equalsIgnoreCase(texts[1]));
    assertTrue(deletedTweets.get(2).getText().equalsIgnoreCase(texts[2]));
  }
}