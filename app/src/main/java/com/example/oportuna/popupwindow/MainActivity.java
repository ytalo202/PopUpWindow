package com.example.oportuna.popupwindow;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Dialog myDialog;
    LinearLayout linearLayout;
    private RecyclerView myrecyclerView;
    private List<Contact> lstContact;
    RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDialog = new Dialog(this);



        lstContact = new ArrayList<>();
        String fl ="Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, " +
                "   ";
        lstContact.add(new Contact("Investigación Presupuestal","Estudio",R.drawable.f1,"18",fl));
        lstContact.add(new Contact("Implementación de Grupo Electrogeno","Proyecto",R.drawable.f1,"120",fl));
        lstContact.add(new Contact("Sistema Comercial","Proyecto",R.drawable.f1,"11",fl));
        lstContact.add(new Contact("Automatización de Adquisiciones","Proyecto",R.drawable.f1,"8",fl));
        lstContact.add(new Contact("Transición a la ciencia de datos","Articulo",R.drawable.f1,"5",fl));

        lstContact.add(new Contact("Automatización de Adquisiciones","Proyecto",R.drawable.f1,"8",fl));
        lstContact.add(new Contact("Transición a la ciencia de datos","Articulo",R.drawable.f1,"5",fl));

//        lstContact.add(new Contact("Ayuda sobre java","Foro",R.drawable.f1,"8",fl));

    }



    public void ShowPopup(View v) {
        TextView txtclose;
        myDialog.setContentView(R.layout.custompopup);

        linearLayout= myDialog.findViewById(R.id.linearLayout);
        LinearLayout reducir= myDialog.findViewById(R.id.reducir);











        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myrecyclerView = myDialog.findViewById(R.id.list_search);
        recyclerViewAdapter = new RecyclerViewAdapter(this,lstContact);
        myrecyclerView.setLayoutManager(new LinearLayoutManager(this  ));
        myrecyclerView.setAdapter(recyclerViewAdapter);
        Display display = getWindowManager().getDefaultDisplay();
        int ancho = display.getWidth();
        int  alto= display.getHeight();

        if (lstContact.size()>=6) {
            linearLayout.getLayoutParams().height=alto*1/2;
            linearLayout.getLayoutParams().width=ancho;
        }
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.getWindow().setGravity(Gravity.BOTTOM);
        myDialog.show();




        int bitmapWidth = alto;
        int bitmapHeight = linearLayout.getLayoutParams().height;
        // set maximum scroll amount (based on center of image)

        Log.v("wight",""+ancho);

        //int maxY = (int)((bitmapHeight / 2) - (height / 2));
        int maxY =  (bitmapWidth / 2);

        // set scroll limits
        final int maxTop = (maxY * -1);
        final int maxBottom = 0;





        linearLayout.setOnTouchListener(new View.OnTouchListener()
        {


            float downX, downY;
            int totalX, totalY;
            int scrollByX, scrollByY;
            public boolean onTouch(View view, MotionEvent event)
            {
                float currentX, currentY;
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getX();
                        downY = event.getY();
                        Log.v("toma","datos");

//                        if (downY>1000){
//                            myDialog.dismiss();
//                        }

                        break;


                    case MotionEvent.ACTION_UP:
                        downX = event.getX();
                        downY = event.getY();
//                        Log.v("toma","datos");

                        if (downY>1000){
                            myDialog.dismiss();
                        }

                        break;

                    case MotionEvent.ACTION_SCROLL:
                        downX = event.getX();
                        downY = event.getY();
//                        Log.v("toma","datos");

                        if (downY>1000){
                            myDialog.dismiss();
                        }

                        break;


                    case MotionEvent.ACTION_MOVE:
                        Log.v("toma","datos2");
                        currentX = event.getX();
                        currentY = event.getY();
                        scrollByY = (int)(downY - currentY);

                        // scrolling to top of image (pic moving to the bottom)
                        if (currentY > downY)
                        {
                            if (totalY == maxTop)
                            {
                                scrollByY = 0;
                            }
                            if (totalY > maxTop)
                            {
                                totalY = totalY + scrollByY;
                            }
                            if (totalY < maxTop)
                            {
                                scrollByY = maxTop - (totalY - scrollByY);
                                totalY = maxTop;
                            }
                        }

                        // scrolling to bottom of image (pic moving to the top)
                        if (currentY < downY)
                        {
                            if (totalY == maxBottom)
                            {
                                scrollByY = 0;
                            }
                            if (totalY < maxBottom)
                            {
                                totalY = totalY + scrollByY;
                            }

                            if (totalY > maxBottom)
                            {
                                scrollByY = maxBottom - (totalY - scrollByY);
                                totalY = maxBottom;
                            }
                        }

                        linearLayout.scrollBy(scrollByX, scrollByY);
                        downX = currentX;
                        downY = currentY;


                        break;



                }

                return true;
            }
        });



    }
}
