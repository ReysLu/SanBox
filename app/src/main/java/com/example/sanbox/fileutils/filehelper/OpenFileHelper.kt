package com.example.sanbox.fileutils.filehelper

import android.content.Context
import com.example.sanbox.fileutils.EnvironmentUtils
import com.example.sanbox.fileutils.request.OpenFileRequest
import java.io.*

/**
 * Author: Even
 * Create time: 2021/8/17 17:45
 * Des:打开文件，获取输入 输出流
 */
object OpenFileHelper {

    fun getFileInputStream(context: Context, request: OpenFileRequest): InputStream? {

        val path = request.mFile?.absolutePath

        return if (path.isNullOrEmpty()){
            null
        }else {
            val relativePath = EnvironmentUtils.getRelativePath(path)
            val file = File(relativePath)
            FileInputStream(file)
        }

    }

    fun getFileOutputStream(context: Context, request: OpenFileRequest): OutputStream? {

        val path = request.mFile?.absolutePath

        return if (path.isNullOrEmpty()){
            null
        }else {
            val relativePath = EnvironmentUtils.getRelativePath(path)
            val file = File(relativePath)
            FileOutputStream(file)
        }

    }

}