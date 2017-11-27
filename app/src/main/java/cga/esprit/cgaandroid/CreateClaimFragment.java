package cga.esprit.cgaandroid;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import com.androidnetworking.error.ANError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateClaimFragment extends Fragment {
    private static final String TAG = "CreateClaimFragment";
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

    public static CreateClaimFragment newInstance() {

        Bundle args = new Bundle();

        CreateClaimFragment fragment = new CreateClaimFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public CreateClaimFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_claim, container, false);
        ButterKnife.bind(this,v);
        onClickDate(getContext(),dateAcc);
        apiCGA= new ApiCGA();
        claim= new Claim();
        return v;
    }

    @OnClick(R.id.btn_add)
    void addClaim(){
        Log.wtf(TAG, "addClaim: "+inj.isChecked());
        if(isValid()){
            apiCGA.postClaim(claim)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(claims -> {
                       // List<Claim> list= Constants.GSON.fromJson(claims,new TypeToken<List<Claim>>(){}.getType());
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
    private void onClickDate(Context context, EditText editText) {
        Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(editText, myCalendar);
            }

        };

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {


            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    new DatePickerDialog(context, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void updateLabel(EditText editText, Calendar calendar) {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            claim.setAccidentDate(Iso8601Format.newDateTimeFormatT().format(calendar.getTime()));

        editText.setText(sdf.format(calendar.getTime()));
    }
}
