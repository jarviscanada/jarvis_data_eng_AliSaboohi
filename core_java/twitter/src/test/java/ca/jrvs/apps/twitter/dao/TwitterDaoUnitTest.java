package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDao dao;

  @Test
  public void postTweet() throws Exception{
    String text = "Mock test post";
    Tweet tweet = new Tweet();
    tweet.setText(text);

    try {
      dao.create(tweet);
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    String tweetJsonStr = "{\n"
        + "    \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "    \"id\":1097607853932564480,\n"
        + "    \"id_str\":\"1097607853932564480\",\n"
        + "    \"text\":\"test with loc223\",\n"
        + "    \"entities\":{\n"
        + "       \"hashtags\":[],"
        + "       \"user_mentions\":[]"
        + "    },\n"
        + "    \"coordinates\":null,"
        + "    \"retweet_count\":0,\n"
        + "    \"favorite_count\":0,\n"
        + "    \"favorited\":false,\n"
        + "    \"retweeted\":false\n"
        + "}";

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());

    Tweet spyTweet = spyDao.create(tweet);
    assertNotNull(spyTweet);
    assertNotNull(spyTweet.getText());

  }
  @Test
  public void showTweet() throws Exception{
    String text = "sometext1";
    String id = "1500950522588803072";
    Tweet tweet = new Tweet();
    tweet.setText(text);

    try {
      dao.findById(id);
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    String tweetJsonStr = "{\n"
        + "    \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "    \"id\":1097607853932564480,\n"
        + "    \"id_str\":\"1097607853932564480\",\n"
        + "    \"text\":\"test with loc223\",\n"
        + "    \"entities\":{\n"
        + "       \"hashtags\":[],"
        + "       \"user_mentions\":[]"
        + "    },\n"
        + "    \"coordinates\":null,"
        + "    \"retweet_count\":0,\n"
        + "    \"favorite_count\":0,\n"
        + "    \"favorited\":false,\n"
        + "    \"retweeted\":false\n"
        + "}";

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());

    Tweet spyTweet = spyDao.findById(id);
    assertNotNull(spyTweet);
    assertNotNull(spyTweet.getText());

  }
  @Test
  public void deleteTweet() throws Exception{
    String text = "sometext1";
    String id = "1500950522588803072";

    try {
      dao.deleteById(id);
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    String tweetJsonStr = "{\n"
        + "    \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "    \"id\":1097607853932564480,\n"
        + "    \"id_str\":\"1097607853932564480\",\n"
        + "    \"text\":\"test with loc223\",\n"
        + "    \"entities\":{\n"
        + "       \"hashtags\":[],"
        + "       \"user_mentions\":[]"
        + "    },\n"
        + "    \"coordinates\":null,"
        + "    \"retweet_count\":0,\n"
        + "    \"favorite_count\":0,\n"
        + "    \"favorited\":false,\n"
        + "    \"retweeted\":false\n"
        + "}";

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());

    Tweet spyTweet = spyDao.deleteById(id);
    assertNotNull(spyTweet);
    assertNotNull(spyTweet.getText());

  }

}
