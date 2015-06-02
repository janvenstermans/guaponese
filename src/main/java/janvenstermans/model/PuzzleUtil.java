package janvenstermans.model;

import janvenstermans.solver.InputValueSolverInfo;
import janvenstermans.solver.PuzzleSolverUtil;

import java.util.Arrays;

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
		System.out.println("PuzzleInput check: xCount: " + xCount + " - yCount: " + yCount);
		return xCount == yCount;
	}

	public static void printPuzzle(PuzzleInput puzzleInput, PuzzleStatus puzzleStatus) {
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
			System.out.println(xTab + line);
		}
		final char[] array = new char[xHeader[0].length()];
		Arrays.fill(array, '-');
		System.out.println(xTab + new String(array));
		for (int i = 0 ; i < yHeader.length ; i++) {
			System.out.println(yHeader[i] + " | " + puzzleLines[i]);
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

	private static String[] valuesAsString(PuzzleStatus puzzleStatus, int rows) {
		String[] result = new String[rows];
		for (int row = 0 ; row < rows; row++) {
			boolean[] statusses = puzzleStatus.getStatusYOfRow(row);
			PuzzleSolverUtil.VALUE[] values = puzzleStatus.getValueYOfRow(row);
			result[row] = formatStatusRow(statusses, values);
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

	public static String formatStatusRow(boolean[] status, PuzzleSolverUtil.VALUE[] values) {
		String line = "";
		if (status.length > 0) {
			line += String.format("%2s", status[0] ? toString(values[0]) : "");
			for (int i = 1 ; i < status.length ; i++) {
				line += String.format("%3s", status[i] ? toString(values[i]) : "");
			}
		}
		return line;
	}

	private static String toString(PuzzleSolverUtil.VALUE value) {
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
}
