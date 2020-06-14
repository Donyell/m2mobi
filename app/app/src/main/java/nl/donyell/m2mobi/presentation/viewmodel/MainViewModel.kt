package nl.donyell.m2mobi.presentation.viewmodel

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

    private var _showLoader = MutableLiveData(false)
    val showLoader: LiveData<Boolean>
        get() = _showLoader

    private var _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean>
        get() = _showError


    init {
        refreshPhotos(getPhotosUseCase)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    private fun refreshPhotos(getPhotosUseCase: GetPhotosUseCase) {
        _showLoader.value = true
        _showError.value = false

        getPhotosUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ photos ->
                _photos.value = photos.sortedBy { it.title }
                _showLoader.value = false
            }, { error ->
                _showError.value = true
                _showLoader.value = false
                error?.let {
                    Log.e("MainViewModel", error.localizedMessage!!)
                }
            }).let {
                compositeDisposable.add(it)
            }
    }
}