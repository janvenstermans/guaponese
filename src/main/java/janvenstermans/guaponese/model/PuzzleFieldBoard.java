package janvenstermans.guaponese.model;

import janvenstermans.guaponese.solver.PuzzleSolverUtil;

/**
 * Console version of application.
 *
 * @author Jan Venstermans
 *
 */
public class PuzzleFieldBoard
{

	/**
	 * Double array indicating the status of puzzle fields.
	 */
	private PuzzleFieldStatus[][] fieldStatusArray;

	public PuzzleFieldBoard(PuzzleInput puzzleInput) {
		this(puzzleInput.getDimensionX(), puzzleInput.getDimensionY());
	}

	public PuzzleFieldBoard(int dimX, int dimY) {
		createEmptyFieldStatusArray(dimX, dimY);
	}

	public PuzzleFieldStatus[][] getFieldStatusArray() {
		return fieldStatusArray;
	}

	public void setFieldStatusAndValue(int x, int y, PuzzleSolverUtil.VALUE value) {
		fieldStatusArray[x][y].setFieldValue(value);
	}

	/**
	 * x-part.
	 * @param column
	 * @return
	 */
	public PuzzleFieldStatus[] getStatusOfColumn(int column) {
		return fieldStatusArray[column];
	}

	/**
	 * y-part.
	 * @param row
	 * @return
	 */
	public PuzzleFieldStatus[] getStatusYOfRow(int row) {
		PuzzleFieldStatus[] result = new PuzzleFieldStatus[fieldStatusArray.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = fieldStatusArray[i][row];
		}
		return result;
	}

	/* PRIVATE METHODS */

	private void createEmptyFieldStatusArray(int dimX, int dimY) {
		fieldStatusArray = new PuzzleFieldStatus[dimY][dimX];
		for (int i = 0; i < dimX; i++) {
			for (int j = 0; j < dimX; j++) {
				fieldStatusArray[i][j] = new PuzzleFieldStatus(i, j);
			}
		}
	}
}
