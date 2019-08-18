using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DaemonConfig
{

        static class Log
        {
            public static List<log_entity> STO = new List<log_entity>();
            public static void Add(String time, String msg)
            {
                STO.Add(new log_entity(time, msg));
            }
            public static void Add(String msg)
            {
                STO.Add(new log_entity(Utils.currentTime(), msg));
            }
        public static void Refresh()
            {
                
            }
        }
        class log_entity
        {
            public String time;
            public String message;
            public log_entity(String t, String m)
            {
                time = t;
                message = m;
            }
        }
    
}
