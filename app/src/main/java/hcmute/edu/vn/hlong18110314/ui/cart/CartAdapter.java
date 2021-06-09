package hcmute.edu.vn.hlong18110314.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.OrderDetailModel;
import hcmute.edu.vn.hlong18110314.database.Model.ProductModel;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameProduct;
        public TextView costProduct;
        public Button btnCong;
        public Button btnTru;
        public TextView quantity;
        public Button btnXoa;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameProduct = (TextView) itemView.findViewById(R.id.txtItemCartName);
            costProduct = (TextView) itemView.findViewById(R.id.txtItemCartCost);
//            btnCong = (Button) itemView.findViewById(R.id.btnCong);
//            btnTru = (Button) itemView.findViewById(R.id.btnTru);
            quantity = (TextView) itemView.findViewById(R.id.txtCartQuantity);
            btnXoa = (Button) itemView.findViewById(R.id.btnDeleteItemCart);
        }
    }
    // Store a member variable for the contacts
    private List<OrderDetailModel> mCart;
    private Context mContext;
    // Pass in the contact array into the constructor
    public CartAdapter(List<OrderDetailModel> products) {
        mCart = products;
    }
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View productView = inflater.inflate(R.layout.item_cart, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(productView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        OrderDetailModel detail = mCart.get(position);
        Database database = new Database(mContext, Database.DATABASE_NAME, null);
        ProductModel product = database.getByIdProduct(detail.getProductId());
        // Set item views based on your views and data model
        TextView nameproduct = holder.nameProduct;
        nameproduct.setText(product.getName());
        TextView costproduct = holder.costProduct;
        costproduct.setText(product.getPrice().toString());
        TextView quantity = holder.quantity;
        quantity.setText(detail.getQuantity().toString());
//        holder.itemView.setOnClickListener((view)->{
//            Log.i("Click",  String.valueOf(position));
//        });
//        holder.addToCart.setOnClickListener((view)->{
//            List<OrderDetailModel> cart = Cart.CURRENT_CART;
//            cart.add(new OrderDetailModel(null,product.getId(),1,product.getPrice()));
//        });
        holder.btnXoa.setOnClickListener((view)->{
        removeItem(holder,position);
        });

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mCart.size();
    }
    private void removeItem(CartAdapter.ViewHolder holder, int position) {
        int newPosition = holder.getAdapterPosition();
        mCart.remove(newPosition);
        notifyItemRemoved(newPosition);
        notifyItemRangeChanged(newPosition, mCart.size());
    }
}
