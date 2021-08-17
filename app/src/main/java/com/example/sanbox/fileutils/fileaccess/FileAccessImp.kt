package com.example.sanbox.fileutils.fileaccess

import android.content.Context
import com.example.sanbox.fileutils.filehelper.CreateFileHelper
import com.example.sanbox.fileutils.filehelper.DeleteFileHelper
import com.example.sanbox.fileutils.filehelper.OpenFileHelper
import com.example.sanbox.fileutils.request.DeleteFileRequest
import com.example.sanbox.fileutils.request.NewFileRequest
import com.example.sanbox.fileutils.request.OpenFileRequest
import com.example.sanbox.fileutils.responce.FileResponse
import java.io.InputStream
import java.io.OutputStream

/**
 * Author: Even
 * Create time: 2021/8/13 17:13
 * Des:传统文件操作实现类
 */
class FileAccessImp : FileAccessInterface {

    companion object {
        val instance: FileAccessImp by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            FileAccessImp()
        }
    }

    override fun newCreateFile(context: Context, request: NewFileRequest): FileResponse {

        return CreateFileHelper.newCreateFile(context, request)


    }

    override fun getFileInputStream(context: Context, request: OpenFileRequest): InputStream? {
        return OpenFileHelper.getFileInputStream(context,request)

    }

    override fun getFileOutputStream(context: Context, request: OpenFileRequest): OutputStream? {
        return OpenFileHelper.getFileOutputStream(context,request)

    }

    override fun deleteFile(context: Context, request: DeleteFileRequest): Boolean {
        return DeleteFileHelper.deleteFile(context,request)

    }

}