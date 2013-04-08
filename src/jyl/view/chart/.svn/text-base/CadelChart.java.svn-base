package jyl.view.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import jyl.util.Log4jLoader;
import jyl.util.MyCalendar;
import jyl.view.chart.entity.DayK;
import jyl.view.chart.entity.DayPrices;
import jyl.view.chart.model.DkModel;
import jyl.view.control.CandelChartControl;
import jyl.view.control.event.CandelListener;

import org.apache.log4j.Logger;

import ref.view.PopupMenuDemo;

public class CadelChart extends JComponent
{
	Component container;
	static Logger logger = Logger.getLogger(CadelChart.class);
	 FontMetrics fontMetrics;
	 DkModel dk ;
	 int actionType = 0 ; //动作类型，可能是键盘鼠标或者窗口大小调整或者初始化
	float widthEachPart ;
	 float candleWidthbase ;
	 float widthvirgin;
	 Point crossPoint;
	 int updateType ;
	private float highvirgin;
	private float highchange;
	private float ccHeight;
	float tenCrossWidth = 1.0f ;
	List <CadelChartRectangle2D> allRectancles ;
	List <CadelChartRectangle2D> needUpdateRectancles = new ArrayList<CadelChartRectangle2D>();
	private Double yDirc;
	private Double xDirc;
	private Rectangle2D dKContent;
	private Rectangle2D xDirc1;
	private Double xDirc2;
	private Double dKContent1;
	private Double yDirc1;
	private Double yDirc2;
	private Double yDirc3;
	boolean moveContentZone;
	 /*
	  * (non-Javadoc)
	  * @see java.awt.Container#paint(java.awt.Graphics)
	  * 
	  */
	 public FontMetrics getFontMetrics() {
			return fontMetrics;
		}
		public float getWidthEachPart() {
			return widthEachPart;
		}
		public float getCandleWidthbase() {
			return candleWidthbase;
		}
		public float getWidthvirgin() {
			return widthvirgin;
		}
		
	 public DkModel getDk() {
		return dk;
	}
	public void setDk(DkModel dk) {
		this.dk = dk;
	}
	
	 public void paint(Graphics g)
	 {
		 //应该加上分配各部份显示范围
		   Graphics2D g2 = (Graphics2D) g;
		  
		   switch (actionType )
		   {
		   case 0 :  
			 paintAllCandel(g2,dk.getNow());//窗口大小调整
			   //super.paint(g);
			   break;
		   case 1 :  
			   DayPrices dps = dk.getDefault("999999");
			   paintAllCandel(g2,dps);
			   actionType=0;
			   break;
		   case 2 :
			   paintAllCandel(g2,dk.getNow());
			   actionType=0;
			  // moveTenCross(g2);
			   break;
		   }
		  
	      //  paintOneCandel(g2,10.7f,10.4f,10.9f,9.9f);
	 }
	 
