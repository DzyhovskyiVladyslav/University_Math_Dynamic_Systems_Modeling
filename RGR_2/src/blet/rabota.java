package blet;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.JFrame;

public class rabota extends JFrame{
	public double T = 0, DT = 0.5, TD = 0, TF = 20, DD = 5; 
	public double S = 201.45, l = 37.55, bA = 5.285, Xt = 0.24, G = 73000, Ix = 170000, Iy = 800000, Iz = 660000;
	public double V0 = 97.2, H0 = 500, p = 0.119, a = 338.36, g = 9.81;
	public double Cy0 = -0.255, Cya = 5.78, Cyd = 0.2865, CyM = 0, Cygp = 0.6446, Cxa = 0.286, CxM = 0, Cxgp = 0.043, Czb = -0.8136, Czdn = -0.16;
	public double mz0 = 0.2, mzwz = -13, mza_ = -3.8, mza = -1.83, mzd = -0.96, mzM = 0, mywy = -0.141, myb = -0.1518, mxdn = -0.02, mxwy = -0.151, mxwx = -0.56, mxb = -0.1146, mxde = -0.07, myde = 0, mywx = 0.026;
	public double P1d = 7003, P1V = -13.8;
	public double C1, C2, C3, C4, C5, C6, C7, C8, C9, C16, C17, C18, C19, e1, e2, e3;
	public TextField TC1, TC2, TC3, TC4, TC5, TC6, TC7, TC8, TC9, TC16, TC17, TC18, TC19, Te1, Te2, Te3;
	public double Vprzad = 10, kv = 1.8, kv_= 2.16, Tv = 1, Tv_ = 1, kfdv = 0.04, kteta = 2, kwz = 3;
	
	TextField Table[][]; 
	double[] min = {0,0,0}; 
	double[] max = {0,0,0};
	Scrollbar SB;
	Graphics graph;
	public class Database{ 
		public double[] Y = new double[12];
		public double[] X = new double[12];
		public double DV, NY, DG;
		
		public Database(double Y1, double Y2, double Y3, double Y4, double Y5, double Y6, double Y7, double Y8, double Y9, double Y10, double Y11, double Y12) {
			Y[0] = Y1;
			Y[1] = Y2;
			Y[2] = Y3;
			Y[3] = Y4;
			Y[4] = Y5;
			Y[5] = Y6;
			Y[6] = Y7;
			Y[7] = Y8;
			Y[8] = Y9;
			Y[9] = Y10;
			Y[10] = Y11;
			Y[11] = Y12;
		}
	}
	public ArrayList<Database> Data = new ArrayList(); 
		
