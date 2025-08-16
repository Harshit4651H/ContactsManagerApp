package com.harshit.contactsmanagerapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

//DAO : Data Access Object, Defines the methods to interact with DB
@Dao
interface ContactDAO {

    //Suspend function is used for creating coroutines.
    //and coroutines are used for asynchronous programming in Kotlin
    //here i am going to perform the insert method in the background thread
    //in order to prevent the blocking the of Main UI thread
    @Insert
    suspend fun insertContact(contact: Contact): Long

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("DELETE FROM contacts_table")
    //the query anotation is used for custom queries
    //you can use placeholder like id to pass parameters to your queries
    suspend fun deleteAll()

    @Query("SELECT * FROM contacts_table")
    fun getAllContactsInDB():LiveData<List<Contact>>
    //LiveData : Update the UI whenever the underlying data changes

}