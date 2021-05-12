package br.iesb.mobile.netflics.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.iesb.mobile.netflics.domain.LoginResult
import br.iesb.mobile.netflics.interactor.LoginInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    app: Application,
    private val interactor: LoginInteractor
) : AndroidViewModel(app) {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordCheck = MutableLiveData<String>()
    val result = MutableLiveData<LoginResult<Nothing>>()
//    val result = MutableLiveData<String>()

    fun login() {
        viewModelScope.launch {
            result.value = interactor.login(email.value, password.value)
//            try {
//                result.value = interactor.login(email.value, password.value)
//            } catch (t: Throwable){
//                Log.d("NETFLICS", "Login Error:  ${t.localizedMessage}")
//                result.value = t.localizedMessage
//            }

        }
    }

    fun signup() {
        viewModelScope.launch {
            result.value = interactor.signup(email.value, password.value)
        }
    }

    fun forgot() {
        viewModelScope.launch {
            result.value = interactor.forgot(email.value)
        }
    }

}