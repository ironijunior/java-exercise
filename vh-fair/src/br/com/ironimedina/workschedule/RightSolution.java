package br.com.ironimedina.workschedule;

import java.util.LinkedList;
import java.util.List;

public class RightSolution {

	public static List<String> findSchedules(int workHours, int dayHours, String pattern) {
        // Write your code here

        int sum = 0;
        int count = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != '?') {
                sum += Character.getNumericValue(pattern.charAt(i));
                count += 1;
            }
        }

        LinkedList<LinkedList<String>> resultAfterFilling = findSchedulesHelper(pattern.length() - count, workHours - sum, dayHours, new String());

        LinkedList<String> res = new LinkedList<>();
        for (LinkedList<String> str:resultAfterFilling) {
            StringBuilder pat = new StringBuilder(pattern);

            String s = str.removeFirst();
            for (int i = 0; i < s.length(); i++) {
                int index = pat.indexOf("?");
                pat.setCharAt(index, s.charAt(i));
            }
            res.add(pat.toString());

        }

        return res;
    }

    private static LinkedList<LinkedList<String>> findSchedulesHelper(int k, int workHours, int dayHours, String res) {
        if (k * dayHours < workHours) {
            return null;
        }
        if (k == 1) {
            res += Integer.toString(workHours);
            LinkedList<String> lst = new LinkedList<>();
            LinkedList<LinkedList<String>> a = new LinkedList<>();
            lst.add(res);
            a.add(lst);
            return a;
        }

        LinkedList<LinkedList<String>> result = new LinkedList<>();
        for (int i = 0; i <= dayHours; i++) {
            if (workHours - i < 0) {
                break;
            }
            LinkedList<LinkedList<String>> subRes = findSchedulesHelper(k - 1, workHours - i, dayHours, new String(res) + Integer.toString(i));
            if (subRes != null) {
                result.addAll(subRes);
            }
        }
        return result;
    }

    public static void main(String[] args) {
//    	System.out.println(RightSolution.findSchedules(3, 2, "??2??00"));
//		System.out.println(RightSolution.findSchedules(56, 8, "??8????"));
//		System.out.println(RightSolution.findSchedules(24, 4, "08??840"));
        System.out.println(RightSolution.findSchedules(13, 2, "??2??00"));
    }
	
}
