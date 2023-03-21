package ru.geekbrain.android.mymvpapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.geekbrain.android.mymvpapplication.app
import ru.geekbrain.android.mymvpapplication.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity(), CounterContract.MainView {

    private lateinit var presenter: CounterPresenter
    private val TAG = "MainActivity"

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = CounterPresenter(app.model)
        presenter.onAttach(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        binding.btnCounter1.setOnClickListener {
            presenter.onAction(1)
        }

        binding.btnCounter2.setOnClickListener {
            presenter.onAction(2)
        }

        binding.btnCounter3.setOnClickListener {
            presenter.onAction(3)
        }
    }

    private fun initView(){
        binding.btnCounter1.text = presenter.onRestoreCounter(1).toString()
        Log.i(TAG, "initView: ")
        binding.btnCounter2.text = presenter.onRestoreCounter(2).toString()
        binding.btnCounter3.text = presenter.onRestoreCounter(3).toString()
    }



    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        initView()
    }

    override fun setCounterText(counterIndex: Int, counterText: String) {
       when(counterIndex){
           1 -> binding.btnCounter1.text = counterText
           2 -> binding.btnCounter2.text = counterText
           3 -> binding.btnCounter3.text = counterText
       }
    }
}