package com.example.sanbox.fileutils.responce

import android.net.Uri
import com.example.sanbox.fileutils.constants.FileConstants
import java.io.File

/**
 * Author: Even
 * Create time: 2021/8/13 16:30
 * Des:文件操作返回
 */
class FileResponse {
    var result: Boolean = false//结果
    var uri: Uri? = null
    var file: File? = null
    var accessType = FileConstants.TYPE_FILE//返回类型，用于区分是普通读写还是Media接口读写，决定文件操作是通过file 还是uri


}