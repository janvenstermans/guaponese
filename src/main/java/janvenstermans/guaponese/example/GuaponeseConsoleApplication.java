package janvenstermans.guaponese.example;

import janvenstermans.guaponese.model.PuzzleInput;
import janvenstermans.guaponese.model.PuzzleStatus;
import janvenstermans.guaponese.model.PuzzleUtil;
import janvenstermans.guaponese.solver.GuaponesePuzzleSolutionTimeline;
import janvenstermans.guaponese.solver.GuaponesePuzzleSolveStatusStatistics;
import janvenstermans.guaponese.solver.GuaponesePuzzleSolveStepMethod;
import janvenstermans.guaponese.solver.PuzzleSolverException;
import janvenstermans.guaponese.solver.PuzzleSolverUtil;

import java.util.Scanner;

/**
 * Console version of application.
 *
 * @author Jan Venstermans
 *
 */
public class GuaponeseConsoleApplication
{

	private PuzzleInput puzzleInput;
	private String tab = "  ";
	private Scanner keyboard = new Scanner(System.in);

	private GuaponesePuzzleSolutionTimeline timeline = new GuaponesePuzzleSolutionTimeline();

    public static void main( String[] args )
    {
		new GuaponeseConsoleApplication().run();
    }

	public void run() {
		try {
			// get PuzzleInput
//			puzzleInput = getUserPuzzleInput();
			puzzleInput = PuzzleInputSampleA.createPuzzleInput();
			// check puzzleInput
			boolean check = PuzzleUtil.checkInputXAndY(puzzleInput);
			if (check) {
				boolean stopSolving = false;

				PuzzleStatus puzzleStatus = new PuzzleStatus(puzzleInput);
				while (!stopSolving) {
					// solve puzzle using a mehtod
					PuzzleSolverUtil.checkAllLinesCount(puzzleStatus, puzzleInput);
					GuaponesePuzzleSolveStatusStatistics currentPuzzleStatistics =
							PuzzleUtil.getStatistics(puzzleInput, puzzleStatus);
					boolean progression = timeline.addNextStep(GuaponesePuzzleSolveStepMethod.LINE, currentPuzzleStatistics);

					// print analyse
					if (progression) {
						PuzzleUtil.printPuzzle(puzzleInput, puzzleStatus);
						PuzzleUtil.printPuzzleStatistics(currentPuzzleStatistics);
						PuzzleUtil.printEmptyLine();
					}

					//should iteration stop or not?
					stopSolving = timeline.isPuzzleSolved() || !progression;
				}
				PuzzleUtil.printTimeline(timeline);
			}
		} catch (PuzzleSolverException e) {
			e.printStackTrace();
		}
	}

//	public PuzzleInput getUserPuzzleInput() {
//		PuzzleInput puzzleInput = new PuzzleInput();
//		System.out.println("X dimenson");
//		int xDim = keyboard.nextInt();
//		System.out.println("Y dimenson");
//		int yDim = keyboard.nextInt();
//		puzzleInput.setDimensions(xDim, yDim);
//		System.out.println( "Hello World!" );
//		int menu = -1;
//		do {
//			System.out.println("Menu choice");
//			System.out.println(tab + "0 exit");
//			System.out.println(tab + "1 show dimension");
//			System.out.println(tab + "2 input");
//			menu = keyboard.nextInt();
//		} while (menu != 0);
//		return puzzleInput;
//	}

}
