using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CalculatorAPI.Business;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace CalculatorAPI.Controllers {
    [Route("api/[controller]")]
    [ApiController]
    [Produces("application/json")]
    public class CalculatorController : ControllerBase {

        private readonly NLP _nlp;
        private readonly Calculator _calculator;

        public CalculatorController(NLP nlp, Calculator calculator) {
            _nlp = nlp;
            _calculator = calculator;
        }

        [HttpGet("nlp/{input}")]
        public IActionResult GetNLP(string input) {

            string ans = _nlp.Translate(input);

            return Ok(new {result = ans});

        }


        // input = dd-MM-yyyy (Careful with dd/MM/YYYY type formatting as it disrupts Routing)
        [HttpGet("date/{input}/weekday")]
        public IActionResult GetWeekDay(string input) {

            _calculator.Date = DateTime.Parse(input);
            string ans = _calculator.DetermineDayOfWeek();

            return Ok(new { result = ans });

        }

        // input = dd-MM-yyyy (Careful with dd/MM/YYYY type formatting as it disrupts Routing)
        [HttpGet("date/{input}/weeknumber")]
        public IActionResult GetWeekNumber(string input) {

            _calculator.Date = DateTime.Parse(input);
            string ans = _calculator.DetermineWeekNumber();

            return Ok(new { result = ans });

        }

        // input = dd-MM-yyyy (Careful with dd/MM/YYYY type formatting as it disrupts Routing)
        [HttpGet("date/{input}/add/{query}")]
        public IActionResult GetAddedResult(string input, string query) {

            _calculator.Date = DateTime.Parse(input);
            string ans = _calculator.Add(query);

            return Ok(new { result = ans });

        }

        // input = dd-MM-yyyy (Careful with dd/MM/YYYY type formatting as it disrupts Routing)
        [HttpGet("date/{input}/subtract/{query}")]
        public IActionResult GetSubtractedResult(string input, string query) {

            _calculator.Date = DateTime.Parse(input);
            string ans = _calculator.Subtract(query);

            return Ok(new { result = ans });

        }
    }
}
