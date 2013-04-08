package ref.view.table;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class TableSortTest {

	//334

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new TableSortFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
 class SortFilterModel {

}

 class TableSortFrame extends JFrame {
     private int DEFAULT_WIDTH = 400;
	private int DEFAULT_HEIGHT =200;
	private Object[] columnNames= {"today","tommorrow"};

	public TableSortFrame()
     {
    	setTitle("TableSortTest"); 
    	setSize(DEFAULT_WIDTH ,DEFAULT_HEIGHT );
    	DefaultTableModel model = new DefaultTableModel(cells,columnNames);
    	final SortFilterModel sorter = new SortFilterModel(model);
    //final JTable
     }
	private Object[][]cells=
	{
			{"very","oliver"},
			{"good","nightmare"},
			{"night","quickly"},
			{"year","fast"}
	};
}