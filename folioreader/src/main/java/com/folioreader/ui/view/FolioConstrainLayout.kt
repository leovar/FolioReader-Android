package com.folioreader.ui.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat

class FolioConstrainLayout : CoordinatorLayout {

    companion object {
        @JvmField
        val LOG_TAG: String = FolioConstrainLayout::class.java.simpleName
    }

    var navigationBarHeight: Int = 0
    var insets: Rect? = null

    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {

        ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
            Log.v(FolioConstrainLayout.LOG_TAG, "-> onApplyWindowInsets")
            // For API level 20 and above

            this.insets = Rect(
                    insets.systemWindowInsetLeft, insets.systemWindowInsetTop,
                    insets.systemWindowInsetRight, insets.systemWindowInsetBottom
            )

            navigationBarHeight = insets.systemWindowInsetBottom

            setMargins(
                    insets.systemWindowInsetLeft, insets.systemWindowInsetBottom,
                    insets.systemWindowInsetRight
            )
            insets
        }
    }

    override fun fitSystemWindows(insets: Rect?): Boolean {
        Log.v(FolioConstrainLayout.LOG_TAG, "-> fitSystemWindows")
        // For API level 19 and below

        this.insets = Rect(insets)

        navigationBarHeight = insets!!.bottom

        setMargins(insets.left, insets.bottom, insets.right)
        return super.fitSystemWindows(insets)
    }

    private fun setMargins(left: Int, bottom: Int, right: Int) {

        val marginLayoutParams = layoutParams as MarginLayoutParams
        marginLayoutParams.leftMargin = left
        marginLayoutParams.bottomMargin = bottom
        marginLayoutParams.rightMargin = right
        layoutParams = marginLayoutParams
    }

    fun setTopMargin(bottom: Int) {
        val marginLayoutParams = layoutParams as MarginLayoutParams
        marginLayoutParams.bottomMargin = bottom
        layoutParams = marginLayoutParams
    }

}