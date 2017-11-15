package com.ep.expression.expressions

import android.graphics.Color
import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.ComponentLayout
import com.facebook.litho.Row
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop

/**
 * Created by andriusstep on 08/11/2017.
 */

@LayoutSpec
class ExpressionItemSpec {

    companion object {

        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(context: ComponentContext): ComponentLayout {

            return Row.create(context).build()
        }
    }

}