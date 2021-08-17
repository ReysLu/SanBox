package com.example.sanbox.fileutils.fileaccess

import android.os.Build
import android.os.Environment
import android.util.Log
import com.example.sanbox.fileutils.EnvironmentUtils

/**
 * Author: Even
 * Create time: 2021/8/13 17:12
 * Des:
 */
object FileAccessFactory {

    fun getCreateFileAccess(filePath: String): FileAccessInterface {

        return if (isSandbox()&&isPublicDir(filePath)) {
            Log.i("FileAccessFactory","MediaStoreAccessImp")
            MediaStoreAccessImp.instance
        } else {
            Log.i("FileAccessFactory","FileAccessImp")

            FileAccessImp.instance
        }

    }

    private fun isPublicDir(filePath: String): Boolean {

        return EnvironmentUtils.isPublicDir(filePath)

    }

    private fun isSandbox(): Boolean {
        return if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
            !Environment.isExternalStorageLegacy()
        }else{
            false
        }

    }




}