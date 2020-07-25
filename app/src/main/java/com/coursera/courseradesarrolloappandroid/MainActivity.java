package com.coursera.courseradesarrolloappandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatEditText txt_name;
    private AppCompatEditText txt_date;
    private AppCompatEditText txt_phone;
    private AppCompatEditText txt_email;
    private AppCompatEditText txt_description;
    private AppCompatButton btnContinue;

    private MaterialDatePicker.Builder builder;
    private MaterialDatePicker materialDatePicker;
    private Contact myContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.loadViews();
        this.receiveExtras();

        //Listeners
        this.btnContinue.setOnClickListener(this);
        this.btnContinue.setOnClickListener(this);

        //Configuration datepicker
        this.builder = MaterialDatePicker.Builder.datePicker();
        this.builder.setTitleText(getResources().getString(R.string.date_picker_title));
        this.builder.setSelection(MaterialDatePicker.todayInUtcMilliseconds());
        this.materialDatePicker = builder.build();

        //Listener datepicker
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                txt_date.setText(materialDatePicker.getHeaderText());
            }
        });

    }

    private void receiveExtras() {
        if(getIntent().getExtras() != null) {
            Contact contact = (Contact) getIntent().getSerializableExtra(getResources().getString(R.string.key_contact));
            this.txt_name.setText(contact.getName());
            this.txt_date.setText(contact.getDate());
            this.txt_phone.setText(contact.getPhone());
            this.txt_email.setText(contact.getEmail());
            this.txt_description.setText(contact.getDescription());
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnContinue:
                boolean flag = createContact();
                sendIntent(flag);
                break;
            case R.id.btnDate:
                materialDatePicker.show(getSupportFragmentManager(), getResources().getString(R.string.tag_date));
                break;
        }
    }

    private void loadViews() {
        this.txt_name = findViewById(R.id.txtFullName);
        this.txt_date = findViewById(R.id.txtDate);
        this.txt_phone = findViewById(R.id.txtPhone);
        this.txt_email = findViewById(R.id.txtEmail);
        this.txt_description = findViewById(R.id.txtDescription);
        this.btnContinue = findViewById(R.id.btnContinue);
    }

    private boolean createContact() {
        String name = this.txt_name.getText().toString();
        String date = this.txt_date.getText().toString();
        String phone = this.txt_phone.getText().toString();
        String email = this.txt_email.getText().toString();
        String description = this.txt_description.getText().toString();

        if(!isEmpty(this.txt_name) && !isEmpty(this.txt_date) && !isEmpty(this.txt_phone) && !isEmpty(this.txt_email) && !isEmpty(this.txt_description)) {
            this.myContact = new Contact(name, date, phone, email, description);
            return true;
        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_msg_empty), Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private boolean isEmpty(AppCompatEditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private void sendIntent(boolean flag) {
        if(flag) {
            Intent intent = new Intent(MainActivity.this, ContactDetail.class);
            intent.putExtra(getResources().getString(R.string.key_contact), myContact);
            startActivity(intent);
            finish();
        }
    }
}