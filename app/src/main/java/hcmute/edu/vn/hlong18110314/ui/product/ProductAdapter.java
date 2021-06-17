package hcmute.edu.vn.hlong18110314.ui.product;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import hcmute.edu.vn.hlong18110314.MainActivity;
import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Model.CartModel;
import hcmute.edu.vn.hlong18110314.database.Model.ProductModel;
import hcmute.edu.vn.hlong18110314.ui.CheckOut.CheckOutActivity;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameProduct;
        public TextView costProduct;
        public  Button btnMinus;
        public  TextView txtValues;
        public  Button btnPlus;
        public ImageView imgItemProduct;

        public ViewHolder(View itemView) {
            super(itemView);
            imgItemProduct = (ImageView) itemView.findViewById(R.id.imgItemProduct);
            nameProduct = (TextView) itemView.findViewById(R.id.txtItemProductName);
            costProduct = (TextView) itemView.findViewById(R.id.txtItemProductCost);
            btnMinus = (Button) itemView.findViewById(R.id.btnMinus);
            txtValues = (TextView) itemView.findViewById(R.id.txtValues);
            btnPlus = (Button) itemView.findViewById(R.id.btnPlus);
        }
    }

    private List<ProductModel> mProducts;

    public ProductAdapter(List<ProductModel> products) {
        mProducts = products;
    }
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View productView = inflater.inflate(R.layout.item_product, parent, false);

        ProductAdapter.ViewHolder viewHolder = new ProductAdapter.ViewHolder(productView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        ProductActivity productActivity = new ProductActivity();
        ProductModel product = mProducts.get(position);

        holder.imgItemProduct.setImageResource(product.getImage());
        holder.nameProduct.setText(product.getName());
        TextView costproduct = holder.costProduct;
        TextView txtValues = holder.txtValues;
        txtValues.setText(0 + "");

        //Cập nhật dữ liệu nếu có sản phẩm trong giỏ hàng
        if(MainActivity.arrayCart.size() > 0){
            for(int i = 0 ; i < MainActivity.arrayCart.size() ; i++){
                if(MainActivity.arrayCart.get(i).getProductId() == product.getId()){
                    txtValues.setText( MainActivity.arrayCart.get(i).getNumberOfProduct() + "");
                }
            }
        }
        if(Integer.parseInt(txtValues.getText().toString()) == 0){
            holder.btnMinus.setVisibility(View.INVISIBLE);
            holder.txtValues.setVisibility(View.INVISIBLE);
        }

        // Định dạng giá cho sản phẩm
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        costproduct.setText(decimalFormat.format(product.getPrice()) + " Đ");

        //Button Click
        holder.itemView.setOnClickListener((view)->{
            Log.i("Click",  String.valueOf(position));
        });
        holder.btnPlus.setOnClickListener((view)->{
            holder.btnMinus.setVisibility(View.VISIBLE);
            holder.txtValues.setVisibility(View.VISIBLE);
            productActivity.enableButton(true);
            int sum = Integer.parseInt(txtValues.getText().toString()) + 1;
            txtValues.setText(  sum + "");
            boolean checkExist = false;
            for(int i = 0 ; i < MainActivity.arrayCart.size(); i ++) {
                if(MainActivity.arrayCart.get(i).getProductId() == product.getId()){
                    MainActivity.arrayCart.get(i).setNumberOfProduct(MainActivity.arrayCart.get(i).getNumberOfProduct() + 1);
                    MainActivity.arrayCart.get(i).setTotalPrice((int) (MainActivity.arrayCart.get(i).getTotalPrice() + product.getPrice()));
                    productActivity.calculatorTotalPrice();


                    checkExist = true;
                }
            }
            // Thêm vào giỏ hàng
            if(checkExist != true){
                //public CartModel(int productId, String name, long price, String image, int numberOfProduct, long totalPrice)
                MainActivity.arrayCart.add(
                        new CartModel(product.getId(), product.getName(), product.getPrice(),
                                product.getImage(),Integer.parseInt(txtValues.getText().toString()) ,
                                product.getPrice() ));

                productActivity.calculatorTotalPrice();
            }
        });

        holder.btnMinus.setOnClickListener((view)->{
            int sum = Integer.parseInt(txtValues.getText().toString()) - 1;
            txtValues.setText(  sum + "");
            for(int i = 0 ; i < MainActivity.arrayCart.size(); i ++) {
                if(MainActivity.arrayCart.get(i).getProductId() == product.getId()){
                    MainActivity.arrayCart.get(i).setNumberOfProduct(MainActivity.arrayCart.get(i).getNumberOfProduct() - 1);
                    MainActivity.arrayCart.get(i).setTotalPrice((int) (MainActivity.arrayCart.get(i).getTotalPrice() - product.getPrice()));
                    productActivity.calculatorTotalPrice();
                    if(Integer.parseInt(txtValues.getText().toString()) < 1){
                        holder.btnMinus.setVisibility(View.INVISIBLE);
                        holder.txtValues.setVisibility(View.INVISIBLE);
                        MainActivity.arrayCart.remove(i);
                        if(MainActivity.arrayCart.size()== 0 ){
                            productActivity.enableButton(false);
                        }
                    }
                }
            }


        });





    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}