package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class ShoppingListAdapter extends ArrayAdapter<ShoppingItem> {

    private final ShoppingListActivity activity;

    public ShoppingListAdapter(Context context, List<ShoppingItem> items, ShoppingListActivity activity) {
        super(context, 0, items);
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_shopping, parent, false);
        }

        ShoppingItem item = getItem(position);

        TextView itemIdTextView = convertView.findViewById(R.id.itemIdTextView);
        TextView itemDescriptionTextView = convertView.findViewById(R.id.itemDescriptionTextView);
        TextView itemQuantityTextView = convertView.findViewById(R.id.itemQuantityTextView);
        ImageButton editButton = convertView.findViewById(R.id.editButton);
        ImageButton deleteButton = convertView.findViewById(R.id.deleteButton);

        if (item != null) {
            itemIdTextView.setText(String.valueOf(item.getId()));
            itemDescriptionTextView.setText(item.getDescription());
            itemQuantityTextView.setText(String.valueOf(item.getQuantity()));

            editButton.setOnClickListener(v -> activity.showEditItemDialog(item));
            deleteButton.setOnClickListener(v -> activity.deleteItem(item));
        }

        return convertView;
    }
}
