package com.example.sanbox.fileutils.filehelper

import android.content.Context
import android.util.Log
import com.example.sanbox.fileutils.EnvironmentUtils
import com.example.sanbox.fileutils.constants.FileConstants
import com.example.sanbox.fileutils.mediastore.CreateFileByMediaStoreHelper
import com.example.sanbox.fileutils.mediastore.MediaStoreUriHelper
import com.example.sanbox.fileutils.request.NewFileRequest
import com.example.sanbox.fileutils.responce.FileResponse
import java.io.File

/**
 * Author: Even
 * Create time: 2021/8/16 11:57
 * Des:创建文件helper
 */
object CreateFileHelper {


    fun newCreateFile(context: Context, newFileRequest: NewFileRequest): FileResponse {

        val path = newFileRequest.mFile?.absolutePath
        val response = FileResponse()

        path?.let {

            val relativePath = EnvironmentUtils.getRelativePath(it)
            Log.i("CreateFileHelper", "relativePath:$relativePath")
            val file = File(relativePath)
            if (file.exists()) {
                file.delete()
            } else {
                val parent = file.parentFile
                parent.mkdirs()
                response.result = file.createNewFile()
                response.file = file
                response.accessType = FileConstants.TYPE_FILE
            }

        }

        return response

    }


}