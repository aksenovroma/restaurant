function validate(form_id,login) {
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    var address = document.forms[form_id].elements[login].value;
    if(reg.test(address) === false) {
        alert('Введите корректный e-mail');
        return false;
    } else {
        return true;
    }
}