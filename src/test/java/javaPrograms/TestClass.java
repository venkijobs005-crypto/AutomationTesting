package javaPrograms;

public class TestClass {
    String brand;
    static int price;
    public TestClass(){

    }
    public TestClass(int price){
        this.price=price;
        System.out.println(price);
    }

    public static void main(String [] args){
        TestClass testClass = new TestClass(200);
    }

    public void setRate(int rate){
        this.price=price;
    }

}
