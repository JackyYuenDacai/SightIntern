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
    public partial class Main : Form
    {
        private Timer statusUpdate;
        public Main()
        {
            InitializeComponent();
            //ListViewGroup ReaderList = new ListViewGroup();
            ColumnHeader Alias = new ColumnHeader();
            Alias.Text = "Alias";
            Alias.Width = 125;
            Alias.TextAlign = HorizontalAlignment.Left;

            ColumnHeader Address = new ColumnHeader();
            Address.Text = "IP";
            Address.Width = 50;
            Address.TextAlign = HorizontalAlignment.Left;

            ColumnHeader Location = new ColumnHeader();
            Location.Text = "Location";
            Location.Width = 75;
            Location.TextAlign = HorizontalAlignment.Left;

            ColumnHeader Status = new ColumnHeader();
            Status.Text = "Status";
            Status.Width = 50;
            Status.TextAlign = HorizontalAlignment.Left;

            ReaderListview.Columns.Add(Alias);
            ReaderListview.Columns.Add(Address);
            ReaderListview.Columns.Add(Location);
            ReaderListview.Columns.Add(Status);

            ListViewItem foo = new ListViewItem();
            foo.Text = "boo";
            foo.SubItems.Add("foo");
            foo.SubItems.Add("bar");
            foo.SubItems.Add("foo");

            ReaderListview.Items.Add(foo);
            ReaderListview.Enabled = true;
            ReaderListview.EndUpdate();

            ColumnHeader ScannedTime = new ColumnHeader();
            ScannedTime.Text = "Time";
            ScannedTime.Width = 125;

            ColumnHeader TagId = new ColumnHeader();
            TagId.Text = "Tag ID";
            TagId.Width = 150;

            ColumnHeader ReaderLocation = new ColumnHeader();
            ReaderLocation.Text = "Scanned Location";
            ReaderLocation.Width = 200;

            TagListView.Columns.Add(ScannedTime);
            TagListView.Columns.Add(TagId);
            TagListView.Columns.Add(ReaderLocation);

            ColumnHeader LogTime = new ColumnHeader();
            LogTime.Text = "Time";
            LogTime.Width = 75;

            ColumnHeader LogMessage = new ColumnHeader();
            LogMessage.Text = "Message";
            LogMessage.Width = 375;

            LogListview.Columns.Add(LogTime);
            LogListview.Columns.Add(LogMessage);


            ColumnHeader SearchName = new ColumnHeader();
            SearchName.Text = "Name";
            SearchName.Width = 65;

            ColumnHeader SearchId = new ColumnHeader();
            SearchId.Text = "Id";
            SearchId.Width = 35;

            listView3.Columns.Add(SearchId);
            listView3.Columns.Add(SearchName
                );

            Config.InitializeConfig();
            Config.LoadConfig();
        }

        private void Main_FormClosed(object sender, FormClosedEventArgs e)
        {
            //Application End;
            Environment.Exit(0);
        }

 

        private void button5_Click(object sender, EventArgs e)
        {
            ReaderConnect.Connect();
            ReaderConnect.ConnectRetry();
        }

        private void button6_Click(object sender, EventArgs e)
        {
            ReaderConnect.RegularScan();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            LogListview.Items.Clear();

            Log.STO.ForEach(p =>
            {
                ListViewItem LVI = new ListViewItem();
                LVI.Text = p.time;
                LVI.SubItems.Add(p.message);
                LogListview.Items.Add(LVI);

            });

            LogListview.EndUpdate();
        }

        private void button7_Click(object sender, EventArgs e)
        {
            if(Request.TokenValidate() == 0)
            {
                richTextBox1.Text = "Token Validation Success\n" + richTextBox1.Text;
            }
            else
            {
                richTextBox1.Text = "Token Validation Failed\n" + richTextBox1.Text;
            }
        }

        private void button8_Click(object sender, EventArgs e)
        {
            if (Request.TokenRefresh() == 0)
            {
                richTextBox1.Text = "Token Refresh Success\t" + Request.Token +"\n" + richTextBox1.Text;
            }
            else
            {
                richTextBox1.Text = "Token Refresh Failed\n" + richTextBox1.Text;
            }
        }

        private void button15_Click(object sender, EventArgs e)
        {
            LocalReaderConnect.Connect();
            if (LocalReaderConnect.ComOpen)
            {
                LocalReaderConnect.RegularScan();
                toolStripStatusLabel2.Text = "Local Reader Connected.";
            }
            else
            {
                toolStripStatusLabel2.Text = "Local Reader failed to connect.";
            }
        }

        private void button16_Click(object sender, EventArgs e)
        {
            ReaderConnect.Disconnect();
            LocalReaderConnect.Disconnect();
        }

        private void AttachTagButton_Click(object sender, EventArgs e)
        {

        }
    }
}
