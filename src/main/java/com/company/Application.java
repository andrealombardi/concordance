package com.company;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Application {

    private static final Pattern SPLIT_WORD = Pattern.compile("\\W+");
    private static final Comparator<Map.Entry<String,Long>> byValueDescending =
            Map.Entry.<String, Long>comparingByValue().reversed();

    public static void main(String[] args) throws Exception{

        Map<String, Long> wordCount  = Files.lines(Paths.get("src/main/resources/1342-0.txt"))
                .flatMap(SPLIT_WORD::splitAsStream)
                .filter(s -> !s.isEmpty())
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        wordCount.entrySet().stream()
                .sorted(byValueDescending)
                .limit(100)
                .forEach(e -> System.out.printf("%20s : %5d\n", e.getKey(), e.getValue()));

    }
}
