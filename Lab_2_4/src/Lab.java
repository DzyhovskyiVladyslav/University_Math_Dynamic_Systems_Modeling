import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Lab extends JFrame{
	public double S = 201.45, ba = 5.285, G = 73000, xt = 0.24, Iz = 660000, V = 190, H = 6400, p = 0.0636, a = 314.34, g = 9.81,
			Cy = -0.28, Cya = 5.9, Cyd = 0.2865, Cx = 0.033, mz0 = 0.22, mzwz = -13.4, mza_ = -4, mza = -1.95, mzd = -0.92;
	public double C1, C2, C3, C4, C5, C6, C9, C16, abal, Cybal, dbal, mx, Sigma, xmax; 
	public TextField TC1, TC2, TC3, TC4, TC5, TC6, TC9, TC16, Tabal, TCybal, Tdbal, Tmx1, TSigma1, Txmax1 , Tmx2, TSigma2, Txmax2;
	public double T = 0, DT = 0.01, TD = 0, TF = 300, DD = 5; 
	public Button TT, TSig, TDV, Ta, Tteta, TH, TNY;
	public double MW1 = 0, MW2 = 0, MS1 = 0, MS2 = 0, MAX1 = 0, MAX2 = 0;
	public double AV, hi = 0, Kh = 0.1, Kh_D = 0.4, kwz = 3, Hz, Sigma_Wy, Ry = 0.324;
	
	TextField Table[][]; 
	double[] min = {0,0}; 
	double[] max = {0,0};
	Scrollbar SB;
	Graphics graph;
	public class Data{ 
		public double[] Y = new double[7];
		public double[] X = new double[7];
		public double DV, NY;
		
		public Data(double Y1, double Y2, double Y3, double Y4, double Y5, double Y6, double Y7) {
			Y[0] = Y1;
			Y[1] = Y2;
			Y[2] = Y3;
			Y[3] = Y4;
			Y[4] = Y5;
			Y[5] = Y6;
			Y[6] = Y7;
		}
	}
	public ArrayList<Data> Database = new ArrayList(); 
		
	public Lab(String s){ 
		super(s);
		setLayout(null);
		setSize(1545, 830);
		setVisible(true);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		graph = getGraphics();
		TC1 = CreatCTF("", 60, 600);
		TC2 = CreatCTF("", 240, 600);
		TC3 = CreatCTF("", 420, 600);
		TC4 = CreatCTF("", 600, 600);
		TC5 = CreatCTF("", 60, 650);
		TC6 = CreatCTF("", 240, 650);
		TC9 = CreatCTF("", 420, 650);
		TC16 = CreatCTF("", 600, 650);
		Tabal = CreatCTF("", 60, 700);
		TCybal = CreatCTF("", 240, 700);
		Tdbal = CreatCTF("", 420, 700);
		Tmx1 = CreatCTF("", 600, 700);
		TSigma1 = CreatCTF("", 40, 750);
		Txmax1 = CreatCTF("", 190, 750);
		Tmx2 = CreatCTF("", 340, 750);
		TSigma2 = CreatCTF("", 490, 750);
		Txmax2 = CreatCTF("", 640, 750);
		
		TT = CreateBT("T", 22, 50, 80, 40);
		TSig = CreateBT("σ", 102, 50, 80, 40);
		TDV = CreateBT("δв", 182, 50, 80, 40);
		Ta = CreateBT("α", 262, 50, 110, 40);
		Tteta = CreateBT("θ", 372, 50, 110, 40);
		TH = CreateBT("H", 482, 50, 110, 40);
		TNY = CreateBT("ny", 592, 50, 93, 40);
		Table = new TextField[16][7];
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 7; j++) {
				Table[i][j] = new TextField("");
				Table[i][j].setFont(new Font("TimesRoman", Font.PLAIN, 16));
				Table[i][j].setEditable(false);
				add(Table[i][j]);
			}
			Table[i][0].setBounds(22, 90+i*25, 80, 25);
			Table[i][1].setBounds(102, 90+i*25, 80, 25);
			Table[i][2].setBounds(182, 90+i*25, 80, 25);
			Table[i][3].setBounds(262, 90+i*25, 110, 25);
			Table[i][4].setBounds(372, 90+i*25, 110, 25);
			Table[i][5].setBounds(482, 90+i*25, 110, 25);
			Table[i][6].setBounds(592, 90+i*25, 93, 25);
		}
		SB = new Scrollbar(Scrollbar.VERTICAL);
		SB.setBounds(685, 50, 20, 440);
		SB.setBackground(Color.GRAY);
		SB.setMinimum(1);
		add(SB);
		double m = G/g;
		Cybal = 2*G/(S*p*Math.pow(V, 2));
		TCybal.setText(Double.toString(Cybal));
		abal = 57.3*((Cybal - Cy)/Cya);
		Tabal.setText(Double.toString(abal));
		dbal = -57.3*((mz0 + (mza*abal/57.3) + Cybal*(xt - 0.24))/mzd);
		Tdbal.setText(Double.toString(dbal));
		C1 = -(mzwz/Iz)*S*ba*ba*p*(V/2);
		TC1.setText(Double.toString(C1));
		C2 = -(mza/Iz)*S*ba*p*V*V/2;
		TC2.setText(Double.toString(C2));
		C3 = -mzd/Iz*S*ba*p*V*V/2;
		TC3.setText(Double.toString(C3));
		C4 = (Cya+Cx)/m*S*p*V/2;
		TC4.setText(Double.toString(C4));
		C5 = -mza_/Iz*S*ba*ba*p*V/2;
		TC5.setText(Double.toString(C5));
    	C6 = V/57.3;
    	TC6.setText(Double.toString(C6));
		C9 = Cyd/m*S*p*V/2;
		TC9.setText(Double.toString(C9));
    	C16 = V/57.3/g;
    	TC16.setText(Double.toString(C16));
    	Database.add(new Data(0, 0, 0, 0, 0, 0, 0));

       // Hz=20.f; Sigma_Wy=0.0f;     //Без турбулентного вітру, в спокійній атмосфері з виходом на висоту 20
        Hz=0.f; Sigma_Wy=0.5f;      //Зі слабким турбулентним з виходом на висоту 0
        //Hz=0.f; Sigma_Wy=1.0f;       //Зі середнім турбулентним з виходом на висоту 0
        //Hz=0.f; Sigma_Wy=3.0f;      //Зі сильним турбулентним з виходом на висоту 0
        double n = 0;
   	 	while(T<=TF+DT) {
   	 		int i = (int)(Math.round(T/DT));
   	 		n++;
   	 		SHUM(i);
   	 		SAU(i);
   	 		DIN(i);
   	 		EILER(i);   	 			
   	 		MW1 += Database.get(i).NY;
   	 			
   	 		MW2 += Database.get(i).Y[4]; 
   	 		T += DT;	
   	 		CheckMaxMin(i);
   	 	}
   	 	MW1/=n;
   	 	MW2/=n;
   	 	double l = 0;
   	 	for(int i = 1; i <= n; i++) {
   	 		l+=Database.get(i).NY;
   	 	}
   	 	l/=n;
   	 	System.out.println(l);
   	 	for (int i=1; i <= n;i++) {
   	 		MS1 += Math.pow((Database.get(i).NY-MW1), 2);
   	 		MS2 += Math.pow((Database.get(i).Y[4]-MW2), 2);
   	 	}

   	 	MS1=Math.sqrt(1/(n-1)*MS1);//дисперсія для вертикального перевантаження
   	 	MS2=Math.sqrt(1/(n-1)*MS2); //дисперсія для висоти
   	 	
   	 	MAX1=Math.abs(MW1)+2*MS1; //cереднє квадратичнє відхилення для вертикального перевантаження
   	 	MAX2=Math.abs(MW2)+2*MS2;
   	 	
   	 	Tmx1.setText(Double.toString(Math.floor(MW1*1000000)/1000000));
   	 	Tmx2.setText(Double.toString(MW2));
   	 	TSigma1.setText(Double.toString(MS1));
   	 	TSigma2.setText(Double.toString(MS2));
   	 	Txmax1.setText(Double.toString(MAX1));
   	 	Txmax2.setText(Double.toString(MAX2));

   	 	for(int i = 0; i < 16; i++) {
   	 		Table[i][0].setText(Double.toString((i+1)*DD));
   	 		Table[i][1].setText(Double.toString(Database.get((i+1)*(int)(DD/DT)).Y[5]));
   	 		Table[i][2].setText(Double.toString(Database.get((i+1)*(int)(DD/DT)).DV));
   	 		Table[i][3].setText(Double.toString(Database.get((i+1)*(int)(DD/DT)).Y[3]));
   	 		Table[i][4].setText(Double.toString(Database.get((i+1)*(int)(DD/DT)).Y[0]));
   	 		Table[i][5].setText(Double.toString(Database.get((i+1)*(int)(DD/DT)).Y[4]));
   	 		Table[i][6].setText(Double.toString(Database.get((i+1)*(int)(DD/DT)).NY));
   	 	}
   	 	SB.setMaximum((int)(TF/DD)-5);
   	 	SB.setValue(1);
   	 	SB.addAdjustmentListener(new TableScroll());

   	 	CreateGraphicX(max[1], min[1], 820, 60, 710, 350, 0);
   	 	CreateGraphicX(max[0], min[0], 820, 440, 710, 350, 4);

	}
	public void SHUM(int i) {
		double SUMHR = 0;
		for(int N = 1; N <=12; N++) {			
			SUMHR += Math.random();
		}
		hi = (SUMHR - 6)/6;
	}
	public void SAU(int i) {
		if (i != 0)
	 		Sigma=Kh*(Database.get(i).Y[4]-Hz)+Kh_D*Database.get(i-1).X[4]+kwz*Database.get(i).Y[1];
	 	else 
	 		Sigma = Kh*(-Hz);
        if (Sigma > 10)
        	Sigma = 10; 
        else if (Sigma < -10) {
        	Sigma = -10;
        }
        Database.get(i).DV = Sigma;
        //Database.get(i).DV = 0;
        AV = Database.get(i).Y[3]+Database.get(i).Y[5]/C6;
	}	
	public void DIN(int i) { 
		Database.get(i).X[0] = Database.get(i).Y[1]; //кутова швидкість тангажу
		Database.get(i).X[1] = -C1*Database.get(i).Y[1] - C2*AV - C5*Database.get(i).X[3] - C3*Database.get(i).DV; //прискорення кутової швидкості тангажу
		Database.get(i).X[2] = C4*AV + C9*Database.get(i).DV; //швидкість зміни кута нахилу траекторії
		Database.get(i).X[3] = Database.get(i).X[0] - Database.get(i).X[2]; //швидкість зміни кута атаки
		Database.get(i).X[4] = C6*Database.get(i).Y[2]; //швидкість зміни висоти
		Database.get(i).X[5] = Database.get(i).Y[6]+1.73*Math.sqrt(Ry)*Sigma_Wy*hi/Math.sqrt(DT); //прискорення вертикальної складової швидкості турбулентного вітру
		Database.get(i).X[6] = -2*Ry*Database.get(i).Y[6]-Ry*Ry*Database.get(i).Y[5]-2.46*Ry*Math.sqrt(Ry)*Sigma_Wy*hi/Math.sqrt(DT); //переміжна змінна
		Database.get(i).NY = C16 * Database.get(i).X[2];

	}
	
	public void EILER(int i) { 
		double[] YN = new double[7];
		 for(int j = 0; j < 7; j++) 
			YN[j] = Database.get(i).Y[j] + Database.get(i).X[j]*DT;  
	 	Database.add(new Data(YN[0], YN[1], YN[2], YN[3], YN[4], YN[5], YN[6]));
	}
	 
	public void CheckMaxMin(int j) {
			if(Database.get(j).NY > max[1])
				max[1] = Database.get(j).NY;
			if(Database.get(j).NY < min[1])
				min[1] = Database.get(j).NY;
			if(Database.get(j).Y[4] > max[0])
				max[0] = Database.get(j).Y[4];
			if(Database.get(j).Y[4] < min[0])
				min[0] = Database.get(j).Y[4];
		}

	public void paint (Graphics gr) {
		gr.setColor(Color.lightGray);
		gr.fillRect(0, 0, 1545, 830);
		gr.setColor(Color.BLACK);
		gr.drawLine(0, 550, 765, 550);
		gr.drawLine(765, 0, 765, 830);		
		gr.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
		gr.drawString("Таблиця даних", 280, 60);
		gr.drawString("Графіки", 1100, 50);
		gr.drawString("Розрахунок коефіцієнтів", 220, 600);
		gr.setFont(new Font("TimesRoman", Font.PLAIN, 18)); 
		gr.drawString("C1 =", 20, 650);
		gr.drawString("C2 =", 200, 650);
		gr.drawString("C3 =", 380, 650);
		gr.drawString("C4 =", 560, 650);
		gr.drawString("C5 =", 20, 700);
		gr.drawString("C6 =", 200, 700);
		gr.drawString("C9 =", 380, 700);
		gr.drawString("C16 =", 550, 700);
		gr.drawString("αбал =", 10, 750);
		gr.drawString("Cyбал =", 180, 750);
		gr.drawString("δбал =", 370, 750);
		gr.setFont(new Font("TimesRoman", Font.PLAIN, 14));
		gr.drawString("mxny =", 560, 750);
		gr.drawString("σny", 20, 800);
		gr.drawString("nymax", 155, 800);
		gr.drawString("mxH", 315, 800);
		gr.drawString("σH", 470, 800);
		gr.drawString("Hmax", 610, 800);

		gr.setColor(Color.white);
		gr.fillRect(30, 80, 680, 440);
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
	
	public void CreateGraphicX(double max, double min, int x, int y, int w, int h, int mas) {
		int bub;
		int bx, lx = x;
		int by, ly = (int)map(Database.get(0).Y[mas], min, max, y+h, y);
		if (mas == 0) {
			ly = (int)map(Database.get(0).NY, min, max, y+h, y);
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
    	 }
    	 graph.setColor(Color.red);   	 
    	 for(double i = 2*DT; i < TF-0.1; i+=DT) {
    		 bx = (int)map(i, 0, TF, x, x+w);
    		 if (mas == 0) 
    			 by = (int)map((Database.get((int)(i/DT)).NY), min, max, y+h, y);
    		 else
    			 by = (int)map((Database.get((int)(i/DT)).Y[mas]), min, max, y+h, y);
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

	public class TableScroll implements AdjustmentListener {
		public void adjustmentValueChanged(AdjustmentEvent e) {
			int bub = SB.getValue();
			for(int i = bub; i < bub+16; i++) {
    			Table[i-bub][0].setText(Double.toString((i)*DD));
    			Table[i-bub][1].setText(Double.toString(Database.get((i)*(int)(DD/DT)).Y[5]));
    			Table[i-bub][2].setText(Double.toString(Database.get((i)*(int)(DD/DT)).DV));
    			Table[i-bub][3].setText(Double.toString(Database.get((i)*(int)(DD/DT)).Y[3]));
    			Table[i-bub][4].setText(Double.toString(Database.get((i)*(int)(DD/DT)).Y[0]));
    			Table[i-bub][5].setText(Double.toString(Database.get((i)*(int)(DD/DT)).Y[4]));
    			Table[i-bub][6].setText(Double.toString(Database.get((i)*(int)(DD/DT)).NY));
    		}			
		}		
	}

	public static void main(String[] args) {
		new Lab("Lab 2.3");
	}
}