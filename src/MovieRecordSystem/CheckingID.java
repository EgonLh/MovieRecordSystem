package MovieRecordSystem;

import java.io.IOException;
import java.util.ArrayList;

public class CheckingID implements Facade_Validation {
	
	//	MovieID Input
	int m_id;

	//	Storing Internal State in constructor
	public CheckingID(int inp) {
		super();
		this.m_id = inp;
	}

	//	Checking Movies ID From Files - Duplicated Check;
	@Override
	public boolean checkValues(MovieOperations movieOperations) {
		ArrayList<Movie> movies = null;
		try {
			movies = FileManipulation.FileReadData(
					"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Files Not Found :" + e);
		}

		for (Movie m : movies) {
			if (m.getMovie_id() == m_id) {
				System.out.println("\n>Duplicated Movied ID\n>Try Again.....\n");
				return false;
			}

		}
		System.out.println(">ID Validataion Success.....");
		return true;
	}

}
