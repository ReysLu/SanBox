package com.example.sanbox.fileutils

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import java.io.File

/**
 * Author: Even
 * Create time: 2021/8/13 17:55
 * Des:
 */
object EnvironmentUtils {
    const val DIR_ANDROID_DATA = "Android/data"
    const val DIR_DATA_DATA = "data/data"

    fun isPublicDir(filePath: String): Boolean {

        return isDCIMDir(filePath) ||
                isPicturesDir(filePath) ||
                isMoviesDir(filePath) ||
                isDownloadsDir(filePath) ||
                isDocumentsDir(filePath)


    }

    fun isDCIMDir(relative: String): Boolean {
        return relative.startsWith(Environment.DIRECTORY_DCIM) ||
                relative.startsWith(File.separator + Environment.DIRECTORY_DCIM)
    }


    fun isPicturesDir(relative: String): Boolean {
        return relative.startsWith(Environment.DIRECTORY_PICTURES) ||
                relative.startsWith(File.separator + Environment.DIRECTORY_PICTURES)

    }

    fun isMoviesDir(relative: String): Boolean {
        return relative.startsWith(Environment.DIRECTORY_MOVIES) ||
                relative.startsWith(File.separator + Environment.DIRECTORY_MOVIES)

    }

    fun isDownloadsDir(relative: String): Boolean {
        return relative.startsWith(Environment.DIRECTORY_DOWNLOADS) ||
                relative.startsWith(File.separator + Environment.DIRECTORY_DOWNLOADS)

    }

    fun isDocumentsDir(relative: String): Boolean {
        return relative.startsWith(Environment.DIRECTORY_DOCUMENTS) ||
                relative.startsWith(File.separator + Environment.DIRECTORY_DOCUMENTS)

    }

    fun isPrivateDir(relative: String): Boolean {
        return relative.startsWith(DIR_ANDROID_DATA) ||
                relative.startsWith(DIR_DATA_DATA) ||
                relative.startsWith(File.separator + DIR_ANDROID_DATA) ||
                relative.startsWith(File.separator + DIR_DATA_DATA)
    }


    fun getFileName(filePath: String): String? {

        getDisplayName(filePath).let {
            val lastDot = filePath.lastIndexOf('.')
            return if (lastDot == -1) {
                filePath
            } else {
                filePath.substring(0, lastDot)
            }
        }
        return null


    }

    fun getDisplayName(filePath: String?): String? {
        var filePath = filePath
        if (filePath == null) {
            return null
        }
        if (filePath.indexOf('/') == -1) {
            return filePath
        }
        if (filePath.endsWith("/")) {
            filePath = filePath.substring(0, filePath.length - 1)
        }
        return filePath.substring(filePath.lastIndexOf('/') + 1)
    }


    fun getDirPath(path: String): String {

        val end = path.lastIndexOf("/")
        return if (path.startsWith("/")) {
            path.substring(1, end + 1)

        } else {
            path
        }


    }


    /**
     * 用于一般模式下，获取真实路径
     */
    fun getRelativePath(path: String): String {

//        Download/aa.txt
//        Download/Even/aa.txt
//        /storage/emulated/0/SuperLivePlus/SpareList.txt
        var relativePath = path
        if (path.startsWith(Environment.DIRECTORY_DCIM) || path.startsWith(File.separator + Environment.DIRECTORY_DCIM)) {


            val dirPath = path.substring(
                path.indexOf(Environment.DIRECTORY_DCIM) + Environment.DIRECTORY_DCIM.length,
                path.length
            )

            relativePath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).absolutePath + dirPath


        } else if (path.startsWith(Environment.DIRECTORY_PICTURES) || path.startsWith(File.separator + Environment.DIRECTORY_PICTURES)) {
            val dirPath = path.substring(
                path.indexOf(Environment.DIRECTORY_PICTURES) + Environment.DIRECTORY_PICTURES.length,
                path.length
            )

            relativePath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath + dirPath
        } else if (path.startsWith(Environment.DIRECTORY_MOVIES) || path.startsWith(File.separator + Environment.DIRECTORY_MOVIES)) {
            val dirPath = path.substring(
                path.indexOf(Environment.DIRECTORY_MOVIES) + Environment.DIRECTORY_MOVIES.length,
                path.length
            )

            relativePath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).absolutePath + dirPath
        } else if (path.startsWith(Environment.DIRECTORY_DOWNLOADS) || path.startsWith(File.separator + Environment.DIRECTORY_DOWNLOADS)) {
            val dirPath = path.substring(
                path.indexOf(Environment.DIRECTORY_DOWNLOADS) + Environment.DIRECTORY_DOWNLOADS.length,
                path.length
            )

            relativePath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + dirPath
        } else if (path.startsWith(Environment.DIRECTORY_DOCUMENTS) || path.startsWith(File.separator + Environment.DIRECTORY_DOCUMENTS)) {
            val dirPath = path.substring(
                path.indexOf(Environment.DIRECTORY_DOCUMENTS) + Environment.DIRECTORY_DOCUMENTS.length,
                path.length
            )
            relativePath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).absolutePath + dirPath
        } else {
            relativePath = path
        }
        return relativePath


    }


}