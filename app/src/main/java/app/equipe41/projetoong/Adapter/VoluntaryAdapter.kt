package app.equipe41.projetoong.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.equipe41.projetoong.Models.Voluntario
import app.equipe41.projetoong.R
import kotlinx.android.synthetic.main.adapter_donation.view.*
import kotlinx.android.synthetic.main.adapter_voluntary.view.*

class VoluntaryAdapter (private val voluntary: ArrayList<Voluntario>) : RecyclerView.Adapter<VoluntaryAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

       fun pegarDados(voluntary: Voluntario) {

           itemView.nome_voluntary_adapter.text = voluntary.nome_voluntario
           itemView.email_voluntary_adapter.text = voluntary.email
           itemView.telefone_voluntary_adapter.text = voluntary.telefone_voluntario
           itemView.date_voluntary_adapter.text = voluntary.createdAT.toString()
           itemView.endereco_voluntary_adapter.text = voluntary.endereco + " N° ${voluntary.numero}"

       }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_voluntary, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = voluntary[position]
        holder.pegarDados(item)

    }
    override fun getItemCount(): Int {
       return voluntary.size
    }


}