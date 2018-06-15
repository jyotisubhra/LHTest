package com.lh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFile {

	public static void main(String[] args) {
		File file = new File("src/conf/inputParameters.properties");

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			GenerateFile.generatedXML(br);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
