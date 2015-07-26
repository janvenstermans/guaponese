package janvenstermans.guaponese.model;

import janvenstermans.guaponese.solver.PuzzleSolverUtil;

/**
 * Contains info of the status of an individual puzzle field.
 *
 * @author Jan Venstermans
 *
 */
public class PuzzleFieldStatus
{

	/**
	 * Nullable enum indicating the solution value of the element.
	 */
	private PuzzleSolverUtil.VALUE fieldValue;

	final private int x, y;

	public PuzzleFieldStatus(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public PuzzleSolverUtil.VALUE getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(PuzzleSolverUtil.VALUE fieldValue) {
		this.fieldValue = fieldValue;
	}

	public boolean isSolved() {
		return fieldValue != null;
	}
}
