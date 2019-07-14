package com.fitness.athome.db.exercises

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "exercises", indices = [Index(value = ["_id"])])
data class Exercises(@PrimaryKey @ColumnInfo(name = "_id") val id: Int,
                     @ColumnInfo (name = "title") val title: String,
                     @ColumnInfo (name = "description") val description: String?,
                     @ColumnInfo (name = "short_description") val shortDescription: String?,
                     @ColumnInfo (name = "full_photo") val fullPhoto: String?,
                     @ColumnInfo (name = "small_photo") val smallPhoto: String?,
                     @ColumnInfo (name = "video_link") val videoLink: String?,
                     @ColumnInfo (name = "id_type") val idType: Int?)