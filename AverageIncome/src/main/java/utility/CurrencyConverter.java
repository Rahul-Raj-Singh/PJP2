package utility;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
	
	static Map<String, Double> map = new HashMap<>(); 
	
	static {
		map.put("INR", 66.0);
		map.put("GBP", 0.67);
		map.put("SGD", 1.5);
		map.put("HKD", 8.0);
	}
	
	public static Double getMultiplier(String currencyType) {
		
		if(map.containsKey(currencyType))
			return map.get(currencyType);
		
		System.out.println("Don't know this currency !!");
		
		return null;
	}

}
