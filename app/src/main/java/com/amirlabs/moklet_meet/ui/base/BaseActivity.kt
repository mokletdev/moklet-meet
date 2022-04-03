package com.amirlabs.moklet_meet.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import java.lang.reflect.ParameterizedType
import java.util.*


abstract class BaseActivity<T : ViewDataBinding>: AppCompatActivity() {
    protected lateinit var binding: T

    private var persistentClass: Class<T>? = null
    init {
        this.persistentClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val resIdName = persistentClass?.simpleName?.dropLast(7)
        var layoutName = ""
        var lastIndex = 0
        if (resIdName != null) {
            for(i in 1..resIdName.lastIndex){
                if (resIdName[i].isUpperCase()){
                        layoutName += resIdName.substring(lastIndex, i)+"_"
                        lastIndex = i
                } else if (i==resIdName.lastIndex){
                    layoutName += resIdName.substring(lastIndex, resIdName.length)
                }
            }
        }
        binding = DataBindingUtil.setContentView(
            this, resources.getIdentifier(
                layoutName.toLowerCase(
                    Locale.getDefault()
                ), "layout", packageName
            )
        )
    }

    override fun onStart() {
        super.onStart()
        initData()
        initUI()
        observeViewModel()
    }

    abstract fun initData()
    abstract fun initUI()
    abstract fun observeViewModel()
}