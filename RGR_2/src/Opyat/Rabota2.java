package Opyat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Rabota2 extends JFrame{
	Graphics graph;
	double maxx = 1, minx = 0.4;
	double maxgx = 640, mingx = 100;
	double maxy = 0.12, miny = 0.08;
	double maxgy = 120, mingy = 440;
	double a = 0, b = 0, c = 0;
	int n = 7;
	double[] x = new double[] {0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 0.95};   	
	double[] y = new double[] {0.094, 0.095, 0.1, 0.105, 0.1125, 0.122, 0.111};
	ArrayList<ArrayList<Double>> XY = new ArrayList<ArrayList<Double>>();
    
	
	public Rabota2(String s){
		super(s);
		setLayout(null);
		setSize(640, 480);
		setVisible(true);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		graph = getGraphics();
		FindABC();
		
    	graph.setColor(Color.white);
    	graph.fillRect(0, 0, 640, 480);
    	graph.setColor(Color.black);
    	graph.fillRect(0, 0, 2, 480);
    	graph.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
    	for(double i = miny; i < maxy+0.021; i+=0.02) {
    		int bub = (int)map(i, miny, maxy, mingy, maxgy);
        	graph.fillRect(0, bub, 640, 2);
        	graph.drawString(String.valueOf((double)((int)(i*100))/100), 10, bub-10);
    	}
       	graph.fillRect(0, 440, 640, 2);
       	graph.fillRect(0, 70, 640, 2);
    	for(double i = minx; i < maxx; i+=maxx/10) {
    		int bub = (int)map(i, minx, maxx, mingx, maxgx);
        	graph.fillRect(bub, 0, 2, 480);
        	graph.drawString(String.valueOf((double)((int)Math.round(i*100))/100), bub+10, 60);
    	}
    	int lx = (int)map(x[0], minx, maxx, mingx, maxgx), ly = (int)map(y[0], miny, maxy, mingy, maxgy);
    	graph.setColor(Color.red); 
    	int j = 0;
    	for (double i = x[0]; i<=x[n-1]; i+=0.0001) {
    		ArrayList<Double> DD = new ArrayList<Double>();
    		DD.add(i);
    		double dy = Calculate(i);
    		DD.add(dy);
    		int nx = (int)map(i, minx, maxx, mingx, maxgx), ny = (int)map(dy, miny, maxy, mingy, maxgy);
    		graph.drawLine(lx,ly,nx,ny);
    		graph.drawLine(lx,ly,nx+1,ny);
    		graph.drawLine(lx,ly,nx,ny+1);
    		graph.drawLine(lx,ly+1,nx,ny);
    		graph.drawLine(lx+1,ly,nx,ny);
    		graph.drawLine(lx,ly,nx-1,ny);
    		graph.drawLine(lx,ly,nx,ny-1);
    		graph.drawLine(lx,ly-1,nx,ny);
    		graph.drawLine(lx-1,ly,nx,ny);
    		lx = nx;
    		ly = ny;
    		if(j == 99) {
    			XY.add(DD);
    			j = 0;
    		}
    		j++;
    	}
    	
    	Object[][] array = new String[XY.size()][2];
    	for(int i = 0; i < XY.size(); i++) {
    		array[i][0] = String.valueOf(XY.get(i).get(0)); 
    		array[i][1] = String.valueOf(XY.get(i).get(1)); 
    	}
    	SimpleTableTest Table = new SimpleTableTest(array);
    	
	}
	public double Calculate(double dx) {
		double res = a*dx*dx+b*dx+c;
		return res;
	}
	
	public void FindABC() {
		double[][] M = new double[3][3];
		double[][] A = new double[3][3];
		double[][] NA = new double[3][3];
		double[] R = new double[3];
		double EX1 = 0, EX2 = 0, EX3 = 0, EX4 = 0, EX2Y = 0, EXY = 0, EY = 0;
		for(int i = 0; i < n; i++) {
			EX1 += x[i];
			EX2 += x[i]*x[i];
			EX3 += x[i]*x[i]*x[i];
			EX4 += x[i]*x[i]*x[i]*x[i];
			EX2Y += x[i]*x[i]*y[i];
			EXY += x[i]*y[i];
			EY += y[i];
		}
		
		M[0][0] = EX4;
		M[0][1] = EX3;
		M[0][2] = EX2;
		M[1][0] = EX3;
		M[1][1] = EX2;
		M[1][2] = EX1;
		M[2][0] = EX2;
		M[2][1] = EX1;
		M[2][2] = n;
		
		R[0] = EX2Y;
		R[1] = EXY;
		R[2] = EY;
		System.out.println("Первинна матриця:");
		System.out.println(M[0][0] + "\t" + M[0][1] + "\t" + M[0][2] + "\t | " + R[0]);
		System.out.println(M[1][0] + "\t\t" + M[1][1] + "\t\t" + M[1][2] + "\t | " + R[1]);
		System.out.println(M[2][0] + "\t\t\t" + M[2][1] + "\t\t" + M[2][2] + "\t | " + R[2]);
		double d = M[0][0]*M[1][1]*M[2][2]+M[0][2]*M[1][0]*M[2][1]+M[0][1]*M[1][2]*M[2][0]-M[0][2]*M[1][1]*M[2][0]-M[0][0]*M[1][2]*M[2][1]-M[2][2]*M[0][1]*M[1][0];
		System.out.println("\nВизначник: d = " + d);
		A[0][0] = M[1][1]*M[2][2]-M[1][2]*M[2][1];
		A[0][1] = -(M[1][0]*M[2][2]-M[1][2]*M[2][0]);
		A[0][2] = M[1][0]*M[2][1]-M[1][1]*M[2][0];
		A[1][0] = -(M[0][1]*M[2][2]-M[0][2]*M[2][1]);
		A[1][1] = M[0][0]*M[2][2]-M[0][2]*M[2][0];
		A[1][2] = -(M[0][0]*M[2][1]-M[0][1]*M[2][0]);
		A[2][0] = M[0][1]*M[1][2]-M[1][1]*M[0][2];
		A[2][1] = -(M[0][0]*M[1][2]-M[0][2]*M[1][0]);
		A[2][2] = M[0][0]*M[1][1]-M[1][0]*M[0][1];
		

		NA[0][0] = A[0][0]/d;
		NA[0][1] = A[1][0]/d;
		NA[0][2] = A[2][0]/d;
		NA[1][0] = A[0][1]/d;
		NA[1][1] = A[1][1]/d;
		NA[1][2] = A[2][1]/d;
		NA[2][0] = A[0][2]/d;
		NA[2][1] = A[1][2]/d;
		NA[2][2] = A[2][2]/d;
		System.out.println("\nОбернена матриця:");
		System.out.println(NA[0][0] + "\t" + NA[0][1] + "\t" + NA[0][2] + "\t |");
		System.out.println(NA[1][0] + "\t" + NA[1][1] + "\t" + NA[1][2] + "\t |");
		System.out.println(NA[2][0] + "\t" + NA[2][1] + "\t" + NA[2][2] + "\t |");

		
		a = NA[0][0]*R[0]+NA[0][1]*R[1]+NA[0][2]*R[2];
		b = NA[1][0]*R[0]+NA[1][1]*R[1]+NA[1][2]*R[2];
		c = NA[2][0]*R[0]+NA[2][1]*R[1]+NA[2][2]*R[2];
		System.out.println("\nРезультат:");
		System.out.println("a = " + a + ", b = " + b + ", c = " + c + ";");
		
	}
  
	public double map(double input, double INmin, double INmax, double OUTmin, double OUTmax) {	    	 
		return (input-INmin)*(OUTmax-OUTmin)/(INmax-INmin)+OUTmin;
	}
	public static void main(String[] args) {
		new Rabota2("Graphics");
		
	}
}

class SimpleTableTest extends JFrame{
	private Object[] columnsHeader = new String[] {"X", "Y"};
	
	SimpleTableTest(Object[][] AR){
		super("Table");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JTable table = new JTable(AR, columnsHeader);
		table.setFont(new Font("Microsoft JhengHei", Font.BOLD, 16));
        table.setRowHeight(30);
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table));
        setContentPane(contents);
        setSize(500, 400);
        setVisible(true);
}
}
