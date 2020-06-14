package nl.donyell.m2mobi.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import nl.donyell.m2mobi.domain.interactors.GetPhotosUseCase
import nl.donyell.m2mobi.domain.interactors.RefreshPhotosUseCase
import nl.donyell.m2mobi.domain.models.Photo
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val refreshPhotosUseCase: RefreshPhotosUseCase,
    private val getPhotosUseCase: GetPhotosUseCase
) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private var _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean>
        get() = _showError


    init {
        refreshPhotos()
        getPhotos()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun onRefresh() {
        refreshPhotos()
    }

    private fun refreshPhotos() {
        _isLoading.value = true
        _showError.value = false

        refreshPhotosUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isLoading.value = false
            }, { error ->
                _isLoading.value = false
                handleError(error)
            }).let {
                compositeDisposable.add(it)
            }
    }

    private fun getPhotos() {
        getPhotosUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ photos ->
                _photos.value = photos
            }, { error ->
                handleError(error)
            }).let {
                compositeDisposable.add(it)
            }
    }

    private fun handleError(error: Throwable) {
        _showError.value = true
        Log.e("MainViewModel", error.localizedMessage!!)
    }
}