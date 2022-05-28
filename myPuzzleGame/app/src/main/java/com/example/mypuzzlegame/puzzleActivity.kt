package com.example.mypuzzlegame

import android.media.Image
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import java.util.ArrayList

class puzzleActivity : AppCompatActivity() {
    val pieces : ArrayList<PuzzlePiece>?=null
    val mCurrentPhotoPath:String?=null
    var mCurrentPhotoUri : String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puzzle)
        val layout=findViewById<RelativeLayout>(R.id.layout)
        val imageView=findViewById<ImageView>(R.id.imageView)
        val intent=intent
        val assetName=intent.getStringArrayExtra(name:"assetName")
        mCurrentPhotoPath=intent.getStringArrayExtra(name:" mCurrentPhotoPath")
        mCurrentPhotoUri=intent.getStringArrayExtra(name:"mCurrentPhotoUri")
    imageView.post{
        if(assetName !=null){
            setPicFromAsset(assetName.imageView)
        }
        else if(mCurrentPhotoPath !=null)
        {
            setPicFromPhotoPath(mCurrentPhotoPath,imageView)

        }
        else if (mCurrentPhotoUri !=null){
            imageView.setImageURI(Uri.parse(mCurrentPhotoUri
            ))
        }
        pieces=splitImage()
        val touchListener = TouchListener(activity = )
    }

    }
}