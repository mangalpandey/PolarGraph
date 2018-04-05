package com.example.mangal.polargraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

public class PolarGraph extends View {

    private int totalResult = 0;

    private ArrayList<PolarDataSet> polarDataSetArrayList = new ArrayList<>();

    public PolarGraph(Context context) {
        super(context);
        init();
    }

    public PolarGraph(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PolarGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        polarDataSetArrayList.add(new PolarDataSet(Color.RED, Color.MAGENTA, 70));
        polarDataSetArrayList.add(new PolarDataSet(Color.GREEN, Color.GRAY, 80));
        polarDataSetArrayList.add(new PolarDataSet(Color.BLUE, Color.MAGENTA, 60));
        for(int index=0;index<polarDataSetArrayList.size();index++){
            totalResult = totalResult + polarDataSetArrayList.get(index).getValue();
        }
        invalidate();
    }

    public void setPolarDataSet(ArrayList<PolarDataSet> polarDataSetArrayList){
        totalResult = 0;
        this.polarDataSetArrayList = polarDataSetArrayList;
        for(int index=0;index<polarDataSetArrayList.size();index++){
            totalResult = totalResult + (polarDataSetArrayList.get(index).getValue());
        }
        invalidate();
    }


    private int getAngle(int angle){
        if(angle>360){
            return angle - 360;
        }
        return angle;
    }
    @Override
    protected void onDraw(final Canvas canvas) {
        try {
            super.onDraw(canvas);
            int number = polarDataSetArrayList.size();
            ArrayList<Integer> anglesEndList = new ArrayList<>();
            int a;
            for (int index = 0; index < number; index++) {
                a = (polarDataSetArrayList.get(index).getValue());
                a = (a * 360) / totalResult;
                anglesEndList.add(a);
            }

            ArrayList<Integer> anglesStartList = new ArrayList<>();
            anglesStartList.add(270);
            for (int index = 1; index < number; index++) {
                anglesStartList.add(getAngle(anglesStartList.get(anglesStartList.size() - 1) + anglesEndList.get(index - 1)));
            }


            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int padding = 50;
            int radius = 0;
            if(centerX<=centerY) {
                radius = centerX - padding;
            }else{
                radius = centerY - padding;
            }
            int margin = radius / number;

            Paint paintOne = new Paint(ANTI_ALIAS_FLAG);
            paintOne.setStyle(Paint.Style.FILL);


            Paint paintTwo = new Paint(ANTI_ALIAS_FLAG);
            paintTwo.setStyle(Paint.Style.STROKE);
            paintTwo.setStrokeWidth(4);

            RectF oval = new RectF();
            int temp;
            PolarDataSet polarDataSet;
            for (int index = 0; index < number; index++) {
                polarDataSet = polarDataSetArrayList.get(index);
                paintOne.setColor(polarDataSet.getColorBackground());
                paintTwo.setColor(polarDataSet.getColorBorder());
                temp = (number - 1 - index);
                oval.set(margin * temp + padding, margin * temp + padding, radius * 2 - margin * temp + padding, radius * 2 - margin * temp + padding);
                canvas.drawArc(oval, anglesStartList.get(index), anglesEndList.get(index), true, paintOne);
                canvas.drawArc(oval, anglesStartList.get(index), anglesEndList.get(index), true, paintTwo);
            }

            Paint paintCircle = new Paint(ANTI_ALIAS_FLAG);
            paintCircle.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            paintCircle.setStrokeWidth(1);
            paintCircle.setStyle(Paint.Style.STROKE);
            for (int index = 0; index < number; index++) {
                canvas.drawCircle(centerX, radius+padding, radius - margin * index, paintCircle);
            }

            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.BLACK);
            Typeface bold = Typeface.DEFAULT;
            paint.setTypeface(bold);
            paint.setTextSize(40);
            for (int index = 0; index < number; index++) {
                temp = (number - 1 - index);
                int yPos = (int) ((margin * temp) + padding - ((paint.descent() + paint.ascent()) / 2));
                paint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(String.valueOf(polarDataSetArrayList.get(index).getValue()), centerX, yPos, paint);
            }

        }catch (Exception e){

        }
    }




}
