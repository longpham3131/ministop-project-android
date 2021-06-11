package hcmute.edu.vn.hlong18110314.ui.bill;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.CartModel;
import hcmute.edu.vn.hlong18110314.database.Model.OrderDetailModel;
import hcmute.edu.vn.hlong18110314.database.Model.ProductModel;
import hcmute.edu.vn.hlong18110314.ui.CheckOut.CheckOutAdapter;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {
    List<OrderDetailModel> lOrderDetail;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtItemName;
        public TextView txtItemPrice;
        public TextView txtItemQuantity;
        public TextView txtItemTotalPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = (TextView) itemView.findViewById(R.id.txtItemNameBill);
            txtItemPrice = (TextView) itemView.findViewById(R.id.txtItemPriceBill);
            txtItemQuantity = (TextView) itemView.findViewById(R.id.txtItemQuantityBill);
            txtItemTotalPrice = (TextView) itemView.findViewById(R.id.txtItemTotalPriceBill);
        }
    }
    public BillAdapter(List<OrderDetailModel> orderDetailModelList) {
        lOrderDetail = orderDetailModelList;
    }
    @NonNull
    @Override
    public BillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View orderDetailView = inflater.inflate(R.layout.item_bill, parent, false);

        // Return a new holder instance
        BillAdapter.ViewHolder viewHolder = new BillAdapter.ViewHolder(orderDetailView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BillAdapter.ViewHolder holder, int position) {
        OrderDetailModel orderDetail = lOrderDetail.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");



        ProductModel productModel = BillActivity.database.getByIdProduct(orderDetail.getId());
//        Log.e("Product Name ", productModel.getName());

        holder.txtItemName.setText(productModel.getName());

        holder.txtItemPrice.setText(decimalFormat.format(productModel.getPrice()));

        holder.txtItemQuantity.setText("x"+String.valueOf(orderDetail.getQuantity()));

        holder.txtItemTotalPrice.setText(decimalFormat.format(orderDetail.getTotal()));

    }

    @Override
    public int getItemCount() {
        return lOrderDetail.size();
    }


}
