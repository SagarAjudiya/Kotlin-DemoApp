package com.example.kotlin_demoapp.adapter

import android.app.SearchManager
import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlin_demoapp.R

class SingleTextCursorAdapter public constructor(context: Context?, cursor: Cursor?, flags: Int, private val getSelectedText : (String) -> Unit) :
    androidx.cursoradapter.widget.CursorAdapter(context, cursor, flags) {

    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.item_simple, parent, false)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val cursorText = cursor?.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1)
            ?.let { cursor.getString(it) }
        view?.findViewById<TextView>(R.id.text)?.apply {
            text = cursorText
        }
        view?.setOnClickListener { getSelectedText(cursorText ?: "") }
    }
}