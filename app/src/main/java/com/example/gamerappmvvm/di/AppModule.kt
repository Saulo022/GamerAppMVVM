package com.example.gamerappmvvm.di

import com.example.gamerappmvvm.data.repository.AuthRepositoryImpl
import com.example.gamerappmvvm.domain.repository.AuthRepository
import com.example.gamerappmvvm.domain.uses_cases.auth.AuthUseCases
import com.example.gamerappmvvm.domain.uses_cases.auth.GetCurrentUSer
import com.example.gamerappmvvm.domain.uses_cases.auth.Login
import com.example.gamerappmvvm.domain.uses_cases.auth.Logout
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUSer = GetCurrentUSer(repository),
        login = Login(repository),
        logout = Logout(repository)
    )

}