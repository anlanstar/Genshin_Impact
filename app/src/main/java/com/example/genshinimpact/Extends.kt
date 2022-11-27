package com.example.genshinimpact

import android.content.res.Resources
import android.util.TypedValue


val String.role
    get() = this.split("@")[0]
val String.num
    get() = this.split("@")[1]