﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace SEP3RazorClient.Pages
{
    public class AboutModel : PageModel
    {
        public string Message { get; set; }

        public void OnGet()
        {
            Message = "Your application description page.";
        }

        public ActionResult OnPost()
        {
            var choice = Request.Form["Movie"];
            System.Console.WriteLine(choice);
            return Redirect("/BookSeats/" + choice);
        }
    }
}
