package com.example.trabajounidad3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
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

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ArrayList<Plato> carta;
    private ArrayList<Plato> pedido;
    private AdaptadorPlato adaptadorCarta;
    private AdaptadorPedido adaptadorPedido;
    private TextView totalTextView;
    private Switch ivaSwitch;
    private boolean ivaAplicado = false;
    private TabLayout tabLayout;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar datos de la carta
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

        // Guardar el precio original de cada plato
        for (Plato plato : carta) {
            plato.setPrecioOriginal(plato.getPrecio());
        }

        tabLayout = findViewById(R.id.tabLayout);
        // Añadir pestañas


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            // Acción al seleccionar una pestaña
                Log.d("TabLayout", "Tab seleccionada: " + tab.getText());
                if(tab.getPosition()==1){
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.main), "Proximamente", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            // Acción al deseleccionar una pestaña
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            // Acción al volver a seleccionar una pestaña
            }
        });








        // Inicializar vistas
        Spinner spinner = findViewById(R.id.languages);
        Button botonPedir = findViewById(R.id.pedir);
        Button botonLimpiar = findViewById(R.id.limpiar);
        ivaSwitch = findViewById(R.id.iva);
        totalTextView = findViewById(R.id.total);

        //Asocio el menu flotante
        registerForContextMenu(totalTextView);

//        //Asocio el menu contextual
//        totalTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopUpMenu(view);
//            }
//        });



        // Inicializar pedido
        pedido = new ArrayList<>();
        actualizarTotal();




        // PRUEBA SPINNER

        // Configuración del Spinner de idiomas

        // Añado el array de los idiomas al adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages, android.R.layout.simple_spinner_item);

        // Configuración de como se ven los elementos como texto
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // El spinner se llena con las opciones del adapter(array de lenguajes)
        spinner.setAdapter(adapter);

        // Listener para cambio de idioma
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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




        // Configuración del RecyclerView para la carta
        RecyclerView recyclerViewCarta = findViewById(R.id.carta);
        recyclerViewCarta.setLayoutManager(new LinearLayoutManager(this));
        adaptadorCarta = new AdaptadorPlato(carta);
        recyclerViewCarta.setAdapter(adaptadorCarta);

        // Configuración del RecyclerView para el pedido
        RecyclerView recyclerViewPedido = findViewById(R.id.pedido);
        recyclerViewPedido.setLayoutManager(new LinearLayoutManager(this));
        adaptadorPedido = new AdaptadorPedido(pedido);
        recyclerViewPedido.setAdapter(adaptadorPedido);

        // Manejar clicks en la carta
        adaptadorCarta.setOnItemClickListener(plato -> {
            pedido.add(plato);
            adaptadorPedido.notifyDataSetChanged();
            actualizarTotal();
        });


        // Manejar clicks en el pedido
        adaptadorPedido.setOnItemClickListener(plato -> {

            actualizarTotal();
        });

        // Listener del Switch IVA
        ivaSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                aplicarIva();
            } else {
                quitarIva();
            }
        });

        // Botón para realizar pedido
        botonPedir.setOnClickListener(v -> {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.main), "Su pedido ha sido realizado", Snackbar.LENGTH_LONG);
            snackbar.show();
//            Toast.makeText(MainActivity.this, "Su pedido ha sido realizado", Toast.LENGTH_SHORT).show();

            Log.i(TAG, "Orden completada");
        });

        // Botón para limpiar el pedido
        botonLimpiar.setOnClickListener(v -> {
            pedido.clear();
            adaptadorPedido.notifyDataSetChanged();
            actualizarTotal();
            Log.i(TAG, "Orden limpiada");
        });
    }

    //Insertar un menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mi_menu, menu);
        return true;
    }

    //Insertar funcionalidades a las opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        if (item.getItemId() == R.id.item){
            Snackbar snackbar = Snackbar.make(findViewById(R.id.main), "Item pulsado", Snackbar.LENGTH_LONG);
            snackbar.show();

        }else if (item.getItemId() == R.id.item2){
            Snackbar snackbar = Snackbar.make(findViewById(R.id.main), "Item2 pulsado", Snackbar.LENGTH_LONG);
            snackbar.show();

        }
        return true;
    }

//        // Método para asociar un menú emergente popup al pulsar en una vista
//    public void showPopUpMenu(View view) {
//        PopupMenu popupMenu = new PopupMenu(this,view);
//        MenuInflater menuInflater = popupMenu.getMenuInflater();
//        menuInflater.inflate(R.menu.mi_menu, popupMenu.getMenu());
//
//        // Manejador de clicks
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                Intent i;
//                if (item.getItemId() == R.id.item){
//                    Snackbar snackbar = Snackbar.make(findViewById(R.id.main), "Item recontrapulsado", Snackbar.LENGTH_LONG);
//                    snackbar.show();
//
//                }else if (item.getItemId() == R.id.item2){
//                    Snackbar snackbar = Snackbar.make(findViewById(R.id.main), "Item2 recontrapulsado", Snackbar.LENGTH_LONG);
//                    snackbar.show();
//
//                }
//                return true;
//            }
//        });
//
//        popupMenu.show();
//    }




     //Método para el menú contextual, donde sew asocia el menú contrextual al textView
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        menu.setHeaderTitle("Elija la divisa");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mi_menu, menu);
    }

    // Método para gestionar los eventos de los elementos del menú contextual
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        Intent i;
        if (item.getItemId() == R.id.item) {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.main), "Item pulsado", Snackbar.LENGTH_LONG);
            snackbar.show();

        } else if (item.getItemId() == R.id.item2) {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.main), "Item2 pulsado", Snackbar.LENGTH_LONG);
            snackbar.show();

        }
        return true;
    }


    // Método para aplicar el IVA
    private void aplicarIva() {
        if (!ivaAplicado) {
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
        }
    }

    // Método para quitar el IVA
    private void quitarIva() {
        if (ivaAplicado) {
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


    //spinner

    // Método para cambiar el idioma de la aplicación

    private void setLocale(String lang) {
        // Obtiene la configuración regional (idioma) actualmente activa en la aplicación.
        Locale currentLocale = getResources().getConfiguration().locale;

        // Verificar si el idioma seleccionado es el mismo que el actual
        if (currentLocale.getLanguage().equals(lang)) {
            Log.i(TAG, "El idioma ya está configurado: " + lang);
            return;
        }

        // Establece este nuevo Locale como el predeterminado para toda la aplicación.
        Locale locale = new Locale(lang);

        // Establece este nuevo Locale como el predeterminado para toda la aplicación.
        Locale.setDefault(locale);

        // Crea un nuevo objeto Configuration que almacena configuraciones como el idioma.
        Configuration config = new Configuration();

        // Asigna el nuevo idioma (locale) a la configuración de la aplicación.
        config.locale = locale;

        // Actualiza los recursos de la aplicación para reflejar la nueva configuración regional.
        // getDisplayMetrics asegura que las métricas de pantalla se mantengan consistentes tras el cambio de configuración.
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Reiniciar la actividad para aplicar el cambio de idioma
        recreate();
    }



}