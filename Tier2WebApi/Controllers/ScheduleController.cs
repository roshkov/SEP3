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
            
            using (StreamWriter StreamWriter = new StreamWriter(NetworkStream))
            {  
                StreamWriter.Write(json);
            }

            using (StreamReader StreamReader = new StreamReader(NetworkStream))
            {  
                var streamTask = StreamReader.ReadToEnd();
                Answer = JsonConvert.DeserializeObject(streamTask) as Package; 
            } 
            
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
        // POST api/values
        [Route("api/schedule/{id1}/{id2}")]
        public void Post(int id1, int id2)
        {
            Schedule[id1].Seats.ElementAt(id2).Booked = true;
        }
    }
}
