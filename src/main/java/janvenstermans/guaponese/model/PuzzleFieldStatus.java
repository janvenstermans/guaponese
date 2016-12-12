package janvenstermans.guaponese.model;

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
	private PuzzleFieldStatusValue fieldValue;

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

	public PuzzleFieldStatusValue getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(PuzzleFieldStatusValue fieldValue) {
		this.fieldValue = fieldValue;
	}

	public boolean isSolved() {
		return fieldValue != null;
	}
}
