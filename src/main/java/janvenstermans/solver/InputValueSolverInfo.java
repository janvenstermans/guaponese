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

	private int inputValue, countUpMin, countUpMax, countDownMax, countDownMin;

	private Set<Integer> solvedValues = new HashSet<Integer>();

	private boolean solved;

	/**
	 * Default constructor, with the input inputValue.
	 * @param inputValue
	 */
	public InputValueSolverInfo(int inputValue) {
		this.inputValue = inputValue;
	}

	/* setter methods */

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
		if (solvedValues.size() == inputValue) {
			solved = true;
		}
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

	public boolean isSolved() {
		return solved;
	}
}
