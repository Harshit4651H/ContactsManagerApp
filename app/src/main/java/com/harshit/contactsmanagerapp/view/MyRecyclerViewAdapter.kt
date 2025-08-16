package com.harshit.contactsmanagerapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.harshit.contactsmanagerapp.R
import com.harshit.contactsmanagerapp.databinding.CardItemBinding
import com.harshit.contactsmanagerapp.room.Contact

class MyRecyclerViewAdapter(private val contactList: List<Contact>,
    private val clickListener: (Contact)-> Unit): RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            val binding: CardItemBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.card_item, parent, false)
            return MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.bind(contactList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }


    class MyViewHolder(val binding: CardItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(contact: Contact, clickListener: (Contact) -> Unit){

            binding.nameTextView.text = contact.contact_name
            binding.emailTextView.text = contact.contact_email

            binding.listItemLayout.setOnClickListener {
                clickListener(contact)
            }


        }
    }
}