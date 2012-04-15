import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Movies {

	public static void main(String[] args) {
		HashMap<String, ArrayList<Integer>> index = new HashMap<String, ArrayList<Integer>>();
		ArrayList<String> movies = new ArrayList<String>();
		BufferedReader movieList = null, input = null;
		try {
			movieList = new BufferedReader(new FileReader(new File(
					"/Volumes/Extended/workspace/movies.list")));
		} catch (FileNotFoundException e) {
			System.out.println("Can't open movie file");
		}

		String line;
		String[] words;
		ArrayList<Integer> poslist;

		int k = 0;
		try {
			while ((line = movieList.readLine()) != null) {
				if (line.matches("==========="))
					break;
			}
			while ((line = movieList.readLine()) != null) {
				line = line.replaceAll("\\s+", " ");
				movies.add(line);
				words = line.split(" ");
				for (String w : words) {
					if (w.matches("\\([0-9][0-9][0-9][0-9]\\)"))
						break;
					w = w.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
					if (!w.trim().equals("")) {
						if ((poslist = index.get(w)) == null) {
							index.put(w, new ArrayList<Integer>());
							poslist = index.get(w);
						}
						poslist.add(k);
					}
				}
				k++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scanner scan = new Scanner(System.in);
		String file;

		while (true) {
			System.out.println("Filename or (q)uit: ");
			file = scan.nextLine();
			if (file.equals("q")) {
				break;
			}

			try {
				input = new BufferedReader(new FileReader(new File(file)));

				while ((line = input.readLine()) != null) {
					line = line.toLowerCase();
					if (index.get(line) != null) {
						ArrayList<Integer> locs = index.get(line);
						for (int l : locs) {
							System.out.println("\t" + movies.get(l));

						}
					} else {
						System.out.println(line + " not found!");
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
