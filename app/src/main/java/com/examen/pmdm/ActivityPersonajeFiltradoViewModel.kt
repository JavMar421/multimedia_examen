package com.examen.pmdm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityPersonajeFiltradoViewModel : ViewModel() {
    private val _isVisible by lazy { MediatorLiveData<Boolean>() }
    val isVisible: LiveData<Boolean>
        get() = _isVisible
    private val _responseText by lazy { MediatorLiveData<String>() }
    val responseText: LiveData<String>
        get() = _responseText

    private val _responseList by lazy { MediatorLiveData<List<Result>>() }
    val responseList: LiveData<List<Result>>
        get() = _responseList


    suspend fun setIsVisibleInMainThread(value: Boolean) = withContext(Dispatchers.Main) {
        _isVisible.value = value
    }

    suspend fun setResponseTextInMainThread(value: String) = withContext(Dispatchers.Main) {
        _responseText.value = value
    }

    suspend fun setResponseListInMainThread(value: List<Result>) = withContext(Dispatchers.Main) {
        _responseList.value = value
    }


    fun descargarPersonas() {
        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                setIsVisibleInMainThread(true)
                setResponseListInMainThread(listapersonajes!!)
                setResponseTextInMainThread("Hay "+responseList.value!!.size )
                setIsVisibleInMainThread(false)
            }
        }
    }


}