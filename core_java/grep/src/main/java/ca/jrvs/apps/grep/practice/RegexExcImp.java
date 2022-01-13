package ca.jrvs.apps.grep.practice;
import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc {

  public RegexExcImp() {
  }

  public boolean matchJpeg(String filename){

    return Pattern.matches("[a-zA-Z]+[-_]?[a-zA-Z]+.jpe?g$", filename);
  }
  public boolean matchIp(String ip){

    return Pattern.matches("^\\d{1,4}\\.\\d{1,4}\\.\\d{1,4}\\.\\d{1,4}$", ip);
  }
  public boolean isEmptyLine(String line){

    return Pattern.matches("^\\s*$", line);
  }
}

