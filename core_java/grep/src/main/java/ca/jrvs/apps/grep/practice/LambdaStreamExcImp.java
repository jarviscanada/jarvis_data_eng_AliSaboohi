package ca.jrvs.apps.grep.practice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp implements LambdaStreamExc{

  @Override
  public Stream<String> createStrStream(String... strings) {
    String[] input = strings;
    List<String> list = new ArrayList<>();
    for(int i = 0; i < input.length; i++){
      list.add(input[i]);
    }
    Stream<String> stream = list.stream();
    return stream;
  }

  @Override
  public Stream<String> toUpperCase(String... strings) {
    String[] input = strings;
    List<String> list = new ArrayList<>();
    for(int i = 0; i < input.length; i++){
      list.add(input[i]);
    }
    return list.stream().map(e -> e.toUpperCase());
  }

  @Override
  public Stream<String> filter(Stream<String> stringStream, String pattern) {
    return stringStream.filter(e -> Pattern.matches(pattern, e));
  }

  @Override
  public IntStream createIntStream(int[] arr) {
    IntStream stream = Arrays.stream(arr);
    return stream;
  }

  @Override
  public <E> List<E> toList(Stream<E> stream) {
    List<E> result = stream.collect(Collectors.toList());
    return result;
  }

  @Override
  public List<Integer> toList(IntStream intStream) {
    List<Integer> result = intStream.boxed().collect(Collectors.toList());
    return result;
  }

  @Override
  public IntStream createIntStream(int start, int end) {
    IntStream stream = IntStream.range(start, end);
    return stream;
  }

  @Override
  public DoubleStream squareRootIntStream(IntStream intStream) {
    DoubleStream stream = intStream.asDoubleStream().map(e -> Math.sqrt(e));
    return stream;
  }

  @Override
  public IntStream getOdd(IntStream intStream) {
    IntStream stream = intStream.filter(e -> e % 2 != 0);
    return stream;
  }

  @Override
  public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
    Consumer<String> consumer = new Consumer<String>() {
      @Override
      public void accept(String s) {
        System.out.println(prefix + s + suffix);
      }
    };
    return consumer;
  }

  @Override
  public void printMessages(String[] messages, Consumer<String> printer) {
    List<String> list = Arrays.asList(messages);
    list.forEach(m -> printer.accept(m));
  }

  @Override
  public void printOdd(IntStream intStream, Consumer<String> printer) {
    IntStream odd = intStream.filter(n -> n % 2 != 0);
    List<Integer> list = odd.boxed().collect(Collectors.toList());
    List<String> stringList = new ArrayList<>();

    for(Integer num : list){
      stringList.add(String.valueOf(num));
    }

    stringList.forEach(n -> printer.accept(n));

  }

  @Override
  public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
    Stream<Integer> stream = ints.flatMap(list -> list.stream().map(n -> n*n));
    return stream;
  }
}

