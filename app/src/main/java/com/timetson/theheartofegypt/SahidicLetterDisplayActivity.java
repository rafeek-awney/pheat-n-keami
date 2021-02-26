package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.timetson.theheartofegypt.helpers.LettersHelper;
import com.timetson.theheartofegypt.helpers.localeHelper;
import com.timetson.theheartofegypt.modules.DataContainer;
import com.timetson.theheartofegypt.modules.PronounceModule;

import java.util.List;

public class SahidicLetterDisplayActivity extends AppCompatActivity {

    ////////// assign views ////////
    private TextView textViewName;
    private TextView textViewType;
    private String title;
    ////////////////////////////////

    ////////////for Language Setting///////////////////
    private void setLanguage(Context context, String language) {
        Resources resources = localeHelper.getLocalizedResources(context, language);
        textViewName.setText(resources.getString(R.string.letter_display_name));
        textViewType.setText(resources.getString(R.string.letter_display_type));
        title = resources.getString(R.string.letter_display_sahidic_title);

    }
    ////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_display);

        ////////initialization///////////
        final Context mContext = this;
        textViewName = findViewById(R.id.letter_name_title);
        textViewType = findViewById(R.id.letter_type_title);
        ///////////////////////////////

        ///////set language///////////
        setLanguage(mContext, DataContainer.LanguageCode);
        //////////////////////////

        // Ads code
        DataContainer.AdmobLoad(this, mContext, R.id.adView);
        // end Ads code

        final int position = getIntent().getIntExtra("POSITION", 0);
        final TextView Capital_letter = findViewById(R.id.cabital_letter);
        final TextView Small_letter = findViewById(R.id.small_letter);
        final TextView letter_type = findViewById(R.id.letter_type);
        final TextView letter_name = findViewById(R.id.letter_name);
        final String[] types = getResources().getStringArray(R.array.letters_type);
        final ScrollView scrollView = findViewById(R.id.scrollview);


        Capital_letter.setText(DataContainer.sahidicLetterModuleList.get(position).getCapital());
        Small_letter.setText(DataContainer.sahidicLetterModuleList.get(position).getLetter());
        letter_type.setText(types[DataContainer.sahidicLetterModuleList.get(position).getType()]);
        letter_name.setText(DataContainer.sahidicLetterModuleList.get(position).getName());

        DataContainer.SahidicPronounciation = new LettersHelper(mContext).getSahidicPronouncation(DataContainer.sahidicLetterModuleList.get(position).getLetter());
        setLayoutContents(mContext, findViewById(R.id.general), mContext.getResources().getString(R.string.letter_display_sahidic_title_coptic), title, DataContainer.SahidicPronounciation);
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Ready, move up
                scrollView.fullScroll(View.FOCUS_UP);
            }
        });


        findViewById(R.id.acadimic_bohiric).setVisibility(View.GONE);
        findViewById(R.id.new_bohiric).setVisibility(View.GONE);
        findViewById(R.id.late_bohiric).setVisibility(View.GONE);
        findViewById(R.id.letter_name_sounds).setVisibility(View.GONE);
    }

    private void setLayoutContents(Context context, View view, String copticTitle, String title, List<PronounceModule> list) {
        if (list.size() == 0) {
            view.setVisibility(View.GONE);
            return;
        }
        TextView copticTitleText = view.findViewById(R.id.coptic_title);
        TextView translatedTitleText = view.findViewById(R.id.translated_title);

        copticTitleText.setText(copticTitle);
        translatedTitleText.setText(title);

        ProunouncationAdapter adapter = new ProunouncationAdapter(context, list);
        ListView listView = view.findViewById(R.id.general_list);
        listView.setAdapter(adapter);
        listView.setEnabled(false);
        view.findViewById(R.id.expand_image).setVisibility(View.GONE);
    }


}
