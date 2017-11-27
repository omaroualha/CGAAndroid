package cga.esprit.cgaandroid;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.error.ANError;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListClaimFragment extends Fragment {
    public static ListClaimFragment newInstance() {

        Bundle args = new Bundle();

        ListClaimFragment fragment = new ListClaimFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @BindView(R.id.list_claimss)
    RecyclerView listClaims;

    @BindView(R.id.add_claim)
    FloatingActionButton btnAddClaim;

    @BindView(R.id.empty_view)
    TextView emptyView;
    ApiCGA api;
    private ListClaimAdapter adapter;

    public ListClaimFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_claim, container, false);
        ButterKnife.bind(this,v);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listClaims.setLayoutManager(linearLayoutManager);
        api= new ApiCGA();
        api.getList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(claims -> {
                    Log.wtf("-->", Constants.GSON.toJson(claims));
                        if (claims.size() > 0) {
                            emptyView.setVisibility(View.GONE);
                            listClaims.setVisibility(View.VISIBLE);
                            adapter = new ListClaimAdapter(getActivity(),claims);
                            listClaims.setAdapter(adapter);
                        } else {
                            emptyView.setVisibility(View.VISIBLE);
                            listClaims.setVisibility(View.GONE);
                        }


                },throwable -> {
                    throwable.printStackTrace();
                    emptyView.setVisibility(View.VISIBLE);
                    listClaims.setVisibility(View.GONE);
                    if(throwable instanceof ANError){
                        ANError anError = (ANError) throwable;
                        anError.fillInStackTrace();
                        //Log.d("body",anError.getErrorBody());
                    }
                });
        return v ;
    }

}
