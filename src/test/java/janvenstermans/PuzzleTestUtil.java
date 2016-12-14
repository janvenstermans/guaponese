package janvenstermans;

import janvenstermans.guaponese.model.PuzzleFieldStatus;
import janvenstermans.guaponese.model.PuzzleFieldStatusValue;
import janvenstermans.guaponese.model.PuzzleUtil;
import janvenstermans.guaponese.solver.InputValueSolverInfo;

/**
 * Unit test for simple App.
 */
public class PuzzleTestUtil {

	public static void printFieldStatusLine(InputValueSolverInfo[] inputArray, PuzzleFieldStatus[] statusArray) {
		System.out.println(PuzzleUtil.formatInputRow(inputArray) +
				" | " + PuzzleUtil.formatFieldStatusLine(statusArray) + " |");
	}

	public static void printMinMaxUnderLine(InputValueSolverInfo[] inputArray, PuzzleFieldStatus[] statusArray, int index) {
		InputValueSolverInfo inputValueSolverInfo = inputArray[index];
		String inputRow = PuzzleUtil.formatInputRow(inputArray);
		String formatLength = "%" + inputRow.length() + "d";
		System.out.println(String.format(formatLength, inputValueSolverInfo.getInputValue()) +
				" | " + PuzzleUtil.formatMinMaxUnderLine(inputValueSolverInfo, statusArray) + " |");
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
			inputValueSolverInfoArray[i] = new InputValueSolverInfo(integers[i], PuzzleFieldStatusValue.BLACK);
		}
		return inputValueSolverInfoArray;
	}
}
