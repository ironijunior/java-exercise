package br.com.ironimedina.jsonstock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.gson.Gson;

public class Solution {

	private static final List<String> DAYS = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
			"Saturday");
	
	public static void main(String[] args) {
		openAndClosePrices("1-January-2000", "22-February-2000", "Monday");
	}
	
	public static void openAndClosePrices(String firstDate, String lastDate, String weekDay) {
		String stocksJson = "";
		try {
			stocksJson = getContentFrom("https://jsonmock.hackerrank.com/api/stocks");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();
		StockEntity stockEntity = gson.fromJson(stocksJson, StockEntity.class);

		stockEntity.getData().stream()
			.filter((stockData) -> hasDateMatching(stockData, firstDate, lastDate, weekDay))
			.forEach((stockData) -> {
				StringBuilder result = new StringBuilder(stockData.getDate());
				result.append(" ")
					.append(stockData.getOpen())
					.append(" ")
					.append(stockData.getClose());
				
				System.out.println(result.toString());
			});
	}
	
	private static String getContentFrom(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1) {
				buffer.append(chars, 0, read);
			}
			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}
	
	private static boolean hasDateMatching(StockDataEntity stockData, String firstDate, String lastDate, String weekDay) {
		try {
			return (parseDate(stockData.date).compareTo(parseDate(firstDate)) >= 0)
					&& (parseDate(stockData.date).compareTo(parseDate(lastDate)) <= 0)
					&& (getDayOfDate(stockData.date) == (DAYS.indexOf(weekDay) + 1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static Date parseDate(String date) throws ParseException {
		return new SimpleDateFormat("dd-MMM-yyyy", Locale.US).parse(date);
	}

	private static int getDayOfDate(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(date));
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	class StockDataEntity {

		private String date;
		private float open;
		private float high;
		private float low;
		private float close;

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public float getOpen() {
			return open;
		}

		public void setOpen(float open) {
			this.open = open;
		}

		public float getHigh() {
			return high;
		}

		public void setHigh(float high) {
			this.high = high;
		}

		public float getLow() {
			return low;
		}

		public void setLow(float low) {
			this.low = low;
		}

		public float getClose() {
			return close;
		}

		public void setClose(float close) {
			this.close = close;
		}
	}

	class StockEntity {

		private Integer page;
		private Integer perPage;
		private Integer total;
		private Integer totalPages;
		private List<StockDataEntity> data = null;

		public Integer getPage() {
			return page;
		}

		public void setPage(Integer page) {
			this.page = page;
		}

		public Integer getPerPage() {
			return perPage;
		}

		public void setPerPage(Integer perPage) {
			this.perPage = perPage;
		}

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public Integer getTotalPages() {
			return totalPages;
		}

		public void setTotalPages(Integer totalPages) {
			this.totalPages = totalPages;
		}

		public List<StockDataEntity> getData() {
			return data;
		}

		public void setData(List<StockDataEntity> data) {
			this.data = data;
		}
	}

}
