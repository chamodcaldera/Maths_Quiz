package com.example.cw

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager

import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import androidx.core.widget.PopupWindowCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bt1=findViewById<Button>(R.id.button)

        bt1.setOnClickListener {

            val GameIntent = Intent(this, GameWindow::class.java)
            startActivity(GameIntent)
        }

        val bt2=findViewById<Button>(R.id.button2)

        bt2.setOnClickListener {

            val inflater:LayoutInflater=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val view=inflater.inflate(R.layout.activity_pop_up_window,null)

            val popupWindow = PopupWindow(
                view,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            // Set an elevation for the popup window
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                popupWindow.elevation = 10.0F
            }

            // If API level 23 or higher then execute the code
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                // Create a new slide animation for popup window enter transition
                val slideIn = Slide()
                slideIn.slideEdge = Gravity.TOP
                popupWindow.enterTransition = slideIn

                // Slide animation for popup window exit transition
                val slideOut = Slide()
                slideOut.slideEdge = Gravity.RIGHT
                popupWindow.exitTransition = slideOut

            }

//            val txtview=view.findViewById<TextView>(R.id.editTextTextMultiLine)

//            txtview.setTextColor(Color.BLUE)

            val popbtn= view.findViewById<Button>(R.id.popbtn)
            popbtn.setOnClickListener { popupWindow.dismiss() }

            popupWindow.setOnDismissListener {
                Toast.makeText(applicationContext,"popup closed",Toast.LENGTH_SHORT).show()
            }

           popupWindow.showAtLocation(view,Gravity.CENTER,0,0)





        }



    }}
