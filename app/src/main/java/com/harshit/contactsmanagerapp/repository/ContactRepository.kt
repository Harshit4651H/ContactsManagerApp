package com.harshit.contactsmanagerapp.repository

import com.harshit.contactsmanagerapp.room.Contact
import com.harshit.contactsmanagerapp.room.ContactDAO

// Repository: acts as a bridge bw the ViewModel and Data Source
class ContactRepository(private val contactDAO: ContactDAO) {

    val contacts = contactDAO.getAllContactsInDB()

    suspend fun insert(contact: Contact):Long{
        return contactDAO.insertContact(contact)
    }

    suspend fun delete(contact: Contact){
        return contactDAO.deleteContact(contact)
    }

    suspend fun update(contact: Contact){
        return contactDAO.updateContact(contact)
    }

    suspend fun deleteAll(){
        return contactDAO.deleteAll()
    }


}