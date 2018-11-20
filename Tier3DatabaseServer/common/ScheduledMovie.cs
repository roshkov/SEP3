using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using Newtonsoft.Json;
namespace Tier3ServerDatabase.common {

    public class ScheduledMovie{
        private int id;
        private string time;
        private string day;
        private int movieId;
        private int roomId;
        private ICollection<Seat> seats;

        [Key]
        [Required (ErrorMessage="Id {0} is required")]
        public int Id { get => id; set => id = value; }
        [Required (ErrorMessage="Time {0} is required")]
        [StringLength (4,MinimumLength=2,
        ErrorMessage="Time Should be minimum 2 characters and a maximum of 40 characters")]
        [DataType(DataType.Text)]
        public string Time { get => time; set => time = value; }
        [Required (ErrorMessage="Day {0} is required")]
        [StringLength (10,MinimumLength=2,
        ErrorMessage="Time Should be minimum 2 characters and a maximum of 40 characters")]
        [DataType(DataType.Text)]
        public string Day { get => day; set => day = value; }
        [Required (ErrorMessage="movieId {0} is required")]
        public int MovieId { get => movieId; set => movieId = value; }
        [Required (ErrorMessage="roomId {0} is required")]
        public int RoomId { get => roomId; set => roomId = value; }

        [Required (ErrorMessage="seats {0} is required")]
        public ICollection<Seat> Seats { get => seats; set => seats = value; }


        [JsonConstructor]
        public ScheduledMovie(int id, string day, string time, int movieId, int roomId, ICollection<Seat> seats)
        {
            Id = id;
            Time = time;
            Day = day;
            MovieId = movieId;
            RoomId = roomId;
            Seats = seats;
        }
        //Default working constructor. Issues with the ICollection
        public ScheduledMovie(int id)
        {
            Id = id;
        }

         public override String ToString() {
	    return "Movie Id=" + Id  + ", Day=" + Day + ", Time=" + Time + ", MovieId=" + MovieId + ", RoomId="
			+ RoomId + NrOfBookedSeats() + " \n ";
    }
        //Method to check how many seats are booked
        public String NrOfBookedSeats()
        {
            return " Number of Booked Seats=" + Seats.Count( b => b.Booked);
        }


    }
    //We keep the Seat class here because it's closely tied to the Scheduled Movie
     public class Seat{
            private int id;
            private bool booked;
            [Key]
            [Required (ErrorMessage="Id {0} is required")]

            public int Id { get => id; set => id = value; }
            [Required (ErrorMessage="Booked {0} is required")]
            public bool Booked { get => booked; set => booked = value; }

            public Seat(int id, bool booked)
            {
                Id = id;
                Booked = booked;
            }
        }
}