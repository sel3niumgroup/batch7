package utils;

public enum DateFormats {
	yyyyMMdd("yyyy-MM-dd"),
	yyyyMMMdd("yyyy-MMM-dd");
	
	private String format;
	
	DateFormats(String format){
		this.format = format;
	}
	
	public String format() {
		return format;
	}
}
