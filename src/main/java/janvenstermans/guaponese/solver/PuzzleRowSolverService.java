package janvenstermans.guaponese.solver;

import janvenstermans.guaponese.model.PuzzleFieldBoard;
import janvenstermans.guaponese.model.PuzzleFieldStatus;
import janvenstermans.guaponese.model.PuzzleInput;

/**
 * Contains solution tactics.
 *
 * @author Jan Venstermans
 *
 */
public interface PuzzleRowSolverService {

	PuzzleFieldStatus[] checkCountOfArray(InputValueSolverInfo[] inputArray, PuzzleFieldStatus[] statusArray)
			throws PuzzleSolverException;

}
