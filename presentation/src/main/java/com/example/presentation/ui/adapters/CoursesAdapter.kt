package com.example.presentation.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.presentation.ui.data.CourseItem
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class CoursesAdapter : AsyncListDifferDelegationAdapter<CourseItem>(DIFF) {
    init {
        delegatesManager.addDelegate(courseAdapterDelegate())
    }
    companion object {
        val DIFF = object : DiffUtil.ItemCallback<CourseItem>() {
            override fun areItemsTheSame(o: CourseItem, n: CourseItem) = o.id == n.id
            override fun areContentsTheSame(o: CourseItem, n: CourseItem) = o == n
        }
    }
}