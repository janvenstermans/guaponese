package janvenstermans.model;

/**
 * Console version of application.
 *
 * @author Jan Venstermans
 *
 */
public class PuzzleStatus
{

	/**
	 * Double array of booleans indicating the solution state of the element.
	 */
	private boolean[][] fieldStatusArray;

	/**
	 * Double array of int indicating the solution value of the element.
	 */
	private int[][] fieldValueArray;

	public PuzzleStatus(int dimX, int dimY) {
		createFieldStatus(dimX, dimY);
		createFieldValue(dimX, dimY);
	}

	private void createFieldStatus(int dimX, int dimY) {
		fieldStatusArray = new boolean[dimY][dimX];
	}

	private void createFieldValue(int dimX, int dimY) {
		fieldValueArray = new int[dimY][dimX];
	}

	public boolean[][] getFieldStatusArray() {
		return fieldStatusArray;
	}

	public int[][] getFieldValueArray() {
		return fieldValueArray;
	}

	public void setFieldStatus(int x, int y, boolean status) {
		fieldStatusArray[x][y] = status;
	}

	public void setFieldValue(int x, int y, int value) {
		fieldValueArray[x][y] = value;
	}
}
