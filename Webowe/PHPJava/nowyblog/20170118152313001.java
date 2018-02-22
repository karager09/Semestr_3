import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PilkaSinus extends JPanel implements ActionListener {
	int y = 0;
	int x = 0;
	int wysOkna;
	double czas=0;
	Timer timer = new Timer(25, this);
	
	public void actionPerformed(ActionEvent e){
		wysOkna=(int)super.getSize().getHeight()-30;
		czas+=0.05;
		y=wysOkna-(int)Math.abs(Math.sin(czas)*wysOkna*Math.exp(-czas/100));
		repaint();
	}
	
	
	public void paintComponent(Graphics g){
		x = super.getSize().width/2;
		super.setBackground(Color.lightGray);
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillOval(x,y,15,15);
		g.drawLine(0, (int)super.getSize().getHeight()-10,  super.getSize().width, (int)super.getSize().getHeight()-10);
		timer.start();
	}
	
	public static void main(String[] args){
		JFrame f = new JFrame("Odbijanie pi³eczki przez sinus i exp");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,500);
        f.add(new PilkaSinus());
        f.setVisible(true);
	}
}



