package janvenstermans.guaponese.example;

import janvenstermans.guaponese.model.PuzzleInput;
import janvenstermans.guaponese.solver.PuzzleSolverException;

/**
 * Simple 15 x 15 example.
 *
 * @author Jan Venstermans
 *
 */
public final class PuzzleInputSampleA
{
	/**
	 * Arrays of input values, i.e. the numbers on top of the grid.
	 */
	static int[][] inputX = {{6},{9},{10,2},{9},{6},
			{},{7},{14},{13},{},
			{6},{14},{1,9,2},{2,8},{6}};

	/**
	 * Arrays of input values, i.e. the numbers on the left of the grid.
	 */
	static int[][] inputY = {{3},{1,1,1,2,2,2},{1,1,1,3,3,1},{1,1,1,3,3,1},{1,1,1,3,3,1},
			{5,3,3,1},{5,3,5},{3,3,3},{3,2,3},{3,2,3},
			{3,2,3},{1,1,2,1,1},{1,1,2,1,1},{3,2,3},{1,2,1}};

	public static PuzzleInput createPuzzleInput() {
		PuzzleInput puzzleInput = null;
		try {
			puzzleInput = new PuzzleInput(inputX, inputY);
		} catch (PuzzleSolverException e) {
			e.printStackTrace();
		}
		return puzzleInput;
	}

}
