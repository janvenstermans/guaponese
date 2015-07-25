package janvenstermans;

import janvenstermans.guaponese.model.PuzzleUtil;
import janvenstermans.guaponese.solver.InputValueSolverInfo;
import janvenstermans.guaponese.solver.PuzzleSolverUtil;

/**
 * Unit test for simple App.
 */
public class PuzzleTestUtil {

	public static void printArrayAsRow(InputValueSolverInfo[] inputArray, boolean[] statusArray, PuzzleSolverUtil.VALUE[] valueArray) {
		System.out.println(PuzzleUtil.formatInputRow(inputArray) +
				" | " + PuzzleUtil.formatStatusRow(statusArray, valueArray) + " |");
	}
}
