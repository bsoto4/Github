package doapps.github.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import doapps.github.uri.UriService;

/**
 * Created by Luis alberto on 1/08/2016.
 */
public class LevelService {

    private static final String TAG = "LevelService";
    private Context context;
    private ProgressDialog progressDialog;
    private LevelInterface LevelInterface;

    public LevelService(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
    }

    public void getAllLevelData() {
        progressDialog.setMessage("Obteniendo información");
        progressDialog.show();
        RequestParams rp =new RequestParams();
        rp.put("id",2);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(context,UriService.ListarDataURL,rp, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    Log.e(TAG, "onSuccess");
                    Log.e(TAG, "onSuccess statuscode :" + i);
                    Log.e(TAG, "onSuccess data type :" + new String(bytes));

                    String jobject = new String(bytes);
                    //JSONArray jsonObject = new JSONArray(jobject);
                    JSONObject jsonObject = new JSONObject(jobject);
                    LevelInterface.interfaceGetData(jsonObject);

                    progressDialog.dismiss();
                } catch (JSONException ex) {
                    ex.getMessage();
                    ex.printStackTrace();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                try {
                Log.e(TAG, "onFailure");
                Log.e(TAG, "onFailure statuscode :" + i);
                progressDialog.dismiss();
                Toast.makeText(context, "No hay respuesta del servidor ,verificar su conexion a internet", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    ex.getMessage();
                    ex.printStackTrace();
                }
            }
        });

    }


    public interface LevelInterface {
        void interfaceGetData(JSONObject jsonObject);
    }

    public void initGetAllLevelDataInterface(LevelInterface LevelInterface) {
        this.LevelInterface = LevelInterface;
    }

}
