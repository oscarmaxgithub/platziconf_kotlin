package com.platzi.conf.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.platzi.conf.R
import com.platzi.conf.databinding.ActivitySplashscreenBinding

private lateinit var binding: ActivitySplashscreenBinding
class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splashscreen)
        binding= ActivitySplashscreenBinding.inflate(layoutInflater)
        var view= binding.root
        setContentView(view)
        val animacion= AnimationUtils.loadAnimation(this,R.anim.animacion)
        binding.ivLogoPlatziConf.startAnimation(animacion)
        val intent=Intent(this,MainActivity::class.java)
        animacion.setAnimationListener(object :Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                startActivity(intent)
                finish()
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
    }
}