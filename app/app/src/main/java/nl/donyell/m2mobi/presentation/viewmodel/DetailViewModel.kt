package nl.donyell.m2mobi.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import nl.donyell.m2mobi.domain.interactors.GetCommentsUseCase
import nl.donyell.m2mobi.domain.interactors.request.GetCommentsRequest
import nl.donyell.m2mobi.domain.models.Comment
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val getCommentsUseCase: GetCommentsUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _comments

     var photoId = MutableLiveData<Long>()
     var title = MutableLiveData<String>()
     var imageUrl = MutableLiveData<String>()

    fun onAttach(photoId: Long, title: String, imageUrl: String){
        this.photoId.value = photoId
        this.title.value = title
        this.imageUrl.value = imageUrl

        refreshComments()
    }

    private fun refreshComments() {
        photoId.value?.let { id ->
            val request = GetCommentsRequest(id)
            getCommentsUseCase.execute(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ comments ->
                    _comments.value = comments.take(20)
                }, {
                    Log.e("DetailViewModel", "Error: $it")
                }).let {
                    compositeDisposable.add(it)
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}