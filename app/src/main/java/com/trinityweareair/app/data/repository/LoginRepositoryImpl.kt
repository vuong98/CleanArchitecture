package com.trinityweareair.app.data.repository

import android.text.TextUtils
import com.trinityweareair.app.domain.repository.LoginRepository
import com.trinityweareair.app.utils.RegexUtils
import com.trinityweareair.app.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl : LoginRepository {
    override suspend fun loginAccount(strEmail: String, strPassword: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        delay(2000)
        if (strEmail.isEmpty()) {
            emit(Resource.Error(message = "Email không được để rỗng"))
        }
        else if (strPassword.isEmpty()) {
            emit(Resource.Error(message = "Password không được để rỗng"))
        }
        else {
            if (RegexUtils.isValidEmail(strEmail) == false) {
                emit(Resource.Error(message = "Email không đúng định dạng"))
            }else {
                if (strPassword.length < 8) {
                    emit(Resource.Error(message =  "Mật khẩu không đủ độ dài"))
                }

                if (strEmail.equals("Admin1300@gmail.com") && strPassword.equals("123456789")) {
                    emit(Resource.Success(true))
                } else {
                    emit(Resource.Error(message =  "Mật khẩu không đúng"))
                }
            }
        }

    }
}