package janvenstermans;

import janvenstermans.model.PuzzleUtil;
import janvenstermans.solver.InputValueSolverInfo;
import janvenstermans.solver.PuzzleSolverUtil;

/**
 * Unit test for simple App.
 */
public class PuzzleTestUtil {

	public static void printArrayAsRow(InputValueSolverInfo[] inputArray, boolean[] statusArray, PuzzleSolverUtil.VALUE[] valueArray) {
		System.out.println(PuzzleUtil.formatInputRow(inputArray) +
				" | " + PuzzleUtil.formatStatusRow(statusArray, valueArray) + " |");
	}
}
