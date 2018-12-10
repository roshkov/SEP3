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
            Package Answer;
            Package GETALLSCHEDULE = new Package("GETALLSCHEDULE");

            //byte[] adr = {127, 0, 0, 1};
            TcpClient client = new TcpClient("127.0.0.1",1100);

            //Connect to end point
            //client.Connect(new IPEndPoint(new IPAddress(adr),1100));
            
            NetworkStream NetworkStream = client.GetStream();
            string json = JsonConvert.SerializeObject(GETALLSCHEDULE);
            
            // StreamWriter StreamWriter = new StreamWriter(NetworkStream);
            // StreamWriter.Write(json);
            // StreamWriter.Close();

            byte[] bytesToSend = Encoding.UTF8.GetBytes(json, 0, json.Length);
            NetworkStream.Write(bytesToSend);
            NetworkStream.Write(Encoding.UTF8.GetBytes(Environment.NewLine));
            NetworkStream.Flush();

            Byte[] bytes = new byte[client.ReceiveBufferSize];
            NetworkStream.Read(bytes, 0, client.ReceiveBufferSize);
            String reply = Encoding.UTF8.GetString(bytes, 2, client.ReceiveBufferSize-2);

            // using (StreamReader StreamReader = new StreamReader(NetworkStream))
            // {
            //     var streamTask = StreamReader.ReadToEnd();
            //     Console.WriteLine(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + streamTask);
            //     Answer = JsonConvert.DeserializeObject(streamTask) as Package; 
            // StreamReader.Dispose();
            // }
        
        Answer = JsonConvert.DeserializeObject<Package>(reply); 
        Schedule = Answer.ScheduleList;
        
        client.GetStream().Close();
        client.Close();
        }
        // GET api/schedule
        [HttpGet]
        public List<ScheduledMovie> Get()
        {
            return Schedule;
        }

        // PUT api/schedule

        [HttpPut("{id}")]
        public void Put([FromQuery]int id, [FromBody]int id2)
        {
            System.Console.WriteLine(id);
            System.Console.WriteLine(id2);
            Schedule[id].Seats.ElementAt(id2).Booked = true;
            System.Console.WriteLine(Schedule[id].Seats.ElementAt(id2).Booked);
            Package UPDATECHEDULE = new Package("UPDATESCHEDULE", null, null, null, Schedule);

            TcpClient client = new TcpClient("127.0.0.1",1100);
             NetworkStream NetworkStream = client.GetStream();
            string json = JsonConvert.SerializeObject(UPDATECHEDULE);
             byte[] bytesToSend = Encoding.UTF8.GetBytes(json, 0, json.Length);
            NetworkStream.Write(bytesToSend);
            NetworkStream.Write(Encoding.UTF8.GetBytes(Environment.NewLine));
            NetworkStream.Flush();
            // Reading so it doesn't throw error and block the whole circuit
            Byte[] bytes = new byte[client.ReceiveBufferSize];
            NetworkStream.Read(bytes, 0, client.ReceiveBufferSize);
            String reply = Encoding.UTF8.GetString(bytes, 2, client.ReceiveBufferSize-2);
            client.GetStream().Close();
            client.Close();
        }
    }
}
