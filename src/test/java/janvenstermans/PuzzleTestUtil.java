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

	public static PuzzleFieldStatus[] createEmptyColumnPuzzleFieldStatusArray(int dimension) {
		PuzzleFieldStatus[] statusArray = new PuzzleFieldStatus[dimension];
		for (int i = 0; i < dimension; i++) {
			statusArray[i] = new PuzzleFieldStatus(i, 0);
		}
		return statusArray;
	}

	public static InputValueSolverInfo[] createInputArrayInput(Integer...integers) {
		int count = integers.length;
		InputValueSolverInfo[] inputValueSolverInfoArray = new InputValueSolverInfo[count];
		for (int i = 0; i < count; i++) {
			inputValueSolverInfoArray[i] = new InputValueSolverInfo(integers[i]);
		}
		return inputValueSolverInfoArray;
	}
}
