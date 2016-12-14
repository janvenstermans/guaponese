package janvenstermans.guaponese.model;

import janvenstermans.guaponese.solver.InputValueSolverInfo;
import janvenstermans.guaponese.solver.PuzzleSolverException;

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
	private InputValueSolverInfo[][] inputValueSolverInfoArrayX;

	/**
	 * Arrays of input values, i.e. the numbers on the left of the grid. Added as seen left to right.
	 */
	private InputValueSolverInfo[][] inputValueSolverInfoArrayY;

	public PuzzleInput(int[][] inputX, int[][] inputY) throws PuzzleSolverException {
		if (inputX != null && inputY != null) {
			dimensionX = inputX.length;
			dimensionY = inputY.length;
			// need dimensions!
			createSolverInfosFromInputX(inputX);
			createSolverInfosFromInputY(inputY);
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

	public InputValueSolverInfo[][] getInputValueSolverInfoArrayX() {
		return inputValueSolverInfoArrayX;
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

	private void createSolverInfosFromInputX(int[][] inputX) throws PuzzleSolverException {
		if (inputX != null) {
			inputValueSolverInfoArrayX = new InputValueSolverInfo[inputX.length][];
			for (int i = 0; i < inputX.length; i++) {
				int[] inputXSub = inputX[i];
				if (inputXSub != null) {
					inputValueSolverInfoArrayX[i] = new InputValueSolverInfo[inputXSub.length];
					for (int j = 0; j < inputXSub.length; j++) {
						InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputXSub[j], PuzzleFieldStatusValue.BLACK);
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

	private void createSolverInfosFromInputY(int[][] inputY) throws PuzzleSolverException {
		if (inputY != null) {
			inputValueSolverInfoArrayY = new InputValueSolverInfo[inputY.length][];
			for (int i = 0; i < inputY.length; i++) {
				int[] inputYSub = inputY[i];
				if (inputYSub != null) {
					inputValueSolverInfoArrayY[i] = new InputValueSolverInfo[inputYSub.length];
					for (int j = 0; j < inputYSub.length; j++) {
						InputValueSolverInfo inputValueSolverInfo = new InputValueSolverInfo(inputYSub[j], PuzzleFieldStatusValue.BLACK);
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
