package ref.view;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import jyl.view.chart.CadelChart;
import jyl.view.chart.model.DkModel;
import jyl.view.control.CandelChartControl;
import jyl.view.control.event.CandelListener;


public class TopLevelDemo {
    public static void main(String s[]) {
        JFrame frame = new JFrame("dayKMainFrame");

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
            
        });
       JFrame f = frame;
        f.setName("dayKMainFrame");
        f.setForeground(Color.BLACK);
		 f.setResizable(true);
		 f.getContentPane().setBackground(Color.BLACK);//添加背景颜色
	        f.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {System.exit(0);}
	        });
	        CadelChart applet = new CadelChart();
	        applet.setActionType(1);
	        applet.setBackground(Color.BLACK);
	        applet.setForeground(Color.BLACK);
	        CandelChartControl cc = new CandelChartControl();
	        cc.setCandel(applet);
	        
	        DkModel dk = new DkModel();
	        applet.setDk(dk);
	        applet.setContainer(f);
	        CandelListener cl = new CandelListener();
	     //   f.addKeyListener(cl);
	     f.getContentPane().add( applet);
	   //     f.pack();
	        cc.init();

        frame.setSize( Toolkit.getDefaultToolkit().getScreenSize());
       // JLabel yellowLabel = new JLabel("");
       // yellowLabel.setOpaque(true);
      //  yellowLabel.setBackground(Color.yellow);
      //  yellowLabel.setPreferredSize(new Dimension(200, 180));

        /*JMenuBar cyanMenuBar = new JMenuBar();
        cyanMenuBar.setOpaque(true);
        cyanMenuBar.setBackground(Color.blue);
      //  cyanMenuBar.setPreferredSize(new Dimension(200, 20));

        frame.setJMenuBar(cyanMenuBar);*/
   //     frame.getContentPane().add(yellowLabel, BorderLayout.CENTER);
        JMenuItem menuItem;

        //Create the popup menu.
        JPopupMenu popup = new JPopupMenu();
        menuItem = new JMenuItem("A popup menu item");
   //     menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Another popup menu item");
   //     menuItem.addActionListener(this);
        popup.add(menuItem);

        //Add listener to the text area so the popup menu can come up.
        MouseListener popupListener = new PopupListener(popup);
        frame.addMouseListener(popupListener);
    //    applet.addMouseListener(popupListener);
        frame.pack();
        frame.setVisible(true);
    }

}
class PopupListener extends MouseAdapter {
    JPopupMenu popup;

    PopupListener(JPopupMenu popupMenu) {
        popup = popupMenu;
    }

    public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popup.show(e.getComponent(),
                       e.getX(), e.getY());
        }
      //  .setComponentZOrder(comp, index);
    }
}