	 private void paintAllCandel(Graphics2D g,DayPrices dps)
	 {
		 logger.info("paintAllCandel");
	 allRectancles =  new ArrayList<CadelChartRectangle2D>();
	 float higest = dps.getHighest();
	 float lowest = dps.getLowest();
	 logger.info("higest: "+higest+"\nlowest :"+lowest);
	  ccHeight = this.getHeight()*0.6f*0.8f;
	 float width = this.getWidth();
	 Dimension d = getSize();
	  highvirgin = ccHeight*0.05f;
	  highchange = ccHeight*0.9f/(higest-lowest);//高度比例换算
	  logger.info("height: "+d.height+"\nhighchange :"+highchange);
	  widthvirgin = width*0.05f;//两边各留5%
	 int dayNum = dps.getLast()-dps.getFirst();
	  this.widthEachPart = width*0.9f/dayNum;
	  this.candleWidthbase = widthEachPart*0.6f;
	 float candleWidth1 = candleWidthbase;//0.0f ;
	 float candleWidth2 = candleWidth1/5;
	 float margin = candleWidthbase*2/5;;
	 List<DayK> container = dps.getContainer();
	 float x = width -widthvirgin;
	 /*
	 BasicStroke bs = new BasicStroke(4.0f) ; 
	 BasicStroke bs1 = new BasicStroke() ;*/
	 int right = dps.getFirst();
	 int left = dps.getLast();
	 DayK dk = null;
	 for(int i=right ;i<=left;i++)
	 {   dk = container.get(i);
		 x= x-widthEachPart;
		 float open =dk.getOpen()*highchange;
		 float close = dk.getClose()*highchange;
		 float high = dk.getHigh()*highchange;
		 float low = dk.getLow()*highchange;
		boolean colBool = close-open>0;
		/* if(!colBool)
	       {
			  candleWidth1 = candleWidth21 ;
			  candleWidth2 = candleWidth22;
			  margin = margin2;
	       }else
	       {
	    	   candleWidth1 = candleWidth11 ;
				  candleWidth2 = candleWidth12;
				  margin = margin1;
	       }*/
		 Color col=colBool?Color.RED :Color.green;
		 g.setColor(col);
		 float height1=Math.abs(close-open);//*highchange;
	        float height2=Math.abs(high-low);//*highchange;
	        height1 = height1 > 2?height1:2;//中影线最小显示必须大于2
	        float y1=ccHeight-( open>close?open:close)-highvirgin+lowest*highchange;//坐标系原点在左上方,向右向下延伸
	        float y2=ccHeight-high-highvirgin+lowest*highchange;
	        CadelChartRectangle2D openClose=new CadelChartRectangle2D( x, y1,candleWidth1, height1 );
	        CadelChartRectangle2D highLow=new CadelChartRectangle2D( x+margin, y2, candleWidth2, height2 );
	       openClose.setColor(col);
	       highLow.setColor(col);
	       allRectancles.add(openClose);
	        allRectancles.add(highLow);
	        //     System.out.println("y1: "+y1+"\ny2 :"+y2);
	        /*
	         * 红线中空设置
	         */
	       /*if(!colBool)
		       {*/
	        g.fill(openClose); 
	        g.fill(highLow); 	
		    /*   }
	        else
	        {
	        	 g.draw(highLow)	;
	        g.fill(highLow); 
	        g.setStroke(bs);
	        g.draw(openClose)	;
	        g.setColor(this.getBackground());
	        g.fill(openClose);
	        g.setStroke(bs1);
	        }*/
	      //  g.drawString(time, x, y1-100);
	 }
	createTenCross(g);
	drawHighestAndLowest(g);
	showDKdata(g,200,400);//jyl
	 }
	 
	
	 public void showDKdata(Graphics2D g,float xPosition,float yPosition) {
		// TODO Auto-generated method stub
		 
		 if(dKContent!= null)
		 {
			 clearContent(g);
			 removeDkcontent(g);
		 }
		 BasicStroke bs = new BasicStroke(6.0f) ; 
		 g.setStroke(bs);
		 Color temp = g.getColor();
		 g.setColor(Color.blue);
		  dKContent=new Rectangle2D.Double( xPosition, yPosition,200, 200 );
		  dKContent1 = new Rectangle2D.Double( xPosition-6, yPosition-4,212, 212 );
	        g.draw(dKContent);
	        g.setColor(Color.black);
	        g.fill(dKContent);
	        g.setColor(temp);
	        generateDKdata(g);
	}
	private void generateDKdata(Graphics2D g) {
		// TODO Auto-generated method stub
		
		clearContent(g);
		Color temp = g.getColor();	
		g.setColor(Color.white);
		
		int currentNum = dk.getNow().getCurrentNum();
		DayK chose = dk.getNow().getContainer().get(currentNum);//关键
		String []values = new String[5];
		values[0] = MyCalendar.getString(chose.getChangeTime());
		values[1] = chose.getOpen()+"";
		values[2] = chose.getClose()+"";
		values[3]= chose.getHigh()+"";
		values[4] = chose.getLow()+"";
		String [] keys = {"时间","开盘价","收盘价","最高价","最低价"};
		float widthIni =(float) dKContent.getX();
		float highIni =(float) dKContent.getY();
		float higInterval = (float)dKContent.getWidth()/5.0f;
		highIni = highIni + higInterval/2;
		for(int i = 0;i<5;i++ )
		{
			String key = keys[i];
			String value = values[i];
			g.drawString(key, widthIni, highIni);
			g.drawString(value, widthIni+(float)dKContent.getWidth()/2, highIni);
			highIni = highIni + higInterval;
		}
		g.setColor(temp);
	}
	private void clearContent(Graphics2D g) {
		// TODO Auto-generated method stub
		Color temp = g.getColor();
		g.setColor(Color.BLACK);
		g.fill(dKContent);
		g.setColor(temp);
	}
	private void removeDkcontent(Graphics2D g) {
		// TODO Auto-generated method stub
		clearRectancle(g,dKContent1);
		clearRectancle(g,dKContent);
		for(CadelChartRectangle2D ccrd : allRectancles)
		 {
			 if((ccrd.intersects(dKContent1)))
			 {
				 paintRectancle(g,ccrd);
				 
			 }
		 }
		//  generateDKdata(g);
	}
	private void drawHighestAndLowest(Graphics2D g) {
		// TODO Auto-generated method stub
			float width = this.getWidth();
			int highNum = dk.getNow().getHighestNum() -  dk.getNow().getFirst();
			int lowNum = dk.getNow().getLowestNum() -  dk.getNow().getFirst();
			float highest = dk.getNow().getHighest();//
			float lowest =dk.getNow().getLowest();
			float positionXH = width -widthvirgin-widthEachPart*(highNum+1) ;//键盘移动时应该这样写
			float positionYH = ccHeight-highest*this.highchange -this.highvirgin+lowest*this.highchange;;
			float positionXL = width -widthvirgin-widthEachPart*(lowNum+1) ;//键盘移动时应该这样写
			float positionYL = ccHeight -this.highvirgin;
			g.drawString( highest+"",positionXH, positionYH-2);
			g.drawString( lowest+"",positionXL, positionYL-2);
		//
		
	}
	private void createTenCross(Graphics2D g2) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
			float height = this.getHeight();
			float width = this.getWidth();
			int currentNum = dk.getNow().getCurrentNum();
			float close = dk.getNow().getContainer().get(currentNum).getClose();//
			
