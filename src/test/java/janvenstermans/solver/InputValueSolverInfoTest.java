package janvenstermans.solver;

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

	@Test(expected=PuzzleSolverException.class)
	public void testSetIndexMaxWithIndexTooLowForValue() throws Exception {
		int inputValueArray = 5;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray);

		inputValueSolverInfo.setIndexMax(inputValueArray - 2);
	}

	@Test
	public void testSetIndexMaxWithIndexJustRightToSolve() throws Exception {
		int inputValueArray = 5;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValueArray);

		inputValueSolverInfo.setIndexMax(inputValueArray - 1);

		Assert.assertTrue(inputValueSolverInfo.isSolved());
	}

	//--------------------------------------------------------
	// method setIndexMin and setIndexMax combined
	//--------------------------------------------------------

	//TODO: test methods for combinations of indexMin and indexMas that will lead to solved/exception states
}
