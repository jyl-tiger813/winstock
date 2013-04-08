package jyl.datacollect.tdxdata.entity;

public class HistoryData implements Comparable<HistoryData>{
	
	private String bs;
	
	private float volumn;


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(HistoryData o) {
		// TODO Auto-generated method stub
		return (o.getVolumn() - volumn)>0? 1 : -1;
	}

	public void setBs(String bs) {
		this.bs = bs;
	}

	public String getBs() {
		return bs;
	}

	public void setVolumn(float volumn) {
		this.volumn = volumn;
	}

	public float getVolumn() {
		return volumn;
	}

}
