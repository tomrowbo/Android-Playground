package com.tomsplayground.ui.camera

import android.content.ContentValues
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tomsplayground.ui.camera.CameraConstants.Companion.FILENAME_FORMAT
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
) : ViewModel() {

    val event: MutableLiveData<CameraEvent> = MutableLiveData()

    fun onTakePhoto() {
        val contentValues = getContentValues()
        event.value = OnTakePhotoEvent(contentValues)
    }

    private fun getContentValues(): ContentValues {
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.UK)
            .format(System.currentTimeMillis())
        return ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }
    }

    fun onError(exc: Exception) {
        when (exc) {
            is ImageCaptureException -> {
                Log.e(CameraConstants.TAG, "Photo capture failed: ${exc.message}", exc)
            }
            else -> {
                Log.e(CameraConstants.TAG, "Error: ${exc.message}", exc)
            }
        }
    }

    fun onSaveImage(output: ImageCapture.OutputFileResults) {
        val msg = "Photo capture succeeded: ${output.savedUri}"
        Log.d(CameraConstants.TAG, msg)
    }

}