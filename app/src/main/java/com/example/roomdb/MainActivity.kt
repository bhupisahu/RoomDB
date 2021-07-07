package com.example.roomdb

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.data.*


class MainActivity : AppCompatActivity() {

    private var countryDao: CountryDao? = null

    private var tvStatus: TextView? = null

    //private ListView listView ;
    private var recyclerView: RecyclerView? = null
    private var recyclerView1: RecyclerView? = null
    private var adapter: CountryRecyclerAdapter? = null
    private var adapter1: StateRecyclerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvStatus = findViewById(R.id.tv_status)
        recyclerView = findViewById(R.id.list_country)
        recyclerView1 = findViewById(R.id.list_state)
        setDataInRecyclerView()

    }

    private fun setDataInRecyclerView() {
        val database = CountryRoomDatabase.getDatabase(this)
        countryDao = database.countryDao()
        adapter = CountryRecyclerAdapter(
            countryDao!!.getAllCountry(),
            clickedListener = { getSelCountryName(it) }
        )
        recyclerView!!.adapter = adapter
        recyclerView!!.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView!!.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // button add clicked, show a Dialog
        if (item.itemId == R.id.action_add) {
            addCountryNameDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Show a input dialog to type a phrase
     * and add it in database
     */
    private fun addCountryNameDialog() {
        val edtPhrase = EditText(this)

        AlertDialog.Builder(this)
            .setTitle("Type country name")
            .setView(edtPhrase)
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Save") { dialogInterface, i ->
                val text = edtPhrase.text.toString()
                // if there is a text, save it
                if (!text.trim { it <= ' ' }.isEmpty()) {
                    val phrase = Country(text)
                    // save in room database
                    countryDao!!.insert(phrase)
                    setDataInRecyclerView()
                }
            }
            .create()
            .show() // show dialog
    }

    private fun addStateNameDialog(countryName: String) {
        val edtPhrase = EditText(this)

        AlertDialog.Builder(this)
            .setTitle("Country : " + countryName)
            .setView(edtPhrase)
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Save") { dialogInterface, i ->
                val text = edtPhrase.text.toString()

                // if there is a text, save it
                if (!text.trim { it <= ' ' }.isEmpty()) {
                    val state = State(text, countryName)

                    // save in room database
                    countryDao!!.insertState(state)
                    getSelCountryName(countryName)
                    setDataInRecyclerView()

                }
            }
            .create()
            .show() // show dialog
    }

    fun getSelStateName(stateName: String) {

        val edtPhrase = EditText(this)

        AlertDialog.Builder(this)
            .setTitle("State : " + stateName)
            .setView(edtPhrase)
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Save") { dialogInterface, i ->
                val text = edtPhrase.text.toString()

                // if there is a text, save it
                if (!text.trim { it <= ' ' }.isEmpty()) {
                    val city = City(text, stateName)
                    // save in room database
                    countryDao!!.insertCity(city)
                    var allCity = ""
                    countryDao!!.getAllCity(stateName).forEach { name ->
                        allCity = allCity + name.cityName + ", "
                    }
                    Toast.makeText(this, allCity, Toast.LENGTH_SHORT).show()

                }
            }
            .create()
            .show() // show dialog
    }

    fun getSelCountryName(countryName: String) {
//         var allStateName = ""
//         countryDao!!.getAllState(countryName).forEach { name ->
//              allStateName = allStateName+name.stateName+", "
//         }
        adapter1 = StateRecyclerAdapter(
            countryDao!!.getAllState(countryName)
        ) { getSelStateName(it) }
        recyclerView1!!.adapter = adapter1
        recyclerView1!!.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView1!!.layoutManager = LinearLayoutManager(this)

        addStateNameDialog(countryName)

    }

}
