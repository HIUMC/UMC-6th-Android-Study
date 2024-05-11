package kr.dori.android.flomaking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.tabs.TabLayoutMediator
import kr.dori.android.flomaking.databinding.FragmentAlbumBinding
import kr.dori.android.flomaking.databinding.FragmentLockerBinding

class LockerFragment : Fragment(){
    lateinit var binding: FragmentLockerBinding

    private val information = arrayListOf("저장한 곡","음악 파일")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding = FragmentLockerBinding.inflate(inflater, container, false)

//        binding.albumBackIv.setOnClickListener {
//            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm,HomeFragment()).commitAllowingStateLoss()
//        }

        val lockerAdapter = LockerVPAdapter(this)
        binding.lockerContentVp.adapter = lockerAdapter
        TabLayoutMediator(binding.lockerContentTb, binding.lockerContentVp){
                tab, position ->
            tab.text = information[position]
        }.attach()


//        binding.songLalacLayout.setOnClickListener{
//            Toast.makeText(activity,"LiLac", Toast.LENGTH_SHORT).show()
//        }

        return binding.root
    }
}