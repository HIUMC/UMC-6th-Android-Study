package kr.dori.android.flomaking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.tabs.TabLayoutMediator
import kr.dori.android.flomaking.databinding.FragmentAlbumBinding

class AlbumFragment : Fragment(){
    lateinit var binding: FragmentAlbumBinding

    private val information = arrayListOf("수록곡", "상세정보", "영상")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        binding.albumBackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm,HomeFragment()).commitAllowingStateLoss()
        }

        val albumAdapter = AlbumVPAdapter(this)
        binding.albumContentVp.adapter = albumAdapter
        TabLayoutMediator(binding.albumContentIb, binding.albumContentVp){
            tab, position ->
            tab.text = information[position]
        }.attach()


//        binding.songLalacLayout.setOnClickListener{
//            Toast.makeText(activity,"LiLac", Toast.LENGTH_SHORT).show()
//        }

        return binding.root
    }
}