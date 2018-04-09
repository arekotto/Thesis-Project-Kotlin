package kotlintest.arkadiuszotto.com.kotlintest.Fragment


import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import kotlintest.arkadiuszotto.com.kotlintest.DrawingView

import kotlintest.arkadiuszotto.com.kotlintest.R


/**
 * A simple [Fragment] subclass.
 */
class DrawFragment : Fragment() {

    companion object {
        private const val PAINT_SIZE_SMALL = 2
        private const val PAINT_SIZE_MEDIUM = 4
        private const val PAINT_SIZE_LARGE = 6
    }

    private lateinit var drawingView: DrawingView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val inflate = inflater.inflate(R.layout.fragment_draw, container, false)

        drawingView = inflate.findViewById(R.id.drawing_view)
        drawingView.paint = createNewPaintWithWidth(PAINT_SIZE_SMALL)

        inflate.findViewById<Button>(R.id.button_clear).setOnClickListener { drawingView.clear() }


        inflate.findViewById<RadioButton>(R.id.radio_small).setOnClickListener {
            drawingView.paint = createNewPaintWithWidth(PAINT_SIZE_SMALL)
        }

        inflate.findViewById<RadioButton>(R.id.radio_medium).setOnClickListener {
            drawingView.paint = createNewPaintWithWidth(PAINT_SIZE_MEDIUM)
        }

        inflate.findViewById<RadioButton>(R.id.radio_large).setOnClickListener {
            drawingView.paint = createNewPaintWithWidth(PAINT_SIZE_LARGE)
        }
        return inflate
    }

    private fun createNewPaintWithWidth(width: Int): Paint {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.isDither = true
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = width.toFloat()
        return paint
    }
}
