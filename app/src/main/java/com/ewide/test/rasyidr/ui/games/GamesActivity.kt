package com.ewide.test.rasyidr.ui.games

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ewide.test.rasyidr.R
import com.ewide.test.rasyidr.databinding.ActivityGamesBinding
import com.ewide.test.rasyidr.ui.games.fragment.FavFragment
import com.ewide.test.rasyidr.ui.games.fragment.HomeFragment
import com.ewide.test.rasyidr.utilities.extenstion.navigateFragment

class GamesActivity : AppCompatActivity() {

    private val binding by lazy { ActivityGamesBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        this.navigateFragment(R.id.frameContainer, HomeFragment())
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuHome -> {
                    this.navigateFragment(R.id.frameContainer, HomeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.menuFavourite -> {
                    this.navigateFragment(R.id.frameContainer, FavFragment())
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}