package com.hobarb.gux;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class My_details extends AppCompatActivity {

    TextView name, city, mobile, gotra, qualify, birthplace, occupation;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    StorageReference storageReference;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);


        imageView = findViewById(R.id.imageView_md);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        Toast.makeText(getApplicationContext(), "" + storageReference.getDownloadUrl(), Toast.LENGTH_SHORT);

        storageReference.child(firebaseAuth.getCurrentUser().getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imageView);
            }
        });


        name = findViewById(R.id.TextView_name_md);
        mobile = findViewById(R.id.TextView_mobile_md);
        city = findViewById(R.id.TextView_city_md);
        gotra = findViewById(R.id.TextView_gotra_md);
        qualify = findViewById(R.id.TextView_qualification_md);
        birthplace = findViewById(R.id.TextView_birthplace_md);
        occupation = findViewById(R.id.TextView_occupation_md);

       String USER_ID = firebaseAuth.getCurrentUser().getUid();

        DocumentReference documentReference = firebaseFirestore.collection("USER_DETAILS").document(USER_ID);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

            /*    try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                name.setText(documentSnapshot.getString("USER_NAME_"));
                occupation.setText(documentSnapshot.getString("OCCUPATION_"));
                gotra.setText(documentSnapshot.getString("GOTRA_"));
                city.setText(documentSnapshot.getString("CITY_"));
                birthplace.setText(documentSnapshot.getString("BIRTHPLACE_"));
                qualify.setText(documentSnapshot.getString("QUALIFICATION_"));
                mobile.setText(documentSnapshot.getString("MOBILE_"));

            }
        });



    }
}
