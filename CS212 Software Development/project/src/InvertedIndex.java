import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class InvertedIndex {

	private final TreeMap<String, TreeMap<String, TreeSet<Integer>>> invertedMap;
	private final Path output;

	InvertedIndex(Path output) {
		this.invertedMap = new TreeMap<String, TreeMap<String, TreeSet<Integer>>>();
		this.output = output;
	}

	InvertedIndex() {
		this.invertedMap = new TreeMap<String, TreeMap<String, TreeSet<Integer>>>();
		this.output = null;
	}

	public boolean containsWord(String word) {
		return invertedMap.containsKey(word);
	}

	public boolean containsFile(String word, String file) {
		return invertedMap.get(word).containsKey(file);
	}

	/**
	 * 
	 * @param map
	 *            : Key is the word, value is positions in specific file
	 * @param file
	 * @return
	 */
	private boolean addMap(Map<String, TreeSet<Integer>> map, Path aFile) {

		String pathString = aFile.toString();
		// System.out.println(pathString);
		if (pathString.toLowerCase().endsWith(".txt") && (map != null)) {
			for (Map.Entry<String, TreeSet<Integer>> entry : map.entrySet()) {
				String word = entry.getKey();
				// if (word.isEmpty()) {
				// System.out.println("EMPTY!" + pathString);
				// }
				if (containsWord(word)) {
					if (containsFile(word, pathString)) {
						invertedMap.get(word).get(pathString).addAll(map.get(word));
					} else {
						invertedMap.get(word).put(pathString, map.get(word));
					}
				} else {
					TreeMap<String, TreeSet<Integer>> fileMap = new TreeMap<String, TreeSet<Integer>>();
					fileMap.put(pathString, entry.getValue());
					invertedMap.put(word, fileMap);
				}
			}
			return true;
		}
		System.out.println("Map was empty or not a 'txt' file");
		return false;

	}

	public boolean convertInverted(Map<String, TreeSet<Integer>> oldMap, Path file) {
		if (addMap(oldMap, file)) {
			return true;

		}
		return false;

	}

	public boolean convertAll(TreeSet<Path> files) {
		if (!files.isEmpty()) {
			FileParser parser;
			for (Path file : files) {
				parser = new FileParser(file);
				if (parser.getMap() != null) {
					convertInverted(parser.getMap(), file);
				}
			}
			return true;
		}
		return false;

	}

	/**
	 * Helper method to indent several times by 2 spaces each time. For example,
	 * indent(0) will return an empty string, indent(1) will return 2 spaces,
	 * and indent(2) will return 4 spaces.
	 * 
	 * @param times
	 * @return
	 * @throws IOException
	 */
	public static String indent(int times) throws IOException {
		return times > 0 ? String.format("%" + (times * 2) + "s", " ") : "";
	}

	public static String quote(String text) {
		return "\"" + text + "\"";
	}

	public static void writeArray(BufferedWriter writer, TreeSet<Integer> elements, int level) throws IOException {
		writer.write("[");
		writer.newLine();

		if (!elements.isEmpty()) {
			Integer first = elements.first().intValue();
			writer.write(indent(level + 1));
			writer.write(first.toString());

			for (Integer entry : elements.tailSet(elements.first(), false)) {
				writer.write(",");
				writer.newLine();
				writer.write(indent(level + 1));
				writer.write(entry.toString());
			}

			writer.newLine();
			writer.write(indent(level));
			writer.write("]");

		} else {
			writer.write(indent(level));
			writer.write("]");
		}
	}

	public boolean writeNestedObject(TreeMap<String, TreeMap<String, TreeSet<Integer>>> wordMap) {
		boolean status = false;
		try (BufferedWriter writer = Files.newBufferedWriter(output)) {
			writer.write("{");
			writer.newLine();
			if (!wordMap.isEmpty()) {
				String firstWord = wordMap.firstKey();
				writer.write(indent(1));
				writer.write(quote(firstWord));
				writer.write(": ");
				writer.write("{");
				writer.newLine();
				writer.write(indent(2));
				String firstFile = wordMap.get(firstWord).firstKey();
				writer.write(quote(firstFile));
				writer.write(": ");
				writeArray(writer, wordMap.get(firstWord).get(firstFile), 2);

				for (Map.Entry<String, TreeSet<Integer>> entry : wordMap.get(firstWord)
						.tailMap(wordMap.get(firstWord).firstKey(), false).entrySet()) {
					writer.write(",");
					writer.newLine();
					writer.write(indent(2));

					writer.write(quote(entry.getKey()));
					writer.write(": ");
					writeArray(writer, entry.getValue(), 2);

				}
				writer.newLine();
				writer.write(indent(1));
				writer.write("}");

				for (Map.Entry<String, TreeMap<String, TreeSet<Integer>>> entry : wordMap
						.tailMap(wordMap.firstKey(), false).entrySet()) {

					writer.write(",");
					writer.newLine();
					writer.write(indent(1));
					writer.write(quote(entry.getKey()));
					writer.write(": ");
					writer.write("{");
					writer.newLine();
					writer.write(indent(2));
					String firstFile1 = entry.getValue().firstKey();
					writer.write(quote(firstFile1));
					writer.write(": ");
					writeArray(writer, entry.getValue().get(firstFile1), 2);
					// System.out.println(entry.getValue());
					for (Map.Entry<String, TreeSet<Integer>> file : entry.getValue()
							.tailMap(entry.getValue().firstKey(), false).entrySet()) {
						writer.write(",");
						writer.newLine();
						writer.write(indent(2));

						writer.write(quote(file.getKey()));
						writer.write(": ");
						writeArray(writer, file.getValue(), 2);

					}
					writer.newLine();
					writer.write(indent(1));
					writer.write("}");

				}
				writer.newLine();
				writer.write("}");
				status = true;
			} else {
				writer.write("}");
			}

			return status;
		}

		catch (

		IOException e)

		{

			e.printStackTrace();
			return false;

		}

	}

	public boolean outputJson() {
		if (output != null) {
			return writeNestedObject(this.invertedMap);
		} else {
			System.out.println("Please entered a desired destination for output");
			return false;
		}
	}

	public TreeMap<String, TreeMap<String, TreeSet<Integer>>> work() {
		return invertedMap;
	}

	@Override
	public String toString() {
		return invertedMap.toString();
	}

}
