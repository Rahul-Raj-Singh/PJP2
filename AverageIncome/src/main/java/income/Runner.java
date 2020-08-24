package income;

import java.io.IOException;

public class Runner {

	public static void main(String[] args) {
		
		String inputFilelocation = "src/main/resources/income.csv";
		String outputFileLocation = "src/main/resources/output.csv";
		
		IncomeCalcuate calcuate = new IncomeCalcuate();
		
		try {
			
			calcuate.readAndParse(inputFilelocation);
			calcuate.writeToCSV(calcuate.calculateAvg(), outputFileLocation);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
