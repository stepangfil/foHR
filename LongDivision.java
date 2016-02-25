package division;

import java.util.ArrayList;
import java.util.Formatter;

public class LongDivision {

	public static void main(String[] args) {
		int dividend = 100;
		int divisor = 3;
		
		System.out.println(calculateLongDivision (dividend, divisor));
	}
	
	/**
	 * Method implements computing long division
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public static String calculateLongDivision (int dividend, int divisor) {
		
		char[] numbersDividend = conversToCharDivid (dividend);
		ArrayList<Integer> resultBasicCalculate = basicCalculate(numbersDividend, divisor);
		String lineBasicCalculate = formatResultBasicCalculate(dividend, divisor, resultBasicCalculate, numbersDividend);
		 
		
		return lineBasicCalculate;
	}
	
	/**
	 * method implements the basic calculate
	 * @param listInputsValues
	 * @param numbersDividend
	 * @param divisor
	 * @return
	 */
	private static ArrayList<Integer> basicCalculate(char[]numbersDividend, int divisor){
		ArrayList<Integer> interimResultsCalculate = new ArrayList<Integer>();
		
		int primaryDividend = formationPrimaryDividend(divisor, numbersDividend);
		int elementIndex = getElementIndex(primaryDividend);
		interimResultsCalculate.add(elementIndex);
		int subtrahend = calculateSubtrahend(primaryDividend, divisor);
		interimResultsCalculate.add(subtrahend);
		int shift = calculateShiftValue(primaryDividend, subtrahend);
		interimResultsCalculate.add(shift);
		int resultSubtraction = calculateSubtraction(primaryDividend, subtrahend);
		interimResultsCalculate.add(resultSubtraction);
		int interimDividend = getInterimDividend(resultSubtraction, numbersDividend, elementIndex);
		interimResultsCalculate.add(interimDividend);
		int tempShift = shift;
		
		while(elementIndex < numbersDividend.length){
			shift = calculateShiftValue(interimDividend, subtrahend);
			tempShift = tempShift + shift;
			interimResultsCalculate.add(tempShift);
			int subtrahendInCycle = calculateSubtrahend(interimDividend, divisor);
			interimResultsCalculate.add(subtrahendInCycle);
			int interimDividendInCycle = getInterimDividend(resultSubtraction, numbersDividend, elementIndex);
			interimResultsCalculate.add(interimDividendInCycle);
			int resultSubtractionInCycle = calculateSubtraction(interimDividend, subtrahend);
			interimResultsCalculate.add(resultSubtractionInCycle);
			elementIndex++;
		}
		
		return interimResultsCalculate;
	}
	
	/**
	 * method implements formatting the basic results of calculation
	 * @param elementIndex
	 * @param inputValues
	 * @param numbersDividend
	 * @return
	 */
	private static String formatResultBasicCalculate(int dividend, int divisor, ArrayList<Integer> inputValues, char[]numbersDividend){
		StringBuilder intermediateValue = new StringBuilder();
		String resultLine;
		
		int elementIndex = inputValues.get(0);
		int subtrahend = inputValues.get(1);
		int shift = inputValues.get(2);
		final int START_POSITION_OF_CALC_RESULT = 5;
		int currentPosition = START_POSITION_OF_CALC_RESULT;
		int shiftInCycle = 0;
		int resultSubtraction = 0; 
		int subtrahendInCycle = inputValues.get(currentPosition+1);
		int interimDividend = inputValues.get(currentPosition+2);
		
		String resultDivision = formatResultDivision(dividend, divisor);
		String firstLine = formatFirstLine(dividend, divisor);
		String secondLine = formatSecondLine(shift,subtrahend);
		String cornerLine = formatCornerLine(shift, numbersDividend);
		String resultDivisionLine = formatResultDivisionLine(numbersDividend, resultDivision);
		String finalLine = formatFinalLine(numbersDividend);
		
		intermediateValue.append(firstLine);
		intermediateValue.append(secondLine);
		intermediateValue.append(cornerLine);
		intermediateValue.append(resultDivisionLine);
		intermediateValue.append(finalLine);
		
		while(elementIndex < numbersDividend.length){
			shiftInCycle = inputValues.get(currentPosition);
			String interimDividendLine = formatCalculationResults(shiftInCycle, interimDividend);
			String subtrahendInCycleLine = formatCalculationResults(shiftInCycle, subtrahendInCycle);
			finalLine = formatFinalLine(numbersDividend);
			resultSubtraction = inputValues.get(currentPosition+3);
			
			intermediateValue.append(interimDividendLine);
			intermediateValue.append(subtrahendInCycleLine);
			intermediateValue.append(finalLine);
			
			elementIndex++;
			currentPosition+=4;
		}
		String lastLine = formatCalculationResults(shiftInCycle, resultSubtraction);
		intermediateValue.append(lastLine);
		
		resultLine = intermediateValue.toString();
		return resultLine;
	}
	/**
	 * Method implements formatting result division
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	private static String formatResultDivision(int dividend, int divisor){
		int integerPart = dividend / divisor;
		int fractionPart = calculateRestModulo (dividend, divisor);
		int restModulo = calculateRestModulo (fractionPart, divisor);
		int compare = restModulo;
		String nonPeriodPart = calculateNonPeriodPart(fractionPart, compare, divisor);
		String periodPart = calculatePeriodPart(restModulo, compare, divisor);
		String resultDivision = formattingResultDivision(integerPart, nonPeriodPart, periodPart);
		
		return resultDivision;
	}
	
	/**
	 * Method implements formatting first string as "1234|4"
	 * @param dividend                                
	 * @param divisor
	 */
	private static String formatFirstLine(int dividend, int divisor){
		String firstLine = dividend + "|" + divisor + "\n";
		
		return firstLine;
	}
	
