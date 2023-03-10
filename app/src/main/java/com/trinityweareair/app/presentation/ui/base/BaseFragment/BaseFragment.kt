package com.trinityweareair.app.presentation.ui.base.BaseFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.trinityweareair.app.R
import com.trinityweareair.app.data.data_source.basenetwork.BaseNetworkException
import com.trinityweareair.app.presentation.ui.base.BaseActivity.BaseActivity
import com.trinityweareair.app.presentation.ui.base.BaseViewModel.BaseViewModel
import com.trinityweareair.app.utils.EventObserver


abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    lateinit var binding : T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        return binding.root
    }

    abstract fun getLayoutResId(): Int

    protected fun showLoading(isShow: Boolean, progress : ProgressBar) {
        val activity = requireActivity()
        if (activity is BaseActivity<*>) {
            activity.showLoading(isShow, progress)
        }
    }

    protected fun showErrorMessage(e: BaseNetworkException) {
        showErrorMessage(e.mainMessage)
    }

    protected fun showErrorMessage(messageId: Int){
        val message = requireContext().getString(messageId)
        showErrorMessage(message)
    }

    protected fun showErrorMessage(message: String){
        val activity = requireActivity()
        if (activity is BaseActivity<*>) {
            activity.showErrorDialog(message)
        }
    }

    protected fun showNotify(title: String?, message: String) {
        val activity = requireActivity()
        if (activity is BaseActivity<*>) {
            activity.showNotifyDialog(title ?: getDefaultNotifyTitle(), message)
        }
    }

    protected fun showNotify(titleId: Int = R.string.default_notify_title, messageId: Int) {
        val activity = requireActivity()
        if (activity is BaseActivity<*>) {
            activity.showNotifyDialog(titleId, messageId)
        }
    }

    protected fun registerAllExceptionEvent( viewModel: BaseViewModel,
                                             viewLifecycleOwner: LifecycleOwner
    ){
        registerObserverExceptionEvent(viewModel,viewLifecycleOwner)
        registerObserverNetworkExceptionEvent(viewModel,viewLifecycleOwner)
        registerObserverMessageEvent(viewModel,viewLifecycleOwner)
    }

    protected fun registerObserverExceptionEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        viewModel.baseNetworkException.observe(viewLifecycleOwner, EventObserver {
            showErrorMessage(it)
        })
    }

    protected fun registerObserverNetworkExceptionEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        viewModel.networkException.observe(viewLifecycleOwner, EventObserver {
            showNotify(getDefaultNotifyTitle(), it.message ?: "Network error")
        })
    }

    protected fun registerObserverMessageEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        viewModel.errorMessageResourceId.observe(viewLifecycleOwner, EventObserver { message ->
            showErrorMessage(message)
        })
    }

    protected fun registerObserverLoadingMoreEvent(viewModel: BaseViewModel,
                                                   viewLifecycleOwner: LifecycleOwner,
                                                   progress: ProgressBar
    ){
        viewModel.isLoadingMore.observe(viewLifecycleOwner,EventObserver{
                isShow->
            showLoadingMore(isShow)
        })
    }

    protected fun registerObserverLoadingEvent(viewModel: BaseViewModel, viewLifecycleOwner: LifecycleOwner, progress: ProgressBar){
        viewModel.isLoading.observe(viewLifecycleOwner,EventObserver{
                isShow ->
            showLoading(isShow, progress)
        })
    }

    protected fun showLoadingMore(isShow: Boolean){

    }


    private fun getDefaultNotifyTitle(): String {
        return getString(R.string.default_notify_title)
    }


}