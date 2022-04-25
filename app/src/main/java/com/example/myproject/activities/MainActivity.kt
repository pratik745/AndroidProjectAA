package com.example.myproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myproject.R
import com.example.myproject.adapter.RickMortyAdapter
import com.example.myproject.api.ApiService
import com.example.myproject.api.RetrofitHelper
import com.example.myproject.databinding.ActivityMainBinding
import com.example.myproject.repository.Repository
import com.example.myproject.utils.Constants
import com.example.myproject.viewModels.MainViewModel
import com.example.myproject.viewModels.MainViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val charService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val repository = Repository(charService)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository))[MainViewModel::class.java]



        mainViewModel.characters.observe(this) { it ->
            var selectedChoice: String
            Constants.charactersList.addAll(it.results)
            val arrayAdapter =ArrayAdapter(this@MainActivity, R.layout.spinner_item,Constants.choices)
            binding.spinner.adapter= arrayAdapter
            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    selectedChoice = p0?.getItemAtPosition(p2).toString()
                    if(selectedChoice=="Sort<None>"){Constants.charactersList.sortBy { it1 -> it1.id };getView()}
                    if(selectedChoice=="Sort<Name>"){Constants.charactersList.sortBy { it.name };getView()}
                    if(selectedChoice=="Sort<No.of.Episodes>"){Constants.charactersList.sortByDescending { it.episode.size };getView()}
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
            binding.button.setOnClickListener{
                val intent = Intent(this, SpeciesGroupActivity::class.java)

                startActivity(intent)
            }

    }


    }
    fun getView()
    {
        val adapt = RickMortyAdapter(this, Constants.charactersList)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapt
            adapt.setOnClickListener(object : RickMortyAdapter.OnItemClickListener{
                override fun onItemClick(name:String,gender:String,status:String,type:String,species:String,originName:String,locationName:String,image:String) {
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("Name",name)
                    intent.putExtra("gender",gender)
                    intent.putExtra("status",status)
                    intent.putExtra("type",type)
                    intent.putExtra("species",species)
                    intent.putExtra("originName",originName)
                    intent.putExtra("locationName",locationName)
                    intent.putExtra("image",image)
                    startActivity(intent)
                }
            })
        }
    }
}