using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using SEP3RazorClient.Communication;
namespace SEP3RazorClient.Pages
{
    public class BookTicketModel : PageModel
    {
        public string Message { get; set; }
        public List<ScheduledMovie> Schedule { get => schedule; set => schedule = value; }

        List<ScheduledMovie> schedule;
        public void OnGet()
        {
            Schedule = APIProvider.GetScheduleAsync("https://localhost:5001/api/schedule").Result;
        }

        public ActionResult OnPost()
        {
            var choice = Request.Form["Movie"];
            return Redirect("/BookSeats/" + choice);
        }
    }
}
