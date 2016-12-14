package janvenstermans.guaponese.solver;

import janvenstermans.PuzzleTestUtil;
import janvenstermans.guaponese.model.PuzzleFieldStatus;
import janvenstermans.guaponese.model.PuzzleFieldStatusValue;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Test {@link PuzzleLineSolverService#solvePuzzleLine(InputValueSolverInfo[], PuzzleFieldStatus[])}
 * result InputValueSolverInfo[].
 */
public class PuzzleLineSolverServiceTest {
	
	private PuzzleLineSolverService puzzleSolverRowService = new PuzzleLineSolverServiceImpl();

	//--------------------------------------------------------
	// method solvePuzzleLine for specials
	//--------------------------------------------------------

	@Test
	public void testSolvePuzzleLineEmptyInput() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput();
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput); // nothing solved yet
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput();
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		for (int j = 0 ; j < dimensionInput; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleFieldStatusValue.NONE);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}

	@Test
	public void testSolvePuzzleLineFullInput() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(10);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput); // nothing solved yet
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(10);
		setValuesIndexMinMax(inputArrayExpected[0], 0, 9);
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		for (int j = 0 ; j < dimensionInput; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleFieldStatusValue.BLACK);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}

	//--------------------------------------------------------
	// method solvePuzzleLine for empty array
	//--------------------------------------------------------

	@Test
	public void testSolvePuzzleLineOneNumber() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(7);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput); // nothing solved yet
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(7);
		setValuesIndexMinMax(inputArrayExpected[0], 0, 9);
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		for (int j = 3 ; j < 7; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleFieldStatusValue.BLACK);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}

	@Test
	public void testSolvePuzzleLineNoNumber() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput();
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput); // nothing solved yet
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput();
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		for (PuzzleFieldStatus fieldStatus : statusArrayExpected) {
			fieldStatus.setFieldValue(PuzzleFieldStatusValue.NONE);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}


	@Test
	public void testSolvePuzzleLineTwoNumbers() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(5, 3);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput); // nothing solved yet
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(5, 3);
		setValuesIndexMinMax(inputArrayExpected[0], 0, 5);
		setValuesIndexMinMax(inputArrayExpected[1], 6, 9);
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		for (int j = 1 ; j < 5; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleFieldStatusValue.BLACK);
		}
		for (int j = 7 ; j < 9; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleFieldStatusValue.BLACK);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}

	//--------------------------------------------------------
	// method solvePuzzleLine for filled array
	//--------------------------------------------------------

	@Test
	public void testSolvePuzzleLineWithSolvedElements1() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(5);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[2].setFieldValue(PuzzleFieldStatusValue.NONE);
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(5);
		setValuesIndexMinMax(inputArrayExpected[0], 3, 9);
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		statusArrayExpected[2].setFieldValue(PuzzleFieldStatusValue.NONE);
		for (int j = 0 ; j <= 2; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleFieldStatusValue.NONE);
		}
		for (int j = 5 ; j <= 7; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleFieldStatusValue.BLACK);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}

	/*@Test
	public void testSolvePuzzleLineWithSolvedElements2() throws Exception {
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

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}*/

	//--------------------------------------------------------

	// method solvePuzzleLine for filling up empty elements
	/*@Test
	public void testSolvePuzzleLineWithSolvedElements3() throws Exception {
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

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}*/

	@Test
	public void testSolvePuzzleLineWithAllSolvedElements3() throws Exception {
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
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayExpected[0].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[1].setFieldValue(PuzzleFieldStatusValue.NONE);
		statusArrayExpected[2].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[3].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[4].setFieldValue(PuzzleFieldStatusValue.NONE);
		statusArrayExpected[5].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[6].setFieldValue(PuzzleFieldStatusValue.NONE);
		statusArrayExpected[7] .setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[8].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[9].setFieldValue(PuzzleFieldStatusValue.BLACK);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}

	//--------------------------------------------------------

	@Test
	public void testSolvePuzzleLineToAddNoneValues1() throws Exception {
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
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		statusArrayExpected[1].setFieldValue(PuzzleFieldStatusValue.BLACK);
		for (int j = 3 ; j <= 5; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleFieldStatusValue.NONE);
		}
		statusArrayExpected[7].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[9].setFieldValue(PuzzleFieldStatusValue.NONE);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}

	@Test
	public void testSolvePuzzleLineToAddNoneValues2() throws Exception {
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
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		for (int j = 0 ; j <= 2; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleFieldStatusValue.NONE);
		}
		for (int j = 3 ; j <= 5; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleFieldStatusValue.BLACK);
		}
		statusArrayExpected[6].setFieldValue(PuzzleFieldStatusValue.NONE);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}

	@Test
	public void testSolvePuzzleLineToAddNoneValues3() throws Exception {
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
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayExpected[3].setFieldValue(PuzzleFieldStatusValue.NONE);
		for (int j = 4 ; j <= 6; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleFieldStatusValue.BLACK);
		}
		for (int j = 7 ; j <= 9; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleFieldStatusValue.NONE);
		}

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}


	@Test
	public void testSolvePuzzleLineToBlackValues3() throws Exception {
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
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		statusArrayExpected[0].setFieldValue(PuzzleFieldStatusValue.NONE);
		statusArrayExpected[1].setFieldValue(PuzzleFieldStatusValue.NONE);
		statusArrayExpected[3].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[5].setFieldValue(PuzzleFieldStatusValue.NONE);
		statusArrayExpected[6].setFieldValue(PuzzleFieldStatusValue.NONE);
		statusArrayExpected[7].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[8].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[9].setFieldValue(PuzzleFieldStatusValue.NONE);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}

	//--------------------------------------------------------
	// method solvePuzzleLine all can be solved
	//--------------------------------------------------------

	@Test
	public void testSolvePuzzleLineAllCanBeSolvedAllBlacksAccounted() throws Exception {
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
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		for (int i = 0 ; i < dimensionInput; i++) {
			statusArrayExpected[i].setFieldValue(PuzzleFieldStatusValue.NONE);
		}
		statusArrayExpected[2].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[4].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[6].setFieldValue(PuzzleFieldStatusValue.BLACK);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}

	@Test
	public void testSolvePuzzleLineAllCanBeSolvedAllNonesAccounted() throws Exception {
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
		// create expected values inputArrayExpected
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(1, 1, 1);
		setValuesIndexMinMax(inputArrayExpected[0], 2, 2);
		setValuesIndexMinMax(inputArrayExpected[1], 4, 4);
		setValuesIndexMinMax(inputArrayExpected[2], 6, 6);
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		for (int i = 0 ; i < dimensionInput; i++) {
			statusArrayExpected[i].setFieldValue(PuzzleFieldStatusValue.NONE);
		}
		statusArrayExpected[2].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[4].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[6].setFieldValue(PuzzleFieldStatusValue.BLACK);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}

	//--------------------------------------------------------
	// special situations
	//--------------------------------------------------------

	@Test
	public void testSolvePuzzleLineMiddleNoneValue() throws Exception {
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
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		statusArrayExpected[1].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[2].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[4].setFieldValue(PuzzleFieldStatusValue.NONE);
		statusArrayExpected[6].setFieldValue(PuzzleFieldStatusValue.BLACK);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}

	/**
	 * Problem was throwing exception throw new PuzzleSolverException("Too many solved values for inputValue");
	 * @throws Exception
	 */
	@Test
	public void testSolvePuzzleLineSpecial1() throws Exception {
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
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		statusArrayExpected[4].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[5].setFieldValue(PuzzleFieldStatusValue.BLACK);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}

	/**
	 * Problem was throwing exception throw new PuzzleSolverException("Too many solved values for inputValue");
	 * @throws Exception
	 */
	@Test
	public void testSolvePuzzleLineSpecial2() throws Exception {
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
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		statusArrayExpected[15].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[14].setFieldValue(PuzzleFieldStatusValue.BLACK);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
	}


	/**
	 * Problem was throwing exception throw new PuzzleSolverException("Too many solved values for inputValue");
	 * @throws Exception
	 */
	@Test
	public void testSolvePuzzleLineSpecialAllValuesSolved() throws Exception {
		int dimensionInput = 10;
		InputValueSolverInfo[] inputArrayInput = PuzzleTestUtil.createInputArrayInput(1, 2);
		PuzzleFieldStatus[] statusArrayInput = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		// solved values
		statusArrayInput[3].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayInput[7].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayInput[8].setFieldValue(PuzzleFieldStatusValue.BLACK);
		// create expected values
		InputValueSolverInfo[] inputArrayExpected  = PuzzleTestUtil.createInputArrayInput(1, 2);
		// solved values
		setValuesIndexMinMax(inputArrayExpected[0], 3, 3);
		setValuesIndexMinMax(inputArrayExpected[1], 7, 8);
		// create expected values statusArrayExpected
		PuzzleFieldStatus[] statusArrayExpected  = PuzzleTestUtil.createEmptyColumnPuzzleFieldStatusArray(dimensionInput);
		for (int j = 0 ; j <= 9; j++) {
			statusArrayExpected[j].setFieldValue(PuzzleFieldStatusValue.NONE);
		}
		statusArrayExpected[3].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[7].setFieldValue(PuzzleFieldStatusValue.BLACK);
		statusArrayExpected[8].setFieldValue(PuzzleFieldStatusValue.BLACK);

		executeCallAndAnalyse(inputArrayInput, statusArrayInput, inputArrayExpected, statusArrayExpected);
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
									   InputValueSolverInfo[] inputArrayExpected,
									   PuzzleFieldStatus[] statusArrayExpected) throws PuzzleSolverException {
		printStatusRowWithLabel("Input", inputArrayInput, statusArrayInput);

		PuzzleLineInfo puzzleLineInfo = new PuzzleLineInfo();
		puzzleLineInfo.setInputArray(inputArrayInput);
		puzzleLineInfo.setStatusArray(statusArrayInput);
		puzzleSolverRowService.solvePuzzleLine(puzzleLineInfo);

		printStatusRowWithLabel("Expected", inputArrayInput, statusArrayExpected);
		printMinMaxUnderRow(inputArrayExpected, statusArrayExpected);
		printStatusRowWithLabel("Result", inputArrayInput, statusArrayInput);
		printMinMaxUnderRow(inputArrayInput, statusArrayInput);

		assertEqualsInputValueSolverInfoArray(inputArrayExpected, inputArrayInput);
		assertStatusArray(statusArrayExpected, statusArrayInput);

		System.out.println();
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

	private void printStatusRowWithLabel(String label, InputValueSolverInfo[] inputArray, PuzzleFieldStatus[] arrayResult) {
		if (label != null) {
			System.out.println(label);
		}
		PuzzleTestUtil.printFieldStatusLine(inputArray, arrayResult);
	}

	private void printMinMaxUnderRow(InputValueSolverInfo[] inputArray, PuzzleFieldStatus[] arrayResult) {
		for (int i = 0; i < inputArray.length; i++) {
			PuzzleTestUtil.printMinMaxUnderLine(inputArray, arrayResult, i);
		}
	}
}
