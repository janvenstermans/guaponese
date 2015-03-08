package janvenstermans.model;

import java.util.Scanner;

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

	/**
	 * Arrays of input values, i.e. the numbers on the left of the grid. Added as seen left to right.
	 */
	private int[][] inputY;

	public int getDimensionX() {
		return dimensionX;
	}

	public void setDimensionX(int dimensionX) {
		this.dimensionX = dimensionX;
	}

	public int getDimensionY() {
		return dimensionY;
	}

	public void setDimensionY(int dimensionY) {
		this.dimensionY = dimensionY;
	}

	public int[][] getInputX() {
		return inputX;
	}

	public void setInputX(int[][] inputX) {
		this.inputX = inputX;
	}

	public int[][] getInputY() {
		return inputY;
	}

	public void setInputY(int[][] inputY) {
		this.inputY = inputY;
	}

	public void setDimensions(int x, int y) {
		setDimensionX(x);
		setDimensionY(y);
	}

	public int[] getInputXOfColumn(int column) {
		return inputX[column];
	}

	public int[] getInputYOfRow(int row) {
		return inputY[row];
	}
}
