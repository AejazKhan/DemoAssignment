package com.example.demo.book.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity()
data class Book (
    @PrimaryKey( autoGenerate = true)
    @NotNull
    val id: Int?,
    var name : String,
    var mobileNumber : String,
    var book : String
    )