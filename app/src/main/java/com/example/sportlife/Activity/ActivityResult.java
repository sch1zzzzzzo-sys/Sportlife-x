package com.example.sportlife.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sportlife.Activity.CreateActivity;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.FindTopService;
import com.example.sportlife.R;

public class ActivityResult extends CreateActivity {

    private RecyclerView recyclerView;


    @Override
    protected int getIdLayout() {
        return R.layout.activity_result;
    }

    @Override
    protected int getIdView() {
        return R.id.activityResult;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FindTopService findTopService = new FindTopService();
        UIController uiController = new UIController(this, null);
        CallBackHandler callBack = new CallBackHandlerImpl(uiController);
        findTopService.findTop(callBack);


        setupFavoriteListeners();

        Button back = this.findViewById(R.id.btnBack);
        Button save = this.findViewById(R.id.btnSave);

        back.setOnClickListener(v -> {
            callBack.onSuccess(null);//назад
        });
        save.setOnClickListener(v -> {
            callBack.onSuccess(null);//сохранить
        });
    }

    // Метод для установки слушателей на сердечки
    private void setupFavoriteListeners() {
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View child = recyclerView.getChildAt(i);
            if (child != null) {
                CheckBox chkFavorite = child.findViewById(R.id.chkFavorite);
                if (chkFavorite != null) {
                    final int position = i;

                    // Снимаем старые слушатели (чтобы не дублировались)
                    chkFavorite.setOnCheckedChangeListener(null);

                    chkFavorite.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        handleFavoriteClick(position, isChecked);
                    });
                }
            }
        }
    }

    private void handleFavoriteClick(int position, boolean isFavorite) {
        //Здесь нужно сделать обработку нажатия


        // Перенастраиваем слушатели после обновления
        setupFavoriteListeners();
    }


}