package com.example.presentation.ui.adapters

import com.example.presentation.R
import com.example.presentation.databinding.MainViewCourseItemBinding
import com.example.presentation.ui.data.CourseItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


fun courseAdapterDelegate(
    onLikeClick: (Int, Boolean) -> Unit
) = adapterDelegateViewBinding<CourseItem, CourseItem, MainViewCourseItemBinding>(
    { inflater, parent -> MainViewCourseItemBinding.inflate(inflater, parent, false) }
) {
    bind {
        binding.courseTitle.text = item.title
        binding.courseDescription.text = item.description
        binding.startDateText.text = item.startDate
        binding.coursePrice.text = item.price
        binding.rateText.text = item.rating

        val isLiked = item.isLiked

        binding.favouriteIcon.setBackgroundResource(
            if (isLiked) R.drawable.ic_liked_icon else R.drawable.ic_liked_course
        )

        binding.favouriteIcon.setOnClickListener {
            onLikeClick(item.id, !isLiked)
        }
    }
}