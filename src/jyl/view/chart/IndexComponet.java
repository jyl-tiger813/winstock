package jyl.view.chart;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class IndexComponet extends Component{

	/**
	 * @param args
	 */
	
	public void paint(Graphics g)
	 {
		//float height = this.getHeight();
	//	float width = this.getWidth();
		Rectangle2D yDirc1=new Rectangle2D.Double( 200f, 0.0f,4, 1024 );
		Graphics2D g2 = (Graphics2D) g;
		  g2.setColor(Color.blue);
		g2.fill(yDirc1);
	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IndexComponet ic = new IndexComponet();
		ic.setSize(20, 40);
		 JFrame f = new JFrame("IndexComponet");
		 f.setSize(300, 900);
		 f.setVisible(true);
		 f.add(ic);
		 ic.setLocation(300, 400);
	}

}
