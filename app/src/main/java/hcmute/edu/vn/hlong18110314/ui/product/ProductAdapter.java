package hcmute.edu.vn.hlong18110314.ui.product;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import hcmute.edu.vn.hlong18110314.MainActivity;
import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Model.CartModel;
import hcmute.edu.vn.hlong18110314.database.Model.ProductModel;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameProduct;
        public TextView costProduct;
//        public Button addToCart;

        public  Button btnMinus;
        public  TextView txtValues;
        public  Button btnPlus;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameProduct = (TextView) itemView.findViewById(R.id.txtItemProductName);
            costProduct = (TextView) itemView.findViewById(R.id.txtItemProductCost);
//            addToCart = (Button) itemView.findViewById(R.id.btnItemAddToCart);
            btnMinus = (Button) itemView.findViewById(R.id.btnMinus);
            txtValues = (TextView) itemView.findViewById(R.id.txtValues);
            btnPlus = (Button) itemView.findViewById(R.id.btnPlus);
        }
    }
    // Store a member variable for the contacts
    private List<ProductModel> mProducts;

    // Pass in the contact array into the constructor
    public ProductAdapter(List<ProductModel> products) {
        mProducts = products;
    }
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View productView = inflater.inflate(R.layout.item_product, parent, false);


        // Return a new holder instance
        ProductAdapter.ViewHolder viewHolder = new ProductAdapter.ViewHolder(productView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        ProductModel product = mProducts.get(position);

        // Set item views based on your views and data model
        TextView nameproduct = holder.nameProduct;
        nameproduct.setText(product.getName());
        TextView costproduct = holder.costProduct;
        TextView txtValues = holder.txtValues;
        //Cập nhật dữ liệu nếu có sản phẩm trong giỏ hàng
        if(MainActivity.arrayCart.size() > 0){
            for(int i = 0 ; i < MainActivity.arrayCart.size() ; i++){
                if(MainActivity.arrayCart.get(i).getProductId() == product.getId()){
                    txtValues.setText( MainActivity.arrayCart.get(i).getNumberOfProduct() + "");
                }
                else{
                    txtValues.setText(0 + "");
                }
            }
        }


        // Định dạng giá
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        costproduct.setText(decimalFormat.format(product.getPrice()) + " Đ");
        holder.itemView.setOnClickListener((view)->{
            Log.i("Click",  String.valueOf(position));
        });
        holder.btnPlus.setOnClickListener((view)->{

            // Thêm vào giỏ hàng
            int sum = Integer.parseInt(txtValues.getText().toString()) + 1;
            Log.e("SUM" , String.valueOf(sum));
            txtValues.setText(  sum + "");
            boolean checkExist = false;
            for(int i = 0 ; i < MainActivity.arrayCart.size(); i ++) {
                if(MainActivity.arrayCart.get(i).getProductId() == product.getId()){
                    MainActivity.arrayCart.get(i).setNumberOfProduct(MainActivity.arrayCart.get(i).getNumberOfProduct() + 1);
                    MainActivity.arrayCart.get(i).setTotalPrice(MainActivity.arrayCart.get(i).getTotalPrice() + product.getPrice());
                    checkExist = true;
                }
            }
            if(checkExist != true){
                //public CartModel(int productId, String name, long price, String image, int numberOfProduct, long totalPrice)
                MainActivity.arrayCart.add(
                        new CartModel(product.getId(), product.getName(), product.getPrice(),
                                product.getImage(),Integer.parseInt(txtValues.getText().toString()) ,
                                product.getPrice() ));
            }
            Toast.makeText(view.getContext(), "THÊM " + product.getName() + " VÀO GIỎ HÀNG" , Toast.LENGTH_SHORT).show();
            for(int i = 0 ; i < MainActivity.arrayCart.size(); i ++) {
                Log.e("CARTHERE", MainActivity.arrayCart.get(i).getName());
                Log.e("NUMBERPRODUCT", String.valueOf(MainActivity.arrayCart.get(i).getNumberOfProduct()) );
            }

            Log.e("Size cart", String.valueOf(MainActivity.arrayCart.size()));

        });





    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}