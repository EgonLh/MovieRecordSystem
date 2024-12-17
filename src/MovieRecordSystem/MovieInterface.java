package MovieRecordSystem;

import java.io.IOException;

public interface MovieInterface {
	void insertMovie() throws Exception;

	void updateMovie() throws IOException;

	void deleteMovie() throws IOException;

	void viewMovies() throws IOException, Exception;

	void searchMovies() throws IOException;

	void sortMovies() throws IOException;

	void moviesCountsByUId() throws IOException;
	
	void listofMoviesByGenre() throws IOException;
	
	void TotalViewsOfMovie() throws IOException;
	

}
