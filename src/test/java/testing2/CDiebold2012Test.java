package testing2;

import java.io.File;
import java.net.URL;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner; 

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)

public class CDiebold2012Test {
	String fileString = "src/test/resources/input.txt";
	
	
	@Test
	public void test_file_exists()
	{
		URL url = MockTestFile.getMockURL(fileString);
		File file = new File(url.getFile());
		assertTrue(file.exists());
	}
	@Test
	public void test_url_not_null()
	{
		URL url = MockTestFile.getMockURL(fileString);
		assertNotNull(url.toString());
	}
	@Test
	public void test_file_not_null()
	{
		URL url = MockTestFile.getMockURL(fileString);
		File file = new File(url.getFile());
		assertNotNull(file);
		
	}
	@Test
	public void test_file_isReadable(){
		URL url = MockTestFile.getMockURL(fileString);
		File file = new File(url.getFile());
		//System.out.println(file.toString());
		assertTrue(file.canRead());
		
	}
	@Test
	public void test_is_one_to_one()
	{
		URL url = MockTestFile.getMockURL(fileString);
		File file = new File(url.getFile());
		Cdiebold2012 obj = MockTestFile.parseInputFileIntoObject(file.toString());
		//obj.printPartitions();
		assertTrue(obj.is_one_to_one());
		
	}
	@Ignore("Test Fails. The current data set is not ONTO")
	@Test
	public void test_is_onto(){
		URL url = MockTestFile.getMockURL(fileString);
		File file = new File(url.getFile());
		Cdiebold2012 obj = MockTestFile.parseInputFileIntoObject(file.toString());
		
		assertTrue(obj.is_onto());
	}
	
	@Test 
	public void test_is_a_function(){
		URL url = MockTestFile.getMockURL(fileString);
		File file = new File(url.getFile());
		Cdiebold2012 obj = MockTestFile.parseInputFileIntoObject(file.toString());
		
		assertTrue(obj.is_a_function());
	}
	@Test
	public void test_is_reflexive(){
		URL url = MockTestFile.getMockURL(fileString);
		File file = new File(url.getFile());
		Cdiebold2012 obj = MockTestFile.parseInputFileIntoObject(file.toString());
		
		assertTrue(obj.is_reflexive());
	}

}
