package janvenstermans.solver;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class PuzzleSolverUtilTest {

	//--------------------------------------------------------
	// method checkCountOfArray
	//--------------------------------------------------------

	@Test
	public void testCheckCountOfArray() throws Exception {
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
