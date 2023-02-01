package com.eurya.luajit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.eurya.luajit.function.Print;
import java.io.File;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;
import android.content.Intent;
import android.util.Log;

public class MainActivity extends Activity {
    private static final String TAG = "LuaJitActivity";
    private LuaJitApplication app;
    private LuaState state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (LuaJitApplication) getApplication();
        state = LuaStateFactory.newLuaState();
        state.openLibs();
        try {
            init();
            Print print = new Print(this, state);
            // Import im = new Import(this, state);
            String code = getIntent().getExtras().get("code").toString();
            loadString(code);
        } catch (LuaJitException e) {
            sendError(e);
        }
    }

    private void init() {
        state.pushJavaObject(this);
        state.setGlobal("activity");
        state.getGlobal("package");
        state.getField(-1, "path");
        state.pushString(";" + app.getLuaLibs());
        state.concat(2);
        state.setField(-2, "path");
        state.pushString(app.soLibs);
        state.setField(-2, "cpath");
        state.pop(1);
    }

    public final void loadString(String code) throws LuaJitException {
        state.setTop(0);
        int ok = state.LloadString(code);
        if (ok == 0) {
            state.getGlobal("debug");
            state.getField(-1, "traceback");
            state.remove(-2);
            state.insert(-2);
            ok = state.pcall(0, 0, -2);
            if (ok == 0) {

            }
        } else {
            Log.e(TAG, "LoadString()");
            throw new LuaJitException(errorReason(ok) + ": " + state.toString(-1));
        }
    }

    public final void loadFile(String path) throws LuaJitException {
        File file = new File(path);
        loadFile(file);
    }

    public final void loadFile(File file) throws LuaJitException {
        state.setTop(0);
        int ok = state.LloadFile(file.getAbsolutePath());
        if (ok == 0) {
            state.getGlobal("debug");
            state.getField(-1, "traceback");
            state.remove(-2);
            state.insert(-2);
            ok = state.pcall(0, 0, -2);
            if (ok == 0) {

            } 
        } else {
            Log.e(TAG, "LoadFile()");
            throw new LuaJitException(errorReason(ok) + ": " + state.toString(-1));
        }
    }

    private String errorReason(Integer error) {
        if (error == null) return "";
        if (error.equals(LuaState.LUA_ERRMEM)) return "Out of memory";
        else if (error.equals(LuaState.LUA_ERRSYNTAX)) return "Syntax error";
        else if (error.equals(LuaState.LUA_ERRRUN)) return "Runtime error";
        else if (error.equals(LuaState.LUA_YIELD)) return "Yield error";
        else {
            return "Unknown error " + error;
        }
    }

    public <T extends LuaJitException> void sendError(T e) {
        TextView tv = new TextView(this);
        tv.setText(((Exception) e).toString());
        setContentView(tv);
    }

    @Override
    protected void onDestroy() {
        if (state != null) {
            state.close();
        }
        super.onDestroy();
    }
}
