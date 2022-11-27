package com.example.genshinimpact.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.genshinimpact.adapter.ListAdapter
import com.example.genshinimpact.databinding.ActivityProgressBinding
import com.example.genshinimpact.entry.SpecificInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ProgressActivity : AppCompatActivity() {
    private var totalData = ArrayList<SpecificInfo>()
    private val fiveRoleData = ArrayList<SpecificInfo>()
    private val fourWeaponData = ArrayList<SpecificInfo>()
    private val fiveWeaponData = ArrayList<SpecificInfo>()
    private val fourRoleData = ArrayList<SpecificInfo>()
    private var residentPool = ArrayList<SpecificInfo>()
    private var weaponPool = ArrayList<SpecificInfo>()
    private var rolePool = ArrayList<SpecificInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val progressBinding = ActivityProgressBinding.inflate(layoutInflater)
        setContentView(progressBinding.root)
        filterData()
        fillDataToRecyclerView(progressBinding)
        progressBinding.btnEnterAnalysis.setOnClickListener() {
            var intent = Intent(this, SamplingAnalysisActivity::class.java)
            intent.putExtra("residentPool",Gson().toJson(residentPool))
            intent.putExtra("weaponPool",Gson().toJson(weaponPool))
            intent.putExtra("rolePool",Gson().toJson(rolePool))
            startActivity(intent)
        }
    }

    private fun filterData() {
        val residentPoolJson = intent.getStringExtra("residentPool")
        residentPool = Gson().fromJson<ArrayList<SpecificInfo>>(residentPoolJson, object : TypeToken<List<SpecificInfo>>(){}.type)
        Log.i("TAG", "filterData: ${residentPool.size}")
        val weaponPoolJson = intent.getStringExtra("weaponPool")
        weaponPool = Gson().fromJson<ArrayList<SpecificInfo>>(weaponPoolJson, object : TypeToken<List<SpecificInfo>>(){}.type)
        Log.i("TAG", "filterData: ${weaponPool.size}")
        val rolePoolJson = intent.getStringExtra("rolePool")
        rolePool = Gson().fromJson<ArrayList<SpecificInfo>>(rolePoolJson, object : TypeToken<List<SpecificInfo>>(){}.type)
        Log.i("TAG", "filterData: ${rolePool.size}")
        totalData.addAll(residentPool)
        totalData.addAll(weaponPool)
        totalData.addAll(rolePool)
        Log.i("TAG", "filterData: ${totalData.size}")
        Log.i("TAG", "filterData: ${residentPool.size+weaponPool.size+rolePool.size}")
        for (item in totalData) {
            if (item.rank_type == "5" && item.item_type == "角色") {
                fiveRoleData.add(item)
            } else if (item.rank_type == "4" && item.item_type == "角色") {
                fourRoleData.add(item)
            } else if (item.rank_type == "5" && item.item_type == "武器") {
                fiveWeaponData.add(item)
            } else if (item.rank_type == "4" && item.item_type == "武器") {
                fourWeaponData.add(item)
            }
        }
    }

    private fun fillDataToRecyclerView(progressBinding: ActivityProgressBinding) {
        progressBinding.rvFiveRole.layoutManager = LinearLayoutManager(this)
        progressBinding.rvFiveRole.adapter = ListAdapter(fiveRoleData)
        progressBinding.rvFiveWeapon.layoutManager = LinearLayoutManager(this)
        progressBinding.rvFiveWeapon.adapter = ListAdapter(fiveWeaponData)
        progressBinding.rvFourRole.layoutManager = LinearLayoutManager(this)
        progressBinding.rvFourRole.adapter = ListAdapter(fourRoleData)
        progressBinding.rvFourWeapon.layoutManager = LinearLayoutManager(this)
        progressBinding.rvFourWeapon.adapter = ListAdapter(fourWeaponData)
    }
}