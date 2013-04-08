package ref.java2d;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;



	/**
	 * @param args
	 */
	
	public   class   PrintTest2   extends   JFrame   {    
		
		AffineTransform   trans;//用来控制面版缩放的对象；    

	
		public   PrintTest2(){    
		setSize(800,   600);    
		PanelZzg   panelzzg=new   PanelZzg();    
		panelzzg.setPreferredSize(new   Dimension(900,700));    
		JScrollPane   jscrollpane=new   JScrollPane(panelzzg);    

		add(jscrollpane,BorderLayout.CENTER);    

		setVisible(true);    
		}    

		public   static   void   main(String[]   args){    


		PrintTest2   a=new   PrintTest2();    
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    

		}    

		class   PanelZzg   extends   JPanel{    

		JButton   btn;    
		JLabel   lab;    
		JToggleButton   a;    
		AbstractButton   b;    
		
		public   PanelZzg(){    
		setLayout(null);    
		a=new   JToggleButton( "kdkfj ");    
		setBackground(Color.BLUE);    
		btn=new   JButton( "红太阳可是可靠   可是扩大   ");    
		btn.setBounds(120,60,120,60);    
		lab=new   JLabel( "skdfjklsdjflsksdfs ");    
		lab.setOpaque(true);    
		lab.setBounds(200,200,100,100);    
		a.setBounds(200,400,100,60);    

		add(a);    
		add(btn);    
		add(lab);    
		setSize(800,600);    
		trans=new   AffineTransform();    
		trans.scale(0.5,   0.5);//设置缩放的陪数；    

		setVisible(true);    

		}    


		public   void   paint(Graphics   g){    
		Graphics2D   g2=(Graphics2D)g;    
		g2.setTransform(trans);//设置画笔坐标系的缩放    
	//	super.paintComponent(g2);    
	//	super.paintChildren(g2);    

		}    


		}    

		}


