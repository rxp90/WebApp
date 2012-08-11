jQuery(function(){
    $("#submit").click(function(){
        $(".error").hide();
        var hasError = false;
        var passwordVal = $("#newPasswordInput").val();
        var checkVal = $("#verifyPasswordInput").val();
        if (passwordVal == '') {
            $("#newPasswordInput").after('<span class="control-group error">Please enter a password.</span>');
            hasError = true;
        } else if (checkVal == '') {
            $("#verifyPasswordInput").after('<span class="control-group error">Please re-enter your password.</span>');
            hasError = true;
        } else if (passwordVal != checkVal ) {
            $("#verifyPasswordInput").after('<span class="control-group error">Passwords do not match.</span>');
            hasError = true;
        }
        if(hasError == true) {
            return false;
        }
    });
});