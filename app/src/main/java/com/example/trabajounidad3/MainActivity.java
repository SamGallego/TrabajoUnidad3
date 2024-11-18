package com.example.trabajounidad3;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String PREFERENCES_NAME = "settings";
    private static final String LANGUAGE_KEY = "language";

    private ArrayList<Plato> carta;
    private ArrayList<Plato> pedido;
    private AdaptadorPlato adaptadorCarta;
    private AdaptadorPedido adaptadorPedido;
    private TextView totalTextView;
    private Switch ivaSwitch;
    private boolean ivaAplicado = false; // Variable para controlar si el IVA está aplicado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new androidx.core.view.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            }
        });

        carta = new ArrayList<>(Arrays.asList(
                new Plato(R.drawable.paella, "Paella", "Arroz con mariscos y azafrán", "Mariscos, Gluten", 12.0),
                new Plato(R.drawable.tortilla, "Tortilla Española", "Tortilla de papas con cebolla", "Huevos", 6.0),
                new Plato(R.drawable.gazpacho, "Gazpacho", "Sopa fría de tomate y verduras", "Gluten", 5.0),
                new Plato(R.drawable.croquetas, "Croquetas de Jamón", "Croquetas crujientes de jamón serrano", "Gluten, Lactosa", 8.0),
                new Plato(R.drawable.pulpo, "Pulpo a la Gallega", "Pulpo cocido con pimentón y aceite de oliva", "Mariscos", 14.0),
                new Plato(R.drawable.chuletillas, "Chuletillas de Cordero", "Cordero a la parrilla con guarnición", "Ninguno", 15.0),
                new Plato(R.drawable.ensalada_mixta, "Ensalada Mixta", "Lechuga, tomate, cebolla y aceitunas", "Ninguno", 4.0)
        ));

        // Guardar el precio original de cada plato
        for (Plato plato : carta) {
            plato.setPrecioOriginal(plato.getPrecio());
        }

        Spinner languageSpinner = findViewById(R.id.language_spinner);
        Button botonPedir = findViewById(R.id.pedir);
        Button botonLimpiar = findViewById(R.id.limpiar);
        ivaSwitch = findViewById(R.id.iva);

        pedido = new ArrayList<>();
        totalTextView = findViewById(R.id.total);
        actualizarTotal();

        // Configuración del RecyclerView para carta
        RecyclerView recyclerViewCarta = findViewById(R.id.carta);
        recyclerViewCarta.setLayoutManager(new LinearLayoutManager(this));
        adaptadorCarta = new AdaptadorPlato(carta);
        recyclerViewCarta.setAdapter(adaptadorCarta);

        // Configuración del RecyclerView para pedido
        RecyclerView recyclerViewPedido = findViewById(R.id.pedido);
        recyclerViewPedido.setLayoutManager(new LinearLayoutManager(this));
        adaptadorPedido = new AdaptadorPedido(pedido);
        recyclerViewPedido.setAdapter(adaptadorPedido);

        // Manejar clics en los elementos de carta
        adaptadorCarta.setOnItemClickListener(new AdaptadorPlato.OnItemClickListener() {
            @Override
            public void onItemClick(Plato plato) {
                pedido.add(plato);
                adaptadorPedido.notifyDataSetChanged(); // Actualizar la vista de pedido
                actualizarTotal(); // Actualizar el total después de agregar un plato
            }
        });


        // Agregar Listener al Switch IVA
        ivaSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aplicarIva();
                } else {
                    quitarIva();
                }
            }
        });

        // Configurar el listener para mostrar el Toast
        botonPedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Su pedido ha sido realizado", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Orden completada");
            }
        });

        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedido.clear();
                adaptadorPedido.notifyDataSetChanged();
                actualizarTotal();
                Log.i(TAG, "Orden limpiada");
            }
        });

    }

    // Método para aplicar el IVA
    private void aplicarIva() {
        if (!ivaAplicado) {
            try {
                for (Plato plato : carta) {
                    plato.setPrecio(plato.getPrecioOriginal() * 1.10);
                }
                for (Plato plato : pedido) {
                    plato.setPrecio(plato.getPrecioOriginal() * 1.10);
                }
                ivaAplicado = true;
                adaptadorCarta.notifyDataSetChanged();
                adaptadorPedido.notifyDataSetChanged();
                actualizarTotal();
                Log.i(TAG, "IVA aplicado");
            } catch (Exception e) {
                Log.e(TAG, "Error aplicando IVA", e);
            }
        }
    }

    // Método para quitar el IVA
    private void quitarIva() {
        if (ivaAplicado) {
            try {
                for (Plato plato : carta) {
                    plato.setPrecio(plato.getPrecioOriginal());
                }
                for (Plato plato : pedido) {
                    plato.setPrecio(plato.getPrecioOriginal());
                }
                ivaAplicado = false;
                adaptadorCarta.notifyDataSetChanged();
                adaptadorPedido.notifyDataSetChanged();
                actualizarTotal();
                Log.i(TAG, "IVA quitado");
            } catch (Exception e) {
                Log.e(TAG, "Error quitando IVA", e);
            }
        }
    }

    // Método para actualizar el total
    private void actualizarTotal() {
        double total = 0;
        for (Plato plato : pedido) {
            total += plato.getPrecio();
        }
        totalTextView.setText(String.format("Total: %.2f €", total));
    }


}

