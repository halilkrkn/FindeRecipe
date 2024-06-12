package com.halilkrkn.finderecipe.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import java.io.File

val Context.createDataStore: DataStore<Preferences> by preferencesDataStore(name = "onboarding_prefs")