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
public class PuzzleSolverServiceImpl implements PuzzleSolverService {

	private PuzzleRowSolverService puzzleRowSolverService = new PuzzleRowSolverServiceImpl();

	@Override
	public void checkAllLinesCount(PuzzleFieldBoard puzzleStatus, PuzzleInput puzzleInput) throws PuzzleSolverException {
		// first x, than y
		for (int column = 0; column < puzzleInput.getDimensionX(); column++) {
			PuzzleFieldStatus[] arrayResult = puzzleRowSolverService.checkCountOfArray(puzzleInput.getSolverInfoXOfColumn(column),
					puzzleStatus.getStatusArrayOfColumn(column));
			for (int i = 0 ; i < arrayResult.length ; i++) {
				if (arrayResult[i].isSolved()) {
					puzzleStatus.setFieldStatusAndValue(column, i, arrayResult[i].getFieldValue());
				}
			}
		}
		for (int row = 0; row < puzzleInput.getDimensionY(); row++) {
			PuzzleFieldStatus[] arrayResult = puzzleRowSolverService.checkCountOfArray(puzzleInput.getSolverInfoYOfRow(row),
					puzzleStatus.getStatusArrayOfRow(row));
			for (int i = 0 ; i < arrayResult.length ; i++) {
				if (arrayResult[i].isSolved()) {
					puzzleStatus.setFieldStatusAndValue(i, row, arrayResult[i].getFieldValue());
				}
			}
		}

	}
}
