package hcmute.edu.vn.hlong18110314.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import hcmute.edu.vn.hlong18110314.MainActivity;
import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Model.CartModel;
import hcmute.edu.vn.hlong18110314.database.Model.OrderModel;
import hcmute.edu.vn.hlong18110314.database.Model.ProductModel;
import hcmute.edu.vn.hlong18110314.ui.CheckOut.CheckOutActivity;
import hcmute.edu.vn.hlong18110314.ui.product.ProductActivity;
import hcmute.edu.vn.hlong18110314.ui.userDetail.UserInfoAdapter;

public class HomeAdapter2 extends RecyclerView.Adapter<HomeAdapter2.ViewHolder> {
    private List<ProductModel> mProducts;
    public HomeAdapter2(List<ProductModel> products) {
        mProducts = products;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgItemProduct;
        public TextView nameProduct;
        public TextView costProduct;
        public  Button btnMinus;
        public  TextView txtValues;
        public  Button btnPlus;

        public ViewHolder( View itemView) {
            super(itemView);
            imgItemProduct = (ImageView) itemView.findViewById(R.id.imgItemProduct);
            nameProduct = (TextView) itemView.findViewById(R.id.txtItemProductName);
            costProduct = (TextView) itemView.findViewById(R.id.txtItemProductCost);
            btnMinus = (Button) itemView.findViewById(R.id.btnMinus);
            txtValues = (TextView) itemView.findViewById(R.id.txtValues);
            btnPlus = (Button) itemView.findViewById(R.id.btnPlus);

        }
    }
    public Context mContext;
    @Override
    public HomeAdapter2.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View cartView = inflater.inflate(R.layout.item_product, parent, false);

        // Return a new holder instance
        HomeAdapter2.ViewHolder viewHolder = new HomeAdapter2.ViewHolder(cartView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( HomeAdapter2.ViewHolder holder, int position) {
        ProductModel product = mProducts.get(position);
        holder.nameProduct.setText(product.getName());
        holder.imgItemProduct.setImageResource(product.getImage());
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

            int sum = Integer.parseInt(txtValues.getText().toString()) + 1;
            txtValues.setText(  sum + "");

            boolean checkExist = false;
            for(int i = 0 ; i < MainActivity.arrayCart.size(); i ++) {
                if(MainActivity.arrayCart.get(i).getProductId() == product.getId()){
                    MainActivity.arrayCart.get(i).setNumberOfProduct(MainActivity.arrayCart.get(i).getNumberOfProduct() + 1);
                    MainActivity.arrayCart.get(i).setTotalPrice((int) (MainActivity.arrayCart.get(i).getTotalPrice() + product.getPrice()));
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
            }

        });

        holder.btnMinus.setOnClickListener((view)->{
            int sum = Integer.parseInt(txtValues.getText().toString()) - 1;
            txtValues.setText(  sum + "");
            for(int i = 0 ; i < MainActivity.arrayCart.size(); i ++) {
                if(MainActivity.arrayCart.get(i).getProductId() == product.getId()){
                    MainActivity.arrayCart.get(i).setNumberOfProduct(MainActivity.arrayCart.get(i).getNumberOfProduct() - 1);
                    MainActivity.arrayCart.get(i).setTotalPrice((int) (MainActivity.arrayCart.get(i).getTotalPrice() - product.getPrice()));

                    if(Integer.parseInt(txtValues.getText().toString()) < 1){
                        holder.btnMinus.setVisibility(View.INVISIBLE);
                        holder.txtValues.setVisibility(View.INVISIBLE);
                        MainActivity.arrayCart.remove(i);
                    }
                }
            }


        });


    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }


}
