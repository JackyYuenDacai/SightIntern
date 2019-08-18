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
        private void SearchNameButton_Click(object sender, EventArgs e)
        {
            //SEARCH BY NAME
            List<user_info_entity> ret = Request.SearchByName(textBox5.Text);
            listView3.Items.Clear();
            ret.ForEach(p =>
            {
                ListViewItem LVI = new ListViewItem();
                LVI.Text = p.Id;
                LVI.SubItems.Add(p.Name);
                listView3.Items.Add(LVI);
            });
        }

        private void SearchIdButton_Click(object sender, EventArgs e)
        {
            List<user_info_entity> ret = Request.SearchById(textBox5.Text);
            listView3.Items.Clear();
            ret.ForEach(p =>
            {
                ListViewItem LVI = new ListViewItem();
                LVI.Text = p.Id;
                LVI.SubItems.Add(p.Name);
                listView3.Items.Add(LVI);
            });

        }
    }
}
        