package com.example.mangal.polargraph;

import android.support.annotation.ColorInt;

public class PolarDataSet {
        private int colorBackground;
        private int colorBorder;
        private int value;

        public PolarDataSet(@ColorInt int colorBackground, @ColorInt int colorBorder, int value) {
            this.colorBackground = colorBackground;
            this.colorBorder = colorBorder;
            this.value = value;
        }

        public int getColorBackground() {
            return colorBackground;
        }

        public int getColorBorder() {
            return colorBorder;
        }

        public int getValue() {
            return value;
        }



}
