package com.example.sanbox.fileutils.mediastore

import android.content.Context
import android.net.Uri
import android.os.Environment
import com.example.sanbox.fileutils.EnvironmentUtils
import com.example.sanbox.fileutils.constants.FileConstants
import com.example.sanbox.fileutils.request.DeleteFileRequest
import com.example.sanbox.fileutils.request.NewFileRequest
import com.example.sanbox.fileutils.request.OpenFileRequest
import com.example.sanbox.fileutils.responce.FileResponse
import java.io.InputStream
import java.io.OutputStream

/**
 * Author: Even
 * Create time: 2021/8/14 9:16
 * Des:删除文件Helper
 */
object DeleteFileByMediaStoreHelper {


    fun deleteFile(context: Context, request: DeleteFileRequest): Boolean {

        request.mFile?.let {
            val uri = MediaStoreUriHelper.queryFileUri(context,it.absolutePath,request.fileType)
            return deleteFile(context, uri)
        }

        return false

    }

    private fun deleteFile(
        context: Context,
        uri: Uri?
    ): Boolean {
        uri?.let {
            val row= context.contentResolver.delete(it,null ,null)
            return row>0
        }
        return false
    }






}