import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Actors {

	public static void main(String[] args) {
		HashMap<String, String> index = new HashMap<String, String>();

		BufferedReader actors = null, actresses = null, input = null;
		try {
			actors = new BufferedReader(new FileReader(new File(
					"/Volumes/Extended/workspace/actors.list")));
			actresses = new BufferedReader(new FileReader(new File(
					"/Volumes/Extended/workspace/actresses.list")));
		} catch (FileNotFoundException e) {
			System.out.println("Can't open file");
		}

		Pattern p = Pattern.compile("^([^,]*?,[^\\t]*?\\t)(.+)");
		Matcher m = null;
		
		String line, currActor = "";
		int k = 1;
		StringBuffer currMovies = new StringBuffer();
		try {
			while ((line = actors.readLine()) != null) {
				m = p.matcher(line);
				if (m.find())
					break;
			}

			currActor = m.group(1).replaceAll("\\s", " ").trim();
			currMovies.append(m.group(2).replaceAll("\\t", "")
					.replaceAll("\\s", " ").trim()
					+ "%");

			k++;

			while ((line = actors.readLine()) != null) {
				m = p.matcher(line);

				if (m.find()) {

					index.put(currActor, new String(currMovies));
					currMovies = new StringBuffer();
					currActor = m.group(1).replaceAll("\\s", " ").trim();
					currMovies.append(m.group(2).replaceAll("\\t", "").replaceAll("\\s", " ").trim()
							+ "%");
				} else {
					currMovies.append(line.replaceAll("\\t", "").replaceAll("\\s", " ").trim() + "%");
				}
				k++;
			}
			while ((line = actresses.readLine()) != null) {
				m = p.matcher(line);

				if (m.find())
					break;
			}
			currActor = m.group(1).replaceAll("\\s", " ").trim();
			currMovies.append(m.group(2).replaceAll("\\t", "")
					.replaceAll("\\s", " ").trim()
					+ "%");

			k++;

			while ((line = actresses.readLine()) != null) {
				m = p.matcher(line);

				if (m.find()) {

					index.put(currActor, new String(currMovies));
					currMovies = new StringBuffer();
					currActor = m.group(1).replaceAll("\\s", " ").trim();
					currMovies.append(m.group(2).replaceAll("\\t", "").replaceAll("\\s", " ").trim()
							+ "%");
				} else {
					currMovies.append(line.replaceAll("\\t", "").replaceAll("\\s", " ").trim() + "%");
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
					System.out.println(line);
					if (index.get(line) != null) {
						String locs = index.get(line);
						System.out.println(locs.replaceAll("%", "\n\t"));
					} else {
						System.out.println(line + " not found");
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("File not found!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
