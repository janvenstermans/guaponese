package janvenstermans.guaponese.solver;

import janvenstermans.guaponese.model.PuzzleFieldStatus;
import janvenstermans.guaponese.model.PuzzleFieldStatusValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains solution tactics.
 *
 * @author Jan Venstermans
 *
 */
public class PuzzleLineSolverServiceImpl implements PuzzleLineSolverService {
	@Override
	public void solvePuzzleLine(PuzzleLineInfo puzzleLineInfo) throws PuzzleSolverException {
		PuzzleLineSummary puzzleLineSummary = getSummary(puzzleLineInfo);
		if (puzzleLineSummary.isAllSolved()) {
			return;
		}
		int amountOfDifferentInputStatusValue = puzzleLineSummary.amountOfDifferentInputStatusValue();
		if (amountOfDifferentInputStatusValue == 0) {
			setAllUnsolvedToSameValue(puzzleLineInfo.getStatusArray(), PuzzleFieldStatusValue.NONE);
			return;
		}
		if (amountOfDifferentInputStatusValue > 1) {
			throw new PuzzleSolverException("Cannot solve yet for different colors of input.");
		}
		// we expect the value to be PuzzleFieldStatusValue.BLACK
		if (puzzleLineSummary.getInputSum(PuzzleFieldStatusValue.BLACK) ==
				puzzleLineSummary.getSolvedSum(PuzzleFieldStatusValue.BLACK)) {
			setAllUnsolvedToSameValue(puzzleLineInfo.getStatusArray(), PuzzleFieldStatusValue.NONE);
			checkInputValuesMinValues(puzzleLineInfo);
			checkInputValuesMaxValues(puzzleLineInfo);
			return;
		}

		checkInputValuesMinValues(puzzleLineInfo);
		checkInputValuesMaxValues(puzzleLineInfo);

		// by now, each InputValueSolverInfo should have min and max value
		goThroughInputValuesFromLowestToHighestCheckBlackValues(puzzleLineInfo);
		goThroughInputValuesFromHighestToLowestCheckBlackValues(puzzleLineInfo);

		checkNonOverlappingConsecutiveMinMaxInfo(puzzleLineInfo);

		checkSolvedRange(puzzleLineInfo);
	}

	//---------------------------------------
	// private methods
	//---------------------------------------

	private void checkNonOverlappingConsecutiveMinMaxInfo(PuzzleLineInfo puzzleLineInfo) throws PuzzleSolverException  {
		for (int z = 0; z < puzzleLineInfo.getInputArray().length; z++) {
			InputValueSolverInfo currentSolverInfo = puzzleLineInfo.getInputArray()[z];
			InputValueSolverInfo solverInfoBefore = null;
			InputValueSolverInfo solverInfoAfter = null;
			// set the before and after
			if (z - 1 >= 0) {
				solverInfoBefore = puzzleLineInfo.getInputArray()[z - 1];
			}
			if (z + 1 < puzzleLineInfo.getInputArray().length) {
				solverInfoAfter = puzzleLineInfo.getInputArray()[z + 1];
			}

			// SET THE NONES
			// for the first: all before min must be set to NONE
			if (solverInfoBefore == null && currentSolverInfo.getIndexMin() > 0) {
				setRangeToNone(puzzleLineInfo, 0, currentSolverInfo.getIndexMin() - 1);
			}
			// check NONE values only between this and previous, covers all
			if (solverInfoBefore != null && solverInfoBefore.getIndexMax() + 1 < currentSolverInfo.getIndexMin()) {
				setRangeToNone(puzzleLineInfo, solverInfoBefore.getIndexMax() + 1, currentSolverInfo.getIndexMin() - 1);
			}
			// for the last: all before max must be set to NONE
			if (solverInfoAfter == null && currentSolverInfo.getIndexMax() + 1 < puzzleLineInfo.getStatusArray().length) {
				setRangeToNone(puzzleLineInfo, currentSolverInfo.getIndexMax() + 1, puzzleLineInfo.getStatusArray().length - 1);
			}

			// check the range
			if (solverInfoBefore != null && solverInfoBefore.getIndexMax() < currentSolverInfo.getIndexMin()) {
				checkWithNotOverlappingPrevious(puzzleLineInfo.getStatusArray(), currentSolverInfo);
			}
			if (solverInfoAfter != null && currentSolverInfo.getIndexMax() < solverInfoAfter.getIndexMin()) {
				checkWithNotOverlappingNext(puzzleLineInfo.getStatusArray(), currentSolverInfo);
			}
		}
	}

