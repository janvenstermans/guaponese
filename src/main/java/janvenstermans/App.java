package janvenstermans;

import janvenstermans.model.PuzzleInput;

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
		puzzleInput = new PuzzleInput();
		getDimensions();
		int menu = -1;
		do {
			System.out.println("Menu choice");
			System.out.println(tab + "0 exit");
			System.out.println(tab + "1 show dimension");
			System.out.println(tab + "2 input");
			menu = keyboard.nextInt();
		} while (menu != 0);
	}

	public void getDimensions() {
		System.out.println("X dimenson");
		int xDim = keyboard.nextInt();
		System.out.println("Y dimenson");
		int yDim = keyboard.nextInt();
		puzzleInput.setDimensions(xDim, yDim);
		System.out.println( "Hello World!" );
	}
}
