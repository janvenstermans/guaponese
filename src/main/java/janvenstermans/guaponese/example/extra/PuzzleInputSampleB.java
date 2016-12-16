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
				{4,1,1}, {2,1,2,1}, {2,1,3}, {2,1,2}, {2,1,1,1},
				{2,1,2}, {1,12}, {2,7,3}, {1,6,3}, {5,2},
				{4,3}, {1,1,3,3}, {2,1,1,2,1}, {3,1,1,1}, {4,1,2}
		};

		int[][] inputY = new int[][] {
				{3,4}, {1,2,3}, {1,1,1,2}, {2,2,1,1}, {2,3},
				{2,4}, {2,5}, {1,6}, {1,7}, {12,1},
				{1,1}, {3,10}, {3,8}, {3,3,2,1}, {2,3}
		};
		return new PuzzleInput(inputX, inputY);
	}
}
