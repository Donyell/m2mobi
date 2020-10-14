package nl.donyell.m2mobi.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import nl.donyell.m2mobi.domain.interactors.usecase.GetPhotosUseCase
import nl.donyell.m2mobi.domain.interactors.usecase.RefreshPhotosUseCase
import nl.donyell.m2mobi.domain.models.Photo
import nl.donyell.m2mobi.presentation.navigator.MainNavigationAction
import nl.donyell.m2mobi.presentation.navigator.MainNavigationAction.OpenDetail
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val refreshPhotosUseCase: RefreshPhotosUseCase,
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> = _photos

    private var _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private var _navigator = MutableLiveData<MainNavigationAction>()
    val navigation: LiveData<MainNavigationAction> = _navigator

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

    fun onPhotoClicked(photo: Photo) {
        _navigator.postValue(OpenDetail(photo))
    }

    private fun refreshPhotos() {
        _errorMessage.value = null

        refreshPhotosUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _isLoading.value = true }
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
        getPhotosUseCase()
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
        when (error) {
            is UnknownHostException -> {
                _errorMessage.value =
                    "Showing offline results, please check your network connection"
            }
            is HttpException -> {
                _errorMessage.value = error.message()
            }
            else -> {
                _errorMessage.value = "Something went wrong"
                Log.d("MainViewModel", error.localizedMessage!!)
            }
        }
    }
}
