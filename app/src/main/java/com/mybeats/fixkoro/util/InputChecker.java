package com.mybeats.fixkoro.util;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by MUSABBIR MAMUN on 26-Sep-17.
 */
public class InputChecker {
    public String blockCharacterSet = "*~^\"|$%><{}[]&+`?:;=!'";
    public InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };
}
