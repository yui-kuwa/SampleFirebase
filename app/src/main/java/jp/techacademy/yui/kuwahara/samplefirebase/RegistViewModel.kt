package jp.techacademy.yui.kuwahara.samplefirebase

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_regist.*

class RegistViewModel : ViewModel() {

    fun getUserData(userName: String, userGender: String, data: HashMap<String, String>) {

        val dataBaseReference = FirebaseDatabase.getInstance().reference
        val genderRef = dataBaseReference.child("userId") // 直接名前を入れる

        data["name"] = userName
        data["gender"] = userGender

        // 保存
        genderRef.push().setValue(data, this)

    }
}