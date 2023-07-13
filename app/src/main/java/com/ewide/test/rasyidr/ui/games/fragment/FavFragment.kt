package com.ewide.test.rasyidr.ui.games.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ewide.test.rasyidr.databinding.FragmentFavBinding
import com.ewide.test.rasyidr.ui.games.GameAdapter
import com.ewide.test.rasyidr.ui.games.GameModel
import com.ewide.test.rasyidr.ui.games.GamesViewModel
import com.ewide.test.rasyidr.utilities.OnItemClicked
import com.ewide.test.rasyidr.utilities.extenstion.toast
import org.koin.android.viewmodel.ext.android.viewModel

class FavFragment: Fragment(), OnItemClicked {

    private var _homeFragment: FragmentFavBinding? = null
    private val binding get() = _homeFragment as FragmentFavBinding

    private val gamesViewModel: GamesViewModel by viewModel()
    private lateinit var gameAdapter: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _homeFragment = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()
        setupView()
    }

    private fun initRV() {
        with(binding.rvFav) {
            gameAdapter = GameAdapter(
                onItemClickedFav = this@FavFragment
            )
            val gridLayoutManager = GridLayoutManager(requireActivity(), 2)
            layoutManager = gridLayoutManager
            adapter = gameAdapter
        }
    }
    private fun setupView() {
        gamesViewModel
            .getFav().observe(viewLifecycleOwner, favorite)
    }

    private val favorite = Observer<List<GameModel>> { data ->
        if (data != null) {
            Log.e("aaa", data.count().toString())
            gameAdapter.addList(data)
        }
    }

    override fun onFavoriteClick(data: GameModel) {
        super.onFavoriteClick(data)
        gamesViewModel.deleteFav(data)
        context?.toast("delete success")
    }


    override fun onDestroy() {
        super.onDestroy()
        _homeFragment = null
    }
}