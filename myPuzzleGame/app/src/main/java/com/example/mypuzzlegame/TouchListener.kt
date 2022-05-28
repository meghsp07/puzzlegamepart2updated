package com.example.mypuzzlegame

import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout

class TouchListener(private val activity: PuzzlePiece): View.OnTouchListener
{
    private var xDelta=0f
    private var yDelta=0f

    override fun onTouch(p0: View?, motionEvent: MotionEvent?): Boolean {
       val x = motionEvent!!.rawX
        val y = motionEvent.rawY
        val tolerance=Math.sqrt(
            Math.pow(view!!.width.toDouble(),2.0)+
                    Math.pow(view.height.toDouble(),2.0))/10

        val piece = view as PuzzlePiece
        if(!piece.canMove){
            return  true

        }
        val lParams=view.LayoutParams as RelativeLayout.LayoutParams
        when(motionEvent.action and motionEvent.ACTION_MASK){
            MotionEvent.ACTION_DOWN ->{
                xDelta=x - lParams.leftMargin
                yDelta = y-lParams.topMargin
                piece.bringToFront()
            }
            MotionEvent.ACTION_MASK ->{
                lParams.leftMargin=(x-xDelta).toInt()
                lParams.leftMargin=(y-yDelta).toInt()
                view.layoutParams=lParams
            }
            motionEvent.ACTION_UP->{
                val xDiff = StrictMath.abs(
                    a:piece.xCoord - lParams.leftMargin

                )
                val yDiff = StrictMath.abs(
                    a:piece.yCoord - lParams.leftMargin

                )
                if(xDiff<=tolerance && yDiff<=tolerance){
                    lParams.leftMargin=piece.xCoord
                    lParams.topMargin=piece.yCoord
                    piece.layoutParams= lParams
                    piece.canMove=false
                  sendViewToBack(piece)
                    activity.checkGameOver()
                }

            }

        }

        return true
    }

    private fun sendViewToBack(child:View) {
val parent=child.parent as ViewGroup
        if(parent!=null){
            parent.removeView(child)
            parent.addView(child,index:0)

        }
    }

}

