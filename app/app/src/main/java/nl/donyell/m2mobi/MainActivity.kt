package nl.donyell.m2mobi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import nl.donyell.m2mobi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

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