let colors = [
    '#3498db',
    '#9b59b6',
    '#e74c3c',
    '#2c3e50',
    '#d35400',
]

let points = 0;
let count = document.getElementById('score');
let pointsElement = count.querySelector('.points');

for (let i = 0; i < colors.length; i++) {
    let box = document.createElement('div');
    box.className = 'box';
        
    let fiveColor = colors[i];
    box.style.backgroundColor = fiveColor;

    box.addEventListener('click', function(){
        box.parentNode.removeChild(box);
        points--;
        pointsUpdates();
    });

    document.querySelector(".boxes").appendChild(box);
    points++;

    document.getElementById('btn').addEventListener('click', function(){
        let box = document.createElement('div');
        box.className = 'box';
        
        let fiveColor = colors[i];
        box.style.backgroundColor = fiveColor;
        document.querySelector(".boxes").appendChild(box);
        points++;
        pointsUpdates();
    });
}
pointsUpdates();

function pointsUpdates(){
    pointsElement.innerHTML = points;
}
