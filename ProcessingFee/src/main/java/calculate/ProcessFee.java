package calculate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessFee {

	Map<String, Double> fee;
	Map<String, List<Integer>> buySellCount;

	public ProcessFee() {
		fee = new HashMap<>();
		buySellCount = new HashMap<>();
	}

	public double normalTransaction(String priority, String transactionType) {

		if (priority.equalsIgnoreCase("Y")) // High
			return 500;

		if (priority.equalsIgnoreCase("N")) { // Normal

			if (transactionType.equalsIgnoreCase("SELL") || transactionType.equalsIgnoreCase("WITHDRAW"))
				return 100;

			if (transactionType.equalsIgnoreCase("BUY") || transactionType.equalsIgnoreCase("DEPOSIT"))
				return 50;
		}

		return 0;
	}

	public void readAndParse(String inputFilePath) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader(inputFilePath));

		String clientId, securityId, transactionType, transactionDate, priority;
		String[] a;
		String key;

		String line;
		while ((line = in.readLine()) != null) {
			a = line.split(",");

			clientId = a[1].trim().toUpperCase();
			securityId = a[2].trim().toUpperCase();
			transactionType = a[3].trim().toUpperCase();
			transactionDate = a[4].trim().toUpperCase();
			priority = a[6].trim().toUpperCase();

			key = String.format("%s,%s,%s,%s", clientId, transactionType, transactionDate, priority);

			// Normal Transaction
			if (transactionType.equalsIgnoreCase("WITHDRAW") || transactionType.equalsIgnoreCase("DEPOSIT")) {

				if (fee.containsKey(key))
					fee.put(key, fee.get(key) + normalTransaction(priority, transactionType));
				else
					fee.put(key, normalTransaction(priority, transactionType));
			}

			// Count Buy and Sell
			if (transactionType.equalsIgnoreCase("BUY") || transactionType.equalsIgnoreCase("SELL")) {

				key = String.format("%s,%s,%s,%s", clientId, securityId, transactionDate, priority);

				if (buySellCount.containsKey(key)) {

					List<Integer> values = buySellCount.get(key);

					if (transactionType.equalsIgnoreCase("BUY"))
						values.set(0, values.get(0) + 1); // Increment BUY count by 1
					else
						values.set(1, values.get(1) + 1); // Increment SELL count by 1
				} else {

					if (transactionType.equalsIgnoreCase("BUY"))
						buySellCount.put(key, new ArrayList<>(Arrays.asList(1, 0))); // Initialize BUY count with 1
					else
						buySellCount.put(key, new ArrayList<>(Arrays.asList(0, 1))); // Initialize SELL count with 1

				}
			}

		} // End of file read

		in.close();

//		populateBuySell();

	}

	public void populateBuySell() {

//		System.out.println(fee);
//		System.out.println(buySellCount);

		String clientId, securityId, transactionType, transactionDate, priority;
		String buyKey, sellKey;
		String[] a;
		int buyCount, sellCount;

		for (Map.Entry<String, List<Integer>> entry : buySellCount.entrySet()) {

			a = entry.getKey().split(",");
			buyCount = entry.getValue().get(0);
			sellCount = entry.getValue().get(1);

			clientId = a[0];
			securityId = a[1];
			transactionDate = a[2];
			priority = a[3];

			buyKey = String.format("%s,%s,%s,%s", clientId, "BUY", transactionDate, priority);
			sellKey = String.format("%s,%s,%s,%s", clientId, "SELL", transactionDate, priority);

			int min = Math.min(buyCount, sellCount); // Represents number of intra-day transactions

			// Intra-day transaction charged 10 USD
			if (min != 0) {
				fee.put(buyKey, min * 10.0);
				fee.put(sellKey, min * 10.0);
			}

			// Remaining are NORMAL transactions
			for (int i = 0; i < (sellCount - min); i++) {
				
				if(fee.containsKey(sellKey))
					fee.put(sellKey, fee.get(sellKey) + normalTransaction(priority, "SELL"));
				else
					fee.put(sellKey, normalTransaction(priority, "SELL"));
			}

			for (int i = 0; i < (buyCount - min); i++) {
				
				if(fee.containsKey(buyKey))
					fee.put(buyKey, fee.get(buyKey) + normalTransaction(priority, "BUY"));
				else
					fee.put(buyKey, normalTransaction(priority, "BUY"));
			}
				

		} // end of Count Map

//		sortFee();
	}

	public List<String> sortFee() {

		List<String> ans = new ArrayList<>();

		for (Map.Entry<String, Double> entry : fee.entrySet())
			ans.add(entry.getKey() + "," + entry.getValue());

		Collections.sort(ans);

		return ans;
	}

	public void writeToFile(List<String> list, String outputFilePath) throws IOException {

		FileWriter writer = new FileWriter(outputFilePath);

		for (String line : list)
			writer.write(line + "\n");

		writer.close();
	}

}
