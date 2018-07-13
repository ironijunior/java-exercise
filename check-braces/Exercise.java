public class Exercise {

	public static void main(String[] args) {
		String braces [] = {"{}[]()"};
		System.out.println(braces(braces));
	}
	
	private static String[] braces(String[] values) {
        String ret [] = new String[values.length];
        
        for(int i = 0; i < values.length; i++) {
            ret[i] = isValidBraces(values[i]) ? "YES" : "NO";
        }
        
        return ret;
    }

    private static final int SIZE = 2;

    private static boolean isValidBraces(final String value) {
        String braces = value;
        
        while (braces.contains("()") || braces.contains("[]") || braces.contains("{}")) {
            if (braces.contains("()")) {
                braces = braces.substring(0, braces.indexOf("()"))
                        + braces.substring(braces.indexOf("()") + SIZE, braces.length());
            }
            if (braces.contains("{}")) {
                braces = braces.substring(0, braces.indexOf("{}"))
                        + braces.substring(braces.indexOf("{}") + SIZE, braces.length());
            }
            if (braces.contains("[]")) {
                braces = braces.substring(0, braces.indexOf("[]"))
                        + braces.substring(braces.indexOf("[]") + SIZE, braces.length());
            }
        }

        return braces.isEmpty();
    }

}