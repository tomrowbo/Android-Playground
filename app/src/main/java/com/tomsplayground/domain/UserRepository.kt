package com.tomsplayground.domain

import com.tomsplayground.domain.model.User
import javax.inject.Inject

class UserRepository @Inject constructor() {

    fun findUserByUsername(username: String?): User {
        //TODO: Add data layer
        return User("tom.rowbo","Tom Rowbo", "https://w0.peakpx.com/wallpaper/979/89/HD-wallpaper-purple-smile-design-eye-smily-profile-pic-face.jpg", listOf("1", "2"))
    }
}