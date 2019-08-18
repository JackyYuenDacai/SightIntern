using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DaemonConfig
{
    public partial class Login : Form
    {
        public Login()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            String ID = textBox1.Text;
            String PWD = textBox2.Text;

            Boolean ConnectSuccess = true;
            Boolean LoginSuccess = true;


            if (!ConnectSuccess)
            {
                label4.Text = "Failed to connect.";
            }
            else
            {
                if (Request.Login(ID, "CARITAS", Utils.MD5_encode(PWD)) != 0) LoginSuccess = false;
                if (!LoginSuccess)
                {
                    label4.Text = "Wrong ID/PWD";
                }
                else
                {
                    //LOGIN SUCCESS
                    Hide();
                    Main main = new Main();
                    main.Show();
                    
                }
            }
        }
    }
}
