package com.example.twentyone.restapi.callback;

public interface AccountAPICallBack extends RestAPICallBack {
    // Este método se llama cuando se ha producido el cambio de contraseña a través de I forgot my psswd
    void onResetPasswordFinish();

    // Este método se llama cuando el usuario no recuerda la contraseña y se le ha enviado el correo
    void onResetPasswordInit();

    // Este método se llama cuando se cambia la contraseña desde el perfil del usuario
    void onChangePassword();

    // Este método se llama cuando se intenta registrar un usuario con un username que ya existe
    void onUsernameFailed();

    // Este método se llama cuando se intenta registrar un usuario con un email que ya existe
    void onEmailFailed();

    // Este método se llama cuando al cambiar la contraseña da error porque la actual no coincide con la introducida
    void onPasswordDontMatch();

    // Este método se llama cuando al pedir I forgot my password se introduce un correo que no existe en la base de datos
    void onEmailNotFound();

    // Este método se llama cuando la secret key para restablecer la contraseña del usuario no coincide con la del correo que se le ha enviado
    void onBadResetKey();

    /* Estos métodos son heredados de la interface RestAPICallBack */
    void onFailure();
    void onSuccess();
}
