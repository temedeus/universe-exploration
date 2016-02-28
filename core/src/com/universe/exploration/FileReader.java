/**
 * 
 */
package com.universe.exploration;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 28.2.2016 Teemu Puurunen
 *
 */
public class FileReader {
    /**
     * Reads a text file and returns its rows as a list of strings.
     * @param path
     * @return List<String>
     * 			Rows read from the given text file.
     * @throws IOException
     */
    public List<String> readTextFile(String path) throws IOException {
	BufferedReader bufferedReader = GdxHelper.provideAssetReader(path);
	List<String> lines = new ArrayList<String>();
	String line = bufferedReader.readLine();
	while (line != null) {
	    lines.add(line);
	    line = bufferedReader.readLine();
	}
	
	return lines;
    }
    
    public boolean fileExist(String path) {
	return GdxHelper.provideFileHandle(path).exists();
    }
    
    public boolean isDirectory(String path) {
	return GdxHelper.provideFileHandle(path).isDirectory();
    }
}
