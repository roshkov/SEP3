using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;

namespace SEP3RazorClient.Communication
{
    public static class APIProvider
    {

        public static async Task<List<ScheduledMovie>> GetScheduleAsync(string path)
        {
            using (var httpClientHandler = new HttpClientHandler())
            {
                httpClientHandler.ServerCertificateCustomValidationCallback = (message, cert, chain, errors) => { return true; };
                using (var client = new HttpClient(httpClientHandler))
                {
                    // Update port # in the following line.
                    client.BaseAddress = new Uri("http://localhost:5001/api/");
                    client.DefaultRequestHeaders.Accept.Clear();
                    client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                    List<ScheduledMovie> list = null;
                    HttpResponseMessage response = await client.GetAsync(path);
                    if (response.IsSuccessStatusCode)
                    {
                        list = await response.Content.ReadAsAsync<List<ScheduledMovie>>();
                    }
                    return list;
                }
            }
        }

        public static async void UpdateProductAsync(int id, int id2)
        {
            using (var httpClientHandler = new HttpClientHandler())
            {
                httpClientHandler.ServerCertificateCustomValidationCallback = (message, cert, chain, errors) => { return true; };
                using (var client = new HttpClient(httpClientHandler))
                {
                    client.BaseAddress = new Uri("http://localhost:5001/api/");
                    client.DefaultRequestHeaders.Accept.Clear();
                    client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                    HttpResponseMessage response = await client.PutAsJsonAsync($"api/schedule/{id}", id2);
                    response.EnsureSuccessStatusCode();
                }
            }
        }



    }
}