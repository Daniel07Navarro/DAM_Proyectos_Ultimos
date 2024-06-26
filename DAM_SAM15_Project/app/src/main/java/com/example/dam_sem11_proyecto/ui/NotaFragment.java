package com.example.dam_sem11_proyecto.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dam_sem11_proyecto.R;
import com.example.dam_sem11_proyecto.db.entity.NotaEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * A fragment representing a list of Items.
 */
public class NotaFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;

    private List<NotaEntity> notaList;

    private MyNotaRecyclerViewAdapter adapterNotas;



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotaFragment newInstance(int columnCount) {
        NotaFragment fragment = new NotaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
                int numeroColumnas = (int) (dpWidth / 180);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager( numeroColumnas, StaggeredGridLayoutManager.VERTICAL));
            }
            notaList = new ArrayList<>();
            notaList.add(new NotaEntity("UC4","Estudiar para la evaluación de la UC4 - Caso: Notas y Listas", true, android.R.color.holo_blue_light));
            notaList.add(new NotaEntity("Recordar", "He aparcado el coche en la calle República Argentina, no olvidarme en el parque",false, android.R.color.holo_green_light));
            notaList.add(new NotaEntity("cumpleaños (fiesta)","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum", true, android.R.color.holo_orange_light));
            notaList.add(new NotaEntity("Deporte DAM","En la semana 16 al terminar la clase, todos nos vamos a jugar futsal", true,android.R.color.holo_blue_light));
            notaList.add(new NotaEntity("Trab. Acad.", "Para el trabajo académico cada estudiante debe elaborar un video, desarrollando el caso Notas y Listas. De inicio a Fin.",false, android.R.color.holo_green_light));
            notaList.add(new NotaEntity("cumpleaños Rakauskas","Para el cumpleaños del delegado, cada estudiante debe traer un bocadito y la profesora traerá la torta para el salón. La temática de la fiesta será: GitHub for Ever", true, android.R.color.holo_orange_light));
            notaList.add(new NotaEntity("Lista de la compra","comprar pan tostado y fruta", true, android.R.color.holo_blue_light));
            adapterNotas = new MyNotaRecyclerViewAdapter(notaList, getActivity());
            recyclerView.setAdapter(adapterNotas);
        }
        return view;
    }


}