	public rabota(String s){ 
		super(s);
		setLayout(null);
		setSize(1900, 1000);
		setVisible(true);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		graph = getGraphics();
		TC1 = CreatCTF("", 80, 650);
		TC2 = CreatCTF("", 280, 650);
		TC3 = CreatCTF("", 480, 650);
		TC4 = CreatCTF("", 680, 650);
		TC5 = CreatCTF("", 80, 720);
		TC6 = CreatCTF("", 280, 720);
		TC7 = CreatCTF("", 480, 720);
		TC8 = CreatCTF("", 680, 720);
		TC9 = CreatCTF("", 80, 790);
		TC16 = CreatCTF("", 280, 790);
		TC17 = CreatCTF("", 480, 790);
		TC18 = CreatCTF("", 680, 790);
		TC19 = CreatCTF("", 80, 860);
		Te1 = CreatCTF("", 280, 860);
		Te2 = CreatCTF("", 480, 860);
		Te3 = CreatCTF("", 680, 860);
		//Таблицу сюда
		double m = G/g, Mgp = V0/a;
		double dXt = Xt - 0.24;
		double ndv = 3, Ydv = 0.5;
		C1 = -mzwz*p*V0*S*bA*bA/2/Iz;
		TC1.setText(Double.toString(C1));
		C2 = -mza*p*V0*V0*S*bA/2/Iz;
		TC2.setText(Double.toString(C2));
		C3 = -mzd*p*V0*V0*S*bA/2/Iz;
		TC3.setText(Double.toString(C3));
		C4 = (Cya+Cxgp)*p*V0*S/2/m;
		TC4.setText(Double.toString(C4));
		C5 = -mza*p*V0*S*bA*bA/2/Iz;
		TC5.setText(Double.toString(C5));
		C6 = V0/57.3;
		TC6.setText(Double.toString(C6));
		C7 = g/57.3;
		TC7.setText(Double.toString(C7));
		C8 = (Cxa-Cygp)*p*V0*V0*S/2/m/57.3;
		TC8.setText(Double.toString(C8));
		C9 = -Cyd*p*V0*S/2/m;
		TC9.setText(Double.toString(C9));
		C16 = V0/57.3/g;
		TC16.setText(Double.toString(C16));
		C17 = -Cya*dXt*p*V0*V0*S*bA/2/Iz;
		TC17.setText(Double.toString(C17));
		C18 = -Cyd*dXt*p*V0*V0*S*bA/2/Iz;
		TC18.setText(Double.toString(C18));
		C19 = -ndv*P1d/57.3/m;
		TC19.setText(Double.toString(C19));
		e1 = p*V0*S*Cxgp*(1+CxM*Mgp/2/Cxgp-ndv*P1V/p/V0/S/Cxgp)/m;
		Te1.setText(Double.toString(e1));
		e2 = 57.3*p*S*Cygp*(1+CyM*Mgp/2/Cygp)/m;
		Te2.setText(Double.toString(e2));
		e3 = -57.3*((mzM/a+2*Cxgp*Ydv/V0/bA)*p*V0*V0*S*bA/2-ndv*P1V*Ydv)/Iz;
		Te3.setText(Double.toString(e3));
		Data.add(new Database(0, 0, 0, 0, 0, V0, 0, 0, 0, 0, 0, 0));
	   	 	while(T<=TF+DT) {
	   	 		int i = (int)(Math.round(T/DT));
	   	 		DIN(i);
	   	 		EILER(i);
	   	 		
	   	 		T += DT;	
	   	 		CheckMaxMin(i);
	   	 	}
	   	 CreateGraphicX(max[0], min[0], 820, 60, 710, 350, 0);
	   	 CreateGraphicX(max[1], min[1], 820, 440, 710, 350, 4);
	   	CreateGraphicX(max[2], min[2], 40, 60, 710, 350, 5);
	}
	
	public void DIN(int i) { 
		double Ffdv = Data.get(i).Y[10];
		if(Ffdv <= -10)
			Ffdv = -10;
		if(Ffdv >= 10)
			Ffdv = 10;
		double Fdz = -(Data.get(i).X[7] + Data.get(i).X[9] + Ffdv);
		if(Fdz <= -10)
			Fdz = -10;
		if(Fdz >= 10)
			Fdz = 10;
		double Fdelta = Fdz + kteta*Data.get(i).Y[0];
		if(Fdelta <= -3.5)
			Fdelta = -3.5;
		if(Fdelta >= 3.5)
			Fdelta = 3.5;
		double sigma = Fdelta + kwz*Data.get(i).X[0];
		if(sigma <= -10)
			sigma = -10;
		if(sigma >= 10)
			sigma = 10;
		Data.get(i).X[11] = 0;
		if(Fdelta <= -2)
			Data.get(i).X[11] = -2;
		if(Fdelta >= 2)
			Data.get(i).X[11] = 2;

		Data.get(i).DG = 0;
		
		//Data.get(i).DV = Data.get(i).Y[11];
		//Data.get(i).DV = sigma + Data.get(i).X[11];
		Data.get(i).DV = -2;
		Data.get(i).Y[3] = 5;
		System.out.println(Data.get(i).DV);
		Data.get(i).X[0] = Data.get(i).Y[1];
		Data.get(i).X[1] = -C1*Data.get(i).X[0] - (C2+C17)*Data.get(i).Y[3] - C5*Data.get(i).X[3] - e3*Data.get(i).Y[5] - (C3 + C18)*Data.get(i).DV;
		Data.get(i).X[2] = C4*Data.get(i).Y[3] + e2*Data.get(i).Y[5] + C9*Data.get(i).DV;
		Data.get(i).X[3] = Data.get(i).X[0] - Data.get(i).X[2];
		Data.get(i).X[4] = C6*Data.get(i).Y[2];
		//Data.get(i).X[5] = 0;
		Data.get(i).X[5] = -e1*Data.get(i).Y[5] - C8*Data.get(i).Y[3] - C7*Data.get(i).Y[0] - C19*Data.get(i).DG;
		double dV = Data.get(i).Y[5] - Vprzad;
		Data.get(i).X[6] = kv*dV;
		Data.get(i).X[7] = (Data.get(i).Y[6] - Data.get(i).Y[7]);
		Data.get(i).X[8] = kv_*dV;
		Data.get(i).X[9] = (Data.get(i).Y[8] - Data.get(i).Y[9])/Tv_;
		Data.get(i).X[10] = kfdv*dV;
		Data.get(i).NY = C16*Data.get(i).X[2];
		//System.out.println(Data.get(i).Y[11]+" "+Data.get(i).X[11]);
	}
	
