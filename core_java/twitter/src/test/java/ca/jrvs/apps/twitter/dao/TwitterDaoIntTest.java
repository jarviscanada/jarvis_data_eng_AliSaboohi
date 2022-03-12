package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import java.math.BigInteger;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {

  private TwitterDao dao;

  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey+"|"+consumerSecret+"|"+accessToken+"|"+tokenSecret);

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    this.dao = new TwitterDao(httpHelper);
  }

  @Test
  public void create() {
    String text = "sometext1";
    Tweet tweet = new Tweet();
    tweet.setText(text);
    Tweet tweet2 = dao.create(tweet);

    assertTrue(tweet.getText().equalsIgnoreCase(text));
  }

  @Test
  public void findById() {
    String id = "1500900565852823552";
    String value = "hello";
    String tweet = dao.findById(id).getText();

    assertTrue(value.equalsIgnoreCase(tweet));
  }

  @Test
  public void deleteById() {
    String text = "testing delete2";
    Tweet tweet = new Tweet();
    tweet.setText(text);

    Tweet newTweet = dao.create(tweet);
    String id_created = newTweet.getId_str();

    Tweet deletedTweet = dao.deleteById(id_created);
    String id_deleted = deletedTweet.getId_str();

    assertTrue(id_created.equalsIgnoreCase(id_deleted));
  }
}