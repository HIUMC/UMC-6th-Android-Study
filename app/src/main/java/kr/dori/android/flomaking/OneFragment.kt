package kr.dori.android.flomaking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.dori.android.flomaking.databinding.FragmentDetailBinding
import kr.dori.android.flomaking.databinding.FragmentMusicfileBinding
import kr.dori.android.flomaking.databinding.FragmentOneBinding

class OneFragment : Fragment() {

    lateinit var binding: FragmentOneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }
}