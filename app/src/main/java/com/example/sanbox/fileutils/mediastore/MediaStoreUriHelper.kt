package com.example.sanbox.fileutils.mediastore

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.BaseColumns
import android.provider.MediaStore
import android.util.Log
import com.example.sanbox.fileutils.EnvironmentUtils
import com.example.sanbox.fileutils.constants.FileType

/**
 * Author: Even
 * Create time: 2021/8/14 9:20
 * Des:用于获取meidia 操作uri
 */
object MediaStoreUriHelper {
    private const val TAG = "MediaStoreUriHelper-->"

    //    FileType
//    const val FILE_TYPE_FILE=1//所有文件
//    const val FILE_TYPE_IMAGE=2//图片文件
//    const val FILE_TYPE_VIDEO=3//视频文件
//    const val FILE_TYPE_AUDIO=4//音频文件
//    const val FILE_TYPE_DOC=5//文档文件
    fun getUri(fileType: Int): Uri {

        return when (fileType) {

            FileType.FILE_TYPE_FILE -> MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            FileType.FILE_TYPE_IMAGE -> MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            FileType.FILE_TYPE_VIDEO -> MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            FileType.FILE_TYPE_AUDIO -> MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            FileType.FILE_TYPE_DOC -> MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            else -> MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        }
    }

    /**
     * 查找共有目录下 已存在的文件的uri
     * @param context
     * @param fileName 文件名
     * @param filetype 文件类型
     */
    fun queryFileUri(context: Context, filePath: String, filetype: Int): Uri? {

        var responseUri:Uri?=null
        val displayName = EnvironmentUtils.getDisplayName(filePath)
        //定义内容解析器
        val contentResolver = context.contentResolver

        val queryUri = getUri(filetype)

        //指定查询的列名
        val photoColumns = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.SIZE
        )


        //查询条件
        val selection = "${MediaStore.Images.Media.DISPLAY_NAME}=?"

        val selectionArgs = arrayOf(displayName)

        //多条件查询
//        val selection = "${MediaStore.Images.Media.DISPLAY_NAME}=?  and ${MediaStore.Images.Media.DISPLAY_NAME}=?"


        val cursor = contentResolver.query(
            queryUri, //指定查询哪张表的URI
            photoColumns, // 指定查询的列明
            selection, //指定where的约束条件
            selectionArgs, //为where中的占位符提供具体的值
            null // 指定查询结果的排序方式
        )

        val count = cursor!!.count
        Log.i(TAG, "imageCollection count: --> $count")

        cursor.use {
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                val title =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE))
                val displayN =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                val mimeType =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE))
                val size =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
                val contentUri = ContentUris.withAppendedId(
                    queryUri,
                    cursor.getLong(cursor.getColumnIndex(BaseColumns._ID))
                )
                Log.i(
                    TAG,
                    "imageCollection id =$id\ntitle = $title\nmime_type: =$mimeType\nsize: =\t$size\ndisplayName: =\t$displayN\ncontentUri: =\t$contentUri\n"
                )


                if (displayN==displayName){
                    responseUri=contentUri
                    break
                }


            }


            cursor.close()
        }


        return responseUri
    }

    /**
     * 查询公共文件夹下的所有图片，包括： DCIM/ 和 Pictures/ 目录
     */
    fun queryImageCollection(context: Context) {
        //定义内容解析器
        val contentResolver = context.contentResolver
        //指定查询的列名
        val photoColumns = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.SIZE
        )

        val queryUri = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
//        val queryUri= MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val cursor = contentResolver.query(
            queryUri, //指定查询哪张表的URI
            photoColumns, // 指定查询的列明
            null, //指定where的约束条件
            null, //为where中的占位符提供具体的值
            null // 指定查询结果的排序方式
        )

        val count = cursor!!.count
        Log.d("queryImageCollection", "imageCollection count: --> $count")

        cursor.use {
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                val title =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE))
                val displayName =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                val mimeType =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE))
                val size =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    cursor.getLong(cursor.getColumnIndex(BaseColumns._ID))
                )
                Log.d(
                    "queryImageCollection",
                    "imageCollection id =$id\ntitle = $title\nmime_type: =$mimeType\nsize: =\t$size\ndisplayName: =\t$displayName\ncontentUri: =\t$contentUri\n"
                )


            }
            cursor.close()
        }
    }


}