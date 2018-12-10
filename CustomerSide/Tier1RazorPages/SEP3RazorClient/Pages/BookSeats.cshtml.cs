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

        [BindProperty]
        public int SeatChoice { get; set; }
        public List<ScheduledMovie> Schedule { get => schedule; set => schedule = value; }

        [HttpGet]
        public void OnGet()
        {

            // get from api
            schedule = APIProvider.GetScheduleAsync("https://localhost:5003/api/schedule").Result;
        }

        public ActionResult OnPost()
        {
            APIProvider.UpdateProductAsync(Choice, (Int32.Parse(Request.Form["SeatChoice"]) - 1));
            return Redirect("/BookTicket");
        }
    }
}