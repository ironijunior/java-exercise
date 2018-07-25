public class Exercise {
	public static void main(String[] args) {
		List<Integer> input = Arrays.asList(1,2,3,4);
		Integer[] outp = new Integer[input.size()];
		for(int i = 0; i < input.size(); i++) {
			outp[i] = multi(i, input);
		}
		System.out.println(Arrays.asList(outp));
	}
	
	private static Integer multi(Integer ignore, List<Integer> input) {
		Integer res = 1;
		for(int j = 0; j < input.size(); j++) {
			if(j != ignore) {
				res = res * input.get(j);
			}
		}
		return res;
	}
}