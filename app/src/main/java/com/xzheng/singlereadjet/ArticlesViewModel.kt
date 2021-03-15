package com.xzheng.singlereadjet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xzheng.singlereadjet.network.Article
import com.xzheng.singlereadjet.network.SingleReadApi
import kotlinx.coroutines.launch

class ArticlesViewModel : ViewModel() {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    init {
        getArticlesList()
    }

    private fun getArticlesList() {
        viewModelScope.launch {
            try {
                val response = SingleReadApi.instance.getArticles(1, 1)
                _status.value = "There are ${response.data.size} articles"
                _articles.value = response.data
            } catch (e: Exception) {
                _status.value = "Failed to get articles: ${e.localizedMessage}"
            }
        }
    }
}