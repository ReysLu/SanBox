package com.example.sanbox.fileutils.request

import android.content.ContentValues
import android.net.Uri
import com.example.sanbox.fileutils.constants.FileType
import java.io.File

/**
 * Author: Even
 * Create time: 2021/8/13 16:28
 * Des:删除文件请求类
 */
class DeleteFileRequest{
     var mFile: File? = null
     var mContentValues: ContentValues? = null
     var mContentUri: Uri? = null
     var fileType: Int = FileType.FILE_TYPE_FILE



    class Builder{

        private var file: File?=null
        private  var contentValues: ContentValues?=null
        private  var uri: Uri?=null
        private  var fileType: Int=FileType.FILE_TYPE_FILE

        fun setFile(file: File):Builder{
            this.file=file
            return this
        }
        fun setContentValues(contentValues: ContentValues):Builder{
            this.contentValues=contentValues
            return this

        }
        fun setUri(uri: Uri):Builder{
            this.uri=uri
            return this

        }

        fun setFileType(fileType:Int):Builder{
            this.fileType=fileType
            return this

        }


        fun build(): DeleteFileRequest {
            val request= DeleteFileRequest()
            request.mFile=file
            request.mContentValues=contentValues
            request.mContentUri=uri
            request.fileType=fileType
            return request
        }



    }

}