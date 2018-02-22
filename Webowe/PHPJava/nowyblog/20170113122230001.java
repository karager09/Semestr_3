import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pilka extends JPanel implements ActionListener {
	static final int PRZYSPIESZENIE=1;
	int y = 0;
	int x = 0;
	int v;
	int wysOkna;
	Kulka[] kulki;
	Timer timer = new Timer(30, this);
	
	public void actionPerformed(ActionEvent e){
		/*wysOkna=(int)super.getSize().getHeight()-30;
		if(y>wysOkna) v=-v*14/15;//¿eby by³o z t³umieniem
		if(y<wysOkna)v+=PRZYSPIESZENIE;
		y+=v;*/
		
		wysOkna=(int)super.getSize().getHeight()-30;
		for(Kulka k:kulki){
			if(k.y>wysOkna && k.v>0) k.v=-k.v*14/15;//¿eby by³o z t³umieniem
			if(k.y<wysOkna)k.v+=PRZYSPIESZENIE;
			k.y+=k.v;
		}
		
		repaint();
	}
	
	class Kulka{
		int x=0;
		int y=0;
		int v=0;
		Kulka(int x, int y, int v){
			this.x=x;
			this.y=y;
			this.v=v;
		}
	}
	
	Pilka(){
		super();
		wysOkna=(int)super.getSize().getHeight()-30;
		int szerOkna=(int)super.getSize().getWidth()-30;
		System.out.println(wysOkna+"  "+szerOkna);
		Random r=new Random();
		kulki=new Kulka[20];
		for(int i=0;i<20;i++){
			//kulki[i]=new Kulka(r.nextInt(szerOkna),r.nextInt(wysOkna),0);
			kulki[i]=new Kulka(r.nextInt(450),r.nextInt(400),0);
		}
	}
	
	
	public void paintComponent(Graphics g){
		//x = super.getSize().width/2;
		super.setBackground(Color.lightGray);
		super.paintComponent(g);
		g.setColor(Color.black);
		for(Kulka k:kulki)
		g.fillOval(k.x,k.y,15,15);
		g.drawLine(0, (int)super.getSize().getHeight()-15,  super.getSize().width, (int)super.getSize().getHeight()-15);
		timer.start();
	}
	
	public static void main(String[] args){
		JFrame f = new JFrame("Odbijanie pi³eczki zgodznie z zasadami fizyki (prawie)");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,500);
        f.add(new Pilka());
        f.setVisible(true);
	}
}