	/**
	 *
	 * @param puzzleLineInfo
	 * @param start inclusief
	 * @param end inclusief
	 */
	private void setRangeToNone(PuzzleLineInfo puzzleLineInfo, int start, int end) throws PuzzleSolverException {
		for (int p = start; p <= end; p++) {
			if (!puzzleLineInfo.getStatusArray()[p].isSolved()) {
				puzzleLineInfo.getStatusArray()[p].setFieldValue(PuzzleFieldStatusValue.NONE);
			} else if (!PuzzleFieldStatusValue.NONE.equals(puzzleLineInfo.getStatusArray()[p].getFieldValue())) {
				throw new PuzzleSolverException("Conflict in status of a cell");
			}
		}
	}

	private void checkSolvedRange(PuzzleLineInfo puzzleLineInfo) {
		//copy solved values from InputValueSolverInfo[] to solution:
		for (InputValueSolverInfo inputValueSolverInfo : puzzleLineInfo.getInputArray()) {
			InputValueSolverRange inputValueSolverRange = inputValueSolverInfo.getFullSolvedRangeCopy();
			if (inputValueSolverRange != null) {
				for (int i = inputValueSolverRange.getSolvedMin() ; i <= inputValueSolverRange.getSolvedMax() ; i++) {
					puzzleLineInfo.getStatusArray()[i].setFieldValue(PuzzleFieldStatusValue.BLACK);
				}
			}
		}
	}

	private void setAllUnsolvedToSameValue(PuzzleFieldStatus[] statusArray, PuzzleFieldStatusValue value) {
		// solve all: all are null value
		for (PuzzleFieldStatus fieldStatus : statusArray) {
			if (!fieldStatus.isSolved()) {
				fieldStatus.setFieldValue(value);
			}
		}
	}

	private PuzzleLineSummary getSummary(PuzzleLineInfo puzzleLineInfo) {
		PuzzleLineSummary puzzleLineSummary = new PuzzleLineSummary();
		puzzleLineSummary.setAllSolved(isAllSolved(puzzleLineInfo.getStatusArray()));
		for (Map.Entry<PuzzleFieldStatusValue, Integer> entry : getSolvedSumPerStatusValueMap(puzzleLineInfo.getStatusArray()).entrySet()) {
			puzzleLineSummary.setSolvedSumPerStatusValue(entry.getKey(), entry.getValue());
		}
		for (Map.Entry<PuzzleFieldStatusValue, Integer> entry : getInputSumStatusValueIntegerMap(puzzleLineInfo.getInputArray()).entrySet()) {
			puzzleLineSummary.setInputSumPerStatusValue(entry.getKey(), entry.getValue());
		}
		return puzzleLineSummary;
	}

	private boolean isAllSolved(PuzzleFieldStatus[] statusArray) {
		for (PuzzleFieldStatus status : statusArray) {
			if (!status.isSolved()) {
				return false;
			}
		}
		return true;
	}

	private Map<PuzzleFieldStatusValue, Integer> getSolvedSumPerStatusValueMap(PuzzleFieldStatus[] statusArray) {
		Map<PuzzleFieldStatusValue, Integer> solvedSumPerStatusValueMap = new HashMap<>();
		for (PuzzleFieldStatus status : statusArray) {
			PuzzleFieldStatusValue statusValue = status.getFieldValue();
			if (statusValue != null) {
				if (solvedSumPerStatusValueMap.containsKey(statusValue)) {
					solvedSumPerStatusValueMap.put(statusValue, 1  + solvedSumPerStatusValueMap.get(statusValue));
				} else {
					solvedSumPerStatusValueMap.put(statusValue, 1);
				}
			}
		}
		return solvedSumPerStatusValueMap;
	}

