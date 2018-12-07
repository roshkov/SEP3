using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using Newtonsoft.Json;
using Tier2CustomerServerWebapi.Controllers;
using Tier2CustomerServerWebapi.view;
using Tier2CustomerServerWebapi.common;
using System.Threading.Tasks;


namespace Tier2CustomerServerWebapi.context
{
    public class methodDelegate
    {
        
        private Socket MovieSchedulerServerSocket;
        private Tier2CustomerView view;
        //private BinaryReader inputStream;
        NetworkStream networkStream;
        StreamReader streamReader;
        StreamWriter streamWriter;
        TcpClient tcpClient;
        Package answer;

      

       public methodDelegate(Tier2CustomerView view)
       {
         this.view=view;

         //connecting to components3Tier2 
          tcpClient = new TcpClient("localhost",1098);
          networkStream = tcpClient.GetStream();
         // streamReader = new StreamReader(networkStream);  
         // streamWriter = new StreamWriter(networkStream);
       }


        public Package getSchedule (int scheduledMovieID, int seat)
        {
        view.Show("geeting schedule");
        Package APIGETSCHEDULE = new Package("APIGETSCHEDULE");

        string json = JsonConvert.SerializeObject (APIGETSCHEDULE);
        view.Show(json);
        byte[] msg = Encoding.UTF8.GetBytes (json, 0, json.Length);
        
         using (StreamWriter streamWrite = new StreamWriter(networkStream))
         {  
            streamWriter.Write(msg);
         }

        answer = null;
         using (StreamReader streamRead = new StreamReader(networkStream))
         {  
             
             var streamTask = streamReader.ReadToEnd();
            answer = JsonConvert.DeserializeObject(streamTask) as Package; 
         }       
         
         return answer;
    }
}
}