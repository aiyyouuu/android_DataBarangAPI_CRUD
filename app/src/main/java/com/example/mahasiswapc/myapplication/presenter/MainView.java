package com.example.mahasiswapc.myapplication.presenter;

import com.example.mahasiswapc.myapplication.model.getall.GetResponse;

public interface MainView {
    void getSuccess(GetResponse list);
    void setToast(String message);
    void onError(String errorMessage);
    void onFailure(String failureMessage);
}
