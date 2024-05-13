using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RGR
{
    public class Rozrakhunok
    {
        public double T = 0, DT = 0.01, TD = 1, DD = 1;
        public double S = 201.45, l = 37.55, G0 = 80000, Gp0 = 20000, Ix = 250000, Iy = 875000, qdv = 0.585;
        public double V0 = 236, H0 = 11050, pn = 0.0372, g = 9.73, abal = 6.04, teta0 = 0;
        public double mywy = -0.145, myb = -0.1719, mydn = -0.0716, mxdn = -0.01719, mxwy = -0.11, mxwx = -0.66, mxb = -0.1146, mxde = -0.043, myde = 0, mywx = -0.006;
        public double Czb = -0.8595, Czdn = -0.1759;
        public double a1, a2, a3, a4, a5, a6, a7, b1, b2, b3, b4, b5, b6, b7;
        public double rad = 57.3, psig, Wx, Wz, W, HB, bv, Vs, pzt, gamaz, gamazad, de, dn, qdB, kkzt, sk, dsk;
        public int state = 1;
        double[] X = new double[8];
        double[] Y = new double[8];
        public List<double> Time = new List<double>();
        public List<double> massFi = new List<double>();
        public List<double> massPsi = new List<double>();
        public List<double> massX = new List<double>();
        public List<double> massZ = new List<double>();
        public List<double> massGp = new List<double>();
        public List<double> graphTime = new List<double>();
        public List<double> graphZ = new List<double>();
        public List<double> graphSk = new List<double>();
        public List<double> graphPsi = new List<double>();


        public Rozrakhunok()
        {
            double m = G0 / g;
            a1 = -mywy * S * l * l * pn * V0 / Iy / 4;
            a2 = -myb * S * l * pn * V0 * V0 / Iy / 2;
            a3 = -mydn * S * l * pn * V0 * V0 / Iy / 2;
            a4 = -Czb * S * pn * V0 / m / 2;
            a5 = -mxdn * S * l * pn * V0 * V0 / Ix / 2; 
            a6 = -mxwy * S * l * l * pn * V0 / Ix / 4;
            a7 = -Czdn * S * pn * V0 / m / 2;
            b1 = -mxwx * S * l * l * pn * V0 / Ix / 4;
            b2 = -mxb * S * l * l * pn * V0 / Ix / 4;
            b3 = -mxde * S * l * pn * V0 * V0 / Ix / 2;
            b4 = g * Math.Cos(abal / rad)/V0;
            b5 = -myde * S * l * pn * V0 * V0 / Iy / 2;
            b6 = -mywx * S * l * l * pn * V0 / Iy / 4;
            b7 = Math.Sin(abal/ rad);
           

            Y[5] = -50000;
            Y[7] = Gp0;
            //Y[6] = 0; W = 0; HB = 0; //N=212, minY = -2, maxY = 2, вітерпопутний
            //Y[6] = 2000; W = 0; HB = 0; //N=213, minY = -2, maxY = 2100, вітерпопутний
            //Y[6] = 0; W = 40; HB = 0; //N=182, minY = -2, maxY = 2, вітерпопутний
            //Y[6] = 0; W = 40; HB = 180; //N=256, minY = -2, maxY = 2, вітерзустрічний
            Y[6] = 0; W = 40; HB = 135;// N=212, minY = -2, maxY = 5000-курсовий,maxY = 35-шляховий,маршрутний, вітерзустрічний і боковий
            Y[0] = rad * Math.Atan(Y[6] / Y[5]);

            while (Y[5] < 0)
            {
                DIN();
                switch (state)
                {
                    case 1:
                        kurs();
                        break;
                    case 2:
                        shlyah();
                        break;
                    case 3:
                        marsh();
                        break;
                }
                Eiller();  
                
                if (T >= TD)
                {             
                    Time.Add(TD);
                    massFi.Add(Y[3]);
                    massPsi.Add(psig);
                    massX.Add(Math.Abs(Y[5]));
                    massZ.Add(Math.Abs(Y[6]));
                    massGp.Add(Y[7]);
                    TD += DD;
                }
                graphTime.Add(T);
                graphZ.Add(Y[6]);
                graphPsi.Add(psig);
                graphSk.Add(sk);
                T = T + DT;
            }
        }

        
        public void DIN()
        {

            
            Wx = W * Math.Cos((HB - psig) / rad);
            Wz = W * Math.Sin((HB - psig) / rad);
            bv = Y[4] + (-rad * Wz) / V0;
            Vs = V0 + Wx;
            pzt = rad * Math.Atan(Y[6] / Y[5]);

            if(gamaz < -20)
            {
                gamazad = -20;
            }
            else if(gamaz > 20)
            {
                gamazad = 20;
            }
            de = 2 * (Y[2] - gamazad) + 1.5 * Y[3];
            dn = 2.5 * Y[1];
            X[0] = Y[1];
            X[1] = -a1 * X[0] - b6 * X[2] - a2 * bv - a3 * dn - b5 * de;
            X[2] = Y[3];
            X[3] = -b1 * X[2] - b6 * X[0] - b2 * bv - a3 * dn - b3 * de;
            X[4] = X[0] + b7 * X[2] + b4 * Y[2] - a4 * bv - a5 * dn - b3 * de;
            X[5] = Vs * Math.Cos((psig + Y[4]) / rad);
            X[6] = Vs * Math.Sin((psig + Y[4]) / rad);
            X[7] = -3*qdv;
            psig = -Y[0];
            sk = rad * Math.Atan(X[6] / X[5]);
        }
        public void kurs()
        {
            kkzt = pzt - psig;
            gamaz = 0.07 * Vs * Math.Sin(kkzt / rad);
            gamazad = gamaz;
        }
        public void shlyah()
        {
            dsk = pzt - sk;
            gamaz = 0.7 * Vs * Math.Sin(dsk / rad);
            gamazad = gamaz;
        }
        public void marsh()
        {
            gamaz = -(0.02 * Y[6] + 0.7 * X[6]);
            gamazad = gamaz;
        }
        public void Eiller()
        {
            for (int i = 0; i < 8; i++)
            {
                Y[i] = Y[i] + X[i] * DT;
            }
        }
    }       
}

