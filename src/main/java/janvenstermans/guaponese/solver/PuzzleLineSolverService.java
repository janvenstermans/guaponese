package janvenstermans.guaponese.solver;

import janvenstermans.guaponese.model.PuzzleFieldStatus;

/**
 * Contains solution tactics.
 *
 * @author Jan Venstermans
 *
 */
public interface PuzzleLineSolverService {

	void solvePuzzleLine(InputValueSolverInfo[] inputArray, PuzzleFieldStatus[] statusArray)
			throws PuzzleSolverException;

}
