package com.example.sportlife.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.FindTopService;
import com.example.sportlife.R;

import org.jspecify.annotations.NonNull;

public class ActivityInventory extends CreateActivity {

    private TextView tvPageNumber;
    private int currentPage = 1;
    private int maxPages = 2;


    @Override
    protected int getIdLayout() {
        return R.layout.activity_equipment_selection;
    }

    @Override
    protected int getIdView() {
        return R.id.activityEquipment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FindTopService findTopService=new FindTopService();
        UIController uiController=new UIController(this,null);
        CallBackHandler callBack=new CallBackHandlerImpl(uiController);
        findTopService.findTop(callBack);


        tvPageNumber = findViewById(R.id.tvPageNumber);

        // Инициализация карточек
        setupCard(R.id.cardDumbbells);
        setupCard(R.id.cardBarbell);
        setupCard(R.id.cardPullUpBar);
        setupCard(R.id.cardTreadmill);
        setupCard(R.id.cardKettlebell);
        setupCard(R.id.cardBench);
        setupCard(R.id.cardRopes);
        setupCard(R.id.cardElliptical);


        // Стрелки
        findViewById(R.id.btnPrev).setOnClickListener(v -> switchPage(-1));
        findViewById(R.id.btnNext).setOnClickListener(v -> switchPage(1));


        Button back=this.findViewById(R.id.btnBack);
        Button save=this.findViewById(R.id.btnSave);

        back.setOnClickListener(v->{
            callBack.onSuccess(null);//назад
        });
        save.setOnClickListener(v->{
            callBack.onSuccess(null);//сохранить
        });
    }


    private void setupCard(int cardId) {
        CardView card = findViewById(cardId);
        if (card != null) {
            card.setOnClickListener(v -> {
                // Просто переключаем состояние
                boolean isSelected = !card.isSelected();
                card.setSelected(isSelected);
            });
        }
    }


    private void switchPage(int direction) {
        currentPage += direction;
        if (currentPage < 1) currentPage = 1;
        if (currentPage > maxPages) currentPage = maxPages;

        tvPageNumber.setText(String.valueOf(currentPage));
        showCardsForPage(currentPage);
    }

    private void showCardsForPage(int page) {
        // Сначала скрываем ВСЕ карточки
        hideAllCards();

        // Потом показываем нужные
        if (page == 1) {
            findViewById(R.id.cardDumbbells).setVisibility(View.VISIBLE);
            findViewById(R.id.cardBarbell).setVisibility(View.VISIBLE);
            findViewById(R.id.cardPullUpBar).setVisibility(View.VISIBLE);
            findViewById(R.id.cardTreadmill).setVisibility(View.VISIBLE);
        } else if (page == 2) {
            findViewById(R.id.cardKettlebell).setVisibility(View.VISIBLE);
            findViewById(R.id.cardBench).setVisibility(View.VISIBLE);
            findViewById(R.id.cardRopes).setVisibility(View.VISIBLE);
            findViewById(R.id.cardElliptical).setVisibility(View.VISIBLE);
        }
    }

    private void hideAllCards() {
        findViewById(R.id.cardDumbbells).setVisibility(View.GONE);
        findViewById(R.id.cardBarbell).setVisibility(View.GONE);
        findViewById(R.id.cardPullUpBar).setVisibility(View.GONE);
        findViewById(R.id.cardTreadmill).setVisibility(View.GONE);
        findViewById(R.id.cardKettlebell).setVisibility(View.GONE);
        findViewById(R.id.cardBench).setVisibility(View.GONE);
        findViewById(R.id.cardRopes).setVisibility(View.GONE);
        findViewById(R.id.cardElliptical).setVisibility(View.GONE);
    }
}
