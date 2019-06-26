package com.bayuirfan.myunittesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView{
    TextView result;
    EditText edtLength, edtWidth, edtHeight;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLength = findViewById(R.id.edt_length);
        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        btnCalculate = findViewById(R.id.btn_calculate);
        result = findViewById(R.id.tv_result);

        final MainPresenter presenter = new MainPresenter(this);

        btnCalculate.setOnClickListener(v -> {
            String length = edtLength.getText().toString().trim();
            String width = edtWidth.getText().toString().trim();
            String height = edtHeight.getText().toString().trim();

            boolean isEmptyField = false;

            if (TextUtils.isEmpty(length)){
                isEmptyField = true;
                edtLength.setError("Field harus diisi");
            }

            if (TextUtils.isEmpty(width)){
                isEmptyField = true;
                edtWidth.setError("Field harus diisi");
            }

            if (TextUtils.isEmpty(height)){
                isEmptyField = true;
                edtHeight.setError("Field harus diisi");
            }

            if (!isEmptyField){
                double l = Double.valueOf(length);
                double w = Double.valueOf(width);
                double h = Double.valueOf(height);

                presenter.calculateVolume(l, w, h);
            }
        });
    }

    @Override
    public void showVolume(MainModel volume) {
        result.setText(String.valueOf(volume.getVolume()));
    }
}
