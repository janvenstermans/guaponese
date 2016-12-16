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
				{5,1}, {1,2,1}, {1,1,2}, {3,3,2}, {9,2,1},
				{2,3,2,2}, {1,1,6,1,1}, {2,2,4,1,1}, {3,1,1,}, {1,2,1,1},
				{2,1,1,1}, {3,1,2,1}, {1,2,1}, {4,1}, {4,1}
		};

		int[][] inputY = new int[][] {
				{4}, {3,1}, {2,1}, {2,1}, {1,1,1},
				{1,1,3}, {1,6,2,1}, {2,7,2}, {1,13}, {1,2,2},
				{2,2,2}, {2,2,2}, {1,2,6}, {2,1,1,1}, {2,8,1}
		};
		return new PuzzleInput(inputX, inputY);
	}
}
