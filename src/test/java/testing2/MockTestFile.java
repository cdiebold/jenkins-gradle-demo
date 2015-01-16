package testing2;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.mockito.Mockito;
import org.mockito.BDDMockito;

public class MockTestFile {
	File fileToParse;
	//URL mockUrl;
	URL mockURL;
	final String fileName = "src/test/resources/input.txt";
    String urlString = "http://andrew.cs.fit.edu/~kgallagher/sampleprogs/onetoone.cgi?p1=251";
	public MockTestFile() {
		mockURL = MockTestFile.getMockURL(fileName);
		fileToParse = new File(fileName);
	}

	public static Cdiebold2012 parseInputFileIntoObject(String fileString)
	{
		Cdiebold2012 obj = null; 
	
		URL oracle = MockTestFile.getMockURL(fileString);
		
		File inputFile = new File(oracle.getFile());
		
		try {
			obj = MockTestFile.parse(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot FInd File to Parse");
			e.printStackTrace();
		}
		
		
		return obj;
	}
	public static URL getMockURL(String fileName)
	{
		File fileToParse = new File(fileName);
		InputStream stream;
		String protocol = "http";
		final int port = 80;
		String host = "andrew.cs.fit.edu/~kgallagher/sampleprogs/onetoone.cgi?p1=251";
		try {
			final URLConnection mockConnection = Mockito.mock(URLConnection.class);
			stream = new FileInputStream(fileToParse);
			BDDMockito.given(mockConnection.getInputStream()).willReturn(stream);
			
			final URLStreamHandler handler = new URLStreamHandler() {
				
				@Override
				protected URLConnection openConnection(URL u) throws IOException {
					return mockConnection;
				}
			};
			final URL url = new URL(protocol, host, port, fileName, handler);
			return url;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	private static Cdiebold2012 parse(File fileToParse) throws FileNotFoundException
	{
		Cdiebold2012 obj = null; 
		HashMap<Integer, Set<Integer>> pairMap = new HashMap<Integer, Set<Integer>>();
		ArrayList<Pair> pList = new ArrayList<Pair>();
		
		Scanner in = new Scanner(fileToParse);
		
		//parse file into a List of Pairs
		int uni = in.nextInt();
		while(in.hasNextInt())
		{
			int x = in.nextInt();
			int y = in.nextInt();
			
			Pair p = new Pair(x,y);
			pList.add(p);
		}
		in.close();
		
		//I now have an ArrayList of Points. Need to put the points into my Map.
		for (int i = 0; i < pList.size(); i++) {
			Set<Integer> x = new TreeSet<Integer>();// A TreeSet is sorted.
			int key = pList.get(i).getX(); // the key to the hashmap.
			for (int j = 0; j < pList.size(); j++) {
				if (pList.get(j).getX() == key)
					x.add(pList.get(j).getY());
			}
			pairMap.put(key, x);

			obj = new Cdiebold2012(uni, pairMap);
		}
		
		return obj;
	}
	
}
