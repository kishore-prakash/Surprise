package kishore.surprise.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import kishore.surprise.MainActivity;
import kishore.surprise.R;
import kishore.surprise.util.MyData;

public class Screen8 extends Activity implements Validator.ValidationListener, View.OnClickListener, TextView.OnEditorActionListener {

    private final String TAG = Screen8.class.getName();
    private StringBuilder message = new StringBuilder();

    private TextView question;

    @NotEmpty(message = "Enter your answer",trim = true)
    private EditText answer;
    private Button next;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen8);

        validator = new Validator(this);
        validator.setValidationListener(this);

        question = (TextView) findViewById(R.id.question);
        answer = (EditText) findViewById(R.id.answer);
        answer.setImeActionLabel("NEXT", EditorInfo.IME_ACTION_DONE);
        answer.setOnEditorActionListener(this);
        next = (Button) findViewById(R.id.next);

        next.setOnClickListener(this);
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

    @Override
    public void onValidationSucceeded() {
        message.append(question.getText().toString());
        message.append(": ");
        message.append(answer.getText().toString());
        message.append("\n");
        MyData.instance.insert(message);

        Intent intent = new Intent(this, Screen9.class);
        startActivity(intent);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        answer.setText(answer.getText().toString().trim());
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        validator.validate();
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
//            onClick(textView);
        }
        return false;
    }
}
