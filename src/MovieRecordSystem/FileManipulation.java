package MovieRecordSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileManipulation {

	// ----- Writing Files ----- //
	public static void FileWriteData(ArrayList<Movie> movies, String fname, boolean append) throws IOException {

		FileWriter fw = new FileWriter(fname, append);
		PrintWriter pw = new PrintWriter(fw);

		for (Movie m : movies) {
			pw.write(m.getMovie_id() + "," + m.getUser_id() + "," + m.getMovie_Name() + "," + m.getRating() + ","
					+ m.getGenre() + "\n");
		}
		pw.close();
		fw.close();
	}

	// ---------- Reading Data From CSV ---------- //
	public static ArrayList<Movie> FileReadData(String fname) throws IOException {

		ArrayList<Movie> movies = new ArrayList<Movie>();
		FileReader fr = new FileReader(fname);
		BufferedReader bf = new BufferedReader(fr);

		String strline = bf.readLine();
		// > Skipping The Column Header
		String checkValue = strline.split(",")[0];
		if(checkValue.isEmpty() && !checkValue.equals("0")) {
			strline = bf.readLine();
		}
		while (strline != null) {
			// > Spiting with custom Regax Expression
			String[] st = strline.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
			int MovidId = Integer.parseInt(st[0]);
			int UserId = Integer.parseInt(st[1]);
			String MovieName = st[2];
			double Rating = Double.parseDouble(st[3]);
			String genre = st[4];

			movies.add(new Movie(MovidId, UserId, MovieName, Rating, genre));
			strline = bf.readLine();

		}

		bf.close();
		fr.close();
		return movies;
	}
	
	// ----- Swapping Old And New Files -----//
	public static void RenameFile(String oldfile, String newfile) {
		
		File oldf = new File(oldfile);
		File tempf = new File(oldfile);
		File newf = new File(newfile);

		oldf.delete();

		newf.renameTo(tempf);
	}
}