			currentNum = currentNum - dk.getNow().getFirst();//用于计算所处位置
			float lowest =dk.getNow().getLowest();
			float positionX = width -widthvirgin-widthEachPart*(currentNum+1)+candleWidthbase/2 ;//键盘移动时应该这样写
			float positionY = ccHeight-close*this.highchange -this.highvirgin+lowest*this.highchange;;
		//	System.out.println("crossPoint :  " +crossPoint);
			       
			       
			        	g2.setColor(Color.white);
			        	  yDirc1=new Rectangle2D.Double( positionX-tenCrossWidth/2.0f, 0.0f,tenCrossWidth, height );
					       // Rectangle2D line2=new Rectangle2D.Double( 0, positionY-rectancelWidth/2.0f,width, rectancelWidth ); 
					        g2.fill(yDirc1); 
			      
						  xDirc=new Rectangle2D.Double( 0, positionY-tenCrossWidth/2.0f,width, tenCrossWidth ); 
							 g2.fill(xDirc);
							 showDKdata(g2,200,400);	  
							 if(xDirc.intersects(dKContent))
						        {
						        	logger.info("x1 :"+dKContent.getX()+"/nx2:"+(width-dKContent.getX()));
						        	xDirc1 = new  Rectangle2D.Double( 0, xDirc.getY(),dKContent.getX(), tenCrossWidth ); 
						        	xDirc2 =new  Rectangle2D.Double( dKContent.getX()+dKContent.getWidth(), xDirc.getY(),width-dKContent.getX(), tenCrossWidth ); 
						        	
						        }else
						        {
						        	xDirc1 = new  Rectangle2D.Double( 0, xDirc.getY(),dKContent.getX(), tenCrossWidth ); 
						        	
						        	xDirc2 =new  Rectangle2D.Double( dKContent.getX(), xDirc.getY(),width-dKContent.getX(), tenCrossWidth ); 
						        	
						        }
					   
	}
	public static void main(String args[])
	 {
		 Log4jLoader.loaddefault();
		 JFrame f = new JFrame("dayKMainFrame");
	//	 f.setName("dayKMainFrame");
		 f.setForeground(Color.BLACK);
		 f.setResizable(true);
		 f.getContentPane().setBackground(Color.BLACK);//添加背景颜色
	        f.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {System.exit(0);}
	        });
	        CadelChart applet = new CadelChart();
	        applet.setContainer(f);
	        applet.setActionType(1);
	        applet.setBackground(Color.BLACK);
	        applet.setForeground(Color.BLACK);
	        CandelChartControl cc = new CandelChartControl();
	        cc.setCandel(applet);
	        
	        DkModel dk = new DkModel();
	        applet.setDk(dk);
	        CandelListener cl = new CandelListener();
	     //   f.addKeyListener(cl);
	     f.getContentPane().add( applet);
	        f.pack();
	        cc.init();
	        f.setSize( Toolkit.getDefaultToolkit().getScreenSize());
	    //    PopupMenuDemo demo = new PopupMenuDemo();
	       // f.setJMenuBar(demo.createMenuBar());
	   //    f.setContentPane(demo.createContentPane());

	        //Create and set up the popup menu.
	    //    demo.createPopupMenu();

	        f.setVisible(true);
	        
	    }
	public int getActionType() {
		return actionType;
	}
	public void setActionType(int actionType) {
		this.actionType = actionType;
	}
	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		//局部更新
		   Graphics2D g2 = (Graphics2D) g;
			 moveTenCross(g2);//窗口大小调整	
	}
	
	
	private void clearRectancle(Graphics2D g2,Rectangle2D rd)
	{
		g2.setColor(Color.black);
		g2.fillRect((int)rd.getX(), (int)rd.getY(), (int)rd.getWidth(), (int)rd.getHeight()+1);
		rd = null;
	}
	private void recovergy(Graphics2D g2) {
		// TODO Auto-generated method stub
		int recoveryNum = dk.getNow().getRecoveryNum();
		DayK recovery = dk.getNow().getContainer().get(recoveryNum);
	}
	private void moveTenCross(Graphics2D g2) {
		// TODO Auto-generated method stub
		float height = this.getHeight();
		float width = this.getWidth();
		int currentNum = dk.getNow().getCurrentNum();
		float lowest =dk.getNow().getLowest();
		
		float positionYBase = ccHeight -this.highvirgin+lowest*this.highchange;;
		
		float close = dk.getNow().getContainer().get(currentNum).getClose();//关键
		float highPositionY = positionYBase-dk.getNow().getContainer().get(currentNum).getHigh()*this.highchange ;//关键
		float lowPositionY = positionYBase-dk.getNow().getContainer().get(currentNum).getLow()*this.highchange ;//关键
		
		currentNum =currentNum -dk.getNow().getFirst();//关键
		//
		float positionX = width -widthvirgin-widthEachPart*(currentNum+1)+candleWidthbase/2 ;//键盘移动时应该这样写
		float positionY = positionYBase-close*this.highchange ;
		float highPositionY1 =(float) dKContent1.getY();
		float lowPositionY1 =(float)dKContent1.getY()+208f;
		//	System.out.println("crossPoint :  " +crossPoint);
		       
		        if(dk.getNow().getCurrentNum()!=dk.getNow().getRecoveryNum())
		        {
		       	if(yDirc1!=null||yDirc!=null||yDirc2!=null||yDirc3!=null)	
		        	recoverYdircRelate(g2);
		        	g2.setColor(Color.white);
		        	  yDirc=new Rectangle2D.Double( positionX-tenCrossWidth/2.0f, 0.0f,tenCrossWidth, height );
		        	  if(!yDirc.intersects(dKContent1))
		        	  {//如果十字线竖线不跟浮动窗口有重叠
		        	  yDirc1=new Rectangle2D.Double( positionX-tenCrossWidth/2.0f, 0.0f,tenCrossWidth, highPositionY );
		        	  yDirc2=new Rectangle2D.Double( positionX-tenCrossWidth/2.0f, lowPositionY,tenCrossWidth, height );
		        	  }else
		        	  {
		        		 // int currentNum = (dk.getNow().getCurrentNum()-dk.getNow().getFirst())*2;
		          		CadelChartRectangle2D ccr2 = allRectancles.get(currentNum*2+1);
		          		
		          		if(ccr2.intersects(dKContent1))
		        		  {//如果k线与浮动窗口有重叠
		          			highPositionY = highPositionY<highPositionY1?highPositionY:highPositionY1;
			          		lowPositionY = lowPositionY1<lowPositionY?lowPositionY:lowPositionY1;
		          			 yDirc1=new Rectangle2D.Double( positionX-tenCrossWidth/2.0f, 0.0f,tenCrossWidth, highPositionY );
				        	  yDirc2=new Rectangle2D.Double( positionX-tenCrossWidth/2.0f, lowPositionY,tenCrossWidth, height );
				        	  
		        		  }else
		        		  {
		        			  
		        			  if(highPositionY<highPositionY1)//如果不重叠且K线在上面
		        			  {
		        				  yDirc3=new Rectangle2D.Double( positionX-tenCrossWidth/2.0f, lowPositionY,tenCrossWidth, highPositionY1-lowPositionY );
		    		        	  logger.info("highPositionY: "+highPositionY+"lowPositionY: "+lowPositionY+"highPositionY1 "+highPositionY1+"lowPositionY1:"+lowPositionY1);
		        			  }
		        			  else{
		        				  yDirc3=new Rectangle2D.Double( positionX-tenCrossWidth/2.0f, lowPositionY1,tenCrossWidth, highPositionY -lowPositionY1);
		    		        	  
		        			  }
		        			  g2.fill(yDirc3); //不能放在判断逻辑之外
		        			  highPositionY = highPositionY<highPositionY1?highPositionY:highPositionY1;
				          		lowPositionY = lowPositionY1<lowPositionY?lowPositionY:lowPositionY1;
				          		 yDirc1=new Rectangle2D.Double( positionX-tenCrossWidth/2.0f, 0.0f,tenCrossWidth, highPositionY );
					        	  yDirc2=new Rectangle2D.Double( positionX-tenCrossWidth/2.0f, lowPositionY,tenCrossWidth, height );
					        	 
		        		  }
		        	  }
		        	  // Rectangle2D line2=new Rectangle2D.Double( 0, positionY-rectancelWidth/2.0f,width, rectancelWidth ); 
				        g2.fill(yDirc1); 
				        g2.fill(yDirc2); 
				     //   if(yDirc3!=null)
				       
		        }
		        if(xDirc1!=null)
		        	recoverXdircRelate(g2);
		        g2.setColor(Color.white);
		        switch (updateType )
				   {case 0 :  //鼠标移动
					   xDirc=new Rectangle2D.Double( 0, crossPoint.getY(),width, tenCrossWidth );  
					   break;
					   case 1 : //向左或向右（没有到达边界的情况，不需要全屏幕刷新）
						  xDirc=new Rectangle2D.Double( 0, positionY-tenCrossWidth/2.0f,width, tenCrossWidth ); 
						   break;         
				   }
		        if(xDirc.intersects(dKContent1))
		        {
		        	logger.info("x1 :"+dKContent1.getX()+"/nx2:"+(width-dKContent1.getX()));
		        	xDirc1 = new  Rectangle2D.Double( 0, xDirc.getY(),dKContent1.getX(), tenCrossWidth ); 
		        	xDirc2 =new  Rectangle2D.Double( dKContent1.getX()+dKContent1.getWidth(), xDirc.getY(),width-dKContent1.getX(), tenCrossWidth ); 
		        	
		        }else
		        {
		        	xDirc1 = new  Rectangle2D.Double( 0, xDirc.getY(),dKContent1.getX(), tenCrossWidth ); 
		        	
		        	xDirc2 =new  Rectangle2D.Double( dKContent1.getX(), xDirc.getY(),width-dKContent1.getX(), tenCrossWidth ); 
		        	
		        }
		        g2.fill(xDirc2);
		        g2.fill(xDirc1);
		        drawHighestAndLowest(g2);
		        generateDKdata(g2);
	}
	private void recoverXdircRelate(Graphics2D g2) {
		// TODO Auto-generated method stub
		boolean repaintDataZone =false;
			 clearRectancle(g2,xDirc1);
			 clearRectancle(g2,xDirc2);
		for(CadelChartRectangle2D ccrd : allRectancles)
		 {
			 if((ccrd.intersects(xDirc1)||ccrd.intersects(xDirc2)))
			 {
				 paintRectancle(g2,ccrd);
				 if(ccrd.intersects(dKContent1))
				 {
					 repaintDataZone = true;
				 }
			 }
		 }
		if(repaintDataZone)
			{ 
			float xP =(float) this.dKContent.getX();
			float yP =(float) this.dKContent.getY();
			showDKdata(g2,xP,yP);
			}
			
	}
	private void recoverYdircRelate(Graphics2D g2) {
		// TODO Auto-generated method stub
		if(yDirc1!=null)
    		clearRectancle(g2,yDirc1);//清空
		if(yDirc2!=null)
    		clearRectancle(g2,yDirc2);
		if(yDirc3!=null)
    		clearRectancle(g2,yDirc3);
    	//	int roveryNum = (dk.getNow().getRecoveryNum()-dk.getNow().getFirst())*2;
    	//	CadelChartRectangle2D ccr1 = allRectancles.get(roveryNum);
    	//	CadelChartRectangle2D ccr2 = allRectancles.get(roveryNum+1);
    	//	paintRectancle(g2,ccr1);
    	//	paintRectancle(g2,ccr2);
	}
	private void paintRectancle(Graphics2D g2, CadelChartRectangle2D ccr1) {
		// TODO Auto-generated method stub
		Color before = g2.getColor();
		g2.setColor(ccr1.getColor());
		g2.fill(ccr1);
		g2.setColor(before);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	}
	public void setCrossPoint(Point point) {
		// TODO Auto-generated method stub
		this.crossPoint = point;
	}
	public int getUpdateType() {
		return updateType;
	}
	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}
	public Rectangle2D getDKContent() {
		return dKContent;
	}
	public void setDKContent(Rectangle2D content) {
		dKContent = content;
	}
	public boolean isMoveContentZone() {
		return moveContentZone;
	}
	public void setMoveContentZone(boolean moveContentZone) {
		this.moveContentZone = moveContentZone;
	}
	public Component getContainer() {
		return container;
	}
	public void setContainer(Component container) {
		this.container = container;
	}
		 
	 
	 }


