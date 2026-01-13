package javaPrograms;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaPracticeStream {
    public void reverseAString() throws Exception{
        String str = "Testing";
        String revStr = IntStream.range(0,str.length())
                .mapToObj(i->str.charAt(str.length()-1-i))
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println(revStr);
    }

    public void removeDupFromString() throws Exception{
        String str = "xxyyzxyzxyzxxyyzzxxxyzxx";
        Set<Character> charSet = new HashSet<>();
        String rmvDup = str.chars()
                .mapToObj(c->String.valueOf((char)c))
                .distinct()
                .collect(Collectors.joining());
        System.out.println(rmvDup);
    }

    public void findEachOccur() throws Exception{
        String str = "the occurrences of each characters from a string";
        Map<Character, Long> mapSet = new LinkedHashMap<>();
        mapSet = str.chars()
                .mapToObj(c->(char)c)
                .filter(c->!Character.isWhitespace(c))
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(mapSet);
        for(Map.Entry<Character, Long> mapTest : mapSet.entrySet()){
            if(mapTest.getValue()>1)
                System.out.println(mapTest.getKey() + " : " + mapTest.getValue());
        }
    }
}
