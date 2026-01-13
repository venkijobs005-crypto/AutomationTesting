package testNGListeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyiAnnotationTransform implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testCounstructor, Method testMethod) {
        //Retry Failed Testcases One of the wat to rerun the failed TCs
        annotation.setRetryAnalyzer(MyiRetryAnalyzer.class);

        //Set the priority of the tests dynamically
        if (testMethod.getName().equals("retryAnalyzerTest_2"))
            annotation.setPriority(1);
        else if (testMethod.getName().equals("retryAnalyzerTest_0"))
            annotation.setPriority(2);

        // Skip test conditionally based on method name or other logic
        if (testMethod.getName().equals("retryAnalyzerTest_1"))
            annotation.setEnabled(false); // Disable this test
    }

}
