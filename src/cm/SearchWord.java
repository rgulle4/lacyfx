package cm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SearchWord {
	
	public static Object[] searchword(File file){
    	try {

			// Keep track of the line we are on and what the line is.
			String line = "";


			// Create a reader which reads our file. In this example searchfile.txt is the file we are searching.
//			BufferedReader bReader = new BufferedReader(new FileReader(file));
			Scanner inFile1 = new Scanner(file);
		    inFile1.useDelimiter(" \\s*");
		    

			// While we loop through the file, read each line until there is nothing left to read.
			// This assumes we have carriage returns ending each text line.
	        
	        List<String> DT_result = new ArrayList<String>();
	        List<String> Pass_result = new ArrayList<String>();
	        List<String> CS_result = new ArrayList<String>();
			int ct_pass = 0;
			int ct_fail = 0;
			int countline =0;
	        
//			while ((line = bReader.readLine()) != null) {
			while (inFile1.hasNext()) {

				countline ++;
				String Design_type = "Design Type:";
				String Pass_status = "Pass";
				String Fail_status = "Fail";
				String Compressive_Strength = "Modulus";
				//String Delimeter_sign = "\u2640";		//female sign in the text
				String Layer_information = "Layer Information";
				
				line = inFile1.nextLine();
				
				// See if the Design_type is in this line, if it is, it will return a position.
				int DT_Found = line.indexOf(Design_type);
				int Pass_Found = line.indexOf(Pass_status);
				int Fail_Found = line.indexOf(Fail_status);
				int CS_Found = line.indexOf(Compressive_Strength);
				int LayerInfo_Found = line.indexOf(Layer_information);		

				

				// If we found the word, print the content of that line.
				
//				
				if (DT_Found > - 1) {
					System.out.println("Design type is found at line "+ countline + " and position " + DT_Found);
					DT_result.add(line);
				}

				if (CS_Found > - 1) {
					System.out.println("Modulus is found at line "+ countline + " and position " + CS_Found);
					CS_result.add(line);
				}
				
				if (Pass_Found > - 1) {
					System.out.println("A pass is found at line "+ countline + " and position " + Pass_Found);
					Pass_result.add(line);
					ct_pass++;
				}
				
				if (Fail_Found > - 1) {
					System.out.println("A Fail is found at line "+ countline + " and position " + Fail_Found);
					Pass_result.add(line);
					ct_fail++;
				}
				
				if (LayerInfo_Found > - 1) {
				System.out.println("Layer Information is found at line "+ countline + " and position " + LayerInfo_Found);
				}

				
				
				

			}
			return new Object[]{DT_result,Pass_result,CS_result,ct_pass,ct_fail};
    	}
    	
    	catch (IOException e) {
			// We encountered an error with the file, print it to the user.
			System.out.println("Error: " + e.toString());
			
		}
		return null;
	}
    	
	}
