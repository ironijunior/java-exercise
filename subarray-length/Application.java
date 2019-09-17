/*
For an array of integers and a number x, find the minimal length subarray with sum greater than the given value.

Examples:
	array = {1, 4, 45, 6, 0, 19}
	x  =  51
	Output: 3
	Minimum length subarray is {4, 45, 6}

	array = {1, 10, 5, 2, 7}
	x  = 9
	Output: 1
	Minimum length subarray is {10}
*/
public class Application {
    public static void main(String[] args) {
        Integer x = 9;
        List<Integer> arr = Arrays.asList(1, 10, 5, 2, 7);

        Integer len = 0;
        for (Integer i = 0; i < arr.size(); i++) {
            Integer s = 0;
            for (Integer j = i; j < arr.size(); j++) {
                s = s + arr.get(j);
                if (s > x
                        && (len > (j - i + 1) || len == 0)) {
                    len = (j - i) + 1;
                }
            }
        }
        System.out.println("len: " + len);
    }
}