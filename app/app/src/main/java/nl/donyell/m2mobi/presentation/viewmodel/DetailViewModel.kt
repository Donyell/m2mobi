package nl.donyell.m2mobi.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import nl.donyell.m2mobi.domain.interactors.request.GetCommentsRequest
import nl.donyell.m2mobi.domain.interactors.usecase.GetCommentsUseCase
import nl.donyell.m2mobi.domain.models.Comment
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> = _comments

    private var _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var photoId: Long? = null

    private var _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private var _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> = _imageUrl

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun onAttach(photoId: Long, title: String, imageUrl: String) {
        this.photoId = photoId

        this._title.value = title
        this._imageUrl.value = imageUrl

        refreshComments()
    }

    fun onRefresh() {
        refreshComments()
    }

    private fun refreshComments() {
        photoId?.let { id ->
            val request = GetCommentsRequest(id)
            getCommentsUseCase(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _isLoading.value = true }
                .subscribe({ comments ->
                    _comments.value = comments.sortedBy { it.name }.take(20)
                    _isLoading.value = false
                }, { error ->
                    _isLoading.value = false
                    handleError(error)

                }).let {
                    compositeDisposable.add(it)
                }
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
