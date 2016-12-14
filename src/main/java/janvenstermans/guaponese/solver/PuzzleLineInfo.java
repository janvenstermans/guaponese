package janvenstermans.guaponese.solver;

import janvenstermans.guaponese.model.PuzzleFieldStatus;
import janvenstermans.guaponese.model.PuzzleRowDimension;

/**
 *
 * @author Jan Venstermans
 *
 */
public class PuzzleLineInfo {

	private PuzzleRowDimension rowDimension;
	private InputValueSolverInfo[] inputArray;
	private PuzzleFieldStatus[] statusArray;

	public PuzzleRowDimension getRowDimension() {
		return rowDimension;
	}

	public void setRowDimension(PuzzleRowDimension rowDimension) {
		this.rowDimension = rowDimension;
	}

	public InputValueSolverInfo[] getInputArray() {
		return inputArray;
	}

	public void setInputArray(InputValueSolverInfo[] inputArray) {
		this.inputArray = inputArray;
	}

	public PuzzleFieldStatus[] getStatusArray() {
		return statusArray;
	}

	public void setStatusArray(PuzzleFieldStatus[] statusArray) {
		this.statusArray = statusArray;
	}
}
