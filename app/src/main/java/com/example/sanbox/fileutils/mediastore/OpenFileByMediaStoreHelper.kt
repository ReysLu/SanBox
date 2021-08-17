package com.example.sanbox.fileutils.mediastore

import android.content.Context
import android.net.Uri
import android.os.Environment
import com.example.sanbox.fileutils.EnvironmentUtils
import com.example.sanbox.fileutils.constants.FileConstants
import com.example.sanbox.fileutils.request.NewFileRequest
import com.example.sanbox.fileutils.request.OpenFileRequest
import com.example.sanbox.fileutils.responce.FileResponse
import java.io.InputStream
import java.io.OutputStream

/**
 * Author: Even
 * Create time: 2021/8/14 9:16
 * Des:创建文件Helper
 */
object OpenFileByMediaStoreHelper {


    fun getFileInputStream(context: Context, request: OpenFileRequest): InputStream? {

        request.mFile?.let {
            val uri = MediaStoreUriHelper.queryFileUri(context,it.absolutePath,request.fileType)
            return getFileInputStream(context, uri)
        }
        return null

    }

    private fun getFileInputStream(
        context: Context,
        uri: Uri?
    ): InputStream? {
        uri?.let {
            return context.contentResolver.openInputStream(uri)
        }

        return null
    }




    fun getFileOutputStream(context: Context, request: OpenFileRequest): OutputStream? {

        request.mFile?.let {
            val uri = MediaStoreUriHelper.queryFileUri(context,it.absolutePath,request.fileType)
            return getFileOutputStream(context, uri)
        }
        return null

    }

    private fun getFileOutputStream(
        context: Context,
        uri: Uri?
    ): OutputStream? {
        uri?.let {

//            return context.contentResolver.openOutputStream(uri)
            return context.contentResolver.openOutputStream(uri)
        }
        return null
    }


}