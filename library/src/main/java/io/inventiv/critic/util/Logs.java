package io.inventiv.critic.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Logs {

    public static File readLogcat(Context context) throws IOException {

        Process process = Runtime.getRuntime().exec(new String[]{ "logcat", "--pid=" + android.os.Process.myPid(), "-t", "500", "-v", "threadtime" });
        File file = File.createTempFile("logcat", ".txt", context.getExternalFilesDir(null));

        BufferedReader bufferedReader = null;
        FileOutputStream fileOutputStream = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            fileOutputStream = new FileOutputStream(file);
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                fileOutputStream.write(line.getBytes());
                fileOutputStream.write("\n".getBytes());
            }
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }catch(IOException e){}
            try{
                if(fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }catch(IOException e){}
        }

        return file;
    }
}
