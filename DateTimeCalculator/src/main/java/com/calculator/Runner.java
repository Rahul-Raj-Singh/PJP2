package com.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Runner {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		NLP nlp = new NLP();	// To allow Natural Language Phrase support
		
		while(true) {
			
			System.out.println("\nSelect option");
			System.out.println("a. To Enter any Date");
			System.out.println("b. To Natural Language Phrase");
			System.out.println("(or Q to terminate)");
			
			String option = in.readLine().trim();
			
			// To Terminate the app
			if(option.equalsIgnoreCase("Q")) {
				// Write Session info to file
				break;
			}
			
			if(option.equalsIgnoreCase("b")) {
				System.out.println("Enter expressions like:- today, next-week, last-month etc.\n");
				System.out.println(nlp.translate(in.readLine().trim()));
				continue;
			}
			
			System.out.println("Enter any date: (e.g, dd-mm-yyyy)\n");
			String date1 = in.readLine().trim();
			
			System.out.println("\nMenu Options:");
			System.out.println("1. Add Date");
			System.out.println("2. Subtract Date");
			System.out.println("3. Determine Day of Week");
			System.out.println("4. Determine Week Number");
			
			Calculator calculator = new Calculator(date1);
			
			int choice = Integer.parseInt(in.readLine().trim());
			
			switch(choice) {
			
			case 3:
				System.out.println(calculator.getWeekDay());
				break;
				
			case 4:
				System.out.println(calculator.getWeekNumber());
				break;
				
			case 1:
				System.out.println("Enter n days/months/weeks\n");
				System.out.println("\te.g. 3 days, 2 months, 1 week etc\n");
				
				System.out.println(calculator.add(in.readLine().trim()));
				break;
				
			case 2:
				System.out.println("Enter any date or n days/months/weeks\n");
				System.out.println("\te.g. 10-09-2015, 3 days, 2 months, 1 week etc\n");
				
				System.out.println(calculator.subtract(in.readLine().trim()));
				break;
			
			case 5:
				
				break;
			
			default:
				System.out.println("Choose Valid Option");
				
			} // End of switch
			

		} // End of while
		
		System.out.println("Terminated");
		
	}

}
