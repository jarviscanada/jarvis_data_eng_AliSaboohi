package ca.jrvs.apps.grep.practice;

public interface RegexExc {
  public boolean matchJpeg(String filename);
  public boolean matchIp(String ip);
  public boolean isEmptyLine(String line);
}
