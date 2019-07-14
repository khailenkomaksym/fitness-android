package com.fitness.athome

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class IntroduceActivity : AppCompatActivity() {

    private lateinit var imgBg : ImageView
    private lateinit var imgFg : ImageView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduce)

        imgBg = findViewById(R.id.img_bg)
        imgFg = findViewById(R.id.img_fg)

        imgFg.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event?.action == MotionEvent.ACTION_UP) {
                    val x : Int = event.getX().toInt()
                    val y : Int = event.getY().toInt()
                    val color = getColour(imgFg, x, y)

                    if (color == Color.parseColor("#ff0000")) {
                        Toast.makeText(this@IntroduceActivity, "Press", Toast.LENGTH_LONG).show();
                    } else if (color == Color.parseColor("#00f6ff")) {
                        Toast.makeText(this@IntroduceActivity, "Biceps", Toast.LENGTH_LONG).show();
                    } else if (color == Color.parseColor("#fff600")) {
                        Toast.makeText(this@IntroduceActivity, "Legs", Toast.LENGTH_LONG).show();
                    }
                }
                return true

                //return v?.onTouchEvent(event) ?: true
            }
        })
    }

    private fun getColour(img : ImageView, x: Int, y: Int): Int {
        val d : Drawable = getResources().getDrawable(R.drawable.muscles_background)
        val b1 = (d as BitmapDrawable).bitmap
        //scale loaded bitmap to same resolution as visible view
        val hotspots = Bitmap.createScaledBitmap(b1, img.width, img.height, false)
        return hotspots.getPixel(x, y)
    }
}
