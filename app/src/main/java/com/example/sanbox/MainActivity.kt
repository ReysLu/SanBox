package com.example.sanbox

import android.Manifest
import android.app.Activity
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.BaseColumns
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.sanbox.fileutils.FileAccessManager
import com.example.sanbox.fileutils.constants.FileConstants
import com.example.sanbox.fileutils.constants.FileType
import com.example.sanbox.fileutils.request.DeleteFileRequest
import com.example.sanbox.fileutils.request.NewFileRequest
import com.example.sanbox.fileutils.request.OpenFileRequest
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.lang.Exception
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity-->"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission(this)

    }

    private fun checkPermission(
        activity: Activity
    ): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            activity.requestPermissions(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), 1
            )
        }
        return false
    }

    fun saveImage(view: View) {

//        val path = Environment.getExternalStorageDirectory().absolutePath + File.separator + FileConstants.PUBLIC_DIR_NAME + File.separator + "a.jpg"
        val path = Environment.DIRECTORY_PICTURES + "a.jpg"

        val newFileRequest = NewFileRequest.Builder()
            .setFile(File(path))
            .setFileType(FileType.FILE_TYPE_IMAGE)
            .build()

        val response = FileAccessManager.getNewFileOutputStream(this, newFileRequest)

        response?.let {
            try {
                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.th)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                it.close()
            } catch (e: Exception) {

            }

        }

    }

    fun saveTxt(view: View) {
        Log.i("MainActivity-->", "saveTxt")

        val newFileRequest = NewFileRequest.Builder()
            .setFile(File(Environment.DIRECTORY_DOWNLOADS + File.separator + "aa.txt"))
            .setFileType(FileType.FILE_TYPE_FILE)
            .build()

        val response = FileAccessManager.getNewFileOutputStream(this, newFileRequest)

        response?.let { op ->
            val testString = "西风吹老洞庭波，一夜湘君白发多。"
            op.write(testString.toByteArray())
            op.close()
        }

    }


    fun readTxt(view: View) {

        Log.i("MainActivity-->", "readTxt")

        val request = OpenFileRequest.Builder()
            .setFile(File(Environment.DIRECTORY_DOWNLOADS + File.separator + "aa.txt"))
            .setFileType(FileType.FILE_TYPE_FILE)
            .build()

        val response = FileAccessManager.getFileInputStream(this, request)
        Log.i("MainActivity-->", "response:$response")

        response?.let {
            val result = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var length: Int
            while (it.read(buffer).also { length = it } != -1) {
                result.write(buffer, 0, length)
            }
            val str: String = result.toString(StandardCharsets.UTF_8.name())

            Log.i("MainActivity-->", "str:$str")
            it.close()

        }


    }


    fun writeTxt(view: View) {
        val request = OpenFileRequest.Builder()
            .setFile(File(Environment.DIRECTORY_DOWNLOADS + File.separator + "aa.txt"))
            .setFileType(FileType.FILE_TYPE_FILE)
            .build()
        val response = FileAccessManager.getFileOutputStream(this, request)
        response?.let {

            val testString = "醉后不知天在水，满床清梦压星河。"
            it.write(testString.toByteArray())
            Context.MODE_APPEND
            it.flush()
            it.close()


        }

    }

    fun deleteTxt(view: View) {
        Log.i("MainActivity-->", "deleteTxt")

        val request = DeleteFileRequest.Builder()
            .setFile(File(Environment.DIRECTORY_DOWNLOADS + File.separator + "aa.txt"))
            .setFileType(FileType.FILE_TYPE_FILE)
            .build()
        val response = FileAccessManager.deleteFile(this, request)

        Log.i("MainActivity-->", "response:$response")
        val status=if (response) "delete success" else "delete Fail"
        Toast.makeText(this,status,Toast.LENGTH_SHORT).show()


    }


}