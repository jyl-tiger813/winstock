package jyl.datacollect.sse.entity;

import java.util.Calendar;
public class Record {
	private Calendar time;
	private float maketTotal;//市价总值（亿元 ）
	private float circulateTotal;//流通市值（亿元 ）
	private float volumn;//成交量   （万股） 
	private float amount;//成交金额   （亿元 ）
	private float hundredShare;//成交笔数  （万笔）
	private float perGet;//平均市盈率  
	private float changeRatio;//换手率
	private float amountPerChange;//每笔成交金额(万元)
	public float getAmountPerChange() {
		return amountPerChange;
	}

	public void setAmountPerChange(float amountPerChange) {
		this.amountPerChange = amountPerChange;
	}

	
	public Record(Calendar time, float maketTotal, float circulateTotal, float volumn, float amount, float hundredShare, float perGet) {
		super();
		// TODO Auto-generated constructor stub
		this.time = time;
		this.maketTotal = maketTotal;
		this.circulateTotal = circulateTotal;
		this.volumn = volumn;
		this.amount = amount;
		this.hundredShare = hundredShare;
		this.perGet = perGet;
	}

	public Record() {
		super();
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getCirculateTotal() {
		return circulateTotal;
	}

	public void setCirculateTotal(float circulateTotal) {
		this.circulateTotal = circulateTotal;
	}

	public float getHundredShare() {
		return hundredShare;
	}

	public void setHundredShare(float hundredShare) {
		this.hundredShare = hundredShare;
	}

	public float getMaketTotal() {
		return maketTotal;
	}

	public void setMaketTotal(float maketTotal) {
		this.maketTotal = maketTotal;
	}

	public float getPerGet() {
		return perGet;
	}

	public void setPerGet(float perGet) {
		this.perGet = perGet;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}
	public float getVolumn() {
		return volumn;
	}
	public void setVolumn(float volumn) {
		this.volumn = volumn;
	}

	@Override
	public String toString() {
		/*private float maketTotal;//市价总值 (亿元 )
		private float circulateTotal;//流通市值  (亿元 )
		private float volumn;//成交量 (万股 )
		private float amount;//成交金额 (亿元 ) 
		private float hundredShare;//成交笔数(万笔 )
		private float perGet;//平均市盈率
*/		StringBuffer sf=new StringBuffer();
		sf.append(time.get(Calendar.YEAR)+":"+(time.get(Calendar.MONTH)+1)+":"+time.get(Calendar.DATE));
		sf.append("  市价总值  "+maketTotal);
		sf.append("  流通市值  "+circulateTotal);
		sf.append("  成交量  "+volumn);
		sf.append("  成交金额  "+amount);
		sf.append("  成交笔数  "+hundredShare);
		sf.append(" 平均市盈率  "+perGet);
		return sf.toString();
	}

	public void setChangeRatio(float changeRatio) {
		this.changeRatio = changeRatio;
	}

	public float getChangeRatio() {
		return changeRatio;
	}
	
}
