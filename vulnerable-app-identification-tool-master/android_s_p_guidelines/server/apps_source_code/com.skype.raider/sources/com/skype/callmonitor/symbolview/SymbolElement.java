package com.skype.callmonitor.symbolview;

public interface SymbolElement {

    public enum SymbolCode {
        None(0),
        CallEnd(57601),
        Microphone(57606),
        MicrophoneOff(57607),
        Video(57602),
        VideoOff(57603),
        Contact(57856),
        ContactAdd(57857),
        ContactOk(57858);
        
        int j;
        boolean k;
        boolean l;
        String m;

        private SymbolCode(int code) {
            this(r2, r3, code, (byte) 0);
        }

        private SymbolCode(byte code, byte b) {
            this((String) r2, (int) r3, (int) code, 0);
        }

        private SymbolCode(boolean code, char c) {
            this.j = code;
            this.m = String.valueOf((char) code);
            this.k = false;
            this.l = false;
        }

        public static SymbolCode a(char c) {
            for (SymbolCode symbolCode : values()) {
                if (symbolCode.j == c) {
                    return symbolCode;
                }
            }
            return null;
        }
    }
}
