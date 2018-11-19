using System;
using Newtonsoft.Json;

namespace Tier3ServerDatabase.common {
    public class Package {

        private string header;
        private string body;
        private Movie movie;
        private int id;

        public string Header { get => header; set => header = value; }
        public string Body { get => body; set => body = value; }
        public Movie Movie { get => movie; set => movie = value; }
        public int Id { get => id; set => id = value; }

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
        public Package (string header, string body, Movie movie, int id) {
            Header = header;
            Body = body;
            Movie = movie;
            Id = id;
        }
    }
}