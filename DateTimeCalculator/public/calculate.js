// Add-ons
const addDate = document.querySelector('.addDate');
const subDate = document.querySelector('.subDate');

const result = document.getElementById('result');

//TextFields
const firstDateInput = document.querySelector('.option input');

// Buttons
const weekDayBtn = document.getElementById('week_day');
const weekNoBtn = document.getElementById('week_no');
const addBtn = document.getElementById('add');
const subBtn = document.getElementById('sub');

// Manage add-ons
addBtn.addEventListener('click', (e)=>{
    e.preventDefault();
    subDate.style.display = 'none';
    addDate.style.display = 'block';
});

subBtn.addEventListener('click', (e)=>{
    e.preventDefault();
    addDate.style.display = 'none';
    subDate.style.display = 'block';
});

// close button
document.querySelectorAll('.close').forEach(element => {
    element.addEventListener('click', e =>{
    e.target.parentElement.style.display = 'none';
})
});



/* Logic */

const weekDays = ['Sunday', 'Monday', 'Tuesday', 'WednesDay', 'Thursday', 'Friday', 'Saturday'];
const monthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
const isLeap = y => ((y % 4 === 0) && (y % 100 !== 0)) || (y % 400 === 0);

weekDayBtn.addEventListener('click', (e)=>{
    e.preventDefault();
    
    let date1 = firstDateInput.value.trim();
    let [date, month, year] = date1.split('/');
    let ans = 'Invalid Date';
    
    if(date1 !== ''){

        date1 = new Date(`${year}/${month}/${date}`);

        ans = weekDays[date1.getDay()];
    }
    
    result.textContent = ans;
    
});

weekNoBtn.addEventListener('click', (e)=>{
    e.preventDefault();
    
    let date1 = firstDateInput.value.trim();
    let [date, month, year] = date1.split('/');
    
    let ans = 0;
    if(date1 === '')
       ans = 'Invalid Date';
    else if(date <= 7)
        ans = 1;
    else if(date <= 14)
        ans = 2;
    else if(date <= 21)
        ans = 3;
    else if(date <= 28)
        ans = 4;
    else
        ans = 5;
    
    result.textContent = ans;
    
});


// ADD date
addDate.children[2].addEventListener('click', e=>{
    e.preventDefault();
    
    let date1 = firstDateInput.value.trim();
    let [date, month, year] = date1.split('/');
    let dateString = `${year}/${month}/${date}`;
    
    const input = addDate.children[1].value.trim();
    
    console.log(input);
    
    let [num, dmw] = input.split(/\s+/);
    
    dmw = dmw.toLowerCase();
    let ans = 'Invalid Input !!!';
    num = Number(num);
    
    if(dmw.startsWith('day')){
        
        ans = new Date(new Date(dateString).getTime() + (num*24*60*60*1000)).toDateString();
        
    }else if(dmw.startsWith('week')){
        
        ans = new Date(new Date(dateString).getTime() + (num*7*24*60*60*1000)).toDateString();
        
    }else if(dmw.startsWith('month')){     
        
        month = Number(month);
        date = Number(date);
        year = Number(year);

        let new_year = year + Math.trunc(num/12);
        month = month % 12;

        let new_month = (month + num) % 12;
        let new_date = (date > monthDays[new_month - 1]) ? monthDays[new_month - 1] : date;
        
        
        ans = new Date(`${new_year}/${new_month}/${new_date}`).toDateString();
    }
    
    
    result.textContent = ans;
    
});



// Subtract Date
subDate.children[2].addEventListener('click', e=>{
    e.preventDefault();
    
    let date1 = firstDateInput.value.trim();
    let [date, month, year] = date1.split('/');
    let dateString = `${year}/${month}/${date}`;
    
    const input = subDate.children[1].value.trim();
    
    console.log(input);
    
    // Since input can also be a date
    let isDate = /^\d{2}\/\d{2}\/\d{4}$/.test(input);
    
    let num = '', dmw = '';
    
    if(!isDate){
        [num, dmw] = input.split(/\s+/);
        dmw = dmw.toLowerCase();
        num = Number(num);
    }

    
    let ans = 'Invalid Input !!!';
    
    if(dmw.startsWith('day')){
        
        ans = new Date(new Date(dateString).getTime() - (num*24*60*60*1000)).toDateString();
        
    }
    else if(dmw.startsWith('week')){
        
        ans = new Date(new Date(dateString).getTime() - (num*7*24*60*60*1000)).toDateString();
        
    }
    else if(dmw.startsWith('month')){     
        
        month = Number(month);
        date = Number(date);
        year = Number(year);

        let new_year = year - Math.trunc(num/12);
        month = month % 12;

        let new_month = (month - num) <= 0 ? (month - num) + 12 : (month - num);
        let new_date = (date > monthDays[new_month - 1]) ? monthDays[new_month - 1] : date;
        
        
        ans = new Date(`${new_year}/${new_month}/${new_date}`).toDateString();
        
    }
    else if(isDate){
        
        let [date2, month2, year2] = input.split('/');
        let dateString2 = `${year2}/${month2}/${date2}`;

        let milliSeconds = Math.abs(new Date(dateString2).getTime() - new Date(dateString).getTime());
        
        ans = `${milliSeconds/1000/60/60} hours`;
    }
    
    
    result.textContent = ans;
    
});