	private Map<PuzzleFieldStatusValue, Integer> getInputSumStatusValueIntegerMap(InputValueSolverInfo[] inputArray) {
		// this should only be done once
		Map<PuzzleFieldStatusValue, Integer> inputSumStatusValueIntegerMap = new HashMap<>();
		for (InputValueSolverInfo value : inputArray) {
			if (inputSumStatusValueIntegerMap.containsKey(value.getStatusValue())) {
				inputSumStatusValueIntegerMap.put(value.getStatusValue(), value.getInputValue() + inputSumStatusValueIntegerMap.get(value.getStatusValue()));
			} else {
				inputSumStatusValueIntegerMap.put(value.getStatusValue(), value.getInputValue());
			}
		}
		return inputSumStatusValueIntegerMap;
	}

	/**
	 *
	 * @param statusArray
	 * @param end
	 * @param length length of the positions under investigation
	 * @return
	 */
	private Integer getFirstSolvedNoneElement(PuzzleFieldStatus[] statusArray, int end, int length) {
		int start = end - (length - 1);
		if (statusArray.length > 0 && start < statusArray.length && end < statusArray.length) {
			for (int i = start; i <= end; i++) {
				if (statusArray[i].isSolved() && PuzzleFieldStatusValue.NONE.equals(statusArray[i].getFieldValue())){
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
	private Integer getLastSolvedNoneElement(PuzzleFieldStatus[] statusArray, int start, int length) {
		int end = start + length - 1;
		if (statusArray.length > 0 && start < statusArray.length && end < statusArray.length) {
			for (int i = end; i >= start; i--) {
				if (statusArray[i].isSolved() && PuzzleFieldStatusValue.NONE.equals(statusArray[i].getFieldValue())){
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
	private int getNumberOfBlackElementDirectlyAfterPosition(PuzzleFieldStatus[] statusArray,
															 int currentPosition) {
		int nextPosition = currentPosition + 1;
		int result = 0;
		while (nextPosition < statusArray.length && statusArray[nextPosition].isSolved()
				&& PuzzleFieldStatusValue.BLACK.equals(statusArray[nextPosition].getFieldValue())) {
			nextPosition++;
			result++;
		}
		return result;
	}

	private int getNumberOfBlackElementDirectlyBeforePosition(PuzzleFieldStatus[] statusArray,
															  int currentPosition) {
		int nextPosition = currentPosition - 1;
		int result = 0;
		while (nextPosition >= 0 && statusArray[nextPosition].isSolved()
				&& PuzzleFieldStatusValue.BLACK.equals(statusArray[nextPosition].getFieldValue())) {
			nextPosition--;
			result++;
		}
		return result;
	}

	private Integer getFirstBlackIndexInSection(PuzzleFieldStatus[] statusArray,
												int startIndex, int length) {
		for (int i = startIndex; i < startIndex + length; i++) {
			if (statusArray[i].isSolved() && statusArray[i].equals(PuzzleFieldStatusValue.BLACK)) {
				return i;
			}
		}
		return null;
	}

	private Integer getLastBlackIndexInSection(PuzzleFieldStatus[] statusArray,
											   int endIndex, int length) {
		for (int i = endIndex; i > endIndex - length; i--) {
			if (statusArray[i].isSolved() && statusArray[i].equals(PuzzleFieldStatusValue.BLACK)) {
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
	private int checkNoneValuesCountUp(PuzzleFieldStatus[] statusArrayResult, int countUpIndex, int inputLength) {
		Integer lastSolvedWithStatusNone =
				getLastSolvedNoneElement(statusArrayResult, countUpIndex, inputLength);
		while (lastSolvedWithStatusNone != null) {
			countUpIndex = lastSolvedWithStatusNone + 1;
			lastSolvedWithStatusNone =
					getLastSolvedNoneElement(statusArrayResult, countUpIndex, inputLength);
		}
		return countUpIndex;
	}

	private int checkNoneValuesCountDown(PuzzleFieldStatus[] statusArrayResult, int countDownIndex, int inputLength) {
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
	private int checkBlackValuesCountUp(PuzzleFieldStatus[] statusArrayResult, int countUpIndex, int inputLength) {
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

	private int checkBlackValuesCountDown(PuzzleFieldStatus[] statusArrayResult, int countDownIndex, int inputLength) {
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
				return countDownIndex - numberOfBlackElementDirectlyBeforePosition;
			}
		}
		return countDownIndex;
	}

	private void checkInputValuesMinValues(PuzzleLineInfo puzzleLineInfo) throws PuzzleSolverException {
		int countUp = 0;
		for (int m = 0; m < puzzleLineInfo.getInputArray().length; m++) {
			InputValueSolverInfo inputValueSolverInfo = puzzleLineInfo.getInputArray()[m];
			if (inputValueSolverInfo.isSolved()) {
				countUp = inputValueSolverInfo.getIndexMax() + 2;
			} else {
				int countUpStart = countUp;
				int inputLength = inputValueSolverInfo.getInputValue();

				int countUpCorrection = countUpStart;
				do {
					countUpStart = countUpCorrection;
					countUpCorrection = checkNoneValuesCountUp(puzzleLineInfo.getStatusArray(), countUpStart, inputLength);
					countUpCorrection = checkBlackValuesCountUp(puzzleLineInfo.getStatusArray(), countUpCorrection, inputLength);
				} while (countUpCorrection > countUpStart);

				inputValueSolverInfo.setIndexMin(countUpStart);
				//update countUp value
				countUp = countUpStart + inputLength + 1;
			}
		}
	}

	private void checkInputValuesMaxValues(PuzzleLineInfo puzzleLineInfo) throws PuzzleSolverException {
		int countDown = puzzleLineInfo.getStatusArray().length - 1;
		for (int m = puzzleLineInfo.getInputArray().length - 1; m >= 0; m--) {
			InputValueSolverInfo inputValueSolverInfo = puzzleLineInfo.getInputArray()[m];
			if (inputValueSolverInfo.isSolved()) {
				countDown = inputValueSolverInfo.getIndexMin() - 2;
			} else {
				int countDownStart = countDown;
				int inputLength = inputValueSolverInfo.getInputValue();

				int countDownCorrection = countDownStart;
				do {
					countDownStart = countDownCorrection;
					countDownCorrection = checkNoneValuesCountDown(puzzleLineInfo.getStatusArray(), countDownStart, inputLength);
					countDownCorrection = checkBlackValuesCountDown(puzzleLineInfo.getStatusArray(), countDownCorrection, inputLength);
				} while (countDownCorrection < countDownStart);

				inputValueSolverInfo.setIndexMax(countDownStart);
				//update countDown value
				countDown = countDownStart - inputLength - 1;
			}
		}
	}

	private void goThroughInputValuesFromLowestToHighestCheckBlackValues(PuzzleLineInfo puzzleLineInfo) throws PuzzleSolverException {
		for (int m = 0; m < puzzleLineInfo.getInputArray().length; m++) {
			InputValueSolverInfo inputValueSolverInfo = puzzleLineInfo.getInputArray()[m];
			// under specific circumstances, register solved items to InputValueSolverInfo
			if (m == 0 || puzzleLineInfo.getInputArray()[m - 1].getIndexMax() + 1 < inputValueSolverInfo.getIndexMin()) {
				for (int k = inputValueSolverInfo.getIndexMin(); k < inputValueSolverInfo.getIndexMin() + inputValueSolverInfo.getInputValue(); k++) {
					if (puzzleLineInfo.getStatusArray()[k].isSolved() && PuzzleFieldStatusValue.BLACK.equals(puzzleLineInfo.getStatusArray()[k].getFieldValue())) {
						inputValueSolverInfo.addSolvedValue(k);
					}
				}
			}
		}
	}

	private void goThroughInputValuesFromHighestToLowestCheckBlackValues(PuzzleLineInfo puzzleLineInfo) throws PuzzleSolverException {
		for (int m = puzzleLineInfo.getInputArray().length - 1; m >= 0; m--) {
			InputValueSolverInfo inputValueSolverInfo = puzzleLineInfo.getInputArray()[m];
			// under specific circumstances, register solved items to InputValueSolverInfo
			if (m == puzzleLineInfo.getInputArray().length - 1 || puzzleLineInfo.getInputArray()[m + 1].getIndexMin() - 1 > inputValueSolverInfo.getIndexMax()) {
				for (int k = inputValueSolverInfo.getIndexMax(); k > inputValueSolverInfo.getIndexMax() - inputValueSolverInfo.getInputValue(); k--) {
					if (puzzleLineInfo.getStatusArray()[k].isSolved() && PuzzleFieldStatusValue.BLACK.equals(puzzleLineInfo.getStatusArray()[k].getFieldValue())) {
						inputValueSolverInfo.addSolvedValue(k);
					}
				}
			}
		}
	}

	private void checkWithNotOverlappingPrevious(PuzzleFieldStatus[] statusArrayResult, InputValueSolverInfo inputValueSolverInfo) throws PuzzleSolverException {
		Integer firstBlackIndex = checkFirstBlackValueInRange(statusArrayResult, inputValueSolverInfo);
		if (firstBlackIndex != null) {
			inputValueSolverInfo.setIndexMax(firstBlackIndex + inputValueSolverInfo.getInputValue() - 1);
		}
	}

	private void checkWithNotOverlappingNext(PuzzleFieldStatus[] statusArrayResult, InputValueSolverInfo inputValueSolverInfo) throws PuzzleSolverException {
		Integer lastBlackIndex = checkLastBlackValueInRange(statusArrayResult, inputValueSolverInfo);
		if (lastBlackIndex != null) {
			inputValueSolverInfo.setIndexMin(lastBlackIndex - inputValueSolverInfo.getInputValue() + 1);
		}
	}

	private Integer checkFirstBlackValueInRange(PuzzleFieldStatus[] statusArrayResult, InputValueSolverInfo inputValueSolverInfo) {
		for (int i = inputValueSolverInfo.getIndexMin(); i <= inputValueSolverInfo.getIndexMax(); i++) {
			if (PuzzleFieldStatusValue.BLACK.equals(statusArrayResult[i].getFieldValue())) {
				return i;
			}
		}
		return null;
	}

	private Integer checkLastBlackValueInRange(PuzzleFieldStatus[] statusArrayResult, InputValueSolverInfo inputValueSolverInfo) {
		for (int i = inputValueSolverInfo.getIndexMax(); i >= inputValueSolverInfo.getIndexMin(); i--) {
			if (PuzzleFieldStatusValue.BLACK.equals(statusArrayResult[i].getFieldValue())) {
				return i;
			}
		}
		return null;
	}

	//-------------------------------------------
	// private classes
	//-------------------------------------------

	private class PuzzleLineSummary {
		private boolean allSolved;

		private Map<PuzzleFieldStatusValue, Integer> inputSumPerStatusValueMap = new HashMap<>();
		private Map<PuzzleFieldStatusValue, Integer> solvedSumPerStatusValueMap = new HashMap<>();

		public boolean isAllSolved() {
			return allSolved;
		}

		public void setAllSolved(boolean allSolved) {
			this.allSolved = allSolved;
		}

		public void setInputSumPerStatusValue(PuzzleFieldStatusValue statusValue, int value) {
			if (value > 0) {
				inputSumPerStatusValueMap.put(statusValue, value);
			} else {
				inputSumPerStatusValueMap.remove(statusValue);
			}
		}

		public void setSolvedSumPerStatusValue(PuzzleFieldStatusValue statusValue, int value) {
			if (value > 0) {
				solvedSumPerStatusValueMap.put(statusValue, value);
			} else {
				solvedSumPerStatusValueMap.remove(statusValue);
			}
		}

		public Integer getInputSum(PuzzleFieldStatusValue statusValue) {
			return inputSumPerStatusValueMap.get(statusValue);
		}

		public Integer getSolvedSum(PuzzleFieldStatusValue statusValue) {
			return solvedSumPerStatusValueMap.get(statusValue);
		}

		public int amountOfDifferentInputStatusValue() {
			return inputSumPerStatusValueMap.size();
		}
	}
}
