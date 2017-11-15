package com.ep.expression.expressions

import android.graphics.Color
import android.util.Log
import com.ep.expression.expressions.ExpressionItem
import com.facebook.litho.*
import com.facebook.yoga.YogaEdge
import org.json.JSONObject

class Expression(contextId:String, jsonObject: JSONObject) {

    var contextId = contextId
    var jsonObject = jsonObject
}

fun Expression.height() : Float? {
    return 0f
}

fun Expression.backgroundColor() : Int? {

    jsonObject.getString("backgroundColor")?.apply {
        Log.d("Expression", this)
        return  Color.parseColor("#"+this)
    }
    return null
}

fun Expression.cornerRadius() : Double? {
    jsonObject.getDouble("cornerRadius").apply {
        return  this
    }
    return  null
}

fun Expression.subNode(context:ComponentContext) : Component<ExpressionItem>? {

    return null
}

fun Expression.node(context:ComponentContext) : Component<ExpressionItem>? {
    Log.d("json", jsonObject.toString())

    jsonObject.getJSONObject("spec").apply {

        this.getString("type").apply {

            when (this) {
                "stack" -> {

                }
            }
        }
    }


    var item = ExpressionItem.create(context)

    height()?.apply {
        item = item.heightDip(this)
    }

    backgroundColor()?.apply {
        item = item.backgroundColor(this)
    }


    cornerRadius()?.apply {
        item = item.border(
                Border.create(context)
                        .color(YogaEdge.ALL, Color.WHITE)
                        .widthDip(YogaEdge.ALL, 1)
                        .cornerEffect(this.toFloat())
                        .build()
        )
    }



    return item
            .heightDip(100f)
            .widthDip(100f)
            .build()
}

class