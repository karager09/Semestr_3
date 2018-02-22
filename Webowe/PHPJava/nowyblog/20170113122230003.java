import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Wykres {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    
    
    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Wykres");
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setSize(250,250);
        f.add(new MyPanel1());
        f.pack();
        f.setVisible(true);
    }
}


class MyPanel1 extends JPanel {
	final double zakres_x0=-2;
	final double zakres_xk=3;
	final double dx=0.05;
	static double a=1,b=-2,c=-1;
	
	double max_y=Math.max(Math.max(Math.abs(funkcja(zakres_x0)), Math.abs(funkcja(zakres_xk))),Math.abs(funkcja(-b/(2*a))));
	
	
	static double funkcja(double x){
    	return a*x*x+b*x+c;
    }
	
	public MyPanel1() {
        setBorder(BorderFactory.createLineBorder(Color.black));
       
    }

    public Dimension getPreferredSize() {
        return new Dimension(700,500);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension d = super.getSize();
        g.setColor(Color.BLACK);
        g.drawLine(0,(int)d.getHeight()/2, (int) d.getWidth(), (int)d.getHeight()/2);
        g.drawLine((int)(d.getWidth()*(0-zakres_x0)/(zakres_xk-zakres_x0)),0,  (int)(d.getWidth()*(0-zakres_x0)/(zakres_xk-zakres_x0)), (int)d.getHeight());
        
        for(int i=(int)zakres_x0;i<zakres_xk;i++){
        	g.drawString(i+"", (int)Math.round(d.getWidth()*(i-zakres_x0)/(zakres_xk-zakres_x0)),(int)d.getHeight()/2+15 );
        	g.drawLine((int)Math.round(d.getWidth()*(i-zakres_x0)/(zakres_xk-zakres_x0)),
        			(int)d.getHeight()/2-5, 
        			(int)Math.round(d.getWidth()*(i-zakres_x0)/(zakres_xk-zakres_x0)),
        			(int)d.getHeight()/2+5);
        }
        
        for(int i=(int)-max_y;i<max_y;i++){
        	g.drawString(i+"", (int)(d.getWidth()*(0-zakres_x0)/(zakres_xk-zakres_x0))+8,(int)Math.round(d.getHeight()/2-d.getHeight()*(i)/(2*max_y))+5 );
        	
        	g.drawLine((int)(d.getWidth()*(0-zakres_x0)/(zakres_xk-zakres_x0))-5,
        			(int)Math.round(d.getHeight()/2-d.getHeight()*(i)/(2*max_y)), 
        			(int)(d.getWidth()*(0-zakres_x0)/(zakres_xk-zakres_x0))+5,
        			(int)Math.round(d.getHeight()/2-d.getHeight()*(i)/(2*max_y)));
        }

        //System.out.println(max_y);
        
        
        g.setColor(Color.RED);
        for(double i=zakres_x0;i<zakres_xk;i+=dx){
        	g.drawLine((int)Math.round(d.getWidth()*(i-zakres_x0)/(zakres_xk-zakres_x0)),
        			(int)Math.round(d.getHeight()/2-d.getHeight()*(funkcja(i))/(2*max_y)), 
        			(int)Math.round(d.getWidth()*(i+dx-zakres_x0)/(zakres_xk-zakres_x0)),
        			(int)Math.round(d.getHeight()/2-d.getHeight()*(funkcja(i+dx))/(2*max_y)));
        } 
    }
}