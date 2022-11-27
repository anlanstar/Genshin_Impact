package com.example.genshinimpact.http


import com.example.genshinimpact.entry.CardPool
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface GenshinService {

    @GET("getGachaLog?win_mode=fullscreen&authkey_ver=1&sign_type=2&auth_appid=webview_gacha&init_type=301&gacha_id=b4ac24d133739b7b1d55173f30ccf980e0b73fc1&lang=zh-cn&device_type=mobile&game_version=CNRELiOS3.0.0_R10283122_S10446836_D10316937&plat_type=ios&game_biz=hk4e_cn&size=20&region=cn_gf01&timestamp=1664481732")
    fun listRepos(
        @Query("authkey") authkey: String,
        @Query("gacha_type") gachaType: Int,
        @Query("page") page: Int,
        @Query("end_id") endId: String
    ): Call<CardPool>
}