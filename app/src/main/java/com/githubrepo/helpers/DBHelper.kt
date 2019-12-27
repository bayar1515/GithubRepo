package com.githubrepo.helpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(val context: Context) : SQLiteOpenHelper(context,DBHelper.DATABASE_NAME,null,DBHelper.DATABASE_VERSION) {
    private val TABLE_NAME="favorites"
    private val COL_FAVORITE_ID = "favorite_id"
    companion object {
        private val DATABASE_NAME = "SQLITE_DATABASE"
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COL_FAVORITE_ID INTEGER PRIMARY KEY AUTOINCREMENT )"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(favoriteId:Int) {
        val sqliteDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_FAVORITE_ID, favoriteId.toString())
        val result =sqliteDB.insert(TABLE_NAME, null, contentValues)
    }

    fun readData():ArrayList<Int>{
        var favoriteId :ArrayList<Int> = arrayListOf()
        val sqliteDB = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = sqliteDB.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                val a = result.getString(result.getColumnIndex(COL_FAVORITE_ID))
                favoriteId.add(result.getString(result.getColumnIndex(COL_FAVORITE_ID)).toInt())
                Log.e("asdas","asdasdas")
            }while (result.moveToNext())
        }
        result.close()
        sqliteDB.close()
        return favoriteId
    }
    fun deleteAllData() :Boolean{
        val sqliteDB = this.writableDatabase
        val result = sqliteDB.delete(TABLE_NAME,null,null)
        sqliteDB.close()
        if(result == 1)
            return true
        return false

    }

    fun delete(favoriteId: Int):Boolean {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME,COL_FAVORITE_ID +"=?", arrayOf(favoriteId.toString()))
        if(result == 1)
            return true
        return false
    }


}