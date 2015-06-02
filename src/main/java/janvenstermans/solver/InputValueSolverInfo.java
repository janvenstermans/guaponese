package janvenstermans.solver;

/**
 * Information linked to an input value during solving the puzzle.
 *
 * @author Jan Venstermans
 *
 */
public class InputValueSolverInfo {

	private final int inputValue;

	private Integer indexMin, indexMax;

	private InputValueSolverRange solvedRange;

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
			checkInputMinAndMaxForSolved();
		}
	}

	public void setIndexMax(Integer indexMax) throws PuzzleSolverException {
		if (this.indexMax == null || this.indexMax > indexMax) {
			this.indexMax = indexMax;
			checkInputMinAndMaxForSolved();
		}
	}

	public void addSolvedValue(int solvedValue) throws PuzzleSolverException {
		if (indexMin != null && solvedValue < indexMin) {
			throw new PuzzleSolverException("The solved index is smaller than current indexMin.");
		}
		if (indexMax != null && solvedValue > indexMax) {
			throw new PuzzleSolverException("The solved index is larger than current indexMax.");
		}
		boolean changed;
		if (hasSolvedRange()) {
			changed = solvedRange.addSolvedValue(solvedValue);
		} else {
			solvedRange = new InputValueSolverRange(solvedValue);
			changed = true;
		}
		if (changed) {
			updateIndexMinMaxBySolvedValues();
		}
	}

	/* getters */

	public int getInputValue() {
		return inputValue;
	}

	public Integer getIndexMax() {
		return indexMax;
	}

	public Integer getIndexMin() {
		return indexMin;
	}

	public boolean isSolved() {
		if (hasSolvedRange()) {
			return solvedRange.getRangeInt() == inputValue;
		}
		return false;
	}

	/**
	 * Returns a copy of the solvedRange values.
	 * This copy makes sure changes are only performed via {@link InputValueSolverInfo}.
	 *
	 * @return copy
	 */
	public InputValueSolverRange getSolvedRangeCopy() {
		if (hasSolvedRange()) {
			return new InputValueSolverRange(solvedRange.getSolvedMin(), solvedRange.getSolvedMax());
		}
		return null;
	}

	//----------------------
	// private methodes
	//----------------------

	/**
	 * Checks whether the indexMin and indexMax values are possible.
	 * In case of a different equal to the input value, the values are solved.
	 *
	 * @throws PuzzleSolverException
	 */
	private void checkInputMinAndMaxForSolved() throws PuzzleSolverException {
		if (indexMax != null && indexMin != null) {
			int diff = indexMax - indexMin;
			if (diff < 0) {
				throw new PuzzleSolverException("InputValueSolverInfo: IndexMax cannot be below IndexMin");
			} else if (diff < getInputValue() - 1) {
				throw new PuzzleSolverException("InputValueSolverInfo:" +
						" Difference between IndexMax and IndexMin cannot be less than inputValue " + getInputValue());
			} else if (diff == getInputValue() - 1) {
				if (hasSolvedRange()) {
					solvedRange.addSolvedValue(getIndexMin());
					solvedRange.addSolvedValue(getIndexMax());
				} else {
					solvedRange = new InputValueSolverRange(getIndexMin(), getIndexMax());
				}
			} else {
				updateSolvedRangeByIndexMinMax();
			}
		}
	}

	private void updateIndexMinMaxBySolvedValues() throws PuzzleSolverException {
		if (hasSolvedRange()) {
			setIndexMin(solvedRange.getSolvedMax() - getInputValue() + 1);
			setIndexMax(solvedRange.getSolvedMin() + getInputValue() - 1);
		}
	}

	private void updateSolvedRangeByIndexMinMax() throws PuzzleSolverException {
		Integer indexRange = getIndexMinMaxRange();
		if (indexRange != null && indexRange < inputValue * 2) {
			int newSolvedRangeMax = getIndexMin() + inputValue - 1;
			int newSolvedRangeMin = getIndexMax() - inputValue + 1;
			if (hasSolvedRange()) {
				solvedRange.addSolvedValue(newSolvedRangeMin);
				solvedRange.addSolvedValue(newSolvedRangeMax);
			} else {
				solvedRange = new InputValueSolverRange(newSolvedRangeMin, newSolvedRangeMax);
			}
		}
	}

	private boolean hasSolvedRange() {
		return solvedRange != null;
	}

	/**
	 * Returns number of places in the range; minimum is 1.
	 * If not enough info, null value is returned.
	 *
	 * @return
	 */
	private Integer getIndexMinMaxRange() {
		if (indexMax != null && indexMin != null) {
			return indexMax - indexMin + 1;
		}
		return null;
	}
}
