import java.awt.*;
import java.applet.*;
import java.awt.event.*;

/*<applet code="mrinal" width=1900 height=850>
  </applet>
 */

public class mrinal extends Applet implements ActionListener, Runnable{
	/**
	 * 
	 */
    public mrinal(){init();}
	private static final long serialVersionUID = 1L;
	Thread thr;
	Label l1, l2;
	Button b1, b2, b3;
	TextField t1, t2;
	int count = 0;
	boolean next = true;
	char[] turing;
	int len, len1, len2, n;
	String s = "", s1, s2;
	int x = 150, y = 350, j = 175, m = 0, tempx,ans=0;
	public void init(){
		l1 = new Label("Enter the first Number:");
		l2 = new Label("Enter the Second Number:");
		//b1 = new Button("Substract");
		b2 = new Button("Addition");
		b3 = new Button("Next");
		setForeground(Color.orange);
		t1 = new TextField();
		t2 = new TextField();
		setLayout(null);
		l1.setBounds(40, 30, 200, 30);
		t1.setBounds(300, 30, 200, 30);
		l2.setBounds(40, 90, 200, 30);
		t2.setBounds(300, 90, 200, 30);
		//b1.setBounds(40, 150, 200, 30);
		b2.setBounds(40, 180, 200, 30);
		b3.setBounds(300, 180, 200, 30);
		add(l1);
		add(l2);
		//add(b1);
		add(t1);
		add(t2);
		add(b2);
		add(b3);
		//b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	}
	
	public void run(){
		initialize();
		while(true){
			if(next) {
				addition();
				if(s.equals("(Q3,B) = (Q4,1,R) \nHalt and Accepted"))
					break;
				repaint();
				next = false;
			}
			try{
				Thread.sleep(2000);
			}
			catch(InterruptedException ie){}
		}
		ans=1;
		repaint();
	}
	
	public void actionPerformed(ActionEvent ae){
		if((ae.getActionCommand()).equals("Addition")){
			thr = new Thread(this);
			thr.start();
		}
		else if((ae.getActionCommand()).equals("Next")) {
			next = true;
		}
	}
	
	public void paint(Graphics g){
		if(ans==0){
			setBackground(Color.black);
			tempx = x;
			g.setColor(Color.orange);
			int k = 0, i = 0;
			while(i < turing.length){
				if(k == 0){
					g.drawString("Step Number : " + m, tempx + 150, y);
					k = 1;
				}
				g.fillOval(tempx, y + 30, 50, 50);
				g.drawString(String.valueOf(turing[i]), tempx + 25, y + 120);
				i++;
				tempx += 70;
			}
			g.drawLine(j, y + 140, j, y + 170);
			g.drawString(s + "", tempx + 20, y + 120);
			if(m>turing.length-1)
				j -= 70;
			else
				j += 70;
		}
		else{
			for(int i=0;i<turing.length;i++){
				if(turing[i]=='1')
					count++;
			}
			Font bigBoldFont = new Font("SansSerif", Font.BOLD, 24);
			g.setFont(bigBoldFont);
			g.drawString("Answer is " + count, x, y);
			y = y-20;
			//int k=0;
			tempx = x;
			int u = turing.length;
			for(int i=0;i<u;i++){
				
				g.fillOval(tempx,y+30,50,50);
				g.drawString(String.valueOf(turing[i]), tempx+25, y+120);
				tempx += 70;
			
		}
		
	}
	}
	
	public void addition(){
		if(turing[m]=='0'){
			turing[m] = '1';
			s = "(Q0,0) = (Q1,1,R)";
		}
		else if(turing[m]=='B' && m!=0){
			s = "(Q1,B) = (Q2,B,L)";
			m++;
			repaint();
			try{
				Thread.sleep(2000);
			}
			catch(InterruptedException ie){}
			for(int k=len-2;k>=0;k--){
				
				if(k==len-2){
					turing[k]='0';
					s = "(Q2,1) = (Q3,0,L)";
					m = m+1;
					repaint();
					try{
						Thread.sleep(2000);
					}
					catch(InterruptedException ie){}
				}
				else if(turing[k]=='1'){
					s = "(Q3,1) = (Q3,1,L)";
					m = m+1;
					repaint();
					try{
						Thread.sleep(2000);
					}
					catch(InterruptedException ie){}
				}
				else{
					s = "(Q3,B) = (Q4,1,R) \nHalt and Accepted";
					m = m+1;
					repaint();
					try{
						Thread.sleep(2000);
					}
					catch(InterruptedException ie){}
					break;
				}
			}
			
		}
		else{
			if(turing[m]=='1' && m<=len1){
				s = "(Q0,1) = (Q0,1,R)";
				
			}
			else if(turing[m]=='1' && m>len1 +1){
				s = "(Q1,1) = (Q1,1,R)";	
			}
		}
		m++;	
}
	
	public void initialize() {
		s1 = t1.getText();
		len1 = s1.length();
		s2 = t2.getText();
		len2 = s2.length();
		len = len1 + len2 + 3;
		turing = new char[len];
		turing[0] = 'B';
		for(int n = 0;n < turing.length;n++){
			if((n > 0) && (n <= len1))
				turing[n] = '1';
			else if(n == (len1 + 1))
				turing[n] = '0';
			else if((n >= (len1 + 2)) && (n < (len - 1)))
				turing[n] = '1';
			else
				turing[n] = 'B';
		}
	}

}