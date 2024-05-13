using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RGR
{
    public class Rozrakhunok
    {
        private double T = 0, DT = 0.01, TD = 1, DD = 1;
        private double S = 201.45f, l = 37.55f, G0 = 80000f, Ix = 250000f, Iy = 900000f;
        private double V = 78f, H = 500f, p = 0.119f, g = 9.81f, abal = 7.1f, Teta0 = 0f;
        private double mywy = -0.21f, myb = -0.2f, mydh = 0.0716f, mxdh = -0.0206f, mxwy = -0.31f, mxwx = -0.583f, mxb = -0.186f, mxde = -0.0688f, myde = 0f, mywx = -0.006f;
        private double Czb = -1.0715f, Czdh = -0.183f;
        private double rad = 53.7f;
        private double a1, a2, a3, a4, a5, a6, a7, b1, b2, b3, b4, b5, b6, b7;
        private double k5 = 2f, k17 = 170f;
        private double[] Y = new double[13], X = new double[13];
        private double Sk, Dg, psig, Ekz, Ikz, dIk = 0, Ik = 0, Fk, Ek, f1, F1, f2, F2, fde, de, fdh, dh;
        public int state = 3;
        public List<double> Time = new List<double>();
        public List<double> massFi = new List<double>();
        public List<double> massPsi = new List<double>();
        public List<double> massX = new List<double>();
        public List<double> massZ = new List<double>();
        public List<double> massGp = new List<double>();
        public List<double> graphTime = new List<double>();
        public List<double> graphZ = new List<double>();
        public List<double> graphDzz = new List<double>();
        public List<double> graphPsi = new List<double>();


        public Rozrakhunok()
        {
            double m = G0 / g;
            a1 = -mywy * S * l * l * p * V / Iy / 4;
            a2 = -myb * S * l * p * V * V / Iy / 2;
            a3 = -mydh * S * l * p * V * V / Iy / 2;
            a4 = -Czb * S * p * V / m / 2;
            a5 = -mxdh * S * l * p * V * V / Ix / 2;
            a6 = -mxwy * S * l * l * p * V / Ix / 4;
            a7 = -Czdh * S * p * V / m / 2;
            b1 = -mxwx * S * l * l * p * V / Ix / 4;
            b2 = -mxb * S * l * p * V * V / Ix / 2;
            b3 = -mxde * S * l * p * V * V / Ix / 2;
            b4 = g * Math.Cos(abal / rad) / V;
            b5 = -myde * S * l * p * V * V / Iy / 2;
            b6 = -mywy * S * l * l * p * V / Iy / 4;
            b7 = Math.Sin(abal / rad);
            switch (state)
            {
                case 1:
                    {
                        Sk = 167f;
                        Y[0] = 90f;
                        Y[5] = 5000f;
                        break;
                    }
                case 2:
                    {
                        Sk = 167f;
                        Y[0] = 90f;
                        Y[5] = 3000f;
                        break;
                    }
                case 3:
                    {
                        Sk = 167f;
                        Y[0] = 90f;
                        Y[5] = 2000f;
                        break;
                    }
                case 4:
                    {
                        Sk = 54f;
                        Y[0] = 90f;
                        Y[5] = 3000f;
                        break;
                    }
                case 5:
                    {
                        Sk = 167f;
                        Y[0] = 90f;
                        Y[5] = 3000f;
                        break;
                    }
                case 6:
                    {
                        Sk = 280f;
                        Y[0] = 90f;
                        Y[5] = 3000f;
                        break;
                    }
                case 7:
                    {
                        Sk = 167f;
                        Y[0] = 0f;
                        Y[5] = -300f;
                        break;
                    }
                case 8:
                    {
                        Sk = 280f;
                        Y[0] = 0f;
                        Y[5] = -2000f;
                        break;
                    }
            }
            Y[6] = -18000f;
            Dg = (500 - 300 * Math.Tan(2.67f / rad)) / Math.Tan(2.67f / rad);


            while (Y[6] < 0)
            {
                if (Math.Abs(Y[6]) < Dg)
                {
                    k5 = 3f;
                    k17 = 120f;
                }

                psig = -Y[0];
                Ekz = rad * Math.Atan(Y[5] / (Math.Abs(Y[6]) + 4000f));
                Ikz = Sk * Ekz + dIk;
                Ek = Y[12] / 167f;
                X[12] = (Fk - Y[12]) / 0.2f;
                if (Ikz >= 250f)
                {
                    Fk = 250f;
                }
                else if (Ikz <= -250f)
                {
                    Fk = -250f;
                }
                else
                {
                    Fk = Ikz;
                }

                f1 = -1.3f * psig + 8f * Ek;
                if (f1 >= 25f)
                {
                    F1 = 25f;
                }
                else if (f1 <= -25f)
                {
                    F1 = -25f;
                }
                else
                {
                    F1 = f1;
                }
                X[7] = (k5 * psig - Y[7]) / 2.3f;
                X[8] = (k17 * Ek - Y[8]) / 2.3f;
                f2 = 1.3f * psig + X[7] + X[8] + F1;

                if (f2 >= 20f)
                {
                    F2 = 20f;
                }
                else if (f2 <= -20f)
                {
                    F2 = -20f;
                }
                else
                {
                    F2 = f2;
                }
                X[9] = -(F2 + Y[9]) / 0.85f;
                X[10] = 1.5f * X[3] - Y[10] / 1.6f;
                fde = 2f * (Y[1] - Y[9]) + Y[10];

                if (fde >= 12f)
                {
                    de = 12f;
                }
                else if (fde <= -12f)
                {
                    de = -12f;
                }
                else
                {
                    de = fde;
                }

                X[11] = 2.5f * X[2] - Y[11] / 2.5f;
                fdh = 2 * Y[2] - Y[11];

                if (fdh >= 10f)
                {
                    dh = 10f;
                }
                else if (fdh <= -10f)
                {
                    dh = -10f;
                }
                else
                {
                    dh = fdh;
                }
                dh = 0;
                X[0] = Y[2];
                X[1] = Y[3];
                X[2] = -a1 * X[0] - b6 * X[1] - a2 * Y[4] - a3 * dh - b5 * de;
                X[3] = -b1 * X[1] - a6 * X[0] - b2 * Y[4] - a5 * dh - b3 * de;
                X[4] = X[0] + b7 * X[1] + b4 * Y[1] - a4 * Y[4] - a7 * dh;
                X[5] = V * Math.Sin((psig + Y[4]) / rad);
                X[6] = V * Math.Cos((psig + Y[4]) / rad);


                for (int i = 0; i < 13; i++)
                {
                    Y[i] = Y[i] + X[i] * DT;
                }
                if (T >= TD)
                {             
                    Time.Add(TD);
                    massFi.Add(Y[3]);
                    massPsi.Add(psig);
                    massX.Add(Math.Abs(Y[6]));
                    massZ.Add(Math.Abs(Y[5]));
                    massGp.Add(Y[7]);
                    TD += DD;
                }
                graphTime.Add(T);
                graphZ.Add(-Y[5]);
                graphPsi.Add(-psig);
                graphDzz.Add(-Y[6]);
                T = T + DT;
            }
        }

    }       
}

