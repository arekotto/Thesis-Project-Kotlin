package kotlintest.arkadiuszotto.com.kotlintest

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*
import kotlintest.arkadiuszotto.com.kotlintest.Model.RandomUser

/**
 * @author arekotto
 */

class RandomUserAdapter(private val randomUsers: List<RandomUser>, var context: Context?) : RecyclerView.Adapter<RandomUserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.random_user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = randomUsers[position]
        holder.headerTitleTextView.text = String.format(Locale.getDefault(), "User %d", position)
        holder.nameTextView.text = user.name?.first
        holder.lastNameTextView.text = user.name?.last
        holder.titleTextView.text = user.name?.title
        holder.genderTextView.text = user.gender
        holder.cityTextView.text = user.location?.city
        holder.streetTextView.text = user.location?.street
    }

    override fun getItemCount(): Int {
        return randomUsers.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val headerTitleTextView: TextView = itemView.findViewById(R.id.header_title_text_view)
        val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        val lastNameTextView: TextView = itemView.findViewById(R.id.last_name_text_view)
        val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
        val genderTextView: TextView = itemView.findViewById(R.id.gender_text_view)
        val cityTextView: TextView = itemView.findViewById(R.id.city_text_view)
        val streetTextView: TextView = itemView.findViewById(R.id.street_text_view)
    }
}