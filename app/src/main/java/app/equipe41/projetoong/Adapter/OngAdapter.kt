package app.equipe41.projetoong.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import app.equipe41.projetoong.Activitys.DonationActivity
import app.equipe41.projetoong.Activitys.MainActivity
import app.equipe41.projetoong.Models.Ong
import app.equipe41.projetoong.R
import kotlinx.android.synthetic.main.adapter_ong.view.*

class OngAdapter (private val ongs: ArrayList<Ong>) : RecyclerView.Adapter<OngAdapter.ViewHolder>(){


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemButton: Button = itemView.findViewById(R.id.donation)

       fun pegarDados(ong: Ong) {
            itemView.title.text = ong.name
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_ong, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = ongs[position]
        holder.pegarDados(item)

        val context=holder.itemButton.context
        holder.itemButton.setOnClickListener {
                v: View -> Unit

            val intent = Intent(context, DonationActivity::class.java).apply {
                putExtra("id",item._id)
            }
            context.startActivity(intent)
        }


    }
    override fun getItemCount(): Int {
       return ongs.size
    }


}