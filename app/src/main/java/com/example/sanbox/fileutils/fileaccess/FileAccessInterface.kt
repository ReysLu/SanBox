package com.example.sanbox.fileutils.fileaccess

import android.content.Context
import com.example.sanbox.fileutils.request.DeleteFileRequest
import com.example.sanbox.fileutils.request.NewFileRequest
import com.example.sanbox.fileutils.request.OpenFileRequest
import com.example.sanbox.fileutils.responce.FileResponse
import java.io.InputStream
import java.io.OutputStream

/**
 * Author: Even
 * Create time: 2021/8/13 16:27
 * Des:文件操作功能接口
 */
interface FileAccessInterface {


    fun newCreateFile(context: Context, request: NewFileRequest):FileResponse
    fun getFileInputStream(context: Context, request: OpenFileRequest):InputStream?
    fun getFileOutputStream(context: Context, request: OpenFileRequest):OutputStream?
    fun deleteFile(context: Context, request: DeleteFileRequest):Boolean
//    fun getFileOutputStream(context: Context, newFileRequest: OpenFileRequest):OutputStream




}