	public void EILER(int i) { 
		double[] YN = new double[12];
		 for(int j = 0; j < 6; j++) 
			YN[j] = Data.get(i).Y[j] + Data.get(i).X[j]*DT;
		 //System.out.println(YN[0] +" "+ YN[1] +" "+ YN[2] +" "+ YN[3] +" "+ YN[4] +" "+ YN[5]);
	 	Data.add(new Database(YN[0], YN[1], YN[2], YN[3], YN[4], YN[5], YN[6], YN[7], YN[8], YN[9], YN[10], YN[11]));
	}
	
	public void CheckMaxMin(int j) {
		if(Data.get(j).NY > max[0])
			max[0] = Data.get(j).NY;
		if(Data.get(j).NY < min[0])
			min[0] = Data.get(j).NY;
		if(Data.get(j).Y[4] > max[1])
			max[1] = Data.get(j).Y[4];
		if(Data.get(j).Y[4] < min[1])
			min[1] = Data.get(j).Y[4];
		if(Data.get(j).Y[5] > max[2])
			max[2] = Data.get(j).Y[5];
		if(Data.get(j).Y[5] < min[2])
			min[2] = Data.get(j).Y[5];
	}
	
	public TextField CreatCTF(String value, int x, int y) { 
		TextField TF = new TextField(value);
		TF.setBounds(x, y, 100, 25);
		TF.setEditable(false);
		TF.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		add(TF);		
		return TF;
	}
	
	public Button CreateBT(String value, int x, int y, int w, int h) {
		Button BT = new Button(value);
		BT.setBounds(x, y, w, h);
		BT.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		add(BT);
		return BT;
	}
	

	public void paint (Graphics gr) {
		gr.setColor(Color.lightGray);
		gr.fillRect(0, 0, 1900, 1000);
		gr.setColor(Color.BLACK);
		gr.drawLine(0, 600, 900, 600);
		gr.drawLine(900, 0, 900, 1000);		
		gr.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
		gr.drawString("Таблиця даних", 280, 60);
		gr.drawString("Графіки", 1100, 50);
		gr.drawString("Розрахунок коефіцієнтів", 270, 650);
		gr.setFont(new Font("TimesRoman", Font.PLAIN, 18)); 
		gr.drawString("C1 =", 35, 707);
		gr.drawString("C2 =", 230, 707);
		gr.drawString("C3 =", 430, 707);
		gr.drawString("C4 =", 630, 707);
		gr.drawString("C5 =", 35, 777);
		gr.drawString("C6 =", 230, 777);
		gr.drawString("C7 =", 430, 777);
		gr.drawString("C8 =", 630, 777);
		gr.drawString("C9 =", 35, 847);
		gr.drawString("C16 =", 230, 847);
		gr.drawString("C17 =", 430, 847);
		gr.drawString("C18 =", 630, 847);
		gr.drawString("C19 =", 35, 917);
		gr.drawString("e1 =", 230, 917);
		gr.drawString("e2 =", 430, 917);
		gr.drawString("e3 =", 630, 917);
		gr.setColor(Color.white);
		gr.fillRect(30, 80, 680, 440);
	}
	
