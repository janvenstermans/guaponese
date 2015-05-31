package janvenstermans.solver;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Unit test for {@link InputValueSolverInfo} class.
 */
public class InputValueSolverInfoTest {

	@Test
	public void testConstructedIsUnsolved() throws Exception {
		int inputValue = 10;
		InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputValue);

		Assert.assertFalse(inputValueSolverInfo.isSolved());
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

}
