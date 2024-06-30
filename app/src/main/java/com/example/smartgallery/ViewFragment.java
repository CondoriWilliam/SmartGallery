package com.example.smartgallery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView autor, titulo, tecnica, categoria, descripcion, anio;
    private Button btnBack;

    public ViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewFragment newInstance(String param1, String param2) {
        ViewFragment fragment = new ViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        autor = view.findViewById(R.id.edtAuthorView);
        titulo = view.findViewById(R.id.edtTitleView);
        tecnica = view.findViewById(R.id.edtTechniqueView);
        categoria = view.findViewById(R.id.edtCategoryView);
        descripcion = view.findViewById(R.id.edtDescriptionView);
        anio = view.findViewById(R.id.edtYearView);
        btnBack = view.findViewById(R.id.btnBack);
        showLastPainting();
        btnBack.setOnClickListener(v -> navigateBack());
        return view;
    }

    private void showLastPainting() {
        try {
            FileInputStream fis = requireContext().openFileInput("painting.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String line = br.readLine();

            if (line != null) {
                String[] paintingData = line.split(",");
                if (paintingData.length == 6) {
                    autor.setText("Author: " + paintingData[0]);
                    titulo.setText("Title: " + paintingData[1]);
                    tecnica.setText("Technique: " + paintingData[2]);
                    categoria.setText("Category: " + paintingData[3]);
                    descripcion.setText("Description: " + paintingData[4]);
                    anio.setText("Year: " + paintingData[5]);
                }
            }

            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void navigateBack() {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, new RegisterFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}