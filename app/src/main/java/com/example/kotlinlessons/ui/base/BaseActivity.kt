package com.example.kotlinlessons.ui.base

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.github.ajalt.timberkt.Timber

abstract class BaseActivity<T, S : BaseViewState<T>> : AppCompatActivity() {
    abstract val viewModel: BaseViewModel<T, S>
    abstract val layoutRes: Int

    abstract fun renderData(data: T)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        viewModel.getViewState().observe(this, Observer<S> { viewState ->
            if (viewState == null) return@Observer
            if (viewState.data != null) renderData(viewState.data)
            if (viewState.error != null) renderError(viewState.error)
        })
    }

    protected fun renderError(error: Throwable?) {
        error?.let { t ->
            t.message?.let {
                showError(it)
            }
        }
    }

    protected fun showError(error: String){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        Timber.e{ error }
    }
}