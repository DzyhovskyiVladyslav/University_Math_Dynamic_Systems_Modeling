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
            //chart2.ChartAreas[0].AxisY.Minimum = 590;
            pid();
            pid3();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
        }

        public void pid()
        {

            dataGridView1.Rows.Add("Широта", r.fiso);
            dataGridView1.Rows.Add("Довгота", r.alphaso);
            dataGridView1.Rows.Add("V", r.V);
            dataGridView1.Rows.Add("Vi", r.Vi);
            dataGridView1.Rows.Add("Vпр", r.Vpr);
            dataGridView1.Rows.Add("Vш", r.Vs);
            dataGridView1.Rows.Add("ЗІШК", r.ZISK);
            dataGridView1.Rows.Add("НВі", r.HBi);
            dataGridView1.Rows.Add("КВ", r.KV);
            dataGridView1.Rows.Add("КЗ", r.KZ);
            dataGridView1.Rows.Add("ККВ", r.KKV);
            dataGridView1.Rows.Add("ІК", r.IK);
            dataGridView1.Rows.Add("МК", r.MK);
            dataGridView1.Rows.Add("КК", r.KK);
            dataGridView1.Rows.Add("Гірос. курс", r.psigo);

        }
      
        public void pid3()
        {

            chart1.Series[0].ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Spline;
            chart2.Series[0].ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Spline;
            chart3.Series[0].ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Spline;

            for (int i = 0; i < r.Time.Count; i++)
            {
                dataGridView3.Rows.Add(r.Time[i], r.massFi[i], r.massAlpha[i], r.massPsi[i], r.massIK[i], r.massX[i], r.massVsx[i], r.massVsz[i]);

            }
            for (int i = 0; i < r.graphTime.Count; i++)
            {
                chart1.Series[0].Points.AddXY(r.graphTime[i], r.graphFi[i]);
                chart2.Series[0].Points.AddXY(r.graphTime[i], r.graphAlpha[i]);
                chart3.Series[0].Points.AddXY(r.graphFi[i], r.graphAlpha[i]);
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
   
    
