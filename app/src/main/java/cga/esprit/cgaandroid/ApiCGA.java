package cga.esprit.cgaandroid;

import com.androidnetworking.common.Priority;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by gofurs on 26/11/17.
 */

public class ApiCGA {
    public ApiCGA(){

    }

    public Observable<List<Claim>> getList(){
        return Rx2AndroidNetworking.get(Constants.API.API_CLAIM)
                .setPriority(Priority.HIGH)
                .getResponseOnlyFromNetwork()
                .build()
                .getObjectListObservable(Claim.class);
    }

    public Observable<String> postClaim(Claim claim){
        return Rx2AndroidNetworking.post(Constants.API.API_CLAIM)
                .addStringBody(Constants.GSON.toJson(claim))
                .setContentType("application/json; charset=utf-8")
                .build()
                .getStringObservable();
    }

    public Observable<String> updateClaim(Claim claim){
        return Rx2AndroidNetworking.put(Constants.API.API_CLAIM)
                .addStringBody(Constants.GSON.toJson(claim))
                .setContentType("application/json; charset=utf-8")
                .build()
                .getStringObservable();
    }

    public Observable<String> deleteClaim(Claim claim){
        return Rx2AndroidNetworking.delete(Constants.API.API_CLAIM+claim.getId())
                .setPriority(Priority.HIGH)
                .getResponseOnlyFromNetwork()
                .build()
                .getStringObservable();
    }
}
