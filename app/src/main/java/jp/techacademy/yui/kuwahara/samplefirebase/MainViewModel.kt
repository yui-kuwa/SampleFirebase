package jp.techacademy.yui.kuwahara.samplefirebase

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class MainViewModel : AppCompatActivity() {

/*    fun removeButtonClicked() { // 削除ボタン

        //リストのセルをタップでデータ削除
        adapter.setOnUserCellClickListener(
            object : CustomAdapter.OnUserCellClickListener {
                override fun onItemClick(user: User) {

                    val dataBaseReference = FirebaseDatabase.getInstance().reference
                    val genderRef = dataBaseReference.child("userId").child(user.uid)// 直接名前を入れる

                    //val userNum: String = genderRef.toString()
                    //Log.d("kotlintest",userNum)

                    genderRef.removeValue()

                    updateDataList()
                }
            }
        )
    }

    private fun updateDataList() {

        // recyclerViewのユーザのリストをクリア
        mUserList.clear()//

        //firebaseのデータ全部取得
        //①Firebase Databaseのインスタンスを取得
        val database = FirebaseDatabase.getInstance().reference

        //②リファレンスを取得
        val genderRef = database.child("userId")

        //③データを取得する（リスナーを用意して二つのメソッドをオーバーライド）
        genderRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                Log.d("kotlintest", dataSnapshot.toString())

                val map = dataSnapshot.value as Map<String, String>
                val name = map["name"] ?: ""
                val gender = map["gender"] ?: ""

                Log.d("kotlintest", map.toString())
                //userListDataの配列に格納
                val data: User = User().also {
                    it.uid = dataSnapshot.key.toString()
                    it.name = name
                    it.gender = gender
                }

                mUserList.add(data)

                //リスト全体を更新するためのメソッド
                adapter.notifyDataSetChanged()
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }*/

}