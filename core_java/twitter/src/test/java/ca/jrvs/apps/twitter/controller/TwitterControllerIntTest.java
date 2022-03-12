package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {

  private TwitterDao dao;
  private TwitterService service;
  private TwitterController controller;

  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    this.dao = new TwitterDao(httpHelper);
    this.service = new TwitterService(dao);
    this.controller = new TwitterController(service);
  }
  @Test
  public void postTweet() {
    String text = "Controller postTweet int test5";
    String[] args = {"post", text, "22.2:33.3"};

    Tweet tweet = controller.postTweet(args);
    assertTrue(tweet.getText().equalsIgnoreCase(text));

  }

  @Test
  public void showTweet() {
    String text = "sometext";
    String created_at = "Mon Mar 07 21:36:12 +0000 2022";
    String id = "1500948454071222273";
    String[] args = {"show", id, "created_at,text"};

    Tweet tweet = controller.showTweet(args);
    assertTrue(tweet.getText().equalsIgnoreCase(text));
    assertTrue(tweet.getCreated_at().equalsIgnoreCase(created_at));
  }

  @Test
  public void deleteTweet() {
    String text5 = "Controller postTweet int test5";
    String id5 = "1501605857078951943";
    String text4 = "Controller postTweet int test4";
    String id4 = "1501601349288529930";
    String text3 = "Controller postTweet int test3";
    String id3 = "1501600524667441157";
    String[] args = {"delete","1501605857078951943,1501601349288529930,1501600524667441157"};

    List<Tweet> tweets = controller.deleteTweet(args);

    assertTrue(tweets.get(0).getText().equalsIgnoreCase(text5));
    assertTrue(tweets.get(1).getText().equalsIgnoreCase(text4));
    assertTrue(tweets.get(2).getText().equalsIgnoreCase(text3));
  }
}