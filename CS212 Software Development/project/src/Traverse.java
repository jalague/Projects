
// Traverses a given directory finding all files with .txt then saving the path to a collection
//
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.TreeSet;

public class Traverse {
	private final TreeSet<Path> pathList;

	Traverse(Path directory) {
		this.pathList = new TreeSet<Path>();
		if (Files.isDirectory(directory)) {
			traverseDirectory(directory, ".txt");
		} else {
			System.out.println("Please enter valid directory");
		}
	}

	Traverse() {
		this.pathList = new TreeSet<Path>();
	}

	// trying to push
	public TreeSet<Path> traverseDirectory(Path directory, String fileType) {

		TreeSet<Path> fileSet = new TreeSet<Path>();
		try (DirectoryStream<Path> listing = Files.newDirectoryStream(directory)) {

			for (Path file : listing) {
				// System.out.println(file.getFileName());
				// System.out.println(file.toString());
				// System.out.println(file.toFile().getCanonicalPath());
				if (file.toString().toLowerCase().endsWith(".txt")) {
					pathList.add(file);
				} else if (Files.isDirectory(file)) {
					// System.out.println(file.toFile().getName());
					traverseDirectory(file, fileType);
				}

			}
			if (pathList.isEmpty()) {
				System.out.println("No files of that type were found");
			}

		} catch (Exception e) {
			System.out.println("Unable to access direcotry");
		}

		return fileSet;
	}

	public TreeSet<Path> getFiles() {
		return pathList;
	}

	@Override
	public String toString() {
		return pathList.toString();
	}

}
