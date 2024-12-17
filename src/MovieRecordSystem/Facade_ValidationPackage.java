package MovieRecordSystem;



public class Facade_ValidationPackage {
	
	Facade_Validation CheckingGenre;
	Facade_Validation CheckingId;
	
	public Facade_ValidationPackage(String genre,int m_id) {
		CheckingGenre = new CheckingGenre(genre);
		CheckingId = new CheckingID(m_id);
	}
	
	//	compose method for both validations - genre,movie_id
	public boolean valid(MovieOperations movies) {
		System.out.println("\n>Checking Package...\n");

		if(CheckingGenre.checkValues(movies) && CheckingId.checkValues(movies)) {
			return true;
		}
		
		//default return		
		try {
			movies.insertMovie();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	
	}
}
