package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {

    private ArrayList<ShoppingItem> shoppingList;
    private ShoppingListAdapter adapter;
    private EditText itemEditText;
    private EditText quantityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        shoppingList = new ArrayList<>();
        adapter = new ShoppingListAdapter(this, shoppingList, this);

        itemEditText = findViewById(R.id.itemEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        Button addButton = findViewById(R.id.addButton);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(v -> addItem());
    }

    private void addItem() {
        String itemDescription = itemEditText.getText().toString();
        String quantityString = quantityEditText.getText().toString();
        if (!itemDescription.isEmpty() && !quantityString.isEmpty()) {
            int quantity = Integer.parseInt(quantityString);
            shoppingList.add(new ShoppingItem(itemDescription, quantity));
            adapter.notifyDataSetChanged();
            itemEditText.setText("");
            quantityEditText.setText("");
        }
    }

    public void deleteItem(ShoppingItem item) {
        shoppingList.remove(item);
        adapter.notifyDataSetChanged();
    }

    public void showEditItemDialog(ShoppingItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_item, null);
        builder.setView(dialogView);

        EditText editDescription = dialogView.findViewById(R.id.editDescription);
        EditText editQuantity = dialogView.findViewById(R.id.editQuantity);

        editDescription.setText(item.getDescription());
        editQuantity.setText(String.valueOf(item.getQuantity()));

        builder.setTitle("Editar Item");
        builder.setPositiveButton("Salvar", (dialog, which) -> {
            String newDescription = editDescription.getText().toString();
            int newQuantity = Integer.parseInt(editQuantity.getText().toString());
            item.setDescription(newDescription);
            item.setQuantity(newQuantity);
            adapter.notifyDataSetChanged();
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
