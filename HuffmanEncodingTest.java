import junit.framework.*;

import java.io.IOException; 
import java.io.File;
import java.lang.Object; 
import java.util.Iterator;

public class HuffmanEncodingTest extends TestCase {
	
	public boolean isSameFile(FileCharIterator it, FileCharIterator it2) {
		while (it.hasNext() && it2.hasNext()) {
			String char1 = it.next();
			String char2 = it2.next();

			if (!char1.equals(char2))
				return false;
		}
		return true;
	}
	
	public void testLargeFile() {
		// encode
		HuffmanEncoding.encode("TheAdventuresOfSherlockHolmes.txt", "TheAdventuresOfSherlockHolmes.txt.huffman", 0);
		
		// decode 
		HuffmanEncoding.decode("TheAdventuresOfSherlockHolmes.txt.huffman", "mybook.txt");

		// check if these two files are the same
		boolean equals = isSameFile(new FileCharIterator("mybook.txt"), 
									new FileCharIterator("TheAdventuresOfSherlockHolmes.txt"));
		assertEquals(true, equals);
	}
	
	public void testImageFile() {
		// encode
		HuffmanEncoding.encode("HangInThere.jpg", "HangInThere.jpg.huffman", 0);

		// decode
		HuffmanEncoding.decode("HangInThere.jpg.huffman", "myHangInThere.jpg");

		// check if these two files are the same
		boolean equals = isSameFile(new FileCharIterator("myHangInThere.jpg"),
									new FileCharIterator("HangInThere.jpg"));
		assertEquals(true, equals);
	}
	
	public void testEmptyFile() {
		File f = new File("originalEmptyFile.txt");

		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// encode
		HuffmanEncoding.encode(f.getAbsolutePath(), "empty.txt.huffman", 0);

		// decode 
		HuffmanEncoding.decode("empty.txt.huffman", "empty.txt");
		
		// check if these two files are the same
		boolean equals = isSameFile(new FileCharIterator("empty.txt"), new FileCharIterator(f.getAbsolutePath()));

		f.delete();
		assertEquals(true, equals);
	}
	
	public void testASCIIFile() {
		File f = new File("originalASCIIFile.txt");
		
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// fill in the file with all the ASCII character
		for (int i = 0; i < 255; i++) {
			String section = Integer.toBinaryString(i);

			FileOutputHelper.writeBinStrToFile(HuffmanEncoding.convertTo8bits(section), f.getAbsolutePath());
		}
		
		// encode
		HuffmanEncoding.encode(f.getAbsolutePath(), "ASCIIFile.txt.huffman", 0);

		// decode 
		HuffmanEncoding.decode("ASCIIFile.txt.huffman", "ASCIIFile.txt");
		
		// check if these two files are the same
		boolean equals = isSameFile(new FileCharIterator(f.getAbsolutePath()), new FileCharIterator("ASCIIFile.txt"));

		f.delete();
		assertEquals(true, equals);
	}
	
	public void testFileWithFreqWords() {
		// encode
		HuffmanEncoding.encode("lastquestion.txt", "lastquestion.txt.huffman", 5);

		// decode
		HuffmanEncoding.decode("lastquestion.txt.huffman", "mylastquestion.txt");

		// check if these two files are the same
		boolean equals = isSameFile(new FileCharIterator("mylastquestion.txt"),
									new FileCharIterator("lastquestion.txt"));
		assertEquals(true, equals);
	}
}
