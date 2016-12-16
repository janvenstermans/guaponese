package janvenstermans.guaponese.solver;

import janvenstermans.guaponese.model.PuzzleFieldStatusValue;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Unit test for {@link InputValueSolverInfo} class.
 */
public class InputValueSolverInfoTest {

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorValues() throws Exception {
		int inputValue = 10;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValue, PuzzleFieldStatusValue.BLACK, 5, 3);
	}

	//--------------------------------------------------------
	// method addSolvedValue
	//--------------------------------------------------------

	@Test
	public void testAddSolvedValueForLessAmountOfInputIsUnsolved() throws Exception {
		int indexMinInput = 0;
		int indexMaxInput = 99;
		int[] inputValueArray = { 1, 8, 4, 13};
		InputValueSolverInfo[] inputValueSolverInfoArray = new InputValueSolverInfo[inputValueArray.length];
		for (int i = 0; i < inputValueArray.length ; i++) {
			inputValueSolverInfoArray[i] = new InputValueSolverInfo(inputValueArray[i], PuzzleFieldStatusValue.BLACK, indexMinInput, indexMaxInput);
		}
		// add distinct values for the amount of each inputValue - 1
		for (int i = 0; i < inputValueArray.length; i++) {
			int inputValue = inputValueArray[i];
			InputValueSolverInfo inputValueSolverInfo = inputValueSolverInfoArray[i];
			for (int j = 1; j < inputValue; j++) {
				inputValueSolverInfo.addSolvedValue(j);
			}
		}

		for (int i = 0; i < inputValueArray.length ; i++) {
			Assert.assertFalse(inputValueSolverInfoArray[i].isSolved());
		}
	}

	@Test
	public void testAddSolvedValueForAmountOfInputIsSolved() throws Exception {
		int indexMinInput = 0;
		int indexMaxInput = 99;
		int[] inputValueArray = { 1, 8, 4, 13};
		InputValueSolverInfo[] inputValueSolverInfoArray = new InputValueSolverInfo[inputValueArray.length];
		for (int i = 0; i < inputValueArray.length ; i++) {
			inputValueSolverInfoArray[i] = new InputValueSolverInfo(inputValueArray[i], PuzzleFieldStatusValue.BLACK, indexMinInput, indexMaxInput);
		}
		// add distinct values for the amount of each inputValue
		for (int i = 0; i < inputValueArray.length; i++) {
			int inputValue = inputValueArray[i];
			InputValueSolverInfo inputValueSolverInfo = inputValueSolverInfoArray[i];
			for (int j = 0; j < inputValue ; j++) {
				inputValueSolverInfo.addSolvedValue(j);
			}
		}

		for (int i = 0; i < inputValueArray.length ; i++) {
			Assert.assertTrue(inputValueSolverInfoArray[i].isSolved());
		}
	}

	//--------------------------------------------------------
	// method setIndexMin
	//--------------------------------------------------------

	@Test
	public void testSetIndexMinWithLowerValueDoesNotChangeIndexMin() throws Exception {
		int inputValueArray = 2;
		int indexMinFirst = 5;
		int indexMinSecond = 3;
		int indexMaxInput = 9;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray, PuzzleFieldStatusValue.BLACK, indexMinFirst, indexMaxInput);

		inputValueSolverInfo.setIndexMin(indexMinSecond);

		Assert.assertNotNull(inputValueSolverInfo.getIndexMin());
		Assert.assertEquals(indexMinFirst, inputValueSolverInfo.getIndexMin());
	}

	@Test
	public void testSetIndexMinWithHigherValueChangesIndexMin() throws Exception {
		int inputValueArray = 2;
		int indexMinFirst = 3;
		int indexMinSecond = 5;
		int indexMaxInput = 9;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray, PuzzleFieldStatusValue.BLACK, indexMinFirst, indexMaxInput);

		inputValueSolverInfo.setIndexMin(indexMinSecond);

		Assert.assertNotNull(inputValueSolverInfo.getIndexMin());
		Assert.assertEquals(indexMinSecond, inputValueSolverInfo.getIndexMin());
	}

	//--------------------------------------------------------
	// method setIndexMax
	//--------------------------------------------------------

	@Test
	public void testSetIndexMaxWithHigherValueDoesNotChangeIndexMax() throws Exception {
		int inputValueArray = 2;
		int indexMinInput = 0;
		int indexMaxFirst = 3;
		int indexMaxSecond = 5;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray, PuzzleFieldStatusValue.BLACK, indexMinInput, indexMaxFirst);

		inputValueSolverInfo.setIndexMax(indexMaxSecond);

		Assert.assertNotNull(inputValueSolverInfo.getIndexMax());
		Assert.assertEquals(indexMaxFirst, inputValueSolverInfo.getIndexMax());
	}

	@Test
	public void testSetIndexMaxWithLowerValueChangesIndexMax() throws Exception {
		int inputValueArray = 2;
		int indexMinInput = 0;
		int indexMaxFirst = 5;
		int indexMaxSecond = 3;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray, PuzzleFieldStatusValue.BLACK, indexMinInput, indexMaxFirst);

		inputValueSolverInfo.setIndexMax(indexMaxSecond);

		Assert.assertNotNull(inputValueSolverInfo.getIndexMax());
		Assert.assertEquals(indexMaxSecond, inputValueSolverInfo.getIndexMax());
	}

	@Test
	public void testSetIndexMaxWithIndexJustRightToSolve() throws Exception {
		int inputValueArray = 5;
		int indexMinInitial = 2;
		int indexMaxInput = 9;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray, PuzzleFieldStatusValue.BLACK, indexMinInitial, indexMaxInput);
		inputValueSolverInfo.setIndexMin(indexMinInitial);

		inputValueSolverInfo.setIndexMax(indexMinInitial + inputValueArray - 1);

		Assert.assertTrue(inputValueSolverInfo.isSolved());
	}

	@Test
	public void testSetIndexMinWithIndexJustRightToSolve() throws Exception {
		int inputValueArray = 5;
		int indexMaxInitial = 8;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray, PuzzleFieldStatusValue.BLACK, 0, indexMaxInitial);

		inputValueSolverInfo.setIndexMin(indexMaxInitial - inputValueArray + 1);

		Assert.assertTrue(inputValueSolverInfo.isSolved());
	}

	//--------------------------------------------------------
	// method setIndexMin and setIndexMax combined
	//--------------------------------------------------------

	//TODO: test methods for combinations of indexMin and indexMas that will lead to solved/exception states

	//--------------------------------------------------------
	// method addSolvedValue
	//--------------------------------------------------------


	@Test
	public void testAddSolvedValueInsideIndexMinMax() throws Exception {
		int inputValueArray = 5;
		int indexMinInput = 2;
		int indexMaxInput = 8;
		int solvedValue = 6;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray, PuzzleFieldStatusValue.BLACK, indexMinInput, indexMaxInput);

		inputValueSolverInfo.addSolvedValue(solvedValue);

		Assert.assertFalse(inputValueSolverInfo.isSolved());
	}

	@Test(expected = PuzzleSolverException.class)
	public void testAddSolvedValueLargerThanMax() throws Exception {
		int inputValueArray = 5;
		int indexMinInput = 2;
		int indexMaxInput = 8;
		int solvedValue = 9;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray, PuzzleFieldStatusValue.BLACK, indexMinInput, indexMaxInput);

		inputValueSolverInfo.addSolvedValue(solvedValue);
	}

	@Test(expected = PuzzleSolverException.class)
	public void testAddSolvedValueSmallerThanMin() throws Exception {
		int inputValueArray = 5;
		int indexMinInput = 2;
		int indexMaxInput = 8;
		int solvedValue = 1;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray, PuzzleFieldStatusValue.BLACK, indexMinInput, indexMaxInput);

		inputValueSolverInfo.addSolvedValue(solvedValue);
	}

	@Test(expected = PuzzleSolverException.class)
	public void testAddSolvedValueSmallerThan0() throws Exception {
		int inputValueArray = 5;
		int indexMinInput = 2;
		int indexMaxInput = 8;
		int solvedValue = -1;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray, PuzzleFieldStatusValue.BLACK, indexMinInput, indexMaxInput);

		inputValueSolverInfo.addSolvedValue(solvedValue);
	}

	@Test
	public void testAddSolvedValueChangesIndexMinAndIndexMax() throws Exception {
		int inputValueArray = 2;
		int indexMinInput = 2;
		int indexMaxInput = 8;
		int solvedValue = 5;
		int indexMinOutput = 4;
		int indexMaxOutput = 6;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray, PuzzleFieldStatusValue.BLACK, indexMinInput, indexMaxInput);

		inputValueSolverInfo.addSolvedValue(solvedValue);

		Assert.assertFalse(inputValueSolverInfo.isSolved());
		Assert.assertEquals(indexMinOutput, inputValueSolverInfo.getIndexMin());
		Assert.assertEquals(indexMaxOutput, inputValueSolverInfo.getIndexMax());
	}

	@Test
	public void testAddSolvedValueChangesIndexMinAndIndexMaxAndAddsSolvedValues() throws Exception {
		int inputValueArray = 3;
		int indexMinInput = 2;
		int indexMaxInput = 8;
		int solvedValue = 3;
		int indexMinOutput = indexMinInput;
		int indexMaxOutput = 5;
		InputValueSolverRange solvedRangeExpected = new InputValueSolverRange(3, 4);
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray, PuzzleFieldStatusValue.BLACK, indexMinInput, indexMaxInput);

		inputValueSolverInfo.addSolvedValue(solvedValue);

		Assert.assertFalse(inputValueSolverInfo.isSolved());
		Assert.assertEquals(indexMinOutput, inputValueSolverInfo.getIndexMin());
		Assert.assertEquals(indexMaxOutput, inputValueSolverInfo.getIndexMax());
		InputValueSolverRange solvedRangeResult = inputValueSolverInfo.getFullSolvedRangeCopy();
		assertEquals(solvedRangeExpected, solvedRangeResult);
	}

	//-----------------------------------
	// helper methods
	//-----------------------------------

	private void assertEquals(InputValueSolverRange expected, InputValueSolverRange actual) {
		if (expected != null) {
			Assert.assertNotNull(actual);
			Assert.assertEquals(expected.getRangeInt(), actual.getRangeInt());
			Assert.assertEquals(expected.getSolvedMin(), actual.getSolvedMin());
			Assert.assertEquals(expected.getSolvedMax(), actual.getSolvedMax());
		} else {
			Assert.assertNull(actual);
		}
	}
}
