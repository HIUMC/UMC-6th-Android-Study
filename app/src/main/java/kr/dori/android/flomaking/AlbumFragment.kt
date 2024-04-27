package kr.dori.android.flomaking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kr.dori.android.flomaking.databinding.FragmentAlbumBinding

class AlbumFragment : Fragment(){
    lateinit var binding: FragmentAlbumBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        binding.albumBackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm,HomeFragment()).commitAllowingStateLoss()
        }

        binding.songLalacLayout.setOnClickListener{
            Toast.makeText(activity,"LiLac", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}