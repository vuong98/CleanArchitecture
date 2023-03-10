package com.trinityweareair.app.presentation.ui.activities.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trinityweareair.app.domain.usecase.LoginUsecase
import com.trinityweareair.app.presentation.ui.base.BaseViewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val usecase: LoginUsecase):BaseViewModel() {

}