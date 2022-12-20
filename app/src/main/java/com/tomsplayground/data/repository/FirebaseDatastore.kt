package com.tomsplayground.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.room.Room
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.component1
import com.google.firebase.storage.ktx.component2
import com.google.firebase.storage.UploadTask
import com.tomsplayground.data.database.TomsPlaygroundDb
import javax.inject.Inject

class FirebaseDatastore @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) {
    fun uploadImage(uri: Uri) {
        //TODO: Store this better
        val imageRef = storage.reference.child("images/${System.currentTimeMillis()}")
        val uploadTask = imageRef.putFile(uri)

        uploadTask.addOnProgressListener { (bytesTransferred, totalByteCount) ->
            val progress = (100.0 * bytesTransferred) / totalByteCount
            Log.d(TAG, "Upload is $progress% done")
        }.addOnPausedListener {
            Log.d(TAG, "Upload is paused")
        }.addOnFailureListener {
            // Handle unsuccessful uploads
            Log.w(TAG, "Unsuccessful upload")
        }.addOnCompleteListener {
            // Handle successful uploads on complete
            if (uploadTask.isSuccessful) {
                imageRef.downloadUrl
            }
        }
    }

    companion object {
        const val TAG = "FirebaseDatastore"
    }
}
