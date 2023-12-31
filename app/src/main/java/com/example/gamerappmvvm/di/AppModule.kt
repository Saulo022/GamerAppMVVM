package com.example.gamerappmvvm.di

import com.example.gamerappmvvm.core.Constants.USERS
import com.example.gamerappmvvm.data.repository.AuthRepositoryImpl
import com.example.gamerappmvvm.data.repository.UsersRepositoryImpl
import com.example.gamerappmvvm.domain.repository.AuthRepository
import com.example.gamerappmvvm.domain.repository.UsersRepository
import com.example.gamerappmvvm.domain.uses_cases.auth.*
import com.example.gamerappmvvm.domain.uses_cases.users.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    fun provideStorageUsersRed(storage: FirebaseStorage): StorageReference = storage.reference.child(USERS)
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signup = Signup(repository)
    )

    @Provides
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        create = Create(repository),
        getUserById = GetUserById(repository),
        update = Update(repository),
        saveImage = SaveImage(repository)
    )


}