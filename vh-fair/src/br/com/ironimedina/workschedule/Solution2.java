package br.com.ironimedina.workschedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {

	public static List<String> findSchedules(int workHours, int dayHours, String pattern) {
        List<String> res = new ArrayList<>();
        int questions = 7;
        for (char c : pattern.toCharArray()) {
            if (Character.isDigit(c)) {
                workHours -= Character.getNumericValue(c);
                questions--;
            }
        }
        if (workHours < 0) {
            return null;
        }
        DFS(workHours, dayHours, pattern.toCharArray(), 0, res, questions);
        return res;
    }

    private static void DFS(int workHours, int dayHours, char[] pattern, int charAt, List<String> res, int questions) {
        if (charAt == 7) {
            if (workHours == 0) {
                res.add(String.valueOf(pattern));
            }
        } else if (questions * dayHours >= workHours) {
            if (pattern[charAt] == '?') {
                for (int i = 0; i <= dayHours; i++) {
                    char[] newPattern = Arrays.copyOf(pattern, pattern.length);
                    newPattern[charAt] = (char) (i + '0');
                    DFS(workHours - i, dayHours, newPattern, charAt + 1, res, questions - 1);
                }
            } else {
                DFS(workHours, dayHours, pattern, charAt + 1, res, questions);
            }
        }
    }

    public static List<String> findSchedule(int workHours, int dayHours, String pattern) {
        char[] pat = pattern.toCharArray();
        int target = workHours;
        int count = 0;
        List<Integer> pos = new ArrayList<>();
        int id = 0;
        for (char one : pat) {
            if (one != '?')
                target -= Character.getNumericValue(one);
            else {
                count++;
                pos.add(id);
            }
            id++;
        }
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum(ans, new ArrayList<Integer>(), count, target, dayHours);
        List<String> res = new ArrayList<>();
        for (List<Integer> one : ans) {
            StringBuilder sb = new StringBuilder(pattern);
            int j = 0;
            for (Integer i : one) {
                char c = (char) (i + '0');
                sb.setCharAt(pos.get(j), c);
                j++;
            }
            res.add(sb.toString());
        }
        return res;
    }

    private static void combinationSum(List<List<Integer>> ans, List<Integer> one, int count, int target, int limit) {
        if (count == 0 && target == 0) {
            ans.add(new ArrayList<>(one));
            return;
        } else if (count == 0)
            return;
        for (int i = 0; i <= limit; i++) {
            one.add(i);
            combinationSum(ans, one, count - 1, target - i, limit);
            one.remove(one.size() - 1);
        }
    }

    public static void main(String[] args) {
        String patter = "88?8?8?";
        System.out.println("-----------------------------------------------------");
        for (String one : findSchedules(54, 9, patter)) {
            System.out.print(one);
            int sum = 0;
            for (char ch : one.toCharArray()) {
                sum += Character.getNumericValue(ch);
            }
            System.out.println("         " + sum);
        }
        System.out.println("-----------------------------------------------------");
        System.out.println(findSchedules(3, 2, "??2??00"));
		System.out.println(findSchedules(56, 8, "??8????"));
		System.out.println(findSchedules(24, 4, "08??840"));
        System.out.println(findSchedules(13, 2, "??2??00"));
        
        System.out.println("");
        System.out.println(findSchedule(3, 2, "??2??00"));
    }
	
}
