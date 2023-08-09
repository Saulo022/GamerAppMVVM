package com.example.gamerappmvvm.presentation.screens.profile_edit

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamerappmvvm.domain.model.Response
import com.example.gamerappmvvm.domain.model.User
import com.example.gamerappmvvm.domain.uses_cases.users.UsersUseCases
import com.example.gamerappmvvm.presentation.utils.ComposeFileProvider
import com.example.gamerappmvvm.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usersUseCases: UsersUseCases,
    @ApplicationContext private val context: Context
) : ViewModel() {

    //STATE FORM
    var state by mutableStateOf(ProfileEditState())
        private set

    //USERNAME
    var usernameErrMsg by mutableStateOf("")
        private set

    fun onUsernameInput(username: String) {
        state = state.copy(username = username)
    }

    val data = savedStateHandle.get<String>("user")
    val user = User.fromJson(data!!)

    //RESPONSE
    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    var saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set

    //IMAGE
    //var imageUri by mutableStateOf("")
    //var hasImage by mutableStateOf(false)

    //FILE
    var file: File? = null

    val resultingActivityHandler = ResultingActivityHandler()
    init {
        state = state.copy(
            username = user.username,
            image = user.image
        )
    }

    /*fun onCameraResult(result: Boolean) {
        hasImage = result
    }

    fun onGalleryResult(uri: Uri?) {
        hasImage = uri != null
        imageUri = uri
    }*/

    fun saveImage() {
        viewModelScope.launch {
            if (file != null) {
                saveImageResponse = Response.Loading
                val result = usersUseCases.saveImage(file!!)
                saveImageResponse = result
            }
        }
    }
    fun pickImage() {
        viewModelScope.launch {
            val result = resultingActivityHandler.getContent("image/*")
            if (result != null) {
                file = ComposeFileProvider.createFileFromUri(context, result)
                state = state.copy(image = result.toString())
            }
        }
    }

    fun takePhoto() {
        viewModelScope.launch {
            val result = resultingActivityHandler.takePicturePreview()
            if (result != null) {
                state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
                file = File(state.image)
            }
        }
    }

    fun onUpdate(url: String) {
        val myUser = User(
            id = user.id,
            username = state.username,
            image = url
        )
        update(myUser)
    }

    fun update(user: User) {
        viewModelScope.launch {
            updateResponse = Response.Loading
            val result = usersUseCases.update(user)
            updateResponse = result
        }
    }

    fun validateUsername() {

        if (state.username.length >= 5) {
            usernameErrMsg = ""
        } else {
            usernameErrMsg = "Al menos 5 caracteres"
        }

    }

}