package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class TestUtils {

	public static SimpleDateFormat formatter = new SimpleDateFormat(DateFormats.yyyyMMdd.format());

	/**
	 * This method parses the JSON file and returns value of the Key <br>
	 * <b>Ex: jsonHandler("data.json","loginDetails",0,"url");
	 * 
	 * @param fileName
	 * @param arrayName
	 * @param index
	 * @param key
	 * @return Key
	 */
	public static String getData(String fileName, String arrayName, int index, String key) {
		try {
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(new FileReader("./testData/" + fileName));
			JSONObject jsonObj = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObj.get(arrayName);
			JSONObject Obj = (JSONObject) jsonArray.get(index);
			String str = (String) Obj.get(key);
			return str;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method take inputDate and converts its format <br>
	 * <b>Example:</b><br>
	 * When we pass the following parameters (String inputDate=<b>"2019-10-15"</b>,
	 * fromFormat = "yyyy-MM-dd", toFormat = "yyyy-MMM-dd") then the inputDate is
	 * converted to <b>"2019-Oct-15".</b>
	 * 
	 * @param inputDate
	 * @param fromFormat
	 * @param toFormat
	 * @return
	 */
	public static String dateFormatConverter(String inputDate, String fromFormat, String toFormat) {
		SimpleDateFormat from = new SimpleDateFormat(fromFormat);
		SimpleDateFormat to = new SimpleDateFormat(toFormat);
		String date = null;
		try {
			date = to.format(from.parse(inputDate));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * This method adds Months to a given date. <br>
	 * <b>Example:</b><br>
	 * String inputDate=<b>"2019-03-22"</b>, int monthsToAdd = 5. <br>
	 * Then the result date is <b>"2019-08-22"</b>
	 * 
	 * @param inputDate
	 * @param monthsToAdd
	 * @return
	 */
	public static String addMontsToDate(String inputDate, int monthsToAdd) {
		try {
			Date dat = formatter.parse(inputDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dat);
			cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) + monthsToAdd));
			dat = cal.getTime();
			String outputDate = formatter.format(dat);
			return outputDate;
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String substractMonths(String inputDate,int YearsToSubstract) {
		try {
			Date dat = formatter.parse(inputDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dat);
			cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) - YearsToSubstract));
			dat = cal.getTime();
			String outputDate = formatter.format(dat);
			return outputDate;
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public static String substractYears(String inputDate,int YearsToSubstract) {
		try {
			Date dat = formatter.parse(inputDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dat);
			cal.set(Calendar.YEAR, (cal.get(Calendar.YEAR) - YearsToSubstract));
			dat = cal.getTime();
			String outputDate = formatter.format(dat);
			return outputDate;
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String diffOfTwoDates(String Date1,String Date2) {
		try {
			Date d1 = formatter.parse(Date1);
			Date d2 = formatter.parse(Date2);
			long diff = d1.getYear()-d2.getYear();
			return String.valueOf(diff);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<String> listOfAllFiles(String dirPath){
		List<String> listOfFiles = null;
		try(Stream<Path> walk = Files.walk(Paths.get(dirPath))){
			listOfFiles = walk.filter(Files::isRegularFile).map(x -> x.toString()).sorted()
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listOfFiles;
	}
	
	public static Map<String, List<Pojo>> testCasesGroupByPolicyNo(String dataFile) throws IOException {
		Pattern pattern = Pattern.compile(",");
		BufferedReader in = new BufferedReader(new FileReader(dataFile));
		Map<String, List<Pojo>> grouped = in.lines().skip(1).map(line->{
			String[] arr = pattern.split(line);
			return new Pojo(arr[0],arr[1],arr[2],arr[3],arr[4]);	
		}).collect(Collectors.groupingBy(Pojo::getPolicy_No,LinkedHashMap::new,Collectors.toList()));
		in.close();
		return grouped;
	}

}
