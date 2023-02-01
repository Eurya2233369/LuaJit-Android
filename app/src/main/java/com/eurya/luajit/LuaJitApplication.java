package com.eurya.luajit;

import android.app.Application;
import android.content.Context;

public class LuaJitApplication extends Application implements LuaContext {

    private final static String luas = "/?.lua";
    private final static String so = "/?.so";
    protected String luaLibs;
    protected String soLibs;
    
    @Override
    public void onCreate() {
        super.onCreate();
        luaLibs = getDir("luaLibs", Context.MODE_PRIVATE).getAbsolutePath();
        soLibs = getApplicationInfo().nativeLibraryDir + so;
    }
    

    @Override
    public String getLuaLibs() {
        return luaLibs + luas;
    }

    
    
}
