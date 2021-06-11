package hcmute.edu.vn.hlong18110314.ui.cart;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.OrderDetailModel;
import hcmute.edu.vn.hlong18110314.database.Model.OrderModel;
import hcmute.edu.vn.hlong18110314.database.Model.ProductModel;
import hcmute.edu.vn.hlong18110314.ui.bill.BillActivity;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtItemDateCreated;
        public TextView txtItemDisplayName;
        public TextView txtItemStatus;
        public TextView txtItemTotalPrice;


        public ViewHolder(View itemView) {
            super(itemView);
            txtItemDateCreated = (TextView) itemView.findViewById(R.id.txtItemDateCreated);
            txtItemDisplayName = (TextView) itemView.findViewById(R.id.txtItemDisplayName);
            txtItemStatus = (TextView) itemView.findViewById(R.id.txtItemStatus);
            txtItemTotalPrice = (TextView) itemView.findViewById(R.id.txtItemTotalPrice);

        }
    }

    private List<OrderModel> lOrderByUser;
    public Context mContext;

    public CartAdapter(List<OrderModel> lOrder) {
        lOrderByUser = lOrder;
    }
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View productView = inflater.inflate(R.layout.item_order, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(productView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        OrderModel orderModel = lOrderByUser.get(position);
        holder.txtItemDateCreated.setText(orderModel.getDateCreated());
        holder.txtItemDisplayName.setText(orderModel.getName());
        holder.txtItemStatus.setText(orderModel.getStatus());
        holder.txtItemTotalPrice.setText(decimalFormat.format(orderModel.getTotal()));

//        holder.itemView.setOnClickListener((view)->{
//            Intent intent = new Intent(getActivity(), BillActivity.class);
//            intent.putExtra("order_id", orderModel.getId());
//
//        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return lOrderByUser.size();
    }

}
