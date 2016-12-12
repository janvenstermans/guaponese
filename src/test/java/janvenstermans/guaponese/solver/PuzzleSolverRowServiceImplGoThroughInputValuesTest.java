package janvenstermans.guaponese.solver;

import janvenstermans.PuzzleTestUtil;
import janvenstermans.guaponese.model.PuzzleFieldStatus;
import janvenstermans.guaponese.model.PuzzleFieldStatusValue;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Test {@link PuzzleRowSolverServiceImpl#goThroughInputValuesFromHighestToLowest(InputValueSolverInfo[], PuzzleFieldStatus[])}
 * result InputValueSolverInfo[].
 */
public class PuzzleSolverRowServiceImplGoThroughInputValuesTest {
	
	private PuzzleRowSolverServiceImpl puzzleSolverRowService = new PuzzleRowSolverServiceImpl();

	//--------------------------------------------------------
	// method checkCountOfArray for specials
	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArrayEmptyInput() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput();
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput); // nothing solved yet
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput();

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}

	@Test
	public void testCheckCountOfArrayFullInput() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(10);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput); // nothing solved yet
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(10);
		setValuesIndexMinMax(inputArrayExpected[0], 0, 9);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}

	//--------------------------------------------------------
	// method checkCountOfArray for empty array
	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArrayOneNumber() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(7);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput); // nothing solved yet
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(7);
		setValuesIndexMinMax(inputArrayExpected[0], 0, 9);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}

	@Test
	public void testCheckCountOfArrayNoNumber() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput();
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput); // nothing solved yet
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput();

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}


	@Test
	public void testCheckCountOfArrayTwoNumbers() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(5, 3);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput); // nothing solved yet
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(5, 3);
		setValuesIndexMinMax(inputArrayExpected[0], 0, 5);
		setValuesIndexMinMax(inputArrayExpected[1], 6, 9);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}

	//--------------------------------------------------------
	// method checkCountOfArray for filled array
	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArrayWithSolvedElements1() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(5);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[2].setFieldValue(PuzzleFieldStatusValue.NONE);
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(5);
		setValuesIndexMinMax(inputArrayExpected[0], 3, 9);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}

	/*@Test
	public void testCheckCountOfArrayWithSolvedElements2() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(5);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[6].setFieldValue(PuzzleFieldStatusValue.NONE);
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput();
		// solved values
		statusArrayExpected[6].setFieldValue(PuzzleFieldStatusValue.NONE);
		for (int j = 1 ; j <= 4; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleFieldStatusValue.BLACK);
		}
		for (int j = 7 ; j < dimensionInput; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleFieldStatusValue.NONE);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}*/

	//--------------------------------------------------------

	// method checkCountOfArray for filling up empty elements
	/*@Test
	public void testCheckCountOfArrayWithSolvedElements3() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(1, 1, 1, 2);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[5].setFieldValue(PuzzleFieldStatusValue.NONE);
		statusArrayInput[9].setFieldValue(PuzzleFieldStatusValue.NONE);
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput();
		// solved values
		statusArrayExpected[0].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[1].setFieldValue(PuzzleFieldStatusValue.NONE);
		statusArrayExpected[2].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[3].setFieldValue(PuzzleFieldStatusValue.NONE);
		statusArrayExpected[4].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[5].setFieldValue(PuzzleFieldStatusValue.NONE);
		statusArrayExpected[7].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[9].setFieldValue(PuzzleFieldStatusValue.NONE);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}*/

	@Test
	public void testCheckCountOfArrayWithAllSolvedElements3() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(1, 2, 1, 3);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(1, 2, 1, 3);
		// solved values
		setValuesIndexMinMax(inputArrayExpected[0], 0, 0);
		setValuesIndexMinMax(inputArrayExpected[1], 2, 3);
		setValuesIndexMinMax(inputArrayExpected[2], 5, 5);
		setValuesIndexMinMax(inputArrayExpected[3], 7, 9);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}

	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArrayToAddNoneValues1() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(2, 2);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[1].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayInput[7].setFieldValue(PuzzleFieldStatusValue.BLACK);
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(2, 2);
		// solved values
		setValuesIndexMinMax(inputArrayExpected[0], 0, 2);
		setValuesIndexMinMax(inputArrayExpected[1], 6, 8);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}

	@Test
	public void testCheckCountOfArrayToAddNoneValues2() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(3, 1);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		for (int j = 3 ; j <= 5; j++) {
			statusArrayInput[j].setFieldValue(PuzzleFieldStatusValue.BLACK);
		}
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(3, 1);
		setValuesIndexMinMax(inputArrayExpected[0], 3, 5);
		setValuesIndexMinMax(inputArrayExpected[1], 7, 9);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}

	@Test
	public void testCheckCountOfArrayToAddNoneValues3() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(1, 3);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		for (int j = 4 ; j <= 6; j++) {
			statusArrayInput[j].setFieldValue(PuzzleFieldStatusValue.BLACK);
		}
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(1, 3);
		setValuesIndexMinMax(inputArrayExpected[0], 0, 2);
		setValuesIndexMinMax(inputArrayExpected[1], 4, 6);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}


	@Test
	public void testCheckCountOfArrayToBlackValues3() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(2, 2);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[3].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayInput[7].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayInput[8].setFieldValue(PuzzleFieldStatusValue.BLACK);
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(2, 2);
		setValuesIndexMinMax(inputArrayExpected[0], 2, 4);
		setValuesIndexMinMax(inputArrayExpected[1], 7, 8);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}

	//--------------------------------------------------------
	// method checkCountOfArray all can be solved
	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArrayAllCanBeSolvedAllBlacksAccounted() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(1, 1, 1);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[2].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayInput[4].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayInput[6].setFieldValue(PuzzleFieldStatusValue.BLACK);
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(1, 1, 1);
		setValuesIndexMinMax(inputArrayExpected[0], 2, 2);
		setValuesIndexMinMax(inputArrayExpected[1], 4, 4);
		setValuesIndexMinMax(inputArrayExpected[2], 6, 6);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}

	@Test
	public void testCheckCountOfArrayAllCanBeSolvedAllNonesAccounted() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(1, 1, 1);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		for (int i = 0 ; i < dimensionInput; i++) {
			statusArrayInput[i].setFieldValue(PuzzleFieldStatusValue.NONE);
		}
		statusArrayInput[2].setFieldValue(null);
		statusArrayInput[4].setFieldValue(null);
		statusArrayInput[6].setFieldValue(null);
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(1, 1, 1);
		// solved values
		setValuesIndexMinMax(inputArrayExpected[0], 2, 2);
		setValuesIndexMinMax(inputArrayExpected[1], 4, 4);
		setValuesIndexMinMax(inputArrayExpected[2], 6, 6);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}

	//--------------------------------------------------------
	// special situations
	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArrayMiddleNoneValue() throws Exception {
		int dimensionInput = 15;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(3, 2, 1, 1); //the 1's are unimportant
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[1].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayInput[2].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayInput[6].setFieldValue(PuzzleFieldStatusValue.BLACK);
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(3, 2, 1, 1);
		// solved values
		setValuesIndexMinMax(inputArrayExpected[0], 0, 3);
		setValuesIndexMinMax(inputArrayExpected[1], 5, 7);
		setValuesIndexMinMax(inputArrayExpected[2], 8, 12);
		setValuesIndexMinMax(inputArrayExpected[3], 10, 14);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}

	/**
	 * Problem was throwing exception throw new PuzzleSolverException("Too many solved values for inputValue");
	 * @throws Exception
	 */
	@Test
	public void testCheckCountOfArraySpecial1() throws Exception {
		int dimensionInput = 20;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(1, 3, 3, 4);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[4].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayInput[5].setFieldValue(PuzzleFieldStatusValue.BLACK);
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(1, 3, 3, 4);
		// solved values
		setValuesIndexMinMax(inputArrayExpected[0], 0, 2);
		setValuesIndexMinMax(inputArrayExpected[1], 3, 6);
		setValuesIndexMinMax(inputArrayExpected[2], 7, 14);
		setValuesIndexMinMax(inputArrayExpected[3], 11, 19);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}

	/**
	 * Problem was throwing exception throw new PuzzleSolverException("Too many solved values for inputValue");
	 * @throws Exception
	 */
	@Test
	public void testCheckCountOfArraySpecial2() throws Exception {
		int dimensionInput = 20;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(4, 3, 3, 1);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[15].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayInput[14].setFieldValue(PuzzleFieldStatusValue.BLACK);
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(4, 3, 3, 1);
		// solved values
		setValuesIndexMinMax(inputArrayExpected[0], 0, 8);
		setValuesIndexMinMax(inputArrayExpected[1], 5, 12);
		setValuesIndexMinMax(inputArrayExpected[2], 13, 16);
		setValuesIndexMinMax(inputArrayExpected[3], 17, 19);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected);
	}

	//--------------------------------------------------------
	// private method
	//--------------------------------------------------------

	private void setValuesIndexMinMax(InputValueSolverInfo inputValueSolverInfo, Integer indexMin, Integer indexMax) throws PuzzleSolverException {
		inputValueSolverInfo.setIndexMin(indexMin);
		inputValueSolverInfo.setIndexMax(indexMax);
	}

	private void executeCallAndAnalyse(InputValueSolverInfo[] inputArrayInput,
									   PuzzleFieldStatus[] statusArrayInput,
									   InputValueSolverInfo[] inputArrayExpected) throws PuzzleSolverException {
		puzzleSolverRowService.goThroughInputValues(inputArrayInput, statusArrayInput);

		assertEqualsInputValueSolverInfoArray(inputArrayExpected, inputArrayInput);
	}

	private void assertEqualsInputValueSolverInfoArray(InputValueSolverInfo[] expected, InputValueSolverInfo[] result) {
		if (expected != null) {
			Assert.assertNotNull(result);
			Assert.assertEquals(expected.length, result.length);
			for (int i = 0; i < expected.length; i++) {
				assertEqualsInputValueSolverInfo(expected[i], result[i]);
			}
		} else {
			Assert.assertNull(result);
		}
	}

	private void assertEqualsInputValueSolverInfo(InputValueSolverInfo expected, InputValueSolverInfo result) {
		if (expected != null) {
			Assert.assertNotNull(result);
			Assert.assertEquals(expected.isSolved(), result.isSolved());
			Assert.assertEquals(expected.getInputValue(), result.getInputValue());
			Assert.assertEquals(expected.getIndexMin(), result.getIndexMin());
			Assert.assertEquals(expected.getIndexMax(), result.getIndexMax());
		} else {
			Assert.assertNull(result);
		}
	}
}
