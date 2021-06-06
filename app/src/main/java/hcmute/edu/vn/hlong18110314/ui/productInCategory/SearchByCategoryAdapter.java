package hcmute.edu.vn.hlong18110314.ui.productInCategory;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Model.Cart;
import hcmute.edu.vn.hlong18110314.database.Model.OrderDetailModel;
import hcmute.edu.vn.hlong18110314.database.Model.ProductModel;

public class SearchByCategoryAdapter extends RecyclerView.Adapter<SearchByCategoryAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameProduct;
        public TextView costProduct;
        public Button addToCart;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameProduct = (TextView) itemView.findViewById(R.id.txtItemProductName);
            costProduct = (TextView) itemView.findViewById(R.id.txtItemProductCost);
            addToCart = (Button) itemView.findViewById(R.id.btnItemAddToCart);
        }
    }
    // Store a member variable for the contacts
    private List<ProductModel> mProducts;

    // Pass in the contact array into the constructor
    public SearchByCategoryAdapter(List<ProductModel> products) {
        mProducts = products;
    }
    @Override
    public SearchByCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View productView = inflater.inflate(R.layout.item_product, parent, false);

        // Return a new holder instance
        SearchByCategoryAdapter.ViewHolder viewHolder = new SearchByCategoryAdapter.ViewHolder(productView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(SearchByCategoryAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        ProductModel product = mProducts.get(position);

        // Set item views based on your views and data model
        TextView nameproduct = holder.nameProduct;
        nameproduct.setText(product.getName());
        TextView costproduct = holder.costProduct;
        costproduct.setText(product.getPrice().toString());
        holder.itemView.setOnClickListener((view)->{
            Log.i("Click",  String.valueOf(position));
        });
        holder.addToCart.setOnClickListener((view)->{
            List<OrderDetailModel> cart = Cart.CURRENT_CART;
            OrderDetailModel detail = new OrderDetailModel(1,product.getId(),1,product.getPrice());
            cart.add(detail);
            Cart.CURRENT_CART = cart;
            Toast.makeText(view.getContext(), "THÊM " + product.getName() + " VÀO GIỎ HÀNG" , Toast.LENGTH_SHORT).show();
        });

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}