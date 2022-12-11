package com.hrdijital.evdekiokulum.menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.hrdijital.evdekiokulum.R;
import com.hrdijital.evdekiokulum.adapters.adapter.MainRecyclerAdapter;
import com.hrdijital.evdekiokulum.models.model.AllCategory;
import com.hrdijital.evdekiokulum.models.model.CategoryItem;

import java.util.ArrayList;
import java.util.List;


public class homepage extends Fragment {





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {




        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_homepage, container, false);
        RecyclerView mainCategoryRecycler;
        MainRecyclerAdapter mainRecyclerAdapter;


        List<CategoryItem> categoryItemList = new ArrayList<>();
        categoryItemList.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList.add(new CategoryItem(1, R.drawable.ss1));

        // added in second category
        List<CategoryItem> categoryItemList2 = new ArrayList<>();
        categoryItemList2.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList2.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList2.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList2.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList2.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList2.add(new CategoryItem(1, R.drawable.ss1));

        // added in 3rd category
        List<CategoryItem> categoryItemList3 = new ArrayList<>();
        categoryItemList3.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList3.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList3.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList3.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList3.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList3.add(new CategoryItem(1, R.drawable.ss1));

        // added in 4th category
        List<CategoryItem> categoryItemList4 = new ArrayList<>();
        categoryItemList4.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList4.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList4.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList4.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList4.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList4.add(new CategoryItem(1, R.drawable.ss1));


        // added in 5th category
        List<CategoryItem> categoryItemList5 = new ArrayList<>();
        categoryItemList5.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList5.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList5.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList5.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList5.add(new CategoryItem(1, R.drawable.ss1));
        categoryItemList5.add(new CategoryItem(1, R.drawable.ss1));


        List<AllCategory> allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory("Yaklaşan Dersler", categoryItemList));
            allCategoryList.add(new AllCategory("Özel Dersler", categoryItemList2));
        allCategoryList.add(new AllCategory("Öğrenme Merkezleri", categoryItemList3));
        allCategoryList.add(new AllCategory("Kayıtlı Dersler", categoryItemList4));
        allCategoryList.add(new AllCategory("Özel Dersler", categoryItemList5));


        mainCategoryRecycler = view.findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mainCategoryRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(getActivity(), allCategoryList);
        mainCategoryRecycler.setAdapter(mainRecyclerAdapter);
        return view;
    }



}