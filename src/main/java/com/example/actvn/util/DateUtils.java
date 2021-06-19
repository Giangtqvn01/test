package com.example.actvn.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class DateUtils {
//	private static Logit log = Logit.getInstance(DateUtils.class);
	static final String RANDOM_STR = "0123456789abcdefghijklmnopqrstuvwxyz";
	static final String RANDOM_NUMBER = "0123456789";
	public static final String PATTERN_DDMMYYYY_DOT = "dd.MM.yyyy";
	public static final String PATTERN_DDMMYYYY = "dd/MM/yyyy";
	public static final String PATTERN_MMDDYYYY_BLANK = "MMddyyyy";
	public static final String PATTERN_DDMMYYYYHHMMSS = "dd/MM/yyyy HH:mm:ss";
	public static final String PATTERN_MMDDYYYY = "MM/dd/yyyy";
	public static final String PATTERN_MMYYYY = "MM/yyyy";
	public static final String PATTERN_MMDDYYYY_HHMMSS = "MM/dd/yyyy HH:mm:ss";
	public static final String PATTERN_MMDDYYYY_HHMM = "MM/dd/yyyy HH:mm";
	public static final String PATTERN_YYMMDD = "yyyy/MM/dd";
	public static final String PATTERN_YYMMDD_HH_MM = "yyyy/MM/dd HH:mm";
	public static final String PATTERN_YYMMDD_BLANK = "yyyyMMdd";
	public static final String PATTERN_YYYYMMDD_BLANK = "yyyyMMdd";
	public static final String PATTERN_YYYYMMDD_HH_COLON_MM = "yyyyMMdd HH:mm";
	public static final String PATTERN_SLASH_YYYYMMDD_HH_COLON_MM = "yyyy/MM/dd HH:mm";
	public static final String PATTERN_SLASH_YYYYMMDD_HH_COLON_MM_SS = "yyyy/MM/dd HH:mm:ss";
	public static final String PATTERN_FOR_GEN_ID = "MMddHHmm";
	public static final String PATTERN_YYYMMDD_HHMMSS = "yyyyMMdd HH:mm:ss";
	public static final String PATTERN_YYYMMDD_HHMMSS_FULL = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String PATTERN_YYMMDD_HHMMSS_BLANK = "yyyyMMddHHmmss";
	public static final String PATTERN_CSV_FILE = "MMddyyyy_kkmmss";
	public static final String PATTERN_DDMMYYYY_BLANK = "ddMMyyyy";
	public static final String PATTERN_DDMMYYYY_HHMMSS = "dd/MM/yyyy HH:mm:ss";
	public static final String PATTERN_YYYYMM_BLANK = "yyyyMM";
	public static final String PATTERN_YYMMDD_HHMMSSS="yyyy-MM-dd HH:mm:ss.S";
	public static final String PATTERN_HHMM="HHmm";
	public static final String PATTERN_HHMMSS_COLON="HH:mm:ss";
	public static final String PATTERN_HHMMSS="HHmmss";
	public static final String PATTERN_YYYY_MM_DD_HHMM = "yyyy-MM-dd HHmm";
	public static final String PATTERN_DD_MM_YYYY = "dd-MM-yyyy";

	public static final String PATTERN_YYmmDDHHmm = "yyMMddHHmm";
	public static final String PATTERN_YYYYmmDDHHmmSS = "yyyyMMddHHmmss";


	public static String generateRandomCode(int len) {
		Random rand = new Random();
		StringBuffer sb = new StringBuffer(len);
		char str;
		boolean checkOneNumber = false;
		for (int i = 0; i < len; i++) {
			str = RANDOM_STR.charAt(rand.nextInt(RANDOM_STR.length()));
			if (StringUtils.isNumeric(str + "")) {
				checkOneNumber = true;
			}
			sb.append(str);
		}

		// set one up case character
		int position1 = rand.nextInt(len);
		char str_check = sb.charAt(position1);
		while (StringUtils.isNumeric(str_check + "")) {
			position1 = rand.nextInt(len);
			str_check = sb.charAt(position1);
		}

		sb.setCharAt(position1, Character.toUpperCase(str_check));

		// check at least one number
		if (!checkOneNumber) {
			int position3 = rand.nextInt(len);
			while (position3 == position1) {
				position3 = rand.nextInt(len);
			}
			sb.setCharAt(position3,
					RANDOM_NUMBER.charAt(rand.nextInt(RANDOM_NUMBER.length())));
		}

		return sb.toString();
	}
	public static String convertDatetoString(Date date, String format){
		 if(date != null)
		 {
			 DateFormat sdf = new SimpleDateFormat(format);
			 String strDate =sdf.format(date);
			 return strDate;
		 }
		 return null;
	}

	public static String getCurrentDate(String format){
		 Date date = new Date();
		 DateFormat sdf = new SimpleDateFormat(format);
		 String strDate =sdf.format(date);
		 return strDate;
	}
	public static String changeFormatDate(String date,String fomartFrom,String formatTo){
		try{
			if(!StringUtils.isEmpty(date)){
				DateFormat sdf = new SimpleDateFormat(fomartFrom);
				Date mDate = sdf.parse(date);
				DateFormat mSdf = new SimpleDateFormat(formatTo);
				return mSdf.format(mDate);
			}
		}catch (Exception e) {
			//ignore date wrong
			//log.error(ex.getMessage(),ex);
		}
		return "";
	}
	public static Date convertStringToDate(String date,String format){
		try{
			if(!StringUtils.isEmpty(date)){
				DateFormat sdf = new SimpleDateFormat(format);
				return sdf.parse(date);
			}
		}catch (Exception e) {
//			log.error(e.getMessage(),e);
		}

		return null;
	}

	/**
	 * subtract days to date in java
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date subtractDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
		return cal.getTime();
	}

	public static String validateDateFormat(String dateToValdate,String dateFormat) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		formatter.setLenient(false);
		String date = null;
		Date parsedDate = formatter.parse(dateToValdate);
		date = formatter.format(parsedDate);
		return date;
	}

	public static String convertDateToString(Date date, String format){
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	public static String dateToString(LocalDate date, String formatDate){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatDate);
		return date.format(formatter);
	}

	public static String dateToString(LocalDateTime date, String formatDate){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatDate);
		return date.format(formatter);
	}

	public static String dateStrToOtherFormat(String dateStr, String currentFormat, String newFormat)
			throws ParseException {
		SimpleDateFormat format1 = new SimpleDateFormat(currentFormat);
		SimpleDateFormat format2 = new SimpleDateFormat(newFormat);
		Date date = format1.parse(dateStr);
		return format2.format(date);
	}

    public static String addMonth(Integer numberMonth,String format) {
        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, numberMonth);
        return formatDate.format(cal.getTime());
    }

    public static String getLastMonth(String format) {
        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return formatDate.format(cal.getTime());
    }

    public static String convertTimestampToString(Timestamp timestamp, String format) throws Exception{
		if (timestamp == null) return  null;
		Date date = new Date(timestamp.getTime());
		DateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

}
