package com.lh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	public static void main(String[] args) {
		File file = new File("src/conf/inputParameters.properties");
		File fileName = new File("src/conf/InputFileName.properties");

		BufferedReader br = null;
		BufferedReader brFile = null;
		String filename = null;
		try {
			br = new BufferedReader(new FileReader(file));
			brFile = new BufferedReader(new FileReader(fileName));
			//Get the filename
			filename = brFile.readLine();
			GenerateFile.generatedXML(br, filename);
			
			
		} catch (Exception e) {
			System.out.println("Error processing records");
			e.printStackTrace();
		} finally {			
			try {
				br.close();
				brFile.close();
			} catch (IOException e) {
				System.out.println("Error closing Buffered Reader");
				e.printStackTrace();
			}
		}

		
	}
}
