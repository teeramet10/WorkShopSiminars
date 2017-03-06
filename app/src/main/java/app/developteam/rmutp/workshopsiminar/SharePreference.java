package app.developteam.rmutp.workshopsiminar;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by barbie on 3/7/2017.
 */

public class SharePreference {
    public static String FILE_NAME ="USER_FILE";
    public static String ID_USER="id";
    public static String USER="user";
    public static String STATUS ="status";
    SharedPreferences sharedPreferences;

    Context context;

    public SharePreference(Context context) {
        this.context = context;
    }

    public boolean getStatus(){
        sharedPreferences =context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        boolean status =sharedPreferences.getBoolean(STATUS,false);
        return status;
    }

    public void setStatus(boolean status){
        sharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(STATUS,status);
        editor.commit();
    }

    public void removeStatus(){
        sharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove(STATUS);
        editor.commit();
    }

    public int getIdUser(){
        sharedPreferences =context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        int id=sharedPreferences.getInt(ID_USER,-1);
        return id;
    }

    public void setIdUser(int id){
        sharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(ID_USER,id);
        editor.commit();
    }

    public void removeIdUser(){
        sharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove(ID_USER);
        editor.commit();
    }

    public String getUser(){
        sharedPreferences =context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String user=sharedPreferences.getString(USER,"");
        return user;
    }

    public void setUser(String user){
        sharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(USER,user);
        editor.commit();
    }

    public void removeUser(){
        sharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove(USER);
        editor.commit();
    }
}
