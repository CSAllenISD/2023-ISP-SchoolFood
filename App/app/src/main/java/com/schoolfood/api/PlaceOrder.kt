package com.schoolfood.api

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.schoolfood.MainActivity
import com.schoolfood.R
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


class PlaceOrder(main: MainActivity, orderJSON: String): Runnable {
    private val context = main
    private val orderJSON = orderJSON

    override fun run() {
        try {
            val body: RequestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                orderJSON
            )

            RestClient.instance?.placeOrder(context.getString(R.string.jwt_token), body)?.enqueue(object :
                Callback<Response?> {
                override fun onResponse(
                    call: Call<Response?>?,
                    response: retrofit2.Response<Response?>
                ) {
                    val res: Response? = response.body()
                    println("Res: $res")

                    if (res != null) {
                        if (res.error) {
                            val handler = Handler(Looper.getMainLooper())
                            val runnable = Runnable {
                                Toast.makeText(context.applicationContext, res.msg, Toast.LENGTH_SHORT).show()
                            }
                            handler.post(runnable)
                            return
                        }

                        val path = context?.filesDir
                        val file = File(path, "balance.txt")
                        if (!file.exists()) file.createNewFile()
                        file.writeText(res.value.toString())

                        val handler = Handler(Looper.getMainLooper())
                        val runnable = Runnable {
                            Toast.makeText(context.applicationContext, "Your order has been placed!", Toast.LENGTH_LONG).show()
                        }
                        handler.post(runnable)
                    }

                }

                override fun onFailure(call: Call<Response?>, t: Throwable) {
                    val handler = Handler(Looper.getMainLooper())
                    val runnable = Runnable {
                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                    }
                    handler.post(runnable)
                }
            })

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}