package janvenstermans.guaponese.model;

import janvenstermans.guaponese.solver.GuaponesePuzzleSolutionTimeline;
import janvenstermans.guaponese.solver.GuaponesePuzzleSolveStatusStatistics;
import janvenstermans.guaponese.solver.InputValueSolverInfo;

import java.util.Arrays;
import java.util.List;

/**
 * Contains solution tactics.
 *
 * @author Jan Venstermans
 *
 */
public final class PuzzleUtil {
	private PuzzleUtil() {
	}

	public static boolean checkInputXAndY(PuzzleInput puzzleInput) {
		int xCount = countInput(puzzleInput.getInputValueSolverInfoArrayX());
		int yCount = countInput(puzzleInput.getInputValueSolverInfoArrayY());
		printLine("PuzzleInput check: xCount: " + xCount + " - yCount: " + yCount);
		return xCount == yCount;
	}

	public static GuaponesePuzzleSolveStatusStatistics getStatistics(PuzzleInput puzzleInput, PuzzleFieldBoard puzzleStatus) {
		GuaponesePuzzleSolveStatusStatistics puzzleStatistics = new GuaponesePuzzleSolveStatusStatistics(puzzleInput.getDimensionX(), puzzleInput.getDimensionY());
		PuzzleFieldStatus[][] fieldStatusArray = puzzleStatus.getFieldStatusArray();
		long fieldsSolved = 0;
		for (int i = 0; i < fieldStatusArray.length; i++) {
			for (int j = 0; j < fieldStatusArray[i].length; j++) {
				if (fieldStatusArray[i][j].isSolved()) {
					fieldsSolved++;
				}
			}
		}
		puzzleStatistics.setFieldsAmountSolved(fieldsSolved);
		return puzzleStatistics;
	}

	public static void printPuzzle(PuzzleInput puzzleInput, PuzzleFieldBoard puzzleStatus) {
		String[] xHeader = inputAsStringsX(puzzleInput.getInputValueSolverInfoArrayX());
		String[] yHeader = inputAsStringsY(puzzleInput.getInputValueSolverInfoArrayY());
		String[] puzzleLines = valuesAsString(puzzleStatus, puzzleInput.getDimensionY());
		int yHeaderLength = 0;
		for (String line : yHeader) {
			if (line.length() > yHeaderLength) {
				yHeaderLength = line.length();
			}
		}
		yHeaderLength += " | ".length();
		String xTab = String.format("%" + yHeaderLength + "s", "");
		for (String line : xHeader) {
			printLine(xTab + line);
		}
		final char[] array = new char[xHeader[0].length()];
		Arrays.fill(array, '-');
		printLine(xTab + new String(array));
		for (int i = 0 ; i < yHeader.length ; i++) {
			printLine(yHeader[i] + " | " + puzzleLines[i]);
		}
	}

	public static void printEmptyLine() {
		printLine("");
	}

	public static void printPuzzleStatistics(GuaponesePuzzleSolveStatusStatistics puzzleStatistics) {
		if (puzzleStatistics.getFieldsAmountSolved() != null) {
			printLine("fields solved: " +
					puzzleStatistics.getFieldsAmountSolved() + " out of " + puzzleStatistics.getFieldsAmountTotal() +
					" (" + (100.0 * puzzleStatistics.getFieldsAmountSolved() / puzzleStatistics.getFieldsAmountTotal()) + " %)");
		}
		if (puzzleStatistics.getRowsAmountSolved() != null) {
			printLine("rows solved: " +
					puzzleStatistics.getRowsAmountSolved() + " out of " + puzzleStatistics.getRowsAmountTotal() +
					" (" + (100.0 * puzzleStatistics.getRowsAmountSolved() / puzzleStatistics.getRowsAmountTotal()) + " %)");
		}
		if (puzzleStatistics.getColumnsAmountSolved() != null) {
			printLine("columns solved: " +
					puzzleStatistics.getColumnsAmountSolved() + " out of " + puzzleStatistics.getColumnsAmountTotal() +
					" (" + (100.0 * puzzleStatistics.getColumnsAmountSolved() / puzzleStatistics.getColumnsAmountTotal()) + " %)");
		}
	}

