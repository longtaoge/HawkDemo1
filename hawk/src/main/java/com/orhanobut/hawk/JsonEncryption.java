package com.orhanobut.hawk;

/**
 * Created by longtaoge on 16/11/20.
 */

public class JsonEncryption implements Encryption {
    @Override
    public boolean init() {
        return true;
    }

    @Override
    public String encrypt(byte[] value) {
        if (value != null)
            return new String(value);
        else return null;
    }

    @Override
    public byte[] decrypt(String value) {

        if (value != null)
            return value.getBytes();
        else return new byte[0];
    }

    @Override
    public boolean reset() {
        return false;
    }
}
