package com.example.testmessage.testmessageapp.helper;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import com.example.testmessage.testmessageapp.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamHelper {


    public static InputStream getInpustreamInstance(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.stats);
        return inputStream;
    }

    /*private InputStream getInputStreamForVirtualFile(Context context, Uri uri, String mimeTypeFilter)
            throws IOException {

        ContentResolver resolver = context.getContentResolver();

        String[] openableMimeTypes = resolver.getStreamTypes(uri, mimeTypeFilter);

        if (openableMimeTypes == null ||
                openableMimeTypes.length < 1) {
            throw new FileNotFoundException();
        }

        return resolver
                .openTypedAssetFileDescriptor(uri, openableMimeTypes[0], null)
                .createInputStream();
    }*/

}
