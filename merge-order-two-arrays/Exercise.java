public class Exercise {
	
	public static void main(String[] args) {
		int a [] = {3,5,7,9,12,14, 15};
	    int b [] = {6 ,7, 10};
		    
	    int[] answer = new int[a.length + b.length];
	    int i = a.length - 1, j = b.length - 1, k = answer.length;
	
	   	while (k > 0) {
	   		if(j < 0 || (i >= 0 && a[i] >= b[j])) {
	   			answer[--k] = a[i--];
	        } else {
	        	answer[--k] = b[j--];
	        }
	    }
		    
	    System.out.println(answer);
	}
}
