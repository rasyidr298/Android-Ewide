package com.ewide.test.rasyidr.ui.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ewide.test.rasyidr.R
import com.ewide.test.rasyidr.databinding.ItemGameBinding
import com.ewide.test.rasyidr.utilities.DiffCallback
import com.ewide.test.rasyidr.utilities.OnItemClicked
import com.ewide.test.rasyidr.utilities.extenstion.loadImage

class GameAdapter(
    private val onItemClickedFav: OnItemClicked
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list = mutableListOf<Any>()

    fun addList(listData: List<Any>) {
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            DiffCallback(
                list,
                listData
            )
        )
        list.clear()
        list.addAll(listData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val menuLayout = ItemGameBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GameHolderList(
            binding = menuLayout
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val menuItemHolder = holder as GameHolderList
        menuItemHolder.bind(
            onItemClickedFav = onItemClickedFav,
            data = list[position] as GameModel
        )
    }

    override fun getItemCount(): Int = list.size
}


class GameHolderList(
    private val binding: ItemGameBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        onItemClickedFav: OnItemClicked,
        data: GameModel
    ) {
        with(binding) {
            tvTitle.text = data.internalName
            imgBackground.loadImage(
                url = data.thumb.toString()
            )
            if (data.isFav == true) {
                binding.ivFav.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.ic_delete))
            }else {
                binding.ivFav.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.ic_add))
            }

            ivFav.setOnClickListener {
                onItemClickedFav.onFavoriteClick(data)
            }
        }
    }
}