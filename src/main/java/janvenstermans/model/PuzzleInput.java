package janvenstermans.model;

import janvenstermans.solver.InputValueSolverInfo;
import janvenstermans.solver.PuzzleSolverException;

/**
 * Console version of application.
 *
 * @author Jan Venstermans
 *
 */
public class PuzzleInput
{
	/**
	 * Dimension of the puzzle.
	 */
	private int dimensionX, dimensionY;

	/**
	 * Arrays of input values, i.e. the numbers on top of the grid. Added as seen from top to bottom.
	 */
	private int[][] inputX;

	private InputValueSolverInfo[][] inputValueSolverInfoArrayX;

	/**
	 * Arrays of input values, i.e. the numbers on the left of the grid. Added as seen left to right.
	 */
	private int[][] inputY;
	private InputValueSolverInfo[][] inputValueSolverInfoArrayY;

	public PuzzleInput(int[][] inputX, int[][] inputY) throws PuzzleSolverException {
		if (inputX != null && inputY != null) {
			this.inputX = inputX;
			this.inputY = inputY;
			dimensionX = inputX.length;
			dimensionY = inputY.length;
			createSolverInfosFromInputX();
			createSolverInfosFromInputY();
			int i = 5;
		} else {
			throw new PuzzleSolverException("input empty");
		}
	}

	public int getDimensionX() {
		return dimensionX;
	}

	public int getDimensionY() {
		return dimensionY;
	}

	public int[][] getInputX() {
		return inputX;
	}

	public InputValueSolverInfo[][] getInputValueSolverInfoArrayX() {
		return inputValueSolverInfoArrayX;
	}

	public int[][] getInputY() {
		return inputY;
	}

	public InputValueSolverInfo[][] getInputValueSolverInfoArrayY() {
		return inputValueSolverInfoArrayY;
	}

	public InputValueSolverInfo[] getSolverInfoXOfColumn(int column) {
		return inputValueSolverInfoArrayX[column];
	}

	public InputValueSolverInfo[] getSolverInfoYOfRow(int row) {
		return inputValueSolverInfoArrayY[row];
	}

	private void createSolverInfosFromInputX() throws PuzzleSolverException {
		if (inputX != null) {
			inputValueSolverInfoArrayX = new InputValueSolverInfo[inputX.length][];
			for (int i = 0; i < inputX.length; i++) {
				int[] inputXSub = inputX[i];
				if (inputXSub != null) {
					inputValueSolverInfoArrayX[i] = new InputValueSolverInfo[inputXSub.length];
					for (int j = 0; j < inputXSub.length; j++) {
						InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputXSub[j]);
						inputValueSolverInfo.setIndexMin(0);
						inputValueSolverInfo.setIndexMax(dimensionY);
						inputValueSolverInfoArrayX[i][j] = inputValueSolverInfo;
					}
				}
			}
		} else {
			inputValueSolverInfoArrayX = null;
		}
	}

	private void createSolverInfosFromInputY() throws PuzzleSolverException {
		if (inputY != null) {
			inputValueSolverInfoArrayY = new InputValueSolverInfo[inputY.length][];
			for (int i = 0; i < inputY.length; i++) {
				int[] inputYSub = inputY[i];
				if (inputYSub != null) {
					inputValueSolverInfoArrayY[i] = new InputValueSolverInfo[inputYSub.length];
					for (int j = 0; j < inputYSub.length; j++) {
						InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputYSub[j]);
						inputValueSolverInfo.setIndexMin(0);
						inputValueSolverInfo.setIndexMax(dimensionX);
						inputValueSolverInfoArrayY[i][j] = inputValueSolverInfo;
					}
				}
			}
		} else {
			inputValueSolverInfoArrayY = null;
		}
	}
}
