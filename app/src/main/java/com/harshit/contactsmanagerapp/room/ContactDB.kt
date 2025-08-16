package com.harshit.contactsmanagerapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
//act as a ROOM DB
abstract class ContactDB : RoomDatabase() {
    abstract val contactDAO:ContactDAO

    //singleton Design Pattern
    //Only one instance of the database exists, avoiding
    //Unnecessary overhead associated with repeated database creation.
    //Show the Singleton pattern helps in preventing memory leaks
   //by ensuring There is only one reference to the database instance
    //Companion object defines a static Singleton instance of this database class.
    //@Volatile prevents any possible race condition in multithreading.
    companion object{
        @Volatile
        private var INSTANCE : ContactDB ?=null

        fun getInstance(context: Context): ContactDB{
            // This function get instance is marked as synchronised
            // meaning that only one thread can access it at a time
            // So this prevent any multiple access to this instance and modification.
            synchronized(this){

                var instance = INSTANCE
                if(instance == null){

                    // Creating the database object
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDB::class.java,
                        "contacts_db").build()
                }
                // If not null
                INSTANCE = instance
                return instance
            }
        }
    }
}