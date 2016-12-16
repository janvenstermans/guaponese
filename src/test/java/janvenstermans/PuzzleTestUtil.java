package janvenstermans;

import janvenstermans.guaponese.model.PuzzleFieldStatus;
import janvenstermans.guaponese.model.PuzzleFieldStatusValue;
import janvenstermans.guaponese.model.PuzzleUtil;
import janvenstermans.guaponese.solver.InputValueSolverInfo;
import janvenstermans.guaponese.solver.PuzzleSolverException;

import java.util.ArrayList;
import java.util.List;

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

	public static InputValueSolverInfo[] createInputArrayInput(int dimension, List<Integer> integers) throws PuzzleSolverException {
		if (integers == null) {
			integers = new ArrayList<>();
		}
		int count = integers.size();
		InputValueSolverInfo[] inputValueSolverInfoArray = new InputValueSolverInfo[count];
		for (int i = 0; i < count; i++) {
			inputValueSolverInfoArray[i] = new InputValueSolverInfo(integers.get(i), PuzzleFieldStatusValue.BLACK, 0, dimension);
		}
		return inputValueSolverInfoArray;
	}
}
