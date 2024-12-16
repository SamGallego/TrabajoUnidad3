package com.example.trabajounidad3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MiFragmento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiFragmento extends Fragment {


    private ArrayList<Plato> carta;
    private AdaptadorPlato adaptadorCarta;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MiFragmento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MiFragmento.
     */
    // TODO: Rename and change types and number of parameters
    public static MiFragmento newInstance(String param1, String param2) {
        MiFragmento fragment = new MiFragmento();
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
        View view = inflater.inflate(R.layout.fragment_mi_fragmento, container, false);

        // Inicializar la lista de platos (carta)
        carta = new ArrayList<>(Arrays.asList(
                new Plato(R.drawable.paella, "Paella", "Arroz con mariscos y azafrán", "Mariscos, Gluten", 12.0),
                new Plato(R.drawable.tortilla, "Tortilla Española", "Tortilla de papas con cebolla", "Huevos", 6.0),
                new Plato(R.drawable.gazpacho, "Gazpacho", "Sopa fría de tomate y verduras", "Gluten", 5.0),
                new Plato(R.drawable.croquetas, "Croquetas de Jamón", "Croquetas crujientes de jamón serrano", "Gluten, Lactosa", 8.0),
                new Plato(R.drawable.pulpo, "Pulpo a la Gallega", "Pulpo cocido con pimentón y aceite de oliva", "Mariscos", 14.0),
                new Plato(R.drawable.chuletillas, "Chuletillas de Cordero", "Cordero a la parrilla con guarnición", "Ninguno", 15.0),
                new Plato(R.drawable.ensalada_mixta, "Ensalada Mixta", "Lechuga, tomate, cebolla y aceitunas", "Ninguno", 4.0),
                new Plato(R.drawable.fabada_asturiana, "Fabada Asturiana", "Guiso de alubias con chorizo, morcilla y tocino", "Gluten", 10.0),
                new Plato(R.drawable.cocido_madrileno, "Cocido Madrileño", "Estofado de garbanzos, carnes y verduras", "Gluten", 12.0),
                new Plato(R.drawable.calamares_romana, "Calamares a la Romana", "Calamares rebozados y fritos", "Gluten, Mariscos", 9.0),
                new Plato(R.drawable.pisto_manchego, "Pisto Manchego", "Guiso de verduras con tomate", "Ninguno", 7.0),
                new Plato(R.drawable.sopa_castellana, "Sopa Castellana", "Sopa de ajo con pan y huevo", "Gluten", 5.0),
                new Plato(R.drawable.albondigas_salsa, "Albóndigas en Salsa", "Albóndigas de carne en salsa de tomate", "Gluten, Lactosa", 9.0),
                new Plato(R.drawable.arroz_negro, "Arroz Negro", "Arroz con tinta de calamar y mariscos", "Mariscos, Gluten", 13.0),
                new Plato(R.drawable.migas_extremenas, "Migas Extremeñas", "Pan desmigado con chorizo y panceta", "Gluten", 8.0),
                new Plato(R.drawable.rabo_toro, "Rabo de Toro", "Estofado de rabo de toro al vino tinto", "Ninguno", 14.0),
                new Plato(R.drawable.lentejas_estofadas, "Lentejas Estofadas", "Lentejas con chorizo y verduras", "Gluten", 7.0),
                new Plato(R.drawable.bacalao_al_pil_pl, "Bacalao al Pil Pil", "Bacalao con aceite de oliva y ajo", "Pescado", 15.0),
                new Plato(R.drawable.gambas_al_ajillo, "Gambas al Ajillo", "Gambas salteadas con ajo y guindilla", "Mariscos", 11.0),
                new Plato(R.drawable.churros_chocolate, "Churros con Chocolate", "Churros fritos con chocolate caliente", "Gluten", 6.0),
                new Plato(R.drawable.flan_huevo, "Flan de Huevo", "Flan de huevo con caramelo", "Lactosa, Huevos", 4.0),
                new Plato(R.drawable.tarta_santiago, "Tarta de Santiago", "Tarta de almendra típica gallega", "Frutos Secos, Gluten", 5.0),
                new Plato(R.drawable.crema_catalana, "Crema Catalana", "Postre de crema con azúcar caramelizado", "Lactosa, Huevos", 5.0),
                new Plato(R.drawable.filete_ternera, "Filete de Ternera", "Filete de ternera a la plancha con patatas", "Ninguno", 14.0),
                new Plato(R.drawable.salmon_plancha, "Salmón a la Plancha", "Salmón con verduras salteadas", "Pescado", 13.0),
                new Plato(R.drawable.canelones, "Canelones", "Canelones rellenos de carne y bechamel", "Gluten, Lactosa", 10.0),
                new Plato(R.drawable.empanada_gallega, "Empanada Gallega", "Empanada rellena de atún y tomate", "Gluten, Pescado", 7.0)
        ));

        // Configurar el RecyclerView
        RecyclerView recyclerViewCarta = view.findViewById(R.id.carta);
        recyclerViewCarta.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptadorCarta = new AdaptadorPlato(carta);
        recyclerViewCarta.setAdapter(adaptadorCarta);

        // Manejar clicks en los elementos de la carta
        adaptadorCarta.setOnItemClickListener(plato -> {
            Bundle result = new Bundle();
            result.putSerializable("plato", plato); // Usamos el objeto Plato
            getParentFragmentManager().setFragmentResult("platoSeleccionado", result);
        });

        return view;
    }


}