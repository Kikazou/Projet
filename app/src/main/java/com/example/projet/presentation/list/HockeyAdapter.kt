package com.example.projet.presentation.list
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.R

class HockeyAdapter (private var dataSet: List<Hockey>, var listener: ((Int) -> Unit)? = null): RecyclerView.Adapter<HockeyAdapter.ViewHolder>() {



        class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val textView: TextView

            init {
                textView =view.findViewById(R.id.hockey_name)
            }
        }


    fun updateList(list: List<Hockey>){
        dataSet = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup : ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.hockey_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val hockey : Hockey = dataSet[position]
        viewHolder.textView.text = hockey.name
        viewHolder.itemView.setOnClickListener{
            listener?.invoke(position)
        }
    }

    override fun getItemCount()=dataSet.size
}