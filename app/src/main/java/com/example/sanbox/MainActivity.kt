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
import com.example.sanbox.fileutils.FileAccessManager
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

        test()

        logPath()

    }

    private fun logPath() {
        Log.i(TAG, "logPath")

        val p1=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).absolutePath
        val p2=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath
        val p3=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).absolutePath
        val p4=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath
        val p5=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).absolutePath
        Log.i(TAG, "p1 :$p1 ")
        Log.i(TAG, "p2 :$p2 ")
        Log.i(TAG, "p3 :$p3 ")
        Log.i(TAG, "p4 :$p4 ")
        Log.i(TAG, "p5 :$p5 ")


        val path=Environment.DIRECTORY_PICTURES + File.separator + "aa.jpg"
        val relativePath=path.substring(path.indexOf(Environment.DIRECTORY_PICTURES)+Environment.DIRECTORY_PICTURES.length,path.length)
        Log.i(TAG, "relativePath :$relativePath ")




    }

    private fun test() {


//        val path = this.getExternalFilesDir("")?.absolutePath/sdcard/SuperLivePlus/SpareList.txt
        val path =
            Environment.getExternalStorageDirectory().absolutePath + File.separator + "SuperLivePlus" + File.separator + "SpareList.txt"

        val file = File(path)
        if (!file.exists()) {
            file.createNewFile()
        }
        Log.i(TAG, "path:$path")

        val inputStream = FileInputStream(file)

        inputStream?.let {
            val result = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var length: Int
            while (it.read(buffer).also { length = it } != -1) {
                result.write(buffer, 0, length)
            }
            val str: String = result.toString(StandardCharsets.UTF_8.name())

            Log.i(TAG, "str:$str")
            it.close()

        }


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
        Log.i("MainActivity-->", "saveImage")
//
//        val newFileRequest = NewFileRequest.Builder()
//            .setFile(File(Environment.DIRECTORY_PICTURES + File.separator + "even/ba.jpg"))
//            .setFileType(FileType.FILE_TYPE_IMAGE)
//            .build()


        val path=Environment.getExternalStorageDirectory().absolutePath + File.separator+"SuperLivePlus"+ File.separator+"aa.jpg"
        val newFileRequest = NewFileRequest.Builder()
            .setFile(File(path))
            .setFileType(FileType.FILE_TYPE_IMAGE)
            .build()

        val response = FileAccessManager.newCreateFile(this, newFileRequest)
//        Log.i("MainActivity-->", "uri:${response?.uri}")
        Log.i("MainActivity-->", "uri:${response?.accessType}")


//        response?.uri?.let {
//            try {
//                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.th)
//                val outputStream = contentResolver.openOutputStream(it)
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
//                outputStream?.close()
//
//            }catch (e:Exception){
//
//            }
//
//        }



//
//
//        val path=this.cacheDir
//        val path2=this.getDir("files", MODE_PRIVATE).absolutePath
////        val path3=Environment.getDataDirectory()
//        val path3=Environment.getDataDirectory().absolutePath
//        val path4=this.filesDir.absolutePath
////        val path5=this.getf
//        Log.i("MainActivity-->","cacheDir:$path\n" +
//                "path2:$path2\n" +
//                "path3:$path3\n" +
//                "path4:$path4\n")
//
//
//        val inputStream=openFileInput("aa.txt")
//
//
//
//        val result = ByteArrayOutputStream()
//        val buffer = ByteArray(1024)
//        var length: Int
//        while (inputStream.read(buffer).also { length = it } != -1) {
//            result.write(buffer, 0, length)
//        }
//        val str: String = result.toString(StandardCharsets.UTF_8.name())
//
//        Log.i("MainActivity-->","str:$str")
//        inputStream.close()


    }

    fun saveTxt(view: View) {
        Log.i("MainActivity-->", "saveTxt")


        val newFileRequest = NewFileRequest.Builder()
            .setFile(File(Environment.DIRECTORY_DOWNLOADS + File.separator + "aa.txt"))
            .setFileType(FileType.FILE_TYPE_FILE)
            .build()

        val response = FileAccessManager.newCreateFile(this, newFileRequest)

        Log.i("MainActivity-->", "uri:${response?.uri}")

        response?.uri?.let {
            try {

                val testString = "西风吹老洞庭波，一夜湘君白发多。"
                val outputStream = contentResolver.openOutputStream(it)
                outputStream?.write(testString.toByteArray())
                outputStream?.close()
            } catch (e: Exception) {

            }
        }

    }

    fun readTxt(view: View) {

        Log.i("MainActivity-->", "readTxt")
//        queryTest()
//        queryImageCollection(this)
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


    }


}