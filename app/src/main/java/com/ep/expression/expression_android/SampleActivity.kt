package com.ep.expression.expression_android

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.OrientationHelper
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout

import com.ep.expression.expression_android.API.APIClient
import com.ep.expression.expressions.Expression
import com.ep.expression.expressions.componentContext
import com.ep.expression.expressions.node

import com.facebook.litho.Component
import com.facebook.litho.LithoView

import com.facebook.litho.widget.LinearLayoutInfo
import com.facebook.litho.widget.Recycler
import com.facebook.litho.widget.RecyclerBinder
import com.facebook.soloader.SoLoader
import org.json.JSONObject

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("b", this.contentBinder.toString())

        SoLoader.init(this, false);
        setContentView(linearLayout)
        linearLayout.addView(recyclerView)
        APIClient.fetchSample ({ success, jsonResponse ->
            for (i in 0 .. 3) {
                insetItems(JSONObject(jsonResponse))
            }
        })
    }

    fun insetItems(jsonObject: JSONObject) {
        contentBinder.appendItem(Expression("abcd", jsonObject)
                .node(this.componentContext()))

    }


    val linearLayout by lazy {
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setVerticalGravity(Gravity.END)
        layout.setBackgroundColor(Color.BLACK)
        layout
    }

    val recyclerView by lazy {
        LithoView.create(this, recycleComponent)
    }

    val recycleComponent: Component<Recycler> by lazy {
        Recycler.create(this.componentContext())
                .binder(contentBinder)
                .build()
    }

    val contentBinder: RecyclerBinder by lazy {
        RecyclerBinder.Builder()
                .layoutInfo(LinearLayoutInfo(this.componentContext(), OrientationHelper.VERTICAL, false))
                .build(componentContext());

    }
}
