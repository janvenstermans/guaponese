package janvenstermans.solver;

/**
 * Helper class that keeps the min and max values of solved values.
 *
 * @author Jan Venstermans
 *
 */
public class InputValueSolverRange {

	private int solvedMin, solvedMax;

	/**
	 * 2 argument constructor. The two borders of a range can be in any order.
	 * @param oneSolvedRangeBorder
	 * @param secondSolvedRangeBorder
	 */
	public InputValueSolverRange(int oneSolvedRangeBorder, int secondSolvedRangeBorder) {
		this(oneSolvedRangeBorder);
		addSolvedValue(secondSolvedRangeBorder);
	}

	public InputValueSolverRange(int firstSolvedValue) {
		solvedMin = firstSolvedValue;
		solvedMax = firstSolvedValue;
	}

	/**
	 * Add a solved value. Returns whether or not the range has changed by adding the value.
	 * @param solvedValue
	 * @return whether the range has changed
	 */
	public boolean addSolvedValue(int solvedValue) {
		if (solvedValue < solvedMin) {
			solvedMin = solvedValue;
			return true;
		}
		if (solvedValue > solvedMax) {
			solvedMax = solvedValue;
			return true;
		}
		return false;
	}

	public int getSolvedMin() {
		return solvedMin;
	}

	public int getSolvedMax() {
		return solvedMax;
	}

	public int getRangeInt() {
			return solvedMax - solvedMin + 1;
		}
}
