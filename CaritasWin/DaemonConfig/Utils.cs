using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace DaemonConfig
{
    static class Utils
    {
        static public String MD5_encode(String text)
        {
            MD5CryptoServiceProvider md5 = new MD5CryptoServiceProvider();
            byte[] bytValue, bytHash;
            bytValue = Encoding.UTF8.GetBytes(text);
            bytHash = md5.ComputeHash(bytValue);
            md5.Clear();
            string strTemp = string.Empty;
            for (int i = 0; i < bytHash.Length; i++)
            {
                strTemp += bytHash[i].ToString("X").PadLeft(2, '0');
            }
            return strTemp.ToLower();
        }
        static public String currentTime()
        {

            return DateTime.Now.ToLocalTime().ToString("yyyy-MM-dd HH:mm:ss");
        }
    }
}
