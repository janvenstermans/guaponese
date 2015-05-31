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

	public static ArrayResult checkCountOfArray(int[] inputArray, boolean[] statusArray, VALUE[] valueArray)
			throws Exception {
		// start with existing values
		if (isAllSolved(statusArray)) {
			return new ArrayResult(statusArray, valueArray);
		}
		boolean[] statusArrayResult = Arrays.copyOf(statusArray, statusArray.length);
		VALUE[] valueArrayResult = Arrays.copyOf(valueArray, statusArray.length);
		int sum = sumOfInput(inputArray);
		if (sum > 0) {
			int solvedSum = sumOfSolved(valueArrayResult, statusArrayResult);
			// TODO: check for alls not-NONE values
			if (solvedSum == sum) {
				for (int i = 0; i < statusArrayResult.length; i++) {
					statusArrayResult[i] = true;
					if (valueArrayResult[i] == null) {
						valueArrayResult[i] = VALUE.NONE;
					}
				}
			} else {
				checkForBlackValues(inputArray, statusArrayResult, valueArrayResult);
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

	private static int sumOfSolved(VALUE[] valueArray, boolean[] statusArray) {
		int sum = 0;
		for (int i = 0 ; i < valueArray.length ; i++) {
			if (statusArray[i] && VALUE.BLACK.equals(valueArray[i])) {
				sum++;
			}
		}
		return sum;
	}

	private static boolean isAllSolved(boolean[] statusArray) {
		for (boolean status : statusArray) {
			if (!status) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Search for BLACK values.
	 *
	 * @param inputArray
	 * @param statusArrayResult
	 * @param valueArrayResult
	 * @throws Exception
	 */
	private static void checkForBlackValues(int[] inputArray, boolean[] statusArrayResult,
											VALUE[] valueArrayResult) throws Exception {
		// create InputValueSolverInfo objects
		// TODO: move this up, as part of the general solve strategy
		InputValueSolverInfo[] temp = new InputValueSolverInfo[inputArray.length];
		for (int k = 0; k < inputArray.length; k++) {
			temp[k] = new InputValueSolverInfo(inputArray[k]);
		}
		// go through the input values from lowest to highest
		int countUp = 0;
		for (InputValueSolverInfo inputValueSolverInfo : temp) {
			int countUpStart = countUp;
			int countUpEnd = countUpStart + inputValueSolverInfo.getInputValue() - 1;
			Integer lastSolvedWithStatusNone =
					getLastSolvedNoneElement(statusArrayResult,  valueArrayResult, countUpStart, countUpEnd);
			while (lastSolvedWithStatusNone != null) {
				countUpStart = lastSolvedWithStatusNone + 1;
				countUpEnd = countUpStart + inputValueSolverInfo.getInputValue() - 1;
				lastSolvedWithStatusNone =
						getLastSolvedNoneElement(statusArrayResult,  valueArrayResult, countUpStart, countUpEnd);
			}
			int numberOfBlackElementDirectlyAfterPosition = getNumberOfBlackElementDirectlyAfterPosition(
					statusArrayResult, valueArrayResult, countUpEnd);
			if (numberOfBlackElementDirectlyAfterPosition > inputValueSolverInfo.getInputValue()) {
			  	throw new Exception();
			}
			inputValueSolverInfo.setCountUp(countUpEnd + numberOfBlackElementDirectlyAfterPosition);
			countUp = countUpEnd + 2;
		}
		int countDown = statusArrayResult.length - 1;
		for (int m = temp.length - 1; m >= 0; m--) {
			InputValueSolverInfo inputValueSolverInfo = temp[m];
			int countDownStart = countDown;
			int countDownEnd = countDownStart - (inputValueSolverInfo.getInputValue() - 1);
			Integer firstSolvedNoneElement =
					getFirstSolvedNoneElement(statusArrayResult,  valueArrayResult, countDownEnd, countDownStart);
			while (firstSolvedNoneElement != null) {
				countDownStart = firstSolvedNoneElement - 1;
				countDownEnd = countDownStart - (inputValueSolverInfo.getInputValue() - 1);
				firstSolvedNoneElement =
						getFirstSolvedNoneElement(statusArrayResult,  valueArrayResult, countDownEnd, countDownStart);
			}
			int numberOfBlackElementDirectlyBeforePosition = getNumberOfBlackElementDirectlyBeforePosition(
					statusArrayResult, valueArrayResult, countDownEnd);
			if (numberOfBlackElementDirectlyBeforePosition > inputValueSolverInfo.getInputValue()) {
				throw new Exception();
			}
			inputValueSolverInfo.setCountDown(countDownEnd - numberOfBlackElementDirectlyBeforePosition);
			countDown = countDownEnd - 2;
		}
		for (int z = 0; z < temp.length; z++) {
			InputValueSolverInfo values = temp[z];
			InputValueSolverInfo valuesBefore = null;
			if (z - 1 >= 0) {
				valuesBefore = temp[z - 1];
			}
			if (values.getCountUpMax() >= values.getCountDownMin()) {
				for (int p = values.getCountDownMin(); p <= values.getCountUpMax(); p++) {
					if (!statusArrayResult[p]) {
						statusArrayResult[p] = true;
						valueArrayResult[p] = VALUE.BLACK;
					} else if (!VALUE.BLACK.equals(valueArrayResult[p])) {
						throw new Exception("Conflict in status of a cell");
					}
				}
			}
			// check NONE values between this and previous
			if (valuesBefore != null) {
				if (valuesBefore.getCountDownMax() + 1 < values.getCountUpMin()) {
					for (int p = valuesBefore.getCountDownMax() + 1; p < values.getCountUpMin(); p++) {
						if (!statusArrayResult[p]) {
							statusArrayResult[p] = true;
							valueArrayResult[p] = VALUE.NONE;
						} else if (!VALUE.NONE.equals(valueArrayResult[p])) {
							throw new Exception("Conflict in status of a cell");
						}
					}
				}
				int i = 0;
			} else if (values.getCountUpMin() > 0) {
				for (int p = 0; p < values.getCountUpMin(); p++) {
					if (!statusArrayResult[p]) {
						statusArrayResult[p] = true;
						valueArrayResult[p] = VALUE.NONE;
					} else if (!VALUE.NONE.equals(valueArrayResult[p])) {
						throw new Exception("Conflict in status of a cell");
					}
				}
			}
			if (z == temp.length -1 && values.getCountDownMax() + 1 < statusArrayResult.length) {
				for (int p = values.getCountDownMax() + 1; p < statusArrayResult.length; p++) {
					if (!statusArrayResult[p]) {
						statusArrayResult[p] = true;
						valueArrayResult[p] = VALUE.NONE;
					} else if (!VALUE.NONE.equals(valueArrayResult[p])) {
						throw new Exception("Conflict in status of a cell");
					}
				}
			}
		}
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

	private static int getNumberOfBlackElementDirectlyAfterPosition(boolean[] statusArray, VALUE[] valueArray,
																		int currentPosition) {
		int nextPosition = currentPosition + 1;
		int result = 0;
		while (nextPosition < statusArray.length && statusArray[nextPosition] && VALUE.BLACK.equals(valueArray[nextPosition])) {
			nextPosition++;
			result++;
		}
		return result;
	}

	private static int getNumberOfBlackElementDirectlyBeforePosition(boolean[] statusArray, VALUE[] valueArray,
																		int currentPosition) {
		int nextPosition = currentPosition - 1;
		int result = 0;
		while (nextPosition >= 0 && statusArray[nextPosition] && VALUE.BLACK.equals(valueArray[nextPosition])) {
			nextPosition--;
			result++;
		}
		return result;
	}
}
