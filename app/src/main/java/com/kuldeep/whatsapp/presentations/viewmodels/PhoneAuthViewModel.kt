package com.kuldeep.whatsapp.presentations.viewmodels

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.kuldeep.whatsapp.models.PhoneAuthUser
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class PhoneAuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val database: FirebaseDatabase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Ideal)
    val authState = _authState.asStateFlow()

    private val userRef = database.getReference("users")
    fun sendVerificationCode(phoneNumber: String, activity: Activity) {
        _authState.value = AuthState.Loading
        val options = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, token)
                Log.d("PhoneAuthViewModel", "onCodeSent: $id")
                _authState.value = AuthState.CodeSent(verificationId = id)
            }

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d("PhoneAuthViewModel", "onVerificationCompleted: ${credential.smsCode}")
                signInWithCredential(credential, activity)
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                Log.e("PhoneAuthViewModel", "onVerificationFailed: ${exception.message}")
                _authState.value =
                    AuthState.Error(message = exception.message ?: "Verification failed")
            }
        }
        val phoneAuthOptions = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(options)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)

    }


    private fun signInWithCredential(credential: PhoneAuthCredential, context: Context) {
        _authState.value = AuthState.Loading
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val phoneAuthUser = PhoneAuthUser(
                        userid = user?.uid ?: "",
                        phoneNumber = user?.phoneNumber ?: "",
                    )
                    markUserAsSignedIn(context)
                    _authState.value = AuthState.Success(phoneAuthUser)
                    fetchUserData(user?.uid ?: "")
                } else {
                    Log.e("PhoneAuthViewModel", "signInWithCredential: ${task.exception?.message}")
                    _authState.value = AuthState.Error(
                        message = task.exception?.message ?: "Sign in failed"
                    )
                }
            }
    }

    private fun markUserAsSignedIn(context: Context) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("is_signed_in", true).apply()
    }

    private fun fetchUserData(userId: String) {
        userRef.child(userId).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val user = snapshot.getValue(PhoneAuthUser::class.java)
                if (user != null) {
                    Log.d("PhoneAuthViewModel", "fetchUserData: User data fetched successfully")
                    _authState.value = AuthState.Success(user)
                } else {
                    Log.e("PhoneAuthViewModel", "fetchUserData: User data is null")
                }
            } else {
                Log.e("PhoneAuthViewModel", "fetchUserData: User does not exist")
            }
        }.addOnFailureListener { exception ->
            Log.e("PhoneAuthViewModel", "fetchUserData: ${exception.message}")
            _authState.value =
                AuthState.Error(message = exception.message ?: "Failed to fetch user data")
        }
    }

    fun verifyCode(otp: String, context: Context) {
        val currentAuthState = _authState.value

        if (currentAuthState !is AuthState.CodeSent || currentAuthState.verificationId.isEmpty()) {
            Log.e("PhoneAuthViewModel", "Attempting to verify code without a valid verification ID")
            _authState.value =
                AuthState.Error(message = "Verification not started or invalid state")
            return
        }
        val credential = PhoneAuthProvider.getCredential(
            currentAuthState.verificationId, otp
        )
        signInWithCredential(credential, context)
    }

    fun saveUserProfile(userId: String, name: String, status: String, profileImage: Bitmap) {
        val database = FirebaseDatabase.getInstance().reference
        val encodedImage = profileImage?.let { convertBitmapToBase64(it) } ?: ""
        val userProfile = PhoneAuthUser(
            userid = userId,
            name = name,
            status = status,
            phoneNumber = firebaseAuth.currentUser?.phoneNumber ?: "",
            profileImage = encodedImage
        )
        database.child("users").child(userId).setValue(userProfile)
    }

    private fun convertBitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    fun resetAuthState() {
        _authState.value = AuthState.Ideal
    }

    fun signOut(activity: Activity): Boolean {
        firebaseAuth.signOut()
        val sharedPreferences = activity.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("is_signed_in", false)
    }
}

sealed class AuthState {
    object Ideal : AuthState()
    object Loading : AuthState()
    data class CodeSent(val verificationId: String) : AuthState()
    data class Success(val user: PhoneAuthUser) : AuthState()
    data class Error(val message: String) : AuthState()
}