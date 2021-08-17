package jp.techacademy.yui.kuwahara.samplefirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import jp.techacademy.yui.kuwahara.samplefirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = MainViewModel()



        viewModel = MainViewModel()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        //registBinding = ActivityRegistBinding.inflate(layoutInflater)

        setupView()
        observeLiveData() // リスナーみたいなものをセット
    }

    override fun onResume() {
        super.onResume()

        //runBlocking{
        viewModel.updateUserDataList()
        //delay(1000)
        //}

        //登録しているユーザのデータをアダプターにセットする
        //adapter.setUserListData(viewModel.userList)
    }

    /**
     * Viewのセットアップ
     */
    private fun setupView() {

        mainBinding.addUserButton.setOnClickListener {
            val intent = Intent(applicationContext, RegistActivity::class.java)
            startActivity(intent)
        }

        //
        adapter = CustomAdapter()

        //登録しているユーザのデータをアダプターにセットする
        adapter.setUserListData(viewModel.userList)

//        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager(this).orientation)
//        mainBinding.userListData.addItemDecoration(dividerItemDecoration)

        // LayoutManagerの設定
        mainBinding.userDataList.layoutManager = LinearLayoutManager(this)

        // CustomAdapterの生成と設定
        mainBinding.userDataList.adapter = adapter

        //recyclerViewサイズの固定化
//        mainBinding.userListData.setHasFixedSize(true)

        //リストのセルをタップでデータ削除
        adapter.setOnUserCellClickListener(
            object : CustomAdapter.OnUserCellClickListener {
                override fun onItemClick(user: User) {
                    //タップしたセルのデータを削除
                    viewModel.removeUserData(user)

                    //リストの更新
                    viewModel.updateUserDataList()

                    //adapter更新の呼び出し
                    adapter.notifyDataSetChanged()
                }
            }
        )
    }

    private fun observeLiveData() {
        viewModel.eventTypeLiveData.observe(this, Observer {
            Log.d("kotlintest",it.toString())
            //adapter更新の呼び出し
            adapter.notifyDataSetChanged()
        })
    }

}