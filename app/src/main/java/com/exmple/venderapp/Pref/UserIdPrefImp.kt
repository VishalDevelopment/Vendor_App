package com.exmple.venderapp.Pref

import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserIdPrefImp(val datastore: DataStore<Preferences>) : UserIdPref {
    override suspend fun setId(user_Id: String) {
        datastore.edit {
            it[USER_KEY] = user_Id
        }
    }

    override fun getId(): Flow<String> {
      return  datastore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[USER_KEY] ?: ""
        }
    }
}