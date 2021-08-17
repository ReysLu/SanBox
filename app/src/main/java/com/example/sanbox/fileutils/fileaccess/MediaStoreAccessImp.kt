package com.example.sanbox.fileutils.fileaccess

import android.content.Context
import com.example.sanbox.fileutils.FileAccessManager.newCreateFile
import com.example.sanbox.fileutils.mediastore.CreateFileByMediaStoreHelper
import com.example.sanbox.fileutils.mediastore.DeleteFileByMediaStoreHelper
import com.example.sanbox.fileutils.mediastore.OpenFileByMediaStoreHelper
import com.example.sanbox.fileutils.request.DeleteFileRequest
import com.example.sanbox.fileutils.request.NewFileRequest
import com.example.sanbox.fileutils.request.OpenFileRequest
import com.example.sanbox.fileutils.responce.FileResponse
import java.io.InputStream
import java.io.OutputStream

/**
 * Author: Even
 * Create time: 2021/8/13 17:06
 * Des:Media文件操作实现类
 */
class MediaStoreAccessImp : FileAccessInterface {

    companion object {
        val instance: MediaStoreAccessImp by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MediaStoreAccessImp()
        }
    }


    override fun newCreateFile(context: Context, newFileRequest: NewFileRequest):FileResponse {


        return CreateFileByMediaStoreHelper.newCreateFile(context, newFileRequest)


    }

    override fun getFileInputStream(context: Context, request: OpenFileRequest): InputStream? {
        return OpenFileByMediaStoreHelper.getFileInputStream(context, request)

    }

    override fun getFileOutputStream(context: Context, request: OpenFileRequest): OutputStream? {
        return OpenFileByMediaStoreHelper.getFileOutputStream(context, request)
    }

    override fun deleteFile(context: Context, request: DeleteFileRequest): Boolean {
        return DeleteFileByMediaStoreHelper.deleteFile(context, request)
    }





}