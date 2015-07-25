package janvenstermans.guaponese.solver;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Unit test for {@link InputValueSolverInfo} class.
 */
public class InputValueSolverInfoTest {

	@Test
	public void testConstructorValues() throws Exception {
		int inputValue = 10;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValue);

		Assert.assertFalse(inputValueSolverInfo.isSolved());
		Assert.assertNull(inputValueSolverInfo.getIndexMin());
		Assert.assertNull(inputValueSolverInfo.getIndexMax());
	}

	//--------------------------------------------------------
	// method addSolvedValue
	//--------------------------------------------------------

	@Test
	public void testAddSolvedValueForLessAmountOfInputIsUnsolved() throws Exception {
		int[] inputValueArray = { 1, 8, 4, 13};
		InputValueSolverInfo[] inputValueSolverInfoArray = new InputValueSolverInfo[inputValueArray.length];
		for (int i = 0; i < inputValueArray.length ; i++) {
			inputValueSolverInfoArray[i] = new InputValueSolverInfo(inputValueArray[i]);
		}
		// add distinct values for the amount of each inputValue - 1
		for (int i = 0; i < inputValueArray.length; i++) {
			int inputValue = inputValueArray[i];
			InputValueSolverInfo inputValueSolverInfo = inputValueSolverInfoArray[i];
			for (int j = 0; j < inputValue - 1 ; j++) {
				inputValueSolverInfo.addSolvedValue(j);
			}
		}

		for (int i = 0; i < inputValueArray.length ; i++) {
			Assert.assertFalse(inputValueSolverInfoArray[i].isSolved());
		}
	}

	@Test
	public void testAddSolvedValueForAmountOfInputIsSolved() throws Exception {
		int[] inputValueArray = { 1, 8, 4, 13};
		InputValueSolverInfo[] inputValueSolverInfoArray = new InputValueSolverInfo[inputValueArray.length];
		for (int i = 0; i < inputValueArray.length ; i++) {
			inputValueSolverInfoArray[i] = new InputValueSolverInfo(inputValueArray[i]);
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
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray);
		inputValueSolverInfo.setIndexMin(indexMinFirst);

		inputValueSolverInfo.setIndexMin(indexMinSecond);

		Assert.assertNotNull(inputValueSolverInfo.getIndexMin());
		Assert.assertEquals(indexMinFirst, inputValueSolverInfo.getIndexMin().intValue());
	}

	@Test
	public void testSetIndexMinWithHigherValueChangesIndexMin() throws Exception {
		int inputValueArray = 2;
		int indexMinFirst = 3;
		int indexMinSecond = 5;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray);
		inputValueSolverInfo.setIndexMin(indexMinFirst);

		inputValueSolverInfo.setIndexMin(indexMinSecond);

		Assert.assertNotNull(inputValueSolverInfo.getIndexMin());
		Assert.assertEquals(indexMinSecond, inputValueSolverInfo.getIndexMin().intValue());
	}

	//--------------------------------------------------------
	// method setIndexMax
	//--------------------------------------------------------

	@Test
	public void testSetIndexMaxWithHigherValueDoesNotChangeIndexMax() throws Exception {
		int inputValueArray = 2;
		int indexMaxFirst = 3;
		int indexMaxSecond = 5;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray);
		inputValueSolverInfo.setIndexMax(indexMaxFirst);

		inputValueSolverInfo.setIndexMax(indexMaxSecond);

		Assert.assertNotNull(inputValueSolverInfo.getIndexMax());
		Assert.assertEquals(indexMaxFirst, inputValueSolverInfo.getIndexMax().intValue());
	}

	@Test
	public void testSetIndexMaxWithLowerValueChangesIndexMax() throws Exception {
		int inputValueArray = 2;
		int indexMaxFirst = 5;
		int indexMaxSecond = 3;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray);
		inputValueSolverInfo.setIndexMax(indexMaxFirst);

		inputValueSolverInfo.setIndexMax(indexMaxSecond);

		Assert.assertNotNull(inputValueSolverInfo.getIndexMax());
		Assert.assertEquals(indexMaxSecond, inputValueSolverInfo.getIndexMax().intValue());
	}

	@Test
	public void testSetIndexMaxWithIndexJustRightToSolve() throws Exception {
		int inputValueArray = 5;
		int indexMinInitial = 2;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray);
		inputValueSolverInfo.setIndexMin(indexMinInitial);

		inputValueSolverInfo.setIndexMax(indexMinInitial + inputValueArray - 1);

		Assert.assertTrue(inputValueSolverInfo.isSolved());
	}

	@Test
	public void testSetIndexMinWithIndexJustRightToSolve() throws Exception {
		int inputValueArray = 5;
		int indexMaxInitial = 8;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray);
		inputValueSolverInfo.setIndexMax(indexMaxInitial);

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
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray);
		inputValueSolverInfo.setIndexMin(indexMinInput);
		inputValueSolverInfo.setIndexMax(indexMaxInput);

		inputValueSolverInfo.addSolvedValue(solvedValue);

		Assert.assertFalse(inputValueSolverInfo.isSolved());
	}

	@Test(expected = PuzzleSolverException.class)
	public void testAddSolvedValueLargerThanMax() throws Exception {
		int inputValueArray = 5;
		int indexMinInput = 2;
		int indexMaxInput = 8;
		int solvedValue = 9;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray);
		inputValueSolverInfo.setIndexMin(indexMinInput);
		inputValueSolverInfo.setIndexMax(indexMaxInput);

		inputValueSolverInfo.addSolvedValue(solvedValue);
	}

	@Test(expected = PuzzleSolverException.class)
	public void testAddSolvedValueSmallerThanMin() throws Exception {
		int inputValueArray = 5;
		int indexMinInput = 2;
		int indexMaxInput = 8;
		int solvedValue = 1;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray);
		inputValueSolverInfo.setIndexMin(indexMinInput);
		inputValueSolverInfo.setIndexMax(indexMaxInput);

		inputValueSolverInfo.addSolvedValue(solvedValue);
	}

	@Test(expected = PuzzleSolverException.class)
	public void testAddSolvedValueSmallerThan0() throws Exception {
		int inputValueArray = 5;
		int indexMinInput = 2;
		int indexMaxInput = 8;
		int solvedValue = -1;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray);
		inputValueSolverInfo.setIndexMin(indexMinInput);
		inputValueSolverInfo.setIndexMax(indexMaxInput);

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
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray);
		inputValueSolverInfo.setIndexMin(indexMinInput);
		inputValueSolverInfo.setIndexMax(indexMaxInput);

		inputValueSolverInfo.addSolvedValue(solvedValue);

		Assert.assertFalse(inputValueSolverInfo.isSolved());
		Assert.assertEquals(indexMinOutput, inputValueSolverInfo.getIndexMin().intValue());
		Assert.assertEquals(indexMaxOutput, inputValueSolverInfo.getIndexMax().intValue());
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
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray);
		inputValueSolverInfo.setIndexMin(indexMinInput);
		inputValueSolverInfo.setIndexMax(indexMaxInput);

		inputValueSolverInfo.addSolvedValue(solvedValue);

		Assert.assertFalse(inputValueSolverInfo.isSolved());
		Assert.assertEquals(indexMinOutput, inputValueSolverInfo.getIndexMin().intValue());
		Assert.assertEquals(indexMaxOutput, inputValueSolverInfo.getIndexMax().intValue());
		InputValueSolverRange solvedRangeResult = inputValueSolverInfo.getSolvedRangeCopy();
		assertEquals(solvedRangeExpected, solvedRangeResult);
	}

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
