package com.harshit.contactsmanagerapp.viewmodel


import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harshit.contactsmanagerapp.repository.ContactRepository
import com.harshit.contactsmanagerapp.room.Contact
import kotlinx.coroutines.launch

// View Model: store and manage UI-related Data
// separating the UI-related logic from UI Controller ( Activity/Frag.)

class ContactViewModel (private val repository: ContactRepository): ViewModel(), Observable{

    val contacts = repository.contacts
    private var isUpdateOrDelete = false
    private lateinit var contactToUpdateOrDelete: Contact

    // Data Binding with live data
    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "ClearAll"
    }

    fun insert(contact: Contact) = viewModelScope.launch{
        repository.insert(contact)
    }

    fun delete(contact: Contact) = viewModelScope.launch {
        repository.delete(contact)

        // Resetting the buttons and fields
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun update(contact: Contact) = viewModelScope.launch {
        repository.update(contact)

        // Resetting the buttons and fields
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun clearAll() = viewModelScope.launch{
        repository.deleteAll()
    }

    fun saveOrUpdate(){
        if(isUpdateOrDelete){
            // make an update
            contactToUpdateOrDelete.contact_name = inputName.value!!
            contactToUpdateOrDelete.contact_email = inputName.value!!
            update(contactToUpdateOrDelete)
        }
        else{
            // Inserting a new contact
            val name = inputName.value!!
            val email = inputEmail.value!!
            insert(Contact(0,name, email))

            //Reset the name and email
            inputName.value = null
            inputEmail.value = null

        }
    }

    fun clearAllOrDelete(){
        if(isUpdateOrDelete){
            delete(contactToUpdateOrDelete)
        }
        else{
            clearAll()
        }
    }

    fun initUpdateAndDelete(contact: Contact){
        inputName.value = contact.contact_name
        inputEmail.value = contact.contact_email
        isUpdateOrDelete = true
        contactToUpdateOrDelete = contact
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}