package com.vivhp.qlct;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.StringTokenizer;

/**
 * Created by KidTjTjLuV on 22/10/2016.
 */

public class HienDoiTien implements TextWatcher {

    TextView textText;


    public HienDoiTien(TextView textText) {
        this.textText = textText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        try {
            textText.removeTextChangedListener(this);
            String value = textText.getText().toString();


            if (value != null && !value.equals("")) {

                if (value.startsWith(".")) {
                    textText.setText("0.");
                }
                if (value.startsWith("0") && !value.startsWith("0.")) {
                    textText.setText("");

                }


                String str = textText.getText().toString().replaceAll("\\.", "");
                if (!value.equals(""))
                    textText.setText(getDecimalFormattedString(str));
                textText.setText(textText.getText().toString().length());
            }
            textText.addTextChangedListener(this);
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static String getDecimalFormattedString(String value) {
        StringTokenizer lst = new StringTokenizer(value, ".");
        String str1 = value;
        String str2 = "";
        if (lst.countTokens() > 1) {
            str1 = lst.nextToken();
            str2 = lst.nextToken();
        }
        String str3 = "";
        int i = 0;
        int j = -1 + str1.length();
        if (str1.charAt(-1 + str1.length()) == '.') {
            j--;
            str3 = ".";
        }
        for (int k = j; ; k--) {
            if (k < 0) {
                if (str2.length() > 0)
                    str3 = str3 + "." + str2;
                return str3;
            }
            if (i == 3) {
                str3 = "." + str3;
                i = 0;
            }
            str3 = str1.charAt(k) + str3;
            i++;
        }

    }

    public static String trimCommaOfString(String string) {
//        String returnString; TextView
        if (string.contains(",")) {
            return string.replace(",", "");
        } else {
            return string;
        }

    }
}
