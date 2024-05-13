using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RGR
{
    public class Rozrakhunok
    {
        public int state = 0;
        public double T = 0, DT = 0.01, TD = 0, TF = 90, DD = 2, TV = 2;
        public double S = 201.45, bA = 5.285, Xt1 = 0.24, Xtc = 0.3, Xt2 = 0.24, G1 = 73000, G2 = 68000, Iz1 = 660000, Iz2 = 650000;
        public double V0 = 97.2, Hzad = 600, H0 = 600, p = 0.119, a = 338.36, g = 9.81;
        public double Cy0 = -0.255, Cya = 5.78, Cyd = 0.2865, Cx = 0.13;
        public double mz0 = 0.2, mzwz = -13, mza_ = -3.8, mza = -1.83, mzd = -0.96;
        public double m, dXt, Xt, dabal, ddvbal, abal1, abal2, Cybal1, Cybal2, dvbal1, dvbal2, Cy;
        public double C1, C2, C3, C4, C5, C6, C9, C16, C20;
        public double kn = 0.1, kn_ = 0.5, kwz = 1, kteta = 1, kf = 0.002, T1 = 20, T2 = 20, Teta0 = 0;
        public double DV = 0, NY = 0, az = 0, dvz = 0, L = 10, an = 0.3;
        double[] X = new double[9];
        double[] Y = new double[9];
        public List<double> Time = new List<double>();
        public List<double> massDV = new List<double>();
        public List<double> massNY = new List<double>();
        public List<double> massTeta = new List<double>();
        public List<double> massH = new List<double>();
        public List<double> massa = new List<double>();
        public List<double> massdXt = new List<double>();
        public List<double> graphTime = new List<double>();
        public List<double> graphNy = new List<double>();
        public List<double> graphH = new List<double>();
        public List<double> graphDV = new List<double>();

        public Rozrakhunok()
        {

            m = G1 / g;
            dXt = Xt1 - 0.24;
            //Розрахунок коефіцієнтів до скидання
            C1 = (-mzwz / Iz1) * S * Math.Pow(bA, 2) * ((p * V0) / 2);
            C2 = (-mza / Iz1) * S * bA * (p * Math.Pow(V0, 2) / 2);
            C3 = -mzd * S * bA * p * V0 * V0 / 2 / Iz1;
            C4 = ((Cya + Cx) / m) * S * (p * V0 / 2);
            C5 = (-mza_ / Iz1) * S * Math.Pow(bA, 2) * (p * V0 / 2);
            C6 = V0 / 57.3;
            C9 = -(Cyd / m) * S * (p * V0 / 2);
            C16 = V0 / (57.3 * g);
            
            Cybal1 = 2 * G1 / S / p / Math.Pow(V0, 2);
            Cybal2 = 2 * G2 / S / p / Math.Pow(V0, 2);
            abal1 = 57.3 * (Cybal1 - Cy0) / Cya;
            abal2 = 57.3 * (Cybal2 - Cy0) / Cya;
            dvbal1 = -57.3 * (mz0 + mza * abal1 / 57.3 + Cybal1 * (Xt1 - 0.24)) / mzd;
            dvbal2 = -57.3 * (mz0 + mza * abal2 / 57.3 + Cybal2 * (Xt2 - 0.24)) / mzd;
            dabal = 0;
            ddvbal = 0;
            Y[4] = H0;
            //Моделювання літака
            while (T <= TF+DT)
            {
                KOF();
                DIN();
                SAU();
                Eiller();           
                if (T >= TD-T) //Запис даних у таблицю
                {
                    massTeta.Add(Y[0]);
                    massH.Add(Y[4]);
                    massDV.Add(DV);
                    massNY.Add(NY);
                    Time.Add(T);
                    massa.Add(Y[3]);
                    massdXt.Add(dXt);
                    TD = TD + DD;
                }
                graphTime.Add(T);
                graphNy.Add(NY);
                graphH.Add(Y[4]);
                graphDV.Add(DV);
                T = T + DT;
            }
        }

        public void KOF() //Метод для розрахунку коефіцієнтів при та після скидання вантажу
        {
            if (T > TV && state == 0)// Очікування початку скидання за часом
                state = 1;
            if (Y[7] >= L && state == 1) //Очікування кінця скидання
                state = 2;
            if(state == 1) //Стан під час скидання
            {
                X[7] = Y[8]; // Швидцість переміщення вантажу
                X[8] = an;  // Прискорення вантажу
                double kc = (Xtc - Xt1) / L;
                dXt = kc * Y[7];
                Cy = Cybal1 + Cya * Y[3] / 57.3 + Cyd * DV / 57.3;
                C20 = 57.3 * Cy * S * bA * p * V0 * V0 / 2 / Iz1;
            }
            if(state == 2) //Стан після скидання
            {
                double m = G2 / g;
                C1 = (-mzwz / Iz2) * S * Math.Pow(bA, 2) * ((p * V0) / 2);
                C2 = (-mza / Iz2) * S * bA * (p * Math.Pow(V0, 2) / 2);
                C3 = -mzd * S * bA * p * V0 * V0 / 2 / Iz2;
                C4 = ((Cya + Cx) / m) * S * (p * V0 / 2);
                C5 = (-mza_ / Iz2) * S * Math.Pow(bA, 2) * (p * V0 / 2);
                C6 = V0 / 57.3;
                C9 = -(Cyd / m) * S * (p * V0 / 2);
                C16 = V0 / (57.3 * g);
                dXt = Xt2 - 0.24;
                dabal = abal1 - abal2;
                ddvbal = dvbal1 - dvbal2;
                state = 3;
            }

        }
        public void DIN() //Розрахунок диф рівняння
        {
            X[0] = Y[1]; //Зміна кута тангажу
            X[1] = -C1 * X[0] - C2 * az - C5 * X[3] - C3 * dvz + C20 * dXt; //Прискорення кута тангажу
            X[2] = C4 * az + C9 * dvz; //Зміна кута нахилу траєкторії
            X[3] = X[0] - X[2]; // Зміна кута атаки
            az = Y[3] + dabal;
            dvz = DV + ddvbal;
            X[4] = C6 * Y[2]; // Зміна висота
            NY = C16 * X[2];
        }
        public void SAU() //Розрахунок САУ
        {
            double dH = Y[4] - Hzad;
            double dTeta = Y[0] - Teta0;
            double sigma;
            //sigma = kn * dH;
            //sigma = kn * dH + kn_ * X[4];
            //sigma = kn * dH + kteta * dTeta;
            X[5] = dTeta - Y[5] / T1;
            //sigma = kn * dH + X[5];
            X[6] = dH;
            sigma = kn * dH + kn_*X[4] + Y[6]*kf;
            DV = sigma + Y[1];
            //DV = -2;
        }
        public void Eiller() //Метод інтегрування Ейлера
        {
            for (int i = 0; i < 9; i++)
            {
                Y[i] = Y[i] + X[i] * DT;
            }
        }
    }       
}

