package ca.jrvs.apps.grep;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepLambdaImp implements JavaGrepLambda {

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

  }

  @Override
  public List<File> listFiles(String rootDir) {
    List<File> list = new ArrayList<>();
    File dir = new File(rootDir);

    list.addAll(Arrays.stream(dir.listFiles())
        .filter(file -> !file.isDirectory())
        .collect(Collectors.toList()));
    Arrays.stream(dir.listFiles())
        .filter(file -> file.isDirectory())
        .forEach(directory -> list.addAll(listFiles(directory.getAbsolutePath())));
    return list;
  }

  @Override
  public List<String> readLines(File inputFile) {
    List<String> list = new ArrayList<>();
    try (Stream<String> stream = Files.lines(inputFile.toPath())) {
      list = stream.collect(Collectors.toList());

    } catch (IOException e) {
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

    lines.stream().forEach(line -> {
      try {
        bw.write(line + "\n");
      } catch (IOException e) {
        logger.error("Error: Unable to write" ,e);
      }
    });


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

