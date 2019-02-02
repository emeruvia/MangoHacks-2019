package fgcu.mangohacks2019;

//import android.accounts.AccountManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.sample.TestListQuery;
import org.jetbrains.annotations.NotNull;
//import com.auth0.android.Auth0;
//import com.auth0.android.authentication.AuthenticationAPIClient;

public class MainActivity extends AppCompatActivity {

  TextView workTv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    workTv = findViewById(R.id.work_tv);
    getPost();
  }

  private void getPost() {
    MyApolloClient.getMyApolloClient().query(
        TestListQuery.builder().build()).enqueue(new ApolloCall.Callback<TestListQuery.Data>() {
      @Override public void onResponse(@NotNull final Response<TestListQuery.Data> response) {
        Log.d("onResponse", response.data().testsList().items().toString());
        MainActivity.this.runOnUiThread(new Runnable() {
          @Override public void run() {
            workTv.setText(response.data().testsList().items().get(0).id());
          }
        });
      }

      @Override public void onFailure(@NotNull ApolloException e) {
        Log.d("onFailure", "Trash");
      }
    });
  }
}
