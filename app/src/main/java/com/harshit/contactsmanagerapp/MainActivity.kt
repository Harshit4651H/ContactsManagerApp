package com.harshit.contactsmanagerapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.harshit.contactsmanagerapp.databinding.ActivityMainBinding
import com.harshit.contactsmanagerapp.repository.ContactRepository
import com.harshit.contactsmanagerapp.room.Contact
import com.harshit.contactsmanagerapp.room.ContactDB
import com.harshit.contactsmanagerapp.view.MyRecyclerViewAdapter
import com.harshit.contactsmanagerapp.viewmodel.ContactViewModel
import com.harshit.contactsmanagerapp.viewmodel.viewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        // Room database
        val dao = ContactDB.getInstance(applicationContext).contactDAO
        val repository = ContactRepository(dao)
        val factory = viewModelFactory(repository)

        // View Model
        contactViewModel = ViewModelProvider(this, factory).get(ContactViewModel::class.java)

        binding.contactViewModel = contactViewModel

        // use this: LiveData and Data Binding Integration
        binding.lifecycleOwner = this

        initRecyclerView()


    }

    private fun initRecyclerView() {
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        DisplayUsersList()
    }

    private fun DisplayUsersList() {
        contactViewModel.contacts.observe(this, Observer{
            binding.recycleView.adapter = MyRecyclerViewAdapter(it, { selectedItem: Contact->listItemClicked(selectedItem)})
        })
    }

    private fun listItemClicked(selectedItem: Contact){
        Toast.makeText(this,"Selected name is ${selectedItem.contact_name}", Toast.LENGTH_LONG).show()

        contactViewModel.initUpdateAndDelete(selectedItem)
    }
}