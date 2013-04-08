package jyl.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;

import jyl.util.resource.ResourceHolder;
import jyl.view.chart.CadelChart;
import jyl.view.chart.model.DkModel;
import jyl.view.control.CandelChartControl;

public class MainFrame {
	 JFrame frame = new JFrame();
	 private JSplitPane splitPane;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame mf = new MainFrame();
		mf.init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		frame.setForeground(Color.BLACK);
		 frame.setResizable(true);
		 frame.getContentPane().setBackground(Color.BLACK);//添加背景颜色
	        frame.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {System.exit(0);}
	        });
	       
	     //   frame.pack();
	        
	        
	        frame.setJMenuBar(this.createMenuBar());
	      
	        CadelChart applet = new CadelChart();
	       // applet.setr ok
	        applet.setActionType(1);
	        applet.setBackground(Color.BLACK);
	        applet.setForeground(Color.BLACK);
	        CandelChartControl cc = new CandelChartControl();
	        cc.setCandel(applet);
	        
	        DkModel dk = new DkModel();
	        applet.setDk(dk);
	        applet.setContainer(frame);
	     //   CandelListener cl = new CandelListener();
	     //   frame.addKeyListener(cl);
	       
	       // frame.setLayout(new BorderLayout());
	     //   frame.getContentPane().add(BorderLayout.CENTER, applet);
	        /*Panel p2 = new Panel();
    p2.setLayout(new BorderLayout());
    p2.add(new TextArea());*/
	       // 
	        
	        cc.init();
	        JButton jb1 = new JButton("top");
	        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
	        		applet, jb1);
	        splitPane.setOneTouchExpandable(true);
	        splitPane.setDividerLocation(600);
	        frame.pack();
	        frame.getContentPane().add(splitPane);
	        frame.setSize( Toolkit.getDefaultToolkit().getScreenSize());
	        frame.setVisible(true);
		       
	}

	private JMenuBar createMenuBar() {
		// TODO Auto-generated method stub
		JMenuBar menuBar = new JMenuBar();

        //Build the first menu.
		//JMenu menu = new JMenu("A Menu");
		JMenu menu = new JMenu("设置窗口个数");
		Font font=new Font("宋体",Font.PLAIN,16);
		//设置jmenu字体
		menu.setFont(font); 
		menuBar.add(menu);
		menu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem rbMenuItem;
        for(int i=0;i<7;i++)
        {
        	rbMenuItem = new JRadioButtonMenuItem(i+"个窗口");
            rbMenuItem.setSelected(true);
           // rbMenuItem.setMnemonic(KeyEvent.VK_R);
            group.add(rbMenuItem);
         //   rbMenuItem.addActionListener(this);
            menu.add(rbMenuItem);
        }
       
		return menuBar;
	}

}
