package com.magic.lineone.session;

import java.util.HashMap;
import java.util.Map;

import com.magic.lineone.util.HexUtil;

public class LocalSessionStore implements ISessionStore {

    private final Map <String, DatedUser>  sidMap   = new HashMap <String, DatedUser> ();

    private final static LocalSessionStore instance = new LocalSessionStore ();

    private LocalSessionStore () {

    }

    public static LocalSessionStore getInstance () {

        return instance;
    }

    @Override
    public String newSession (String username) {

        DatedUser datedUserId = new DatedUser (username, System.currentTimeMillis ());
        do {
            final String sessionId = HexUtil.getHexStringRandomly (32);
            if (containsSessionId (sessionId))
                continue;
            sidMap.put (sessionId, datedUserId);
            return sessionId;
        } while (true);
    }

    @Override
    public SessionState getSessionState (String username, String sid, long experied_time) {

        if (username == null || sid == null || sid.length () != 32)
            return SessionState.ILLEGAL;
        DatedUser datedUser = sidMap.get (sid);

        if (datedUser == null) {
            return SessionState.ILLEGAL;
        }

        if (!username.equals (datedUser.getUsername ()))
            return SessionState.ILLEGAL;
        if (System.currentTimeMillis () - datedUser.getStarttime () > experied_time) {
            sidMap.remove (sid);
            return SessionState.EXPIRED;
        }
        return SessionState.LEGAL;
    }

    @Override
    public SessionState removeSession (String username, String sid) {

        DatedUser datedUserId = sidMap.get (sid);

        if (!username.equals (datedUserId.getUsername ())) {
            return SessionState.ILLEGAL;
        }
        sidMap.remove (sid);
        return SessionState.LEGAL;
    }

    private boolean containsSessionId (String sid) {

        return sidMap.containsKey (sid);
    }
}
