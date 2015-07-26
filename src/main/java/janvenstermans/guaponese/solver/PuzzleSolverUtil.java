package janvenstermans.guaponese.solver;

import janvenstermans.guaponese.model.PuzzleFieldStatus;
import janvenstermans.guaponese.model.PuzzleInput;
import janvenstermans.guaponese.model.PuzzleFieldBoard;

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
		boolean countUpFirst = true;
		for (InputValueSolverInfo inputValueSolverInfo : inputValueSolverInfoArray) {
			int countUpStart = countUp;
			int countUpEnd = countUpStart + inputValueSolverInfo.getInputValue() - 1;

			// move up until it is possible to fit in the inputValue
			Integer lastSolvedWithStatusNone =
					getLastSolvedNoneElement(statusArrayResult, countUpStart, countUpEnd);
			while (lastSolvedWithStatusNone != null) {
				countUpStart = lastSolvedWithStatusNone + 1;
				countUpEnd = countUpStart + inputValueSolverInfo.getInputValue() - 1;
				lastSolvedWithStatusNone =
						getLastSolvedNoneElement(statusArrayResult, countUpStart, countUpEnd);
			}
			// end move up

			// register solved items to InputValueSolverInfo
			inputValueSolverInfo.setIndexMin(countUpStart);
			// it is not possible to always attribute solved values this way, for every inputValueSolverInfo
			// it is possible for the first TODO: change to the first unsolved inputValueSolverInfo
			if (countUpFirst) {
				for (int i = countUpStart; i <= countUpEnd; i++) {
					if (statusArrayResult[i].isSolved() && VALUE.BLACK.equals(statusArrayResult[i].getFieldValue())) {
						inputValueSolverInfo.addSolvedValue(i);
					}
				}
			}

			// check adjacing solved values
			int numberOfBlackElementDirectlyAfterPosition = getNumberOfBlackElementDirectlyAfterPosition(
					statusArrayResult, countUpEnd);
			// it is possible for the first TODO: change to the first unsolved inputValueSolverInfo
			if (countUpFirst) {
				for (int i = countUpEnd + 1; i <= countUpEnd + numberOfBlackElementDirectlyAfterPosition; i++) {
					if (statusArrayResult[i].isSolved() && VALUE.BLACK.equals(statusArrayResult[i].getFieldValue())) {
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
					getFirstSolvedNoneElement(statusArrayResult, countDownEnd, countDownStart);
			while (firstSolvedNoneElement != null) {
				countDownStart = firstSolvedNoneElement - 1;
				countDownEnd = countDownStart - (inputValueSolverInfo.getInputValue() - 1);
				firstSolvedNoneElement =
						getFirstSolvedNoneElement(statusArrayResult, countDownEnd, countDownStart);
			}
			// end move down

			// register solved items to InputValueSolverInfo
			inputValueSolverInfo.setIndexMax(countDownStart);
			// it is not possible to always attribute solved values this way, for every inputValueSolverInfo
			// it is possible for the first TODO: change to the first unsolved inputValueSolverInfo
			if (countDownFirst) {
				for (int i = countDownStart; i >= countDownEnd; i--) {
					if (statusArrayResult[i].isSolved() && VALUE.BLACK.equals(statusArrayResult[i].getFieldValue())) {
						inputValueSolverInfo.addSolvedValue(i);
					}
				}
			}

			// check adjacing solved values
			int numberOfBlackElementDirectlyBeforePosition = getNumberOfBlackElementDirectlyBeforePosition(
					statusArrayResult, countDownEnd);
			// it is possible for the first TODO: change to the first unsolved inputValueSolverInfo
			if (countDownFirst) {
				for (int i = countDownStart - 1; i >= countDownEnd - numberOfBlackElementDirectlyBeforePosition; i--) {
					if (statusArrayResult[i].isSolved() && VALUE.BLACK.equals(statusArrayResult[i].getFieldValue())) {
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

	private static Integer getFirstSolvedNoneElement(PuzzleFieldStatus[] statusArray, int start, int end) {
		if (statusArray.length > 0 && start < statusArray.length && end < statusArray.length) {
			for (int i = start; i <= end; i++) {
				if (statusArray[i].isSolved() && VALUE.NONE.equals(statusArray[i].getFieldValue())){
					return i;
				}
			}
		}
		return null;
	}

	private static Integer getLastSolvedNoneElement(PuzzleFieldStatus[] statusArray, int start, int end) {
		if (statusArray.length > 0 && start < statusArray.length && end < statusArray.length) {
			for (int i = end; i >= start; i--) {
				if (statusArray[i].isSolved() && VALUE.NONE.equals(statusArray[i].getFieldValue())){
					return i;
				}
			}
		}
		return null;
	}

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
}
