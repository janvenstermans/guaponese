package janvenstermans.solver;

/**
 * Console version of application.
 *
 * @author Jan Venstermans
 *
 */
public class GuaponesePuzzleSolveStatusStatistics
{
	final private long fieldsAmountTotal;
	private Long fieldsAmountSolved;

	final private int rowsAmountTotal;
	private Integer rowsAmountSolved;

	final private int columnsAmountTotal;
	private Integer columnsAmountSolved;

	/**
	 *
	 * @param columnsAmountTotal x
	 * @param rowsAmountTotal y
	 */
	public GuaponesePuzzleSolveStatusStatistics(int columnsAmountTotal, int rowsAmountTotal) {
		this.rowsAmountTotal = rowsAmountTotal;
		this.columnsAmountTotal = columnsAmountTotal;
		this.fieldsAmountTotal = ((long) rowsAmountTotal) * ((long) columnsAmountTotal) ;
	}

	/* GETTERS */

	public long getFieldsAmountTotal() {
		return fieldsAmountTotal;
	}

	public Long getFieldsAmountSolved() {
		return fieldsAmountSolved;
	}

	public int getRowsAmountTotal() {
		return rowsAmountTotal;
	}

	public Integer getRowsAmountSolved() {
		return rowsAmountSolved;
	}

	public int getColumnsAmountTotal() {
		return columnsAmountTotal;
	}

	public Integer getColumnsAmountSolved() {
		return columnsAmountSolved;
	}

	/* SETTERS */

	public void setFieldsAmountSolved(Long fieldsAmountSolved) {
		this.fieldsAmountSolved = fieldsAmountSolved;
	}

	public void setRowsAmountSolved(Integer rowsAmountSolved) {
		this.rowsAmountSolved = rowsAmountSolved;
	}

	public void setColumnsAmountSolved(Integer columnsAmountSolved) {
		this.columnsAmountSolved = columnsAmountSolved;
	}

	/* METHODS */

	public boolean isSolved() {
		if (fieldsAmountSolved != null) {
			return fieldsAmountSolved == fieldsAmountTotal;
		}
		return false;
	}
}
