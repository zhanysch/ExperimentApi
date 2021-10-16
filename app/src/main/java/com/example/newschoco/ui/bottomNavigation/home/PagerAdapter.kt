package com.example.newschoco.ui.bottomNavigation.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

class PagerAdapter(f : FragmentManager) : FragmentStatePagerAdapter(f)  {

    private var list = arrayListOf<Fragment>()
    private val titles = arrayListOf<String>()

    fun addFragments(fragment : Fragment, title : String){
        list.add(fragment)
        titles.add(title)
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int)= titles[position]

    override fun getItem(position: Int)=list[position]
    override fun getCount()=  list.size
}