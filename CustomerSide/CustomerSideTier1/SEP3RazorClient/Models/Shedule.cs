using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SEP3RazorClient
{
    public class Shedule
    {
        public List<ScheduledMovie> list;

        public Shedule()
        {
            list = new List<ScheduledMovie>();
            Create();
        }

        public void Create()
        {
            Movie m1 = new Movie("Harry Potter", "1998", "24/12/2000", 500.0, "Warner Bros", "Daniela Koch", "About a girl who survived", "Daniela Koch");
            Movie m2 = new Movie("The life of Daniela", "1998", "24/12/2018", 55.5, "DWD Koch", "Miśka Kubicz", "Daniela's life in a nutshel.", "Daniela Koch");
            Room r1 = new Room(60, "Ente");
            Room r2 = new Room(120, "Arla");
            ScheduledMovie sm1 = new ScheduledMovie("Monday", "11:50", m1, r2);
            ScheduledMovie sm2 = new ScheduledMovie("Wednesday", "15:00", m2, r1);
            ScheduledMovie sm3 = new ScheduledMovie("Friday", "19:00", m1, r1);
            ScheduledMovie sm4 = new ScheduledMovie("Saturday", "21:00", m2, r2);
            list.Add(sm1);
            list.Add(sm2);
            list.Add(sm3);
            list.Add(sm4);
        }


    }
}
