package br.com.ironimedina.reformatdate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		final List<String> dates = Arrays.asList("20th Oct 2052", "6th Jun 1933", "26th May 1960", "20th Sep 1958",
				"16th Mar 2068", "25th May 1912", "16th Dec 2018", "26th Dec 2061", "4th Nov 2030", "28th Jul 1963");
		List<String> result = reformatDate(dates);
		System.out.println(result);
	}

	/*
	 * Complete the 'reformatDate' function below.
	 *
	 * The function is expected to return a STRING_ARRAY. The function accepts
	 * STRING_ARRAY dates as parameter.
	 */

	public static List<String> reformatDate(List<String> dates) {
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern(
        		"d['st']['nd']['rd']['th'] MMM yyyy", Locale.ENGLISH);
        
        List<String> formattedDates = new ArrayList<>();
        for (String date : dates) {
            LocalDate fDate = LocalDate.parse(date, dtFormat);
            formattedDates.add(fDate.toString());
        }

        return formattedDates;
	}

}
