package com.example.lunchforyou.utils

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthService{
    private var auth = Firebase.auth


    fun createNewUser(email: String, password: String, callback: RegisterCallback){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    if (it.result.user == null)
                        callback.onUserCreateFailure()
                    else
                        callback.onUserCreateResult(it.result.user!!.uid)
                } else{
                    callback.onUserCreateFailure()
                }
            }
    }

    fun checkCurrentUser():String?{
        return if(auth.currentUser==null)
            null
        else
            auth.currentUser!!.uid
    }

    fun signIn(email: String, password: String, callback: SignInCallback){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    if(auth.currentUser!=null)
                        callback.onUserSignInResult(auth.currentUser!!.uid)
                    else
                        callback.onUserSignInFailure()
                } else {
                    callback.onUserSignInFailure()
                }
            }
    }
    fun signOut() {
        auth.signOut()
    }

}

interface SignInCallback{
    fun onUserSignInResult(uid:String)
    fun onUserSignInFailure()
}

interface RegisterCallback{
    fun onUserCreateResult(uid: String)
    fun onUserCreateFailure()
}
