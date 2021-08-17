package jp.techacademy.yui.kuwahara.samplefirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import jp.techacademy.yui.kuwahara.samplefirebase.databinding.ActivityMainBinding
import jp.techacademy.yui.kuwahara.samplefirebase.databinding.ActivityRegistBinding
import kotlinx.android.synthetic.main.activity_regist.*

class RegistActivity : AppCompatActivity(), DatabaseReference.CompletionListener{

    private lateinit var registBinding: ActivityRegistBinding
    private lateinit var viewModel: RegistViewModel
    private lateinit var mAdapter: ArrayAdapter<String> //スピナー用adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityRegistBinding>(this, R.layout.activity_regist)
        binding.viewModel = RegistViewModel()

        viewModel = RegistViewModel()

        val registBinding : ActivityRegistBinding = ActivityRegistBinding.inflate(layoutInflater)
        setContentView(registBinding.root)

        // スピナーで表示する項目を配列で用意します。
        val array = arrayOf("男性", "女性", "答えたくない")

        // スピナーに設定するアダプタを生成します。
        mAdapter = ArrayAdapter<String>(applicationContext, R.layout.support_simple_spinner_dropdown_item, array)

        // スピナーを取得し、アダプタを設定します。
        registBinding.genderSpinner.adapter = mAdapter

        setupView()
    }

    fun setupView(){
        cancelButton.setOnClickListener {
            finish()
        }

        registButton.setOnClickListener {
            Log.d("kotlintest", "registButton")

            // 名前と性別を取得する
            val name = nameText.text.toString()
            val gender = genderSpinner.selectedItem.toString()

            val dataBaseReference = FirebaseDatabase.getInstance().reference
            val genderRef = dataBaseReference.child("userId") // 直接名前を入れる

            val data = HashMap<String, String>()

            data["name"] = name
            data["gender"] = gender

            // 保存
            genderRef.push().setValue(data, this)

        }
    }

    //書き込み完了を受け取る
    override fun onComplete(databaseError: DatabaseError?, databaseReference: DatabaseReference) {
        if (databaseError == null) {//成功

        } else {   //失敗
            //SnackBarで失敗の旨を表示
            Snackbar.make(findViewById(android.R.id.content), "登録に失敗しました", Snackbar.LENGTH_LONG).show()
        }
    }

}