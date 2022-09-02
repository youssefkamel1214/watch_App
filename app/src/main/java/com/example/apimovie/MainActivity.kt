package com.example.apimovie

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.apimovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = this.findNavController(R.id.fragmentnavcontroller)
    }
    //    public void move_to_egybest(Movie movie) {
    //        String url=movie.getTitle();
    //        url=url.toLowerCase();
    //        url=url.replace(' ', '-');
    //        url=url.replace(":", "");
    //        url+="-"+movie.getRelease_date().substring(0, 4);
    //        url=egybest_url+url;
    //        System.out.println(url);
    //        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    //        startActivity(browserIntent);
    //    }
}