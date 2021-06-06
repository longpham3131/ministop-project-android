package hcmute.edu.vn.hlong18110314.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.ProductModel;

public class ProductFragment extends Fragment {

    private ProductViewModel dashboardViewModel;
    List<ProductModel> products;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(ProductViewModel.class);
        View root = inflater.inflate(R.layout.fragment_product, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        RecyclerView rvProducts = (RecyclerView) root.findViewById(R.id.rvProducts);

        // Initialize contacts
//        products = ProductModel.createContactsList(20);
        // Create adapter passing in the sample user data
        Database database = new Database(getContext(), Database.DATABASE_NAME, null);
        List<ProductModel> dbproducts = database.getAllProducts();
        products = dbproducts;
        ProductAdapter adapter = new ProductAdapter(products);
        // Attach the adapter to the recyclerview to populate items
        rvProducts.setAdapter(adapter);
        // Set layout manager to position the items
        rvProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        // That's all!
        // Add a new contact
        // record this value before making any changes to the existing list
        int curSize = adapter.getItemCount();

        // replace this line with wherever you get new records
//        ArrayList<P> newItems = Contact.createContactsList(20);
//
//        // update the existing list
//        contacts.addAll(newItems);
        // curSize should represent the first element that got added
        // newItems.size() represents the itemCount
//        adapter.notifyItemRangeInserted(curSize, newItems.size());

        return root;
    }
}