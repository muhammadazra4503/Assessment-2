package org.d3ifcool3046.assessment2.ui.main.country

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.d3ifcool3046.assessment2.R
import org.d3ifcool3046.assessment2.databinding.ListItemBinding
import org.d3ifcool3046.assessment2.model.Country

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val data = mutableListOf<Country>()
    fun updateData(newData: List<Country>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }


    class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) = with(binding) {
            namaTextView.text = country.namaNegara
            imageView.setImageResource(country.imageCountry)
            root.setOnClickListener {
                val message = root.context.getString(R.string.message, country.namaNegara)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}