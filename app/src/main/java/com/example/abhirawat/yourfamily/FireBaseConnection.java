package com.example.abhirawat.yourfamily;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Abhi Rawat on 24-09-2017.
 */

public class FireBaseConnection {
    FirebaseDatabase database;
    public FirebaseDatabase getFirebaseConnection()
    {
        database = FirebaseDatabase.getInstance();
        return database;
    }
}
