import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class FileParser {
	private final Map<String, TreeSet<Integer>> index;
	private Path file;

	/**
	 * Takes file, reads it line by line cleans line, creates a map with string
	 * word key and treeSet Locations
	 * 
	 */
	FileParser(Path file) {
		this.index = new HashMap<String, TreeSet<Integer>>();
		this.file = file;
		readFile();
	}

	/** Regular expression for removing special characters. */
	public static final String CLEAN_REGEX = "(?U)[^\\p{Alnum}\\p{Space}]+";

	/** Regular expression for splitting text into words by whitespace. */
	public static final String SPLIT_REGEX = "(?U)\\p{Space}+";

	public static String clean(String text) {
		String newText = text.trim().toLowerCase().replaceAll(CLEAN_REGEX, "");
		return newText;

	}

	public static String[] split(String text) {
		if (!clean(text).equals("")) {
			String cleaned = clean(text);
			String[] splitString = cleaned.split(SPLIT_REGEX);

			return splitString;
		} else {
			return new String[] {};
		}
	}

	public boolean add(String word, int position) {

		String cleanWord = clean(word);

		if (index.containsKey(cleanWord)) {
			if (index.get(cleanWord).add(position)) {
				return true;
			}

		} else {
			TreeSet<Integer> firstPos = new TreeSet<Integer>();
			if (!cleanWord.equals("")) {

				index.put(cleanWord, firstPos);
				if (index.get(cleanWord).add(position)) {
					return true;
				}

			}

		}
		return false;

	}

	public void addAll(String[] words, int position) {

		for (int i = 0; i < words.length; i++) {
			if (!words[i].isEmpty() && !words[i].equals("")) {
				if (add(words[i], position + i)) {

				}

			}

		}

	}

	public boolean readFile() {
		try (BufferedReader br = Files.newBufferedReader(file)) {

			String line;
			int position = 1;
			while ((line = br.readLine()) != null) {
				// System.out.println("line" + lineNumber);
				if (split(line).length != 0) {
					String[] wordLine = split(line);

					addAll(wordLine, position);
					position += wordLine.length;

				}
			}
			// System.out.println(words());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;

		}
	}

	public Map<String, TreeSet<Integer>> getMap() {
		if (!index.isEmpty()) {
			return index;
		}
		return null;
	}

	public int words() {
		return index.size();
	}

	@Override
	public String toString() {
		return index.toString();
	}

}
