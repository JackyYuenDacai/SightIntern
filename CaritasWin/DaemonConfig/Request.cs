using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Security;
using System.Security.Cryptography;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading.Tasks;

namespace DaemonConfig
{
    static class ServerInfo{
        static String Ip = "127.0.0.1";
        static String Port = "443";
        static Boolean SSL = true;
        static String RequestPath = "api/tag_scanned";
        static public String getLoginRequestUrl()
        {
            String ret = null;
            if (SSL)
            {
                ret += "https://";
            }
            else
            {
                ret += "http://";
            }
            ret += Ip + ":" + Port + "//" + RequestPath;
            return ret;
        }
        static public String getScannedRequestUrl()
        {
            String ret = null;
            if (SSL)
            {
                ret += "https://";
            }
            else
            {
                ret += "http://";
            }
            ret += Ip + ":" + Port + "//" + RequestPath;
            return ret;

        }
    }
    class user_info_entity
    {
        public String Name;
        public String Id;
    }
    static class Request
    {
        static String Token = null;
        static String Id = null;
        static String SOC;
        static public int Login(String id,String soc,String pwd)
        {
            int ret = 502;
        
            JObject requestJson = new JObject();
            Id = id;
            SOC = soc;
            requestJson.Add("id", id);
            requestJson.Add("soc", soc);
            requestJson.Add("pwd", pwd);
            HttpWebRequest request = null;
            request = WebRequest.Create(ServerInfo.getLoginRequestUrl()) as HttpWebRequest;

            ServicePointManager.ServerCertificateValidationCallback =
                new RemoteCertificateValidationCallback(CheckValidationResult);
            ServicePointManager.SecurityProtocol = (SecurityProtocolType)3072;
            ServicePointManager.CheckCertificateRevocationList = true;
            ServicePointManager.DefaultConnectionLimit = 100;
            ServicePointManager.Expect100Continue = false;

            request.ProtocolVersion = HttpVersion.Version11;
            request.Method = "POST";
            request.ContentType = "application/json";
            request.Accept = "application/json";
            request.Referer = null;
            request.Timeout = 1000;
            request.AllowAutoRedirect = true;
            
            try
            {
                StreamWriter writer = new StreamWriter(request.GetRequestStream());
                writer.Write(requestJson.ToString());
                HttpWebResponse response = (HttpWebResponse)request.GetResponse();
                Stream stream = response.GetResponseStream();
                string result = string.Empty;
                using (StreamReader sr = new StreamReader(stream))
                {
                    result = sr.ReadToEnd();
                }
                JObject jObject = JsonConvert.DeserializeObject<JObject>(result);

                if (jObject["error"].ToString() == "0")
                {
                    ret = 0;
                    JObject retObj = jObject["data"].ToObject<JObject>();
                    Token = retObj["token"].ToString();
                }
                Console.Write(result);
            }
            catch(Exception ex)
            {
                ret = 502;
            }
            
            return ret;
        }
        static public List<user_info_entity> SearchById(String id)
        {
            List<user_info_entity> ret = new List<user_info_entity>();
            return ret;
        }
        static public List<user_info_entity> SearchByName(String name)
        {
            List<user_info_entity> ret = new List<user_info_entity>();
            return ret;
        }
        static public int TagScanned(String location,String tagId,String time)
        {
            int ret = 502;

            JObject requestJson = new JObject();
            JObject JsonData = new JObject();
            
            requestJson.Add("id", Id);
            requestJson.Add("token", Token);
            requestJson.Add("soc", SOC);
            JsonData.Add("location", location);
            JsonData.Add("tag_id", tagId);
            JsonData.Add("time", time);
            requestJson.Add("parameters", JsonData);

            HttpWebRequest request = null;
            request = WebRequest.Create(ServerInfo.getLoginRequestUrl()) as HttpWebRequest;

            ServicePointManager.ServerCertificateValidationCallback =
                new RemoteCertificateValidationCallback(CheckValidationResult);
            ServicePointManager.SecurityProtocol = (SecurityProtocolType)3072;
            ServicePointManager.CheckCertificateRevocationList = true;
            ServicePointManager.DefaultConnectionLimit = 100;
            ServicePointManager.Expect100Continue = false;

            request.ProtocolVersion = HttpVersion.Version11;
            request.Method = "POST";
            request.ContentType = "application/json";
            request.Accept = "application/json";
            request.Referer = null;
            request.Timeout = 1000;
            request.AllowAutoRedirect = true;

            try
            {
                StreamWriter writer = new StreamWriter(request.GetRequestStream());
                writer.Write(requestJson.ToString());
                HttpWebResponse response = (HttpWebResponse)request.GetResponse();
                Stream stream = response.GetResponseStream();
                string result = string.Empty;
                using (StreamReader sr = new StreamReader(stream))
                {
                    result = sr.ReadToEnd();
                }
                JObject jObject = JsonConvert.DeserializeObject<JObject>(result);

                if (jObject["error"].ToString() == "0")
                {
                    ret = 0;
                }
                Console.Write(result);
            }
            catch (Exception ex)
            {
                ret = 502;
            }

            return ret;
        }
        private static bool CheckValidationResult(object sender, X509Certificate certificate, X509Chain chain, SslPolicyErrors sslPolicyErrors)
        {
            throw new NotImplementedException();
        }
    }
}
