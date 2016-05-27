package kishore.surprise.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import kishore.surprise.MainActivity;
import kishore.surprise.R;
import kishore.surprise.util.MyData;

public class Screen2 extends Activity implements View.OnClickListener{

    private final String TAG = Screen2.class.getName();
    private StringBuilder message = new StringBuilder();

    private TextView question;
    private Button yesButton;
    private Button noButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);

        question = (TextView) findViewById(R.id.question);
        yesButton = (Button) findViewById(R.id.buttonYes);
        noButton = (Button) findViewById(R.id.buttonNo);
        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        message.append(question.getText().toString());
        message.append(": ");
        message.append(button.getText().toString());
        message.append("\n");
        MyData.instance.insert(message);
        final Intent intent = new Intent(this, Screen3.class);
        switch (view.getId()) {
            case R.id.buttonNo:
                for (int i = 0; i < 2 ; i++) {
                    Toast.makeText(this, "Then why did you press NO, you should have closed the app :D", Toast.LENGTH_SHORT).show();
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        startActivity(intent);
                    }
                }, 3000);
                break;

            case R.id.buttonYes:
                startActivity(intent);
                break;

            default: break;
        }
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Log.d(TAG, "In onRestart method");
    }

}
