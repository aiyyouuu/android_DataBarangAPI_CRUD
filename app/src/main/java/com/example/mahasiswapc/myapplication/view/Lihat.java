package com.example.mahasiswapc.myapplication.view;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahasiswapc.myapplication.Adapter.ItemAdapter;
import com.example.mahasiswapc.myapplication.R;
import com.example.mahasiswapc.myapplication.model.getall.DataItem;
import com.example.mahasiswapc.myapplication.model.getall.GetResponse;
import com.example.mahasiswapc.myapplication.presenter.MainPresenter;
import com.example.mahasiswapc.myapplication.presenter.MainView;

import java.util.ArrayList;
import java.util.List;

public class Lihat extends AppCompatActivity implements MainView, ItemAdapter.OnAdapterClickListener {
    private RecyclerView recyclerView;
    private ItemAdapter itemsAdapter;
    private MainPresenter presenter;
    private List<DataItem> list;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_items);
        itemsAdapter = new ItemAdapter(this, list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemsAdapter);
        presenter = new MainPresenter(this);
        presenter.getAllItems();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchfile, menu);
        final MenuItem myActionMenuItem = menu.findItem(R.id.search);
        searchView = (android.support.v7.widget.SearchView) myActionMenuItem.getActionView();
        changeSearchViewTextColor(searchView);
        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.white));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    private List<DataItem> filter (List<DataItem> p1, String query){
        query = query.toLowerCase();
        final List<DataItem> filteredModeList = new ArrayList<>();
        for (DataItem model:p1){
            final String text = model.getName().toLowerCase();
            if (text.startsWith(query))
            {
                filteredModeList.add(model);
            }
        }
        return filteredModeList;
    }

    private void changeSearchViewTextColor(View view){
        if (view != null){
            if (view instanceof TextView){
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            }else if (view instanceof ViewGroup){
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++ ){
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }
        private void newItemsDialog() {
            LayoutInflater factory = LayoutInflater.from(this);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Tambah Barang");
            final View textEntryView = factory.inflate(R.layout.text_entry, null);
            final EditText name = (EditText)
                    textEntryView.findViewById(R.id.edt_name);
            final EditText description = (EditText)
                    textEntryView.findViewById(R.id.edt_description);
            name.setHint("Nama Barang");
            description.setHint("Deskripsi");
            name.setText("", TextView.BufferType.EDITABLE);
            description.setText("", TextView.BufferType.EDITABLE);
            builder.setView(textEntryView);
            builder.setPositiveButton("ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!name.getText().toString().equals("")) {
                        presenter.createItems(name.getText().toString(),
                                description.getText().toString());
                    } else {
                        Toast.makeText(Lihat.this, "Masukkan Nama Barang", Toast.LENGTH_SHORT).show();
                    }
                }
            });
                    builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            builder.show();
            }
        private void deleteDialog(final String id) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Apakah kita Benar Akan Menghapus Item ini?");
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    presenter.deleteItems(id);
                }
            });
            builder.setNegativeButton("Tidak", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            builder.show();
        }
        private void editDialog(final String id, final String name, final String
                description) {
            LayoutInflater factory = LayoutInflater.from(this);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Tambah Barang");
            final View textEntryView = factory.inflate(R.layout.text_entry, null);
            final EditText edtName = (EditText)
                    textEntryView.findViewById(R.id.edt_name);
            final EditText edtDescription = (EditText)
                    textEntryView.findViewById(R.id.edt_description);
            edtName.setText(name, TextView.BufferType.EDITABLE);
            edtDescription.setText(description, TextView.BufferType.EDITABLE);
            builder.setView(textEntryView);
            builder.setTitle("Update Barang");
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    presenter.updateItems(id, edtName.getText().toString(),
                            edtDescription.getText().toString());
                }
            });
            builder.setNegativeButton("Tidak", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            builder.show();
        }

        @Override
        protected void onResume() {
            super.onResume();
            presenter.getAllItems();
        }
        @Override
        public void onClicked(String id, String name, String description, String key) {
            if (key.equalsIgnoreCase("edit")) {
                editDialog(id, name, description);
            } else {
                deleteDialog(id);
            }
        }

        @Override
        public void getSuccess(GetResponse list) {
            this.list.clear();
            this.list.addAll(list.getData());
            itemsAdapter.notifyDataSetChanged();
        }

        @Override
        public void setToast(String message) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            presenter.getAllItems();
        }

        @Override
        public void onError(String errorMessage) {
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(String failureMessage) {
            Toast.makeText(this, failureMessage, Toast.LENGTH_LONG).show();
        }


}
