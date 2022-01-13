package ca.jrvs.apps.grep;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepLambdaImp extends JavaGrepImp {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  public static void main(String[] args) {
    if(args.length != 3){
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }
    BasicConfigurator.configure();

    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try{
      javaGrepLambdaImp.process();
    } catch (Exception ex){
      javaGrepLambdaImp.logger.error("Error: Unable to process", ex);
    }

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
}

