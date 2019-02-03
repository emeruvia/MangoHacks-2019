package fgcu.mangohacks2019

import android.preference.PreferenceActivity
import android.os.Bundle
import android.preference.PreferenceFragment



class LanguagePreferences: PreferenceActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentManager.beginTransaction().replace(android.R.id.content, MyPreferenceFragment()).commit()

    }


    class MyPreferenceFragment : PreferenceFragment() {

        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)

            addPreferencesFromResource(R.xml.preference)

        }

    }
}