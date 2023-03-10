package com.trinityweareair.app.presentation.di

import com.trinityweareair.app.data.repository.LoginRepositoryImpl
import com.trinityweareair.app.data.repository.PhotoRepositoryImpl
import com.trinityweareair.app.data.services.PhotoService
import com.trinityweareair.app.domain.repository.LoginRepository
import com.trinityweareair.app.domain.repository.PhotoRepository
import com.trinityweareair.app.domain.usecase.AllUseCase
import com.trinityweareair.app.domain.usecase.LoginUsecase
import com.trinityweareair.app.domain.usecase.PhotoUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoginRepository() : LoginRepository {
        return LoginRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providePhotoRepository(photoService: PhotoService, @MainDispatcher dispatcher: CoroutineDispatcher) : PhotoRepository{
        return PhotoRepositoryImpl(photoService ,dispatcher)
    }

    @Provides
    @Singleton
    fun provideAllUseCase(loginRepository: LoginRepository, photoRepository: PhotoRepository) : AllUseCase {
        return AllUseCase(LoginUsecase(loginRepository = loginRepository), PhotoUsecase(photoRepository))
    }


}