package com.fitness.athome.db.exercises

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fitness.athome.Constants.Companion.LOG_TAG
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception


@Database(entities = arrayOf(Exercises::class, Types::class), version = 1)
abstract class ExercisesDatabase : RoomDatabase() {

    abstract fun exercisesDao() : ExercisesDao

    companion object {
        @Volatile private var INSTANCE: ExercisesDatabase? = null

        fun getInstance(context: Context): ExercisesDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) :ExercisesDatabase {
            val dbFile = context.getDatabasePath("exercises.db")

            if (!dbFile.exists()) {
                copyDatabaseFile(context, "exercises.db")
            }

            return Room.databaseBuilder(
                context.applicationContext,
                ExercisesDatabase::class.java, "exercises.db"
                )
                .allowMainThreadQueries()
                .build()
        }

        private fun copyDatabaseFile(context: Context, databaseName: String) {
            val dbPath = context.getDatabasePath(databaseName)

            // If the database already exists, return
            if (dbPath.exists()) {
                return
            }

            // Make sure we have a path to the file
            dbPath.getParentFile().mkdirs()

            // Try to copy database file
            try {
                val inputStream = context.getAssets().open("databases/$databaseName")
                val output = FileOutputStream(dbPath)

                val buffer = ByteArray(8192)

                var length = inputStream.read(buffer)


                while (length > 0) {
                    output.write(buffer, 0, length)
                    length = inputStream.read(buffer)
                }

                output.flush()
                output.close()
                inputStream.close()
            } catch (e: Exception) {
                Log.d(LOG_TAG, "Failed to open file", e)
                e.printStackTrace()
            }

        }
    }
}