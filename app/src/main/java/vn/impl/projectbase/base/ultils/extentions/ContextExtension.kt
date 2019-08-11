package vn.impl.projectbase.base.ultils.extentions

import android.content.Context
import android.provider.Settings

fun Context.getDeviceId(): String {
    return Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
}
