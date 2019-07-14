package com.fitness.athome.db.exercises

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "types", indices = [Index(value = ["_id"])])
data class Types(@PrimaryKey @ColumnInfo(name = "_id") val id: Int,
                 @ColumnInfo(name = "title") val types: String)