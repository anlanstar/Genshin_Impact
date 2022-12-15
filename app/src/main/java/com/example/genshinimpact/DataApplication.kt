package com.example.genshinimpact

import android.app.Application
import com.example.genshinimpact.entry.SpecificInfo

class DataApplication : Application() {
    var residentPool: ArrayList<SpecificInfo> = ArrayList()
    var weaponPool: ArrayList<SpecificInfo> = ArrayList()
    var rolePool: ArrayList<SpecificInfo> = ArrayList()
    fun getTotalData(): ArrayList<SpecificInfo> {
        var total = ArrayList<SpecificInfo>()
        total.addAll(residentPool)
        total.addAll(weaponPool)
        total.addAll(rolePool)
        return total
    }
}