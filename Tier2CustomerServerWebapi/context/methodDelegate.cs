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


<<<<<<< HEAD
        public Package getSchedule ()                       //(int scheduledMovieID, int seat)
        {
        view.Show("getting schedule");
        Package APIGETSCHEDULE = new Package("GETSCHEDULE");                //looks for it in C3Tier2
=======
        public Package getSchedule (int scheduledMovieID, int seat)
        {
        view.Show("geeting schedule");
        Package APIGETSCHEDULE = new Package("APIGETSCHEDULE");
>>>>>>> d82fb1e69b0a59c114f442c72bca7fabfe1100d5

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
<<<<<<< HEAD


        public Package bookSeat ()                       //(int scheduledMovieID, int seat)
        
        {
        view.Show("booking seat");
        Package schedule = getSchedule();       //gets schedule
        int seatToBook = 5;                     //hardcoded, but should come as a parameter from Tier2CustomerThreadHandler somehow from tier1
       
        // '1' is hardcoded value - id of scheduled movie;
        //could be some method that goes through the list of scheduled movies and check for id for each movie
        ScheduledMovie updatedScheduledMovie =  schedule.ScheduleList[1]; 
        
       //marking specific seat as booked
        foreach(Seat s in updatedScheduledMovie.Seats){
            if (s.Id==seatToBook)
                s.Booked=true;
        }
       schedule.ScheduleList[1] = updatedScheduledMovie;
      
      
       //sending schedule to C3Tier2
         Package SENDSCHEDULE = new Package("SENDSCHEDULE"); 
        SENDSCHEDULE.ScheduleList = schedule.ScheduleList;
        
        string json = JsonConvert.SerializeObject (SENDSCHEDULE);
        byte[] msg = Encoding.UTF8.GetBytes (json, 0, json.Length);

         using (StreamWriter streamWrite = new StreamWriter(networkStream))
         {  
            streamWriter.Write(msg);
         }
       //////////////////////////////////////////////////


       // 
       return schedule; //something else

    }

=======
>>>>>>> d82fb1e69b0a59c114f442c72bca7fabfe1100d5
}
}