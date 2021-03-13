package com.acemirr.projectwork.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.acemirr.projectwork.data.model.Meet
import com.acemirr.projectwork.databinding.ActivityAddMeetBinding
import com.acemirr.projectwork.ui.base.BaseActivity
import com.acemirr.projectwork.ui.base.ViewModelFactory
import com.acemirr.projectwork.ui.viewmodel.MeetViewModel
import com.acemirr.projectwork.utils.DialogHelper
import java.util.*

class AddMeetActivity : BaseActivity<ActivityAddMeetBinding>() {

    private lateinit var viewModel: MeetViewModel
    private lateinit var startDateTime: Date
    private lateinit var endDateTime: Date

    override fun initData() {
        viewModel = ViewModelProvider(this, ViewModelFactory()).get(MeetViewModel::class.java)
    }
    @SuppressLint("SetTextI18n")
    override fun initUI() {
        binding.etStartDate.setOnClickListener {
            DialogHelper.datePickerDialog(this) { dp, year, month, day ->
                DialogHelper.timePickerDialog(this){tp, h, m ->
                    val selectedCalendar = Calendar.getInstance()
                    selectedCalendar.set(year,month,day,h,m)
                    startDateTime = selectedCalendar.time
                    binding.etStartDate.setText("$year-$month-$day $h:$m")
                }
            }
        }
        binding.etEndDate.setOnClickListener {
            DialogHelper.datePickerDialog(this) { dp, year, month, day ->
                DialogHelper.timePickerDialog(this){tp, h, m ->
                    val selectedCalendar = Calendar.getInstance()
                    selectedCalendar.set(year,month,day,h,m)
                    endDateTime = selectedCalendar.time
                    binding.etEndDate.setText("$year-$month-$day $h:$m")
                }
            }
        }
        binding.btnAddMeet.setOnClickListener {
            if (binding.etNames.text.isNullOrEmpty() || binding.etMeetName.text.isNullOrEmpty() || binding.etStartDate.text.isNullOrEmpty() || binding.etEndDate.text.isNullOrEmpty()){
                Toast.makeText(this, "Harap Isi Semua Bidang", Toast.LENGTH_SHORT).show()
            }else {
                val meet = Meet(null,binding.etMeetName.text.toString(),binding.etNames.text.toString(),null,startDateTime.toString(),endDateTime.toString())
                viewModel.insertMeet(meet)
            }
        }
    }

    override fun observeViewModel() {
        viewModel.meets.observe(this, {
            if (it != null) {
                Toast.makeText(this, "Berhasil Menambahkan Meet", Toast.LENGTH_SHORT).show()
                finish()
            }

        })
        viewModel.message.observe(this, {
            if (it != null) Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

}