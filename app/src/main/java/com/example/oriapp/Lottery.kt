package com.example.oriapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class Lottery constructor(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mOutterPaint = Paint()
    private val mPath = Path()
    private var mCanvas: Canvas? = null
    private var mBitmap: Bitmap? = null

    private var mLastX = 0
    private var mLastY = 0

    private var message //中奖信息
            : String? = null
    private var mBackground //文字背景矩形大小
            : Rect? = null
    private val messagePaint = Paint() //文字画笔

    private val isClear = false

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888) //以获得的宽高创建一个32位的bitmap
        mCanvas = Canvas(mBitmap!!)

        mCanvas!!.drawColor(Color.GREEN) //设置画布的颜色为绿色


        mBackground = Rect()
        message = "恭喜中奖，3万元!"
        messagePaint.color = Color.RED
        messagePaint.isAntiAlias = true
        messagePaint.style = Paint.Style.STROKE
        message?.length?.let { messagePaint.getTextBounds(message, 0, it, mBackground) }
        messagePaint.textSize = 80F
        // 设置画笔
        mOutterPaint.color = Color.BLUE
        mOutterPaint.isAntiAlias = true //使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢
        mOutterPaint.isDither = true //图像抖动处理,会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        mOutterPaint.style = Paint.Style.STROKE
        mOutterPaint.strokeJoin = Paint.Join.ROUND
        mOutterPaint.strokeCap = Paint.Cap.ROUND
        mOutterPaint.strokeWidth = 60f
        messagePaint.color = Color.RED
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event!!.action
        val x = event.x.toInt() //获得x坐标
        val y = event.y.toInt() //获得y坐标
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                mLastX = x
                mLastY = y
                mPath.moveTo(mLastX.toFloat(), mLastY.toFloat())
            }
            MotionEvent.ACTION_MOVE -> {
                mPath.lineTo(x.toFloat(), y.toFloat())
                mLastX = x
                mLastY = y
            }
            else -> {
            }
        }
        invalidate() //刷新View，回调onDraw方法
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        message?.let {
            canvas?.drawText(
                it,
                (mBackground?.width()?.div(2)?.let { it1 -> mBitmap?.width?.div(2)?.minus(it1) })!!.toFloat(),
                (measuredHeight / 2 + mBackground!!.height() / 2).toFloat(),
                messagePaint
            )
        };
        mOutterPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        mCanvas?.drawPath(mPath, mOutterPaint);
        mBitmap?.let { canvas?.drawBitmap(it, 0F, 0F, null) };
    }
}