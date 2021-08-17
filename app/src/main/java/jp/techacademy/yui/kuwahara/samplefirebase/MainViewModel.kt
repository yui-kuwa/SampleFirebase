package jp.techacademy.yui.kuwahara.samplefirebase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class MainViewModel : ViewModel() {

    private val userRepository: UserRepository = UserRepositoryMock()

    var userList: ArrayList<User> = arrayListOf()
//    lateinit var adapter: CustomAdapter

    //firebaseのデータ全部取得
    //①Firebase Databaseのインスタンスを取得
    val database = FirebaseDatabase.getInstance().reference

    enum class EventType {
        TEST
    }

    val eventTypeLiveData = MutableLiveData<EventType>()

    // repository用
/*    fun updateUserDataList() {
        userList = userRepository.getUsers().toMutableList()

    }*/
    //

    fun updateUserDataListFB() {

        // recyclerViewのユーザのリストをクリア
        userList.clear()

        //③データを取得する（リスナーを用意して二つのメソッドをオーバーライド）
        database.child("userId").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                //Log.d("kotlintest",dataSnapshot.toString())

                val map = dataSnapshot.value as Map<String, String>
                val name = map["name"] ?: ""
                val gender = map["gender"] ?: ""

                //Log.d("kotlintest",map.toString())
                //userListDataの配列に格納
                val data = User().also {
                    it.uid = dataSnapshot.key.toString()
                    it.name = name
                    it.gender = gender
                }

                //ユーザリストにユーザのデータを追加
                userList.add(data)

                //リスト全体を更新するためのメソッド
//                adapter.notifyDataSetChanged()
                eventTypeLiveData.postValue(EventType.TEST)

            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    fun removeUserData(user: User) {
        database.child("userId").child(user.uid).removeValue()
    }
}