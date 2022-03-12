package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

  @Mock
  Service service;

  @InjectMocks
  TwitterController controller;

  private static Tweet tweet = new Tweet();
  private static List<Tweet> tweets = new ArrayList<>();

  @Test
  public void postTweet() {
    when(service.postTweet(any())).thenReturn(tweet);
    String text = "Controller postTweet Unit test";
    String[] args = {"post", text, "44.4:55.5"};
    tweet.setText(text);

    Tweet result = controller.postTweet(args);

    assertTrue(result.getText().equalsIgnoreCase(text));
  }

  @Test
  public void showTweet() {
    when(service.showTweet(any(), any())).thenReturn(tweet);
    String text = "sometext";
    String created_at = "Mon Mar 07 21:36:12 +0000 2022";
    String id = "1500948454071222273";
    String[] args = {"show", id, "created_at,text"};
    tweet.setText(text);
    tweet.setCreated_at(created_at);

    Tweet result = controller.showTweet(args);

    assertTrue(result.getText().equalsIgnoreCase(text));
    assertTrue(result.getCreated_at().equalsIgnoreCase(created_at));

  }

  @Test
  public void deleteTweet() {
    when(service.deleteTweets(any())).thenReturn(tweets);
    String firstText = "Controller postTweet int test2";
    String firstId = "1501599906368299009";
    String secondText = "sometext";
    String SecondId = "1500948454071222273";
    String[] args = {"delete", "1501599906368299009,1500948454071222273"};

    List<Tweet> result = controller.deleteTweet(args);

    assertNotNull(result);
  }
}