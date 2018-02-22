import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Szachownica {
    
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
        JFrame f = new JFrame("Szachownica");
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setSize(250,250);
        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    }
}


class MyPanel extends JPanel {
	public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
       
    }

    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension d = super.getSize();
        g.setColor(Color.BLACK);
        int w=1;
        char k='A';
        int dt;
        if(d.width>d.height){
        	dt = d.height/12;
        }
        else{
        	dt = d.width/12;
        }
        g.setFont(new Font("TimesRoman", Font.BOLD, dt/2)); 
        for(int i=0; i<dt*12; i+=dt){
        	for(int j=0; j<dt*12; j+=dt){
        		
        		 if(j==0 && i!=0 && i!=11*dt){
        			 g.drawString(""+w,j,i+dt*1/2);
        		
        		 }
        		 else if(j==11*dt && i!=dt*11 && i!=0){
        			 g.drawString(""+w,j,i+dt*1/2);
        			 w++;
        		 }
        		 else if(i==0 && j!=0 && j!=11*dt){
        			 g.drawString(""+k,j+dt/4,i+dt/2);
        			 k++;
        		 }
        		 else if((i==11*dt)&&(j!=11*dt) && j!=0){
        			 g.drawString(""+k,j+dt/4,i+dt/2);
        			 k++;
        		 }
        		 else if(i!=0 && j!=0 && i!=11*dt) {
        			 if((i+j)%(2*dt)==0)
        				 g.setColor(Color.WHITE);
        			 g.fillRect(j,i,dt,dt);
        			 g.setColor(Color.black);
        			 
        		 }
        		 if((i==dt)&&(j==dt)){k='A';};
        	}
        }
    }
}