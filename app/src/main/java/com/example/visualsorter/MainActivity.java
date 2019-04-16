package com.example.visualsorter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    private ListView sortedLV, unsortedLV;
    private ArrayAdapter<String> sortedAA, unsortedAA;
    private int[] sortedNumbers, unsortedNumbers;
    private String[] sortedStrings, unsortedStrings;
    private final int numberOfElements = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sortedLV = this.findViewById(R.id.sortedLV);
        this.unsortedLV = this.findViewById(R.id.unsortedLV);

        this.sortedNumbers = new int[this.numberOfElements];
        this.unsortedNumbers = new int[this.numberOfElements];
        this.sortedStrings = new String[this.numberOfElements];
        this.unsortedStrings = new String[this.numberOfElements];

        this.sortedAA = new ArrayAdapter<String>(this, R.layout.simple_listview_row, this.sortedStrings);
        this.unsortedAA = new ArrayAdapter<String>(this, R.layout.simple_listview_row, this.unsortedStrings);

        this.sortedLV.setAdapter(this.sortedAA);
        this.unsortedLV.setAdapter(this.unsortedAA);

        this.initializeArrays();
    }

    private void insertionSort(int[] ar)
    {
        int theFollower, swap;

        for(int currStart = 1; currStart < ar.length; currStart++) {
            theFollower = currStart;
            while(theFollower > 0 && ar[theFollower] < ar[theFollower-1]) {
                swap = ar[theFollower];
                ar[theFollower] = ar[theFollower-1];
                ar[theFollower-1] = swap;
                theFollower--;
            }
        }
    }

    private void mergeSort(int[] ar)
    {
        this.mergeSortHelper(ar, 0, ar.length-1);
    }

    private void merge(int[] ar, int begin1, int end1, int begin2, int end2)
    {
        int[] temp = new int[end2 - begin1 + 1];
        int pos1 = begin1;
        int pos2 = begin2;
        for(int i = 0; i < temp.length; i++)
        {
            if(pos1 <= end1 && pos2 <= end2)
            {
                if(ar[pos1] < ar[pos2]) {
                    temp[i] = ar[pos1];
                    pos1++;
                }
                else {
                    temp[i] = ar[pos2];
                    pos2++;
                }
            }
            else if(pos1 <= end1) {
                temp[i] = ar[pos1];
                pos1++;
            }
            else {
                temp[i] = ar[pos2];
                pos2++;
            }
        }

        int pos = begin1;
        for(int i = 0; i < temp.length; i++)
        {
            ar[pos] = temp[i];
            pos++;
        }
    }

    private void mergeSortHelper(int[] ar, int begin, int end)
    {
        if(begin != end) {
            int begin1 = begin;
            int end1 = (end + begin)/2;
            int begin2 = end1 + 1;
            int end2 = end;
            this.mergeSortHelper(ar, begin1, end1);
            this.mergeSortHelper(ar, begin2, end2);
            this.merge(ar, begin1, end1, begin2, end2);
        } }



    private int factorial(int n)
    {
        if(n == 1) {
            return 1;
        }
        return n * this.factorial(n-1);
    }

    public void insertionSortButtonPressed(View vy)
    {

        this.insertionSort(this.unsortedNumbers);
        this.updateStringArrays();
    }

    public void mergeSortButtonPressed(View vy)
    {

        this.mergeSort(this.unsortedNumbers);
        this.updateStringArrays();
    }

    public void resetButtonPressed(View austin)
    {
        this.initializeArrays();
    }

    private void initializeArrays()
    {

        this.fillRandomIntArray(this.unsortedNumbers);
        this.copyContentsOfIntArrays(this.unsortedNumbers, this.sortedNumbers);
        this.updateStringArrays();
    }

    private void updateStringArrays()
    {
        this.copyIntArrayToStringArray(this.unsortedNumbers, this.unsortedStrings);
        this.copyIntArrayToStringArray(this.sortedNumbers, this.sortedStrings);
        this.sortedAA.notifyDataSetChanged();
        this.unsortedAA.notifyDataSetChanged();
    }

    private void copyIntArrayToStringArray(int[] arInts, String[] arStrings)
    {
        for(int i = 0; i < arInts.length; i++)
        {
            arStrings[i] = "" + arInts[i];

        }
    }

    private void copyContentsOfIntArrays(int[] source, int[] destination)
    {
        for(int i = 0; i < source.length; i++)
        {
            destination[i] = source[i];
        }
    }

    private void fillRandomIntArray(int[] ar)
    {
        Random r = new Random();

        for(int i = 0; i < ar.length; i++)
        {
            ar[i] = r.nextInt(500);
        } }
}
