package com.schoolfood.api

import android.content.ContentValues.TAG
import android.os.AsyncTask
import android.util.Log
import java.io.BufferedOutputStream
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class GetTokenTask : AsyncTask<String,Integer,String>() {

    // All your networking logic
    // should be here
    override fun doInBackground(vararg params: String): String {
        var toReturn = ""

        val url: String = params[0]
        val data: String = params[1]
        try {
            val url = URL(url)
            val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            // setting the  Request Method Type
            httpURLConnection.requestMethod = "POST"
            // adding the headers for request
            httpURLConnection.setRequestProperty("Content-Type", "application/json")
            try {
                //to tell the connection object that we will be wrting some data on the server and then will fetch the output result
                httpURLConnection.doOutput = true
                // this is used for just in case we don't know about the data size associated with our request
                httpURLConnection.setChunkedStreamingMode(0)

                // to write tha data in our request
                val outputStream: OutputStream =
                    BufferedOutputStream(httpURLConnection.outputStream)
                val outputStreamWriter = OutputStreamWriter(outputStream)
                outputStreamWriter.write(data)
                outputStreamWriter.flush()
                outputStreamWriter.close()

                // to log the response code of your request
                Log.d(
                    TAG,
                    "MyHttpRequestTask doInBackground : " + httpURLConnection.responseCode
                )

                toReturn = httpURLConnection.responseMessage

                // to log the response message from your server after you have tried the request.
                Log.d(
                    TAG,
                    "MyHttpRequestTask doInBackground : " + httpURLConnection.responseMessage
                )
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                // this is done so that there are no open connections left when this task is going to complete
                httpURLConnection.disconnect()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return toReturn
    }
}