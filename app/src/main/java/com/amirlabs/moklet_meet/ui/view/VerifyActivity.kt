package com.amirlabs.moklet_meet.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.amirlabs.moklet_meet.R
import com.amirlabs.moklet_meet.data.model.Meet
import com.amirlabs.moklet_meet.databinding.ActivityVerifyBinding
import com.amirlabs.moklet_meet.ui.base.BaseActivity
import com.amirlabs.moklet_meet.ui.base.ViewModelFactory
import com.amirlabs.moklet_meet.ui.viewmodel.MeetViewModel
import com.amirlabs.moklet_meet.utils.formatDate
import org.jitsi.meet.sdk.JitsiMeet
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetUserInfo

class VerifyActivity : BaseActivity<ActivityVerifyBinding>() {
    private lateinit var data: Meet
    private lateinit var viewModel: MeetViewModel

    override fun initData() {
        try {
            data = intent.getParcelableExtra("data")!!
            viewModel = ViewModelProvider(this, ViewModelFactory()).get(MeetViewModel::class.java)
        } catch (e: Exception) {
            Toast.makeText(this, "Yahh Aplikasi Tertutup", Toast.LENGTH_SHORT).show()
        }
    }

    override fun initUI() {
        binding.tvMeetName.text = data.name
        binding.tvMeetCreator.text = data.creator
        binding.tvMeetDate.text = getString(R.string.range_date,data.startDate.formatDate("dd-MM-yyyy HH:mm"),data.endDate.formatDate("dd-MM-yyyy HH:mm"))

        binding.etUsername.addTextChangedListener {
            binding.btnJoinMeet.isEnabled = !it.isNullOrBlank()&&it.length>2
        }
        binding.btnJoinMeet.setOnClickListener { joinMeet() }

        binding.ivMic.setOnClickListener {
            binding.ivMic.isActivated = !binding.ivMic.isActivated
        }

        binding.ivVideo.setOnClickListener {
            binding.ivVideo.isActivated = !binding.ivVideo.isActivated
        }
    }

    override fun observeViewModel() {}

    private fun joinMeet(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val userInfo = JitsiMeetUserInfo()
            userInfo.displayName = binding.etUsername.text.toString()
            val options = JitsiMeetConferenceOptions.Builder()
                .setRoom(data.url)
                .setUserInfo(userInfo)
                .setFeatureFlag("invite.enabled", false)
                .setFeatureFlag("kick-out.enabled", false)
                .setFeatureFlag("notifications.enabled", false)

            if (binding.ivMic.isActivated)options.setAudioMuted(false)
            else options.setAudioMuted(true)
            if (binding.ivVideo.isActivated) options.setVideoMuted(false)
            else options.setVideoMuted(true)

            if (JitsiMeet.getCurrentConference() == null) {
                val intent = Intent(this, JitsiMeetActivity::class.java)
                intent.action = "org.jitsi.meet.CONFERENCE"
                intent.putExtra("JitsiMeetConferenceOptions", options.build())
                startActivity(intent)
            } else {
                if (JitsiMeet.getCurrentConference() == data.url) {
                    val intent = Intent(this, JitsiMeetActivity::class.java)
                    intent.action = Intent.ACTION_VIEW
                    intent.putExtra("JitsiMeetConferenceOptions", options.build())
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Tutup conference lainnya sebelum memulai", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(
                    "${data.url}" +
                            "#userInfo.displayName=\"${binding.etUsername.text.toString()}\""
                )
            )
            startActivity(browserIntent)
        }
    }
}