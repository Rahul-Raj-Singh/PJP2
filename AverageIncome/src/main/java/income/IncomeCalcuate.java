package income;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utility.CurrencyConverter;
import utility.Pair;

public class IncomeCalcuate {

	private Map<String, Pair> map;

	public IncomeCalcuate() {
		this.map = new HashMap<>();
	}

	public void readAndParse(String inputFileLocation) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader(inputFileLocation));

		String line, city, country, gender, currencyType, key;
		Double amount;
		Pair pair;
		String[] columns;

		while ((line = in.readLine()) != null) {
			columns = line.split(",");

			city = columns[0].toUpperCase();
			country = columns[1].toUpperCase();
			gender = columns[2].toUpperCase();
			currencyType = columns[3].toUpperCase();

			amount = Double.parseDouble(columns[4]);
			// Convert to USD
			amount = amount / CurrencyConverter.getMultiplier(currencyType);

			// Since, country might not be present
			if (country.isEmpty()) {

				key = city + "," + gender;

				// City seen before, so update total amount and add total count
				if (map.containsKey(key)) {
					pair = map.get(key);
					pair.setFirstValue(pair.getFirstValue() + amount);
					pair.setSecondValue(pair.getSecondValue() + 1);
				}
				// New city found, so create new entry
				else {
					map.put(key, new Pair(amount, 1));
				}
			}
			// If country is present
			else {

				key = country + "," + gender;

				// country seen before, so update total amount and add total count
				if (map.containsKey(key)) {
					pair = map.get(key);
					pair.setFirstValue(pair.getFirstValue() + amount);
					pair.setSecondValue(pair.getSecondValue() + 1);
				}
				// New country found, so create new entry
				else {
					map.put(key, new Pair(amount, 1));
				}

			}

		} // End of File(csv) Read

		in.close();

	}

	public List<String> calculateAvg() {

		List<String> toSort = new ArrayList<>();
		double avg;
		Pair pair;

		for (Map.Entry<String, Pair> entry : map.entrySet()) {
			pair = entry.getValue();
			avg = pair.getFirstValue() / pair.getSecondValue();
			avg = Math.round(avg * 100d) / 100d; // Round to 2 decimal place

			toSort.add(entry.getKey() + "," + avg);
		}

		// Sort the final array
		Collections.sort(toSort);
		
		System.out.println(toSort);
		
		return toSort;

	}

	public void writeToCSV(List<String> lines, String outputFileLocation) throws IOException {

		FileWriter writer = new FileWriter(outputFileLocation);

		for (String line : lines)
			writer.write(line + "\n");

		writer.close();
	}

}
