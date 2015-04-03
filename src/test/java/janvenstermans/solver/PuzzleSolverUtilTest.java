package janvenstermans.solver;

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
		int[] inputArrayInput = new int[] {  };
		boolean[] statusArrayInput  = new boolean[dimensionInput]; // nothing solved yet
		PuzzleSolverUtil.VALUE[] valueArrayInput  = new PuzzleSolverUtil.VALUE[dimensionInput]; // nothing solved yet
		// create expected values
		boolean[] statusArrayExpected  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayExpected  = new PuzzleSolverUtil.VALUE[dimensionInput];
		for (int j = 0 ; j < dimensionInput; j++) {
			statusArrayExpected[j] = true;
			valueArrayExpected[j] = PuzzleSolverUtil.VALUE.NONE;
		}

		ArrayResult arrayResult = PuzzleSolverUtil.checkCountOfArray(inputArrayInput, statusArrayInput, valueArrayInput);

		assertStatusArray(statusArrayExpected, arrayResult.getStatusArray());
		assertValueArray(valueArrayExpected, arrayResult.getValueArray());
	}

	@Test
	public void testCheckCountOfArrayFullInput() throws Exception {
		int dimensionInput = 10;
		int[] inputArrayInput = new int[] { 10 };
		boolean[] statusArrayInput  = new boolean[dimensionInput]; // nothing solved yet
		PuzzleSolverUtil.VALUE[] valueArrayInput  = new PuzzleSolverUtil.VALUE[dimensionInput]; // nothing solved yet
		// create expected values
		boolean[] statusArrayExpected  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayExpected  = new PuzzleSolverUtil.VALUE[dimensionInput];
		for (int j = 0 ; j < dimensionInput; j++) {
			statusArrayExpected[j] = true;
			valueArrayExpected[j] = PuzzleSolverUtil.VALUE.BLACK;
		}

		ArrayResult arrayResult = PuzzleSolverUtil.checkCountOfArray(inputArrayInput, statusArrayInput, valueArrayInput);

		assertStatusArray(statusArrayExpected, arrayResult.getStatusArray());
		assertValueArray(valueArrayExpected, arrayResult.getValueArray());
	}

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
		for (int j = 0 ; j <= 2; j++) {
			statusArrayExpected[j] = true;
			valueArrayExpected[j] = PuzzleSolverUtil.VALUE.NONE;
		}
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

	// method checkCountOfArray for filling up empty elements
	@Test
	public void testCheckCountOfArrayWithSolvedElements3() throws Exception {
		int dimensionInput = 10;
		int[] inputArrayInput = new int[] { 1,1,1,2 };
		boolean[] statusArrayInput  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayInput  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		statusArrayInput[5] = true;
		valueArrayInput[5] = PuzzleSolverUtil.VALUE.NONE;
		statusArrayInput[9] = true;
		valueArrayInput[9] = PuzzleSolverUtil.VALUE.NONE;
		// create expected values
		boolean[] statusArrayExpected  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayExpected  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		statusArrayExpected[0] = true;
		valueArrayExpected[0] = PuzzleSolverUtil.VALUE.BLACK;
		statusArrayExpected[1] = true;
		valueArrayExpected[1] = PuzzleSolverUtil.VALUE.NONE;
		statusArrayExpected[2] = true;
		valueArrayExpected[2] = PuzzleSolverUtil.VALUE.BLACK;
		statusArrayExpected[3] = true;
		valueArrayExpected[3] = PuzzleSolverUtil.VALUE.NONE;
		statusArrayExpected[4] = true;
		valueArrayExpected[4] = PuzzleSolverUtil.VALUE.BLACK;
		statusArrayExpected[5] = true;
		valueArrayExpected[5] = PuzzleSolverUtil.VALUE.NONE;
		statusArrayExpected[7] = true;
		valueArrayExpected[7] = PuzzleSolverUtil.VALUE.BLACK;
		statusArrayExpected[9] = true;
		valueArrayExpected[9] = PuzzleSolverUtil.VALUE.NONE;

		ArrayResult arrayResult = PuzzleSolverUtil.checkCountOfArray(inputArrayInput, statusArrayInput, valueArrayInput);

		assertStatusArray(statusArrayExpected, arrayResult.getStatusArray());
		assertValueArray(valueArrayExpected, arrayResult.getValueArray());
	}

	@Test
	public void testCheckCountOfArrayWithAllSolvedElements3() throws Exception {
		int dimensionInput = 10;
		int[] inputArrayInput = new int[] { 1,2,1,3 };
		boolean[] statusArrayInput  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayInput  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// create expected values
		boolean[] statusArrayExpected  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayExpected  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		statusArrayExpected[0] = true;
		valueArrayExpected[0] = PuzzleSolverUtil.VALUE.BLACK;
		statusArrayExpected[1] = true;
		valueArrayExpected[1] = PuzzleSolverUtil.VALUE.NONE;
		statusArrayExpected[2] = true;
		valueArrayExpected[2] = PuzzleSolverUtil.VALUE.BLACK;
		statusArrayExpected[3] = true;
		valueArrayExpected[3] = PuzzleSolverUtil.VALUE.BLACK;
		statusArrayExpected[4] = true;
		valueArrayExpected[4] = PuzzleSolverUtil.VALUE.NONE;
		statusArrayExpected[5] = true;
		valueArrayExpected[5] = PuzzleSolverUtil.VALUE.BLACK;
		statusArrayExpected[6] = true;
		valueArrayExpected[6] = PuzzleSolverUtil.VALUE.NONE;
		statusArrayExpected[7] = true;
		valueArrayExpected[7] = PuzzleSolverUtil.VALUE.BLACK;
		statusArrayExpected[8] = true;
		valueArrayExpected[8] = PuzzleSolverUtil.VALUE.BLACK;
		statusArrayExpected[9] = true;
		valueArrayExpected[9] = PuzzleSolverUtil.VALUE.BLACK;

		ArrayResult arrayResult = PuzzleSolverUtil.checkCountOfArray(inputArrayInput, statusArrayInput, valueArrayInput);

		assertStatusArray(statusArrayExpected, arrayResult.getStatusArray());
		assertValueArray(valueArrayExpected, arrayResult.getValueArray());
	}

	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArrayToAddNoneValues1() throws Exception {
		int dimensionInput = 10;
		int[] inputArrayInput = new int[] { 2,2 };
		boolean[] statusArrayInput  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayInput  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		statusArrayInput[1] = true;
		valueArrayInput[1] = PuzzleSolverUtil.VALUE.BLACK;
		statusArrayInput[7] = true;
		valueArrayInput[7] = PuzzleSolverUtil.VALUE.BLACK;
		// create expected values
		boolean[] statusArrayExpected  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayExpected  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		statusArrayExpected[1] = true;
		valueArrayExpected[1] = PuzzleSolverUtil.VALUE.BLACK;
		for (int j = 3 ; j <= 5; j++) {
			statusArrayExpected[j] = true;
			valueArrayExpected[j] = PuzzleSolverUtil.VALUE.NONE;
		}
		statusArrayExpected[7] = true;
		valueArrayExpected[7] = PuzzleSolverUtil.VALUE.BLACK;
		statusArrayExpected[9] = true;
		valueArrayExpected[9] = PuzzleSolverUtil.VALUE.NONE;

		ArrayResult arrayResult = PuzzleSolverUtil.checkCountOfArray(inputArrayInput, statusArrayInput, valueArrayInput);

		assertStatusArray(statusArrayExpected, arrayResult.getStatusArray());
		assertValueArray(valueArrayExpected, arrayResult.getValueArray());
	}

	@Test
	public void testCheckCountOfArrayToAddNoneValues2() throws Exception {
		int dimensionInput = 10;
		int[] inputArrayInput = new int[] { 3, 1 };
		boolean[] statusArrayInput  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayInput  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		for (int j = 3 ; j <= 5; j++) {
			statusArrayInput[j] = true;
			valueArrayInput[j] = PuzzleSolverUtil.VALUE.BLACK;
		}
		// create expected values
		boolean[] statusArrayExpected  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayExpected  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		for (int j = 0 ; j <= 2; j++) {
			statusArrayExpected[j] = true;
			valueArrayExpected[j] = PuzzleSolverUtil.VALUE.NONE;
		}
		for (int j = 3 ; j <= 5; j++) {
			statusArrayExpected[j] = true;
			valueArrayExpected[j] = PuzzleSolverUtil.VALUE.BLACK;
		}

		ArrayResult arrayResult = PuzzleSolverUtil.checkCountOfArray(inputArrayInput, statusArrayInput, valueArrayInput);

		assertStatusArray(statusArrayExpected, arrayResult.getStatusArray());
		assertValueArray(valueArrayExpected, arrayResult.getValueArray());
	}

	@Test
	public void testCheckCountOfArrayToAddNoneValues3() throws Exception {
		int dimensionInput = 10;
		int[] inputArrayInput = new int[] { 1, 3 };
		boolean[] statusArrayInput  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayInput  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		for (int j = 4 ; j <= 6; j++) {
			statusArrayInput[j] = true;
			valueArrayInput[j] = PuzzleSolverUtil.VALUE.BLACK;
		}
		// create expected values
		boolean[] statusArrayExpected  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayExpected  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		for (int j = 4 ; j <= 6; j++) {
			statusArrayExpected[j] = true;
			valueArrayExpected[j] = PuzzleSolverUtil.VALUE.BLACK;
		}
		for (int j = 7 ; j <= 9; j++) {
			statusArrayExpected[j] = true;
			valueArrayExpected[j] = PuzzleSolverUtil.VALUE.NONE;
		}

		ArrayResult arrayResult = PuzzleSolverUtil.checkCountOfArray(inputArrayInput, statusArrayInput, valueArrayInput);

		assertStatusArray(statusArrayExpected, arrayResult.getStatusArray());
		assertValueArray(valueArrayExpected, arrayResult.getValueArray());
	}

	//--------------------------------------------------------
	// method checkCountOfArray all can be solved
	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArrayAllCanBeSolvedAllBlacksAccounted() throws Exception {
		int dimensionInput = 10;
		int[] inputArrayInput = new int[] { 1, 1, 1 };
		boolean[] statusArrayInput  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayInput  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		statusArrayInput[2] = true;
		valueArrayInput[2] = PuzzleSolverUtil.VALUE.BLACK;
		statusArrayInput[4] = true;
		valueArrayInput[4] = PuzzleSolverUtil.VALUE.BLACK;
		statusArrayInput[6] = true;
		valueArrayInput[6] = PuzzleSolverUtil.VALUE.BLACK;
		// create expected values
		boolean[] statusArrayExpected  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayExpected  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		for (int i = 0 ; i < dimensionInput; i++) {
			statusArrayExpected[i] = true;
			valueArrayExpected[i] = PuzzleSolverUtil.VALUE.NONE;
		}
		valueArrayExpected[2] = PuzzleSolverUtil.VALUE.BLACK;
		valueArrayExpected[4] = PuzzleSolverUtil.VALUE.BLACK;
		valueArrayExpected[6] = PuzzleSolverUtil.VALUE.BLACK;

		ArrayResult arrayResult = PuzzleSolverUtil.checkCountOfArray(inputArrayInput, statusArrayInput, valueArrayInput);

		assertStatusArray(statusArrayExpected, arrayResult.getStatusArray());
		assertValueArray(valueArrayExpected, arrayResult.getValueArray());
	}

	@Test
	public void testCheckCountOfArrayAllCanBeSolvedAllNonesAccounted() throws Exception {
		int dimensionInput = 10;
		int[] inputArrayInput = new int[] { 1, 1, 1 };
		boolean[] statusArrayInput  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayInput  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		for (int i = 0 ; i < dimensionInput; i++) {
			statusArrayInput[i] = true;
			valueArrayInput[i] = PuzzleSolverUtil.VALUE.NONE;
		}
		statusArrayInput[2] = false;
		valueArrayInput[2] = null;
		statusArrayInput[4] = false;
		valueArrayInput[4] = null;
		statusArrayInput[6] = false;
		valueArrayInput[6] = null;
		// create expected values
		boolean[] statusArrayExpected  = new boolean[dimensionInput];
		PuzzleSolverUtil.VALUE[] valueArrayExpected  = new PuzzleSolverUtil.VALUE[dimensionInput];
		// solved values
		for (int i = 0 ; i < dimensionInput; i++) {
			statusArrayExpected[i] = true;
			valueArrayExpected[i] = PuzzleSolverUtil.VALUE.NONE;
		}
		valueArrayExpected[2] = PuzzleSolverUtil.VALUE.BLACK;
		valueArrayExpected[4] = PuzzleSolverUtil.VALUE.BLACK;
		valueArrayExpected[6] = PuzzleSolverUtil.VALUE.BLACK;

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
