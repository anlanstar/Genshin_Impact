package com.example.genshinimpact.file

import android.content.Context
import android.widget.Toast
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class aaa {
    fun save(context: Context) {
        try {
            val outStream: FileOutputStream =
                context.openFileOutput("a.txt", Context.MODE_WORLD_READABLE)
            outStream.close()
        } catch (e: FileNotFoundException) {
            return
        } catch (e: IOException) {
            return
        }
    }

}