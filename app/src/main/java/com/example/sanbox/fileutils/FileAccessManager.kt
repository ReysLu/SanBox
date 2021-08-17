package com.example.sanbox.fileutils

import android.content.Context
import com.example.sanbox.fileutils.constants.FileConstants
import com.example.sanbox.fileutils.fileaccess.FileAccessFactory
import com.example.sanbox.fileutils.request.DeleteFileRequest
import com.example.sanbox.fileutils.request.NewFileRequest
import com.example.sanbox.fileutils.request.OpenFileRequest
import com.example.sanbox.fileutils.responce.FileResponse
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

/**
 * Author: Even
 * Create time: 2021/8/13 17:20
 * Des:文件操作系统唯一入口
 * 要修改文件保存在公共目录下的二级文件夹名字可以在
 * @see com.example.sanbox.fileutils.constants.FileConstants 中修改
 */
object FileAccessManager {


    /**
     * 创建新文件，但不带输出流，Android Q 会返回uri 普通模式会返回file
     */
    fun newCreateFile(context: Context, request: NewFileRequest): FileResponse? {
        return if (request.mFile != null) {
            FileAccessFactory.getCreateFileAccess(request.mFile!!.absolutePath)
                .newCreateFile(context, request)
        } else {
            null
        }

    }


    /**
     * 打开文件并获取输入流，可以对文件进行读取
     */
    fun getFileInputStream(context: Context, request: OpenFileRequest): InputStream? {
        return if (request.mFile != null) {
            FileAccessFactory.getCreateFileAccess(request.mFile!!.absolutePath)
                .getFileInputStream(context, request)
        } else {
            null
        }

    }

    /**
     * 打开文件并获取输出流，可以对文件进行写入，注意该输出流是覆盖写入
     */
    fun getFileOutputStream(context: Context, request: OpenFileRequest): OutputStream? {
        return if (request.mFile != null) {
            FileAccessFactory.getCreateFileAccess(request.mFile!!.absolutePath)
                .getFileOutputStream(context, request)
        } else {
            null
        }

    }

    /**
     * 删除文件
     */
    fun deleteFile(context: Context, request: DeleteFileRequest): Boolean {
        return if (request.mFile != null) {
            FileAccessFactory.getCreateFileAccess(request.mFile!!.absolutePath)
                .deleteFile(context, request)
        } else {
            false
        }

    }


    /**
     * 通过返回结果获取输出流，注意该输出流是覆盖写入
     */
    fun getFileOutputStream(context: Context, response: FileResponse): OutputStream? {

        return when (response.accessType) {
            FileConstants.TYPE_MEDIA_STORE ->{
                if (response.uri!=null){
                    context.contentResolver.openOutputStream(response.uri!!)
                }else{
                    null
                }
            }
            else -> {
                if (response.file!=null){
                    FileOutputStream(response.file!!)
                }else{
                    null
                }

            }


        }


    }

    /**
     * 创建新文件并获取输出流，注意该输出流是覆盖写入
     */
    fun getNewFileOutputStream(context: Context, request: NewFileRequest): OutputStream?{
        val response=newCreateFile(context,request)

        return if (response!=null){
            getFileOutputStream(context,response)
        }else{
            null
        }

    }


}