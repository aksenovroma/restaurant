var min = 0;

function onClickPlus() {
    min++;
    document.getElementById("min").innerHTML = min;
}

function onClickMinus() {
    if (min > 0) {
        min--;
        document.getElementById("min").innerHTML = min;
    }
}
