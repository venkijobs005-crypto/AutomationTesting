package testNGListeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyiRetryAnalyzer implements IRetryAnalyzer{
    int maxTry=3;
    int count=0;
    @Override
    public boolean retry(ITestResult result) {
        if(!result.isSuccess()) {
            if(count<maxTry) {
                count++;
                return true;
            }
        }
        return false;
    }
}
