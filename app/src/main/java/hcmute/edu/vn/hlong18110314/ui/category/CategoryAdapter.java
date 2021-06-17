package hcmute.edu.vn.hlong18110314.ui.category;

import android.content.Context;
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
import hcmute.edu.vn.hlong18110314.ui.CheckOut.CheckOutActivity;

public class CategoryAdapter extends RecyclerView.Adapter<hcmute.edu.vn.hlong18110314.ui.category.CategoryAdapter.ViewHolder> {
    private List<CartModel> lCartM;
    public CategoryAdapter(List<CartModel> lCart) {
        lCartM = lCart;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgItemCart;
        public TextView nameProduct;
        public TextView priceProduct;
        public TextView numberOfProduct;
        public Button btnMinus;
        public  Button btnPlus;
        public  Button btnDeleteItemCart;

        public ViewHolder( View itemView) {
            super(itemView);
            imgItemCart = (ImageView) itemView.findViewById(R.id.imgItemCart);
            nameProduct = (TextView) itemView.findViewById(R.id.txtItemCartName);
            numberOfProduct = (TextView) itemView.findViewById(R.id.txtCartQuantity);
            priceProduct = (TextView) itemView.findViewById(R.id.txtItemCartCost);
            btnMinus = (Button) itemView.findViewById(R.id.btnMinus);
            btnPlus = (Button) itemView.findViewById(R.id.btnPlus);
            btnDeleteItemCart = (Button) itemView.findViewById(R.id.btnDeleteItemCart);

        }
    }
    public Context mContext;
    @Override
    public hcmute.edu.vn.hlong18110314.ui.category.CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View cartView = inflater.inflate(R.layout.item_cart, parent, false);

        // Return a new holder instance
        hcmute.edu.vn.hlong18110314.ui.category.CategoryAdapter.ViewHolder viewHolder = new hcmute.edu.vn.hlong18110314.ui.category.CategoryAdapter.ViewHolder(cartView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(hcmute.edu.vn.hlong18110314.ui.category.CategoryAdapter.ViewHolder holder, int position) {
        //Lấy dữ liệu từ MainActivity
        CategoryFragment categoryFragment = new CategoryFragment();
        CartModel cartModel = MainActivity.arrayCart.get(position);
        holder.imgItemCart.setImageResource(cartModel.getImage());
        holder.nameProduct.setText(cartModel.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.priceProduct.setText(decimalFormat.format(cartModel.getPrice())  + " Đ");
        holder.numberOfProduct.setText(cartModel.getNumberOfProduct() + "");

        holder.btnPlus.setOnClickListener((view)->{
            for(int i = 0 ; i < MainActivity.arrayCart.size(); i ++) {
                if(MainActivity.arrayCart.get(i).getProductId() == cartModel.getProductId()){
                    MainActivity.arrayCart.get(i).setNumberOfProduct(MainActivity.arrayCart.get(i).getNumberOfProduct() + 1);
                    MainActivity.arrayCart.get(i).setTotalPrice((int) (MainActivity.arrayCart.get(i).getTotalPrice() + cartModel.getPrice()));
                    int sum = Integer.parseInt(holder.numberOfProduct.getText().toString())  + 1;
                    holder.numberOfProduct.setText( sum + "");
                    categoryFragment.calculatorTotalPrice();
                }
            }
        });

        holder.btnMinus.setOnClickListener((view)->{
            for(int i = 0 ; i < MainActivity.arrayCart.size(); i ++) {
                if(MainActivity.arrayCart.get(i).getProductId() == cartModel.getProductId()){
                    if(MainActivity.arrayCart.get(i).getNumberOfProduct() > 1){
                        MainActivity.arrayCart.get(i).setNumberOfProduct(MainActivity.arrayCart.get(i).getNumberOfProduct() - 1);
                        MainActivity.arrayCart.get(i).setTotalPrice((int) (MainActivity.arrayCart.get(i).getTotalPrice() - cartModel.getPrice()));
                        int sum = Integer.parseInt(holder.numberOfProduct.getText().toString())  - 1;
                        holder.numberOfProduct.setText(sum + "");
                        categoryFragment.calculatorTotalPrice();
                    }
                }
            }
        });

        holder.btnDeleteItemCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.arrayCart.remove(position);
                categoryFragment.calculatorTotalPrice();
                if(MainActivity.arrayCart.size() ==0){
                    categoryFragment.enableFunction(false);
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lCartM.size();
    }


}