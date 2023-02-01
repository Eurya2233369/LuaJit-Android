package com.eurya.luajit.function;

import android.app.Activity;
import android.widget.Toast;
import com.eurya.luajit.LuaJitException;
import org.keplerproject.luajava.JavaFunction;
import org.keplerproject.luajava.LuaState;

public class Print extends JavaFunction {
    private Activity mActivity;
    private LuaState mState;

    public Print(Activity activity, LuaState state) throws LuaJitException{
        super(state);
        mActivity = activity;
        mState = state;
        try {
            register("print");
        } catch (LuaJitException e) {
            throw new LuaJitException("Cannot override print");
        }
    }

    @Override
    public int execute() throws LuaJitException {
        for (int i = 2; i <= L.getTop(); i++) {
            int type = L.type(i);
            String stype = L.typeName(type);
            String val = null;
            switch (stype){
                case "userdata":
                    Object obj = L.toJavaObject(i);
                    if (obj != null) val = obj.toString();
                    break;
                case "boolean":
                    val = L.toBoolean(i) ? "true" : "false";
                    break;
                default:
                    val = L.toString(i);
                    break;
            }
            if (val == null) val = stype;
            Toast.makeText(mActivity, val, Toast.LENGTH_SHORT).show();
        }
        return 0;
    }
    
}
