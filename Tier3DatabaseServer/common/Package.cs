using System;
using System.Collections.Generic;
using Newtonsoft.Json;

namespace Tier3ServerDatabase.common {
    public class Package {

        private string header;
        private string body;
        private Movie movie;
        private int id;

        private List<ScheduledMovie> schedule;
        private Room room;

        public string Header { get => header; set => header = value; }
        public string Body { get => body; set => body = value; }
        public Movie Movie { get => movie; set => movie = value; }
        public int Id { get => id; set => id = value; }
        public List<ScheduledMovie> Schedule { get => schedule; set => schedule = value; }
        public Room Room { get => room; set => room = value; }

        public Package (string header) {
            Header = header;
        }

        public Package (string header, string body) {
            Header = header;
            Body = body;
        }

        public Package (string header, Movie movie) {
            Header = header;
            Movie = movie;
        }
        //The constructor the json needs to default to
        //Doesn't deserialize without this
        [JsonConstructor]
        public Package (string header, string body, Movie movie, int id, Room room ,List<ScheduledMovie> schedule) {
            Header = header;
            Body = body;
            Movie = movie;
            Id = id;
            Schedule = schedule;
            Room = room;
        }
    }
}