	/**
	 * Method implements formatting second string 
	 * @param argrument
	 * @param width
	 */
	private static String formatSecondLine(int width, int argument){
		Formatter formatSecondLine = new Formatter();
		String shift = "%" + (width + 1) + "d";
		formatSecondLine.format(shift, argument);
		String secondLine = formatSecondLine.toString();
		
		return secondLine;
	}
	
	/**
	 * Method implements formatting corner line as "+------"
	 * @param shift
	 * @param numbersDividend
	 */
	private static String formatCornerLine(int shift, char[] numbersDividend){
		Formatter formatCornerLine = new Formatter();
		String constantCornerLine = "+-----";
		int lengthLine = constantCornerLine.length();
		int lengthArray = numbersDividend.length-1;
		int resultWidth = lengthArray - shift;
	    lengthLine = lengthLine+resultWidth;
		String shiftLine = "%" + lengthLine + "s" + "\n";
		formatCornerLine.format(shiftLine, constantCornerLine);
		String resultCornerLine;
		resultCornerLine = formatCornerLine.toString();
		
		return resultCornerLine;
		
	}
	
	 /**
	 * Method implements formatting result dividend as "| 123"
	 * @param resPrint
	 * @param numbersDividend
	 */
	private static String formatResultDivisionLine(char[] numbersDividend, String resPrint){
		Formatter formatResultLine = new Formatter();
		int lengthArrayDividend = numbersDividend.length;
		String resultDividend;
		String shiftResultDividend = "%" + (lengthArrayDividend+1) + "s" + "%s" + "\n";
		formatResultLine.format(shiftResultDividend, "|", resPrint);
		resultDividend = formatResultLine.toString();
		
		return resultDividend;
	}
	
	/**
	 * Method implements formatting line after computing
	 * @param numbersDividend
	 */
	private static String formatFinalLine(char[]numbersDividend){
		StringBuilder line = new StringBuilder();
		String lineToPrint;
		for(int i = 0; i < numbersDividend.length; i++){
			line.append('_');
		}
		line.append("\n");
		lineToPrint = line.toString();
		
		return lineToPrint;
	}
	
	/**
	 * Method implements formatting digit with certain shift
	 * @param width
	 * @param argument
	 */
	private static String formatCalculationResults(int width, int argument ){
		Formatter formatShiftLine = new Formatter();
		String ShiftLine;
		String shiftPrint = "%" + (width + 1) + "d" + "\n";
		formatShiftLine.format(shiftPrint, argument);
		ShiftLine = formatShiftLine.toString();
		return ShiftLine;
	}
	
	/**
	 * Method implements calculating value a shift
	 * @param primaryDividend
	 * @param subtrahend
	 * @return
	 */
	private static int calculateShiftValue(int primaryDividend, int subtrahend){
		String dividend = Integer.toString(primaryDividend);
		String subtrah = Integer.toString(subtrahend);
		int dividendLength = dividend.length();
		int subtrahLength = subtrah.length();
		int shift;
		
		if(dividendLength > subtrahLength){
			shift = dividendLength - subtrahLength;
		} else {
			shift = dividendLength-1;
		}
		return shift;
	}
	
