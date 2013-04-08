package jyl.view.control;

import java.awt.Component;

import org.apache.log4j.Logger;

import jyl.view.chart.CadelChart;
import jyl.view.control.event.CandelListener;

public class CandelChartControl {

	static Logger logger = Logger.getLogger(CandelChartControl.class);
	CadelChart candel;
	
	public CadelChart getCandel() {
		return candel;
	}
	public void setCandel(CadelChart candel) {
		this.candel = candel;
	}
	/**
	 * @param args
	 */
	public void init()
	{
		CandelListener clistener = new CandelListener();
		clistener.setCct(candel);
		logger.info("candeljyl"+candel);
		candel.addMouseMotionListener(clistener);
		candel.addMouseListener(clistener);
		Component cp = candel.getContainer();//.getParent().getParent().getParent();
		cp.addKeyListener(clistener);
		//candel.addKeyListener(clistener);
		cp.addMouseListener(clistener);
	//	cp.addKeyListener(clistener);
		
	//	candel.addKeyListener(clistener);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	

}
