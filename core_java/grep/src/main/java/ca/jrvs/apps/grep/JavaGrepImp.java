package ca.jrvs.apps.grep;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);
  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
        if(args.length != 3){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }
      BasicConfigurator.configure();

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try{
            javaGrepImp.process();
        } catch (Exception ex){
            javaGrepImp.logger.error("Error: Unable to process", ex);
        }

  }

  @Override
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<>();
    for(File file : listFiles(this.rootPath)) {
      for(String line : readLines(file)) {
        if(containsPattern(line)) {
          matchedLines.add(line);
        }
      }
    }
    writeToFile(matchedLines);
  }

  @Override
  public List<File> listFiles(String rootDir) {
    List<File> list = new ArrayList<>();
    File dir = new File(rootDir);
    for(File file : dir.listFiles()){
      if(file.isDirectory()) {
        list = listFiles(file.getAbsolutePath());
      }else {
        list.add(file);
      }
    }
    return list;
  }

  @Override
  public List<String> readLines(File inputFile) {
    /**
        The Java FileReader class, java. io. FileReader makes it possible to read the contents of a file
        as a stream of characters. It works much like the FileInputStream except the FileInputStream reads bytes,
        whereas the FileReader reads characters. The FileReader is intended to read text, in other words.

        Bufferedreader lets us to invoke extra methods such as readLine()
     */
    List<String> list = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
      String line;
      while(br.ready()){
        line = br.readLine();
        list.add(line);
      }
    } catch(IOException e) {
      logger.error("Error: Unable to read lines" ,e);
    }
    return list;
  }

  @Override
  public boolean containsPattern(String line) {
    return Pattern.matches(this.regex, line);
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter(this.outFile));
    for(String line : lines) {
      bw.write(line + "\n");
    }
    bw.close();
  }

  @Override
  public String getRootPath() {
    return this.rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return this.regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return this.outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
