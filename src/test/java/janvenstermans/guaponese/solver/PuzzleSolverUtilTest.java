package janvenstermans.guaponese.solver;

import janvenstermans.PuzzleTestUtil;
import janvenstermans.guaponese.model.PuzzleFieldStatus;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class PuzzleSolverUtilTest {

	//--------------------------------------------------------
	// method checkCountOfArray for specials
	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArrayEmptyInput() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput();
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput); // nothing solved yet
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		for (int j = 0 ; j < dimensionInput; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}

	@Test
	public void testCheckCountOfArrayFullInput() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput(10);
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput); // nothing solved yet
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		for (int j = 0 ; j < dimensionInput; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}

	//--------------------------------------------------------
	// method checkCountOfArray for empty array
	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArrayOneNumber() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput(7);
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput); // nothing solved yet
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		for (int j = 3 ; j < 7; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}

	@Test
	public void testCheckCountOfArrayNoNumber() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput();
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput); // nothing solved yet
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		for (PuzzleFieldStatus fieldStatus : statusArrayExpected) {
			fieldStatus.setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}


	@Test
	public void testCheckCountOfArrayTwoNumbers() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput(5, 3);
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput); // nothing solved yet
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		for (int j = 1 ; j < 5; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		}
		for (int j = 7 ; j < 9; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}

	//--------------------------------------------------------
	// method checkCountOfArray for filled array
	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArrayWithSolvedElements1() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput(5);
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[2].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayExpected[2].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		for (int j = 0 ; j <= 2; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		}
		for (int j = 5 ; j <= 7; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}

	/*@Test
	public void testCheckCountOfArrayWithSolvedElements2() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput(5);
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[6].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayExpected[6].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		for (int j = 1 ; j <= 4; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		}
		for (int j = 7 ; j < dimensionInput; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}*/

	//--------------------------------------------------------

	// method checkCountOfArray for filling up empty elements
	/*@Test
	public void testCheckCountOfArrayWithSolvedElements3() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput(1, 1, 1, 2);
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[5].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		statusArrayInput[9].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayExpected[0].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[1].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		statusArrayExpected[2].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[3].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		statusArrayExpected[4].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[5].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		statusArrayExpected[7].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[9].setFieldValue(PuzzleSolverUtil.VALUE.NONE);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}*/

	@Test
	public void testCheckCountOfArrayWithAllSolvedElements3() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput(1, 2, 1, 3);
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayExpected[0].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[1].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		statusArrayExpected[2].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[3].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[4].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		statusArrayExpected[5].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[6].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		statusArrayExpected[7] .setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[8].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[9].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}

	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArrayToAddNoneValues1() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput(2, 2);
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[1].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayInput[7].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayExpected[1].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		for (int j = 3 ; j <= 5; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		}
		statusArrayExpected[7].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[9].setFieldValue(PuzzleSolverUtil.VALUE.NONE);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}

	@Test
	public void testCheckCountOfArrayToAddNoneValues2() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput(3, 1);
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		for (int j = 3 ; j <= 5; j++) {
			statusArrayInput[j].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		}
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		for (int j = 0 ; j <= 2; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		}
		for (int j = 3 ; j <= 5; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}

	@Test
	public void testCheckCountOfArrayToAddNoneValues3() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput(1, 3);
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		for (int j = 4 ; j <= 6; j++) {
			statusArrayInput[j].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		}
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		for (int j = 4 ; j <= 6; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		}
		for (int j = 7 ; j <= 9; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}


	@Test
	public void testCheckCountOfArrayToBlackValues3() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput(2, 2);
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[3].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayInput[7].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayInput[8].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[0].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayInput[1].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayInput[3].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayInput[5].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayInput[6].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayInput[7].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayInput[8].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayInput[9].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}

	//--------------------------------------------------------
	// method checkCountOfArray all can be solved
	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArrayAllCanBeSolvedAllBlacksAccounted() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput(1, 1, 1);
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[2].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayInput[4].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayInput[6].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		for (int i = 0 ; i < dimensionInput; i++) {
			statusArrayExpected[i].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		}
		statusArrayExpected[2].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[4].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[6].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}

	@Test
	public void testCheckCountOfArrayAllCanBeSolvedAllNonesAccounted() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput(1, 1, 1);
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		for (int i = 0 ; i < dimensionInput; i++) {
			statusArrayInput[i].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		}
		statusArrayInput[2].setFieldValue(null);
		statusArrayInput[4].setFieldValue(null);
		statusArrayInput[6].setFieldValue(null);
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		for (int i = 0 ; i < dimensionInput; i++) {
			statusArrayExpected[i].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		}
		statusArrayExpected[2].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[4].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[6].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}

	//--------------------------------------------------------
	// special situations
	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArrayMiddleNoneValue() throws Exception {
		int dimensionInput = 15;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput(3, 2, 1, 1); //the 1's are unimportant
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[1].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayInput[2].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayInput[6].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayExpected[1].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[2].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[4].setFieldValue(PuzzleSolverUtil.VALUE.NONE);
		statusArrayExpected[6].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}

	/**
	 * Problem was throwing exception throw new PuzzleSolverException("Too many solved values for inputValue");
	 * @throws Exception
	 */
	@Test
	public void testCheckCountOfArraySpecial1() throws Exception {
		int dimensionInput = 20;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput(1, 3, 3, 4);
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[4].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayInput[5].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayExpected[4].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[5].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}

	/**
	 * Problem was throwing exception throw new PuzzleSolverException("Too many solved values for inputValue");
	 * @throws Exception
	 */
	@Test
	public void testCheckCountOfArraySpecial2() throws Exception {
		int dimensionInput = 20;
		InputValueSolverInfo[] inputArrayInput = createInputArrayInput(4, 3, 3, 1);
		PuzzleFieldStatus[] statusArrayInput = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[15].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayInput[14].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		// create expected values
		PuzzleFieldStatus[] statusArrayExpected  = createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayExpected[15].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);
		statusArrayExpected[14].setFieldValue(PuzzleSolverUtil.VALUE.BLACK);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, statusArrayExpected);
	}

	//--------------------------------------------------------
	// private method
	//--------------------------------------------------------

	private PuzzleFieldStatus[] createEmptyColumnPuzzleFieldStatusArray(int dimension) {
		PuzzleFieldStatus[] statusArray = new PuzzleFieldStatus[dimension];
		for (int i = 0; i < dimension; i++) {
			statusArray[i] = new PuzzleFieldStatus(i, 0);
		}
		return statusArray;
	}

	private void assertStatusArray(PuzzleFieldStatus[] expected, PuzzleFieldStatus[] result) {
		if (expected != null) {
			Assert.assertNotNull(result);
			Assert.assertEquals(expected.length, result.length);
			for (int i = 0; i < expected.length; i++) {
				assertEquals(expected[i], result[i]);
			}
		} else {
			Assert.assertNull(result);
		}
	}

	private void assertEquals(PuzzleFieldStatus expected, PuzzleFieldStatus result) {
		if (expected != null) {
			Assert.assertNotNull(result);
			Assert.assertEquals(expected.isSolved(), result.isSolved());
			Assert.assertEquals(expected.getFieldValue(), result.getFieldValue());
			Assert.assertEquals(expected.getX(), result.getX());
			Assert.assertEquals(expected.getY(), result.getY());
		} else {
			Assert.assertNull(result);
		}
	}

	private void printInputAndExpectedOutputAsRow(InputValueSolverInfo[] inputArray,
												  PuzzleFieldStatus[] statusArrayInput,
												  PuzzleFieldStatus[] statusArrayExpected) {
		System.out.println("Input vs expected");
		PuzzleTestUtil.printArrayAsRow(inputArray, statusArrayInput);
		PuzzleTestUtil.printArrayAsRow(inputArray, statusArrayExpected);
	}

	private void printCalculatedOutputAsRow(InputValueSolverInfo[] inputArray, PuzzleFieldStatus[] arrayResult) {
		System.out.println("Output");
		PuzzleTestUtil.printArrayAsRow(inputArray, arrayResult);
		System.out.println();
	}

	private InputValueSolverInfo[] createInputArrayInput(Integer...integers) {
		int count = integers.length;
		InputValueSolverInfo[] inputValueSolverInfoArray = new InputValueSolverInfo[count];
		for (int i = 0; i < count; i++) {
			inputValueSolverInfoArray[i] = new InputValueSolverInfo(integers[i]);
		}
		return inputValueSolverInfoArray;
	}

	private void executeCallAndAnalyse(InputValueSolverInfo[] inputArrayInput, PuzzleFieldStatus[] statusArrayInput, PuzzleFieldStatus[] statusArrayExpected) throws PuzzleSolverException {
		PuzzleFieldStatus[] arrayResult = PuzzleSolverUtil.checkCountOfArray(inputArrayInput, statusArrayInput);

		printInputAndExpectedOutputAsRow(inputArrayInput, statusArrayInput, statusArrayExpected);
		printCalculatedOutputAsRow(inputArrayInput, arrayResult);
		assertStatusArray(statusArrayExpected, arrayResult);
	}
}
