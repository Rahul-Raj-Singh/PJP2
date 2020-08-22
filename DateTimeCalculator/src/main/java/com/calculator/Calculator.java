package com.calculator;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

	private LocalDate date1;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public Calculator(String s) {
		
		this.date1 = LocalDate.parse(s, formatter);
		
	}
	
	/*
	 *  
	 *  Utility Methods
	 *  
	 */

	public String getWeekDay() {

		return date1.getDayOfWeek().toString();

	}

	public String getWeekNumber() {

		int weekM, weekY;
		String follower1, follower2;
		
		weekM = getWeekNumberInMonth();
		follower1 = numberFollower(weekM);	// gets st of 1st, rd of 3rd etc
		
		weekY = getWeekNumberInYear();
		follower2 = numberFollower(weekY);
		
		return String.format("Current week is %s%s week of month and %s%s week of year", 
				weekM, follower1, weekY, follower2);

	}

	public int getWeekNumberInMonth() {

		return date1.get(ChronoField.ALIGNED_WEEK_OF_MONTH);

	}

	public int getWeekNumberInYear() {

		return date1.get(ChronoField.ALIGNED_WEEK_OF_YEAR);

	}
	
	/*
	 * Accepts weeks, days and months
	 */
	public String add(String s) {
		
		int[] dwm = getDayWeekMonth(s);
		int day = dwm[0], week = dwm[1], month = dwm[2];
		
		if(day != 0)
			date1 =  date1.plusDays(day);
		
		if(week != 0)
			date1 =  date1.plusWeeks(week);
		
		if(month != 0)
			date1 =  date1.plusMonths(month);
		
		
		return date1.format(formatter);
	}
	
	/*
	 *  Accepts weeks, days, months and dates
	 */
	public String subtract(String s) {
		
		if(s.matches("\\d{2}-\\d{2}-\\d{4}"))
			return subtractDate(s);
		
		int[] dwm = getDayWeekMonth(s);
		int day = dwm[0], week = dwm[1], month = dwm[2];
		
		if(day != 0)
			date1 =  date1.minusDays(day);
		
		if(week != 0)
			date1 =  date1.minusWeeks(week);
		
		if(month != 0)
			date1 =  date1.minusMonths(month);
		
		
		return date1.format(formatter);
		
					
	}
	
	public String subtractDate(String s) {
		
		LocalDate date2 = LocalDate.parse(s, formatter);
		Period period = Period.between(date1, date2);
		
		// After
		if(date1.isBefore(date2)) {
			return String.format("AFTER: %s days %s months %s years", period.getDays(), period.getMonths(), period.getYears());
		}
		
		//Before
		if(!date1.isBefore(date2)) {
			return String.format("BEFORE: %s days %s months %s years", -period.getDays(), -period.getMonths(), -period.getYears());
		}
		
		// never hits
		return null;
		
	}
	
	public String NLP(String s) {
		return null;
	}
	
	/*
	 * 
	 * Helper Methods
	 * 
	 */
	
	public String numberFollower(int n) {
		int d = n % 10;
		
		if(d == 1)
			return "st";
		
		if(d == 2)
			return "nd";
		
		if(d == 3)
			return "rd";
		
		return "th";
	}
	
	/*
	 * Returns an array of int {days, weeks, months}
	 */
	public int[] getDayWeekMonth(String s) {
		
        String regex = "(\\d+) days?|(\\d+) weeks?|(\\d+) months?";
        Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(s);
        String day = null, week = null, month= null;
        
        while(matcher.find()) {
        	
        	if (matcher.group(1) != null)
        		day = matcher.group(1);
        	
        	if (matcher.group(2) != null)
        		week = matcher.group(2);
        	
        	if (matcher.group(3) != null)
        		month = matcher.group(3);
        }
        
         return new int[] {day != null ? Integer.parseInt(day) : 0, 
        					week != null ? Integer.parseInt(week) : 0,
        					month != null ? Integer.parseInt(month) : 0};
	}
	
	
	



}
