package com.example.smartgallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText autor, titulo, tecnica, categoria, descripcion, anio;
    private Button btnSave,btnView;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        autor = view.findViewById(R.id.editAutorRegister);
        titulo = view.findViewById(R.id.editTituloRegister);
        tecnica = view.findViewById(R.id.editTecnicaRegister);
        categoria = view.findViewById(R.id.editCategoriaRegister);
        descripcion = view.findViewById(R.id.editDescripcionRegister);
        anio = view.findViewById(R.id.editAnioRegister);
        btnSave = view.findViewById(R.id.btnSaveRegister);
        btnView = view.findViewById(R.id.btnViewRegister);

        btnSave.setOnClickListener(v -> savePainting());
        btnView.setOnClickListener(v -> showViewFragment());

        return view;
    }

    private void savePainting() {
        String data = autor.getText().toString() + ","
                + titulo.getText().toString() + ","
                + tecnica.getText().toString() + ","
                + categoria.getText().toString() + ","
                + descripcion.getText().toString() + ","
                + anio.getText().toString();

        try {
            FileOutputStream fos = requireContext().openFileOutput("painting.txt", getContext().MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
            Toast.makeText(getActivity(), "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
    }

    private void showViewFragment() {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, new ViewFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}