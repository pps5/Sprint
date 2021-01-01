package io.github.pps5.sprint.feature.main.home.view.binder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import io.github.pps5.sprint.R
import io.github.pps5.sprint.databinding.ItemDailyGoalsBinding

fun ItemDailyGoalsBinding.setupWith(adapter: RecyclerView.Adapter<*>) {

    val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            val isFirst = position == 0
            val isEnd = position == goalPager.adapter!!.itemCount - 1
            left.visibility = if (isFirst) View.INVISIBLE else View.VISIBLE
            right.visibility = if (isEnd) View.INVISIBLE else View.VISIBLE
        }
    }

    fun setupViewPager(adapter: RecyclerView.Adapter<*>) {
        goalPager.adapter = adapter
        (goalPager.getChildAt(0) as RecyclerView).let {
            it.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            // set padding to show adjacent items
            val padding = root.resources
                .getDimensionPixelOffset(R.dimen.card_horizontal_margin)
            it.setPadding(padding, 0, padding, 0)
            it.clipToPadding = false
        }
    }

    fun setupTabLayout() {
        TabLayoutMediator(tabLayout, goalPager) { tab, _ ->
            tab.view.isClickable = false
        }.attach()

        goalPager.registerOnPageChangeCallback(onPageChangeCallback)
        left.setOnClickListener { goalPager.currentItem -= 1 }
        right.setOnClickListener { goalPager.currentItem += 1 }
    }

    setupViewPager(adapter)
    setupTabLayout()
}

