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
public class ArrayResult {
	private boolean[] statusArray;
	private PuzzleSolverUtil.VALUE[] valueArray;

	public ArrayResult(boolean[] statusArray, PuzzleSolverUtil.VALUE[] valueArray) {
		this.statusArray = statusArray;
		this.valueArray = valueArray;
	}

	public boolean[] getStatusArray() {
		return statusArray;
	}

	public PuzzleSolverUtil.VALUE[] getValueArray() {
			return valueArray;
		}
}
