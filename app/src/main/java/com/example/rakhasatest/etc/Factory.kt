package com.example.rakhasatest.etc

import android.content.Context

interface Factory<T> {
    fun create(context: Context): T
}