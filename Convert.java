// Convert.java
// converts InputStream to String

import java.io.IOException;
import java.nio.charset.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public class Convert{


    public String convert(InputStream inputStream, Charset charset) throws IOException {
 
	    StringBuilder stringBuilder = new StringBuilder();
    	String line = null;
    	
	    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) { 
	        while ((line = bufferedReader.readLine()) != null) {
	            stringBuilder.append(line);
	        }
	    }
	 
	    return stringBuilder.toString();
	}

}