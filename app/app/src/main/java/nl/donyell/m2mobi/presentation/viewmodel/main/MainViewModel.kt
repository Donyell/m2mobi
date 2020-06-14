package nl.donyell.m2mobi.presentation.viewmodel.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import nl.donyell.m2mobi.domain.interactors.GetPhotosUseCase
import nl.donyell.m2mobi.domain.models.Photo
import javax.inject.Inject

class MainViewModel @Inject constructor(getPhotosUseCase: GetPhotosUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    init {
        refreshPhotos(getPhotosUseCase)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    private fun refreshPhotos(getPhotosUseCase: GetPhotosUseCase) {
        getPhotosUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ photos ->
                _photos.value = photos.sortedBy { it.title }
            }, {
                Log.e("MainViewModel", "Error: $it")
            }).let {
                compositeDisposable.add(it)
            }
    }
}