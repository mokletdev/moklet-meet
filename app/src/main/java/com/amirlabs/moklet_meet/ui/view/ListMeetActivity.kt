package com.amirlabs.moklet_meet.ui.view

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.amirlabs.moklet_meet.data.model.Meet
import com.amirlabs.moklet_meet.databinding.ActivityListMeetBinding
import com.amirlabs.moklet_meet.ui.adapter.ListMeetAdapter
import com.amirlabs.moklet_meet.ui.base.BaseActivity
import com.amirlabs.moklet_meet.ui.base.ViewModelFactory
import com.amirlabs.moklet_meet.ui.viewmodel.MeetViewModel
import com.testfairy.TestFairy

class ListMeetActivity : BaseActivity<ActivityListMeetBinding>() {
    private val adapter = ListMeetAdapter()
    private lateinit var viewModel : MeetViewModel

    override fun initData(){
        try {
            viewModel = ViewModelProvider(this, ViewModelFactory())[MeetViewModel::class.java]
            viewModel.getListMeet()
            TestFairy.begin(this, "SDK-kYtGo3pS")
        }catch (e:Exception){
            Toast.makeText(this, "Yahh Aplikasi Tertutup", Toast.LENGTH_SHORT).show()
        }
    }

    override fun initUI(){
        binding.rvListMeet.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.rvListMeet.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        binding.rvListMeet.adapter = adapter
        adapter.setListener(object:ListMeetAdapter.Listener{
            override fun onItemClicked(data: Meet) {
                val intent = Intent(this@ListMeetActivity,VerifyActivity::class.java)
                intent.putExtra("data",data)
                startActivity(intent)
            }
        })
        binding.fatAdd.setOnClickListener {
            intent = Intent(this,AddMeetActivity::class.java)
            startActivity(intent)
        }
        binding.srl.setOnRefreshListener {
            binding.srl.isRefreshing = false
            viewModel.getListMeet()
        }
    }

    override fun observeViewModel(){
        viewModel.listMeet.observe(this, {
            adapter.submitList(it)
        })
        viewModel.meets.observe(this, {
            if (it.id != null) {
                Toast.makeText(this, "Berhasil Membuat Meet Baru", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.message.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}