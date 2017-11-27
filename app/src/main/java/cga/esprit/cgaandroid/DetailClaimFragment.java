package cga.esprit.cgaandroid;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.androidnetworking.error.ANError;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailClaimFragment extends AppCompatActivity {
    @BindView(R.id.id_C)
    TextView idClaim;
    @BindView(R.id.date_accident)
    EditText dateAcc;
    @BindView(R.id.heur_accident )
    EditText hAcc;
    @BindView(R.id.location)
    EditText loc;
    @BindView(R.id.injured )
    Switch inj;
    @BindView(R.id.dmg_vh )
    Switch vhDmg;
    @BindView(R.id.other_dmg)
    Switch otherDmg;
    @BindView(R.id.nameAddress)
    EditText nameAddres;
    @BindView(R.id.sketch)
    EditText sketch;

    ApiCGA apiCGA;
    Claim claim;

    public DetailClaimFragment() {
        // Required empty public constructor
    }

    private static final String TAG = "DetailClaimFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail_claim);
        ButterKnife.bind(this);
        claim = (Claim)getIntent().getSerializableExtra("claim");
        Log.w(TAG, "onCreate: "+Constants.GSON.toJson(claim));
        //claim = (Claim)b.getSerializable("claim");
        idClaim.setText(String.format("ID : %d",claim.getId()));
        apiCGA= new ApiCGA();
    }

    @OnClick(R.id.btn_delete)
    void deleteClaim(){
        apiCGA.deleteClaim(claim)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(result -> {
                    Log.wtf("-->",result);
                    finish();
                },throwable -> {
                    throwable.printStackTrace();
                    if(throwable instanceof ANError){
                        ANError anError = (ANError) throwable;
                        anError.fillInStackTrace();
                        //Log.d("body",anError.getErrorBody());
                    }
                });
    }

    public boolean isValid(){
        if(dateAcc.getText().toString().isEmpty()){
            dateAcc.setError("Veuillez remplir !");
            return false;
        }
        if(hAcc.getText().toString().isEmpty()){
            hAcc.setError("Veuillez remplir !");
            return false;
        }
        claim.setAccidentHour(hAcc.getText().toString());
        if(loc.getText().toString().isEmpty()){
            loc.setError("Veuillez remplir !");
            return false;
        }
        claim.setLocalisation(loc.getText().toString());
        if(nameAddres.getText().toString().isEmpty()){
            nameAddres.setError("Veuillez remplir !");
            return false;
        }
        claim.setNamesAddressesPhonesWitnesses(nameAddres.getText().toString());
        if(sketch.getText().toString().isEmpty()){
            sketch.setError("Veuillez remplir !");
            return false;
        }
        claim.setSketchOfTheAccident(sketch.getText().toString());

        claim.setInjured(inj.isChecked());
        claim.setVehiclesDamage(vhDmg.isChecked());
        claim.setOtherObjectDamage(otherDmg.isChecked());
        return  true;
    }

    @OnClick(R.id.btn_update)
    void updateClaim(){
        if(isValid())
            apiCGA.updateClaim(claim)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(claims -> {
                    finish();
                    Log.wtf("-->",claims);
                },throwable -> {
                    throwable.printStackTrace();
                    if(throwable instanceof ANError){
                        ANError anError = (ANError) throwable;
                        anError.fillInStackTrace();
                        //Log.d("body",anError.getErrorBody());
                    }
                });
    }
}
