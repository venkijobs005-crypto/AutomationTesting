package javaPrograms;

//import org.testng.annotations.Test;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Map.Entry;

public class JavaPractice {

    public static void main(String [] args){
        String str = "Geeks om geeks";
        int cnt = StringUtils.countMatches(str, "is");
        Set<Entry<Character,Long>> result = str.toLowerCase().chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(c->c,LinkedHashMap::new,Collectors.counting())).entrySet();
    }

}
