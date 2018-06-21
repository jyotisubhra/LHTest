package com.lh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class GenerateFile {

	public static void generatedXML(BufferedReader br, String filename) throws IOException {
		String st;
		// root elements
		Document doc = null;
		Element rootElement = null;
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
			rootElement = doc.createElement("target");
			
			// set attribute name to staff element
			Attr name = doc.createAttribute("name");
			name.setValue("soapui");
			rootElement.setAttributeNode(name);
						
			doc.appendChild(rootElement);

		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("File Name " + filename);
		while ((st = br.readLine()) != null) {
			System.out.println(st);

			// exec elements
			Element exec = doc.createElement("exec");
			rootElement.appendChild(exec);

			// set attribute dir to staff element
			Attr dir = doc.createAttribute("dir");
			dir.setValue(".");
			exec.setAttributeNode(dir);

			// set attribute dir to staff element
			Attr executable = doc.createAttribute("executable");
			executable.setValue("${soap.executable}");
			exec.setAttributeNode(executable);
			
			// arg elements
			Element arg = doc.createElement("arg");
			exec.appendChild(arg);

			// set attribute line to arg element
			Attr line = doc.createAttribute("line");
			line.setValue("-r -j -f '${testsuite.dir}' -s" + st + " '${xml.dir}/" + filename + "'");
			arg.setAttributeNode(line);


		}
		
		try {
			
			System.out.println(doc.getNodeName());
			// write the content into txt file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			String path = GenerateFile.class.getProtectionDomain().getCodeSource().getLocation().getFile();
			//path= path.replaceAll("build/jar/GenerateBuild.jar/", "input/");
			System.out.println(path);
			StreamResult result = new StreamResult(new File(path + "file.txt"));

			transformer.transform(source, result);
			System.out.println("File saved!");
			converttoTXT(path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void converttoTXT(String txtPath) throws IOException {
		
		File path = new File(txtPath + "file.txt");
		Scanner scanner = new Scanner(path);
		ArrayList<String> coll = new ArrayList<String>();
		scanner.nextLine();
		while (scanner.hasNextLine()) {
		    String line = scanner.nextLine();
		    coll.add(line);
		}

		scanner.close();

		FileWriter writer = new FileWriter(txtPath + "output.txt");
		for (String line : coll) {
		    writer.write(line);
		}

		writer.close();
		
	}
		
	
}

