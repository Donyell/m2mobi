package nl.donyell.m2mobi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nl.donyell.m2mobi.response.GetPhotosResponse

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//            NetworkModule.service.getPhotos()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ photosResponse ->
//                    photosResponse.forEach { photo: GetPhotosResponse ->
//                        Log.d("Main", photo.title)
//                    }
//                }, {
//                    Log.d("Main", "wa3hed error: $it")
//                })
    }
}