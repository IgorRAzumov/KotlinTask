package com.example.kotlinlessons.ext

import java.text.SimpleDateFormat
import java.util.*


fun Date.format(format: String): String = SimpleDateFormat(format, Locale.getDefault()).format(this)