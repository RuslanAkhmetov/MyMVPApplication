package ru.geekbrain.android.mymvpapplication.ui

import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrain.android.mymvpapplication.databinding.ActivityMainBinding
import ru.geekbrain.android.mymvpapplication.model.CounterModelImpl


class MainActivity : MvpAppCompatActivity(), CounterContract.MainView {

    private val presenter by moxyPresenter { CounterPresenter(CounterModelImpl()) }
    private val TAG = "MainActivity"

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCounter1.setOnClickListener {
            presenter.counterOneClick()
        }

        binding.btnCounter2.setOnClickListener {
            presenter.counterTwoClick()
        }

        binding.btnCounter3.setOnClickListener {
            presenter.counterThreeClick()
        }
    }

    override fun setCounterOneText(counterText: String) {
        binding.btnCounter1.text = counterText
    }

    override fun setCounterTwoText(counterText: String) {
        binding.btnCounter2.text = counterText
    }

    override fun setCounterThreeText(counterText: String) {
        binding.btnCounter3.text = counterText
    }
}