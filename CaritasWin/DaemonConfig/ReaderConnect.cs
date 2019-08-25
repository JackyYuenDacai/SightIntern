using ReaderB;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace DaemonConfig
{
    struct IPPORT
    {
        public String ip;
        public String port;
        public int portNum;
        public byte ComAddr;
        public int PortHandle;
        public String Location;
        public int numOfTags;
    };

    struct TAGS
    {
        public byte[] EPC;
        public byte[] USER;
        //public String TID;
        //public String Location;
    };
    static class ReaderConnect
    {
        public static IPPORT[] iparr = new IPPORT[50];
        public static TAGS[] TagsNear = new TAGS[50];
        public static int numOfReader = 0;
        public static Timer[] reg;
        public static Timer counterThread;

        public static Dictionary<String, int>[] counter = new Dictionary<string, int>[50];
        public static void ClearCounter()
        {
            //AUTO CLEAR OF COUNTER OF EACH READER AFTER 5 SECS
            counterThread = new System.Threading.Timer(new System.Threading.TimerCallback(state =>
            {
                for (int i = 0; i < numOfReader; i++)
                {
                    counter[i].Clear();
                }
            }), 0, 0, 5000);
        }
        public static void AsyncConnect()
        {

        }
        public static void AsyncConnectRetry()
        {

        }
        public static void Connect()
        {
            Config.Readers.ForEach(RC =>
            {
                try
                {
                    if (RC.status == 0)
                    {
                        RC.portNum = Convert.ToInt32(RC.port);

                    }
                    int i = StaticClassReaderB.OpenNetPort(RC.portNum,
                        RC.ip,
                        ref RC.ComAddr,
                        ref RC.PortHandle);

                    if (i == 0)
                    {
                        RC.status = 1;
                    }
                    else
                    {
                        RC.status = 3;
                    }
                }
                catch (Exception ex)
                {
                    RC.status = 3;
                    Log.Add(ex.Message);
                }
            });
        }
        public static void ConnectRetry()
        {
            Config.Readers.ForEach(RC =>
            {
                try
                {
                    if (RC.status == 3)
                    {
                        RC.portNum = Convert.ToInt32(RC.port);

                    }
                    int i = StaticClassReaderB.OpenNetPort(RC.portNum,
                        RC.ip,
                        ref RC.ComAddr,
                        ref RC.PortHandle);

                    if (i == 0)
                    {
                        RC.status = 1;
                    }
                    else
                    {
                        RC.status = 3;
                    }
                }
                catch (Exception ex)
                {
                    RC.status = 3;
                    Log.Add(ex.Message);
                }
            });
        }
        public static void RegularScan()
        {
            Config.Readers.ForEach(RC =>
            {
                if (RC.status == 1)
                {
                    RC.ScanThread = new Timer(new System.Threading.TimerCallback(state =>
                    {
                        byte[] epclenandepc = new byte[80000];
                        byte[] data = new byte[80000];
                        int EPCLEN = 12;
                        int CountTH = 5;
                        //EPC  len  == 12
                        int cardnum = 0;
                        StaticClassReaderB.Inventory_G2(
                            ref RC.ComAddr, 0, 1, 0
                            , epclenandepc, ref RC.numOfTags, ref cardnum,
                            RC.PortHandle);

                        for (int j = 0; j < cardnum; j++)
                        {
                            ReaderConnect.TagsNear[j].EPC = new byte[EPCLEN];
                            for (int a = 0; a < EPCLEN; a++)
                                ReaderConnect.TagsNear[j].EPC[a] = epclenandepc[(j + 1) + j * EPCLEN + a];

                            ReaderConnect.TagsNear[j].USER = new byte[16];
                            Console.WriteLine("\tTAG EPC SCANNED:" + BitConverter.ToString(ReaderConnect.TagsNear[j].EPC));
                            if (RC.counter.ContainsKey(BitConverter.ToString(ReaderConnect.TagsNear[j].EPC)))
                            {
                                RC.counter[BitConverter.ToString(ReaderConnect.TagsNear[j].EPC)] += 1;
                            }
                            else
                            {
                                RC.counter.Add(BitConverter.ToString(ReaderConnect.TagsNear[j].EPC), 1);
                            }
                        }
                        for (int j = 0; j < cardnum; j++)
                        {

                            if (RC.counter.ContainsKey(BitConverter.ToString(ReaderConnect.TagsNear[j].EPC)))
                                if (RC.counter[BitConverter.ToString(ReaderConnect.TagsNear[j].EPC)] >= CountTH)
                                {
                                    RC.counter[BitConverter.ToString(ReaderConnect.TagsNear[j].EPC)] = 0;

                                    Console.WriteLine("TAG RECORDED:" + BitConverter.ToString(ReaderConnect.TagsNear[j].EPC));


                                    //SENDING DATA TO DATABASE
                                    try
                                    {


                                        Request.TagScanned(RC.location,
                                            BitConverter.ToString(ReaderConnect.TagsNear[j].EPC), //EPC data
                                            Utils.currentTime()); //Time

                                    }
                                    catch (Exception ex)
                                    {
                                        Console.WriteLine(ex.ToString());
                                        Log.Add(ex.Message);
                                    }

                                }

                        }
                    }),
                    null,
                    0,
                    50);
                }
            });
        }
        static public void Disconnect()
        {
            Config.Readers.ForEach(RC =>
            {
                if (RC.status == 1)
                {

                    RC.ScanThread.Dispose();
                    StaticClassReaderB.CloseNetPort(RC.PortHandle);
                    RC.status = 0;
                    RC.ScanThread = null;
                    
                }
            });

        }
    }

    

    static class LocalReaderConnect
    {
        static private bool fAppClosed;
        static private byte fComAdr = 0xff;
        static private int ferrorcode;
        static private byte fBaud;
        static private int fOpenComIndex; //COM PORT INDEX
        static int port = 0;
        static public Boolean ComOpen = false;
        static public int fCmdRet = 0;
        static private byte AdrTID = 0;
        static private byte LenTID = 12;
        static private byte TIDFlag = 0;
        static private int CardNum = 0;
        static private int Totallen = 0;
        static private byte[] EPC = new byte[2048];
        static private Timer timer;
        static public TAGS[] TagsNear = new TAGS[50];
        static public void Connect()
        {
            fOpenComIndex = -1;
            fComAdr = 0;
            ferrorcode = -1;
            fBaud = 5;
            fCmdRet = StaticClassReaderB.AutoOpenComPort(ref port, ref fComAdr, fBaud, ref fOpenComIndex);
            if (fCmdRet == 0)
            {
                ComOpen = true;
            }
            else
            {
                ComOpen = false;
            }
        }
        static private int EPCLEN = 12;
        static public Dictionary<String, int> counter = new Dictionary<string, int>();
        static public int CountTH = 4;
        static public void Disconnect()
        {

            if (ComOpen )
            {
                timer.Dispose();
                StaticClassReaderB.CloseSpecComPort(port);
            }
        }
        static public void RegularScan()
        {
            if (ComOpen == true)
            {
                timer = new Timer(new TimerCallback(state =>
                {
                    fCmdRet = StaticClassReaderB.Inventory_G2(ref fComAdr, AdrTID, LenTID, TIDFlag, EPC, ref Totallen, ref CardNum, fOpenComIndex);
                    if ((fCmdRet == 1) | (fCmdRet == 2) | (fCmdRet == 3) | (fCmdRet == 4) | (fCmdRet == 0xFB))//End scanning
                    {
                        for (int j = 0; j < CardNum; j++)
                        {
                            ReaderConnect.TagsNear[j].EPC = new byte[EPCLEN];
                            for (int a = 0; a < EPCLEN; a++)
                                ReaderConnect.TagsNear[j].EPC[a] = EPC[(j + 1) + j * EPCLEN + a];
                            if (counter.ContainsKey(BitConverter.ToString(LocalReaderConnect.TagsNear[j].EPC)))
                            {
                                counter[BitConverter.ToString(LocalReaderConnect.TagsNear[j].EPC)] += 1;
                            }
                            else
                            {
                                counter.Add(BitConverter.ToString(LocalReaderConnect.TagsNear[j].EPC), 1);
                            }
                        }
                        for (int j = 0; j < CardNum; j++)
                        {

                            if (counter.ContainsKey(BitConverter.ToString(LocalReaderConnect.TagsNear[j].EPC)))
                                if (counter[BitConverter.ToString(LocalReaderConnect.TagsNear[j].EPC)] >= CountTH)
                                {
                                    counter[BitConverter.ToString(LocalReaderConnect.TagsNear[j].EPC)] = 0;

                                    Console.WriteLine("TAG RECORDED:" + BitConverter.ToString(ReaderConnect.TagsNear[j].EPC));


                                    //SENDING DATA TO DATABASE
                                    try
                                    {


                                        Request.TagScanned(Request.Id,
                                            BitConverter.ToString(ReaderConnect.TagsNear[j].EPC), //EPC data
                                            Utils.currentTime()); //Time

                                    }
                                    catch (Exception ex)
                                    {
                                        Console.WriteLine(ex.ToString());
                                        Log.Add(ex.Message);
                                    }

                                }

                        }
                    }
                }),
                    null,
                    0,
                    50);
            }

        }
    }
}
