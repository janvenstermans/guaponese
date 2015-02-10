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
	 * Arrays of input values, i.e. the numbers on top and besides the grid.
	 */
	private int[][] inputX, inputY;

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
}
