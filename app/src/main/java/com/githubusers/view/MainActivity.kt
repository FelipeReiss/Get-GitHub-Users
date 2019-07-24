package com.githubusers.view

import android.os.AsyncTask
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.githubusers.model.GithubUserItemList
import com.githubusers.viewModel.UsersListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

// Principal Activity
class MainActivity : AppCompatActivity() {

    private lateinit var hintMessage: TextView

    // Listener created to change the Search Bar HINT as user swipe between both menu options
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.tab_users -> {
                hintMessage.setHint(R.string.hint_search_users)
                return@OnNavigationItemSelectedListener true
            }
            R.id.tab_favorites -> {
                hintMessage.setHint(R.string.hint_search_favorites)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    /* On Creation will ensure Menu working fine, get the Search
       Bar reference to change the hint as needed and generate the
       github users list
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        //Github API link
        val url = "https://api.github.com/users"
        // Generating the list
        AsyncTaskHandleJson().execute(url)

        // Creating Menu
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        // Getting search bar
        hintMessage = findViewById(R.id.search_bar)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    // Class to dowload github data
    inner class AsyncTaskHandleJson : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg url: String?): String {
            var text: String = ""
            val connections = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connections.connect()
                text = connections.inputStream.use { it.reader().use { reader -> reader.readText() } }
            } catch (e: Exception) {
                println("ERROR = ${e.message}")
            } finally {
                connections.disconnect()
            }

            return text
        }
        // This function will call a function to convert the json file and create a view to users list
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJson(result)
        }
    }

    // this function will map the json file and generate the view with all users list
    private fun handleJson(jsonString: String?) {
        if (jsonString.isNullOrEmpty()) {
            println("Error = JSON Null or Empty")
        } else {
            try {
                val jsonArray = JSONArray(jsonString)
                val list = ArrayList<GithubUserItemList>()
                // mapping JSON and creating a list of users
                for (j in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(j)

                    list.add(
                        GithubUserItemList(
                            jsonObject.getInt("id"),
                            jsonObject.getString("login")
                        )
                    )
                }

                // creating a new view
                val adapter = UsersListAdapter(this,list)

                // setting the new view at main activity
                github_user_list.adapter = adapter

            } catch (e: Exception) {
                println("ERROR = ${e.message}")
            }
        }
    }
}
