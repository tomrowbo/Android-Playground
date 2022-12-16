package com.tomsplayground.ui.camera

import android.content.ContentValues

sealed class CameraEvent

data class OnTakePhotoEvent(val contentValues: ContentValues) :
    CameraEvent()
