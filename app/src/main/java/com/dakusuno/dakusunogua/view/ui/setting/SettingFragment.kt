package com.dakusuno.dakusunogua.view.ui.setting

import android.app.AlarmManager
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.dakusuno.dakusunogua.MainApplication.Companion.prefs
import com.dakusuno.dakusunogua.R
import com.dakusuno.dakusunogua.base.BaseFragment
import com.dakusuno.dakusunogua.databinding.FragmentSettingBinding
import com.dakusuno.dakusunogua.receiver.AlarmReceiver
import kotlinx.android.synthetic.main.item_placeholder_layout.view.*

class SettingFragment : BaseFragment(){
    override fun layoutId(): Int {
        return R.layout.fragment_setting
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val alarmReceiver = AlarmReceiver()
        FragmentSettingBinding.bind(view).apply {
            language.setOnClickListener {
                    startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }
            val check = prefs.pull("check",false)
            if(check){
                switchNotification.text = resources.getString(R.string.active)
            }else{
                switchNotification.text = resources.getString(R.string.nonactive)
            }
            switchNotification.isChecked = check
            switchNotification.setOnCheckedChangeListener{buttonView, isChecked ->
                prefs.push("check",isChecked)
                if(isChecked){
                    switchNotification.text = resources.getString(R.string.active)
                    alarmReceiver.setRepeatingAlarm(requireContext(),resources.getString(R.string.notif))
                }else{
                    switchNotification.text = resources.getString(R.string.nonactive)
                    alarmReceiver.cancelAlarm(requireContext())
                }

            }
        }.root
    }


}