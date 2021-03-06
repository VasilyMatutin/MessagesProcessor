package ru.edu.hse.messagesprocessor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.content.Context;
import android.widget.Toast;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import java.lang.ref.WeakReference;

public class NetworkTask extends AsyncTask<Void, Void, Translation> {
    //private WeakReference<SMSTranslator> serviceReference;
    private String sourceLanguageCode;
    private String targetLanguageCode;
    private String sourceText;
    private Translation translation;
    GoogleCredentials credentials;
    AlertDialog alertDialog;

    NetworkTask(SMSTranslator context, String sourceLanguageCode, String targetLanguageCode, String sourceText, GoogleCredentials credentials) {
        //serviceReference = new WeakReference<>(context);
        this.sourceLanguageCode = sourceLanguageCode;
        this.targetLanguageCode = targetLanguageCode;
        this.sourceText = sourceText;
        this.credentials = credentials;
    }

    @Override
    protected Translation doInBackground(Void... voids) {
        Translate translate = TranslateOptions.newBuilder().setCredentials(credentials).build().getService();
        Translate.TranslateOption srcLang = Translate.TranslateOption.sourceLanguage(sourceLanguageCode);
        Translate.TranslateOption tgtLang = Translate.TranslateOption.targetLanguage(targetLanguageCode);
        // nmt means Neural Machine Translation
        Translate.TranslateOption model = Translate.TranslateOption.model("nmt");
        translation = translate.translate(sourceText, srcLang, tgtLang, model);
        return translation;
    }

    @Override
    protected void onPostExecute(Translation result) {
        super.onPostExecute(result);
        //final SMSTranslator service = serviceReference.get();
        //if (service == null) return;
        
	    //Toast.makeText(service.getBaseContext(), translation.getTranslatedText(), Toast.LENGTH_SHORT).show();
    }


}
