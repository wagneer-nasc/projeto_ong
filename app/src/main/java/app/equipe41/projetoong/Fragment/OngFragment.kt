package app.equipe41.projetoong.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.equipe41.projetoong.Models.User
import app.equipe41.projetoong.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_ong.view.*
import kotlinx.android.synthetic.main.fragment_ong.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OngFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OngFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null






    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OngFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private lateinit var adapter : MyQuoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_ong, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val items = listOf(
            User("Premature optimization is the root of all evil"),
            User("Any sufficiently advanced technology is indistinguishable from magic."),
            User("Content 01"),
            User("Content 02"),
            User("Content 03"),
            User("Content 04"),
            User("Content 05")
        )

        adapter = MyQuoteAdapter()
        adapter.replaceItems(items)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
    }

    class MyQuoteAdapter : RecyclerView.Adapter<MyQuoteAdapter.ViewHolder>() {
        private var items = listOf<User>()


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_ong, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.itemView.title.text = item.nome
        }

        fun replaceItems(items: List<User>) {
            this.items = items
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int = items.size

        inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
    }

}
