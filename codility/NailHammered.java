package codility;

public class NailHammered {

    public static void main(String[] args) {
        int[] A = {1, 1, 3, 3, 3, 4, 5, 5, 5};
        System.out.println(solutionPosted(A, 2));
        System.out.println(solutionPosted(new int[]{1, 1, 3, 3, 3, 4, 5, 5, 5, 5}, 2));
        System.out.println(solutionPosted(new int[]{1}, 3));
        System.out.println(solutionPosted(new int[]{1}, 1));
        System.out.println(solutionPosted(new int[]{1, 3}, 2));
        System.out.println(solutionPosted(new int[]{1, 2, 3, 4}, 2));

    }

    public static int solutionBest(int[] A, int K) {
        int aLength = A.length;
        if (K >= A.length) {
            return A.length; // if K is greater than array size so return array size
        }

        int biggestRepeat = 1; //starts with 1 because always repeat at least once
        int repeatCounter = 1; //starts with 1 because always repeat at least once
        for (int i = 0 ; i < aLength - K - 1; i++) { //ignore the last K numbers from array
            if (A[i] == A[i+1]) { //is repeated with next number
                repeatCounter++;
            } else {
                repeatCounter = 1; // reset repeater
            }
            if (repeatCounter > biggestRepeat) {
                biggestRepeat = repeatCounter;
            }
        }
        return biggestRepeat + K; // sum the biggest repetition with the K
    }

    public static int solutionPosted(int[] A, int N) {
        int[] res = new int[A[A.length - 1]+1];    //new array with (lastNumber + 1) positions
        int len = A.length -1;
        while(len >= 0 && A[A.length - 1] == A[len]) {
            len--;
        }                                          //set len as the lastIndex before the last number
        res[A[A.length-1]] = A.length - 1 - len;   //quantidade de numeros iguais nas ultimas posições do array
        int cur = 0;
        for (int i = len; i>= 0; i--) {
            if (A[i+1] != A[i] && A[i+1] - A[i] <= N) {
                if (i+1 != A.length - 1) {
                    res[A[i+1]] = cur + res[A[i+1]];
                }
                res[A[i]] = Math.min(N, res[A[i+1]]);
                cur = 1;
            } else if (A[i] == A[i+1]) {
                cur++;
            } else if (A[i+1] != A[i] && A[i+1] - A[i] > N) {
                res[A[i]] = 0;
                cur = 1;
            }
        }

        int greatest = 0;
        for (int i = 0; i < A[A.length-1]+1; i++) {
            greatest = Math.max(res[i], greatest);
        }
        return greatest;
    }

}