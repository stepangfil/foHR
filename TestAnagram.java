package TaskOne.anagram;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Assert;
import org.junit.Test;


public class TestAnagram {


	
	@Test
 	public void anagramString() {
		
		String source = "he 123 1w2e";
		String expected = "eh 123 1e2w"; 
		String actual = Anagram.anagramString(source);
		Assert.assertEquals(expected, actual);
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void exchangeCharInWordOutOfBoundIndexValue() {
		
		char[] source = new char[]{'h', 'e'};
		char[] expected = new char[]{'e', 'h'}; 
		char[] actual = Anagram.exchangeCharInWord(source, 0, 2);
		Assert.assertArrayEquals(expected, actual);
	}
	
 	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void exchangeCharInWordNegativeIndex() {
		
		char[] source = new char[]{'h', 'e'};
		char[] expected = new char[]{'e', 'h'}; 
		char[] actual = Anagram.exchangeCharInWord(source, -1, 1);
		Assert.assertArrayEquals(expected, actual);
	}
	
	@Test
	public void exchangeCharInWordInputParam() {
		
		char[] source = new char[]{'h', 'h'};
		char[] expected = new char[]{'h', 'h'}; 
		char[] actual = Anagram.exchangeCharInWord(source, 0, 1);
		Assert.assertArrayEquals(expected, actual);
	}
	@Test
	public void exchangeCharInWordValidInputSwap() {
		
		char[] source = new char[]{'h', 'e'};
		char[] expected = new char[]{'e', 'h'}; 
		char[] actual = Anagram.exchangeCharInWord(source, 0, 1);
		Assert.assertArrayEquals(expected, actual);
	}
	
	@Test
	public void checkLetterInWordValidInputSwapNotLetter() {
		char[] source = new char[]{'2', '!', 'b', 'a'};
		char[] expected = new char[]{'2', '!', 'a', 'b'};
		char[] actual = Anagram.swapChars(source, 0, 3);
		Assert.assertArrayEquals(expected, actual);
	}

}
