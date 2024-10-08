package com.kliachenko.presentation.content.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kliachenko.presentation.databinding.ErrorStateLayoutBinding
import com.kliachenko.presentation.databinding.ProgressStateLayoutBinding
import com.kliachenko.presentation.databinding.VideoItemLayoutBinding

class ContentAdapter(
    private val clickActions: ClickActions,
    private val navigation: (String) -> Unit,
    private val viewTypeList: List<ContentUiViewType> = ContentUiViewType.typeList(),
) : RecyclerView.Adapter<ContentViewHolder>(), ShowList {

    private val list = mutableListOf<ContentUi>()

    override fun getItemViewType(position: Int): Int {
        val item = list[position]
        val actualType = item.type()
        val typeIndex = viewTypeList.indexOf(actualType)
        if (typeIndex == -1)
            throw IllegalStateException(
                "Type $actualType isn't included in the typeList $viewTypeList"
            )
        return typeIndex
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return viewTypeList[viewType].createViewHolder(parent, clickActions, navigation)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    override fun showList(uiState: List<ContentUi>) {
        val diffCallback = ContentDiffUtil(oldList = list, newList = uiState)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(uiState)
        diffResult.dispatchUpdatesTo(this)
    }
}

abstract class ContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: ContentUi) = Unit

    class Progress(private val binding: ProgressStateLayoutBinding) :
        ContentViewHolder(binding.root) {
        override fun bind(item: ContentUi) {
            item.show(binding)
        }
    }

    class Error(
        private val binding: ErrorStateLayoutBinding,
        private val clickActions: ClickActions,
    ) : ContentViewHolder(binding.root) {
        override fun bind(item: ContentUi) {
            item.show(binding)
            binding.retryButton.setOnClickListener {
                clickActions.retry()
            }
        }
    }

    class VideoRecord(
        private val binding: VideoItemLayoutBinding,
        private val navigation: (String) -> Unit,
    ) : ContentViewHolder(binding.root) {
        override fun bind(item: ContentUi) {
            item.show(binding)
            binding.root.setOnClickListener {
                navigation(item.videoUrl())
            }
        }
    }

}

interface ShowList {

    fun showList(uiState: List<ContentUi>)
}
