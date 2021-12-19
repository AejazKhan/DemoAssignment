package com.example.demo.dummylist.db.entity

import androidx.room.Embedded
import androidx.room.Relation
/**
 * Created by aejaz.khan.
 */
data class UserWithLocation(
    @Embedded
    var user : User,
    @Relation(
        parentColumn = "uuid",
        entityColumn = "uuid",
        entity = Location::class
    )
    var location : Location
)
