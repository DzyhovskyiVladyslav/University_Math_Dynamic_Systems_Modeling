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
/*            chart1.ChartAreas[0].AxisY.Minimum = -10;
            chart1.ChartAreas[0].AxisY.Maximum = 10;
            chart2.ChartAreas[0].AxisY.Minimum = -10;
            chart2.ChartAreas[0].AxisY.Maximum = 10;
            chart3.ChartAreas[0].AxisY.Minimum = -10;
            chart3.ChartAreas[0].AxisY.Maximum = 10;*/
            pid();
            pid3();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
        }

        public void pid()
        {
            dataGridView1.Rows.Add("a1", r.a1);
            dataGridView1.Rows.Add("a2", r.a2);
            dataGridView1.Rows.Add("a3", r.a3);
            dataGridView1.Rows.Add("a4", r.a4);
            dataGridView1.Rows.Add("a5", r.a5);
            dataGridView1.Rows.Add("a6", r.a6);
            dataGridView1.Rows.Add("a7", r.a7);
            dataGridView1.Rows.Add("b1", r.b1);
            dataGridView1.Rows.Add("b2", r.b2);
            dataGridView1.Rows.Add("b3", r.b3);
            dataGridView1.Rows.Add("b4", r.b4);
            dataGridView1.Rows.Add("b5", r.b5);
            dataGridView1.Rows.Add("b6", r.b6);
            dataGridView1.Rows.Add("b7", r.b7);
        }
      
        public void pid3()
        {

            chart1.Series[0].ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Spline;
            chart2.Series[0].ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Spline;
            chart3.Series[0].ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Spline;

            for (int i = 0; i < r.Time.Count; i++)
            {
                dataGridView3.Rows.Add(r.Time[i], r.massFi[i], r.massPsi[i], r.massX[i], r.massZ[i], r.massGp[i]);
            }
            for (int i = 0; i < r.graphTime.Count; i++)
            {
                chart1.Series[0].Points.AddXY(r.graphTime[i], r.graphZ[i]);
                chart2.Series[0].Points.AddXY(r.graphTime[i], r.graphSk[i]);
                chart3.Series[0].Points.AddXY(r.graphTime[i], r.graphPsi[i]);
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
   
    
