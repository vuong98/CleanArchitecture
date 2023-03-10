package com.trinityweareair.app.presentation.ui.activities.photo

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trinityweareair.app.R
import com.trinityweareair.app.databinding.ActivityListPhotoBinding
import com.trinityweareair.app.domain.model.Photo
import com.trinityweareair.app.domain.model.PhotoItem
import com.trinityweareair.app.presentation.ui.activities.login.adapter.DemoAdapter
import com.trinityweareair.app.presentation.ui.base.BaseActivity.BaseActivity
import com.trinityweareair.app.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListPhotoActivity : BaseActivity<ActivityListPhotoBinding>(), DemoAdapter.IDemo{

    val photoViewModel : PhotoViewModel by viewModels()
    lateinit var photoAdapter: DemoAdapter
    override fun getLayoutResourceId(): Int {
        return R.layout.activity_list_photo
    }
    var listPhotos = mutableListOf<PhotoItem>()
    override fun initControls(savedInstanceState: Bundle?) {
//        TODO("Not yet implemented")
        photoViewModel.getListPhoto("2172797")

        registerAllExceptionEvent(photoViewModel, this)
        registerObserverNetworkExceptionEvent(photoViewModel, this)
        registerObserverLoadingEvent(photoViewModel, this, mBinding.progressbar)

        observe(photoViewModel._livedataPhoto, ::handlePhoto)


    }

    override fun getCount(): Int {
        return listPhotos.size
    }

    override fun getData(position: Int): PhotoItem {
        return listPhotos[position]
    }

    override fun getContext(): Context {
        return applicationContext
    }

    fun handlePhoto(photo: Photo){
        listPhotos = photo
        photoAdapter = DemoAdapter(this)
        mBinding.rcvPhoto.apply {
            adapter = photoAdapter
            layoutManager = LinearLayoutManager(applicationContext,RecyclerView.VERTICAL, false)
            hasFixedSize()
        }
    }
}