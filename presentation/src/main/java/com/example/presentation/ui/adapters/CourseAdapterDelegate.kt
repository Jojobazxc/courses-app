package com.example.presentation.ui.adapters

import com.example.presentation.databinding.MainViewCourseItemBinding
import com.example.presentation.ui.data.CourseItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


fun courseAdapterDelegate() = adapterDelegateViewBinding<CourseItem, CourseItem, MainViewCourseItemBinding>(
    { inflater, parent -> MainViewCourseItemBinding.inflate(inflater, parent, false) }
) {
    bind {
        binding.courseTitle.text = item.title
        binding.courseDescription.text = item.description
        binding.startDateText.text = item.startDate
        binding.coursePrice.text = item.price
        binding.rateText.text = item.rating
    }
}