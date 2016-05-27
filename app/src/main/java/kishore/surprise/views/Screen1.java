package kishore.surprise.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import kishore.surprise.MainActivity;
import kishore.surprise.R;
import kishore.surprise.util.MyData;

public class Screen1 extends Activity implements View.OnClickListener {

    private final String TAG = Screen1.class.getName();
    private StringBuilder message = new StringBuilder();

    private TextView question;
    private Button buttonYes;
    private Button buttonNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);

        question = (TextView) findViewById(R.id.question);
        buttonYes = (Button) findViewById(R.id.buttonYes);
        buttonNo = (Button) findViewById(R.id.buttonNo);
        buttonYes.setOnClickListener(this);
        buttonNo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Button button = (Button) view;
        message.append(question.getText().toString());
        message.append(button.getText().toString());
        message.append("\n");
        MyData.instance.insert(message);

        Intent intent = new Intent(this, Screen2.class);
        switch (view.getId()) {
            case R.id.buttonNo:
                Toast.makeText(this, "How about now?", Toast.LENGTH_SHORT).show();
                buttonNo.setVisibility(View.GONE);
                break;

            case R.id.buttonYes:
                startActivity(intent);
                break;

            default:
                break;
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
    }

}
