package jp.techacademy.yui.kuwahara.samplefirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview.view.*

class CustomAdapter(private val userListData: ArrayList<User>) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>(){

    // リスナを格納する変数を定義（lateinitで初期化を遅らせている）
    private lateinit var listener: OnUserCellClickListener

    // インターフェースを作成
    interface  OnUserCellClickListener {
        fun onItemClick(user: User)
    }

    // リスナーをセット
    fun setOnUserCellClickListener(listener: OnUserCellClickListener) {
        // 定義した変数listenerに実行したい処理を引数で渡す（BookListFragmentで渡している）
        this.listener = listener
    }

    // ViewHolderクラス(別ファイルに書いてもOK)
    //各行に表示するための画面部品要素をレイアウトファイル
    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val userName: TextView = view.nameText
        val userGender: TextView = view.genderText
    }

    // getItemCount onCreateViewHolder onBindViewHolderを実装
    // 上記のViewHolderクラスを使ってViewHolderのインスタンスを作成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.recyclerview, parent, false)

        return CustomViewHolder(item)
    }

    // recyclerViewのコンテンツ(userListDataの件数)のサイズ
    override fun getItemCount(): Int {
        return userListData.size
    }

    // ViewHolderに表示するテキストを挿入
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        // positionは表示するリストuserListDataのインデックス番号のようなもの
        val user = userListData[position]

        //setText
        holder.userName.text = user.name
        holder.userGender.text = user.gender


        // セルのクリックイベントにリスナをセット
        holder.itemView.setOnClickListener {
            // セルがクリックされた時にインターフェースの処理が実行される
            listener.onItemClick(user)
        }

    }

}