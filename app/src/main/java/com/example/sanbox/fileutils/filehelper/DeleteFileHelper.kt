package com.example.sanbox.fileutils.filehelper

import android.content.Context
import com.example.sanbox.fileutils.EnvironmentUtils
import com.example.sanbox.fileutils.request.DeleteFileRequest
import java.io.File

/**
 * Author: Even
 * Create time: 2021/8/17 17:53
 * Des: 删除文件
 */
object DeleteFileHelper {


    fun deleteFile(context: Context, request: DeleteFileRequest): Boolean {

        val path = request.mFile?.absolutePath

        return if (path.isNullOrEmpty()){
            false
        }else {
            val relativePath = EnvironmentUtils.getRelativePath(path)
            val file = File(relativePath)
            if (file.exists()){
                file.delete()
            }else{
                true
            }


        }

    }
}