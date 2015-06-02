package janvenstermans;

import janvenstermans.example.PuzzleInputSampleA;
import janvenstermans.model.PuzzleInput;
import janvenstermans.model.PuzzleStatus;
import janvenstermans.model.PuzzleUtil;
import janvenstermans.solver.PuzzleSolverException;
import janvenstermans.solver.PuzzleSolverUtil;

import java.util.Scanner;

/**
 * Console version of application.
 *
 * @author Jan Venstermans
 *
 */
public class App
{

	private PuzzleInput puzzleInput;
	private String tab = "  ";
	private Scanner keyboard = new Scanner(System.in);

    public static void main( String[] args )
    {
		new App().run();
    }

	public void run() {
		try {
			// get PuzzleInput
//			puzzleInput = getUserPuzzleInput();
			puzzleInput = PuzzleInputSampleA.createPuzzleInput();

			// check puzzleInput
			boolean check = PuzzleUtil.checkInputXAndY(puzzleInput);
			if (check) {
				PuzzleStatus puzzleStatus = new PuzzleStatus(puzzleInput);
				PuzzleUtil.printPuzzle(puzzleInput, puzzleStatus);

				PuzzleSolverUtil.checkCount(puzzleStatus, puzzleInput);
				PuzzleUtil.printPuzzle(puzzleInput, puzzleStatus);

				PuzzleSolverUtil.checkCount(puzzleStatus, puzzleInput);
				PuzzleUtil.printPuzzle(puzzleInput, puzzleStatus);

				PuzzleSolverUtil.checkCount(puzzleStatus, puzzleInput);
				PuzzleUtil.printPuzzle(puzzleInput, puzzleStatus);
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
