package janvenstermans.solver;

import java.util.HashSet;
import java.util.Set;

/**
 * Information linked to an input value during solving the puzzle.
 *
 * @author Jan Venstermans
 *
 */
public class InputValueSolverInfo {

	private final int inputValue;

	// TODO remove
	private int countUpMin, countUpMax, countDownMax, countDownMin;

	private Integer indexMin, indexMax;

	private Set<Integer> solvedValues = new HashSet<Integer>();

	/**
	 * Default constructor, with the input inputValue.
	 * @param inputValue
	 */
	public InputValueSolverInfo(int inputValue) {
		this.inputValue = inputValue;
	}

	/* setter methods */

	public void setIndexMin(Integer indexMin) throws PuzzleSolverException {
		if (this.indexMin == null || this.indexMin < indexMin) {
			this.indexMin = indexMin;
		}
	}

	public void setIndexMax(Integer indexMax) throws PuzzleSolverException {
		if (this.indexMax == null || this.indexMax > indexMax) {
			if (indexMax < getInputValue() - 1) {
				throw new PuzzleSolverException("Max index too small for input value");
			}
			this.indexMax = indexMax;
			checkInputMinAndMaxForSolved();
		}
	}

	public void setCountUp(int endValue) {
		countUpMax = endValue;
		countUpMin = endValue - inputValue + 1;
	}

	public void setCountDown(int endValue) {
		countDownMin = endValue;
		countDownMax = endValue + inputValue - 1;
	}

	public void addSolvedValue(int solvedValue) {
		solvedValues.add(solvedValue);
	}

	/* getters */

	public int getInputValue() {
		return inputValue;
	}

	public int getCountUpMin() {
		return countUpMin;
	}

	public int getCountUpMax() {
		return countUpMax;
	}

	public int getCountDownMax() {
		return countDownMax;
	}

	public int getCountDownMin() {
		return countDownMin;
	}

	public Integer getIndexMax() {
		return indexMax;
	}

	public Integer getIndexMin() {
		return indexMin;
	}

	public boolean isSolved() {
		return solvedValues.size() == inputValue;
	}

	//----------------------
	// private methodes
	//----------------------

	private void checkInputMinAndMaxForSolved() throws PuzzleSolverException {
		if (indexMax != null && indexMin != null) {
			int diff = indexMax - indexMin;
			if (diff < 0) {
				throw new PuzzleSolverException("InputValueSolverInfo: IndexMax cannot be below IndexMin");
			} else if (diff < getInputValue() - 1) {
				throw new PuzzleSolverException("InputValueSolverInfo:" +
						" Difference between IndexMax and IndexMin cannot be less than inputValue " + getInputValue());
			} else if (diff == getInputValue() - 1) {
				solveValuesForMinMaxStretchToInputValue();
			}
		} else if (indexMax != null) {
			if (indexMax < getInputValue() - 1) {
				throw new PuzzleSolverException("InputValueSolverInfo:" +
						" IndexMax cannot be lower than inputValue " + getInputValue());
			} else if (indexMax == getInputValue() - 1) {
				this.indexMin = 0;
				solveValuesForMinMaxStretchToInputValue();
			}
		}
	}

	private void solveValuesForMinMaxStretchToInputValue() {
		if (getIndexMin() != null && getIndexMax() != null &&
				getIndexMax() - getIndexMin() == getInputValue() - 1) {
			for (int i = getIndexMin(); i <= getIndexMax(); i++) {
				addSolvedValue(i);
			}
		}
	}
}
