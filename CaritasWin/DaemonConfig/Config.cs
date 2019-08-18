using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DaemonConfig
{
    static class Config
    {
        static public List<reader_config_entity> Readers = new List<reader_config_entity>();
        static public String PWD = "";
        static public void InitializeConfig()
        {
             
        }
        static public void AddReader(reader_config_entity Entity)
        {
            Readers.Add(Entity);
            SaveConfig();
        }
        static public void DelReader(String Alias)
        {

            foreach(reader_config_entity RCE in Readers)
            {
                if (RCE.name == Alias)
                {
                    Readers.Remove(RCE);
                    break;
                }
            }

        }
        static public void SaveConfig()
        {
            String sto = "";
            sto += String.Format("{0}\n{1}\n", PWD, Readers.Count);
            Readers.ForEach(p => 
            {
                sto += p.name;
                sto += '\n';
                sto += p.id;
                sto += '\n';
                sto += p.location;
                sto += '\n';
                sto += p.token;
                sto += '\n';
                sto += p.ip;
                sto += '\n';
                sto += p.port;
                sto += '\n';
                sto += p.extra;
                sto += '\n';
            });

            byte[] bytes = Encoding.Default.GetBytes(sto);
            String str = Convert.ToBase64String(bytes);
            FileStream fs = new FileStream(@"readers.config", FileMode.Truncate, FileAccess.ReadWrite);
            fs.Close();
            using (StreamWriter writer = new StreamWriter(@"readers.config"))
            {
                writer.Write(sto);
            }
        }
        static public void LoadConfig()
        {
            try
            {
                using (StreamReader reader = new StreamReader(@"readers.config"))
                {
                    string ID = reader.ReadLine();
                    string Count = reader.ReadLine();
                    int No = Convert.ToInt32(Count);
                    for(int i = 0; i < No; i++)
                    {
                        reader_config_entity RCE = new reader_config_entity();
                        RCE.name = reader.ReadLine();
                        RCE.id = reader.ReadLine();
                        RCE.location = reader.ReadLine();
                        RCE.token = reader.ReadLine();
                        RCE.ip = reader.ReadLine();
                        RCE.port = reader.ReadLine();
                        RCE.extra = reader.ReadLine();

                        Readers.Add(RCE);
                    }
                
                }
            }catch(Exception ex)
            {
                Console.Write(ex.Message);
                
            }
        }
    }
    class reader_config_entity
    {
        public String name;
        public String id;
        public String location;
        public String token;
        public String ip;
        public String port;
        public String extra;
        public int status = 0;
        //0:NOT CONNECTED
        //1:CONNECTED
        //2:BEGIN SCAN
        //3:FAILED TO CONNECT
        //4:EXCEPTION

        //
        public byte ComAddr;
        public int PortHandle;
        public int portNum;
        public int numOfTags;

        public Dictionary<String, int> counter = new Dictionary<string, int>();
    }
}
