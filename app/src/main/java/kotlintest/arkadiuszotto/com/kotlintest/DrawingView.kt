package kotlintest.arkadiuszotto.com.kotlintest

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @author arekotto
 */
class DrawingView : View {

    companion object {
        private const val TOUCH_TOLERANCE = 4f
    }

    private lateinit var bitmap: Bitmap
    private lateinit var canvas: Canvas
    private val path = Path()
    private val bitmapPaint = Paint(Paint.DITHER_FLAG)
    lateinit var paint: Paint

    private var currX = 0f
    private var currY = 0f

    private var h = 0
    private var w = 0

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        this.h = h
        this.w = w

        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(bitmap, 0f, 0f, bitmapPaint)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchStart(x, y)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                touchMove(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                touchUp()
                invalidate()
            }
        }
        return true
    }

    private fun touchStart(x: Float, y: Float) {
        path.reset()
        path.moveTo(x, y)
        currX = x
        currY = y
    }

    private fun touchMove(x: Float, y: Float) {
        val dx = Math.abs(x - this.currX)
        val dy = Math.abs(y - this.currY)
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.quadTo(this.currX, this.currY, (x + this.currX) / 2, (y + this.currY) / 2)
            currX = x
            currY = y
        }
    }

    private fun touchUp() {
        path.lineTo(currX, currY)
        canvas.drawPath(path, paint)
        path.reset()
    }

    fun clear() {
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)
        invalidate()
    }
}