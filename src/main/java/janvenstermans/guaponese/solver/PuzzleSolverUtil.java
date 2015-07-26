package janvenstermans.guaponese.solver;

import janvenstermans.guaponese.model.PuzzleFieldStatus;
import janvenstermans.guaponese.model.PuzzleInput;
import janvenstermans.guaponese.model.PuzzleFieldBoard;

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

	public static void checkAllLinesCount(PuzzleFieldBoard puzzleStatus, PuzzleInput puzzleInput) throws PuzzleSolverException {
		// first x, than y
		for (int column = 0; column < puzzleInput.getDimensionX(); column++) {
			PuzzleFieldStatus[] arrayResult = checkCountOfArray(puzzleInput.getSolverInfoXOfColumn(column),
					puzzleStatus.getStatusOfColumn(column));
			for (int i = 0 ; i < arrayResult.length ; i++) {
				if (arrayResult[i].isSolved()) {
					puzzleStatus.setFieldStatusAndValue(column, i, arrayResult[i].getFieldValue());
				}
			}
		}
		for (int row = 0; row < puzzleInput.getDimensionY(); row++) {
			PuzzleFieldStatus[] arrayResult = checkCountOfArray(puzzleInput.getSolverInfoYOfRow(row),
					puzzleStatus.getStatusYOfRow(row));
			for (int i = 0 ; i < arrayResult.length ; i++) {
				if (arrayResult[i].isSolved()) {
					puzzleStatus.setFieldStatusAndValue(i, row, arrayResult[i].getFieldValue());
				}
			}
		}

	}

	public static PuzzleFieldStatus[] checkCountOfArray(InputValueSolverInfo[] inputArray, PuzzleFieldStatus[] statusArray)
			throws PuzzleSolverException {
		// start with existing values
		if (isAllSolved(statusArray)) {
			return statusArray;
		}
		int sum = sumOfInput(inputArray);
		if (sum > 0) {
			int solvedSum = sumOfSolved(statusArray);
			// TODO: check for all not-NONE values
			if (solvedSum == sum) {
				for (int i = 0; i < statusArray.length; i++) {
					if (!statusArray[i].isSolved()) {
						statusArray[i].setFieldValue(VALUE.NONE);
					}
				}
			} else {
				checkForBlackValues(inputArray, statusArray);
			}
		} else {
			// solve all: all are null value
			for (PuzzleFieldStatus fieldStatus : statusArray) {
				fieldStatus.setFieldValue(VALUE.NONE);
			}
		}
		return statusArray;
	}

	private static int sumOfInput(InputValueSolverInfo[] inputArray) {
		int sum = 0;
		for (InputValueSolverInfo value : inputArray) {
			sum += value.getInputValue();
		}
		return sum;
	}

	private static int sumOfSolved(PuzzleFieldStatus[] statusArray) {
		int sum = 0;
		for (int i = 0 ; i < statusArray.length ; i++) {
			if (statusArray[i].isSolved() && VALUE.BLACK.equals(statusArray[i].getFieldValue())) {
				sum++;
			}
		}
		return sum;
	}

	private static boolean isAllSolved(PuzzleFieldStatus[] statusArray) {
		for (PuzzleFieldStatus status : statusArray) {
			if (!status.isSolved()) {
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
	 * @throws Exception
	 */
	private static void checkForBlackValues(InputValueSolverInfo[] inputValueSolverInfoArray,
											PuzzleFieldStatus[] statusArrayResult) throws PuzzleSolverException {
		// go through the input values from lowest to highest
		int countUp = 0;
		for (InputValueSolverInfo inputValueSolverInfo : inputValueSolverInfoArray) {
			if (inputValueSolverInfo.isSolved()) {
				countUp = inputValueSolverInfo.getIndexMax() + 2;
			} else {
				int countUpStart = countUp;
				int inputLength = inputValueSolverInfo.getInputValue();

				int countUpCorrection = countUpStart;
				do {
					countUpStart = countUpCorrection;
					countUpCorrection = checkNoneValuesCountUp(statusArrayResult, countUpStart, inputLength);
					countUpCorrection = checkBlackValuesCountUp(statusArrayResult, countUpCorrection, inputLength);
				} while (countUpCorrection > countUpStart);

				// register solved items to InputValueSolverInfo
				inputValueSolverInfo.setIndexMin(countUpStart);
				for (int k = countUpStart; k < countUpStart + inputLength; k++) {
					if (statusArrayResult[k].isSolved() && VALUE.BLACK.equals(statusArrayResult[k].getFieldValue())) {
						inputValueSolverInfo.addSolvedValue(k);
					}
				}

				//update countUp value
				countUp = countUpStart + inputLength + 1;
			}
		}
		// end count up

		// go through the input values from highest to lowest
		int countDown = statusArrayResult.length - 1;
//		boolean countDownFirst = true;
		for (int m = inputValueSolverInfoArray.length - 1; m >= 0; m--) {
			InputValueSolverInfo inputValueSolverInfo = inputValueSolverInfoArray[m];
			if (inputValueSolverInfo.isSolved()) {
				countDown = inputValueSolverInfo.getIndexMin() - 2;
			} else {
				int countDownStart = countDown;
				int inputLength = inputValueSolverInfo.getInputValue();

				int countDownCorrection = countDownStart;
				do {
					countDownStart = countDownCorrection;
					countDownCorrection = checkNoneValuesCountDown(statusArrayResult, countDownStart, inputLength);
					countDownCorrection = checkBlackValuesCountDown(statusArrayResult, countDownCorrection, inputLength);
				} while (countDownCorrection < countDownStart);


				// register solved items to InputValueSolverInfo
				inputValueSolverInfo.setIndexMax(countDownStart);
				for (int k = countDownStart; k > countDownStart - inputLength; k--) {
					if (statusArrayResult[k].isSolved() && VALUE.BLACK.equals(statusArrayResult[k].getFieldValue())) {
						inputValueSolverInfo.addSolvedValue(k);
					}
				}

				//update countUp value
				countDown = countDownStart - inputLength - 1;
			}
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
						if (!statusArrayResult[p].isSolved()) {
							statusArrayResult[p].setFieldValue(VALUE.NONE);
						} else if (!VALUE.NONE.equals(statusArrayResult[p].getFieldValue())) {
							throw new PuzzleSolverException("Conflict in status of a cell");
						}
					}
				}
				int i = 0;
			} else if (currentSolverInfo.getIndexMin() > 0) {
				for (int p = 0; p < currentSolverInfo.getIndexMin(); p++) {
					if (!statusArrayResult[p].isSolved()) {
						statusArrayResult[p].setFieldValue(VALUE.NONE);
					} else if (!VALUE.NONE.equals(statusArrayResult[p].getFieldValue())) {
						throw new PuzzleSolverException("Conflict in status of a cell");
					}
				}
			}
			if (z == inputValueSolverInfoArray.length -1 && currentSolverInfo.getIndexMax() + 1 < statusArrayResult.length) {
				for (int p = currentSolverInfo.getIndexMax() + 1; p < statusArrayResult.length; p++) {
					if (!statusArrayResult[p].isSolved()) {
						statusArrayResult[p].setFieldValue(VALUE.NONE);
					} else if (!VALUE.NONE.equals(statusArrayResult[p].getFieldValue())) {
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
					statusArrayResult[i].setFieldValue(VALUE.BLACK);
				}
			}
		}
	}

	/**
	 *
	 * @param statusArray
	 * @param end
	 * @param length length of the positions under investigation
	 * @return
	 */
	private static Integer getFirstSolvedNoneElement(PuzzleFieldStatus[] statusArray, int end, int length) {
		int start = end - (length - 1);
		if (statusArray.length > 0 && start < statusArray.length && end < statusArray.length) {
			for (int i = start; i <= end; i++) {
				if (statusArray[i].isSolved() && VALUE.NONE.equals(statusArray[i].getFieldValue())){
					return i;
				}
			}
		}
		return null;
	}

	/**
	 *
	 * @param statusArray
	 * @param start start index
	 * @param length length of the positions under investigation
	 * @return
	 */
	private static Integer getLastSolvedNoneElement(PuzzleFieldStatus[] statusArray, int start, int length) {
		int end = start + length - 1;
		if (statusArray.length > 0 && start < statusArray.length && end < statusArray.length) {
			for (int i = end; i >= start; i--) {
				if (statusArray[i].isSolved() && VALUE.NONE.equals(statusArray[i].getFieldValue())){
					return i;
				}
			}
		}
		return null;
	}

	/**
	 *
	 * @param statusArray
	 * @param currentPosition
	 * @return
	 */
	private static int getNumberOfBlackElementDirectlyAfterPosition(PuzzleFieldStatus[] statusArray,
																	int currentPosition) {
		int nextPosition = currentPosition + 1;
		int result = 0;
		while (nextPosition < statusArray.length && statusArray[nextPosition].isSolved()
				&& VALUE.BLACK.equals(statusArray[nextPosition].getFieldValue())) {
			nextPosition++;
			result++;
		}
		return result;
	}

	private static int getNumberOfBlackElementDirectlyBeforePosition(PuzzleFieldStatus[] statusArray,
																		int currentPosition) {
		int nextPosition = currentPosition - 1;
		int result = 0;
		while (nextPosition >= 0 && statusArray[nextPosition].isSolved()
				&& VALUE.BLACK.equals(statusArray[nextPosition].getFieldValue())) {
			nextPosition--;
			result++;
		}
		return result;
	}

	private static Integer getFirstBlackIndexInSection(PuzzleFieldStatus[] statusArray,
																		int startIndex, int length) {
		for (int i = startIndex; i < startIndex + length; i++) {
			if (statusArray[i].isSolved() && statusArray[i].equals(VALUE.BLACK)) {
				return i;
			}
		}
		return null;
	}

	private static Integer getLastBlackIndexInSection(PuzzleFieldStatus[] statusArray,
																		int endIndex, int length) {
		for (int i = endIndex; i > endIndex - length + length; i--) {
			if (statusArray[i].isSolved() && statusArray[i].equals(VALUE.BLACK)) {
				return i;
			}
		}
		return null;
	}

	/**
	 * move up until it is possible to fit in the inputValue
	 * @param statusArrayResult
	 * @param countUpIndex
	 * @param inputLength
	 * @return
	 */
	private static int checkNoneValuesCountUp(PuzzleFieldStatus[] statusArrayResult, int countUpIndex, int inputLength) {
		Integer lastSolvedWithStatusNone =
				getLastSolvedNoneElement(statusArrayResult, countUpIndex, inputLength);
		while (lastSolvedWithStatusNone != null) {
			countUpIndex = lastSolvedWithStatusNone + 1;
			lastSolvedWithStatusNone =
					getLastSolvedNoneElement(statusArrayResult, countUpIndex, inputLength);
		}
		return countUpIndex;
	}

	private static int checkNoneValuesCountDown(PuzzleFieldStatus[] statusArrayResult, int countDownIndex, int inputLength) {
		Integer firstSolvedNoneElement =
				getFirstSolvedNoneElement(statusArrayResult, countDownIndex, inputLength);
		while (firstSolvedNoneElement != null) {
			countDownIndex = firstSolvedNoneElement - 1;
			firstSolvedNoneElement =
					getLastSolvedNoneElement(statusArrayResult, countDownIndex, inputLength);
		}
		return countDownIndex;
	}

	/**
	 *
	 * @param statusArrayResult
	 * @param countUpIndex
	 * @param inputLength
	 * @return corrected countUpIndex
	 */
	private static int checkBlackValuesCountUp(PuzzleFieldStatus[] statusArrayResult, int countUpIndex, int inputLength) {
		Integer firstBlackValueInDirectRange = getFirstBlackIndexInSection(statusArrayResult, countUpIndex, inputLength);
		int numberOfBlackElementDirectlyAfterPosition = getNumberOfBlackElementDirectlyAfterPosition(
				statusArrayResult, countUpIndex + inputLength - 1);
		if (numberOfBlackElementDirectlyAfterPosition > 0) {
			if (numberOfBlackElementDirectlyAfterPosition > inputLength) {
				return countUpIndex + inputLength + numberOfBlackElementDirectlyAfterPosition + 1;
			}
			if (firstBlackValueInDirectRange != null &&
					(firstBlackValueInDirectRange - countUpIndex) < numberOfBlackElementDirectlyAfterPosition) {
				return countUpIndex + inputLength + numberOfBlackElementDirectlyAfterPosition + 1;
			} else {
				return countUpIndex + numberOfBlackElementDirectlyAfterPosition;
			}
		}
		return countUpIndex;
	}

	private static int checkBlackValuesCountDown(PuzzleFieldStatus[] statusArrayResult, int countDownIndex, int inputLength) {
		Integer lastBlackValueInDirectRange = getLastBlackIndexInSection(statusArrayResult, countDownIndex, inputLength);
		int numberOfBlackElementDirectlyBeforePosition = getNumberOfBlackElementDirectlyBeforePosition(
				statusArrayResult, countDownIndex - (inputLength - 1));
		if (numberOfBlackElementDirectlyBeforePosition > 0) {
			if (numberOfBlackElementDirectlyBeforePosition > inputLength) {
				return countDownIndex - (inputLength + numberOfBlackElementDirectlyBeforePosition + 1);
			}
			if (lastBlackValueInDirectRange != null &&
					(countDownIndex - lastBlackValueInDirectRange) < numberOfBlackElementDirectlyBeforePosition) {
				return countDownIndex - (inputLength + numberOfBlackElementDirectlyBeforePosition + 1);
			} else {
				return countDownIndex + numberOfBlackElementDirectlyBeforePosition;
			}
		}
		return countDownIndex;
	}
}
