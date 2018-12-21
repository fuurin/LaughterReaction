package com.komatsu.laughterreaction

import android.util.Log

fun Any?.log() {
    Log.v("DEBUG", this.toString())
}