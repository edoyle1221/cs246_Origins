package com.example.edoyle.scripture;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private int numParts;
    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    static String scripture = "";
    public static final Map<Integer, String> myMap;
    static {
        Map<Integer, String> aMap = new HashMap<>();
        aMap.put(0, "1-ne");
        aMap.put(1, "2-ne");
        aMap.put(2, "jacob");
        aMap.put(3, "enos");
        aMap.put(4, "jarom");
        aMap.put(5, "omni");
        aMap.put(6, "w-of-m");
        aMap.put(7, "mosiah");
        aMap.put(8, "alma");
        aMap.put(9, "hel");
        aMap.put(10, "3-ne");
        aMap.put(11, "4-ne");
        aMap.put(12, "morm");
        aMap.put(13, "ether");
        aMap.put(14, "moro");
        aMap.put(15, "gen");
        aMap.put(16, "ex");
        aMap.put(17, "lev");
        aMap.put(18, "num");
        aMap.put(19, "deut");
        aMap.put(20, "josh");
        aMap.put(21, "judg");
        aMap.put(22, "ruth");
        aMap.put(23, "1-sam");
        aMap.put(24, "2-sam");
        aMap.put(25, "1-kgs");
        aMap.put(26, "2-kgs");
        aMap.put(27, "1-chr");
        aMap.put(28, "2-chr");
        aMap.put(29, "ezra");
        aMap.put(30, "neh");
        aMap.put(31, "esth");
        aMap.put(32, "job");
        aMap.put(33, "ps");
        aMap.put(34, "prov");
        aMap.put(35, "eccl");
        aMap.put(36, "song");
        aMap.put(37, "isa");
        aMap.put(38, "jer");
        aMap.put(39, "lam");
        aMap.put(40, "ezek");
        aMap.put(41, "dan");
        aMap.put(42, "hosea");
        aMap.put(43, "joel");
        aMap.put(44, "amos");
        aMap.put(45, "obad");
        aMap.put(46, "jonah");
        aMap.put(47, "micah");
        aMap.put(48, "nahum");
        aMap.put(49, "hab");
        aMap.put(50, "zeph");
        aMap.put(51, "hag");
        aMap.put(52, "zech");
        aMap.put(53, "mal");
        aMap.put(54, "matt");
        aMap.put(55, "mark");
        aMap.put(56, "luke");
        aMap.put(57, "john");
        aMap.put(58, "acts");
        aMap.put(59, "rom");
        aMap.put(60, "1-cor");
        aMap.put(61, "2-cor");
        aMap.put(62, "gal");
        aMap.put(63, "eph");
        aMap.put(64, "philip");
        aMap.put(65, "col");
        aMap.put(66, "1-thes");
        aMap.put(67, "2-thes");
        aMap.put(68, "1-tim");
        aMap.put(69, "2-tim");
        aMap.put(70, "titus");
        aMap.put(71, "philem");
        aMap.put(72, "heb");
        aMap.put(73, "james");
        aMap.put(74, "1-pet");
        aMap.put(75, "2-pet");
        aMap.put(76, "1-jn");
        aMap.put(77, "2-jn");
        aMap.put(78, "3-jn");
        aMap.put(79, "jude");
        aMap.put(80, "rev");
        aMap.put(81, "D&C");
        aMap.put(82, "D+C");
        aMap.put(83, "DOCTRINE AND COVENANTS");
        aMap.put(84,  "js-h");
        aMap.put(85,  "js-h");
        aMap.put(86, "js-h");
        aMap.put(87, "js-h");
        aMap.put(88, "moses");
        aMap.put(89,  "abr");
        aMap.put(90,  "abr/fac-1");
        aMap.put(91, "abr/fac-2");
        aMap.put(92, "abr/fac-3");
        aMap.put(93, "js-m");
        aMap.put(94,  "js-m");
        aMap.put(95,  "a-of-f");
        myMap = Collections.unmodifiableMap(aMap);
    }
    public static final String[] SCRIPTURES = new String[] {
            "1 NEPHI", "2 NEPHI", "JACOB", "ENOS", "JAROM", "OMNI", "WORDS OF MORMON", "MOSIAH",
            "ALMA", "HELAMAN", "3 NEPHI", "4 NEPHI", "MORMON", "ETHER", "MORONI", "GENESIS",
            "EXODUS", "LEVITICUS", "NUMBERS", "DEUTERONOMY", "JOSHUA", "JUDGES", "RUTH", "1 SAMUEL",
            "2 SAMUEL", "1 KINGS", "2 KINGS", "1 CHRONICLES", "2 CHRONICLES", "EZRA", "NEHEMIAH",
            "ESTHER", "JOB", "PSALMS", "PROVERBS", "ECCLESIASTES", "SONG OF SOLOMON", "ISAIAH",
            "JEREMIAH", "LAMENTATIONS", "EZEKIEL", "DANIEL", "HOSEA", "JOEL", "AMOS", "OBADIAH",
            "JONAH", "MICAH", "NAHUM", "HABAKKUK", "ZEPHANIAH", "HAGGAI", "ZECHARIAH", "MALACHI",
            "MATTHEW", "MARK", "LUKE", "JOHN", "ACTS", "ROMANS", "1 CORINTHIANS", "2 CORINTHIANS",
            "GALATIANS", "EPHESIANS", "PHILIPPIANS", "COLOSSIANS", "1 THESSALONIANS",
            "2 THESSALONIANS", "1 TIMOTHY", "2 TIMOTHY", "TITUS", "PHILEMON", "HEBREWS", "JAMES",
            "1 PETER", "2 PETER", "1 JOHN", "2 JOHN", "3 JOHN", "JUDE", "REVELATION", "D&C", "D+C",
            "DOCTRINE AND COVENANTS", "JSH", "JSHISTORY", "JOSEPH SMITH HISTORY",
            "JOSEPH SMITH—HISTORY", "MOSES", "ABRAHAM", "FACSIMILE 1", "FACSIMILE 2", "FACSIMILE 3",
            "JOSEPH SMITH—MATTHEW", "JOSEPH SMITH MATTHEW", "ARTICLES OF FAITH"
    };

    public static final String[] SCRIPTURESC = new String[] {
            "1 Nephi", "2 Nephi", "Jacob", "Enos", "Jarom", "Omni", "Words Of Mormon", "Mosiah",
            "Alma", "Helaman", "3 Nephi", "4 Nephi", "Mormon", "Ether", "Moroni", "Genesis",
            "Exodus", "Leviticus", "Numbers", "Deuteronomy", "Joshua", "Judges", "Ruth", "1 Samuel",
            "2 Samuel", "1 Kings", "2 Kings", "1 Chronicles", "2 Chronicles", "Ezra", "Nehemiah",
            "Esther", "Job", "Psalms", "Proverbs", "Ecclesiastes", "Song Of Solomon", "Isaiah",
            "Jeremiah", "Lamentations", "Ezekiel", "Daniel", "Josea", "Joel", "Amos", "Obadiah",
            "Jonah", "Micah", "Nahum", "Habakkuk", "Zephaniah", "Haggai", "Zechariah", "Malachi",
            "Matthew", "Mark", "Luke", "John", "Acts", "Romans", "1 Corinthians", "2 Corinthians",
            "Galatians", "Ephesians", "Philippians", "Colossians", "1 Thessalonians",
            "2 Thessalonians", "1 Timothy", "2 Timothy", "Titus", "Philemon", "Hebrews", "James",
            "1 Peter", "2 Peter", "1 John", "2 John", "3 John", "Jude", "Revelation", "D&C",
            "D+C", "Doctrine And Covenants", "JSH", "JSHistory", "Joseph Smith History",
            "Joseph Smith—History", "Moses", "Abraham", "Facsimile 1", "Facsimile 2",
            "Facsimile 3", "Joseph Smith—Matthew", "Joseph Smith Matthew", "Articles Of Faith"
    };

    public static final String[] BOM = new String[] {
            "1 NEPHI", "2 NEPHI", "JACOB", "ENOS", "JAROM", "OMNI", "WORDS OF MORMON", "MOSIAH",
            "ALMA", "HELAMAN", "3 NEPHI", "4 NEPHI", "MORMON", "ETHER", "MORONI"
    };
    public static final String[] OT = new String[] {
            "GENESIS", "EXODUS", "LEVITICUS", "NUMBERS", "DEUTERONOMY", "JOSHUA", "JUDGES", "RUTH",
            "1 SAMUEL", "2 SAMUEL", "1 KINGS", "2 KINGS", "1 CHRONICLES", "2 CHRONICLES", "EZRA",
            "NEHEMIAH", "ESTHER", "JOB", "PSALMS", "PROVERBS", "ECCLESIASTES", "SONG OF SOLOMON",
            "ISAIAH", "JEREMIAH", "LAMENTATIONS", "EZEKIEL", "DANIEL", "HOSEA", "JOEL", "AMOS",
            "OBADIAH", "JONAH", "MICAH", "NAHUM", "HABAKKUK", "ZEPHANIAH", "HAGGAI", "ZECHARIAH",
            "MALACHI"
    };

    public static final String[] NT = new String[] {
            "MATTHEW", "MARK", "LUKE", "JOHN", "ACTS", "ROMANS", "1 CORINTHIANS", "2 CORINTHIANS",
            "GALATIANS", "EPHESIANS", "PHILIPPIANS", "COLOSSIANS", "1 THESSALONIANS",
            "2 THESSALONIANS", "1 TIMOTHY", "2 TIMOTHY", "TITUS", "PHILEMON", "HEBREWS", "JAMES",
            "1 PETER", "2 PETER", "1 JOHN", "2 JOHN", "3 JOHN", "JUDE", "REVELATION"
    };

    public static final String[] POGP = new String[] {
            "JSH", "JSHISTORY", "JOSEPH SMITH HISTORY", "JOSEPH SMITH—HISTORY", "MOSES", "ABRAHAM",
            "FACSIMILE 1", "FACSIMILE 2", "FACSIMILE 3", "JOSEPH SMITH—MATTHEW",
            "JOSEPH SMITH MATTHEW", "ARTICLES OF FAITH"
    };

    public static final String[] DC = new String[] {
            "D&C", "D+C", "DOCTRINE AND COVENANTS"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SCRIPTURESC);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.editText);
        textView.setAdapter(adapter);
        AutoCompleteTextView txt = (AutoCompleteTextView) findViewById(R.id.editText);
        txt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                AutoCompleteTextView book = (AutoCompleteTextView) findViewById(R.id.editText);
                if (Arrays.asList(SCRIPTURES).contains(book.getText().toString().trim().toUpperCase())) {
                    findViewById(R.id.textView6).setVisibility(View.INVISIBLE);
                    book.setText(Arrays.asList(SCRIPTURESC).get(Arrays.asList(SCRIPTURES).
                            indexOf(book.getText().toString().trim().toUpperCase())));
                    // WordUtils.capitalizeFully(book.getText().toString());
                } else if (!book.getText().toString().equals(""))
                    findViewById(R.id.textView6).setVisibility(View.VISIBLE);

            }
        });
        EditText txt2 = (EditText) findViewById(R.id.editText4);
        txt2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText chapter = (EditText) findViewById(R.id.editText4);
                if (chapter.getText().toString().trim().matches("^[0-9]+$")) {
                    findViewById(R.id.textView7).setVisibility(View.INVISIBLE);
                } else if (!chapter.getText().toString().equals(""))
                    findViewById(R.id.textView7).setVisibility(View.VISIBLE);

            }
        });
        EditText txt3 = (EditText) findViewById(R.id.editText5);
        txt3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
             validateVerse();
            }
        });


    }
    private boolean validateVerse() {
        EditText verse = (EditText) findViewById(R.id.editText5);
        String [] elements = verse.getText().toString().split("-");
        if (elements.length > 2)
            findViewById(R.id.textView8).setVisibility(View.VISIBLE);
        else {
            if (elements[0].trim().matches("^[0-9]+$")) {
                if (elements.length == 2) {
                    if (elements[1].trim().matches("^[0-9]+$")) {
                        findViewById(R.id.textView8).setVisibility(View.INVISIBLE);
                        return true;
                    }
                    else {
                        findViewById(R.id.textView8).setVisibility(View.VISIBLE);
                        return false;
                    }
                }
                else {
                    findViewById(R.id.textView8).setVisibility(View.INVISIBLE);
                    return true;
                }
            }
            else if (!verse.getText().toString().equals("")) {
                findViewById(R.id.textView8).setVisibility(View.VISIBLE);
                return false;
            }
            else {
                findViewById(R.id.textView8).setVisibility(View.INVISIBLE);
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        // Toast.makeText(this, "Left to Right swipe [Next]", Toast.LENGTH_SHORT).show ();
                    }

                    // Right to left swipe action
                    else
                    {
                        validateInput();
                        // Toast.makeText(this, "Right to Left swipe [Previous]", Toast.LENGTH_SHORT).show ();
                    }

                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
       // Toast.makeText(this, "" + x1 + " " + x2, Toast.LENGTH_SHORT).show();
        onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clearFocus(View v){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void validateInput() {
        AutoCompleteTextView book = (AutoCompleteTextView) findViewById(R.id.editText);
        ScrollView sv = (ScrollView)findViewById(R.id.lo);
        EditText chapter = (EditText) findViewById(R.id.editText4);
        EditText verse = (EditText) findViewById(R.id.editText5);
        if (book.getText().toString().trim().equals("")) {
            book.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(book, InputMethodManager.SHOW_IMPLICIT);
            sv.scrollTo(book.getLeft(), book.getTop());
        }
        else if (chapter.getText().toString().trim().equals("") || !chapter.getText().toString().trim().matches("^[0-9]+$")) {
            chapter.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(chapter, InputMethodManager.SHOW_IMPLICIT);
            sv.scrollTo(chapter.getLeft(), chapter.getTop());
        }
        else if (verse.getText().toString().trim().equals("") || !validateVerse()) {
        verse.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(verse, InputMethodManager.SHOW_IMPLICIT);
            sv.scrollTo(verse.getLeft(), verse.getTop());
        }
        else {
        if (Arrays.asList(SCRIPTURES).contains(book.getText().toString().trim().toUpperCase())) {
            Intent i = new Intent(MainActivity.this, Display.class);
            startActivity(i);
            book.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(book, InputMethodManager.SHOW_IMPLICIT);
            findViewById(R.id.textView6).setVisibility(View.INVISIBLE);
            book.setText(Arrays.asList(SCRIPTURESC).get(Arrays.asList(SCRIPTURES).
                    indexOf(book.getText().toString().trim().toUpperCase())));
            scripture = book.getText().toString().trim() + " " +
                    chapter.getText().toString().trim() + " " + verse.getText().toString().trim();
            book.setText("");
            chapter.setText("");
            verse.setText("");
        }
        else {
            book.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(book, InputMethodManager.SHOW_IMPLICIT);
            findViewById(R.id.textView6).setVisibility(View.VISIBLE);
            sv.scrollTo(book.getLeft(), book.getTop());
        }

        }
    }

    public void passText(View v) {
        validateInput();
        }
}

