import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercise {

	private static long solucao2_getSumOfLeastCommonMultiple(int input) {
		long result = 0;
		int sum = 0;

		for (int i = 0; i <= input; i++) {
			int gcd = 1;
			for (int j = 1; j <= input && j <= i; ++j) {
				// Checks if i is factor of both integers
				if (input % j == 0 && i % j == 0)
					gcd = j;
			}

			int lcm = (input * i) / gcd;
			if (lcm == input) {
				sum = sum + i;
			}
		}
		System.out.println("SUM: " + sum);
		return sum;
	}

	public static void solucao1_lcmSum(int array[]) {

		for (int j = 0; j < array.length; j++) {
			int lcmSum = 0;
			for (int i = 1; i <= array[j]; i++) {

				if (array[j] % i == 0)
					lcmSum = lcmSum + i;

			}
			System.out.println("lcmSum : " + lcmSum);
		}

	}

	private static int[] solucao3_getMaximumSumWithLCM(int[] numbers) {
		int[] ret = new int[numbers.length];

		for (int ii = 0; ii < numbers.length; ii++) {
			int number = numbers[ii];
			int sum = 0;
			int sqrt = (int) Math.sqrt(number);

			for (int i = 1; i <= sqrt; i++) {

				if (number % i == 0) {

					if (i == (number / i))
						sum += i;
					else
						sum += (i + number / i);
				}
			}
			ret[ii] = sum;
		}

		return ret;
	}
	
	private static long[] solucao7_maxSubsetSum2(int[] k) {
        int l = k.length;
        long[] sums = new long[l];
        for (int i=0;i<l;i++) {
            sums[i]=computeSubsetSum(k[i]);
        }
        return sums;
    }

    private static long computeSubsetSum(int n) {
        if (n==1) return 1;
        long sum = 1+n;
        for (int i=2;i<=Math.sqrt(n);i++) {
            if (n%i==0) sum += (i==n/i?i:(i+n/i));
        }
         return sum;
    }	
	
	private static long[] solucao6_maxSubsetSum(int[] lcm) {

		long subSetSum[] = new long[lcm.length];
		for (int i = 0; i < lcm.length; i++) {
			for (int j = 1; j <= lcm[i] / j; j++) {
				if (lcm[i] % j == 0 && lcm[i] % (lcm[i] / j) == 0) {
					if (j == lcm[i] / j) {
						subSetSum[i] = subSetSum[i] + j;
					} else {
						subSetSum[i] = subSetSum[i] + lcm[i] / j + j;
					}
				}
			}

		}

		return subSetSum;
	}

	public static int[] solucao4_getSubsetSum(int[] array) {
		int[] ret = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			int val = getSubsetSumWithLCM(array[i]);
			ret[i] = val;
		}
		return ret;
	}

	public static int getSubsetSumWithLCM(int num) {
		int max_sum = 0; // Initialize result
		// Finding a divisor of n and adding
		// it to max_sum
		for (int i = 1; i * i <= num; i++) {
			if (num % i == 0) {
				max_sum += i;
				if (num / i != i)
					max_sum += (num / i);
			}
		}
		return max_sum;
	}

	private static int[] solucao5_findLargestSubsetSum(int[] kArray) {
		int[] result = new int[kArray.length];
		for (int i = 0; i < kArray.length; i++) {
			int k = kArray[i];
			int sumOfLcmOfK = k;
			for (int j = 1; j <= k / 2; j++) {
				if (k % j == 0) {
					sumOfLcmOfK += j;
				}
			}
			result[i] = sumOfLcmOfK;
		}
		return result;
	}

	private static void printArray(int[] array) {
		for (int i : array) {
			System.out.println(i);
		}
	}
	
	private static void solucao8_findLcm(int[] arr) {
		List<Integer> result = new ArrayList<>();
		
		for (int element : arr) {
			int flag = 0;
			for(int i = 1; i <= element; i++){
				if(element % i == 0)
					flag += i;
			}
			result.add(flag);
		}
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		int array[] = { 10, 4, 2, 12, 4 };

		System.out.println(System.currentTimeMillis());
		solucao1_lcmSum(array);
		System.out.println(System.currentTimeMillis());

		
		System.out.println(System.currentTimeMillis());
		long[] result = new long[array.length];

		for (int i = 0; i < array.length; i++) {
			result[i] = solucao2_getSumOfLeastCommonMultiple(array[i]);
		}
		System.out.println(result);
		System.out.println(System.currentTimeMillis());

		
		System.out.println(System.currentTimeMillis());
		int[] res = (solucao3_getMaximumSumWithLCM(array));
		for (int i = 0; i < res.length; i++) {
			System.out.println(res[i]);
		}
		System.out.println(System.currentTimeMillis());
		

		System.out.println(System.currentTimeMillis());
		System.out.println(Arrays.toString(solucao4_getSubsetSum(array)));
		System.out.println(System.currentTimeMillis());

		
		System.out.println(System.currentTimeMillis());
		int[] largestSubsetSum = solucao5_findLargestSubsetSum(array);
		printArray(largestSubsetSum);
		System.out.println(System.currentTimeMillis());

		
		System.out.println(System.currentTimeMillis());
		long maxSum[] = solucao6_maxSubsetSum(array);
		for (long sum : maxSum) {
			System.out.println(sum);
		}
		System.out.println(System.currentTimeMillis());
		
		
		System.out.println(System.currentTimeMillis());
		long[] arr = solucao7_maxSubsetSum2(array);
		for(long a: arr) {
			System.out.println(a);
		}
		System.out.println(System.currentTimeMillis());
		
		
		System.out.println(System.currentTimeMillis());
		solucao8_findLcm(array);
		System.out.println(System.currentTimeMillis());
		
		
		System.out.println(System.currentTimeMillis());
		
		System.out.println(System.currentTimeMillis());
		
	}
	
	
}
