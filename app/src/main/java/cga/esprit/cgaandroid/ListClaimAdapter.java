package cga.esprit.cgaandroid;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nightonke.boommenu.BoomMenuButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListClaimAdapter extends RecyclerView.Adapter<ListClaimAdapter.ViewHolder>  {
    private final Context mContext;
    private final LayoutInflater inflater;

    private List<Claim> claims=new ArrayList<>();

    public ListClaimAdapter(Context mContext, List<Claim> claims) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.claims = claims;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_claim_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Claim claim = claims.get(position);
        holder.id.setText(String.format("Réf : %s",claim.getId()));
        if(claim.getAccidentDate()!=null){
            Date d = new Date(Long.parseLong(claim.getAccidentDate()));
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            holder.date.setText(String.format("Date : %s",df2.format(d)));
        }else{
            holder.date.setText(String.format("Date : %s","Vide"));

        }
        holder.claim=claim;
        holder.position=position;

        holder.heur.setText(String.format("Heur : %s",claim.getAccidentHour()));
    }

    @Override
    public int getItemCount() {
        if (claims == null)
            return 0;
        return claims.size();
    }

    public void setData(List<Claim> data){
        this.claims=data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.id_claim)
        public TextView id;
        @BindView(R.id.date_claim)
        public TextView date;
        @BindView(R.id.heur_claim)
        public TextView heur;
        @BindView(R.id.bmbDetails)
        BoomMenuButton menuButtonDetails;
        @BindView(R.id.bmbShare)
        BoomMenuButton menuButtonShare;

        public int position;
        public Claim claim;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, DetailClaimFragment.class);
               /* Bundle bundle = new Bundle();
                bundle.putSerializable("claim", claim);*/
                intent.putExtra("claim",claim);
                mContext.startActivity(intent);
            });
        }
    }
}
