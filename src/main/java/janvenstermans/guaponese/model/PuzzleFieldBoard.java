package janvenstermans.guaponese.model;

import janvenstermans.guaponese.solver.PuzzleSolverUtil;

/**
 * Class containing the status of each field of the rectangular board.
 *
 * @author Jan Venstermans
 *
 */
public class PuzzleFieldBoard
{

	/**
	 * Double array indicating the status of puzzle fields.
	 * X-dimension first (columns), Y-dimension second (rows).
	 * This means: to get a value from the array: fieldStatusArray[column][row].
	 */
	private PuzzleFieldStatus[][] fieldStatusArray;

	/**
	 *
	 * @param columnCount
	 * @param rowCount
	 */
	public PuzzleFieldBoard(int columnCount, int rowCount) {
		createEmptyFieldStatusArray(columnCount, rowCount);
	}

	public PuzzleFieldStatus[][] getFieldStatusArray() {
		return fieldStatusArray;
	}

	public void setFieldStatusAndValue(int columnIndex, int rowIndex, PuzzleSolverUtil.VALUE value) {
		fieldStatusArray[columnIndex][rowIndex].setFieldValue(value);
	}

	/**
	 * Extract column info.
	 * @param columnIndex
	 * @return
	 */
	public PuzzleFieldStatus[] getStatusArrayOfColumn(int columnIndex) {
		return fieldStatusArray[columnIndex];
	}

	/**
	 * Extract row info.
	 * @param rowIndex
	 * @return
	 */
	public PuzzleFieldStatus[] getStatusArrayOfRow(int rowIndex) {
		PuzzleFieldStatus[] result = new PuzzleFieldStatus[fieldStatusArray.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = fieldStatusArray[i][rowIndex];
		}
		return result;
	}

	/* PRIVATE METHODS */

	private void createEmptyFieldStatusArray(int columnCount, int rowCount) {
		fieldStatusArray = new PuzzleFieldStatus[columnCount][rowCount];
		for (int i = 0; i < columnCount; i++) {
			for (int j = 0; j < rowCount; j++) {
				fieldStatusArray[i][j] = new PuzzleFieldStatus(i, j);
			}
		}
	}


	// static methods

	public static PuzzleFieldBoard createPuzzleFieldBoard(PuzzleInput puzzleInput) {
		return new PuzzleFieldBoard(puzzleInput.getDimensionX(), puzzleInput.getDimensionY());
	}

}
