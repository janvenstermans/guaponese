package janvenstermans.guaponese.example.extra;

import janvenstermans.guaponese.model.PuzzleInput;
import janvenstermans.guaponese.solver.PuzzleSolverException;

/**
 * Copied from Denksport, for testing purpuses.
 *
 * @author Jan Venstermans
 *
 */
public class PuzzleInputSampleB
{


	public static PuzzleInput createPuzzleInput() throws PuzzleSolverException {
		// copied from Denksport, for testing purpuses
		int[][] inputX = new int[][] {
				{4}, {3,2}, {6,1}, {11}, {1,1,4},
				{1,4} ,{1,3,2}, {6,4,2}, {14},{8}
		};

		int[][] inputY = new int[][] {
				{4}, {1,2}, {1,2}, {1,2}, {1,2},
				{2,2}, {1,1}, {2,2}, {3,3}, {9},
				{9}, {10}, {1,1,1,2}, {2,4}, {3,4}
		};
		return new PuzzleInput(inputX, inputY);
	}
}
