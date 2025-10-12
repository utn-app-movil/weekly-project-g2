package com.example.semana4_ever

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnShowMessage = findViewById<Button>(R.id.btnShowMsg1Main)
        btnShowMessage.setOnClickListener(View.OnClickListener { view ->
            Toast.makeText(this,getString(R.string.Msg1)
                ,Toast.LENGTH_LONG).show()
        })

        val clmain = findViewById<ConstraintLayout>(R.id.main)
        class myUndoListener: View.OnClickListener{
            override fun onClick(v: View?) {
                Snackbar.make(clmain, R.string.MsgCancel
                    , Snackbar.LENGTH_LONG).show()
            }
        }

        val btnShowMessage2 = findViewById<Button>(R.id.btnShowMsg2Main)
        btnShowMessage2.setOnClickListener(View.OnClickListener{view ->
            val mySnackbar = Snackbar.make(clmain,R.string.Msg2
                , Snackbar.LENGTH_LONG)
            mySnackbar.setAction(R.string.Cancel
                , myUndoListener())
            mySnackbar.show()
        })

        val btnOpenActivity = findViewById<Button>(R.id.btnOpenActivityMain)
        btnOpenActivity.setOnClickListener(View.OnClickListener{view ->
            util.OpenActivity(this
                , SecondActivity::class.java)
        })
    }
}