package com.schoolfood.api

import android.widget.Toast
import com.schoolfood.MainActivity
import com.schoolfood.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*

class GetBalance(main: MainActivity): Runnable {
    private val context = main

    override fun run() {
        try {
            RestClient.instance?.getBalance(context.getString(R.string.jwt_token))?.enqueue(object : Callback<Response?> {
                override fun onResponse(
                    call: Call<Response?>?,
                    response: retrofit2.Response<Response?>
                ) {
                    val res: Response? = response.body()

                    if (res != null) {
                        if (res.error) {
                            Toast.makeText(context.applicationContext, res.msg, Toast.LENGTH_SHORT).show()
                            return
                        }

                        val path = context?.filesDir
                        val file = File(path, "balance.txt")
                        if (!file.exists()) file.createNewFile()
                        file.writeText(res.value.toString())

                    }

                }

                override fun onFailure(call: Call<Response?>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            })

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}