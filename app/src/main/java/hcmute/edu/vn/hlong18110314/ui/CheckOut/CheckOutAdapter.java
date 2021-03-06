package hcmute.edu.vn.hlong18110314.ui.CheckOut;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import hcmute.edu.vn.hlong18110314.MainActivity;
import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Model.CartModel;

public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.ViewHolder> {
    ArrayList<CartModel> arrayCart;
    public  TextView totalPriceCart;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView imgItemCart;
        public TextView nameProduct;
        public TextView priceProduct;
        public TextView numberOfProduct;
        public  Button btnMinus;
        public  Button btnPlus;
        public  Button btnDeleteItemCart;

        public ViewHolder(View itemView) {
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
    // Pass in the contact array into the constructor
    public CheckOutAdapter(ArrayList<CartModel> cart) {
        arrayCart = cart;
    }
    @NonNull
    @Override
    public CheckOutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View cartView = inflater.inflate(R.layout.item_cart, parent, false);

        // Return a new holder instance
        CheckOutAdapter.ViewHolder viewHolder = new CheckOutAdapter.ViewHolder(cartView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CheckOutAdapter.ViewHolder holder, int position) {
        //L???y d??? li???u t??? MainActivity
        CheckOutActivity checkOut = new CheckOutActivity();
        CartModel cartModel = MainActivity.arrayCart.get(position);
        holder.nameProduct.setText(cartModel.getName());
        holder.imgItemCart.setImageResource(cartModel.getImage());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.priceProduct.setText(decimalFormat.format(cartModel.getPrice())  + " ??");
        holder.numberOfProduct.setText(cartModel.getNumberOfProduct() + "");

        holder.btnPlus.setOnClickListener((view)->{
            for(int i = 0 ; i < MainActivity.arrayCart.size(); i ++) {
                if(MainActivity.arrayCart.get(i).getProductId() == cartModel.getProductId()){
                    MainActivity.arrayCart.get(i).setNumberOfProduct(MainActivity.arrayCart.get(i).getNumberOfProduct() + 1);
                    MainActivity.arrayCart.get(i).setTotalPrice((int) (MainActivity.arrayCart.get(i).getTotalPrice() + cartModel.getPrice()));
                    int sum = Integer.parseInt(holder.numberOfProduct.getText().toString())  + 1;
                    holder.numberOfProduct.setText( sum + "");
                    checkOut.calculatorTotalPrice();
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
                        checkOut.calculatorTotalPrice();
                    }
                }
            }
        });

        holder.btnDeleteItemCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.arrayCart.remove(position);
                checkOut.calculatorTotalPrice();
                if(MainActivity.arrayCart.size() ==0){
                    CheckOutActivity.enableButton(false);
                }
                notifyDataSetChanged();
            }
        });

    }



    @Override
    public int getItemCount() {
        return arrayCart.size();
    }


}
