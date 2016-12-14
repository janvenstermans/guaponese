package janvenstermans.guaponese.solver;

import janvenstermans.guaponese.model.PuzzleFieldBoard;
import janvenstermans.guaponese.model.PuzzleFieldStatus;
import janvenstermans.guaponese.model.PuzzleInput;
import janvenstermans.guaponese.model.PuzzleRowDimension;

/**
 * Contains solution tactics.
 *
 * @author Jan Venstermans
 *
 */
public class PuzzleSolverServiceImpl implements PuzzleSolverService {

	private PuzzleLineSolverService puzzleLineSolverService = new PuzzleLineSolverServiceImpl();

	@Override
	public void checkAllLinesCount(PuzzleFieldBoard puzzleStatus, PuzzleInput puzzleInput) throws PuzzleSolverException {
		// first x, than y
		for (int column = 0; column < puzzleInput.getDimensionX(); column++) {
			PuzzleLineInfo puzzleLineInfo = new PuzzleLineInfo();
			puzzleLineInfo.setRowDimension(PuzzleRowDimension.X);
			puzzleLineInfo.setStatusArray(puzzleStatus.getStatusArrayOfColumn(column));
			puzzleLineInfo.setInputArray(puzzleInput.getSolverInfoXOfColumn(column));
			puzzleLineSolverService.solvePuzzleLine(puzzleLineInfo);
			/*for (int i = 0 ; i < columnStatusArray.length ; i++) {
				if (columnStatusArray[i].isSolved()) {
					puzzleStatus.setFieldStatusAndValue(column, i, columnStatusArray[i].getFieldValue());
				}
			}*/
		}
		for (int row = 0; row < puzzleInput.getDimensionY(); row++) {
			PuzzleLineInfo puzzleLineInfo = new PuzzleLineInfo();
			puzzleLineInfo.setRowDimension(PuzzleRowDimension.Y);
			puzzleLineInfo.setStatusArray(puzzleStatus.getStatusArrayOfRow(row));
			puzzleLineInfo.setInputArray(puzzleInput.getSolverInfoYOfRow(row));
			puzzleLineSolverService.solvePuzzleLine(puzzleLineInfo);
			/*for (int i = 0 ; i < rowStatusArray.length ; i++) {
				if (rowStatusArray[i].isSolved()) {
					puzzleStatus.setFieldStatusAndValue(i, row, rowStatusArray[i].getFieldValue());
				}
			}*/
		}

	}
}
