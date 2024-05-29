package kr.dori.android.flomaking

import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kr.dori.android.flomaking.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity(){

    lateinit var binding : ActivitySongBinding
    lateinit var song : Song
    lateinit var timer: Timer
    private var mediaPlayer: MediaPlayer? = null
    private var gson: Gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSong()
        setPlayer(song)

        binding.songDownIb.setOnClickListener{
            finish()
        }
        binding.songMiniplayerIv.setOnClickListener{
            setPlayerStatus(false)
        }
        binding.songPauseIv.setOnClickListener{
            setPlayerStatus(true)
        }
    }
    private fun initSong(){
        if(intent.hasExtra("title") && intent.hasExtra("singer")){
            song= Song(
                intent.getStringExtra("title")!!,
                intent.getStringExtra("singer")!!,
                intent.getIntExtra("second",0),
                intent.getIntExtra("playTime",0),
                intent.getBooleanExtra("isPlaying",false),
                intent.getStringExtra("music")!!

            )
        }
        startTimer()
    }

    private fun setPlayer(song: Song){
        binding.songMusicTitleTv.text=intent.getStringExtra("title")!!
        binding.songSingerNameTv.text=intent.getStringExtra("singer")!!
        binding.songStartTimeTv.text = String.format("%02d:%02d",song.second/60, song.second % 60 )
        binding.songEndTimeTv.text = String.format("%02d:%02d",song.playTime/60, song.playTime % 60 )
        binding.songProgressSb.progress = (song.second * 1000 / song.playTime)
        val music = resources.getIdentifier(song.music,"raw",this.packageName)

        mediaPlayer = MediaPlayer.create(this,music)
        setPlayerStatus(song.isPlaying)

    }

    private fun setPlayerStatus(isPlaying : Boolean){
        song.isPlaying = isPlaying
        timer.isPlaying = isPlaying

        if(isPlaying){
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
            mediaPlayer?.start()
        }
        else{
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
            if(mediaPlayer?.isPlaying==true){
                mediaPlayer?.pause()
            }
       }
    }

    private fun startTimer(){
        timer = Timer(song.playTime, song.isPlaying)
        timer.start()
    }

    inner class Timer(private val playTime: Int, var isPlaying: Boolean = true):Thread(){

        private var second : Int = 0
        private var mills : Float = 0f

        override fun run(){
            super.run()
            try{
                while(true){

                    if (second >= playTime){
                        break
                    }
                    if (isPlaying){
                        sleep(50)
                        mills += 50

                        runOnUiThread(){
                            binding.songProgressSb.progress = ((mills / playTime)*100).toInt()
                        }

                        if (mills % 1000 == 0f){
                            runOnUiThread(){
                                binding.songStartTimeTv.text = String.format("%02d:%02d",second/60, second % 60 )
                            }
                            second++

                        }

                    }
                }

            }catch(e: InterruptedException){
                Log.d("Song","쓰레드가 죽었습니다.${e.message }")
            }

        }

    }
    //사용자가 포커싱을 잃었을 때 음악이 중지
    override fun onPause(){
        super.onPause()
        setPlayerStatus(false)
        song.second = ((binding.songProgressSb.progress*song.playTime)/100)/1000
        val sharedPreferences = getSharedPreferences("song", MODE_PRIVATE) //sharedPreferences(임시 저장소) 이름이 song
        val editor = sharedPreferences.edit() //에디터
        val songJson = gson.toJson(song) //gson이 song이라는 데이터를 json형식으로 포멧시켜준다.
        editor.putString("songData",songJson) //저장소 안에 있는 데이터 이름이 songData

        editor.apply() //same as git push
    }

    override fun onDestroy(){
        super.onDestroy()
        timer.interrupt()
        mediaPlayer?.release() //미디어 플레 이어가 갖고 있는 리소스 해제
        mediaPlayer =null
    }
}

