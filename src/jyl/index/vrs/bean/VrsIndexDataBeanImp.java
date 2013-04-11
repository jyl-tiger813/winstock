package jyl.index.vrs.bean;
public  class VrsIndexDataBeanImp extends VrsIndexDataBeanAbstract 



	{
	private int stockCodeInt;
	
	private Double changeRatio;
	
	public Double getAve_change_ratio() {
		return ave_change_ratio;
	}



	public Double getChange_ratio_close_begin() {
		return change_ratio_close_begin;
	}



	public Double getChange_ratio_avg_close() {
		return change_ratio_avg_close;
	}



	public void setAve_change_ratio(Double ave_change_ratio) {
		this.ave_change_ratio = ave_change_ratio;
	}



	public void setChange_ratio_close_begin(Double change_ratio_close_begin) {
		this.change_ratio_close_begin = change_ratio_close_begin;
	}



	public void setChange_ratio_avg_close(Double change_ratio_avg_close) {
		this.change_ratio_avg_close = change_ratio_avg_close;
	}

	private Double ave_change_ratio ;//今天昨天均价变化率
	private Double change_ratio_close_begin ;//今天收盘 开盘
	private Double change_ratio_avg_close ;//今天均价收盘

	public void setStockCodeInt(int stockCodeInt) {
		this.stockCodeInt = stockCodeInt;
	}



	public int getStockCodeInt() {
		return stockCodeInt;
	}

	public void setChangeRatio(Double changeRatio) {
		this.changeRatio = changeRatio;
	}

	public Double getChangeRatio() {
		return changeRatio;
	}
	}
