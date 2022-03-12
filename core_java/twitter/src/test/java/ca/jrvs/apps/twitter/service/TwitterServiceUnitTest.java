package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService service;

  private static Tweet tweet = new Tweet();

  @Test
  public void postTweet() {
    String text = "Unit_tetsing postTweet";
    tweet.setText(text);
    when(dao.create(any())).thenReturn(tweet);


    Tweet result = service.postTweet(tweet);

    assertTrue(result.getText().equalsIgnoreCase(text));
  }

  @Test
  public void showTweet() {
    when(dao.findById(any())).thenReturn(tweet);
    String id = "1500900565852823552";
    String value = "hello";
    String createTime = "Mon Mar 07 18:25:54 +0000 2022";
    String[] requiredFields = {"created_at", "id_str", "text"};
    tweet.setId_str(id);
    tweet.setText(value);
    tweet.setCreated_at(createTime);
    Tweet result = service.showTweet(id, requiredFields);

    assertTrue(result.getText().equalsIgnoreCase(value));
    assertTrue(result.getId_str().equalsIgnoreCase(id));
    assertTrue(result.getCreated_at().equalsIgnoreCase(createTime));

  }

  @Test
  public void deleteTweets() {
    when(dao.deleteById(any())).thenReturn(tweet);
    String text = "sometext1";
    String[] ids = {"1500950522588803072"};

    tweet.setText(text);
    tweet.setId_str(ids[0]);

    List<Tweet> result = service.deleteTweets(ids);

    assertTrue(result.get(0).getText().equalsIgnoreCase(text));

  }
}