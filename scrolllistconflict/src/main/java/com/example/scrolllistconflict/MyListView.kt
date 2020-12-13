package com.example.scrolllistconflict

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ListView


class MyListView(context: Context?, attrs: AttributeSet?) : ListView(context, attrs) {


    private var mLastY = 0f

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                super.onInterceptTouchEvent(ev)
                //不允许上层viewGroup拦截事件.
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE ->
                if (computeVerticalScrollOffset() == 0 && ev.y - mLastY > 0) {
                    parent.requestDisallowInterceptTouchEvent(false)
                } else if (computeVerticalScrollExtent() + computeVerticalScrollOffset() >= computeVerticalScrollRange() && ev.y - mLastY < 0) {
                    parent.requestDisallowInterceptTouchEvent(false)
                } else {
                    parent.requestDisallowInterceptTouchEvent(true)
                }
            MotionEvent.ACTION_UP ->
                parent.requestDisallowInterceptTouchEvent(true)
        }
        mLastY = ev?.y ?: 0f
        return super.dispatchTouchEvent(ev)
    }
}


