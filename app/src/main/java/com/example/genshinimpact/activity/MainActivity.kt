package com.example.genshinimpact.activity

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.genshinimpact.DataApplication
import com.example.genshinimpact.LoadingDialog
import com.example.genshinimpact.databinding.ActivityMainBinding
import com.example.genshinimpact.entry.CardPool
import com.example.genshinimpact.entry.SpecificInfo
import com.example.genshinimpact.http.DataRequest
import com.google.gson.Gson


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var queryParameter: String
    lateinit var dialog: Dialog
    private lateinit var applicationA: DataApplication
    lateinit var mainBinding: ActivityMainBinding
    private val handler = object : Handler(Looper.getMainLooper()) {
        var number = 1
        override fun handleMessage(msg: Message) {
            if (msg.what == 1) {
                applicationA.residentPool.addAll((msg.obj as CardPool).data.list)
            }
            if (msg.what == 2) {
                applicationA.weaponPool.addAll((msg.obj as CardPool).data.list)
            }
            if (msg.what == 3) {
                applicationA.rolePool.addAll((msg.obj as CardPool).data.list)
            }
            if (msg.what == 4) {
                if (number++ >= 4) {
                    Log.i("TAG", "handleMessage: $number")
                    mainBinding.btnGetData.visibility = View.GONE
                    mainBinding.btnGetNum.visibility = View.VISIBLE
                    dialog.dismiss()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.btnGetData.setOnClickListener(this)
        mainBinding.btnGetNum.setOnClickListener(this)
        mainBinding.btnGetUrl.setOnClickListener(this)
    }

    private fun initData() {
//        residentPool = ArrayList()
//        weaponPool = ArrayList()
//        rolePool = ArrayList()

        val uri = Uri.parse(mainBinding.evLink.text.toString())
        queryParameter = uri.getQueryParameter("authkey").toString()
    }

    override fun onStart() {
        super.onStart()
        applicationA = application as DataApplication
    }

    override fun onClick(view: View?) {
        if (view == mainBinding.btnGetUrl) {
            initData()
            if (queryParameter != "null") {
                mainBinding.btnGetNum.visibility = View.GONE
                mainBinding.btnGetData.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "url??????", Toast.LENGTH_SHORT).show()
            }
        } else if (view == mainBinding.btnGetData) {
            val loadBuilder = LoadingDialog.Builder(this)
                .setMessage("?????????...")
                .setCancelable(true)
                .setCancelOutside(false)
            dialog = loadBuilder.create()
            dialog.show()
            Thread { DataRequest.settingNetwork(queryParameter, 100, handler, 1) }.start()
            Thread { DataRequest.settingNetwork(queryParameter, 200, handler, 1) }.start()
            Thread { DataRequest.settingNetwork(queryParameter, 302, handler, 2) }.start()
            Thread { DataRequest.settingNetwork(queryParameter, 301, handler, 3) }.start()

            mainBinding.btnGetData.visibility = View.GONE

        } else if (view == mainBinding.btnGetNum) {
            Log.i("TAG", "applicationA.residentPool: ${applicationA.residentPool.size}")
            mainBinding.btnGetUrl.visibility = View.VISIBLE
            mainBinding.btnGetData.visibility = View.GONE
            mainBinding.btnGetNum.visibility = View.GONE

//            val residentPoolTmp = ArrayList<SpecificInfo>()
//            val weaponPoolTmp = ArrayList<SpecificInfo>()
//            val rolePoolTmp = ArrayList<SpecificInfo>()

//            for (i in residentPool) {
//                residentPoolTmp.addAll(i.data.list)
//            }
//            for (i in weaponPool) {
//                weaponPoolTmp.addAll(i.data.list)
//            }
//            for (i in rolePool) {
//                rolePoolTmp.addAll(i.data.list)
//            }

//            Log.i("TAG", "?????????: ${residentPoolTmp.size}")
//            Log.i("TAG", "?????????: ${weaponPoolTmp.size}")
//            Log.i("TAG", "?????????: ${rolePoolTmp.size}")

            val intent = Intent(this, ProgressActivity::class.java)
//            intent.putExtra("residentPool", Gson().toJson(residentPoolTmp))
//            intent.putExtra("weaponPool", Gson().toJson(weaponPoolTmp))
//            intent.putExtra("rolePool", Gson().toJson(rolePoolTmp))
            startActivity(intent)
        }
    }
}

