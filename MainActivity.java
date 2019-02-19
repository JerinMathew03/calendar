package com.example.windows.cal;

import java.util.Calendar;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;


public class MainActivity extends AppCompatActivity {
    int d;
    int year, month;
    public String[] monthsName = {
            "",                               // leave empty so that months[1] = "January"
            "JANUARY", "FEBRUARY", "MARCH",
            "APRIL", "MAY", "JUNE",
            "JULY", "AUGUST", "SEPTEMBER",
            "OCTOBER", "NOVEMBER", "DECEMBER"     // months[i] = name of month i
    };

    //from where each month to be start
    private String[] weekStart = {" ", " ", " ", " ", " ", " ", " "};
    private int[] eachMonthEnd = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};//how many days in each month
    private Button next;
    int total;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView currentMonth = (TextView) findViewById(R.id.curr_mnth);
        TextView currentEnglishYear = (TextView) findViewById(R.id.engyear);
        //CURRENT MONTH DISPLAY
        Calendar cal = Calendar.getInstance();
        String monthsCurrent = monthsName[cal.get(Calendar.MONTH) + 1];
        currentMonth.setText(monthsCurrent + "");
        int val = cal.get(Calendar.MONTH) + 1; // current month ie;month in letters.eg:January

        int currentYear = cal.get(Calendar.YEAR);
        currentEnglishYear.setText(currentYear + "");
        year = currentYear;// current year

        month = val;// current month :index value


        //next month
        // Capture our image view from layout
        LinearLayout monthDisplay = (LinearLayout) findViewById(R.id.MonthDisplay);
        d =day(month, 1, year);
        calendar(month, year, d);
    }


    private void calendar(int month, int year, int d) {
        int row = 1;
        int column = 7;


        // check for leap year
        if (month == 2 && isLeapYear(year)) {
            total = d +29  ;
        }
        else {
            total = d + eachMonthEnd[month];//display date
            // get a reference to the already created main layout
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);


        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        // setting column and row count
        gridLayout.setColumnCount(column);
        // gridLayout.setRowCount(row);
        // declare inflater layout
        LayoutInflater inflater = LayoutInflater.from(this);

        gridLayout.removeAllViews();// remove all item from grid layout
        int k = 1;
        for (int i = 0; i < total; i++) {


            RelativeLayout inflatedLayout = (RelativeLayout) inflater.inflate(R.layout.addon, gridLayout, false);

            TextView t2 = inflatedLayout.findViewById(R.id.addon1);

            if (i < d) {

                t2.setText(weekStart[i] + "");//display from where each month to be start
                gridLayout.addView(inflatedLayout);
            } else {


                t2.setText(String.valueOf(k));
                gridLayout.addView(inflatedLayout);// display actual dates
                k++;
            }

            // set text t1 to bold
            t2.setTypeface(null, Typeface.BOLD);


            // define border between grid layout
            inflatedLayout.setBackgroundResource(R.drawable.border_grid);

        }


    }


    private static int day(int month, int day, int year) {
        int y = year - (14 - month) / 12;
        int x = y + y / 4 - y / 100 + y / 400;
        int m = month + 12 * ((14 - month) / 12) - 2;
        int d = (day + x + (31 * m) / 12) % 7;
        return d;
    }

    // return true if the given year is a leap year
    private static boolean isLeapYear(int year) {
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
            return true;
        } else {
            return false;
        }

    }


    public void moveF(View view) {
        if(view.getId()==R.id.b2)
        {
            month=month+1;
            if(month>12)
            {
                month=1;
                year=year+1;
            }

            Calculation(month,1,year);
        }
    }


    public void Calculation(int mo,int da,int ye)
    {
     d=day(mo,da,ye);
        TextView currentMonth = (TextView) findViewById(R.id.curr_mnth);
        currentMonth.setText(monthsName[mo] + ""); //prints current month
        TextView currentEnglishYear = (TextView) findViewById(R.id.engyear);
        currentEnglishYear.setText(ye + "");//prints current year

     calendar(mo,ye,d);
    }

    public void moveB(View view) {
        if(view.getId()==R.id.b1)
        {
            month=month-1;

            if(month<1)
            {
                month=12;
                year=year-1;
            }


            Calculation(month,1,year);
        }

    }
}


















