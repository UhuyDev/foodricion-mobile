package com.lans.foodricion.data.source.local

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(private val context: Context) {
    companion object {
        val USER_ID = stringPreferencesKey("USER_ID")
        val ACCESS_TOKEN = stringPreferencesKey("ACCESS_TOKEN")
        val REFRESH_TOKEN = stringPreferencesKey("REFRESH_TOKEN")
        val EXPIRED_AT = longPreferencesKey("EXPIRED_AT")
        private const val DATASTORE = "foodricionapp"
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE)

    suspend fun <T> storeData(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    fun getAccessToken(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            Log.d("TOKEN", "intercept: " + preferences[ACCESS_TOKEN])
            preferences[ACCESS_TOKEN] ?: ""
        }
    }

    fun getRefreshToken(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN] ?: ""
        }
    }

    fun checkTokenExpiration(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            val expired = preferences[EXPIRED_AT]
            if (expired != null) {
                expired <= System.currentTimeMillis()
            } else {
                false
            }
        }
    }

    suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_ID)
            preferences.remove(ACCESS_TOKEN)
            preferences.remove(REFRESH_TOKEN)
            preferences.remove(EXPIRED_AT)
        }
    }
}