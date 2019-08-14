function validatePassword(form_id,password) {
    var pas = document.forms[form_id].elements[password].value;
    if(pas.toString().length < 3) {
        alert('Пароль должен содержать не менее 3 символов');
        return false;
    }
}