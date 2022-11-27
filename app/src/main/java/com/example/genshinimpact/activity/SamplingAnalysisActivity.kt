package com.example.genshinimpact.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.genshinimpact.adapter.RoleAnaylAdapter
import com.example.genshinimpact.databinding.ActivitySamplingAnalysisBinding
import com.example.genshinimpact.entry.SpecificInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SamplingAnalysisActivity : AppCompatActivity() {
    private var totalData = ArrayList<SpecificInfo>()
    private var residentPool = ArrayList<SpecificInfo>()
    private var weaponPool = ArrayList<SpecificInfo>()
    private var rolePool = ArrayList<SpecificInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val analysisBinding = ActivitySamplingAnalysisBinding.inflate(layoutInflater)
        setContentView(analysisBinding.root)
        val residentPoolJson = intent.getStringExtra("residentPool")
        residentPool = Gson().fromJson<ArrayList<SpecificInfo>>(
            residentPoolJson,
            object : TypeToken<List<SpecificInfo>>() {}.type
        )
        val weaponPoolJson = intent.getStringExtra("weaponPool")
        weaponPool = Gson().fromJson<ArrayList<SpecificInfo>>(
            weaponPoolJson,
            object : TypeToken<List<SpecificInfo>>() {}.type
        )
        val rolePoolJson = intent.getStringExtra("rolePool")
        rolePool = Gson().fromJson<ArrayList<SpecificInfo>>(
            rolePoolJson,
            object : TypeToken<List<SpecificInfo>>() {}.type
        )
        totalData.addAll(residentPool)
        totalData.addAll(weaponPool)
        totalData.addAll(rolePool)
        analysisBinding.tvUid.text = totalData[0].uid
        analysisBinding.rvRoleAnalysisData.layoutManager = LinearLayoutManager(this)
        analysisBinding.rvRoleAnalysisData.adapter = RoleAnaylAdapter(analysisData(rolePool))

        analysisBinding.rvResidentAnalysisData.layoutManager = LinearLayoutManager(this)
        analysisBinding.rvResidentAnalysisData.adapter = RoleAnaylAdapter(analysisData(residentPool))

        analysisBinding.rvWeaponAnalysisData.layoutManager = LinearLayoutManager(this)
        analysisBinding.rvWeaponAnalysisData.adapter = RoleAnaylAdapter(analysisData(weaponPool))
    }

    private fun analysisData(tmpData: ArrayList<SpecificInfo>): ArrayList<String> {
        val roleStatistics = ArrayList<String>()
        var number = 1
        for (i in tmpData.size - 1 downTo 0) {
            if (tmpData[i].rank_type == "5") {
                roleStatistics.add("${tmpData[i].name}@$number")
                number = 0
            }
            number++
        }
        roleStatistics.add("已垫@${--number}")
        return roleStatistics
    }
}