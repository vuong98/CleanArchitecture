package com.trinityweareair.app.presentation.ui.activities.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.trinityweareair.app.R
import com.trinityweareair.app.databinding.ActivityLoginBinding
import com.trinityweareair.app.presentation.ui.base.BaseActivity.BaseActivity
import com.trinityweareair.app.utils.observe
import com.trinityweareair.app.utils.toGone
import com.trinityweareair.app.utils.toVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    val loginViewMode : LoginViewModel by viewModels()
    override fun getLayoutResourceId(): Int {
        return R.layout.activity_login
    }


    override fun initControls(savedInstanceState: Bundle?) {

    }

}