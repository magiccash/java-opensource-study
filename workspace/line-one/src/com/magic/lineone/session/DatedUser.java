package com.magic.lineone.session;

import com.magic.lineone.util.ByteUtil;

public class DatedUser {

    private String username;
    private long   starttime;

    public DatedUser () {

    }

    public DatedUser (String username, long starttime) {

        this.username = username;
        this.starttime = starttime;
    }

    public String getUsername () {

        return username;
    }

    public void setUsername (String username) {

        this.username = username;
    }

    public long getStarttime () {

        return starttime;
    }

    public void setStarttime (long starttime) {

        this.starttime = starttime;
    }

    public byte [] toBytes () {

        return ByteUtil.merge (username.getBytes (), ByteUtil.longIntegerToBytes (starttime));
    }

    public void fromBytes (byte [] bytes) {

        if (bytes == null || bytes.length != 40)
            throw new IllegalArgumentException ("Input bytes should be 40-bytes long");
        username = new String (ByteUtil.subbytes (bytes, 0, 32));
        starttime = ByteUtil.bytesToLongInteger (ByteUtil.subbytes (bytes, 32, 40));
    }
}
