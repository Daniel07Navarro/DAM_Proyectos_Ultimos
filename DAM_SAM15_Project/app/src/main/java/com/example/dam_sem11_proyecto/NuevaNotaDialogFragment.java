package com.example.dam_sem11_proyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.dam_sem11_proyecto.db.entity.NotaEntity;

public class NuevaNotaDialogFragment extends DialogFragment {

    //private NuevaNotaDialogViewModel mViewModel;

    private View view;


    public static NuevaNotaDialogFragment newInstance() {
        return new NuevaNotaDialogFragment();
    }


    private EditText etTitulo, etContenido;
    private RadioGroup rgColor;
    private Switch swNotaFavorita;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nueva_nota_dialog, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Nueva Nota");
        builder.setMessage("Introduzca los datos de la nueva nota")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String titulo = etTitulo.getText().toString();
                        String contenido = etContenido.getText().toString();
                        String color = "azul";
                        switch (rgColor.getCheckedRadioButtonId()){
                            case R.id.radioButtonColorRojo:
                                color = "rojo";
                                break;
                            case R.id.radioButtonColorVerde:
                                color = "verde";
                                break;
                        }
                        boolean esFavorita = swNotaFavorita.isChecked();
                        // Comunicar al mViewModel la nuevaNota
                        NuevaNotaDialogViewModel mViewModel = ViewModelProvider.of(getActivity()).get(NuevaNotaDialogViewModel.class);
                        mViewModel.insertarNota(new NotaEntity(titulo,contenido,esFavorita,color));
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });

        // Creamos un LayoutInflater
        // y obtenemos el contexto con getActivity (Porque estamos en un fragmento)
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // null porque no va a ser devuelto a otro layout raiz
        view = inflater.inflate(R.layout.fragment_nueva_nota_dialog, null);

        // Con esto tendremos acceso a esas variables que el usuario
        // haya escrito en cada uno de esos componentes
        etTitulo = view.findViewById(R.id.editTextTitulo);
        etContenido = view.findViewById(R.id.editTextContenido);
        rgColor = view.findViewById(R.id.radioGroupColor);
        swNotaFavorita = view.findViewById(R.id.switchNotaFavorita);

        // La forma de añadir esta lista al cuadro de dialogo es a través del builder
        builder.setView(view);

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
