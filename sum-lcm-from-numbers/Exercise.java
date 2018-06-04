public class Exercise {
	
	public static void main(String[] args) {
		int[] k = {10,4,2,12,4};
		long[] result = new long[k.length];
		
		for(int i = 0; i < k.length; i++) {
			result[i] = getSumOfLeastCommonMultiple(k[i]);
		}
		System.out.println(result);
	}
	
	private static long getSumOfLeastCommonMultiple(int input) {
		long result = 0;
		int sum = 0;
		for(int i = 0; i <= input; i++){
			int gcd = 1;
			for(int j = 1; j <= input && j <= i; ++j)
	        {
	            // Checks if i is factor of both integers
	            if(input % j == 0 && i % j == 0)
	                gcd = j;
	        }

	        int lcm = (input * i) / gcd;
			if(lcm == input) {
				sum = sum + i;
			}
		}
		System.out.println("SUM: "+sum);
		return sum;
	}
}
