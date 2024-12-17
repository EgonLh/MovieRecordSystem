package MovieRecordSystem;

public class Movie {
	private int Movie_id;
	private int User_id;
	private String Movie_Name;
	private Double Rating;
	private String Genre;
	

	public Movie(int movie_id, int user_id, String movie_Name, double rating, String genre) {
		this.Movie_id = movie_id;
		this.User_id = user_id;
		this.Movie_Name = movie_Name;
		this.Rating = rating;
		this.Genre = genre;
	}
	

	//	Getter And Setter For Movie_id
	public int getMovie_id() {
		return Movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.Movie_id = movie_id;
	}

	//	Getter And Setter For User_id
	public int getUser_id() {
		return User_id;
	}

	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	
	//  Getter And Setter For Movie_name
	public String getMovie_Name() {
		return Movie_Name;
	}

	public void setMovie_Name(String movie_Name) {
		Movie_Name = movie_Name;
	}

	//	Getter And Setter For Rating
	public Double getRating() {
		return Rating;
	}

	public void setRating(double rating) {
		Rating = rating;
	}

	//	Getter And Setter For Genre
	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}
		
}
