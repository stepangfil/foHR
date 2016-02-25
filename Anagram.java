package TaskOne.anagram;

public class Anagram {

	public static void main(String[] args) {
	String strInput = "hello 123 he1l0";
		
		printString(anagramString(strInput));
		
	}
	
	public static String anagramString(String str){
		String[] strArray = str.split(" ");
		StringBuilder afterFormat = new StringBuilder();
		String afterFormatToStr = "";
		char[] word;
		
		for (int i = 0; i < strArray.length; i++){
			String wordFromArray = strArray[i];
			word = wordFromArray.toCharArray();
			int firstElem = 0;
			int lastElem = word.length-1;
			
			afterFormat.append(swapChars(word, firstElem, lastElem));
			afterFormat.append(' ');
			afterFormatToStr = afterFormat.toString(); 
		}
		return  afterFormatToStr;
	}

	/**
	 * method implements the word verification for the maintenance of non-letter characters
	 * @param firsLetter 
	 * @param lastLetter
	 * @param checkWord 
	 * @return
	 */
	public static char[] swapChars(char[] checkWord, int firsLetter, int lastLetter){
		while(firsLetter<lastLetter) {
			if(!Character.isLetter(checkWord[firsLetter])){
				firsLetter++;
			}
			else if(!Character.isLetter(checkWord[lastLetter])){
				lastLetter--;
			}
			else {
				exchangeCharInWord(checkWord, firsLetter, lastLetter);
				firsLetter++; 
				lastLetter--;
			}
		}
		return checkWord;
	}

	/**
	 * method implements swapping two characters in the array
	 * @param charArray - Array chars of word
	 * @param first - first element of array
	 * @param last - second element of array
	 * @return
	 */
	
	public static char[] exchangeCharInWord(char[] charArray, int first, int last ){
		char intermediateChar;
		intermediateChar=charArray[first]; 
		charArray[first]=charArray[last]; 
		charArray[last]=intermediateChar;
		return charArray;
	}
	
	private static void printString(String printStr){
		System.out.print(printStr.toString());

	}

}
