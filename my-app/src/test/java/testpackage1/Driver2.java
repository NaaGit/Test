package testpackage1;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class Driver2 {
	
    public static void main(String[] args)
    {
    List<String> file = new ArrayList<String>();
    file.add("C:\\Bhargavi\\maven\\my-app\\testng.xml");
    TestNG testNG = new TestNG();
    testNG.setTestSuites(file);
    testNG.run();
}

}
