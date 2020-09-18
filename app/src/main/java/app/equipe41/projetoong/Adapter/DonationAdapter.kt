package app.equipe41.projetoong.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.equipe41.projetoong.Models.Donation
import app.equipe41.projetoong.R
import kotlinx.android.synthetic.main.adapter_donation.view.*
import kotlinx.android.synthetic.main.adapter_ong.view.*

class DonationAdapter (private val donations: ArrayList<Donation>) : RecyclerView.Adapter<DonationAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

       fun pegarDados(donation: Donation) {

           itemView.nome_donor_adapter.text = donation.nome_doador
           itemView.email_donor_adapter.text = donation.email_doador
           itemView.value_donor_adapter.text = donation.valor_doacao

       }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_donation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = donations[position]
        holder.pegarDados(item)

    }
    override fun getItemCount(): Int {
       return donations.size
    }


}