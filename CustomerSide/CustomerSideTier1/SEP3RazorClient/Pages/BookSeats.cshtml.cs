using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Newtonsoft.Json;
using SEP3RazorClient.Models;

namespace SEP3RazorClient.Pages
{
    public class SeatsModel : PageModel
    {
        private Shedule shedule;

        public ScheduledMovie sm { get; set; }
        [BindProperty(SupportsGet = true)]
        public int Choice { get; set; }
        public Shedule Shedule { get => shedule; set => shedule = value; }
        [BindProperty]
        public int SeatChoice { get; set; }

        [HttpGet]
        public void OnGet(int choice)
        {
           
            // get from api
            Shedule = new Shedule();
           
        }

        public ActionResult OnPost()
        {  
            System.Console.WriteLine((Int32.Parse(Request.Form["SeatChoice"]) - 1));
            return Redirect("/BookSeats/" + Choice);
        }
    }
}