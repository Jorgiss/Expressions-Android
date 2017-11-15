package com.ep.expression.expressions

import android.content.Context
import com.facebook.litho.ComponentContext

/**
 * Created by andriusstep on 08/11/2017.
 */
fun Context.componentContext(): ComponentContext {
    return ComponentContext(this)
}