using System;
using Tier2CustomerServerWebapi.view;
//using Tier3ServerDatabase.database;
using System.Threading;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Options;
using Microsoft.EntityFrameworkCore;
using Tier2CustomerServerWebapi.context;

namespace Tier2CustomerServerWebapi.Controllers {

    public class Tier2CustomerController {
        private Tier2CustomerView view;
        private Tier2CustomerServer server;

        private methodDelegate mDelegate;

        public Tier2CustomerView View { get => view; set => view = value; }
      //  public RepositoryDatabaseAdapter Database { get => database; set => database = value; }
        public Tier2CustomerController(Tier2CustomerView view, string adress, int port)
        {
            this.View = view;

            mDelegate = new methodDelegate(View);
            
            try{
                this.server = new Tier2CustomerServer(this,mDelegate);
            } catch(Exception e)
            {
                //Replace with e.Message when development is done
                view.Show(e.StackTrace);
            }

            Thread t = new Thread(() => this.server.StartListening(adress, port));
            t.Start();
        }
    }
}