	/**
	 * Method implements conversation number to array chars
	 * @param inputNumber
	 * @param dividend
	 * @return
	 */
	private static char[] conversToCharDivid (int dividend){
		String StringFromIntDividend = Integer.toString(dividend); 
		char[] arrayNumberDividend = StringFromIntDividend.toCharArray();
		
		return arrayNumberDividend;
	}
	
	/**
	 * Method implements calculating primary dividend
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	private static int formationPrimaryDividend(int divisor, char[] dividend){
		StringBuilder interimValue = new StringBuilder();  
		int elementIndex = 0;
		int primaryDividend = 1;
		
		while(primaryDividend < divisor){
			interimValue.append(dividend[elementIndex]);
			primaryDividend = Integer.parseInt(interimValue.toString());
			elementIndex++;
		}
		return primaryDividend;
	}
	
	/**
	 * Method implements the index of the array initialization
	 * @param primaryDividend
	 * @return
	 */
	private static int getElementIndex(int primaryDividend){
		String indexFromDividend = Integer.toString(primaryDividend);
		int elementIndex = indexFromDividend.length();
		
		return elementIndex;
	}
	
	/**
	 * Method implements calculating value of subtrahend 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	private static int calculateSubtrahend(int dividend, int divisor){
		int subtrahend = 1;
		int factor = 0;
		while(subtrahend < dividend ){
			subtrahend = factor * divisor;
			factor++;
		}
		subtrahend = (factor-2) * divisor; 
		
		return subtrahend;
	}
	/**
	 * Method implements calculating value of quontien
	 * @param subtrahend
	 * @param divisor
	 * @return
	 */
	private static String calculateQuontient (int subtrahend, int divisor){
		int resultCalculateQuontient = subtrahend / divisor; 
		String quontient = Integer.toString(resultCalculateQuontient);
		
		return quontient;
	}
	/**
	 * Method implements calculation result  
	 * @param dividend
	 * @param subtrahend
	 * @return
	 */
	private static int calculateSubtraction(int dividend, int subtrahend){
		int resultSubtraction = dividend - subtrahend;
		return resultSubtraction;
	}
	/**
	 * Method implements calculation main dividing
	 * @param resultSubtract
	 * @param numbersDividend
	 * @param elementIndex
	 * @return
	 */
	private static int getInterimDividend(int resultSubtract, char[]numbersDividend, int elementIndex){
		StringBuilder interimDividend = new StringBuilder(); 
		
		interimDividend.append(resultSubtract);
		interimDividend.append(numbersDividend[elementIndex]);
		int dividend = Integer.parseInt(interimDividend.toString());
		
		return dividend;
	}
	/**
	 * Method implements calculation fractional period part quotient
	 * @param restModulo
	 * @param compare
	 * @param divisor
	 * @return
	 */
	private static String calculatePeriodPart (int restModulo, int compare, int divisor){
		StringBuilder intermediateValue = new StringBuilder();
		String periodPart;
		intermediateValue.append('(');
		do{
			int resultDivision = calculateDivision (restModulo, divisor);
			restModulo = calculateRestModulo(restModulo, divisor);
			intermediateValue.append(resultDivision);
		 }while(restModulo != compare);
		intermediateValue.append(')');
		periodPart = intermediateValue.toString();
		return periodPart;
	}
	/**
	* Method implements calculation fractional non period part quotient
	 * @param restModulo
	 * @param compare
	 * @param divisor
	 * @return
	 */
	private static String calculateNonPeriodPart(int restModulo, int compare, int divisor){
		StringBuilder intermediateValue = new StringBuilder();	
		String nonPeriodPart;
		while(restModulo != compare){
			int resultDivision = calculateDivision (restModulo, divisor);
			intermediateValue.append(resultDivision);
			restModulo = calculateRestModulo(restModulo, divisor);
		}
		nonPeriodPart = intermediateValue.toString();
		return nonPeriodPart;
	}
	/**
	 * Method implements calculation division
	 * @param argument
	 * @param divisor
	 * @return
	 */
	private static int calculateDivision (int argument, int divisor){
		argument = argument*10/divisor;
		return argument;
	}
	/**
	 * Method implements calculation rest modulo
	 * @param argument
	 * @param divisor
	 * @return
	 */
	private static int calculateRestModulo(int argument, int divisor){
		argument=(argument*10)%divisor;
		return argument;
	}
	/**
	 * Method implements formating result division to print line
	 * @param resultWholePart
	 * @param nonPeriodPart
	 * @param periodPart
	 * @return
	 */
	private static String formattingResultDivision(int resultWholePart, String nonPeriodPart, String periodPart){
		String resultDivision = resultWholePart + "," + nonPeriodPart + periodPart;
		return resultDivision;
	}
} 
