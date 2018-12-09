using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using Tier2WebApi.Common;

namespace Tier2WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ScheduleController : ControllerBase
    {
        private List<ScheduledMovie> Schedule;

        public ScheduleController()
        {
            //byte[] adr = {127, 0, 0, 1};
            //TcpClient client = new TcpClient("127.0.0.1",1100);
            //Connect to end point
            //client.Connect(new IPEndPoint(new IPAddress(adr),1100));
            
            // NetworkStream NetworkStream = client.GetStream();
            // string json = JsonConvert.SerializeObject(GETALLSCHEDULE);
            // string wabla = "Cacat";
            // string json = JsonConvert.SerializeObject(wabla);
            // byte[] msg = Encoding.UTF8.GetBytes(json, 0, json.Length);
            // using (StreamWriter StreamWriter = new StreamWriter(NetworkStream))
            // {  
            //     StreamWriter.Write(msg);
            // }

            // using (StreamReader StreamReader = new StreamReader(NetworkStream))
            // {  
            //     var streamTask = StreamReader.ReadToEnd();
            //     Answer = JsonConvert.DeserializeObject<Package>(streamTask); 
            // } 
            
            Package Answer;
            Package GETALLSCHEDULE = new Package("GETALLSCHEDULE");

            

            IPHostEntry hostEntry = Dns.GetHostEntry("127.0.0.1");
            IPEndPoint ipe = new IPEndPoint(hostEntry.AddressList[1], 1100);
            Socket client = new Socket(ipe.AddressFamily, SocketType.Stream, ProtocolType.Tcp);

            client.Connect(ipe);

            string json = JsonConvert.SerializeObject(GETALLSCHEDULE);
            byte[] msg = Encoding.UTF8.GetBytes(json, 0, json.Length);
            client.Send(msg);

            byte[] bytes = new Byte[8192];
            int bytesRec = client.Receive(bytes);

            String data = Encoding.UTF8.GetString(bytes, 2, bytesRec);
            System.Console.WriteLine(data);

            Answer = JsonConvert.DeserializeObject<Package>(data);
            Schedule = Answer.ScheduleList;
        
        // client.GetStream().Close();
        // client.Close();
        }
        // GET api/schedule
        [HttpGet]
        public List<ScheduledMovie> Get()
        {
            return Schedule;
        }
        // POST api/values
        [Route("api/schedule/{id1}/{id2}")]
        public void Post(int id1, int id2)
        {
            Schedule[id1].Seats.ElementAt(id2).Booked = true;
        }
    }
}
