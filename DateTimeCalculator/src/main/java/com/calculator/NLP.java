package com.calculator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NLP {
	
	private Map<String, String> nlp;	
	
	public NLP() {
		setupMap();
	}
	
	public void setupMap() {
			
		nlp = new HashMap<>();
		
		nlp.put("today", getInstance().add("0 day"));	
		nlp.put("tomorrow", getInstance().add("1 day"));
		nlp.put("day-after-tomorrow", getInstance().add("2 day"));
		nlp.put("yesterday", getInstance().subtract("1 day"));
		nlp.put("day-before-yesterday", getInstance().subtract("2 day"));
		nlp.put("last-week", getInstance().subtract("1 week"));
		nlp.put("previous-week", nlp.get("last-week"));
		nlp.put("next-week", getInstance().add("1 week"));
		nlp.put("next-month", getInstance().add("1 month"));
		nlp.put("month-after", nlp.get("next-month"));
		nlp.put("next-year", getInstance().add("12 months"));
		nlp.put("last-month", getInstance().subtract("1 month"));
		nlp.put("month-before", nlp.get("last-month"));
		nlp.put("last-year", getInstance().subtract("12 months"));
		
	}
	
	public String translate(String s) {
		
		if(nlp.containsKey(s.toLowerCase()))
			return nlp.get(s.toLowerCase());
		
		// Complex NLPs
		String regex = "(\\d+) (weeks?|months?|days?|years?) (from|earlier).*";
		Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(s);
		
		while(matcher.find()) {
			
			if(matcher.group(3).equals("from")) {
				return matcher.group(2).startsWith("year") ? 
						getInstance().add("12 months") : getInstance().add(matcher.group(1) + " " + matcher.group(2));
			}
			
			if(matcher.group(3).equals("earlier")) {
				return matcher.group(2).startsWith("year") ? 
						getInstance().subtract("12 months") : getInstance().subtract(matcher.group(1) + " " + matcher.group(2));
			}
				
		}
		
		return "Cannot understand language";
	}
	
	public Calculator getInstance() {
		return new Calculator(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
	}
	
	public void printMap() {
		
		for(Map.Entry<String, String> entry: nlp.entrySet()) {
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}
	}


}
