package MovieRecordSystem;

import java.io.IOException;
import java.util.Scanner;

public class Utilities {

	Scanner sc = new Scanner(System.in);

	// ----- Repeating Movies Controls ----- //
	public boolean repeat() throws IOException {
		System.out.println("\ntype :y          :To Go Back\r\n" + "type :any        :To Exit\n");
		System.out.print(">>");
		char opt = sc.next().charAt(0);
		if (Character.toUpperCase(opt) == 'Y') {
			return true;
		} else
			return false;
	}

	// ----- Existing System ----- //
	public void Exit() {
		System.out.println("\n\n\t!----------* Thank You! *----------!\t");
		System.exit(0);
	}

}
