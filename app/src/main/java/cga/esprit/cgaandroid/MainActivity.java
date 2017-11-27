package cga.esprit.cgaandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.androidnetworking.error.ANError;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(ListClaimFragment.newInstance(),true);
                    return true;
                case R.id.navigation_dashboard:
                    replaceFragment(ListClaimFragment.newInstance(),true);
                    return true;
                case R.id.navigation_notifications:
                    replaceFragment(CreateClaimFragment.newInstance(),true);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ApiCGA api = new ApiCGA();

        api.getList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(claims -> {
                    Log.wtf("-->", Constants.GSON.toJson(claims));
                },throwable -> {
                    throwable.printStackTrace();
                    if(throwable instanceof ANError){
                        ANError anError = (ANError) throwable;
                        anError.fillInStackTrace();
                        //Log.d("body",anError.getErrorBody());
                    }
                });
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack, int... resId) {
        replaceFragment(fragment, addToBackStack, true, null
                , resId);
    }
    private Fragment findAlreadyExistingFragment(Fragment fragment) {
        return getSupportFragmentManager().findFragmentByTag(fragment.getClass().getSimpleName());
    }
    public void replaceFragment(Fragment fragment, boolean addToBackStack,
                                boolean mode, String stackName, int... resId) {
        Boolean exists = Boolean.FALSE;

        Fragment existing = findAlreadyExistingFragment(fragment);
        if (mode  && existing != null) {
            fragment = existing;
            exists = true;
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        if (resId.length == 4) {
            fragmentTransaction.setCustomAnimations(resId[0],
                    resId[1],
                    resId[2],
                    resId[3]);
        }


        if (addToBackStack && !exists) {
            fragmentTransaction.addToBackStack(stackName);
        }
        fragmentTransaction
                .replace(R.id.container, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }
}
