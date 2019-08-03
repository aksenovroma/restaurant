function validateNumber(form_id,price,weight) {
    var reg = /^[0-9]*[.,]?[0-9]+$/;
    var priceNum = document.forms[form_id].elements[price].value;
    var weightNum = document.forms[form_id].elements[weight].value;
    if(reg.test(priceNum) == false || reg.test(weightNum) == false) {
        alert('Введите корректное число');
        return false;
    }
}