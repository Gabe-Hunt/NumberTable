package com.example.numbertable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buildTable(View view) {
        // Padding and tableFont resources are set for later use
        int padding = (int) (getResources().getDimension(R.dimen.padding) / (getResources().getDisplayMetrics().density));
        float tableFont = getResources().getDimension(R.dimen.tableFont);

        // EditTexts to capture user min and max numbers
        EditText minET = findViewById(R.id.min);
        EditText maxET = findViewById(R.id.max);
        int min = 0;
        int max = 1;

        // Try/catch block to make sure only numbers get input
        try {
            min = Integer.parseInt(minET.getText().toString());
            max = Integer.parseInt(maxET.getText().toString());
        } catch (InputMismatchException e){
            minET.setText("");
            maxET.setText("");
            Toast.makeText(this, "Incorrect input; please try again", Toast.LENGTH_SHORT).show();
        }

        TableLayout table = findViewById(R.id.table);
        table.removeAllViews();

        TableRow header = new TableRow(this);

        // Each following block creates a textview for each category, and sets sizes
        TextView num = new TextView(this);
        num.setText("n");
        num.setTextSize(TypedValue.COMPLEX_UNIT_PX, tableFont);
        num.setPadding(0,0,padding,0);

        TextView numSquared = new TextView(this);
        numSquared.setText(getString(R.string.squared));
        numSquared.setTextSize(TypedValue.COMPLEX_UNIT_PX, tableFont);
        numSquared.setPadding(0,0,padding,0);

        TextView numCubed = new TextView(this);
        numCubed.setText(getString(R.string.cubed));
        numCubed.setTextSize(TypedValue.COMPLEX_UNIT_PX, tableFont);
        numCubed.setPadding(0,0,padding,0);

        TextView numSQRT = new TextView(this);
        numSQRT.setText(getString(R.string.sqrt));
        numSQRT.setTextSize(TypedValue.COMPLEX_UNIT_PX, tableFont);
        numSQRT.setPadding(0,0,padding,0);

        TextView numFactorial = new TextView(this);
        numFactorial.setText(getString(R.string.factorial));
        numFactorial.setTextSize(TypedValue.COMPLEX_UNIT_PX, tableFont);
        numFactorial.setPadding(0,0,padding,0);

        CheckBox nFactorial = findViewById(R.id.factorial);
        CheckBox nSquared = findViewById(R.id.squared);
        CheckBox nCubed= findViewById(R.id.cubed);
        CheckBox nSQRT = findViewById(R.id.sqrt);

        HashSet<TextView> views = new HashSet<>();
        ArrayList<String> answers = new ArrayList<>();

        // If statements to check if a checkbox is marked
        if (nSquared.isChecked()) {
            header.addView(numSquared);
            views.add(numSquared);
            answers.add("squared");
        }
        if (nCubed.isChecked()) {
            header.addView(numCubed);
            views.add(numCubed);
            answers.add("cubed");
        }
        if (nSQRT.isChecked()) {
            header.addView(numSQRT);
            views.add(numSQRT);
            answers.add("sqrt");
        }
        if (nFactorial.isChecked()) {
            header.addView(numFactorial);
            views.add(numFactorial);
            answers.add("factorial");
        }
        table.addView(header);

        // For statement loops through designated numbers, then checks to see what boxes are checked, then computes accordingly
        for(int i = min; i <= max; i++) {
            TableRow row = new TableRow(this);

            for(int j = 0; j < views.size(); j++) {
                String a = "";
                TextView t = new TextView(this);
                t.setPadding(0,0,padding,0);
                t.setTextSize(TypedValue.COMPLEX_UNIT_PX, tableFont);
                if (answers.get(j).equals("squared")) {
                    a.equals(answers.get(j));
                    t.setText("" + squareN(i));
                    answers.remove(a);
                } else if (answers.get(j).equals("cubed")) {
                    a.equals(answers.get(j));
                    t.setText("" + cubeN(i));
                    answers.remove(a);
                } else if (answers.get(j).equals("sqrt")) {
                    a.equals(answers.get(j));
                    t.setText("" + rootN(i));
                    answers.remove(a);
                } else  if (answers.get(j).equals("factorial")){
                    a.equals(answers.get(j));
                    t.setText("" + factorialN(i));
                    answers.remove(a);
                }
                row.addView(t);
            }
            table.addView(row);
        }
    }

    /**
     *
     * @param n
     * @return n squared
     */
    private int squareN(int n) {
        return (n*n);
    }

    /**
     *
     * @param n
     * @return n cubed
     */
    private int cubeN(int n) {
        return (n*n*n);
    }

    /**
     *
     * @param n
     * @return square root of n
     */
    private double rootN(int n) {
        return Math.sqrt(n);
    }

    /**
     *
     * @param n
     * @return factorial of n
     */
    private int factorialN(int n) {
        for(int i = n; i >1; i--) {
            n*=(i-1);
        }
        return n;
    }
}
