import java.awt.*;
import java.applet.*;
import java.awt.event.*;

/*<applet code="trail2" width=30000 height=25000>
  </applet>
 */

public class trail2 extends Applet implements ActionListener, Runnable, KeyListener{
	/**
	 * 
	 */
    public trail2(){
        init();
    }
	private static final long serialVersionUID = 1L;
	Thread thr;
	Label l1,l2;
	Button b1,b2;
	TextField t1,t2;
	char[] turing;
	int len,len1,len2,n;
	String s="",s1,s2;
	int x = 200,y=250, j = 225,m=0,tempx,ans=0,count=0;
	
	public void init(){
		l1 = new Label("Enter the first Number:");
		l2 = new Label("Enter the Second Number:");
		//b1 = new Button("Substract");
		b2 = new Button("Addition");
		setForeground(Color.orange);
		t1 = new TextField();
		t2 = new TextField();
		setLayout(null);
		l1.setBounds(40,30,200,30);
		t1.setBounds(300,30,200,30);
		l2.setBounds(40,90,200,30);
		t2.setBounds(300,90,200,30);
		//b1.setBounds(200,150,150,30);
		b2.setBounds(500,150,150,30);
		add(l1);
		add(l2);
		//add(b1);
		add(t1);
		add(t2);
		add(b2);
		//b1.addActionListener(this);
		b2.addActionListener(this);
		t2.addKeyListener(this);
		
	}
	
	public void run(){
		initialize();
		while(true){
			addition();
			if(s.equals("(Q3,B) = (Q4,1,R) \nHalt and Accepted"))
				break;
			repaint(x,y-20,2000,170);
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
	}
	
	public void keyReleased(KeyEvent k1){
		
	}
	public void keyTyped(KeyEvent k1){
	
	}
	
	public void keyPressed(KeyEvent ke){
		if(ke.getKeyCode()==KeyEvent.VK_ENTER){
			thr = new Thread(this);
			thr.start();
			System.out.println("Nhi chal raha");
		}
	}
	
	public void paint(Graphics g){
		if(ans==0){
			setBackground(Color.black);
			tempx = x;
			g.setColor(Color.orange);
			int k=0;
			int u = turing.length;
			for(int i=0;i<u;i++){
				if(k==0){
					g.drawString("Step Number:" +m, tempx+200,y);
					k+=1;
				}
				g.fillOval(tempx,y+30,50,50);
				g.drawString(String.valueOf(turing[i]), tempx+25, y+120);
				tempx += 70;
			}
			g.drawLine(j, y+ 140, j, y+170);
			g.drawString(s+"", tempx+20, y+120);
			if(m>turing.length-1)
				j -= 70;
			else
				j += 70;
			y += 170;
			if(y>600)
				y=250;
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
				repaint(x,y-20,2000,170);
				try{
					Thread.sleep(2000);
				}
				catch(InterruptedException ie){}
				for(int k=len-2;k>=0;k--){
					
					if(k==len-2){
						turing[k]='0';
						s = "(Q2,1) = (Q3,0,L)";
						m = m+1;
						repaint(x,y-20,2000,170);
						try{
							Thread.sleep(2000);
						}
						catch(InterruptedException ie){}
					}
					else if(turing[k]=='1'){
						s = "(Q3,1) = (Q3,1,L)";
						m = m+1;
						repaint(x,y-20,2000,170);
						try{
							Thread.sleep(2000);
						}
						catch(InterruptedException ie){}
					}
					else{
						s = "(Q3,B) = (Q4,1,R) \nHalt and Accepted";
						m = m+1;
						repaint(x,y-20,2000,170);
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
	
	public void initialize(){
		s1 = t1.getText();
		len1 = s1.length();
		s2 = t2.getText();
		len2 = s2.length();
		len = len1 + len2 +3;
		turing = new char[len];
		turing[0]='B';
		for(int n=0;n<turing.length;n++){
			if((n>0)&&(n<=len1))
				turing[n] = '1';
			else if(n == len1 + 1)
				turing[n] = '0';
			else if((n>=len1+2)&&(n<len-1))
				turing[n] = '1';
			else
				turing[n] = 'B';
		}
		
	}
	
	public void subtraction(){
		initialize();
		for(int w=1;w<turing.length;w++){
			if(turing[w]=='1'){
				int v=w+1;
				for(v=w+1;turing[v]=='B';v++){
					repaint(x,y-20,2000,170);
					try{
						Thread.sleep(2000);
					}
					catch(InterruptedException ie){}
					continue;
				}
				if(turing[v-1]=='1'){
					turing[v-1]='B';
					turing[turing.length-v]='B';
					repaint(x,y-20,2000,170);
					try{
						Thread.sleep(2000);
					}
					catch(InterruptedException ie){}
				}
				else{
					break;
				}
			}
		}
	}
}