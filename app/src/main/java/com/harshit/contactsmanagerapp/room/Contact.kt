package com.harshit.contactsmanagerapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

//Each Instance of this data class represents a row in a table
@Entity(tableName = "contacts_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val contact_id:Int,
    var contact_name:String,
    var contact_email:String)
