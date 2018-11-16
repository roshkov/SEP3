using System;
using System.Net.Sockets;
using System.Text;
using Newtonsoft.Json;
using Tier3ServerDatabase.common;
using Tier3ServerDatabase.database;
using Tier3ServerDatabase.view;
namespace Tier3ServerDatabase.controller {

    public class Tier3MovieCreatorThreadHandler {
        private Socket handler;
        private RepositoryDatabaseAdapter database;
        private Tier3MovieCreatorView view;
        private string data;
        //Receive all the data needed
        public Tier3MovieCreatorThreadHandler (Socket handler, Tier3MovieCreatorView view, RepositoryDatabaseAdapter database) {
            this.database = database;
            this.view = view;
            this.handler = handler;
            view.Show ("Client connected ");
        }

        public void HandleCommunication () {
            try {
                //The size of the buffer to read from the Client
                byte[] bytes = new Byte[4096];
                int bytesRec = handler.Receive (bytes);
                //Receive the Message from the Client
                //The first 2 bytes are skipped because Java uses a special format of UTF 8
                // Where is sends the length first
                data += Encoding.UTF8.GetString (bytes, 2, bytesRec);
                System.Console.WriteLine (data);
                //Covert the Request to Package
                Package request = JsonConvert.DeserializeObject<Package> (data);
                // Print out the received message to the console.
                view.Show ("Package: " + request.Header);

                //Get the proper reply
                Package reply = Operation (request);
                
                view.Show ("Reply: " + reply.Body);
                //Convert the package to json
                string json = JsonConvert.SerializeObject (reply);

                view.Show (json);
                // Convert it to bytes
                byte[] msg = Encoding.UTF8.GetBytes(json, 0, json.Length);

                //Sending the data
                handler.Send (msg);
                //Shutting down the communication so it knows the sending is done
                handler.Shutdown (SocketShutdown.Both);
                handler.Close ();

            } catch (Exception e) {
                //Change with e.Message
                string message = e.StackTrace;
                view.Show ("Error" + " - Message: " + message);
            }
        }

        public Package Operation (Package request) {
            Package wrong = new Package ("WRONG FORMAT");
            try {
                switch (request.Header) {
                    // get a list of current movies
                    case "GET":
                        return new Package ("GET", database.getStringMovies ());

                    //First we add the movie then we get a list of the current movies back
                    case "ADD":
                        if (database.addMovie (request.Movie)) {
                            return new Package ("ADD", database.getStringMovies ());
                        }
                        break;
                    default:
                        return wrong;
                }
            } catch (Exception e) {
                //Change with message
                view.Show (e.StackTrace);
            }
            return wrong;
        }
    }
}