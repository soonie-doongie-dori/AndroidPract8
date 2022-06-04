package ru.mirea.lukyanchuk.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Calculator#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calculator extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String line="";

    public Calculator() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Calculator.
     */
    // TODO: Rename and change types and number of parameters
    public static Calculator newInstance(String param1, String param2) {
        Calculator fragment = new Calculator();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Button bbuttonn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        TextView textView=  view.findViewById(R.id.textView666);

        pop_it(view,textView,R.id.but0);
        pop_it(view,textView,R.id.but1);
        pop_it(view,textView,R.id.but2);
        pop_it(view,textView,R.id.but3);
        pop_it(view,textView,R.id.but4);
        pop_it(view,textView,R.id.but5);
        pop_it(view,textView,R.id.but6);
        pop_it(view,textView,R.id.but7);
        pop_it(view,textView,R.id.but8);
        pop_it(view,textView,R.id.but9);
        pop_it(view,textView,R.id.plusbut);
        pop_it(view,textView,R.id.minusbut);
        pop_it(view,textView,R.id.divisionbut);
        pop_it(view,textView,R.id.multiplicationbut);
        pop_it(view,textView,R.id.clearbut);
        pop_it(view,textView,R.id.equalbut);

        return view;
    }
    public void pop_it(View view, TextView textView, int number){
        bbuttonn =  view.findViewById(number);
        bbuttonn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                line+=((Button)v).getText().toString();
                if (((Button)v).getText().toString().equals("C"))
                    line="";
                if (((Button)v).getText().toString().equals("="))
                {
                    String cutline = (String) textView.getText();
                    int k=0,j=0,countA=0,countB=0;
                    String a = "",b= "",c= "";
                    float answer = 0, first=0,second=0;
                    for (int i = 0; i < cutline.length(); i++) {
                        if (cutline.charAt(i)=='-' && i==0)
                            countA++;
                        else
                        {
                            if ((cutline.charAt(i)=='+'||cutline.charAt(i)=='-'||cutline.charAt(i)=='*'||cutline.charAt(i)=='/') && k==0)
                            {
                                k++;
                                j=i;
                            }

                            if (k==0)
                                a+=(String.valueOf(cutline.charAt(i)));
                            else
                            {
                                c =(String.valueOf(cutline.charAt(j)));
                                if (i > j)
                                {
                                    if (cutline.charAt(i)=='-' && i==j+1)
                                        countB++;
                                    else
                                        b+=(String.valueOf(cutline.charAt(i)));
                                }
                            }
                        }
                    }
                    first=Float.parseFloat(a);
                    second=Float.parseFloat(b);
                    System.out.println(first+" "+c+" "+second);
                    if (countA==1)
                        first=-first;
                    if (countB==1)
                        second=-second;
                    if (c.equals("+"))
                        answer=first+second;
                    if (c.equals("-"))
                        answer=first-second;
                    if (c.equals("*"))
                        answer = first * second;
                    if (c.equals("/"))
                        answer=first/second;
                    textView.setText(Float.toString(answer));
                    line="";
                }
                else textView.setText(line);
            }
        });
    }

}