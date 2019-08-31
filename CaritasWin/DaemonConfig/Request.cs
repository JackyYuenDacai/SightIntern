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
        static String Ip = "10.211.55.2";
        static String Port = "8888";
        static Boolean SSL = false;
        static public String TagScanRequest = "api/tag_scanned";
        static public String LoginRequest = "api/login";

        static public String TokenValidate = "test/validate_token";
        static public String TokenRefresh = "api/token_update";
        static public String SearchUser = "api/search_user";
        static public String tag_config = "api/tag_config";
        static public String get_records = "api/records";
        static public String submit_request = "api/submit";
        static public String getRequestUrl(String req)
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
            ret += Ip + ":" + Port + "/" + req;
            return ret;
        }
    }
    class user_info_entity
    {
        public String Extra;
        public String Role;
        public String Name;
        public String Id;
        public user_info_entity(String id, String name, String role, String extra)
        {
            Id = id;Name = name;Role = role;Extra = extra;
            
        }
    }
    class record_info_entity
    {
        public String id;
        public String name;
        public String record_token;
        public String location;
        public String tag_id;
        public String tag_type;
        public String time_start;
        public record_info_entity(String Id,String Name,String Record_token,String Location,String TagId,String TagType,String Time_Start)
        {
            id = Id;name = Name;record_token = Record_token;location = Location; tag_id = TagId;tag_type = TagType;time_start = Time_Start;
        }
    }
    static class Request
    {
        static public String Token = null;
        static public String Id = null;
        static public String SOC;

        public delegate void CallBack(ref JObject obj);
        public delegate void ExCallBack(Exception ex);
        static public bool post(String url,CallBack requestHandler,CallBack responseHandler,ExCallBack exceptionHandler)
        {
            
            JObject requestJson = new JObject();
            requestHandler.Invoke(ref requestJson);
            HttpWebRequest request = null;
            request = WebRequest.Create(url) as HttpWebRequest;
            /*
            ServicePointManager.ServerCertificateValidationCallback =
                new RemoteCertificateValidationCallback(CheckValidationResult);
            ServicePointManager.SecurityProtocol = (SecurityProtocolType)3072;
            ServicePointManager.CheckCertificateRevocationList = true;
            ServicePointManager.DefaultConnectionLimit = 100;
            ServicePointManager.Expect100Continue = false;
            */
            request.ProtocolVersion = HttpVersion.Version11;
            request.Method = "POST";
            request.ContentType = "application/json";
            request.Accept = "application/json";
            request.Referer = null;
            request.Timeout = 10000;
            request.AllowAutoRedirect = true;

            try
            {
                StreamWriter writer = new StreamWriter(request.GetRequestStream());
                writer.Write(requestJson.ToString());
                writer.Close();
                HttpWebResponse response = (HttpWebResponse)request.GetResponse();
                Stream stream = response.GetResponseStream();
                string result = string.Empty;
                using (StreamReader sr = new StreamReader(stream))
                {
                    result = sr.ReadToEnd();
                }

                JObject jObject = JsonConvert.DeserializeObject<JObject>(result);

                responseHandler.Invoke(ref jObject);

            }
            catch (Exception ex)
            {
                exceptionHandler(ex);
                return false;
            }
            return true;
        }

        static public int Login(String id,String soc,String pwd)
        {
            int ret = 502;
            post(ServerInfo.getRequestUrl(ServerInfo.LoginRequest),
                delegate(ref JObject JObj1) {
                    Id = id;
                    SOC = soc;
                    JObj1.Add("id", id);
                    JObj1.Add("soc", soc);
                    JObj1.Add("pwd", pwd);
                },
                delegate (ref JObject JObj2) {
                    if (JObj2["error"].ToString() == "0")
                    {
                        ret = 0;
                        JObject retObj = JObj2["data"].ToObject<JObject>();
                        Token = retObj["token"].ToString();
                    }
                },
                delegate (Exception ex)
                {
                    ret = 502;
                });

            return ret;
        }
        static public int TokenValidate()
        {
            int ret = 502;
         
            post(ServerInfo.getRequestUrl(ServerInfo.TokenValidate),
                delegate (ref JObject JObj1) {
                    JObj1.Add("id", Id);
                    JObj1.Add("soc", SOC);
                    JObj1.Add("token", Token);
                },
                delegate (ref JObject JObj2) {
                    if (JObj2["error"].ToString() == "0")
                    {
                        ret = 0;
                    }
                    else
                    {
                        ret = 502;
                    }
                },
                delegate (Exception ex)
                {
                    ret = 502;
                });

            return ret;
        }
        static public int TokenRefresh()
        {
            int ret = 502;
            post(ServerInfo.getRequestUrl(ServerInfo.TokenRefresh),
                delegate (ref JObject JObj1) {
                    JObj1.Add("id", Id);
                    JObj1.Add("soc", SOC);
                    JObj1.Add("token", Token);
                },
                delegate (ref JObject JObj2) {
                    if (JObj2["error"].ToString() == "0")
                    {
                        ret = 0;
                        JObject retObj = JObj2["data"].ToObject<JObject>();
                        Token = retObj["token"].ToString();
                    }
                    else
                    {
                        ret = 502;
                    }
                },
                delegate (Exception ex)
                {
                    ret = 502;
                });

            return ret;
        }
        static public int AttachTag(String id,String soc,String tag_type,String tag_id)
        {
            int ret = 502;
            JObject pam_in = new JObject();
            post(ServerInfo.getRequestUrl(ServerInfo.tag_config),
                delegate(ref JObject JObj1)
                {
                    JObj1.Add("id", Id);
                    JObj1.Add("soc", SOC);
                    JObj1.Add("token", Token);
                    pam_in.Add("type", "add");
                    pam_in.Add("id", id);
                    pam_in.Add("soc", soc);
                    pam_in.Add("tag_type", tag_type);
                    pam_in.Add("tag_id", tag_id);
                    JObj1.Add("parameters", pam_in);
                },
                delegate(ref JObject JObj2)
                {
                    if (JObj2["error"].ToString() == "0") ret = 0;
                },
                delegate (Exception ex)
                {
                    Log.Add(ex.Message);
                    
                });
            return ret;
        }
        static public int DetachTag(String tag_type,String tag_id)
        {
            int ret = 502;
            JObject pam_in = new JObject();
            post(ServerInfo.getRequestUrl(ServerInfo.tag_config),
                delegate (ref JObject JObj1)
                {
                    JObj1.Add("id", Id);
                    JObj1.Add("soc", SOC);
                    JObj1.Add("token", Token);
                    pam_in.Add("type", "del");
                    pam_in.Add("tag_type", tag_type);
                    pam_in.Add("tag_id", tag_id);
                    JObj1.Add("parameters", pam_in);
                },
                delegate (ref JObject JObj2)
                {
                    if (JObj2["error"].ToString() == "0") ret = 0;
                },
                delegate (Exception ex)
                {
                    Log.Add(ex.Message);

                });
            return ret;
        }
        static public List<user_info_entity> SearchById(String id)
        {
            JObject pam_in = new JObject();
            List<user_info_entity> ret = new List<user_info_entity>();
            post(ServerInfo.getRequestUrl(ServerInfo.SearchUser),
                            delegate (ref JObject JObj1) {
                                JObj1.Add("id", Id);
                                JObj1.Add("soc", SOC);
                                JObj1.Add("token", Token);
                                pam_in.Add("type", "id");
                                pam_in.Add("id", id);
                                JObj1.Add("parameters", pam_in);
                            },
                delegate (ref JObject JObj2) {
                    if (JObj2["error"].ToString() == "0")
                    {
                        
                        JObject retObj = JObj2["data"].ToObject<JObject>();
                        JArray users = retObj.GetValue("users").ToObject<JArray>();
                        List<JObject> users_list = users.ToObject<List<JObject>>();
                        users_list.ForEach(p =>
                        {
                            ret.Add(new user_info_entity(p.GetValue("id").ToString(),
                                p.GetValue("name").ToString(),
                                p.GetValue("extra").ToString(),
                                p.GetValue("role").ToString()));
                        });
                      
                    }
                    else
                    {
                        
                    }
                },
                delegate (Exception ex)
                {
                    Log.Add(ex.Message);
                });
            return ret;
        }
        static public List<user_info_entity> SearchByName(String name)
        {
            JObject pam_in = new JObject();
            List<user_info_entity> ret = new List<user_info_entity>();
            post(ServerInfo.getRequestUrl(ServerInfo.SearchUser),
                            delegate (ref JObject JObj1) {
                                JObj1.Add("id", Id);
                                JObj1.Add("soc", SOC);
                                JObj1.Add("token", Token);
                                pam_in.Add("type", "name");
                                pam_in.Add("name", name);
                                JObj1.Add("parameters", pam_in);
                            },
                delegate (ref JObject JObj2) {
                    if (JObj2["error"].ToString() == "0")
                    {

                        JObject retObj = JObj2["data"].ToObject<JObject>();
                        JArray users = retObj.GetValue("users").ToObject<JArray>();
                        List<JObject> users_list = users.ToObject<List<JObject>>();
                        users_list.ForEach(p =>
                        {
                            ret.Add(new user_info_entity(p.GetValue("id").ToString(),
                                p.GetValue("name").ToString(),
                                p.GetValue("extra").ToString(),
                                p.GetValue("role").ToString()));
                        });

                    }
                    else
                    {

                    }
                },
                delegate (Exception ex)
                {
                    Log.Add(ex.Message);
                });
            return ret;
        }
        static public String TagScanned(String location,String tagId,String time)
        {
            String ret = "502";
            JObject JsonData = new JObject();
            post(ServerInfo.getRequestUrl(ServerInfo.TagScanRequest),
                delegate (ref JObject JObj1) {
                    

                    JObj1.Add("id", Id);
                    JObj1.Add("token", Token);
                    JObj1.Add("soc", SOC);
                    JsonData.Add("location", location);
                    JsonData.Add("tag_id", tagId);
                    JsonData.Add("time", time);
                    JsonData.Add("soc", SOC);
                    JObj1.Add("parameters", JsonData);
                },
                delegate (ref JObject JObj2) {
                    if (JObj2["error"].ToString() == "0")
                    {
                        ret = "0";
                        JObject retObj = JObj2["data"].ToObject<JObject>();
                        ret = retObj["record_id"].ToString();
                    }
                    else
                    {
                        ret = "502";
                    }
                },
                delegate (Exception ex)
                {
                    ret = "502";
                });
            return ret;
        }

        static public List<record_info_entity> getRecord()
        {
            JObject pam_in = new JObject();
            List<record_info_entity> ret = new List<record_info_entity>();
            
            post(ServerInfo.getRequestUrl(ServerInfo.get_records),
                            delegate (ref JObject JObj1) {
                                JObj1.Add("id", Id);
                                JObj1.Add("soc", SOC);
                                JObj1.Add("token", Token);
                                pam_in.Add("status", "ongoing");
                                pam_in.Add("id", "test");
                                pam_in.Add("registered", "true");
                                JObj1.Add("parameters", pam_in);
                            },
                delegate (ref JObject JObj2) {
                    if (JObj2["error"].ToString() == "0")
                    {

                        JArray retObj = JObj2["data"].ToObject<JArray>();
                        JArray records = retObj;
                        List<JObject> records_list = records.ToObject<List<JObject>>();
                        records_list.ForEach(p =>
                        {
                            ret.Add(new record_info_entity(p.GetValue("id").ToString(),
                                p.GetValue("name").ToString(),
                                p.GetValue("record_token").ToString(),
                                p.GetValue("location").ToString(),
                                p.GetValue("tag_id").ToString(),
                                p.GetValue("tag_type").ToString(),
                                p.GetValue("time_start").ToString()));

                        });

                    }
                    else
                    {

                    }
                },
                delegate (Exception ex)
                {
                    Log.Add(ex.Message);
                });
            return ret;
        }

        static public int submit(String record_token,String form)
        {
            int ret = 502;
            JObject pam_in = new JObject();
            post(ServerInfo.getRequestUrl(ServerInfo.submit_request),
                delegate (ref JObject JObj1)
                {
                    JObj1.Add("id", Id);
                    JObj1.Add("soc", SOC);
                    JObj1.Add("token", Token);
                    pam_in.Add("record_token", record_token);
                    pam_in.Add("form", form);
                    JObj1.Add("parameters", pam_in);
                },
                delegate (ref JObject JObj2)
                {
                    if (JObj2["error"].ToString() == "0") ret = 0;
                    else
                    {
                        ret = 502;
                        Log.Add(JObj2["error_msg"].ToString());
                    }
                },
                delegate (Exception ex)
                {
                    Log.Add(ex.Message);

                });
            return ret;
        }
        private static bool CheckValidationResult(object sender, X509Certificate certificate, X509Chain chain, SslPolicyErrors sslPolicyErrors)
        {
            //throw new NotImplementedException();
            return true;
        }
    }
}
