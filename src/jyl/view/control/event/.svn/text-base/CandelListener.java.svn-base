package jyl.view.control.event;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.apache.log4j.Logger;

import jyl.view.chart.CadelChart;
import jyl.view.chart.entity.DayPrices;
import jyl.view.chart.model.DkModel;
import jyl.view.dao.imp.PriceDaoJdbcImp;

public class CandelListener implements KeyListener , MouseMotionListener,MouseListener
{
	static Logger logger = Logger.getLogger(CandelListener.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
		CadelChart cct; 
		
	public CadelChart getCct() {
			return cct;
		}

		public void setCct(CadelChart cct) {
			logger.info("setCct : " +cct );
			this.cct = cct;
		}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	//	System.out.println("jyl:"+e.getKeyChar()
		logger.info("getKeyCode" +e.getKeyCode()+"thiscct "+this.cct );
		int currentNum = cct.getDk().getNow().getCurrentNum();
		int keyCode = e.getKeyCode();
		boolean repainOrUpdate=true;//全部重绘或部分更新,全部重绘为true
		DkModel dk = cct.getDk();
		switch (keyCode)
		{
		case 37://向左键
			
			if(currentNum > dk.getNow().getLast()-1)
			{
				logger.info("currentNum :");
				if(!dk.LeftForward())
					return;
				currentNum = currentNum+1;
				repainOrUpdate = true;
			}else
			{
				currentNum = currentNum+1;
				repainOrUpdate = false;
			}
		break;
		
		case 39: //向右键
			if(currentNum <dk.getNow().getFirst()+1)
			{
				repainOrUpdate = true;
				if(currentNum<1)
					return;
				currentNum = currentNum-1;
				dk.rightForward();
				
			}else
			{
				repainOrUpdate = false;
				currentNum = currentNum-1;
			}
			break;
		case 38://向上,放大
			if(!dk.upWard())
				return;
			repainOrUpdate = true;
			break;
			
		case 40://向下,缩小
			if(!dk.downWard())
				return;
			repainOrUpdate = true;
			break;
		}
		
			
		cct.getDk().getNow().setCurrentNum(currentNum);
		logger.info("fisrt :"+dk.getNow().getFirst());
		logger.info("currentNum :"+currentNum);
		if(	repainOrUpdate )
		{
			cct.setActionType(2);
		cct.repaint()	; 
		
		}
		else
		{
			cct.setUpdateType(1);
		cct.update(cct.getGraphics());
		}
		//CadelChart cc = (CadelChart)e.getComponent();
		/*DayPrices dp = cc.getDk().getNow();
			if(chooseNum<0||chooseNum>dp.getContainer().size()-1)//超过图形范围的,不做响应
			return;
		cc.getDk().getNow().setCurrentNum(chooseNum);
		cc.update(cc.getGraphics());
*/		//}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//logger.info("jyl:"+e.getKeyChar()+" "+e.getKeyCode() );
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//logger.info("jyl:"+e.getKeyChar()+" "+e.getKeyCode() );
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		fixTenCrossPosition(e);
		
		//}
	}

	private void fixTenCrossPosition(MouseEvent e) {
		// TODO Auto-generated method stub
Point p = e.getPoint();
		
		CadelChart cc = (CadelChart)e.getComponent();
		cc.setCrossPoint (p);
		cc.setUpdateType(0);
		float x = (float)e.getPoint().getX();
		DayPrices dp = cc.getDk().getNow();
		int chooseNum = (int) ((cc.getWidth()-cc.getWidthvirgin()-x)/cc.getWidthEachPart())+cct.getDk().getNow().getFirst();
		logger.info("chooseNum : "+chooseNum);
		logger.info("first : "+cc.getDk().getNow().getFirst());
		if(chooseNum<cc.getDk().getNow().getFirst()||chooseNum>cc.getDk().getNow().getLast())//超过图形范围的,不做响应
			return;//问题很可能就在这里
		cc.getDk().getNow().setCurrentNum(chooseNum);
		cc.update(cc.getGraphics());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		logger.info("mouseClicked");
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(cct.getDKContent().contains(e.getX(), e.getY()))
		{
			cct.setMoveContentZone(true);
			
		}
		else
		{
			cct.setMoveContentZone(false);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		logger.info("mouseDragged");
		if(cct.isMoveContentZone())
		{
		cct.showDKdata((Graphics2D)(cct.getGraphics()), e.getX(), e.getY());
		}
		fixTenCrossPosition(e);
	}
	}

