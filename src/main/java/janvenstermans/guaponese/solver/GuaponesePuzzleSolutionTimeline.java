package janvenstermans.guaponese.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains timeline on the solving of the puzzle: contains the consecutive statistics and methods used.
 *
 * @author Jan Venstermans
 *
 */
public class GuaponesePuzzleSolutionTimeline
{
	private List<GuaponesePuzzleSolvingStep> solvingStepList = new ArrayList<GuaponesePuzzleSolvingStep>();

	/**
	 * Returns unmodifiable list.
	 * @return
	 */
	public List<GuaponesePuzzleSolvingStep> getSolvingStepList() {
		return Collections.unmodifiableList(solvingStepList);
	}

	/**
	 * Add info of next solving step to the timeline. The step will only be registered when there is progression.
	 *
	 * @param method
	 * @param statusStatistics
	 * @return boolean indicating whether info has been registered (only when there is progression)
	 */
	public boolean addNextStep(GuaponesePuzzleSolveStepMethod method,
							   GuaponesePuzzleSolveStatusStatistics statusStatistics) {
		if (method != null && statusStatistics != null) {
			GuaponesePuzzleSolvingStep currentStep = new GuaponesePuzzleSolvingStep(method, statusStatistics);
			GuaponesePuzzleSolvingStep lastStep = getLastStep();
			if (lastStep == null || isMoreSolved(lastStep.getStatusStatistics(), currentStep.getStatusStatistics())) {
				solvingStepList.add(currentStep);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the info of the last registerd solving step. Returns null when no previous step available.
	 * @return
	 */
	public GuaponesePuzzleSolvingStep getLastStep() {
		if (solvingStepList != null && solvingStepList.size() > 0) {
			return solvingStepList.get(solvingStepList.size() - 1);
		}
		return null;
	}

	private boolean isMoreSolved(GuaponesePuzzleSolveStatusStatistics puzzleStatisticsFirst,
								 GuaponesePuzzleSolveStatusStatistics puzzleStatisticsSecond) {
		if (puzzleStatisticsFirst != null && puzzleStatisticsSecond != null) {
			return puzzleStatisticsSecond.getFieldsAmountSolved() > puzzleStatisticsFirst.getFieldsAmountSolved();
		}
		return false;
	}

	public boolean isPuzzleSolved() {
		GuaponesePuzzleSolvingStep lastStep = getLastStep();
		if (lastStep != null) {
			return lastStep.getStatusStatistics().isSolved();
		}
		return false;
	}

	/**
	 * Inner class with info for each step the solving of the Guaponese puzzle has progressed.
	 */
	public class GuaponesePuzzleSolvingStep {
		final private GuaponesePuzzleSolveStepMethod method;

		final private GuaponesePuzzleSolveStatusStatistics statusStatistics;

		public GuaponesePuzzleSolvingStep(GuaponesePuzzleSolveStepMethod method,
										  GuaponesePuzzleSolveStatusStatistics statusStatistics) {
			this.method = method;
			this.statusStatistics = statusStatistics;
		}

		public GuaponesePuzzleSolveStepMethod getMethod() {
			return method;
		}

		public GuaponesePuzzleSolveStatusStatistics getStatusStatistics() {
			return statusStatistics;
		}
	}
}
