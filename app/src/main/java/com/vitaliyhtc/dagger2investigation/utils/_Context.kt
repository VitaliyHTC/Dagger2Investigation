package com.vitaliyhtc.dagger2investigation.utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import java.io.IOException
import java.nio.charset.Charset

fun Context.toastLong(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.toastShort(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.layoutInflater(): LayoutInflater {
    return LayoutInflater.from(this)
}

fun Context.readStringFromAsset(assetName: String): String {
    val json: String?
    try {
        val inputStream = assets.open(assetName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, Charset.forName("UTF-8"))
    } catch (ex: IOException) {
        ex.printStackTrace()
        return ""
    }
    return json
}