package prog;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainProgram extends JFrame{
	public double S = 201.45, b = 5.285, G = 73000, x = 0.24, I = 660000, V, H, p, a, Cy0, Cya, Cyd, Cx, mz, mzw, mza_, mza, mzd, g = 9.81;
	public TextField TS, Tb, TG, Tx, TI, TV, TH, Tp, Ta, TCy0, TCya, TCyd, TCx, Tmz, Tmzw, Tmza_, Tmza, Tmzd;
	public double ddd, C1, C2, C3, C4, C5, C9, C16, Cy, Ca, Cd;
	public TextField Td, TC1, TC2, TC3, TC4, TC5, TC9, TC16, TCy, TCa, TCd;
	public double T, DT, TD, TF, DD;
	public TextField TT, TDT, TTD, TTF, TDD;
	public Button S1, S2, S3;
	public double Ks = 0.112, xv = -17.86, kwz = 1.0, Twz = 0.7;
	public int switcher = 1;
	TextField Table[][];
	double min, max;
	Scrollbar SB;
	Graphics graph;
	public class Data{
		public double[] Y = new double[5];
		public double[] X = new double[5];
		public double NY, DV;
		
		public Data(double Y1, double Y2, double Y3, double Y4, double Y5) {
			Y[0] = Y1;
			Y[1] = Y2;
			Y[2] = Y3;
			Y[3] = Y4;
			Y[4] = Y5;
		}
	}
	public ArrayList<Data> Database = new ArrayList();
		
	public MainProgram(String s){
		super(s);
		setLayout(null);
		setSize(1545, 830);
		setVisible(true);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		graph = getGraphics();
		TT = CreatTF("0", 85, 60);
		TDT = CreatTF("0.01", 85, 120);
		TTD = CreatTF("0", 85, 180);
		TTF = CreatTF("20", 85, 240);
		TDD = CreatTF("0.5", 85, 300);
		TV = CreatTF("97.2", 85, 360);
		TH = CreatTF("500", 270, 60);
		Tp = CreatTF("0.1190", 270, 120);
		Ta = CreatTF("338.36", 270, 180);
		TCy0 = CreatTF("-0.255", 270, 240);
		TCya = CreatTF("5.78", 270, 300);
		TCyd = CreatTF("0.2865", 270, 360);
		TCx = CreatTF("0.046", 455, 60);
		Tmz = CreatTF("0.20", 455, 120);
		Tmzw = CreatTF("-13.0", 455, 180);
		Tmza_ = CreatTF("-3.8", 455, 240);
		Tmza = CreatTF("-1.83", 455, 300);
		Tmzd = CreatTF("-0.96", 455, 360);
		S1 = new Button("1");
		S1.setBounds(150, 410, 40, 40);
		S1.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		S1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				S1.setBackground(Color.green);
				S2.setBackground(Color.red);
				S3.setBackground(Color.red);
				switcher = 1;
			}			
		});
		S1.setBackground(Color.green);
		add(S1);
		S2 = new Button("2");
		S2.setBounds(200, 410, 40, 40);
		S2.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		S2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				S1.setBackground(Color.red);
				S2.setBackground(Color.green);
				S3.setBackground(Color.red);
				switcher = 2;
			}			
		});
		S2.setBackground(Color.red);
		add(S2);
		S3 = new Button("3");
		S3.setBounds(250, 410, 40, 40);
		S3.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		S3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				S1.setBackground(Color.red);
				S2.setBackground(Color.red);
				S3.setBackground(Color.green);
				switcher = 3;
			}			
		});
		S3.setBackground(Color.red);
		add(S3);
		Button Input = new Button("Розрахувати");
		Input.setBounds(350, 410, 200, 40);
		Input.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		Input.addActionListener(new Calculate());
		add(Input);
		TC1 = CreatCTF("", 690, 710);
		TC2 = CreatCTF("", 860, 710);
		TC3 = CreatCTF("", 1030, 710);
		TC4 = CreatCTF("", 1200, 710);
		TC5 = CreatCTF("", 1370, 710);
		TC9 = CreatCTF("", 690, 750);
		TC16 = CreatCTF("", 860, 750);
		TCy = CreatCTF("", 1030, 750);
		TCa = CreatCTF("", 1200, 750);
		TCd = CreatCTF("", 1370, 750);
		Td = CreatCTF("", 730, 660);
		Button BT= new Button("T");
		BT.setBounds(18, 520, 70, 50);
		BT.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		add(BT);
		Button BDV= new Button("δв");
		BDV.setBounds(88, 520, 100, 50);
		BDV.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		add(BDV);
		Button Bny= new Button("ny");
		Bny.setBounds(188, 520, 140, 50);
		Bny.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		add(Bny);
		Button BY1= new Button("Кут тангажу");
		BY1.setBounds(328, 520, 140, 50);
		BY1.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		add(BY1);
		Button BY3= new Button("Кут атаки");
		BY3.setBounds(468, 520, 140, 50);
		BY3.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		add(BY3);
		Table = new TextField[8][5];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 5; j++) {
				Table[i][j] = new TextField("");
				Table[i][j].setFont(new Font("TimesRoman", Font.PLAIN, 16));
				Table[i][j].setEditable(false);
				add(Table[i][j]);
			}
			Table[i][0].setBounds(18, 570+i*25, 70, 25);
			Table[i][1].setBounds(88, 570+i*25, 100, 25);
			Table[i][2].setBounds(188, 570+i*25, 140, 25);
			Table[i][3].setBounds(328, 570+i*25, 140, 25);
			Table[i][4].setBounds(468, 570+i*25, 140, 25);
		}
		SB = new Scrollbar(Scrollbar.VERTICAL);
		SB.setBounds(610, 520, 20, 250);
		SB.setBackground(Color.GRAY);
		SB.setMinimum(1);
		add(SB);
	}
	
	public void paint (Graphics gr) {
		gr.setColor(Color.lightGray);
		gr.fillRect(0, 0, 1545, 830);
		gr.setColor(Color.BLACK);
		gr.drawLine(0, 500, 650, 500);
		gr.drawLine(650, 620, 1545, 620);
		gr.drawLine(650, 0, 650, 830);		
		gr.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
		gr.drawString("Панель введння даних", 170, 60);
		gr.drawString("Таблиця даних", 230, 530);
		gr.drawString("Графік", 1050, 60);
		gr.drawString("Розрахунок коефіцієнтів", 1000, 660);
		gr.drawString("Демпфер:", 35, 465);
		gr.drawString("T  = ", 35, 116);
		gr.drawString("DT = ", 35, 176);
		gr.drawString("TD = ", 35, 236);
		gr.drawString("TF = ", 35, 296);
		gr.drawString("DD = ", 35, 356);
		gr.drawString("V  = ", 35, 416);
		gr.drawString("H  = ", 222, 116);
		gr.drawString("ρ  = ", 222, 176);
		gr.drawString("a  = ", 222, 236);
		gr.drawString("C  = ", 222, 296);
		gr.drawString("C  = ", 222, 356);
		gr.drawString("C  = ", 222, 416);
		gr.drawString("C  = ", 409, 116);
		gr.drawString("m  = ", 409, 176);
		gr.drawString("m  = ", 409, 236);
		gr.drawString("m  = ", 409, 296);
		gr.drawString("m  = ", 409, 356);
		gr.drawString("m  = ", 409, 416);
		gr.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 
		gr.drawString("0", 46, 423);
		gr.drawString("0", 239, 123);
		gr.drawString("н", 236, 243);
		gr.drawString("y0", 236, 303);
		gr.drawString("y", 236, 363);
		gr.drawString("α", 236, 338);
		gr.drawString("y", 236, 423);
		gr.drawString("δв", 236, 398);
		gr.drawString("x", 425, 123);
		gr.drawString("z0", 430, 183);	
		gr.drawString("z", 430, 243);
		gr.drawString("ωz", 428, 223);
		gr.drawString("__", 430, 210);
		gr.drawString("z", 430, 303);
		gr.drawString("α", 428, 283);
		gr.drawString(".", 430, 273);
		gr.drawString("_", 429, 265);
		gr.drawString("z", 430, 363);
		gr.drawString("α", 428, 343);
		gr.drawString("z", 430, 423);
		gr.drawString("δв", 428, 403);
		gr.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		gr.drawString("δвny =", 670, 710);
		gr.drawString("C1 =", 650, 760);
		gr.drawString("C2 =", 810, 760);
		gr.drawString("C3 =", 980, 760);
		gr.drawString("C4 =", 1150, 760);
		gr.drawString("C5 =", 1320, 760);
		gr.drawString("C9 =", 650, 800);
		gr.drawString("C16 =", 810, 800);
		gr.drawString("Cуб =", 980, 800);
		gr.drawString("αуб =", 1150, 800);
		gr.drawString("δуб =", 1320, 800);
		gr.setColor(Color.WHITE);
		gr.fillRect(25, 550, 600, 250);	
		gr.fillRect(695, 100, 800, 500);		
	}
	
	public TextField CreatTF(String value, int x, int y) {
		TextField TF = new TextField(value);
		TF.setBounds(x, y, 120, 30);
		TF.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		add(TF);		
		return TF;
	}
	
	public TextField CreatCTF(String value, int x, int y) {
		TextField TF = new TextField(value);
		TF.setBounds(x, y, 110, 30);
		TF.setEditable(false);
		TF.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		add(TF);		
		return TF;
	}

	public class Calculate implements ActionListener {
	     public void actionPerformed(ActionEvent e) {
	    	 Database.clear();
	    	 min = 0;
	    	 max = 0;	    
	    	 T = Double.parseDouble(TT.getText());
	    	 DT = Double.parseDouble(TDT.getText());
	    	 TD = Double.parseDouble(TTD.getText());
	    	 TF = Double.parseDouble(TTF.getText());
	    	 DD = Double.parseDouble(TDD.getText());
	    	 V = Double.parseDouble(TV.getText());
	    	 H = Double.parseDouble(TH.getText());
	    	 p = Double.parseDouble(Tp.getText());
	    	 a = Double.parseDouble(Ta.getText());
	    	 Cy0 = Double.parseDouble(TCy0.getText());
	    	 Cya = Double.parseDouble(TCya.getText());
	    	 Cyd = Double.parseDouble(TCyd.getText());
	    	 Cx = Double.parseDouble(TCx.getText());
	    	 mz = Double.parseDouble(Tmz.getText());
	    	 mzw = Double.parseDouble(Tmzw.getText());
	    	 mza_ = Double.parseDouble(Tmza_.getText());
	    	 mza = Double.parseDouble(Tmza.getText());
	    	 mzd = Double.parseDouble(Tmzd.getText());

	    	 C1 = -(mzw/I)*S*Math.pow(b, 2)*(p*V/2); 
	    	 TC1.setText(Double.toString(C1));
	    	 C2 = -(mza/I)*S*b*(p*Math.pow(V, 2)/2);
	    	 TC2.setText(Double.toString(C2));
	    	 C3 = -(mzd/I)*S*b*(p*Math.pow(V, 2)/2);
	    	 TC3.setText(Double.toString(C3));
	    	 C4 = ((Cya+Cx)*g/G)*S*(p*V/2);
	    	 TC4.setText(Double.toString(C4));
	    	 C5 = -(mza_/I)*S*Math.pow(b, 2)*(p*V/2);
	    	 TC5.setText(Double.toString(C5));
	    	 C9 = (Cyd*g/G)*S*(p*V/2);
	    	 TC9.setText(Double.toString(C9));
	    	 C16 = V/57.3/g;
	    	 TC16.setText(Double.toString(C16));
	    	 Cy = 2*G/S/p/Math.pow(V, 2);
	    	 TCy.setText(Double.toString(Cy));
	    	 Ca = 57.3*(Cy-Cy0)/Cya;
	    	 TCa.setText(Double.toString(Ca));
	    	 Cd = -57.3*(mz+(mza*Ca/57.3)+Cy*(x-0.24))/mzd;
	    	 TCd.setText(Double.toString(Cd));
	    	 ddd = -53.7*((mza/Cya)+(p*S*b*g/2/G)*mzw)*Cy/mzd;
	    	 Td.setText(Double.toString(ddd));
	    	 Database.add(new Data(0, 0, 0, 0, 0));
	    	 
	    	 while(T<=TF+0.01) {
	    		 int i = (int)(T/DT);
	    		 SAU(i);
	    		 DIN(i);
	    		 EILER(i);
	    		 if(T >= TD)   
	    			 TD += DD;    		 
	    		 T += DT;
	    		 if (Database.get(i).NY > max)
	    			 max = Database.get(i).NY;
	    		 else if (Database.get(i).NY < min)
	    			 min = Database.get(i).NY;
	    	 }
    		 for(int i = 0; i < 8; i++) {
    			 Table[i][0].setText(Double.toString((i+1)*DD));
    			 Table[i][1].setText(Double.toString(Database.get((i+1)*(int)(DD/DT)).DV));
    			 Table[i][2].setText(Double.toString(Database.get((i+1)*(int)(DD/DT)).NY));
    			 Table[i][3].setText(Double.toString(Database.get((i+1)*(int)(DD/DT)).Y[0]));
    			 Table[i][4].setText(Double.toString(Database.get((i+1)*(int)(DD/DT)).Y[3]));
    		 }
    		 SB.setMaximum((int)(TF/DD)+3);
    		 SB.setValue(1);
    		 SB.addAdjustmentListener(new TableScroll());
    		 CreateGraphic();
	     }
	     
	     public void DIN(int i) {
    		 Database.get(i).X[0] = Database.get(i).Y[1];
    		 Database.get(i).X[2] = C4*Database.get(i).Y[3] + C9*Database.get(i).DV;
    		 Database.get(i).X[3] = Database.get(i).X[0] - Database.get(i).X[2];
    		 Database.get(i).X[1] = -C1*Database.get(i).Y[1]-C2*Database.get(i).Y[3]-C5*Database.get(i).X[3]-C3*Database.get(i).DV;
    		 Database.get(i).NY = C16*Database.get(i).X[2];
    	 }
	     
    	 public void SAU(int i) {
    		 double dvs, dvd = 0;
    		 switch (switcher) {
    		 case 1:
    			 dvd = 0;
    			 break;
    		 case 2:
    			 dvd = kwz*Database.get(i).Y[1];
    			 break;
    		 case 3: 
    			 dvd = kwz*Database.get(i).Y[1] - Database.get(i).Y[4]/Twz;
    		 }
    		 Database.get(i).X[4] = dvd;
    		 dvs = Ks*xv;
    		 Database.get(i).DV = dvd + dvs;
    	 }
    	 
    	 public void EILER(int i) {
    		 double[] YN = new double[5];
    		 for(int j = 0; j < 5; j++) 
    			 YN[j] = Database.get(i).Y[j] + Database.get(i).X[j]*DT;   		 
    		 Database.add(new Data(YN[0], YN[1], YN[2], YN[3], YN[4]));
    	 }
    	 
	     public void CreateGraphic() {
	    	 int bub;
	    	 int bX, lX = 695;
	    	 int bY, lY = (int)map((Database.get(0).NY), min, max, 600, 100);;
	    	 max += 0.005;
	    	 graph.setColor(Color.lightGray);
	    	 graph.fillRect(651, 70, 50, 530);
	    	 graph.setColor(Color.white);
	    	 graph.fillRect(695, 100, 800, 500);
	    	 graph.setColor(Color.black);
	    	 graph.fillRect(695, 100, 2, 500);	    	
	    	 bub = (int)map(0, min, max, 600, 100);
	    	 graph.fillRect(695, bub, 800, 3);
	    	 graph.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
	    	 graph.drawString("^", 686, 125);
	    	 graph.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
	    	 graph.drawString(">", 1480, bub+12);
	    	 graph.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
	    	 for (double i = Math.ceil(min*100)/100; i < Math.floor(max*100)/100; i+=0.01) {
	    		 bub = (int)map(i, min, max, 600, 100);
	    		 graph.drawString(Double.toString(((double)Math.round(i*100))/100), 650, bub-7);
	    		 graph.drawLine(690, bub, 1495, bub);
	    	 }
	    	 for (double i = DD*2; i < TF; i += DD*2) {
	    		 bub = (int)map(i, 0, TF, 690, 1495);
	    		 graph.drawString(Double.toString(((double)Math.round(i*10))/10), bub-15, (int)map(0, min, max, 600, 100)+25);
	    		 graph.drawLine(bub, 100, bub, 600);
	    		 
	    	 }	    	 
	    	 graph.setColor(Color.red);   	 
	    	 for(double i = 2*DT; i < TF+0.01; i+=DT) {
	    		 bX = (int)map(i, 0, TF, 695, 1495);
	    		 bY = (int)map((Database.get((int)(i/DT)).NY), min, max, 600, 100);
	    		 graph.drawLine(lX, lY, bX, bY);
	    		 graph.drawLine(lX, lY, bX+1, bY);
	    		 graph.drawLine(lX, lY, bX, bY+1);
	    		 graph.drawLine(lX+1, lY, bX, bY);
	    		 graph.drawLine(lX, lY+1, bX, bY);
	    		 graph.drawLine(lX, lY, bX-1, bY);
	    		 graph.drawLine(lX, lY, bX, bY-1);
	    		 graph.drawLine(lX-1, lY, bX, bY);
	    		 graph.drawLine(lX, lY-1, bX, bY);
	    		lX = bX;
	    		lY = bY;
	    	 }
	    	 int j = Database.size();
	    	 graph.setColor(Color.blue);
	    	 bub = (int)map(Database.get(j-10).NY, min, max, 600, 100);
	    	 graph.drawLine(695, bub, 1495, bub);	
	    	 graph.drawString(Double.toString(((double)Math.round(Database.get(j-10).NY*1000))/1000), 720, bub-5);
	    }
	     
	     public double map(double input, double INmin, double INmax, double OUTmin, double OUTmax) {	    	 
	    	 return (input-INmin)*(OUTmax-OUTmin)/(INmax-INmin)+OUTmin;
	     }
	}

	public class TableScroll implements AdjustmentListener {
		public void adjustmentValueChanged(AdjustmentEvent e) {
			int bub = SB.getValue();
			 for(int i = bub; i < bub+8; i++) {
    			 Table[i-bub][0].setText(Double.toString((i)*DD));
    			 Table[i-bub][1].setText(Double.toString(Database.get((i)*(int)(DD/DT)).DV));
    			 Table[i-bub][2].setText(Double.toString(Database.get((i)*(int)(DD/DT)).NY));
    			 Table[i-bub][3].setText(Double.toString(Database.get((i)*(int)(DD/DT)).Y[0]));
    			 Table[i-bub][4].setText(Double.toString(Database.get((i)*(int)(DD/DT)).Y[3]));
    		 }			
		}
		
	}

	public static void main(String[] args) {
		new MainProgram("Lab 2.1");
		
	}
}
