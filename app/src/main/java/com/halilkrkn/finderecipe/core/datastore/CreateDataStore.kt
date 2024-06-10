package com.halilkrkn.finderecipe.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import java.io.File

//fun Context.createDataStore(name: String): DataStore<Preferences> {
//    return PreferenceDataStoreFactory.create(
//        produceFile = { File(this.filesDir, "datastore/$name.preferences_pb") }
//    )
//}

val Context.createDataStore: DataStore<Preferences> by preferencesDataStore(name = "onboarding_prefs")