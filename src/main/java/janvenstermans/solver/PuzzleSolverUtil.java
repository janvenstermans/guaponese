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
		// first x, than y
		for (int column = 0; column < puzzleInput.getDimensionX(); column++) {
			try {
				ArrayResult arrayResult = checkCountOfArray(puzzleInput.getInputXOfColumn(column),
						puzzleStatus.getStatusOfColumn(column), puzzleStatus.geValueOfColumn(column));
				for (int i = 0 ; i < arrayResult.getStatusArray().length ; i++) {
					if (arrayResult.getStatusArray()[i]) {
						puzzleStatus.setFieldStatusAndValue(column, i, true, arrayResult.getValueArray()[i]);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (int row = 0; row < puzzleInput.getDimensionY(); row++) {
			try {
				ArrayResult arrayResult = checkCountOfArray(puzzleInput.getInputYOfRow(row),
						puzzleStatus.getStatusYOfRow(row), puzzleStatus.getValueYOfRow(row));
				for (int i = 0 ; i < arrayResult.getStatusArray().length ; i++) {
					if (arrayResult.getStatusArray()[i]) {
						puzzleStatus.setFieldStatusAndValue(i, row, true, arrayResult.getValueArray()[i]);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static ArrayResult checkCountOfArray(int[] inputArray, boolean[] statusArray, VALUE[] valueArray) throws Exception {
		// start with existing values
		boolean[] statusArrayResult = Arrays.copyOf(statusArray, statusArray.length);
		VALUE[] valueArrayResult = Arrays.copyOf(valueArray, statusArray.length);

		int sum = sumOfInput(inputArray);
		if (sum > 0) {
			Integer[][] temp = new Integer[inputArray.length][];
			for (int k = 0; k < inputArray.length; k++) {
				temp[k] = new Integer[2];
			}

			int countUp = 0;
			for (int k = 0; k < inputArray.length; k++) {
				int value = inputArray[k];
				int countUpStart = countUp;
				int countUpEnd = countUpStart + value - 1;
				Integer lastSolvedWithStatusNone =
						getLastSolvedNoneElement(statusArrayResult,  valueArrayResult, countUpStart, countUpEnd);
				while (lastSolvedWithStatusNone != null) {
					countUpStart = lastSolvedWithStatusNone + 1;
					countUpEnd = countUpStart + value - 1;
					lastSolvedWithStatusNone =
							getLastSolvedNoneElement(statusArrayResult,  valueArrayResult, countUpStart, countUpEnd);
				}
				temp[k][0] = countUpEnd;
				countUp = countUpEnd + 2;
			}
			int countDown = statusArray.length - 1;
			for (int m = inputArray.length - 1; m >= 0; m--) {
				int value = inputArray[m];
				int countDownStart = countDown;
				int countDownEnd = countDownStart - (value - 1);
				Integer firstSolvedNoneElement =
						getFirstSolvedNoneElement(statusArrayResult,  valueArrayResult, countDownEnd, countDownStart);
				while (firstSolvedNoneElement != null) {
					countDownStart = firstSolvedNoneElement - 1;
					countDownEnd = countDownStart - (value - 1);
					firstSolvedNoneElement =
							getFirstSolvedNoneElement(statusArrayResult,  valueArrayResult, countDownEnd, countDownStart);
				}
				temp[m][1] = countDownEnd;
				countDown = countDownEnd - 2;
			}
			for (int z = 0; z < temp.length; z++) {
				Integer[] values = temp[z];
				int val0 = values[0];
				int val1 = values[1];
				if (val0 >= val1) {
					for (int p = val1; p <= val0; p++) {
						if (!statusArrayResult[p]) {
							statusArrayResult[p] = true;
							valueArrayResult[p] = VALUE.BLACK;
						} else if (!VALUE.BLACK.equals(valueArrayResult[p])) {
							throw new Exception("Conflict in status of a cell");
						}
					}
				}
			}
		} else {
			Arrays.fill(statusArrayResult, true);
			Arrays.fill(valueArrayResult, VALUE.NONE);
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

	private static Integer getFirstSolvedNoneElement(boolean[] statusArray, VALUE[] valueArray, int start, int end) {
		if (statusArray.length > 0 && valueArray.length > 0 && statusArray.length == valueArray.length
				&& start < statusArray.length && end < statusArray.length) {
			for (int i = start; i <= end; i++) {
				if (statusArray[i] && VALUE.NONE.equals(valueArray[i])){
					return i;
				}
			}
		}
		return null;
	}

	private static Integer getLastSolvedNoneElement(boolean[] statusArray, VALUE[] valueArray, int start, int end) {
		if (statusArray.length > 0 && valueArray.length > 0 && statusArray.length == valueArray.length
				&& start < statusArray.length && end < statusArray.length) {
			for (int i = end; i >= start; i--) {
				if (statusArray[i] && VALUE.NONE.equals(valueArray[i])){
					return i;
				}
			}
		}
		return null;
	}
}
