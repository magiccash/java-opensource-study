package com.magic.lineone.session;

public interface ISessionStore {

    public static enum SessionState {
        LEGAL, ILLEGAL, EXPIRED
    };

    public String newSession (String id);

    public SessionState getSessionState (String username, String sid, long experied_time);

    public SessionState removeSession (String username, String sid);

}
