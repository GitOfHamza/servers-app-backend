package com.network.server._enum;

public enum OS {
    LINUX("LINUX") , MacOS("MACOS") , WINDOWS("WINDOWS") ;
    private final String os;

    OS(String os) {
        this.os = os;
    }

    public String getOS() {
        return  this.os;
    }
}
