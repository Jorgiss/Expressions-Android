package com.ep.expression.expression_android.API

import APIResponseHandler
import JSONObjectResponseHandler
import ResponseHandler
import android.util.Log
import okhttp3.*
import org.json.JSONObject
import timber.log.Timber
import java.io.Console
import java.io.IOException
import java.util.logging.Logger

/**
 * Created by andriusstep on 08/11/2017.
 */
class APIClient {
    companion object {
        val environemtnURL: String = "https://s3.eu-west-2.amazonaws.com/expression-framework-templates/"

        fun fetchSample(completion: JSONObjectResponseHandler) {

            val url: String = environemtnURL + "1.json"

            Request.Builder()
                    .get()
                    .url(url)
                    .build()
                    .perform(APIResponseCallback { success, jsonResponse ->
                        if (success) {
                            jsonResponse?.apply {
                                completion(success, this)
                            }
                        }
                    })
        }
    }
}


fun Request.perform(completion: APIResponseCallback) {
    val client = OkHttpClient()
    client.newCall(this).enqueue(completion)
}

interface Mappable<E> {

    fun map(item: JSONObject): E
}

class APIResponseCallback : Callback {

    var completion: APIResponseHandler

    constructor(completion: APIResponseHandler) {
        this.completion = completion
    }

    override fun onFailure(call: Call?, e: IOException?) {

        completion(false, null)
    }

    override fun onResponse(call: Call?, response: Response?) {

        when(response?.code()){
            200 -> completion(true, response?.body()?.string())
            else -> completion(false, response?.body()?.string())
        }
    }
}