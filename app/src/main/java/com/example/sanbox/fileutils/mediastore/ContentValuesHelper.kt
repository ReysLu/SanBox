package com.example.sanbox.fileutils.mediastore

import android.content.ContentValues
import android.provider.MediaStore
import android.util.Log
import com.example.sanbox.fileutils.EnvironmentUtils
import com.example.sanbox.fileutils.constants.FileConstants
import com.example.sanbox.fileutils.constants.FileType
import com.example.sanbox.fileutils.request.NewFileRequest
import com.example.sanbox.fileutils.request.OpenFileRequest

/**
 * Author: Even
 * Create time: 2021/8/14 9:36
 * Des:
 */
object ContentValuesHelper {

    private const val MIME_TYPE_IMAGE = "image/*"
    private const val MIME_TYPE_VIDEO = "video/*"
    private const val MIME_TYPE_FILE = "*/*"


    private fun getMimeType(fileType: Int): String {

        return when (fileType) {
            FileType.FILE_TYPE_IMAGE -> MIME_TYPE_IMAGE
            FileType.FILE_TYPE_VIDEO -> MIME_TYPE_VIDEO

            // TODO: 2021/8/14 类型需要补充完善
            else -> MIME_TYPE_FILE


        }
    }


    fun getContentValues(newFileRequest: NewFileRequest): ContentValues {


        val contentValues = newFileRequest.mContentValues

        if (contentValues == null) {

            val values = ContentValues()

            newFileRequest.mFile?.let {
                val title = EnvironmentUtils.getDisplayName(it.absolutePath)
                val displayName = EnvironmentUtils.getDisplayName(it.absolutePath)
                val relativePath=EnvironmentUtils.getDirPath(it.absolutePath)
                val type = getMimeType(newFileRequest.fileType)
                val savingTimestamp = System.currentTimeMillis()
                val now = savingTimestamp / 1000
                values.put(MediaStore.MediaColumns.TITLE, title)
                values.put(MediaStore.MediaColumns.DISPLAY_NAME, displayName)
                values.put(MediaStore.MediaColumns.MIME_TYPE, type)
                values.put(MediaStore.MediaColumns.DATE_TAKEN, savingTimestamp)
                values.put(MediaStore.MediaColumns.DATE_MODIFIED, now)
                values.put(MediaStore.MediaColumns.DATE_ADDED, now)
                values.put(MediaStore.MediaColumns.RELATIVE_PATH, relativePath+FileConstants.PUBLIC_DIR_NAME+"/")
                values.put(MediaStore.MediaColumns.SIZE, it.length())
                Log.i("ContentValuesHelper", "----->\n" +
                        "MediaStore.MediaColumns.TITLE:$title\n" +
                        "MediaStore.MediaColumns.DISPLAY_NAM:$displayName\n" +
                        "MediaStore.MediaColumns.MIME_TYPE:$type\n" +
                        "MediaStore.MediaColumns.DATE_TAKEN:$savingTimestamp\n" +
                        "MediaStore.MediaColumns.DATE_MODIFIED:$now\n" +
                        "MediaStore.MediaColumns.DATE_ADDED:$now\n" +
                        "MediaStore.MediaColumns.RELATIVE_PATH:$relativePath\n" +
                        "MediaStore.MediaColumns.SIZE:${it.length()}\n" )

            }

            return values

        } else {
            return contentValues
        }


    }



    fun getContentValues(newFileRequest: OpenFileRequest): ContentValues {

        val contentValues = newFileRequest.mContentValues

        if (contentValues == null) {

            val values = ContentValues()

            newFileRequest.mFile?.let {
                val title = EnvironmentUtils.getFileName(it.absolutePath)
                val displayName = EnvironmentUtils.getDisplayName(it.absolutePath)
                val relativePath=EnvironmentUtils.getDirPath(it.absolutePath)
                val type = getMimeType(newFileRequest.fileType)
                val savingTimestamp = System.currentTimeMillis()
                val now = savingTimestamp / 1000
                values.put(MediaStore.MediaColumns.TITLE, title)
                values.put(MediaStore.MediaColumns.DISPLAY_NAME, displayName)
                values.put(MediaStore.MediaColumns.MIME_TYPE, type)
                values.put(MediaStore.MediaColumns.DATE_TAKEN, savingTimestamp)
                values.put(MediaStore.MediaColumns.DATE_MODIFIED, now)
                values.put(MediaStore.MediaColumns.DATE_ADDED, now)
                values.put(MediaStore.MediaColumns.RELATIVE_PATH, relativePath+FileConstants.PUBLIC_DIR_NAME+"/")
                values.put(MediaStore.MediaColumns.SIZE, it.length())
                Log.i("ContentValuesHelper", "----->\n" +
                        "MediaStore.MediaColumns.TITLE:$title\n" +
                        "MediaStore.MediaColumns.DISPLAY_NAM:$displayName\n" +
                        "MediaStore.MediaColumns.MIME_TYPE:$type\n" +
                        "MediaStore.MediaColumns.DATE_TAKEN:$savingTimestamp\n" +
                        "MediaStore.MediaColumns.DATE_MODIFIED:$now\n" +
                        "MediaStore.MediaColumns.DATE_ADDED:$now\n" +
                        "MediaStore.MediaColumns.RELATIVE_PATH:$relativePath\n" +
                        "MediaStore.MediaColumns.SIZE:${it.length()}\n" )

            }

            return values

        } else {
            return contentValues
        }


    }


}