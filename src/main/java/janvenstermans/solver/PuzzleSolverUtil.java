package janvenstermans.solver;

import janvenstermans.model.PuzzleInput;
import janvenstermans.model.PuzzleStatus;

import java.util.Arrays;

/**
 * Contains solution tactics.
 *
 * @author Jan Venstermans
 *
 */
public class PuzzleSolverUtil {
	public enum Dimension {
		X, Y;
	}
	public enum VALUE {
		NONE, BLACK;
	}

	public static void checkCount(PuzzleStatus puzzleStatus, PuzzleInput puzzleInput) {

	}

	public static ArrayResult checkCountOfArray(int[] inputArray, boolean[] statusArray, VALUE[] valueArray) {
		// start with existing values
		boolean[] statusArrayResult = Arrays.copyOf(statusArray, statusArray.length);
		VALUE[] valueArrayResult = Arrays.copyOf(valueArray, statusArray.length);

		int sum = sumOfInput(inputArray);
		int[][] temp = new int[inputArray.length][];
		int countUp = -1;
		for (int k = 0 ; k < inputArray.length; k++) {
			int value = inputArray[k];
			if (k != 0) {
				countUp += value + 1;
			} else {
				countUp = value - 1;
			}
			temp[k] = new int[] {countUp, -1};
		}
		int countDown = -1;
		for (int m = inputArray.length - 1 ; m >= 0 ; m--) {
			int value = inputArray[m];
			if (m != 0) {
				countDown -= (value + 1);
			} else {
				countDown = statusArray.length - value;
			}

			temp[m][1] = countDown;
		}
		for (int z = 0 ; z < temp.length; z++) {
			int[] values = temp[z];
			int val0 = values[0];
			int val1 = values[1];
			if (val0 >= val1) {
				for (int p = val1; p <= val0; p++) {
					statusArrayResult[p] = true;
					valueArrayResult[p] = VALUE.BLACK;
				}
			}
		}

		return new ArrayResult(statusArrayResult, valueArrayResult);
	}

	private static int sumOfInput(int[] inputArray) {
		int sum = 0;
		for (int value : inputArray) {
			sum += value;
		}
		return sum;
	}
}
