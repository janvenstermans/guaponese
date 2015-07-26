package janvenstermans;

import janvenstermans.guaponese.model.PuzzleFieldStatus;
import janvenstermans.guaponese.model.PuzzleUtil;
import janvenstermans.guaponese.solver.InputValueSolverInfo;

/**
 * Unit test for simple App.
 */
public class PuzzleTestUtil {

	public static void printArrayAsRow(InputValueSolverInfo[] inputArray, PuzzleFieldStatus[] statusArray) {
		System.out.println(PuzzleUtil.formatInputRow(inputArray) +
				" | " + PuzzleUtil.formatStatusRow(statusArray) + " |");
	}
}
