package com.hgc.handlerleak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import java.lang.ref.WeakReference

class NoLeakActivity : AppCompatActivity() {

    private var mHandler: NoLeakHandler = NoLeakHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_leak)
        val msg = Message.obtain()
        mHandler.sendMessageDelayed(msg, 10 * 1000)
    }


    class NoLeakHandler(activity: NoLeakActivity) : Handler() {
        private var mActivity: WeakReference<NoLeakActivity> = WeakReference(activity)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            mActivity.get()?.let {
                Toast.makeText(mActivity.get(), "test", Toast.LENGTH_SHORT).show()
            }
        }
    }

}