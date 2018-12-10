using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Newtonsoft.Json;
using SEP3RazorClient.Communication;
namespace SEP3RazorClient.Pages
{
    public class SeatsModel : PageModel
    {
        private List<ScheduledMovie> schedule;

        [BindProperty(SupportsGet = true)]
        public int Choice { get; set; }
        public List<ScheduledMovie> Schedule { get => schedule; set => schedule = value; }
        public APIProvider Provider { get => provider; set => provider = value; }

        APIProvider provider;

        public SeatsModel(APIProvider provider)
        {
            Provider = provider;
        }

        // get the schedule from the api using the APIProvider
        [HttpGet]
        public void OnGet()
        {
            schedule =  Provider.GetScheduleAsync("https://localhost:5003/api/schedule").Result;
        }
        // book a seat inside the movie
        public ActionResult OnPost()
        {
            Provider.UpdateProductAsync(Choice, (Int32.Parse(Request.Form["SeatChoice"]) - 1));
            return Redirect("/BookSeats/" + Choice);
        }
    }
}