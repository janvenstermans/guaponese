package janvenstermans.solver;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class PuzzleSolverUtilTest {

	//--------------------------------------------------------
	// method checkCountOfArray for empty array
	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArrayOneNumber() throws Exception {
		int dimensionInput = 10;
		int[] inputArrayInput = new int[] { 7 };
		boolean[] statusArrayInput  = new boolean[dimensionInput]; // nothing solved yet
		PuzzleSolverUtil.VALUE[] valueArrayInput  = new PuzzleSolverUtil.VALUE[dimensionInput]; // nothing solved yet
		// create expected values
		boolean[] statusArrayExpected  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayExpected  = new PuzzleSolverUtil.VALUE[dimensionInput];
		for (int j = 3 ; j < 7; j++) {
			statusArrayExpected[j] = true;
			valueArrayExpected[j] = PuzzleSolverUtil.VALUE.BLACK;
		}

		ArrayResult arrayResult = PuzzleSolverUtil.checkCountOfArray(inputArrayInput, statusArrayInput, valueArrayInput);

		assertStatusArray(statusArrayExpected, arrayResult.getStatusArray());
		assertValueArray(valueArrayExpected, arrayResult.getValueArray());
	}

	@Test
	public void testCheckCountOfArrayNoNumber() throws Exception {
		int dimensionInput = 10;
		int[] inputArrayInput = new int[]{};
		boolean[] statusArrayInput = new boolean[dimensionInput]; // nothing solved yet
		PuzzleSolverUtil.VALUE[] valueArrayInput = new PuzzleSolverUtil.VALUE[dimensionInput]; // nothing solved yet
		// create expected values
		boolean[] statusArrayExpected = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayExpected = new PuzzleSolverUtil.VALUE[dimensionInput];
		Arrays.fill(valueArrayExpected, PuzzleSolverUtil.VALUE.NONE);
		Arrays.fill(statusArrayExpected, true);

		ArrayResult arrayResult = PuzzleSolverUtil.checkCountOfArray(inputArrayInput, statusArrayInput, valueArrayInput);

		assertStatusArray(statusArrayExpected, arrayResult.getStatusArray());
	}


	@Test
	public void testCheckCountOfArrayTwoNumbers() throws Exception {
		int dimensionInput = 10;
		int[] inputArrayInput = new int[] { 5, 3 };
		boolean[] statusArrayInput  = new boolean[dimensionInput]; // nothing solved yet
		PuzzleSolverUtil.VALUE[] valueArrayInput  = new PuzzleSolverUtil.VALUE[dimensionInput]; // nothing solved yet
		// create expected values
		boolean[] statusArrayExpected  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayExpected  = new PuzzleSolverUtil.VALUE[dimensionInput];
		for (int j = 1 ; j < 5; j++) {
			statusArrayExpected[j] = true;
			valueArrayExpected[j] = PuzzleSolverUtil.VALUE.BLACK;
		}
		for (int j = 7 ; j < 9; j++) {
			statusArrayExpected[j] = true;
			valueArrayExpected[j] = PuzzleSolverUtil.VALUE.BLACK;
		}

		ArrayResult arrayResult = PuzzleSolverUtil.checkCountOfArray(inputArrayInput, statusArrayInput, valueArrayInput);

		assertStatusArray(statusArrayExpected, arrayResult.getStatusArray());
		assertValueArray(valueArrayExpected, arrayResult.getValueArray());
	}

	//--------------------------------------------------------
	// method checkCountOfArray for filled array
	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArrayWithSolvedElements1() throws Exception {
		int dimensionInput = 10;
		int[] inputArrayInput = new int[] { 5 };
		boolean[] statusArrayInput  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayInput  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		statusArrayInput[2] = true;
		valueArrayInput[2] = PuzzleSolverUtil.VALUE.NONE;
		// create expected values
		boolean[] statusArrayExpected  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayExpected  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		statusArrayExpected[2] = true;
		valueArrayExpected[2] = PuzzleSolverUtil.VALUE.NONE;
		for (int j = 5 ; j <= 7; j++) {
			statusArrayExpected[j] = true;
			valueArrayExpected[j] = PuzzleSolverUtil.VALUE.BLACK;
		}

		ArrayResult arrayResult = PuzzleSolverUtil.checkCountOfArray(inputArrayInput, statusArrayInput, valueArrayInput);

		assertStatusArray(statusArrayExpected, arrayResult.getStatusArray());
		assertValueArray(valueArrayExpected, arrayResult.getValueArray());
	}

	@Test
	public void testCheckCountOfArrayWithSolvedElements2() throws Exception {
		int dimensionInput = 10;
		int[] inputArrayInput = new int[] { 5 };
		boolean[] statusArrayInput  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayInput  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		statusArrayInput[6] = true;
		valueArrayInput[6] = PuzzleSolverUtil.VALUE.NONE;
		// create expected values
		boolean[] statusArrayExpected  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayExpected  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		statusArrayExpected[6] = true;
		valueArrayExpected[6] = PuzzleSolverUtil.VALUE.NONE;
		for (int j = 1 ; j <= 4; j++) {
			statusArrayExpected[j] = true;
			valueArrayExpected[j] = PuzzleSolverUtil.VALUE.BLACK;
		}

		ArrayResult arrayResult = PuzzleSolverUtil.checkCountOfArray(inputArrayInput, statusArrayInput, valueArrayInput);

		assertStatusArray(statusArrayExpected, arrayResult.getStatusArray());
		assertValueArray(valueArrayExpected, arrayResult.getValueArray());
	}

	//--------------------------------------------------------
	// private method
	//--------------------------------------------------------

	private void assertStatusArray(boolean[] expected, boolean[] result) {
		if (expected != null) {
			Assert.assertNotNull(result);
			Assert.assertEquals(expected.length, result.length);
			for (int i = 0; i < expected.length; i++) {
				Assert.assertEquals(expected[i], result[i]);
			}
		} else {
			Assert.assertNull(result);
		}
	}

	private void assertValueArray(PuzzleSolverUtil.VALUE[] expected, PuzzleSolverUtil.VALUE[] result) {
		if (expected != null) {
			Assert.assertNotNull(result);
			Assert.assertEquals(expected.length, result.length);
			for (int i = 0; i < expected.length; i++) {
				Assert.assertEquals(expected[i], result[i]);
			}
		} else {
			Assert.assertNull(result);
		}
	}
}
