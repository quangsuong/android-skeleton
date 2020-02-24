package vn.impl.projectbase.base.sharepreferences

import android.content.SharedPreferences

class AppConfigPreferences(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val KEY_EXAMPLE = "key_example"
    }

    fun getExample(): String? {
        return sharedPreferences.getString(KEY_EXAMPLE, "")
    }

    fun setExample(example: String) {
        sharedPreferences.edit().putString(KEY_EXAMPLE, example).apply()
    }
}