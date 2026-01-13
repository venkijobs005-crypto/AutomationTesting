package javaPrograms;

import org.testng.annotations.Test;

import java.util.*;

public class JavaArrayFunctions {

    //1. Find minimum and maximum values from an array
    @Test
    public void minNMaxInAnIntArray(){
        int min, max;
        int [] arr = {10, 87, 32,  45, 67, 90, 76, 35};
        min = arr[0];
        max = arr[0];
        for(int num : arr){
            if(num>max)
                max=num;
            else if(num<min)
                min=num;
        }
        System.out.println("Min number in an array is : " + min);
        System.out.println("Max number in an array is : " + max);
    }

    //2. Second Highest number in an array
    @Test
    public void secondMaxInAnIntArray() {
        int max, secondMax;
        int[] arr = {10, 87, 32, 45, 67, 90, 76, 35};
        max = arr[0];
        secondMax = arr[0];
        for(int num : arr){
            if(num>max){
                secondMax=max;
                max=num;
            } else if(num>secondMax && num!=max)
                secondMax=num;
        }
        System.out.println("Max number in an array is : " + max);
        System.out.println("Second Max number in an array is : " + secondMax);

    }

    //3. nth Highest number in an array
    @Test
    public void nthMaxInAnIntArray() {
        int n=0;
        int[] arr = {10, 87, 32, 45, 67, 90, 76, 35};
        Arrays.sort(arr);
        System.out.println(arr[n]);
    }

    @Test
    //4. Find the duplicate of integer array using hashmap
    public void findDuplicateInAnIntArray() {
        int [] intArr = {2, 4, 6, 4, 65, 876, 54, 98, 23, 65, 876};
        Map<Integer,Integer> map = new LinkedHashMap<>();
        //To remove duplicates
        Set<Integer> intSet = new LinkedHashSet<>();

        for(int num : intArr){
            intSet.add(num);
            map.merge(num,1,Integer::sum);
        }
        for(Map.Entry<Integer,Integer> mapVal : map.entrySet())
            if(mapVal.getValue()>1)
                System.out.println(mapVal.getKey() + " : " + mapVal.getValue());

        System.out.println(intSet);
        int [] intArr1 = {5, 2, 90, 34, 67, 90, 34, 2, 78, 56, 38};
        Set<Integer> intSet1 = new HashSet<>();
        for(int num : intArr1)
            if(!intSet1.add(num))
                System.out.println(num + " is a duplicate in an array");
    }

    //6. Sort the names in an array
    @Test
    public void sortArray(){
        String [] namesArr = {"Banana", "Orange", "Apple", "Avacoda", "plums"};
        Set<String> sortArr = new TreeSet<>();
        for(String names : namesArr)
            sortArr.add(names);
        System.out.println(sortArr);
    }

    //7. common elements in 2 arrays
    @Test
    public void commonEleInArrays() {
        String [] arr1 = {"Sachin", "Dhoni", "Yuvi", "Virat", "Kapil", "Shewag"};
        String [] arr2 = {"Bat", "Kapil", "Sachin", "Glove","Ball"};
        for(String name1 : arr1)
            for(String name2 : arr2)
                if(name1.equalsIgnoreCase(name2))
                    System.out.println("Common elements in both array is : " + name1);
        //Another way
        Map<String,Integer> map= new LinkedHashMap<>();
        for(String name1 : arr1)
            map.merge(name1,1,Integer::sum);
        for(String name2 : arr2)
            map.merge(name2,1,Integer::sum);

        for(Map.Entry<String,Integer> entry : map.entrySet())
            if(entry.getValue()>1)
                System.out.println("Common elements in both array is : " + entry.getKey());
    }

    //8. implement bubble sort
    public void bubbleSortArray(){
        int [] numArr = { 4, 7, 3, 2, 6, 1, 5};
        for(int i =0; i<=numArr.length-1;i++){
            for(int j = 0; j<=numArr.length-1;j++){
                if(numArr[j]>numArr[i]){
                    int temp = numArr[i];
                    numArr[i] = numArr[j];
                    numArr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(numArr));
    }

    //9. Sort the word by length from a string
    public void sortByWordLength(){
        String str = "This is new sring of the program";
        String [] arr = str.split(" ");
        for(int i=0;i<=arr.length-1;i++)
            for(int j = i+1;j<=arr.length-1;j++)
                if(arr[j].length()<arr[i].length()){
                    String temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
        System.out.println(Arrays.toString(arr));
    }

    //11. Move all 0s at the end from an array
    @Test
    public void move0sToEnd(){
        int[] arr = { 0, 5, 6, 0, 3, 0, 8, 0 };
        int left = 0;
        int right = arr.length-1;
        while(left<=right){
            if(arr[left]==0 && arr[right]!=0){
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            } else if(arr[right]==0)
                right--;
            else if(arr[left]!=0)
                left++;
        }
        System.out.println(Arrays.toString(arr));
    }

    //11. Move all -ve numbers at the end from an array
    @Test(invocationCount = 3)
    public void moveNegativesToEnd(){
        int[] arr = { -1, 5, -7, -4, 3, -9, 8, 0 };
        int left = 0;
        int right = arr.length-1;
        while(left<=right){
            if(arr[left]<0 && arr[right]>=0){
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            } else if(arr[right]<0)
                right--;
            else if(arr[left]>=0)
                left++;
        }
        System.out.println(Arrays.toString(arr));
    }
}



