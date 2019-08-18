namespace DaemonConfig
{
    partial class Main
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.ReaderConfig = new System.Windows.Forms.TabPage();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.button6 = new System.Windows.Forms.Button();
            this.button5 = new System.Windows.Forms.Button();
            this.button3 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.LocationTextBox = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.PortTextBox = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.IpTextBox = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.AliasTextBox = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.ReaderListview = new System.Windows.Forms.ListView();
            this.TagConfig = new System.Windows.Forms.TabPage();
            this.QueryTagButton = new System.Windows.Forms.Button();
            this.TagListView = new System.Windows.Forms.ListView();
            this.AttachTagButton = new System.Windows.Forms.Button();
            this.listView3 = new System.Windows.Forms.ListView();
            this.SearchIdButton = new System.Windows.Forms.Button();
            this.SearchNameButton = new System.Windows.Forms.Button();
            this.textBox5 = new System.Windows.Forms.TextBox();
            this.LogPage = new System.Windows.Forms.TabPage();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.button4 = new System.Windows.Forms.Button();
            this.LogListview = new System.Windows.Forms.ListView();
            this.tabControl1.SuspendLayout();
            this.ReaderConfig.SuspendLayout();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.TagConfig.SuspendLayout();
            this.LogPage.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.ReaderConfig);
            this.tabControl1.Controls.Add(this.TagConfig);
            this.tabControl1.Controls.Add(this.LogPage);
            this.tabControl1.Location = new System.Drawing.Point(0, 13);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(1323, 877);
            this.tabControl1.TabIndex = 0;
            // 
            // ReaderConfig
            // 
            this.ReaderConfig.Controls.Add(this.groupBox1);
            this.ReaderConfig.Controls.Add(this.ReaderListview);
            this.ReaderConfig.Location = new System.Drawing.Point(4, 33);
            this.ReaderConfig.Name = "ReaderConfig";
            this.ReaderConfig.Padding = new System.Windows.Forms.Padding(3);
            this.ReaderConfig.Size = new System.Drawing.Size(1315, 840);
            this.ReaderConfig.TabIndex = 0;
            this.ReaderConfig.Text = "Reader Config";
            this.ReaderConfig.UseVisualStyleBackColor = true;
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.groupBox2);
            this.groupBox1.Controls.Add(this.LocationTextBox);
            this.groupBox1.Controls.Add(this.label5);
            this.groupBox1.Controls.Add(this.PortTextBox);
            this.groupBox1.Controls.Add(this.label4);
            this.groupBox1.Controls.Add(this.IpTextBox);
            this.groupBox1.Controls.Add(this.label3);
            this.groupBox1.Controls.Add(this.AliasTextBox);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Location = new System.Drawing.Point(6, 6);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(304, 822);
            this.groupBox1.TabIndex = 1;
            this.groupBox1.TabStop = false;
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.button6);
            this.groupBox2.Controls.Add(this.button5);
            this.groupBox2.Controls.Add(this.button3);
            this.groupBox2.Controls.Add(this.button2);
            this.groupBox2.Controls.Add(this.button1);
            this.groupBox2.Location = new System.Drawing.Point(12, 360);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(286, 456);
            this.groupBox2.TabIndex = 8;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Actions";
            // 
            // button6
            // 
            this.button6.Location = new System.Drawing.Point(6, 260);
            this.button6.Name = "button6";
            this.button6.Size = new System.Drawing.Size(270, 52);
            this.button6.TabIndex = 4;
            this.button6.Text = "Begin Scanning";
            this.button6.UseVisualStyleBackColor = true;
            this.button6.Click += new System.EventHandler(this.button6_Click);
            // 
            // button5
            // 
            this.button5.Location = new System.Drawing.Point(8, 202);
            this.button5.Name = "button5";
            this.button5.Size = new System.Drawing.Size(270, 52);
            this.button5.TabIndex = 3;
            this.button5.Text = "Connect";
            this.button5.UseVisualStyleBackColor = true;
            this.button5.Click += new System.EventHandler(this.button5_Click);
            // 
            // button3
            // 
            this.button3.Location = new System.Drawing.Point(6, 144);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(270, 52);
            this.button3.TabIndex = 2;
            this.button3.Text = "Refresh";
            this.button3.UseVisualStyleBackColor = true;
            this.button3.Click += new System.EventHandler(this.button3_Click);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(6, 86);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(270, 52);
            this.button2.TabIndex = 1;
            this.button2.Text = "Remove";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(6, 28);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(270, 52);
            this.button1.TabIndex = 0;
            this.button1.Text = "Add";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // LocationTextBox
            // 
            this.LocationTextBox.Location = new System.Drawing.Point(12, 296);
            this.LocationTextBox.Name = "LocationTextBox";
            this.LocationTextBox.Size = new System.Drawing.Size(280, 29);
            this.LocationTextBox.TabIndex = 7;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(12, 267);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(86, 25);
            this.label5.TabIndex = 6;
            this.label5.Text = "Location";
            // 
            // PortTextBox
            // 
            this.PortTextBox.Location = new System.Drawing.Point(12, 215);
            this.PortTextBox.Name = "PortTextBox";
            this.PortTextBox.Size = new System.Drawing.Size(280, 29);
            this.PortTextBox.TabIndex = 5;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(12, 186);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(121, 25);
            this.label4.TabIndex = 4;
            this.label4.Text = "Port Number";
            // 
            // IpTextBox
            // 
            this.IpTextBox.Location = new System.Drawing.Point(12, 132);
            this.IpTextBox.Name = "IpTextBox";
            this.IpTextBox.Size = new System.Drawing.Size(280, 29);
            this.IpTextBox.TabIndex = 3;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(12, 103);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(30, 25);
            this.label3.TabIndex = 2;
            this.label3.Text = "IP";
            // 
            // AliasTextBox
            // 
            this.AliasTextBox.Location = new System.Drawing.Point(12, 58);
            this.AliasTextBox.Name = "AliasTextBox";
            this.AliasTextBox.Size = new System.Drawing.Size(280, 29);
            this.AliasTextBox.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(7, 29);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(55, 25);
            this.label1.TabIndex = 0;
            this.label1.Text = "Alias";
            // 
            // ReaderListview
            // 
            this.ReaderListview.FullRowSelect = true;
            this.ReaderListview.GridLines = true;
            this.ReaderListview.Location = new System.Drawing.Point(327, 6);
            this.ReaderListview.Name = "ReaderListview";
            this.ReaderListview.Size = new System.Drawing.Size(952, 822);
            this.ReaderListview.Sorting = System.Windows.Forms.SortOrder.Descending;
            this.ReaderListview.TabIndex = 0;
            this.ReaderListview.UseCompatibleStateImageBehavior = false;
            this.ReaderListview.View = System.Windows.Forms.View.Details;
            this.ReaderListview.SelectedIndexChanged += new System.EventHandler(this.listView1_SelectedIndexChanged);
            // 
            // TagConfig
            // 
            this.TagConfig.Controls.Add(this.QueryTagButton);
            this.TagConfig.Controls.Add(this.TagListView);
            this.TagConfig.Controls.Add(this.AttachTagButton);
            this.TagConfig.Controls.Add(this.listView3);
            this.TagConfig.Controls.Add(this.SearchIdButton);
            this.TagConfig.Controls.Add(this.SearchNameButton);
            this.TagConfig.Controls.Add(this.textBox5);
            this.TagConfig.Location = new System.Drawing.Point(4, 33);
            this.TagConfig.Name = "TagConfig";
            this.TagConfig.Padding = new System.Windows.Forms.Padding(3);
            this.TagConfig.Size = new System.Drawing.Size(1315, 840);
            this.TagConfig.TabIndex = 1;
            this.TagConfig.Text = "Tag Config";
            this.TagConfig.UseVisualStyleBackColor = true;
            // 
            // QueryTagButton
            // 
            this.QueryTagButton.Location = new System.Drawing.Point(28, 718);
            this.QueryTagButton.Name = "QueryTagButton";
            this.QueryTagButton.Size = new System.Drawing.Size(211, 40);
            this.QueryTagButton.TabIndex = 6;
            this.QueryTagButton.Text = "Query Tag";
            this.QueryTagButton.UseVisualStyleBackColor = true;
            // 
            // TagListView
            // 
            this.TagListView.Location = new System.Drawing.Point(299, 47);
            this.TagListView.Name = "TagListView";
            this.TagListView.Size = new System.Drawing.Size(957, 742);
            this.TagListView.TabIndex = 5;
            this.TagListView.UseCompatibleStateImageBehavior = false;
            this.TagListView.View = System.Windows.Forms.View.Details;
            // 
            // AttachTagButton
            // 
            this.AttachTagButton.Location = new System.Drawing.Point(28, 671);
            this.AttachTagButton.Name = "AttachTagButton";
            this.AttachTagButton.Size = new System.Drawing.Size(211, 40);
            this.AttachTagButton.TabIndex = 4;
            this.AttachTagButton.Text = "Attach Tag";
            this.AttachTagButton.UseVisualStyleBackColor = true;
            // 
            // listView3
            // 
            this.listView3.Location = new System.Drawing.Point(28, 214);
            this.listView3.Name = "listView3";
            this.listView3.Size = new System.Drawing.Size(211, 450);
            this.listView3.TabIndex = 3;
            this.listView3.UseCompatibleStateImageBehavior = false;
            this.listView3.View = System.Windows.Forms.View.Details;
            // 
            // SearchIdButton
            // 
            this.SearchIdButton.Location = new System.Drawing.Point(28, 154);
            this.SearchIdButton.Name = "SearchIdButton";
            this.SearchIdButton.Size = new System.Drawing.Size(211, 45);
            this.SearchIdButton.TabIndex = 2;
            this.SearchIdButton.Text = "Search By Id";
            this.SearchIdButton.UseVisualStyleBackColor = true;
            this.SearchIdButton.Click += new System.EventHandler(this.SearchIdButton_Click);
            // 
            // SearchNameButton
            // 
            this.SearchNameButton.Location = new System.Drawing.Point(28, 91);
            this.SearchNameButton.Name = "SearchNameButton";
            this.SearchNameButton.Size = new System.Drawing.Size(211, 45);
            this.SearchNameButton.TabIndex = 1;
            this.SearchNameButton.Text = "Search By Name";
            this.SearchNameButton.UseVisualStyleBackColor = true;
            this.SearchNameButton.Click += new System.EventHandler(this.SearchNameButton_Click);
            // 
            // textBox5
            // 
            this.textBox5.Location = new System.Drawing.Point(28, 47);
            this.textBox5.Name = "textBox5";
            this.textBox5.Size = new System.Drawing.Size(211, 29);
            this.textBox5.TabIndex = 0;
            // 
            // LogPage
            // 
            this.LogPage.Controls.Add(this.groupBox3);
            this.LogPage.Controls.Add(this.LogListview);
            this.LogPage.Location = new System.Drawing.Point(4, 33);
            this.LogPage.Name = "LogPage";
            this.LogPage.Padding = new System.Windows.Forms.Padding(3);
            this.LogPage.Size = new System.Drawing.Size(1315, 840);
            this.LogPage.TabIndex = 2;
            this.LogPage.Text = "Log";
            this.LogPage.UseVisualStyleBackColor = true;
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.button4);
            this.groupBox3.Location = new System.Drawing.Point(6, 21);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(236, 797);
            this.groupBox3.TabIndex = 1;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "Actions";
            // 
            // button4
            // 
            this.button4.Location = new System.Drawing.Point(6, 45);
            this.button4.Name = "button4";
            this.button4.Size = new System.Drawing.Size(224, 45);
            this.button4.TabIndex = 0;
            this.button4.Text = "Refresh";
            this.button4.UseVisualStyleBackColor = true;
            this.button4.Click += new System.EventHandler(this.button4_Click);
            // 
            // LogListview
            // 
            this.LogListview.Location = new System.Drawing.Point(263, 21);
            this.LogListview.Name = "LogListview";
            this.LogListview.Size = new System.Drawing.Size(1005, 797);
            this.LogListview.TabIndex = 0;
            this.LogListview.UseCompatibleStateImageBehavior = false;
            this.LogListview.View = System.Windows.Forms.View.Details;
            // 
            // Main
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(11F, 24F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1318, 886);
            this.Controls.Add(this.tabControl1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "Main";
            this.ShowIcon = false;
            this.Text = "Main";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.Main_FormClosed);
            this.tabControl1.ResumeLayout(false);
            this.ReaderConfig.ResumeLayout(false);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.TagConfig.ResumeLayout(false);
            this.TagConfig.PerformLayout();
            this.LogPage.ResumeLayout(false);
            this.groupBox3.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage ReaderConfig;
        private System.Windows.Forms.TabPage TagConfig;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.Button button3;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TextBox LocationTextBox;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox PortTextBox;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox IpTextBox;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox AliasTextBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ListView ReaderListview;
        private System.Windows.Forms.TabPage LogPage;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.ListView LogListview;
        private System.Windows.Forms.Button button4;
        private System.Windows.Forms.Button QueryTagButton;
        private System.Windows.Forms.ListView TagListView;
        private System.Windows.Forms.Button AttachTagButton;
        private System.Windows.Forms.ListView listView3;
        private System.Windows.Forms.Button SearchIdButton;
        private System.Windows.Forms.Button SearchNameButton;
        private System.Windows.Forms.TextBox textBox5;
        private System.Windows.Forms.Button button5;
        private System.Windows.Forms.Button button6;
    }
}