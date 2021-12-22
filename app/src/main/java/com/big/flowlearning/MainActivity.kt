package com.big.flowlearning

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.big.flowlearning.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mainViewModel = MainViewModel()

        lifecycleScope.launchWhenStarted {
            mainViewModel.countDownFlow.collectLatest {
                Log.d("MAIN_ACTIVITY", "Happy to See you $it")
                binding.myId.text = it.toString()
            }
        }
    }
}