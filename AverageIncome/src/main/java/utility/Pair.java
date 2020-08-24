package utility;

public class Pair {

	private Double firstValue;		// cumulative amount for particular country/city
	private Integer secondValue;	// count for particular country/city
	
	public Pair(Double firstValue, Integer secondValue) {
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}

	public Double getFirstValue() {
		return firstValue;
	}

	public void setFirstValue(Double firstValue) {
		this.firstValue = firstValue;
	}

	public Integer getSecondValue() {
		return secondValue;
	}

	public void setSecondValue(Integer secondValue) {
		this.secondValue = secondValue;
	}

	@Override
	public String toString() {
		return "(" + this.firstValue + ", " + this.secondValue + ")";
	}
	
	

}
