package hcmute.edu.vn.hlong18110314.ui.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.ui.product.ProductActivity;

public class CategoryFragment extends Fragment {
    private CategoryViewModel categoryViewModel;
    public Button btnCtgRice;
    public Button btnCtgNoodle;
    public Button btnCtgFFood;
    public Button btnCtgSnack;
    public Button btnCtgSoftDrink;
    public Button btnCtgChay;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        btnCtgRice = (Button) root.findViewById(R.id.btn_category_1);
        btnCtgNoodle = (Button) root.findViewById(R.id.btn_category_2);
        btnCtgFFood = (Button) root.findViewById(R.id.btn_category_3);
        btnCtgSnack = (Button) root.findViewById(R.id.btn_category_4);
        btnCtgSoftDrink = (Button) root.findViewById(R.id.btn_category_5);
        btnCtgChay = (Button) root.findViewById(R.id.btn_category_6);

        btnCtgRice.setOnClickListener((view)->{
            Intent intent = new Intent(inflater.getContext(), ProductActivity.class);
            intent.putExtra("CATEGORY_ID", 1);
            startActivity(intent);
        });
        btnCtgNoodle.setOnClickListener((view)->{
            Intent intent = new Intent(inflater.getContext(), ProductActivity.class);
            intent.putExtra("CATEGORY_ID", 2);
            startActivity(intent);
        });
        btnCtgFFood.setOnClickListener((view)->{
            Intent intent = new Intent(inflater.getContext(), ProductActivity.class);
            intent.putExtra("CATEGORY_ID", 3);
            startActivity(intent);
        });
        btnCtgSnack.setOnClickListener((view)->{
            Intent intent = new Intent(inflater.getContext(), ProductActivity.class);
            intent.putExtra("CATEGORY_ID", 4);
            startActivity(intent);
        });
        btnCtgSoftDrink.setOnClickListener((view)->{
            Intent intent = new Intent(inflater.getContext(), ProductActivity.class);
            intent.putExtra("CATEGORY_ID", 5);
            startActivity(intent);
        });
        btnCtgChay.setOnClickListener((view)->{
            Intent intent = new Intent(inflater.getContext(), ProductActivity.class);
            intent.putExtra("CATEGORY_ID", 6);
            startActivity(intent);
        });

        return root;
    }
}
