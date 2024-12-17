package MovieRecordSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MovieOperations implements MovieInterface {

	// ----- Singleton Pattern Implemented ----- //
	private static MovieOperations instance = new MovieOperations();

	private MovieOperations() {
	}

	public static MovieOperations getInstance() {
		return instance;
	}

	// ----- Utilities ----- //
	static Scanner sc = new Scanner(System.in);
	static Utilities util = new Utilities();

	// ----- Movie Control Panel ----- //
	public void movieControl() throws Exception {

		System.out.println("\tWelcome to Movies Record System");
		System.out.println("\t===============================\n");
		System.out.println("Choose An Number to Perfom Operation :\n");
		System.out.println("> To View Movies  ?  : 1 ");
		System.out.println("> To Add Movie    ?  : 2 ");
		System.out.println("> To Update Movie ?  : 3 ");
		System.out.println("> To Delete Movie ?  : 4");
		System.out.println("> To Search Movie ?  : 5");
		System.out.println("> To Sort Movie By Rates ?    : 6");
		System.out.println("> To Count Movie By Id   ?    : 7");
		System.out.println("> To View Movie By Genre ?    : 8");
		System.out.println("> To Count Views Of Movie?    : 9");
		System.out.print("\nEnter Here? >");

		int opt = 0;
		try {
			opt = sc.nextInt();
		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block
			System.out.println("The Input Must Be Integer (1 from 8) :" + e.getMessage());
			// clearing the input 
			sc.nextLine();
		}

		switch (opt) {
		case 1:
			System.out.println("\n>Loading.......\n");
			viewMovies();
			break;
		case 2:
			System.out.println("\n>Loading.......\n");
			insertMovie();
			break;
		case 3:
			System.out.println("\n>Loading.......\n");
			updateMovie();
			break;
		case 4:
			System.out.println("\n>Loading.......\n");
			deleteMovie();
			break;
		case 5:
			System.out.println("\n>Loading........\n");
			searchMovies();
			break;
		case 6:
			System.out.println("\n>Loading........\n");
			sortMovies();
			break;
		case 7:
			System.out.println("\n>Loading........\n");
			moviesCountsByUId();
			break;
		case 8:
			System.out.println("\n>Loading........\n");
			listofMoviesByGenre();
			break;
		case 9:
			System.out.println("\n>Loading........\n");
			TotalViewsOfMovie();
			break;
		default:
			System.out.println("\nInvalid Input......,\n>Try Again......");
			break;
		}

		// > Repeating The Process
		boolean b = util.repeat();
		if (b) {
			movieControl();
		} else {
			util.Exit();
		}

	}

	/*
	 * > Operations 	>Rating value’s range is within 0 to 5 > Need to add validation
	 * > With try-catch 	>Need to check duplicated movieId
	 */
	
	
	// ----- Views Movies ----- //
	@Override
	public void viewMovies() throws Exception {

		ArrayList<Movie> movies = null;
		try {
			movies = FileManipulation.FileReadData(
					"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Files Can't Find :"+e.getMessage());
			movieControl();
		}
		
		String format = "%-10s%-10s%-60s%-10s%-20s%n";
		System.out.printf(format, "\nMovies", "User_ID", "Movie_Name", "Rating", "Genre\n");
		
		for (Movie movie : movies) {

			System.out.printf(format, movie.getMovie_id(), movie.getUser_id(), movie.getMovie_Name(), movie.getRating(),
					movie.getGenre());
		}

	}

	// ----- Insert Movies ----- //
	@Override
	public void insertMovie() throws Exception {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		System.out.print("> Enter Movie ID     :");
		int m_id = sc.nextInt();
		System.out.print("> Enter User ID      :");
		int u_id = sc.nextInt();
		sc.nextLine();
		System.out.print("> Enter Movie Name   :");
		String m_name = sc.nextLine();
		System.out.print("> Enter Movie Rating :");
		Double m_rating = sc.nextDouble();
		sc.nextLine();
		System.out.print("> Enter Movie Genre  :");
		String genre = sc.nextLine();
		
		//----- Facade Design Pattern ---//
		Facade_ValidationPackage validation = new Facade_ValidationPackage(genre.trim(), m_id);
		if (validation.valid(instance)) {
			movies.add(new Movie(m_id, u_id, m_name, m_rating, genre));
			FileManipulation.FileWriteData(movies,
					"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset.csv",
					true);
		}
		;
	}

	// ----- Update Movies ----- //
	@Override
	public void updateMovie() throws IOException {
		// TODO Auto-generated method stub
		boolean found = false;
		sc.nextLine();
		System.out.print("Enter Movie Name :");
		String movie_name = sc.nextLine();

		System.out.print("Enter User ID :");
		int u_id = sc.nextInt();
		System.out.print("Enter Update Movie Rating:");
		Double rating = sc.nextDouble();
		ArrayList<Movie> movies = FileManipulation.FileReadData(
				"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset.csv");

		for (Movie m : movies) {
			if ((m.getMovie_Name()).equalsIgnoreCase(movie_name) && m.getUser_id() == u_id) {
				System.out.println("\n>Movie Data :" + m.getMovie_Name());
				m.setRating(rating);
				System.out.println(">Update Successful....\n");
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("There is no movie with this id and user id");
		} else {

			FileManipulation.FileWriteData(movies,
					"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset1.csv",
					true);

			FileManipulation.RenameFile(
					"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset.csv",
					"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset1.csv");
		}
	}

	// ----- Delete Movies ----- //
	@Override
	public void deleteMovie() throws IOException {
		boolean flag = false;
		sc.nextLine();
		System.out.print("Enter Movie Name :");
		String movie_name = sc.nextLine();
		System.out.print("Enter User ID :");
		int u_id = sc.nextInt();
		ArrayList<Movie> movies = FileManipulation.FileReadData(
				"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset.csv");
		
		for (Movie m : movies) {
			if ((m.getMovie_Name()).equalsIgnoreCase(movie_name) && m.getUser_id() == u_id) {
				System.out.println("\n>Movie Data :" + m.getMovie_Name());
				movies.remove(m);
				System.out.println(">Removed Successful.....");
				flag = true;
				break;
			}
		}
		if (!flag) {
			System.out.println("There is no movie with this id and user id");
		} else {

			FileManipulation.FileWriteData(movies,
					"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset1.csv",
					true);

			FileManipulation.RenameFile(
					"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset.csv",
					"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset1.csv");
		}

	}

	// ----- Search Movies ----- //
	@Override
	public void searchMovies() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Choose A Seaching Options :");
		System.out.println(">By Movie Name ?    : m");
		System.out.println(">By User ID    ?    : i\n");
		
		System.out.print(">>");
		char c = sc.next().charAt(0);
		
		switch (c) {
		case 'm':
			searchMovieName();
			break;
		case 'i':
			searchMovieByUID();

			break;
		default:
			System.out.println("\n>Invalid Input!\n");
			searchMovies();
			break;
		}
	}

	// ----- Search Movies By Name ----- //
	private void searchMovieName() throws IOException {
		ArrayList<Movie> movies = FileManipulation.FileReadData(
				"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset.csv");
		
		boolean flag = false;
		sc.nextLine();
		System.out.print(">Enter Movie Name :");
		String mName = sc.nextLine();
		
		System.out.println(mName);
		
		for (Movie m : movies) {
			if (m.getMovie_Name().equalsIgnoreCase(mName)) {
				System.out.println("\n>Searching.........\n");
				String format = "%-10s%-10s%-60s%-10s%-20s%n";
				System.out.printf(format, "\nMovies", "User_ID", "Movie_Name", "Rating", "Genre\n");

				System.out.printf(format, m.getMovie_id(), m.getUser_id(), m.getMovie_Name(), m.getRating(),
						m.getGenre());

				flag = true;
				break;
			}
		}
		if (!flag) {
			System.out.println("\n>Don't Have movies with this Name\n");
			searchMovieName();
		}

	}
	
	// ----- Search Movies By U ID ----- //
	private void searchMovieByUID() throws IOException {
		ArrayList<Movie> movies = FileManipulation.FileReadData(
				"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset.csv");
		boolean flag = false;
		System.out.print(">Enter User ID  :");
		int u_id = sc.nextInt();
		System.out.println("\n>Searching.........\n");
		String format = "%-10s%-10s%-60s%-10s%-20s%n";
		System.out.printf(format, "\nMovies", "User_ID", "Movie_Name", "Rating", "Genre\n");
		for (Movie m : movies) {

			if (m.getUser_id() == u_id) {

				System.out.printf(format, m.getMovie_id(), m.getUser_id(), m.getMovie_Name(), m.getRating(),
						m.getGenre());

				flag = true;
//				break;
			}
		}
		if (!flag) {
			System.out.println("\n>Don't Have movies with this ID\n");
			searchMovieByUID();
		}

	}

	// ----- Sort Movies ----- //
	@Override
	public void sortMovies() throws IOException {
		System.out.println("Sorted By Rating");
		ArrayList<Movie> movies = FileManipulation.FileReadData(
				"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset.csv");
		// TODO Auto-generated method stub
		Collections.sort(movies, new Comparator<Movie>() {
			@Override
			public int compare(Movie m1, Movie m2) {
				return Double.compare(m2.getRating(), m1.getRating());
			}
		});
		String format = "%-10s%-10s%-60s%-10s%-20s%n";
		System.out.printf(format, "\nMovies", "User_ID", "Movie_Name", "Rating", "Genre\n");
		for (Movie movie : movies) {

			System.out.printf(format, movie.getMovie_id(), movie.getUser_id(), movie.getMovie_Name(), movie.getRating(),
					movie.getGenre());
		}

	}

	// ----- Count Movies ----- //
	@Override
	public void moviesCountsByUId() throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Movie> movies = FileManipulation.FileReadData(
				"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset.csv");

		ArrayList<Integer> countedUserIds = new ArrayList<>();

		for (Movie movie : movies) {
			int userId = movie.getUser_id();

			if (!countedUserIds.contains(userId)) {
				int count = 0;

				for (Movie m : movies) {
					if (m.getUser_id() == userId) {
						count++;
					}
				}

				countedUserIds.add(userId);

				// Print the count for this userId
				System.out.println(">User ID: " + userId + " added " + count + " Movie's Rating(s) And Record(s)");
			}
		}
	}

	// ----- List Movies By Genre ----- //
	@Override
	public void listofMoviesByGenre() throws IOException {
		// TODO Auto-generated method stub
		System.out.print("Enter The Desired Movie Gnere:");
		String genre = sc.next();

		System.out.println("Movies With Genre :" + genre);
		ArrayList<Movie> movies = FileManipulation.FileReadData(
				"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset.csv");
		String format = "%-10s%-10s%-60s%-10s%-20s%n";
		System.out.printf(format, "\nMovies", "User_ID", "Movie_Name", "Rating", "Genre\n");
		int count = 0;
		for (Movie movie : movies) {
			String str = movie.getGenre();
			String[] arr = str.split("\\|");
			for (String a : arr) {
				if (genre.equalsIgnoreCase(a)) {

					System.out.printf(format, movie.getMovie_id(), movie.getUser_id(), movie.getMovie_Name(),
							movie.getRating(), movie.getGenre());
					count++;
				}
			}

		}
		if(count == 0) {
			System.out.println("\n>There is no movie with this genre , Try Again....\n");
			util.repeat();
		}else {
			System.out.println("\n>Movies With Genre:"+genre+" :"+count);
		}
	}

	@Override
	public void TotalViewsOfMovie() throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Movie> movies = FileManipulation.FileReadData(
				"C:\\Users\\egonh\\Desktop\\WorkSpace\\IMU\\DSA\\MovieRecordSystem\\src\\MovieRecordSystem\\movies_dataset.csv");
		 HashMap<Integer, Integer> movieCountMap = new HashMap<>();

	        // Count the number of movies viewed by each user
	        for (Movie record : movies) {
	            int userId = record.getUser_id();
	            movieCountMap.put(userId, movieCountMap.getOrDefault(userId, 0) + 1);
	        }

	        // Convert the HashMap to a List of Map entries and sort it by movie count in descending order
	        List<Map.Entry<Integer, Integer>> sortedList = new ArrayList<>(movieCountMap.entrySet());
	        Collections.sort(sortedList, new Comparator<Map.Entry<Integer, Integer>>() {
	            @Override
	            public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
	                return Integer.compare(entry2.getValue(), entry1.getValue());
	            }
	        });

	        // Display the results
	        System.out.println("\nThe Views Rate Based On Users\n");
	        for (Map.Entry<Integer, Integer> entry : sortedList) {
	        
	            System.out.println("> User ID :"+entry.getKey() + " : View Count:" + entry.getValue());
	        }
	}

}
