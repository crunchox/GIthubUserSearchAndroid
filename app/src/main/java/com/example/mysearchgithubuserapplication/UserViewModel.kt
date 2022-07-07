package com.example.mysearchgithubuserapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserViewModel : ViewModel() {
    private val modelSearchMutableLiveData = MutableLiveData<ArrayList<ModelSearchData>>()
    private val modelUserMutableLiveData = MutableLiveData<ModelUser>()
    val strApiKey = "b650046bf640e7bf7054093854b8d02a"

    fun setSearchUser(query: String?) {
        val apiService: ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        val call: Call<ModelSearch> = apiService.searchUser(strApiKey, query)
        call.enqueue(object : Callback<ModelSearch?> {
            override fun onResponse(call: Call<ModelSearch?>, response: Response<ModelSearch?>) {
                if (!response.isSuccessful) {
                    Log.e("response", response.toString())
                } else if (response.body() != null) {
                    val items: ArrayList<ModelSearchData> =
                        ArrayList(response.body()!!.getModelSearchData())
                    modelSearchMutableLiveData.value = items
                }
            }

            override fun onFailure(call: Call<ModelSearch?>, t: Throwable) {
                Log.e("failure", t.toString())
            }
        })
    }

    fun setUserDetail(username: String?) {
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        val call = apiService.detailUser(username)
        call.enqueue(object : Callback<ModelUser?> {
            override fun onResponse(call: Call<ModelUser?>, response: Response<ModelUser?>) {
                if (!response.isSuccessful) {
                    Log.e("response", response.toString())
                } else if (response.body() != null) {
                    modelUserMutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ModelUser?>, t: Throwable) {
                Log.e("failure", t.toString())
            }
        })
    }

    fun getResultList(): MutableLiveData<ArrayList<ModelSearchData>> {
        return modelSearchMutableLiveData
    }

    fun getUserDetail(): MutableLiveData<ModelUser> {
        return modelUserMutableLiveData
    }
}
