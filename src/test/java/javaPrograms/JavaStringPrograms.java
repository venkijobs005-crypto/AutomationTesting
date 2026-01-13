package javaPrograms;

import org.testng.annotations.Test;

import java.util.*;

public class JavaStringPrograms {

    public void panagramTest(){
        String str = "The quick brown fox jumps over the lazy dog";
        Set<Character> set = new HashSet<>();
        for(char ch : str.toLowerCase().toCharArray()){
            if(ch==' ')
                continue;
                if(ch>='a' && ch<='z')
                    set.add(ch);
        }
        if(set.size()==26)
            System.out.println("Given string is panagram");
        else
            System.out.println("Given string is NOT a panagram");
        System.out.println(set);
    }

//    @Test
    public void testing(){
        int [] intArr = {2, 4, 6, 4, 65, 876, 54, 98, 23, 65, 876};
        Map<Integer,Integer> map = new LinkedHashMap<>();
        for(int num : intArr)
            map.merge(num,1,Integer::sum);
        map.forEach(
                (k,v) ->{
                    if(v>2){
                        System.out.println(k + " has more occurrence as : " + v);
                    }
                }
            );
        map.forEach((i,j) -> System.out.println(i + "" + j));
    }

    public static void removeDuplicateFromMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "test1");
        map.put(2, "test2");
        map.put(3, "test3");
        map.put(4, "test1");
        map.put(5, "test2");

        Set<String> set = new HashSet<>();
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            String value = entry.getValue();
            if (!set.add(value)) {
                iterator.remove();
            }
        }
        System.out.println(map);
    }

    @Test
    public static void test123() {
		List<Integer> tempTest = Arrays.asList(2,5,1,4,8,2,10,65,35);
//        List<Integer> tempTest = Arrays.asList(4,4,9,2,7);
        List<Integer> addList = new LinkedList<>();
        int val;
        for(int i=0;i<=tempTest.size()-1;i++) {
            val= tempTest.get(i);
            if (i!=0) {
                if(val>=tempTest.get(i-1)) {
                    int greatVal = val-tempTest.get(i-1);
                    addList.add(greatVal);
                } else if(val<tempTest.get(i-1)) {
                    int lessVal = Math.max(val - Math.min(tempTest.get(i-1), tempTest.get(i-2)),0);
                    addList.add(lessVal);
                }
            } else {
                addList.add(tempTest.get(i));
            }
        }
        System.out.println(addList);
        int sum = 0;
        for(int num : addList) {
            sum += num;
        }
        System.out.println(sum);
    }


    public void reverseStringUsingWhile(){
        String str = "reverse the word";
        char [] charArr = str.toCharArray();
        int left = 0;
        int right = charArr.length-1;
        while(left<right){
            char temp = charArr[left];
            charArr[left] = charArr[right];
            charArr[right] = temp;
            left++;
            right--;
        }
        System.out.println(new String(charArr));
    }

//    @Test
    public void replaceCharOccWithNumbers(){
        String str = "retrorespro";
        int occ=0;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<=str.toCharArray().length-1;i++){
            if (str.charAt(i) == 'r') {
                occ++;
                sb.append(occ);
            } else {
                sb.append(str.charAt(i));
            }
        }
        System.out.println(sb.toString());
    }
}
