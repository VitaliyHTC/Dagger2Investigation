package com.vitaliyhtc.dagger2investigation.utils

import android.content.Context
import java.io.IOException
import java.nio.charset.Charset

fun readStringFromAsset(context: Context, assetName: String): String {
    val json: String?
    try {
        val inputStream = context.assets.open(assetName)
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