package hcmute.edu.vn.hlong18110314.ui.cart;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.Cart;
import hcmute.edu.vn.hlong18110314.database.Model.OrderDetailModel;

public class CartFragment extends Fragment {

    private CartViewModel mViewModel;

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container,
                              Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(CartViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        RecyclerView rvCarts = (RecyclerView) root.findViewById(R.id.rvCart);

        Database database = new Database(getContext(), Database.DATABASE_NAME, null);
        List<OrderDetailModel> dbcarts = Cart.CURRENT_CART;
        CartAdapter adapter = new CartAdapter(dbcarts);

        rvCarts.setAdapter(adapter);

        rvCarts.setLayoutManager(new LinearLayoutManager(getContext()));
        int curSize = adapter.getItemCount();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        // TODO: Use the ViewModel
    }

}