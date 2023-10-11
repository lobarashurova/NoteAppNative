package uz.mlsoft.noteappnative

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import uz.mlsoft.noteappnative.presentaion.screen.HomeScreen
import uz.mlsoft.noteappnative.utils.createFragment


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFragment(R.id.fragmentContainerView, HomeScreen())
    }
}