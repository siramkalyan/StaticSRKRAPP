package com.example.myorganisation.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myorganisation.MainActivity;
import com.example.myorganisation.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    public int position=0;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final ImageSwitcher imgsw;
       final ImageButton b1,b2;

        final int[] imgs={R.drawable.sagi1,R.drawable.sagi2,R.drawable.sagi3,R.drawable.sagi4,R.drawable.sagi5};
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        b1=root.findViewById(R.id.img2);
        b2=root.findViewById(R.id.img1);
        imgsw=root.findViewById(R.id.is);
        imgsw.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imgVw= new ImageView(getContext());
                imgVw.setImageResource(imgs[position]);
                return imgVw;
            }
        });
        imgsw.setInAnimation(getContext(), android.R.anim.fade_in);
        imgsw.setOutAnimation(getContext(), android.R.anim.fade_out);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(position>0)
                    position--;
                else if(position<0)
                    position = 0;
                imgsw.setImageResource(imgs[position]);
                Toast.makeText(getContext(),"You Have reached end",Toast.LENGTH_SHORT).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position<imgs.length)
                    position++;
                if(position>=imgs.length)
                    position = imgs.length-1;
                imgsw.setImageResource(imgs[position]);
            }
        });


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;


    }
}