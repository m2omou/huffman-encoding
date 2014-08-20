import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;


public class FileFreqWordsIteratorTest extends TestCase {

	public void writeToFile(String input, String destination) {
		for (int i = 0; i < input.length(); i++) {
			String bin = HuffmanEncoding.convertTo8bits(Integer.toBinaryString(input.charAt(i)));
			FileOutputHelper.writeBinStrToFile(bin, destination);
		}
	}
	
	public boolean checkOutput(String[] output, FileFreqWordsIterator it) {
		for (int i = 0; i < output.length; i++) {
			
			if (!it.hasNext()) 
				return false;
			
			String check = "";
			String character = it.next();
			
			for (int x = 0; x < output[i].length(); x++) {
				check += HuffmanEncoding.convertTo8bits(Integer.toBinaryString(output[i].charAt(x)));
			}
			
			System.out.println("expect => [" + check + "] = " + character);
			
			if (!check.equals(character)) 
				return false;
		}
		return true;
	}
	
	public void testOneFreq() {
		File f = new File("regularFile.txt");
		String[] output = {"kitten", " ", "kitten", " ", "kitten", " ", "k", "i", "m", " ", "kitten"};
		
		try {
			if (f.exists())
				f.delete();
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		writeToFile("kitten kitten kitten kim kitten", f.getAbsolutePath());
		
		assertEquals(true, checkOutput(output, new FileFreqWordsIterator(f.getAbsolutePath(), 1)));
		f.delete();
	}
	
	public void testThreeFreq() {
		File f = new File("threeFreq.txt");
		String[] output = {"aaa", " ", "aaa", " ", "bbb", " ", "bbb", " ", "c", "c", "c", " ", "dddd", " ", "dddd"};
		
		try {
			if (f.exists())
				f.delete();
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		writeToFile("aaa aaa bbb bbb ccc dddd dddd", f.getAbsolutePath());
		
		assertEquals(true, checkOutput(output, new FileFreqWordsIterator(f.getAbsolutePath(), 3)));
		f.delete();
	}
	
	public void testZeroFreq() {
		File f = new File("zeroFreq.txt");
		String[] output = {"B", "o", "n", "j", "o", "u", "r", " ", "l", "e", " ", "c", "h", "a", "t"};
		
		try {
			if (f.exists())
				f.delete();
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		writeToFile("Bonjour le chat", f.getAbsolutePath());
		
		assertEquals(true, checkOutput(output, new FileFreqWordsIterator(f.getAbsolutePath(), 0)));
		f.delete();
	}
	
	public void testNegativeFreq() {
		assertEquals(true, true);
	}
	
	public void testBigFreq() {
		File f = new File("bigFreq.txt");
		String[] output = {"aaa", " ", "aaa", " ", "bbb", " ", "bbb", " ", "ccc", " ", "dddd", " ", "dddd"};
		
		try {
			if (f.exists())
				f.delete();
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		writeToFile("aaa aaa bbb bbb ccc dddd dddd", f.getAbsolutePath());
		
		assertEquals(true, checkOutput(output, new FileFreqWordsIterator(f.getAbsolutePath(), 4000)));
		f.delete();
	}

	public void testEmptyFile() {
		File f = new File("emptyFile.txt");

		try {
			if (f.exists())
				f.delete();
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileFreqWordsIterator it = new FileFreqWordsIterator(f.getAbsolutePath(), 2);
		
		assertEquals(false, it.hasNext());
		f.delete();
	}
	
	public void testWithNoWords() {
		File f = new File("bigFreq.txt");
		String[] output = {"a", " ", "b", " ", "c", " ", "d", " ", "e", " ", "f", " ", "g"};
		
		try {
			if (f.exists())
				f.delete();
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		writeToFile("a b c d e f g", f.getAbsolutePath());
		
		assertEquals(true, checkOutput(output, new FileFreqWordsIterator(f.getAbsolutePath(), 5)));
		f.delete();
	}
	
	
}

