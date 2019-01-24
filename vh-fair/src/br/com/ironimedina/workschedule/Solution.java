package br.com.ironimedina.workschedule;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	public static List<String> findSchedules(int workHours, int dayHours, String pattern) {
		Long daysToSolve = pattern.chars().filter(ch -> ch =='?').count();
		Integer usedHours = 0;
		for(int i = 0; i < pattern.toCharArray().length; i++) {
			if(Character.isDigit(pattern.toCharArray()[i])) {
				usedHours += Character.getNumericValue(pattern.toCharArray()[i]);
			}
		}
		Integer remainingHours = workHours - usedHours;
		
		List<String> schedule = new ArrayList<>();
		
		List<String> permutedDays = permuteDays(remainingHours, daysToSolve, dayHours);
		for(int i = 0; i < permutedDays.size(); i++) {
			String newSch = permutedDays.get(i);
			String cpPattern = pattern;
			for(Character ch: newSch.toCharArray()) {
				cpPattern = cpPattern.replaceFirst("\\?", String.valueOf(ch));
			}
			schedule.add(cpPattern);
		}
		
		return schedule;
    }
	
	private static List<String> permuteDays(Integer remainingHours, Long daysToSolve, Integer dayHours) {
		List<String> permuted = new ArrayList<>();
		
		if(remainingHours % daysToSolve == 0
				&& remainingHours/daysToSolve == dayHours) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < daysToSolve; i++) {
				sb.append(remainingHours/daysToSolve);
			}
			permuted.add(sb.toString());
		} else if(remainingHours < daysToSolve/dayHours){
			for(int i = 0; i < daysToSolve; i++) {
				
			}
		} else {
			for(int i = 0; i <= remainingHours; i++) {
				permuted.add(i + "" + (remainingHours-i));
			}
		}
		return permuted;
	}
	
	private static List<String> permute(String str, int l, int r, List<String> lst) {
        
		if (l == r) {
            lst.add(str);
        } else {
            for (int i = l; i <= r; i++) {
                str = swap(str,l,i);
                permute(str, l+1, r, lst);
                str = swap(str,l,i);
            }
        }
		return lst;
    }

    public static String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
	
	public static void main(String[] args) {
		//System.out.println(findSchedules(3, 2, "??2??00"));
//		System.out.println(findSchedules(56, 8, "??8????"));
		System.out.println(findSchedules(24, 4, "08??840"));
		List<String> lst = new ArrayList<>();
//		System.out.println(permute("ABC", 0, "ABC".length()-1, lst));
	}
	
}
