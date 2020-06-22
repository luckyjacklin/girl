package me.jack.demo.girl.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.Dimension
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Jack on 2020/6/20.
 */
class RecyclerViewDivider(
    @param:Dimension private val dividerSize: Int,
    @ColorInt private val dividerColor: Int,
    private val orientation: Int
) : RecyclerView.ItemDecoration() {

    private val paint: Paint = Paint().also {
        it.color = dividerColor
        it.style = Paint.Style.FILL
    }

    constructor(
        context: Context,
        @DimenRes dividerSizeResId: Int,
        @ColorRes dividerColorResId: Int,
        orientation: Int = RecyclerView.VERTICAL
    ) : this(
        context.resources.getDimensionPixelSize(dividerSizeResId),
        ContextCompat.getColor(context, dividerColorResId),
        orientation
    )

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (orientation == RecyclerView.HORIZONTAL) {
            outRect.set(0, 0, dividerSize, 0)
        } else {
            outRect.set(0, 0, 0, dividerSize)
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        if (orientation == RecyclerView.HORIZONTAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    private fun drawVertical(c: Canvas, parent: RecyclerView) {
        (0 until parent.childCount).forEach { i ->
            val child = parent.getChildAt(i)
            c.drawRect(
                child.right.toFloat(),
                child.top.toFloat(),
                (child.right + dividerSize).toFloat(),
                child.bottom.toFloat(),
                paint
            )
        }
    }

    private fun drawHorizontal(c: Canvas, parent: RecyclerView) {
        (0 until parent.childCount).forEach { i ->
            val child = parent.getChildAt(i)
            c.drawRect(
                child.left.toFloat(),
                child.bottom.toFloat(),
                child.right.toFloat(),
                (child.bottom + dividerSize).toFloat(),
                paint
            )
        }
    }


}