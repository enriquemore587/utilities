package com.bbva.intranet.utilities;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class DateUtility {

	private static final Logger logger = LoggerFactory.getLogger(DateUtility.class);

	private static SimpleDateFormat sdf;

	public static Date createDateWithFormat(String format) {
		if (format != null) {
			return createDateTimeWithFormat(format).toDate();
		}
		return null;
	}

	public static Date createDateWithLocale(String format, Locale locale) {
		if (locale != null) {
//			return createDateTimeWithFormat(format, locale).toDate();
			DateTime dateTime = createDateTimeWithFormat(format, locale);
			logger.info(dateTime.toString());
//			return dateTime.toDate();
			return dateTime.toLocalDateTime().toDate();
		}
		return null;
	}

//	public static Date createDateWithDateTimeZone(String format, DateTimeZone dateTimeZone) {
	public static Date createDateWithDateTimeZone(DateTimeZone dateTimeZone) {
		if (dateTimeZone != null) {
			return new DateTime(dateTimeZone).toLocalDateTime().toDate();
		}
		return null;
	}

	public static Date createDateWithFormat(Date date, String format) {
		if (date != null && format != null) {
			return createDateTimeWithFormat(new DateTime(date), format).toDate();
		}
		return null;
	}
	
	public static DateTime createDateTimeWithFormat(String format) {
		DateTime date = new DateTime();
		
		return createDateTimeWithFormat(date, format);
	}

	public static DateTime createDateTimeWithFormat(String format, Locale locale) {
		DateTime date = new DateTime();

		return createDateTimeWithFormat(date, format, locale);
	}

//	public static DateTime createDateTimeWithFormat(String format, DateTimeZone dateTimeZone) {
	public static DateTime createDateTimeWithDateTimeZone(DateTimeZone dateTimeZone) {
		return new DateTime(dateTimeZone);
	}

	public static DateTime createDateTimeWithFormat(DateTime date, String format) {
		if (date != null && format != null) {
			sdf = new SimpleDateFormat(format);
			String srtDate = sdf.format(date.toDate());
			
			return convertStringToDateTime(srtDate, format);
		}
		return null;
	}

	public static DateTime createDateTimeWithFormat(DateTime date, String format, Locale locale) {
		if (date != null && format != null) {
			sdf = new SimpleDateFormat(format, locale);
			String srtDate = sdf.format(date.toDate());

			return convertStringToDateTime(srtDate, format);
		}
		return null;
	}

	public static String convertDateToString(Date date, String format) {
		if (date != null && format != null) {
			return convertDateTimeToString(new DateTime(date), format);
		}
		return null;
	}
	
	public static Date 	convertStringToDate(String strDate, String format) {
		if (strDate != null && !strDate.equals("")) {
			return convertStringToDateTime(strDate, format).toDate();
		}
		return null;
	}
	
	public static String convertDateTimeToString(DateTime date, String format) {
		if (date != null && format != null) {
			sdf = new SimpleDateFormat(format);
			return sdf.format(date.toDate());
		}
		
		return null;
	}
	
	public static DateTime convertStringToDateTime(String strDate, String format) {
		if (strDate != null && !strDate.equals("")) {
            DateTimeFormatter dtf = DateTimeFormat.forPattern(format);
            
            return DateTime.parse(strDate, dtf);
    	}
		
		return null;
	}

	public static Date addDays(Date date, int dias) {
		if (date != null) {
			DateTime dateTime = new DateTime(date);
			return dateTime.plusDays(dias).toDate();
		}
		return null;
	}
	
	public static boolean isCurrentRangeOverlapNewRange(Date currentBegin, Date currentEnd, Date newBegin, Date newEnd) {
		boolean isRange = false;
		
		if (isDate1LessOrEqualThanDate2(currentBegin, newBegin)
//				|| isDate1GreaterThanDate2(currentBegin, newEnd)) {
				|| isDate1LessThanDate2(currentBegin, newEnd)) {
			if (isDate1GreaterOrEqualThanDate2(currentEnd, newEnd)
//					|| isDate1LessThanDate2(currentEnd, newBegin)) {
					|| isDate1GreaterThanDate2(currentEnd, newBegin)) {
				isRange = true;
			}
		}
		
		
		return isRange;
	}
	
	public static boolean isCurrentDateBetweenDate1AndDate2(Date date1, Date date2) {
		Date currentDate = createDateWithFormat("dd/MM/yyyy");
		
		return isDateBetweenData1AndDate2(currentDate, date1, date2);
	}
	
	public static boolean isDateBetweenData1AndDate2(Date date, Date date1, Date date2) {
		boolean isRange = false;
		
		if (isDate1LessOrEqualThanDate2(date1, date) && isDate1GreaterOrEqualThanDate2(date2, date)) {
			isRange = true;
		}
		
		return isRange;
	}
	
	public static boolean isDate1EqualsDate2(Date date1, Date date2) {
		boolean isEqual = false;
		
		DateTime fechaTime1 = new DateTime(date1);
		DateTime fechaTime2 = new DateTime(date2);
		if (fechaTime1.isEqual(fechaTime2)) {
			isEqual = true;
		}
		
		return isEqual;
	}
	
	public static boolean isDate1GreaterThanDate2(Date date1, Date date2) {
		boolean isGreater = false;
		
		DateTime fechaTime1 = new DateTime(date1);
		DateTime fechaTime2 = new DateTime(date2);
		if (fechaTime1.isAfter(fechaTime2)) {
			isGreater = true;
		}
		
		return isGreater;
	}
	
	public static boolean isDate1LessThanDate2(Date date1, Date date2) {
		boolean isLess = false;
		
		DateTime fechaTime1 = new DateTime(date1);
		DateTime fechaTime2 = new DateTime(date2);
		if (fechaTime1.isBefore(fechaTime2)) {
			isLess = true;
		}
		
		return isLess;
	}
	
	public static boolean isDate1GreaterOrEqualThanDate2(Date date1, Date date2) {
		boolean isGreaterOrEqual = false;
		
		DateTime fechaTime1 = new DateTime(date1);
		DateTime fechaTime2 = new DateTime(date2);
		if (fechaTime1.isAfter(fechaTime2) || fechaTime1.isEqual(fechaTime2)) {
			isGreaterOrEqual = true;
		}
		
		return isGreaterOrEqual;
	}
	
	public static boolean isDate1LessOrEqualThanDate2(Date date1, Date date2) {
		boolean isLessOrEqual = false;
		
		DateTime fechaTime1 = new DateTime(date1);
		DateTime fechaTime2 = new DateTime(date2);
		if (fechaTime1.isBefore(fechaTime2) || fechaTime1.isEqual(fechaTime2)) {
			isLessOrEqual = true;
		}
		
		return isLessOrEqual;
	}
	
}