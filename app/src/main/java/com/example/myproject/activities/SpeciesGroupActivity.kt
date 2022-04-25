package com.example.myproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myproject.R
import com.example.myproject.adapter.RickMortyAdapter
import com.example.myproject.databinding.ActivitySpeciesGroupBinding
import com.example.myproject.model.RickMorty
import com.example.myproject.utils.Constants

class SpeciesGroupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpeciesGroupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_species_group)
        val speciesList = Constants.charactersList.groupBy { it.species }
        val arrayAdapter = ArrayAdapter(this@SpeciesGroupActivity, R.layout.spinner_item,speciesList.keys.toTypedArray())
        binding.spinner3.adapter= arrayAdapter
        binding.spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedChoice = p0?.getItemAtPosition(p2).toString()
                speciesList[selectedChoice]?.let { getView(it) }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }
    fun getView(list:List<RickMorty>)
    {
        val adapt = RickMortyAdapter(this, list)
        binding.recyclerView2.apply {
            layoutManager = LinearLayoutManager(this@SpeciesGroupActivity)
            adapter = adapt

        }
    }
}