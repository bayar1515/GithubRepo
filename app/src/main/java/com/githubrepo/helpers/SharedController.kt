package com.githubrepo.helpers

import android.content.Context
import com.valdesekamdem.library.mdtoast.MDToast

class SharedController() {
    private lateinit var context: Context
    constructor(context: Context):this(){
        this.context = context
    }

    fun isHasFromFavorites(id:Int,db:DBHelper):Boolean {
        for (i in db.readData()){
            if (i == id)
                return true
        }
        return false
    }

    fun showErrorToast(message:String){
        MDToast.makeText( context, message, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show()
    }
    fun showWarningToast(message:String){
        MDToast.makeText( context, message, MDToast.LENGTH_SHORT, MDToast.TYPE_WARNING).show()
    }
    fun showInfoToast(message:String){
        MDToast.makeText( context, message, MDToast.LENGTH_SHORT, MDToast.TYPE_INFO).show()
    }
    fun showSuccessToast(message:String){
        MDToast.makeText( context, message, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show()
    }
}