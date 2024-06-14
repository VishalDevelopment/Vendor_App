package com.exmple.venderapp.Pref

import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow

val USER_KEY = stringPreferencesKey("user_id")
interface UserIdPref {
    suspend fun setId(user_Id:String)
    fun getId():Flow<String>
}