	public void CreateGraphicX(double max, double min, int x, int y, int w, int h, int mas) {
		int bub;
		int bx, lx = x;
		int by, ly = (int)map(Data.get(0).Y[mas], min, max, y+h, y);
		if (mas == 0) {
			ly = (int)map(Data.get(0).NY, min, max, y+h, y);
		}
		graph.setColor(Color.white);
    	graph.fillRect(x, y, w, h);
    	graph.setFont(new Font("TimesRoman", Font.PLAIN, 20));
    	double start, end, delta, mod = Math.abs(max - min);
    	if (mod > 50) {
    		delta = 20;
       		start = Math.ceil(min); 
    		end = Math.floor(max);
    	}
    	else if (mod > 10) {
    		delta = 5;
    		start = Math.ceil(min*2/10)*10/2; 
    		end = Math.floor(max);
    	}
    	else if (mod > 7) {
    		delta = 2;
    		start = Math.ceil(min/2)*2;
    		end = Math.floor(max/2)*2;
    	}
    	else if (mod > 3) {
    		delta = 1;
    		start = Math.ceil(min);
    		end = Math.floor(max);
    	}
    	else if (mod > 1) {
    		delta = 0.5;
    		start = Math.ceil(min*2)/2;
    		end = Math.floor(max*2)/2;
    	}
    	else if (mod > 0.3) {
    		delta = 0.1;
    		start = Math.ceil(min*10)/10;
    		end = Math.floor(max*10)/10;
    	}
    	else if (mod > 0.15) {
    		delta = 0.05;
    		start = Math.ceil(min*20)/20;
    		end = Math.floor(max*20)/20;
    	}
    	else {
    		delta = 0.02;
    		start = Math.ceil(min*500)/500;
    		end = Math.floor(max*500)/500;
    	}
    	for (double i = start; i <= end; i+=delta) { 
    		bub = (int)map(i, min, max, y+h, y);
       		if((int)Math.floor(i*100) != 0) {
       			graph.setColor(Color.black);
    		if(i % 1 == 0) 
    			graph.drawString(Integer.toString((int)Math.round(i*100)/100), x-35, bub+10);
    		else if (((double)Math.round(i*100)/100)*10 % 1 == 0)
    			graph.drawString(Double.toString((double)Math.round(i*100)/100), x-35, bub+10);
    		else
    			graph.drawString(Double.toString((double)Math.round(i*100)/100), x-45, bub+10);
        	 graph.setColor(Color.lightGray);
        	 graph.drawLine(x, bub, x+w, bub);
    	}
    }
    	 for (double i = TF/10; i <= TF-TF/10; i += TF/10) {
    	bub = (int)map(i, 0, TF, x, x+w);
    		graph.setColor(Color.black);
    		graph.drawString(Double.toString(i), bub, (int)map(0, min, max, y+h, y));
     	 	graph.setColor(Color.lightGray);
    		graph.drawLine(bub, y, bub, y+h);		 
    	 }	   	
    	 graph.setColor(Color.black);
    	 graph.fillRect(x, y, 2, h);
    	 bub = (int)map(0, min, max, y+h, y);
    	 graph.fillRect(x, bub, w, 2);
    	 graph.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
    	 graph.drawString("^", x-6, y+20);
    	 graph.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
    	 graph.drawString(">", x+w-15, bub+11);
       	 graph.setFont(new Font("TimesRoman", Font.PLAIN, 20));
    	 graph.drawString("T", x+w/2, y+h+20);
    	 switch(mas) {
    	 case 0:
    		 graph.drawString("ny", x-30, bub+10);
    		 break;
    	 case 4:
    		 graph.drawString("H", x-20, bub+10);
    		 break;
    	 case 5:
    		 graph.drawString("V", x-20, bub+10);
    		 break;
    	 }
    	 graph.setColor(Color.red);   	 
    	 for(double i = 2*DT; i < TF-0.1; i+=DT) {
    		 bx = (int)map(i, 0, TF, x, x+w);
    		 if (mas == 0) 
    			 by = (int)map((Data.get((int)(i/DT)).NY), min, max, y+h, y);
    		 else
    			 by = (int)map((Data.get((int)(i/DT)).Y[mas]), min, max, y+h, y);
    		 graph.drawLine(lx, ly, bx, by);
    		 graph.drawLine(lx, ly, bx+1, by);
    		 graph.drawLine(lx, ly, bx, by+1);
    		 graph.drawLine(lx+1, ly, bx, by);
    		 graph.drawLine(lx, ly+1, bx, by);
    		 graph.drawLine(lx, ly, bx-1, by);
    		 graph.drawLine(lx, ly, bx, by-1);
    		 graph.drawLine(lx-1, ly, bx, by);
    		 graph.drawLine(lx, ly-1, bx, by);
    		 lx = bx;
    		 ly = by;
    	 } 
	 }
    public double map(double input, double INmin, double INmax, double OUTmin, double OUTmax) {
    	return (input-INmin)*(OUTmax-OUTmin)/(INmax-INmin)+OUTmin;
    }

	
	public static void main(String[] args) {
		new rabota("RGR");
	}
}
