package jyl.view.table.model;

import javax.swing.table.AbstractTableModel;

public class SortFilterModel extends AbstractTableModel {

	/**
	 * @param args
	 * 排序数据模型,保持多列数据顺序的引用
	 */
	
	int sortedColumn ; //当前选中要进行排序的列
	
	Object [][] model ;
	Integer [][] sortref ;//保存每一列的排列顺序,如果无法得到选中列下标,则采用Map(String,Integer[])方式来保存
	public SortFilterModel (Object [][] model)
	
	{
		this.model = model;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        arrayInitTest();
	}

	private static void arrayInitTest() {
		// TODO Auto-generated method stub
		Integer [][] sortref =new Integer[10][10];
		System.out.println("length :"+sortref.length);
		for(Integer arr[] :sortref)
		{
			for(Integer i :arr)
			{
				System.out.println("i : "+i);
			}
		}
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return sortref.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return sortref[0].length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSortedColumn(int sortedColumn) {
		this.sortedColumn = sortedColumn;
	}

}
