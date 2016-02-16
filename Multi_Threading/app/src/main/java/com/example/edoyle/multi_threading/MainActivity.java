package com.example.edoyle.multi_threading;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * the mainActivity is the activity loaded at app startup
 * @author Edward Doyle
 * @collaborators John Meyer, Klenton Stone, Wellesley Shumway
 */
public class MainActivity extends AppCompatActivity {

    ArrayAdapter<Integer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
    }

    /**
     * handles the case of the clear button being clicked
     * @param v the View that is clicked
     */
    public void clearClick(View v) {
        adapter.clear();
        ((ProgressBar) findViewById(R.id.progressBar)).setProgress(0);
        ((TextView)findViewById(R.id.numProg)).setText("");
    }

    /**
     * handles the case that the create button is clicked
     * @param v the View that is clicked
     */
    public void createClick(View v) {
        //Executes a new thread for CreateFileTask
        new CreateFile().execute(getFilesDir() + "numbers.txt");
    }

    /**
     * used for an instance of a CreateFile thread
     * @author  Edward Doyle
     * @see AsyncTask
     */
    private class CreateFile extends AsyncTask<String, Integer, Void> {

        /**
         * sets up the progress display to begin displaying progress
         */
        @Override
        protected void onPreExecute() {
            ((ProgressBar) findViewById(R.id.progressBar)).setProgress(0);
            ((TextView)findViewById(R.id.numProg)).setText("0%");
        }

        /**
         * defines what this thread will do as it creates the file
         * @param parameters in this case it is the name of the file being created
         * @return null
         */
        @Override
        protected Void doInBackground(String... parameters) {

            try (FileWriter fout = new FileWriter(parameters[0])) {
                //write each line to the file
                for (int i = 1; i < 11; ++i){
                    fout.write(i + System.getProperty("line.separator"));
                    //Pause to emulate latency
                    Thread.sleep(250);
                    publishProgress(i * 10);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * what is done when progress is made. Updates progress display
         * @param progress
         */
        @Override
        protected void onProgressUpdate(Integer... progress) {
            ((ProgressBar) findViewById(R.id.progressBar)).setProgress(progress[0]);
            ((TextView)findViewById(R.id.numProg)).setText(progress[0] + "%");
        }

        /**
         * lets the user know the file was successfully created
         * @param nothing does nothing
         */
        @Override
        protected void onPostExecute(Void nothing) {
            ((TextView)findViewById(R.id.numProg)).setText("");
            (Toast.makeText(getApplicationContext(), "File Successfully Created", Toast.LENGTH_SHORT)).show();
        }
    }

    /**
     * handles the case that the load button is clicked
     * @param v the View that is clicked
     */
    public void loadClick(View v) {
        new LoadFile().execute(getFilesDir() + "numbers.txt");
    }

    /**
     * used for an instance of a LoadFile thread
     * @author  Edward Doyle
     * @see AsyncTask
     */
    private class LoadFile extends AsyncTask<String, Integer, Void> {
        List<Integer> list = new ArrayList<>();

        /**
         * sets the progressbar and numerical display to 0
         */
        @Override
        protected void onPreExecute() {
            ((ProgressBar) findViewById(R.id.progressBar)).setProgress(0);
            ((TextView)findViewById(R.id.numProg)).setText("0%");
        }

        /**
         * the heart of the logic for loading a file
         * @param parameters the parameters passed in from execute()
         * @return null
         */
        @Override
         protected Void doInBackground(String... parameters) {
            try (Scanner fin = new Scanner(new File(parameters[0]))){
                while (fin.hasNextInt()) {
                    list.add(fin.nextInt());
                    Thread.sleep(250);
                    publishProgress(10);
                }
                fin.close();
            }
            catch (FileNotFoundException e) {
                //Hopefully we don't end up here
                System.out.println("The file 'number.txt' was not found");
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * what is done when progress is made. Updates progress display
         * @param values the progress that was published
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);
            bar.setProgress(bar.getProgress() + values[0]);
            ((TextView)findViewById(R.id.numProg)).setText(bar.getProgress() + "%");
        }

        /**
         * sets up the activity layout for after the data is loaded
         * @param nothing not used in function
         */
        @Override
        protected void onPostExecute(Void nothing) {
            adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
            ((ListView) findViewById(R.id.listView)).setAdapter(adapter);
            ((TextView)findViewById(R.id.numProg)).setText("");
            //let the user know loading was successful
            (Toast.makeText(getApplicationContext(), "File Successfully Loaded", Toast.LENGTH_SHORT)).show();
        }
    }




}