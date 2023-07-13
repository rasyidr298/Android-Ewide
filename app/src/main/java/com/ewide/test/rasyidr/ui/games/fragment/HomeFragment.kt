package com.ewide.test.rasyidr.ui.games.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ewide.test.rasyidr.databinding.FragmentHomeBinding
import com.ewide.test.rasyidr.ui.games.GameAdapter
import com.ewide.test.rasyidr.ui.games.GameModel
import com.ewide.test.rasyidr.ui.games.GamesViewModel
import com.ewide.test.rasyidr.utilities.ApiResponse
import com.ewide.test.rasyidr.utilities.OnItemClicked
import com.ewide.test.rasyidr.utilities.extenstion.hide
import com.ewide.test.rasyidr.utilities.extenstion.show
import com.ewide.test.rasyidr.utilities.extenstion.toast
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment: Fragment(), OnItemClicked {

    private var _homeFragment: FragmentHomeBinding? = null
    private val binding get() = _homeFragment as FragmentHomeBinding

    private val gamesViewModel: GamesViewModel by viewModel()
    private lateinit var gameAdapter: GameAdapter

    private var isSortAsc = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _homeFragment = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()
        setupView()
    }

    private fun initRV() {
        with(binding.rvHome) {
            gameAdapter = GameAdapter(
                onItemClickedFav = this@HomeFragment
            )
            val gridLayoutManager = GridLayoutManager(requireActivity(), 2)
            layoutManager = gridLayoutManager
            adapter = gameAdapter
        }
    }

    private fun setupView() {
        gamesViewModel
            .getGames("batman")
            .observe(viewLifecycleOwner, games)
    }

    private fun filter(data: List<GameModel>) {
        binding.fabFilter.setOnClickListener {
            if (isSortAsc) {
                isSortAsc = false
                gameAdapter.addList(
                        listData = data.sortedByDescending { it.internalName }
                    )
            }else {
                isSortAsc = true
                gameAdapter.addList(
                        listData = data.sortedBy { it.internalName }
                    )
            }
        }
    }

    private val games = Observer<ApiResponse<List<GameModel>>> { data ->
        if (data != null) {
            when (data) {
                is ApiResponse.Loading -> {
                    binding.progress.show()
                }
                is ApiResponse.Success -> {
                    binding.progress.hide()
                    gameAdapter.addList(data.data)
                    filter(data = data.data)
                }
                is ApiResponse.Error -> {
                    binding.progress.hide()
                    context?.toast(data.errorMessage)
                }
                else -> {}
            }
        }
    }

    override fun onFavoriteClick(data: GameModel) {
        super.onFavoriteClick(data)
        gamesViewModel.insertFav(data)
        context?.toast("add success")
    }
}