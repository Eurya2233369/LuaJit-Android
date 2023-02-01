package com.eurya.luajit;

public class LuaJitException extends Exception {
    
    public LuaJitException(String message){
        super(message);
    }
    
    public LuaJitException(Exception e){
        super((e.getCause() != null) ? e.getCause() : e);
    }
}
