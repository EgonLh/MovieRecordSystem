package MovieRecordSystem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class CheckingGenre implements Facade_Validation {
	
	//	Genre Input
	String inp;

	//	Storing Internal State in constructor
	public CheckingGenre(String inp) {
		super();
		this.inp = inp;
	}

	//	Checking Values From Genre Input - Format Check;
	public boolean checkValues(MovieOperations movieOperations) {
		
		Matcher matcher;
		Pattern pattern = null;
		String regex = "^([a-zA-Z]*(\\|[a-zA-Z]*)?){3,}$";

		   try {
	            // Compile the regex pattern
	            pattern = Pattern.compile(regex);
	        } catch (PatternSyntaxException e) {
	            // Handle regex compilation errors
	            System.out.println("Regex pattern compilation failed: " + e.getDescription());
	           
	        }
		matcher = pattern.matcher(inp.trim());
		if (matcher.matches()) {
			System.out.println(">Genre Validataion Success.....");
			return true;
		}
		
		
		System.out.println("\n>Invalid Genre , Try Again.\n");
		return false;
	}

}
