package ru.geekbrain.android.mymvpapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.geekbrain.android.mymvpapplication.databinding.ActivityMainBinding

const val COUNTERS = "counters"

class MainActivity : AppCompatActivity() {
    var counters = mutableListOf(0, 0, 0)
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCounter1.setOnClickListener {
            binding.btnCounter1.text =(++counters[0]).toString()
        }

        binding.btnCounter2.setOnClickListener {
            binding.btnCounter2.text =(++counters[1]).toString()
        }

        binding.btnCounter3.setOnClickListener {
            binding.btnCounter3.text =(++counters[2]).toString()
        }
    }

    fun initView(){
        binding.btnCounter1.text = counters[0].toString()
        binding.btnCounter2.text = counters[1].toString()
        binding.btnCounter3.text = counters[2].toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray(COUNTERS, counters.toIntArray())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val intArray = savedInstanceState.getIntArray(COUNTERS)
        intArray?.toList()?.let {
            counters.clear()
            counters.addAll(it)
        }
        initView()
    }
}