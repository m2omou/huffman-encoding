import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

public class ZipperTest extends TestCase {

	// test a file
	// test empty folder
	// test folder with folders inside

	public boolean isSameFile(FileCharIterator it, FileCharIterator it2) {
		while (it.hasNext() && it2.hasNext()) {
			String char1 = it.next();
			String char2 = it2.next();

			if (!char1.equals(char2))
				return false;
		}
		return true;
	}

	public void writeToFile(String input, String destination) {
		for (int i = 0; i < input.length(); i++) {
			String bin = HuffmanEncoding.convertTo8bits(Integer
					.toBinaryString(input.charAt(i)));
			FileOutputHelper.writeBinStrToFile(bin, destination);
		}
	}

	public void testSignleFile() {
		File f = new File("singleFile.txt");
		boolean equals = false;

		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		writeToFile("this is a test", f.getAbsolutePath());

		Zipper.encodeZip(f.getAbsolutePath(), "singleFile.txt.zip");
		Zipper.decodeZip("singleFile.txt.zip", "singleFile-copy.txt");

		// check if these two files are the same
		equals = isSameFile(new FileCharIterator("singleFile-copy.txt"),
				new FileCharIterator(f.getAbsolutePath()));

		f.delete();
		assertEquals(true, equals);
	}

	public void testEmptyFolder() {
		File f = new File("emptyFolder");

		if (!f.exists())
			f.mkdir();

		Zipper.encodeZip(f.getName(), "emptyFolder.txt.zip");
		Zipper.decodeZip("emptyFolder.txt.zip", "emptyFolder-copy");

		// check if these two files are the same
		File emptyFolder = new File("emptyFolder-copy");

		assertEquals(true, emptyFolder.exists());
		f.delete();
		emptyFolder.delete();
	}

	File createFolder(String name) {
		File f = new File(name);

		if (!f.exists())
			f.mkdir();
		return f;
	}

	File createFile(String name, String content) {
		File f = new File(name);

		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		writeToFile(content, f.getAbsolutePath());
		return f;
	}

	public void testFolderWithFiles() {
		/* Folders */
		File main = createFolder("folderWithFiles");
		File folder1 = createFolder("folderWithFiles/folder1");
		File folder2 = createFolder("folderWithFiles/folder2");

		/* Files */
		File file1 = createFile("folderWithFiles/folder1/file1.txt", "");
		File file2 = createFile("folderWithFiles/folder1/file2.txt", "blabla");
		File file3 = createFile("folderWithFiles/folder2/file3.txt",
				"Hello my name is Mourad");

		/* encode */
		Zipper.encodeZip(main.getName(), "folderWithFiles.txt.zip");
		/* decode */
		Zipper.decodeZip("folderWithFiles.txt.zip", "folderWithFiles-copy");

		/* check that the folders exist */

		File copyMain = createFolder("folderWithFiles-copy");
		File copyFolder1 = createFolder("folderWithFiles-copy/folder1");
		File copyFolder2 = createFolder("folderWithFiles-copy/folder2");

		assertEquals(true, copyMain.exists());
		assertEquals(true, copyFolder1.exists());
		assertEquals(true, copyFolder2.exists());

		/* check that the files exist */

		File copyFile1 = createFolder("folderWithFiles-copy/folder1/file1.txt");
		File copyFile2 = createFolder("folderWithFiles-copy/folder1/file2.txt");
		File copyFile3 = createFolder("folderWithFiles-copy/folder2/file3.txt");

		assertEquals(true, copyFile1.exists());
		assertEquals(true, copyFile2.exists());
		assertEquals(true, copyFile3.exists());

		/* check the files exist */
		assertEquals(
				true,
				isSameFile(new FileCharIterator(file1.getAbsolutePath()),
						new FileCharIterator(copyFile1.getAbsolutePath())));
		assertEquals(
				true,
				isSameFile(new FileCharIterator(file2.getAbsolutePath()),
						new FileCharIterator(copyFile2.getAbsolutePath())));
		assertEquals(
				true,
				isSameFile(new FileCharIterator(file2.getAbsolutePath()),
						new FileCharIterator(copyFile2.getAbsolutePath())));

		/* now delete them all */
		main.delete();
		folder1.delete();
		folder2.delete();
		file1.delete();
		file2.delete();
		file3.delete();

		copyMain.delete();
		copyFolder1.delete();
		copyFolder2.delete();
		copyFile1.delete();
		copyFile2.delete();
		copyFile3.delete();
	}
}
