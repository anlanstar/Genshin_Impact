package com.example.genshinimpact.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.genshinimpact.DataApplication
import com.example.genshinimpact.adapter.RoleAnaylAdapter
import com.example.genshinimpact.databinding.ActivitySamplingAnalysisBinding
import com.example.genshinimpact.entry.SpecificInfo

class SamplingAnalysisActivity : AppCompatActivity() {
    private var totalData = ArrayList<SpecificInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val analysisBinding = ActivitySamplingAnalysisBinding.inflate(layoutInflater)
        setContentView(analysisBinding.root)
        analysisBinding.tvUid.text = (application as DataApplication).residentPool[0].uid
        analysisBinding.rvRoleAnalysisData.layoutManager = LinearLayoutManager(this)
        analysisBinding.rvRoleAnalysisData.adapter =
            RoleAnaylAdapter(analysisData((application as DataApplication).rolePool))

        analysisBinding.rvResidentAnalysisData.layoutManager = LinearLayoutManager(this)
        analysisBinding.rvResidentAnalysisData.adapter =
            RoleAnaylAdapter(analysisData((application as DataApplication).residentPool))

        analysisBinding.rvWeaponAnalysisData.layoutManager = LinearLayoutManager(this)
        analysisBinding.rvWeaponAnalysisData.adapter =
            RoleAnaylAdapter(analysisData((application as DataApplication).weaponPool))
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