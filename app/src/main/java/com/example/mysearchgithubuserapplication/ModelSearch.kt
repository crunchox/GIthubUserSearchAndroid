package com.example.mysearchgithubuserapplication

import com.google.gson.annotations.SerializedName

class ModelSearch {
    @SerializedName("items")
    private var modelSearchData: ArrayList<ModelSearchData>? = null

    fun getModelSearchData(): ArrayList<ModelSearchData> {
        return modelSearchData!!
    }

    fun setModelSearchData(modelSearchData: ArrayList<ModelSearchData>) {
        this.modelSearchData = modelSearchData
    }
}
