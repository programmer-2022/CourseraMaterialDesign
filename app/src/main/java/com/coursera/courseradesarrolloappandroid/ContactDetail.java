package com.coursera.courseradesarrolloappandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class ContactDetail extends AppCompatActivity {

    private AppCompatTextView etName;
    private AppCompatTextView etDate;
    private AppCompatTextView etPhone;
    private AppCompatTextView etEmail;
    private AppCompatTextView etDescription;
    private AppCompatButton btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacto_detalle);
        this.loadViews();
        this.receiveExtras();

        this.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editContact();
            }
        });
    }

    private void loadViews() {
        this.etName = findViewById(R.id.txtFullName);
        this.etDate = findViewById(R.id.txtDate);
        this.etPhone = findViewById(R.id.txtPhone);
        this.etEmail = findViewById(R.id.txtEmail);
        this.etDescription = findViewById(R.id.txtDescription);
        this.btnEdit = findViewById(R.id.btnContinue);
    }

    private void receiveExtras() {
         if(getIntent().getExtras() != null) {
            Contact contact = (Contact) getIntent().getSerializableExtra(getResources().getString(R.string.key_contact));
            this.etName.setText(contact.getName());
            this.etDate.setText(contact.getDate());
            this.etPhone.setText(contact.getPhone());
            this.etEmail.setText(contact.getEmail());
            this.etDescription.setText(contact.getDescription());
        }
    }

    private void editContact() {
        Contact contact = new Contact(
                this.etName.getText().toString(),
                this.etDate.getText().toString(),
                this.etPhone.getText().toString(),
                this.etEmail.getText().toString(),
                this.etDescription.getText().toString()
        );

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(getResources().getString(R.string.key_contact), contact);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) editContact();
        return super.onKeyDown(keyCode, event);
    }
}