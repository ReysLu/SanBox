package com.example.sanbox.fileutils.mediastore

import android.content.Context
import android.net.Uri
import com.example.sanbox.fileutils.constants.FileConstants
import com.example.sanbox.fileutils.request.NewFileRequest
import com.example.sanbox.fileutils.responce.FileResponse

/**
 * Author: Even
 * Create time: 2021/8/14 9:16
 * Des:创建文件Helper
 */
object CreateFileByMediaStoreHelper {


    fun newCreateFile(context: Context, newFileRequest: NewFileRequest): FileResponse {


        val uri = MediaStoreUriHelper.getUri(newFileRequest.fileType)
        return newCreateFile(context, newFileRequest, uri)


    }

    private fun newCreateFile(
        context: Context,
        newFileRequest: NewFileRequest,
        uri: Uri
    ): FileResponse {

        val contentValues = ContentValuesHelper.getContentValues(newFileRequest)
        val resolver = context.contentResolver
        val responseUri = resolver.insert(uri, contentValues)

        val fileResponse = FileResponse()
        fileResponse.accessType = FileConstants.TYPE_MEDIA_STORE
        fileResponse.uri = responseUri
        fileResponse.result = false
        responseUri?.let {
            fileResponse.result = true
        }
        return fileResponse


    }
}