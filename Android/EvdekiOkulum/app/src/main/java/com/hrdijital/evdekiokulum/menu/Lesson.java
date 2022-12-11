package com.hrdijital.evdekiokulum.menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hrdijital.evdekiokulum.R;
import com.hrdijital.evdekiokulum.adapters.adapterSearch.RecyclerviewAdapter;
import com.hrdijital.evdekiokulum.models.modelSearch.UserData;

import java.util.ArrayList;
import java.util.List;


public class Lesson extends Fragment {


    RecyclerView userRecycler;
    RecyclerviewAdapter recyclerviewAdapter;
    EditText searchView;
    CharSequence search = "";
    View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lesson, container, false);
        searchView = view.findViewById(R.id.search_bar);

        List<UserData> userDataList = new ArrayList<>();
        userDataList.add(new UserData("Lgs 2021`de başarıya beraber yürüyoruz","99 TL", R.drawable.ss1));
        userDataList.add(new UserData("Lgs 2021`de başarıya beraber yürüyoruz","99 TL", R.drawable.ss1));
        userDataList.add(new UserData("Lgs 2021`de başarıya beraber yürüyoruz","99 TL", R.drawable.ss1));
        userDataList.add(new UserData("Lgs 2021`de başarıya beraber yürüyoruz","99 TL", R.drawable.ss1));
        userDataList.add(new UserData("Lgs 2021`de başarıya beraber yürüyoruz","99 TL", R.drawable.ss1));
        userDataList.add(new UserData("Lgs 2021`de başarıya beraber yürüyoruz","99 TL", R.drawable.ss1));
        userDataList.add(new UserData("Lgs 2021`de başarıya beraber yürüyoruz","99 TL", R.drawable.ss1));
        userDataList.add(new UserData("Lgs 2021`de başarıya beraber yürüyoruz","99 TL", R.drawable.ss1));

        setUserRecycler(userDataList);


        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                recyclerviewAdapter.getFilter().filter(charSequence);
                search = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return view;
}


    private  void  setUserRecycler(List<UserData> userDataList){
        userRecycler = view.findViewById(R.id.userRecycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        userRecycler.setLayoutManager(layoutManager);
        recyclerviewAdapter = new RecyclerviewAdapter(getActivity(), userDataList);
        userRecycler.setAdapter(recyclerviewAdapter);
    }


}