package kr.dori.android.flomaking

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BannerVpAdapter(fragment: Fragment) :FragmentStateAdapter(fragment) {

    private val fragmentlist : ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int {
        return fragmentlist.size
    }

    override fun createFragment(position: Int): Fragment = fragmentlist[position]//0,1,2,3

    fun addFragment(fragment: Fragment){
        fragmentlist.add(fragment)
        notifyItemInserted(fragmentlist.size-1)
    }
}