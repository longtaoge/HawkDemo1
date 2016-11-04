package com.orhanobut.hawk;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class DataHelper {

    private static final char DELIMITER = '@';
    private static final String INFO_DELIMITER = "#";
    private static final char NEW_VERSION = 'V';

    private static final Map<Character, DataType> TYPE_MAP = new HashMap<>();

    static {
        TYPE_MAP.put(DataType.OBJECT.getType(), DataType.OBJECT);
        TYPE_MAP.put(DataType.LIST.getType(), DataType.LIST);
        TYPE_MAP.put(DataType.MAP.getType(), DataType.MAP);
        TYPE_MAP.put(DataType.SET.getType(), DataType.SET);
    }

    private DataHelper() {
        // no instance
    }

    /**
     * Saved data contains more info than cipher text, this method will demarshall the data
     *
     * @param storedText is the saved data
     * @return the DataInfo object which contains all necessary information
     */
    static DataInfo getDataInfo(String storedText) {
        HawkUtils.checkNullOrEmpty("Text", storedText);

        int index = storedText.indexOf(DELIMITER);
        if (index == -1) {
            throw new IllegalArgumentException("Text should contain delimiter");
        }

        String text = storedText.substring(0, index);
        String cipherText = storedText.substring(index + 1);
        if (TextUtils.isEmpty(text) || TextUtils.isEmpty(cipherText)) {
            throw new IllegalArgumentException("Invalid stored text");
        }
        char firstChar = text.charAt(text.length() - 1);
        if (firstChar == NEW_VERSION) {
            return getNewDataInfo(text, cipherText);
        } else {
            // old data is no longer supported
            throw new IllegalStateException("storedText is not valid");
        }
    }

    static DataInfo getNewDataInfo(String text, String cipherText) {
        //first char defines whether it is new version or not

        String[] infos = text.split(INFO_DELIMITER);

        char type = infos[2].charAt(0);
        DataType dataType = TYPE_MAP.get(type);

        // if it is collection, no need to create the class object
        Class<?> keyClazz = null;
        if (!TextUtils.isEmpty(infos[0])) {
            try {
                keyClazz = Class.forName(infos[0]);
            } catch (ClassNotFoundException e) {
                Logger.d(e.getMessage());
            }
        }

        Class<?> valueClazz = null;
        if (!TextUtils.isEmpty(infos[1])) {
            try {
                valueClazz = Class.forName(infos[1]);
            } catch (ClassNotFoundException e) {
                Logger.d(e.getMessage());
            }
        }

        return new DataInfo(dataType, cipherText, keyClazz, valueClazz);
    }

    //TODO optimize
    static <T> String addType(String cipherText, T t) {
        HawkUtils.checkNullOrEmpty("Cipher text", cipherText);
        HawkUtils.checkNull("Value", t);

        String keyClassName = "";
        String valueClassName = "";
        DataType dataType;
        if (List.class.isAssignableFrom(t.getClass())) {
            List<?> list = (List<?>) t;
            if (!list.isEmpty()) {
                keyClassName = list.get(0).getClass().getName();
            }
            dataType = DataType.LIST;
        } else if (Map.class.isAssignableFrom(t.getClass())) {
            dataType = DataType.MAP;
            Map<?, ?> map = (Map) t;
            if (!map.isEmpty()) {
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    keyClassName = entry.getKey().getClass().getName();
                    valueClassName = entry.getValue().getClass().getName();
                    break;
                }
            }
        } else if (Set.class.isAssignableFrom(t.getClass())) {
            Set<?> set = (Set<?>) t;
            if (!set.isEmpty()) {
                Iterator<?> iterator = set.iterator();
                if (iterator.hasNext()) {
                    keyClassName = iterator.next().getClass().getName();
                }
            }
            dataType = DataType.SET;
        } else {
            dataType = DataType.OBJECT;
            keyClassName = t.getClass().getName();
        }

        return keyClassName + INFO_DELIMITER +
                valueClassName + INFO_DELIMITER +
                dataType.getType() + NEW_VERSION + DELIMITER +
                cipherText;
    }

}
