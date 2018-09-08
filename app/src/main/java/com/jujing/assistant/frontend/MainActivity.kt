package com.jujing.assistant.frontend

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jujing.assistant.R
import com.jujing.assistant.util.VersionUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val localVersion = VersionUtil.getVersionName(this)
        tv_version.text = localVersion


    }

    override fun onResume() {
        super.onResume()
        if (isModuleLoaded()) {
            tv_notice.text = "插件正常运行"
            tv_notice.setTextColor(Color.BLACK)
        } else {
            tv_notice.text = "插件未正常运行,请检查xposed设置"
            tv_notice.setTextColor(Color.RED)
            return
        }

    }

    private fun isModuleLoaded(): Boolean {
        return false
    }
}
