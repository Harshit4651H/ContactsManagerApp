package com.harshit.contactsmanagerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harshit.contactsmanagerapp.repository.ContactRepository

// if your Viewmodel has a constructor with parameters.
// You can't use the default constructor that the
// view model framework provides.

//Viewmodel factory: Passed the required parameters to ViewModel.

class viewModelFactory(private val repository: ContactRepository)
    : ViewModelProvider.Factory{

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ContactViewModel::class.java)){
            return ContactViewModel(repository) as T
        }
            throw IllegalArgumentException("Unkown View Model Class")
    }
}