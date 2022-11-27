package com.example.genshinimpact.http

import android.os.Handler
import android.os.Message
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataRequest {
    companion object {

        @JvmStatic
        fun settingNetwork(a: String, gachaType: Int, handler: Handler, what: Int) {
            val flag = true
            var endId = "0"
            val retrofit = Retrofit.Builder().let {
                it.addConverterFactory(GsonConverterFactory.create())
                it.baseUrl("https://hk4e-api.mihoyo.com/event/gacha_info/api/")
                it.build()
            }
            val service = retrofit.create(GenshinService::class.java)
            while (flag) {
                val call = service.listRepos(a, gachaType, 1, endId)
                val response = call.execute()
                val message = Message()
                if (!response.isSuccessful) break
                val tmp = response.body()
                if (tmp?.message == "visit too frequently") continue
                if (tmp?.message == "authkey error") continue
                if (tmp?.message == "authkey timeout") {
                    break
                }
                if (tmp?.data?.list != null && tmp.data.list.isEmpty()) {
                    handler.sendEmptyMessage(4)
                    break
                }
                if (tmp != null && tmp.data.list.isNotEmpty()) {
                    endId = tmp.data.list[tmp.data.list.size - 1].id
                }
                message.what = what
                message.obj = tmp
                handler.sendMessage(message)
            }
        }
    }
}