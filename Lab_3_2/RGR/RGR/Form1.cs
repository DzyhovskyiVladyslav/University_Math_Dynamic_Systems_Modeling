using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace RGR
{

    public partial class Form1 : Form
    {
        Rozrakhunok r = new Rozrakhunok();
        public Form1()
        {
            InitializeComponent();
            chart2.ChartAreas[0].AxisY.Minimum = 590;
            pid();
            pid3();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
        }

        public void pid()
        {


            dataGridView1.Rows.Add("C1", r.C1);
            dataGridView1.Rows.Add("C2", r.C2);
            dataGridView1.Rows.Add("C3", r.C3);
            dataGridView1.Rows.Add("C4", r.C4);
            dataGridView1.Rows.Add("C5", r.C5);
            dataGridView1.Rows.Add("C6", r.C6);
            dataGridView1.Rows.Add("C9", r.C9);
            dataGridView1.Rows.Add("C16", r.C16);
            dataGridView1.Rows.Add("da", r.dabal);
            dataGridView1.Rows.Add("dv", r.ddvbal);
        }
      
        public void pid3()
        {

            chart1.Series[0].ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Spline;
            chart2.Series[0].ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Spline;
            chart3.Series[0].ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Spline;

            for (int i = 0; i < r.Time.Count; i++)
            {
                dataGridView3.Rows.Add(r.Time[i],r.massDV[i],r.massNY[i],r.massTeta[i],r.massH[i], r.massa[i], r.massdXt[i]);
                
            }
            for (int i = 0; i < r.graphTime.Count; i++)
            {
                chart1.Series[0].Points.AddXY(r.graphTime[i], r.graphNy[i]);
                chart2.Series[0].Points.AddXY(r.graphTime[i], r.graphH[i]);
                chart3.Series[0].Points.AddXY(r.graphTime[i], r.graphDV[i]);
            }
            }

        private void button1_Click(object sender, EventArgs e)
        {
            panel1.Visible = false;
        }

        private void dataGridView3_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }


        /*  private void tableLayoutPanel1_Paint(object sender, PaintEventArgs e)
          {
              this.tableLayoutPanel1.AutoScroll = false;
              tableLayoutPanel1.HorizontalScroll.Visible = false;

              tableLayoutPanel1.VerticalScroll.Visible = true;
              tableLayoutPanel1.AutoScroll = true;


          }*/






    }
}
   
    
