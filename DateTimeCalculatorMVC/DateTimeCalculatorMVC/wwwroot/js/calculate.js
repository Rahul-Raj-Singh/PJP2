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

