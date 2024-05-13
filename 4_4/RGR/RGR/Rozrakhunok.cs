using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RGR
{
    public class Rozrakhunok
    {
        double S = 201.45, ba = 5.285, G0 = 73000, xTv = 24, Iz = 660000, bz = 17, psist = -2.5, bzp = 2, m = 0;
        double V0 = 78, H0 = 500, p = 0.119, g = 9.81;
        double mZbz=-0.458, cybz=1.222, mZpsi=-2.786, cypsi=0.715, cy0=-0.255, cya=5.78, cybB=0.2865, cx=0.13;
        double mZwzv=-13, mZapv=-3.8, mZa=-1.51, mZbB=-0.96;
        double c1, c2, c3, c4, c5, c6, c9, c12, c13, c21, c22, c16;
        double DT=0.01f, DD=5;
        double bB = 0, Ny = 0;
        double myK = 0, Kc =0.006, kn = 0.1, km = 0.5, T1 =20, T2 = 20, Hzad = 600, Lv = 10, Tv=0;
        double dz = 0, DZ = 0, Fist = 0, dH = 0, del = 0, del1 = 0, del2=0, Sg1=0;
        double Sg2 = 0, kwzz = 0, F1 = 0, F2 = 0, Fx = 0, Fgl = 0, F3 = 0, F4d = 0, F4 = 0, F44d = 0, F44 = 0;
        double F6 = 0, F66 = 0, F11d = 0, F11 = 0, Sg = 0, TTzad1 = 0, ttzad = 0;
        double Hgl=0, dHgl=0, Eglz=0, Iglz=0, Egl=0, k7=0;
        double k2 = 0, T4 = 0, ttop1 = 0, dIgl = 0, ttop2 = 0, dDz = 0;

        double Sgl = 560;//200 560, 925
        double ttgl = 4; //2, 2.67, 4;
                                    //2.67, 560
        double Dg;
        double h = 0.05f;
        double T = 0;
        double TD = 5;
        bool check = false;

        double[] X = new double[15], Y = new double[15];
        public List<double> Time = new List<double>();
        public List<double> massdB = new List<double>();
        public List<double> massdZ = new List<double>();
        public List<double> massFist = new List<double>();
        public List<double> massTeta = new List<double>();
        public List<double> massH = new List<double>();
        public List<double> massDzps = new List<double>();
        public List<double> massY5 = new List<double>();
        public List<double> massIglz = new List<double>();
        public List<double> graphTime = new List<double>();
        public List<double> graphH = new List<double>();


        public Rozrakhunok()
        {
            Dg = 57.3 * 500 / ttgl - 300;
            m = G0 / g;

            //коефіціентидиф.рівнянь

            c1 = -mZwzv / Iz * S * ba * ba * p * V0 / 2;
            c2 = -mZa / Iz * S * ba * p * V0 * V0 / 2;
            c3 = -mZbB / Iz * S * ba * p * V0 * V0 / 2;
            c4 = (cya + cx) / m * S * p * V0 / 2;
            c5 = -mZapv / Iz * S * ba * ba * p * V0 / 2;
            c6 = V0 / 57.3f;
            c9 = cybB / m * S * p * V0 / 2;
            c12 = -mZbz / Iz * S * ba * ((p * V0 * V0) / 2);
            c13 = cybz / m * S * p * V0 / 2;
            c21 = -mZpsi / Iz * S * ba * p * V0 * V0 / 2;
            c22 = cypsi / m * S * p * V0 / 2;
            c16 = V0 / 57.3f / g;
            Tv = Math.Sqrt(2 * Lv / 0.3);
            Y[4] = H0;
            Y[5] = 15000;


            while (Y[4] > 20)
            {
                Hgl = ttgl / 57.3 * (Y[5] + 300);
                dHgl = Y[4] - Hgl;
                Eglz = 57.3 * dHgl / (Y[5] + 300);
                Iglz = Sgl * Eglz + dIgl;

                if (Iglz >= 250)
                {
                    Fgl = 250;
                }
                else if(Iglz < 250 && Iglz > -250){
                    Fgl = Iglz;
                }
                else
                {
                    Fgl = -250;
                }

                Egl = Y[13] / 560;

                if (Y[5] < (Dg + 3000))
                {
                    dDz = 2;
                    ttop1 = 5;
                }
                if (DZ >= 17)
                {
                    dDz = 0;
                    ttop1 = 0;
                    DZ = 17;
                }
                if (0.002 * Y[6] >= 10)
                {
                    F1 = 10;
                }
                else if(0.002 * Y[6] < 10 && 0.002 * Y[6] > -10){
                    F1 = 0.002 * Y[6];
                }
                else
                {
                    F1 = -10;
                }
                dz = F1 + X[7] + 0.2 * X[6];

                if (dz >= 10)
                {
                    F2 = 10;
                }
                else if(dz < 10 && dz > -10){
                    F2 = dz;
                }
                else
                {
                    F2 = -10;
                }

                del1 = F2 + 1 * Y[0] + Y[8];

                if (del1 >= 8)
                {
                    F3 = 8;
                }
                else if(del1 < 8 && del1 > -8){
                    F3 = del1;
                }
                else
                {
                    F3 = -8;
                }

                F4d = 1 * Y[1] + F3;

                if (F4d >= 10)
                {
                    F4 = 10;
                }
                else if(F4d < 10 && F4d > -10){
                    F4 = F4d;
                }
                else
                {
                    F4 = -10;
                }

                Sg1 = F4;

                if (Y[4] >= 250)
                {
                    k7 = 15;
                    k2 = 210;
                    T4 = 2.3;
                    F66 = 7;
                }
                else if(Y[4] < 250 && Y[4] > 100) {
                    k7 = 6.5;
                    k2 = 90;
                    T4 = 2.3;
                    F66 = 3.5;
                }
                else
                {
                    k7 = 6.5;
                    k2 = 90;
                    T4 = 1;
                    F66 = 3.5;
                }

                TTzad1 = k7 * Egl + X[9] + X[10];

                F11d = Y[12] + X[10];

                if (F11d >= 7.5)
                {
                    F11 = 7.5;
                }
                else if(F11d < 7.5 && F11d > -7.5){
                    F11 = F11d;
                }
                else
                {
                    F11 = 7.5;
                }
                ttzad = -F11;

                if (4 * ttzad >= F66)
                {
                    F6 = F66;
                }
                else if(4 * ttzad < F66 && 4 * ttzad > -F66){
                    F6 = 4 * ttzad;
                }
                else
                {
                    F6 = -F66;
                }

                del2 = -F6;

                F4d = (1 + kwzz) * Y[1] + del2;

                if (F44d >= 10)
                {
                    F44 = 10;
                }
                else if(F44d < 10 && F44d > -10){
                    F44 = F44d;
                }
                else
                {
                    F44 = -10;
                }
                Sg2 = F44;

                if (dHgl > 0)
                {
                    check = true;
                }

                if (check)
                {
                    kwzz = 3;
                    ttop2 = 2.5;
                    Sg = Sg2;
                    del = del2;
                }
                else
                {
                    kwzz = 0;
                    ttop2 = 0;
                    Sg = Sg1;
                    del = del1;
                }

                if (del >= 2)
                {
                    Fx = 0.6;
                }
                else if(del < 2 && del > -2){
                    Fx = 0;
                }
                else
                {
                    Fx = -0.6;
                }

                bB = Sg + Y[14];
                Fist = -0.14706 * DZ;
                X[0] = Y[1];
                X[1] = -c1 * X[0] - c2 * Y[3] - c5 * X[3] - c3 * bB - c12 * DZ - c21 * Fist;
                X[2] = c4 * Y[3] + c9 * bB + c13 * DZ + c22 * Fist;
                X[3] = X[0] - X[2];
                X[4] = c6 * Y[2];
                X[5] = -V0 * Math.Cos(Y[2] / 57.3);//Dzps
                X[6] = Y[4] - 500; //dH
                X[7] = (0.4 * X[6] - Y[7]);// use x
                X[8] = (ttop1 - Y[8]) / 2; //ttopuse y
                X[9] = (k2 * Egl - Y[9]) / 0.7; //k2 use x
                X[10] = (6 * (Y[0] + ttop2) - Y[10]) / 1.7; //k11 use x
                X[11] = (13 * (Y[0] + ttop2) - Y[11]) / 15;//k8 use x
                X[12] = (TTzad1 - Y[12]) / T4; //k4 use y
                X[13] = (Fgl - Y[13]) / 0.2; //Igluse y
                X[14] = Fx;
                DZ += dDz * h;


                for (int i = 0; i < 15; i++)
                {
                    Y[i] = Y[i] + X[i] * 0.01;
                }
                if (T >= TD)
                {             
                    Time.Add(T);
                    massdB.Add(bB);
                    massdZ.Add(dz);
                    massFist.Add(Fist);
                    massTeta.Add(Y[2]);
                    massH.Add(Y[4]);
                    massDzps.Add(Y[5]);
                    massY5.Add(Y[6]);
                    massIglz.Add(Iglz);
                    TD += DD;
                }
                graphTime.Add(Math.Round(Y[5]));
                graphH.Add(Y[4]);
                T = T + DT;
            }
        }

    }       
}

