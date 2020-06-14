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
import retrofit2.HttpException
import java.net.UnknownHostException
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

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

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
        _errorMessage.value = null

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
        when (error) {
            is UnknownHostException -> {
                _errorMessage.value = "Showing offline results, please check your network connection"
            }
            is HttpException -> {
                _errorMessage.value = error.message()
            }
            else -> {
                _errorMessage.value = "Something went wrong"
                Log.d("MainViewModel",  error.localizedMessage!!)
            }
        }
    }
}