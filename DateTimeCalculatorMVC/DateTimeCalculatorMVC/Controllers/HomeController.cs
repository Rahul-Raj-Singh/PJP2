using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using DateTimeCalculatorMVC.Models;
using System.Net.Http;
using System.Net.Http.Json;

namespace DateTimeCalculatorMVC.Controllers {
    public class HomeController : Controller {

        private readonly ILogger<HomeController> _logger;
        private readonly IHttpClientFactory _clientFactory;
        public HomeController(IHttpClientFactory clientFactory, ILogger<HomeController> logger) {
            _logger = logger;
            _clientFactory = clientFactory;
        }

        public async Task<IActionResult> Index() {

            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Index(FieldModel model, String submit) {

            // Validation Fail
            if (!ModelState.IsValid)
                return View();

            string endpointUri = "";
            switch (submit) {

                case "Week Day":
                    endpointUri = $"date/{model.FirstDate.Trim()}/weekday";
                    break;
                case "Week No.":
                    endpointUri = $"date/{model.FirstDate.Trim()}/weeknumber";
                    break;
                case "Add":
                    endpointUri = $"date/{model.FirstDate.Trim()}/add/{model.SecondAddDate.Trim()}";
                    break;
                case "Subtract":
                    endpointUri = $"date/{model.FirstDate.Trim()}/subtract/{model.SecondSubDate.Trim()}";
                    break;
                default:
                    return BadRequest("Invalid Option !!!");
            }


            // Consume REST services from CalculatorAPI
            var client = _clientFactory.CreateClient("HttpClientWithSSLUntrusted");
            BaseServiceModel baseServiceResult;

            try {
                baseServiceResult = await client.GetFromJsonAsync<BaseServiceModel>($"https://127.0.0.1:5001/api/Calculator/{endpointUri}");
                model.Result = baseServiceResult.result;
            }
            catch (Exception ex) {
                _logger.LogError(ex.Message);
                return NotFound(ex.Message);
            }

            return View(model);
        }

        

        public IActionResult Privacy() {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error() {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