	public static void printTimeline(GuaponesePuzzleSolutionTimeline timeline) {
		printLine("Puzzle Timeline Feedback");
		boolean puzzleSolved = timeline.isPuzzleSolved();
		printLine(puzzleSolved ? "puzzle is solved" : "puzzle is not solved");
		List<GuaponesePuzzleSolutionTimeline.GuaponesePuzzleSolvingStep> stepList = timeline.getSolvingStepList();
		printLine(stepList.size() + " steps executed." +
				(stepList.size() > 0 ? " Progression" : ""));
		for (int i = 0; i < stepList.size() ; i++) {
			GuaponesePuzzleSolutionTimeline.GuaponesePuzzleSolvingStep step = stepList.get(i);
			double solutionPercentage = 100.0 * step.getStatusStatistics().getFieldsAmountSolved() /
					step.getStatusStatistics().getFieldsAmountTotal();
			printLine("step " + i + ": method:" + step.getMethod() + " solution%:" + solutionPercentage);
		}
	}

	private static int countInput(InputValueSolverInfo[][] input) {
		int xCount = 0;
		for (InputValueSolverInfo[] sub : input) {
			for (InputValueSolverInfo subSub : sub) {
				xCount += subSub.getInputValue();
			}
		}
		return xCount;
	}

	private static String[] valuesAsString(PuzzleFieldBoard puzzleStatus, int rows) {
		String[] result = new String[rows];
		for (int row = 0 ; row < rows; row++) {
			PuzzleFieldStatus[] statusses = puzzleStatus.getStatusArrayOfRow(row);
			result[row] = formatStatusRow(statusses);
		}
		return result;
	}

	private static String[] inputAsStringsX(InputValueSolverInfo[][] input) {
		return inputAsStringsY(turnXIntoY(input));
	}

	private static String[] inputAsStringsY(InputValueSolverInfo[][] input) {
		int longestY = getLongestInput(input);
		int sizeY = 3 * longestY - 1;
		// in y input this is the length of the left column
		String[] inputAsStrings = new String[input.length];
		for (int i = 0 ; i < input.length ; i++) {
			inputAsStrings[i] = formatInputRow(input[i], sizeY);
		}
		return inputAsStrings;
	}

	private static int getLongestInput(InputValueSolverInfo[][] input) {
	   	int longest = 0;
		for (InputValueSolverInfo[] sub : input) {
			if (sub.length > longest) {
				longest = sub.length;
			}
		}
		return longest;
	}

	private static InputValueSolverInfo[][] turnXIntoY(InputValueSolverInfo[][] input) {
		int longestX = getLongestInput(input);
		int size = 3 * input.length - 1;
		InputValueSolverInfo[][] result = new InputValueSolverInfo[longestX][];
		for (int i = 0 ; i < result.length; i++) {
			result[i] = new InputValueSolverInfo[input.length];
		}
		for (int i = 0 ; i < input.length ; i++) {
			InputValueSolverInfo[] sub = input[i];
			int subLength = sub.length;
			for (int j = 0 ; j < sub.length ; j++) {
				result[longestX - subLength + j][i] = input[i][j];
			}
		}
		return result;
	}

	/**
	 * Return a formatted string from the input, to be displayed as a horizontal string,
	 * where every number is presented as two characters and there is a space between the numbers.
	 *
	 * @param input
	 * @param sizeTotal
	 * @return
	 */
	private static String formatInputRow(InputValueSolverInfo[] input, int sizeTotal) {
	   	return String.format("%" + sizeTotal +"s", formatInputRow(input));
	}

	public static String formatInputRow(InputValueSolverInfo[] input) {
		String line = "";
		if (input.length > 0) {
			line += String.format("%2s",  (input[0] != null && input[0].getInputValue() > 0)
					? input[0].getInputValue() : "");
			for (int i = 1 ; i < input.length ; i++) {
				line += String.format("%3s", (input[i] != null && input[i].getInputValue() > 0)
						? input[i].getInputValue() : "");
			}
		}
	   	return line;
	}

	public static String formatStatusRow(PuzzleFieldStatus[] status) {
		String line = "";
		if (status.length > 0) {
			line += String.format("%2s", status[0].isSolved() ? toString(status[0].getFieldValue()) : "");
			for (int i = 1 ; i < status.length ; i++) {
				line += String.format("%3s", status[i].isSolved() ? toString(status[i].getFieldValue()) : "");
			}
		}
		return line;
	}

	private static String toString(PuzzleFieldStatusValue value) {
		if (value != null) {
			switch (value) {
			   case NONE:
				   return ".";
			   case BLACK:
				   return "*";
			}
		}
		return "";
	}

	private static void printLine(String line) {
		System.out.println(line);
	}
}
