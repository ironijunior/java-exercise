public class Exercise {
	public static void main(String[] args) {
		Integer[] arr = new Integer[] {7,8,4,5,5,8,7,8,8,4};
		
		System.out.println(hashSetSolution(arr));		
		System.out.println(xorSolution(arr));
	}
	
	private static Set<Integer> hashSetSolution(Integer[] arr) {
		Set<Integer> ans = new HashSet<>();
		for(Integer a: arr) {
			int v = 1;
			if(ans.contains(a)) {
				ans.remove(a);
			} else {
				ans.add(a);
			}
		}
		return ans;
	}
	
	private static Integer xorSolution(Integer[] arr) {
		Integer xor = 0;
		for(Integer n: arr) {
			xor = xor^n;
		}
		return xor;
	}
}