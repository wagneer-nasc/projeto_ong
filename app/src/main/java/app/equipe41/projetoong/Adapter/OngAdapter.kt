package app.equipe41.projetoong.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.equipe41.projetoong.Models.User
import app.equipe41.projetoong.R
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_ong.view.*

class OngAdapter (private val users: ArrayList<User>) : RecyclerView.Adapter<OngAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun pegarDados(user: User) {
            itemView.title.text = user.nome
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_ong, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = users[position]
        holder.pegarDados(item)
      //  holder.itemView.title.text = item.nome
    }

    override fun getItemCount(): Int {
       return users.size
    }
}