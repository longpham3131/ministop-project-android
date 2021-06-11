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
    public Button btnCtgSandwich;
    public Button btnCtgNuocGiaiKhat;
    public Button btnCtgSushi;
    public Button btnCtgOnigiri;
    public Button btnCtgTrangMieng;
    public Button btnCtgCom;
    public Button btnCtgOden;
    public Button btnCtgThucAnNhanh;
    public Button btnCtgStreetFood;
    public Button btnCtgSalad;
    public Button btnCtgMi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        btnCtgSandwich = (Button) root.findViewById(R.id.btnCtgSandwich);
        btnCtgNuocGiaiKhat = (Button) root.findViewById(R.id.btnCtgNuocGiaiKhat);
        btnCtgSushi = (Button) root.findViewById(R.id.btnCtgSushi);
        btnCtgOnigiri = (Button) root.findViewById(R.id.btnCtgOnigiri);
        btnCtgTrangMieng = (Button) root.findViewById(R.id.btnCtgTrangMieng);
        btnCtgCom = (Button) root.findViewById(R.id.btnCtgCom);
        btnCtgOden = (Button) root.findViewById(R.id.btnCtgOden);
        btnCtgThucAnNhanh = (Button) root.findViewById(R.id.btnCtgThucAnNhanh);
        btnCtgSandwich.setOnClickListener((view)->{
            Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(inflater.getContext(), ProductActivity.class);
            intent.putExtra("CATEGORY_ID", 1);
            startActivity(intent);
        });
        btnCtgNuocGiaiKhat.setOnClickListener((view)->{
//            Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(inflater.getContext(), ProductActivity.class);
            intent.putExtra("CATEGORY_ID", 2);
            startActivity(intent);
        });
        btnCtgSushi.setOnClickListener((view)->{
//            Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT).show();
        });
        btnCtgOnigiri.setOnClickListener((view)->{
//            Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT).show();
        });
        btnCtgTrangMieng.setOnClickListener((view)->{
//            Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT).show();
        });
        btnCtgCom.setOnClickListener((view)->{
//            Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT).show();
        });
        btnCtgOden.setOnClickListener((view)->{
//            Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT).show();
        });
        btnCtgThucAnNhanh.setOnClickListener((view)->{
//            Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT).show();
        });


        return root;
    }
}
