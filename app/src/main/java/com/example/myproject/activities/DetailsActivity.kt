package com.example.myproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.myproject.R
import com.example.myproject.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        val bundle: Bundle?= intent.extras
        Glide.with(binding.img).load(bundle!!.getString("image")).into(binding.img)
        binding.name.text = bundle.getString("Name")
        binding.species.text = bundle.getString("species")
        binding.gender.text = bundle.getString("gender")
        binding.type.text = bundle.getString("type")
        binding.status.text = bundle.getString("status")
        binding.location.text = bundle.getString("locationName")
    }
}