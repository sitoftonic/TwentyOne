package com.example.twentyone.restapi.callback;

import com.example.twentyone.model.data.User;

public interface AccountAPICallBack extends RestAPICallBack {
    // Este método se llama cuando se ha producido el cambio de contraseña a través de I forgot my psswd
    void onResetPasswordFinish();
    // Este método se llama cuando el usuario no recuerda la contraseña y se le ha enviado el correo
    void onResetPasswordInit();
    // Este método se llama cuando se cambia la contraseña desde el perfil del usuario
    void onChangePassword();
    void onUsernameFailed();
    void onEmailFailed();
    void onFailure();
    void onSuccess();
}
