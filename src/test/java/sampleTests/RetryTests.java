package sampleTests;

import org.junit.Assert;
import org.testng.annotations.Test;
import testNGListeners.MyiRetryAnalyzer;

public class RetryTests {
    int exeCnt = 0;
    int exeCnt1 = 0;
    int exeCnt2 = 0;

//    @Test(retryAnalyzer = MyiRetryAnalyzer.class)
//    @Test
    public void retryAnalyzerTest_0(){
        System.out.println("Test");
        exeCnt++;
        if(exeCnt<=2){
            Assert.assertTrue(false);
        } else {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void retryAnalyzerTest_1(){
        System.out.println("Test1");
        exeCnt1++;
        if(exeCnt1<=2){
            Assert.assertTrue(false);
        } else {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void retryAnalyzerTest_2(){
        System.out.println("Test2");
        exeCnt2++;
        if(exeCnt2<=2){
            Assert.assertTrue(false);
        } else {
            Assert.assertTrue(true);
        }
    }


}
