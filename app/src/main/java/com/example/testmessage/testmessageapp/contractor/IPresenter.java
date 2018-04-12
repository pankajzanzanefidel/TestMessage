package com.example.testmessage.testmessageapp.contractor;

public interface IPresenter<V extends IView > {
    void attach(V view);
    void dettach();
}
