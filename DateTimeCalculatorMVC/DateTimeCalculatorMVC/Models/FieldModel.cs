using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace DateTimeCalculatorMVC.Models {
    public class FieldModel {
        [Display(Name ="Date")]
        [Required]
        public string FirstDate { get; set; }
        public string SecondAddDate { get; set; }
        public string SecondSubDate { get; set; }
        public string Result { get; set; }
    }
}
