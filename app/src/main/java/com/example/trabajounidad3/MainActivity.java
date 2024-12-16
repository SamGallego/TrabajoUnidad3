package com.example.trabajounidad3;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ArrayList<Plato> pedido;
    private AdaptadorPedido adaptadorPedido;
    private TextView totalTextView;
    private Switch ivaSwitch;
    private boolean ivaAplicado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            );
            return insets;
        });

        // Inicializar vistas
        Spinner spinner = findViewById(R.id.languages);
        Button botonPedir = findViewById(R.id.pedir);
        Button botonLimpiar = findViewById(R.id.limpiar);
        ivaSwitch = findViewById(R.id.iva);
        totalTextView = findViewById(R.id.total);

        // Inicializar pedido
        pedido = new ArrayList<>();
        RecyclerView recyclerViewPedido = findViewById(R.id.pedido);
        recyclerViewPedido.setLayoutManager(new LinearLayoutManager(this));
        adaptadorPedido = new AdaptadorPedido(pedido);
        recyclerViewPedido.setAdapter(adaptadorPedido);

        // Configurar Spinner de idiomas
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.languages, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                switch (position) {
                    case 0:
                        setLocale("en");
                        break;
                    case 1:
                        setLocale("es");
                        break;
                    case 2:
                        setLocale("gl");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });

        // Listener del Switch IVA
        ivaSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                aplicarIva();
                Toast.makeText(MainActivity.this, "IVA aplicado", Toast.LENGTH_SHORT).show();
            } else {
                quitarIva();
                Toast.makeText(MainActivity.this, "IVA desactivado", Toast.LENGTH_SHORT).show();
            }
        });

        // Botón para realizar pedido
        botonPedir.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Su pedido ha sido realizado", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Orden completada");
        });

        // Botón para limpiar el pedido
        botonLimpiar.setOnClickListener(v -> {
            pedido.clear();
            adaptadorPedido.notifyDataSetChanged();
            actualizarTotal();
            Log.i(TAG, "Orden limpiada");
        });

        // Configurar listener para recibir datos del Fragment
        getSupportFragmentManager().setFragmentResultListener("platoSeleccionado", this, (requestKey, result) -> {
            if (result.containsKey("plato")) {
                Plato plato = (Plato) result.getSerializable("plato");
                if (plato != null) {
                    agregarPlatoAPedido(plato);
                }
            }
        });

        // Cargar el fragmento
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentoCarta, new MiFragmento())
                .commit();
    }

    private void agregarPlatoAPedido(Plato plato) {
        pedido.add(plato);
        adaptadorPedido.notifyDataSetChanged();
        actualizarTotal();
        Toast.makeText(this, plato.getNombre() + " añadido al pedido", Toast.LENGTH_SHORT).show();
    }

    private void aplicarIva() {
        if (!ivaAplicado) {
            for (Plato plato : pedido) {
                plato.setPrecio(plato.getPrecioOriginal() * 1.10);
            }
            ivaAplicado = true;
            adaptadorPedido.notifyDataSetChanged();
            actualizarTotal();
        }
    }

    private void quitarIva() {
        if (ivaAplicado) {
            for (Plato plato : pedido) {
                plato.setPrecio(plato.getPrecioOriginal());
            }
            ivaAplicado = false;
            adaptadorPedido.notifyDataSetChanged();
            actualizarTotal();
        }
    }

    private void actualizarTotal() {
        double total = 0;
        for (Plato plato : pedido) {
            total += plato.getPrecio();
        }
        totalTextView.setText(String.format("Total: %.2f €", total));
    }

    private void setLocale(String lang) {
        String currentLang = getResources().getConfiguration().locale.getLanguage();
        if (!currentLang.equals(lang)) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());
            recreate();
        }
    }
}
