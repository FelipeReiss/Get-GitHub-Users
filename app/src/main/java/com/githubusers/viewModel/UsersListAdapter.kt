package com.githubusers.viewModel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatTextView
import com.githubusers.model.GithubUserItemList
import com.githubusers.view.R
// Class to create a new view with users list
class UsersListAdapter(val context: Context, val list: ArrayList<GithubUserItemList>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.user_row_layout, parent, false)
        val userID = view.findViewById(R.id.user_id) as AppCompatTextView
        val userLogin = view.findViewById(R.id.user_login) as AppCompatTextView

        userID.text = list[position].id.toString()
        userLogin.text = list[position].login

        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}