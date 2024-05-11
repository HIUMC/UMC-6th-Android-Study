package kr.dori.android.flomaking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.dori.android.flomaking.databinding.FragmentBannerBinding

class BannerFragment(val imgRes : Int) : Fragment() {

    lateinit var binding : FragmentBannerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding = FragmentBannerBinding.inflate(inflater, container, false)

        binding.bannerImageIv.setImageResource(imgRes)
        return binding.root
    }
}