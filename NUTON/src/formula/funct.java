package formula;

public class funct {
	
	public double[] X = {1, 2, 3, 4};
	public double[] Y = {1, 2, 3, 4};
	
	funct(){
		for(double i = X[0]; i < X[3]; i+=0.01) {
			System.out.println("X = " + i + " Y = " + Newton(i));
		}
	}
	
	public double Newton(double x) { //формула Ньютона
        double res = C(0);
        for (int j = 1; j < X.length; j++)
        {
            double dodatok = C(j);
            for (int k = 0; k < j; k++)
                dodatok *= x - X[k];
            res += dodatok;
        }
        return res;
	}
	
    public double C(int j) //Розрахунок коефіцієнта C
    {
        if (j == 0)
            return Y[0];
        double delta = Delta(j, j);
        return delta / (Factorial(j) * Math.pow(X[1] - X[0], j));
    }

    public double Delta(int p, int i){ //Розрахунок дельти степення p
        if (p == 1)
            return Y[i] - Y[i - 1];
        return Delta(p - 1, i) - Delta(p - 1, i - 1);
    }

    public double Factorial(double value){ //Розрахунок факторіалу
        if (value == 0)
            return 1;
        return value * Factorial(value - 1);
    }
	
	public static void main(String[] args) {
		funct test = new funct();
	}
}
