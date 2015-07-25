package janvenstermans.guaponese.model;

import janvenstermans.guaponese.solver.PuzzleSolverUtil;

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
	private PuzzleSolverUtil.VALUE[][] fieldValueArray;

	public PuzzleStatus(PuzzleInput puzzleInput) {
		this(puzzleInput.getDimensionX(), puzzleInput.getDimensionY());
	}

	public PuzzleStatus(int dimX, int dimY) {
		createFieldStatus(dimX, dimY);
		createFieldValue(dimX, dimY);
	}

	private void createFieldStatus(int dimX, int dimY) {
		fieldStatusArray = new boolean[dimY][dimX];
	}

	private void createFieldValue(int dimX, int dimY) {
		fieldValueArray = new PuzzleSolverUtil.VALUE[dimY][dimX];
	}

	public boolean[][] getFieldStatusArray() {
		return fieldStatusArray;
	}

	public PuzzleSolverUtil.VALUE[][] getFieldValueArray() {
		return fieldValueArray;
	}

	public void setFieldStatusAndValue(int x, int y, boolean status, PuzzleSolverUtil.VALUE value) {
		fieldStatusArray[x][y] = status;
		fieldValueArray[x][y] = value;
	}

	/**
	 * x-part.
	 * @param column
	 * @return
	 */
	public boolean[] getStatusOfColumn(int column) {
		return fieldStatusArray[column];
	}

	/**
	 * y-part.
	 * @param row
	 * @return
	 */
	public boolean[] getStatusYOfRow(int row) {
		boolean[] result = new boolean[fieldStatusArray.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = fieldStatusArray[i][row];
		}
		return result;
	}

	/**
	 * x-part.
	 * @param column
	 * @return
	 */
	public PuzzleSolverUtil.VALUE[] geValueOfColumn(int column) {
		return fieldValueArray[column];
	}

	/**
	 * y-part.
	 * @param row
	 * @return
	 */
	public PuzzleSolverUtil.VALUE[] getValueYOfRow(int row) {
		PuzzleSolverUtil.VALUE[] result = new PuzzleSolverUtil.VALUE[fieldValueArray.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = fieldValueArray[i][row];
		}
		return result;
	}
}
