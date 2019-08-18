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
        private String SelectedItemAlias;
        private void listView1_SelectedIndexChanged(object sender, EventArgs e)
        {
            //if(ReaderListview.SelectedItems.Count != 0)
            if (((ListView)sender).FocusedItem.Text != "")
                SelectedItemAlias = ((ListView)sender).FocusedItem.Text;

        }
        private void button1_Click(object sender, EventArgs e)
        {
            //ADD
            reader_config_entity RCE = new reader_config_entity();
            RCE.name = AliasTextBox.Text;
            RCE.location = LocationTextBox.Text;
            RCE.ip = IpTextBox.Text;
            RCE.port = PortTextBox.Text;
            RCE.status = 0;
            Config.AddReader(RCE);

            //REFRESH
            RefreshReaderList();
        }
        private void RefreshReaderList()
        {
            ReaderListview.Items.Clear();
            Config.Readers.ForEach(p =>
            {
                ListViewItem foo = new ListViewItem();
                foo.Text = p.name;
                foo.SubItems.Add(p.ip);
                foo.SubItems.Add(p.location);
                switch (p.status)
                {
                    case 0: foo.SubItems.Add("NOT CONNECTED");break;
                    case 1: foo.SubItems.Add("CONNECTED"); break;
                    case 2: foo.SubItems.Add("SCANNING"); break;
                    case 3: foo.SubItems.Add("FAILED TO CONNECT"); break;
                    case 4: foo.SubItems.Add("EXCEPTION"); break;
                }
                
                ReaderListview.Items.Add(foo);
            });

            ReaderListview.Enabled = true;
            ReaderListview.EndUpdate();
        }
        private void button3_Click(object sender, EventArgs e)
        {
            //REFRESH
            RefreshReaderList();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            //REMOVE


            String Alias;
            Alias = SelectedItemAlias;
            Config.DelReader(Alias);
            RefreshReaderList();
        }
    }
}