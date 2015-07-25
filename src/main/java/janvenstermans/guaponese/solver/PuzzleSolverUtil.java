package janvenstermans.guaponese.solver;

import janvenstermans.guaponese.model.PuzzleInput;
import janvenstermans.guaponese.model.PuzzleStatus;

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

	public static void checkAllLinesCount(PuzzleStatus puzzleStatus, PuzzleInput puzzleInput) throws PuzzleSolverException {
		// first x, than y
		for (int column = 0; column < puzzleInput.getDimensionX(); column++) {
			ArrayResult arrayResult = checkCountOfArray(puzzleInput.getSolverInfoXOfColumn(column),
					puzzleStatus.getStatusOfColumn(column), puzzleStatus.geValueOfColumn(column));
			for (int i = 0 ; i < arrayResult.getStatusArray().length ; i++) {
				if (arrayResult.getStatusArray()[i]) {
					puzzleStatus.setFieldStatusAndValue(column, i, true, arrayResult.getValueArray()[i]);
				}
			}
		}
		for (int row = 0; row < puzzleInput.getDimensionY(); row++) {
			ArrayResult arrayResult = checkCountOfArray(puzzleInput.getSolverInfoYOfRow(row),
					puzzleStatus.getStatusYOfRow(row), puzzleStatus.getValueYOfRow(row));
			for (int i = 0 ; i < arrayResult.getStatusArray().length ; i++) {
				if (arrayResult.getStatusArray()[i]) {
					puzzleStatus.setFieldStatusAndValue(i, row, true, arrayResult.getValueArray()[i]);
				}
			}
		}

	}

	public static ArrayResult checkCountOfArray(InputValueSolverInfo[] inputArray, boolean[] statusArray,
												VALUE[] valueArray)
			throws PuzzleSolverException {
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

	private static int sumOfInput(InputValueSolverInfo[] inputArray) {
		int sum = 0;
		for (InputValueSolverInfo value : inputArray) {
			sum += value.getInputValue();
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
	 * @param inputValueSolverInfoArray
	 * @param statusArrayResult
	 * @param valueArrayResult
	 * @throws Exception
	 */
	private static void checkForBlackValues(InputValueSolverInfo[] inputValueSolverInfoArray,
											boolean[] statusArrayResult,
											VALUE[] valueArrayResult) throws PuzzleSolverException {
		// go through the input values from lowest to highest
		int countUp = 0;
		boolean countUpFirst = true;
		for (InputValueSolverInfo inputValueSolverInfo : inputValueSolverInfoArray) {
			int countUpStart = countUp;
			int countUpEnd = countUpStart + inputValueSolverInfo.getInputValue() - 1;

			// move up until it is possible to fit in the inputValue
			Integer lastSolvedWithStatusNone =
					getLastSolvedNoneElement(statusArrayResult,  valueArrayResult, countUpStart, countUpEnd);
			while (lastSolvedWithStatusNone != null) {
				countUpStart = lastSolvedWithStatusNone + 1;
				countUpEnd = countUpStart + inputValueSolverInfo.getInputValue() - 1;
				lastSolvedWithStatusNone =
						getLastSolvedNoneElement(statusArrayResult,  valueArrayResult, countUpStart, countUpEnd);
			}
			// end move up

			// register solved items to InputValueSolverInfo
			inputValueSolverInfo.setIndexMin(countUpStart);
			// it is not possible to always attribute solved values this way, for every inputValueSolverInfo
			// it is possible for the first TODO: change to the first unsolved inputValueSolverInfo
			if (countUpFirst) {
				for (int i = countUpStart; i <= countUpEnd; i++) {
					if (statusArrayResult[i] && VALUE.BLACK.equals(valueArrayResult[i])) {
						inputValueSolverInfo.addSolvedValue(i);
					}
				}
			}

			// check adjacing solved values
			int numberOfBlackElementDirectlyAfterPosition = getNumberOfBlackElementDirectlyAfterPosition(
					statusArrayResult, valueArrayResult, countUpEnd);
			// it is possible for the first TODO: change to the first unsolved inputValueSolverInfo
			if (countUpFirst) {
				for (int i = countUpEnd + 1; i <= countUpEnd + numberOfBlackElementDirectlyAfterPosition; i++) {
					if (statusArrayResult[i] && VALUE.BLACK.equals(valueArrayResult[i])) {
						inputValueSolverInfo.addSolvedValue(i);
					}
				}
			}
			if (numberOfBlackElementDirectlyAfterPosition > inputValueSolverInfo.getInputValue()) {
			  	throw new PuzzleSolverException("Too many solved values for inputValue");
			}

			countUp = countUpEnd + 2;
			countUpFirst = false;
		}
		// end count up

		// go through the input values from highest to lowest
		int countDown = statusArrayResult.length - 1;
		boolean countDownFirst = true;
		for (int m = inputValueSolverInfoArray.length - 1; m >= 0; m--) {
			InputValueSolverInfo inputValueSolverInfo = inputValueSolverInfoArray[m];
			int countDownStart = countDown;
			int countDownEnd = countDownStart - (inputValueSolverInfo.getInputValue() - 1);

			// move down until it is possible to fit in the inputValue
			Integer firstSolvedNoneElement =
					getFirstSolvedNoneElement(statusArrayResult,  valueArrayResult, countDownEnd, countDownStart);
			while (firstSolvedNoneElement != null) {
				countDownStart = firstSolvedNoneElement - 1;
				countDownEnd = countDownStart - (inputValueSolverInfo.getInputValue() - 1);
				firstSolvedNoneElement =
						getFirstSolvedNoneElement(statusArrayResult,  valueArrayResult, countDownEnd, countDownStart);
			}
			// end move down

			// register solved items to InputValueSolverInfo
			inputValueSolverInfo.setIndexMax(countDownStart);
			// it is not possible to always attribute solved values this way, for every inputValueSolverInfo
			// it is possible for the first TODO: change to the first unsolved inputValueSolverInfo
			if (countDownFirst) {
				for (int i = countDownStart; i >= countDownEnd; i--) {
					if (statusArrayResult[i] && VALUE.BLACK.equals(valueArrayResult[i])) {
						inputValueSolverInfo.addSolvedValue(i);
					}
				}
			}

			// check adjacing solved values
			int numberOfBlackElementDirectlyBeforePosition = getNumberOfBlackElementDirectlyBeforePosition(
					statusArrayResult, valueArrayResult, countDownEnd);
			// it is possible for the first TODO: change to the first unsolved inputValueSolverInfo
			if (countDownFirst) {
				for (int i = countDownStart - 1; i >= countDownEnd - numberOfBlackElementDirectlyBeforePosition; i--) {
					if (statusArrayResult[i] && VALUE.BLACK.equals(valueArrayResult[i])) {
						inputValueSolverInfo.addSolvedValue(i);
					}
				}
			}
			inputValueSolverInfo.setIndexMax(countDownStart - numberOfBlackElementDirectlyBeforePosition);
			if (numberOfBlackElementDirectlyBeforePosition > inputValueSolverInfo.getInputValue()) {
				throw new PuzzleSolverException("Too many solved values for inputValue");
			}

			countDown = countDownEnd - 2;
			countDownFirst = false;
		}
		for (int z = 0; z < inputValueSolverInfoArray.length; z++) {
			InputValueSolverInfo currentSolverInfo = inputValueSolverInfoArray[z];
			InputValueSolverInfo solverInfoBefore = null;
			if (z - 1 >= 0) {
				solverInfoBefore = inputValueSolverInfoArray[z - 1];
			}
			// check NONE values between this and previous
			if (solverInfoBefore != null) {
				if (solverInfoBefore.getIndexMax() + 1 < currentSolverInfo.getIndexMin()) {
					for (int p = solverInfoBefore.getIndexMax() + 1; p < currentSolverInfo.getIndexMin(); p++) {
						if (!statusArrayResult[p]) {
							statusArrayResult[p] = true;
							valueArrayResult[p] = VALUE.NONE;
						} else if (!VALUE.NONE.equals(valueArrayResult[p])) {
							throw new PuzzleSolverException("Conflict in status of a cell");
						}
					}
				}
				int i = 0;
			} else if (currentSolverInfo.getIndexMin() > 0) {
				for (int p = 0; p < currentSolverInfo.getIndexMin(); p++) {
					if (!statusArrayResult[p]) {
						statusArrayResult[p] = true;
						valueArrayResult[p] = VALUE.NONE;
					} else if (!VALUE.NONE.equals(valueArrayResult[p])) {
						throw new PuzzleSolverException("Conflict in status of a cell");
					}
				}
			}
			if (z == inputValueSolverInfoArray.length -1 && currentSolverInfo.getIndexMax() + 1 < statusArrayResult.length) {
				for (int p = currentSolverInfo.getIndexMax() + 1; p < statusArrayResult.length; p++) {
					if (!statusArrayResult[p]) {
						statusArrayResult[p] = true;
						valueArrayResult[p] = VALUE.NONE;
					} else if (!VALUE.NONE.equals(valueArrayResult[p])) {
						throw new PuzzleSolverException("Conflict in status of a cell");
					}
				}
			}
		}
		//copy solved values from InputValueSolverInfo[] to solution:
		for (InputValueSolverInfo inputValueSolverInfo : inputValueSolverInfoArray) {
			InputValueSolverRange inputValueSolverRange = inputValueSolverInfo.getSolvedRangeCopy();
			if (inputValueSolverRange != null) {
				for (int i = inputValueSolverRange.getSolvedMin() ; i <= inputValueSolverRange.getSolvedMax() ; i++) {
					statusArrayResult[i] = true;
					valueArrayResult[i] = VALUE.BLACK;
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
