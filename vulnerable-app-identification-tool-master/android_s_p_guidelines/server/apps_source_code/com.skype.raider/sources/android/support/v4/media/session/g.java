package android.support.v4.media.session;

import android.media.session.PlaybackState.Builder;
import android.media.session.PlaybackState.CustomAction;
import android.support.annotation.RequiresApi;
import java.util.Iterator;
import java.util.List;

@RequiresApi(21)
final class g {
    public static Object a(int state, long position, long bufferedPosition, float speed, long actions, CharSequence errorMessage, long updateTime, List<Object> customActions, long activeItemId) {
        Builder stateObj = new Builder();
        stateObj.setState(state, position, speed, updateTime);
        stateObj.setBufferedPosition(bufferedPosition);
        stateObj.setActions(actions);
        stateObj.setErrorMessage(errorMessage);
        Iterator it = customActions.iterator();
        while (it.hasNext()) {
            stateObj.addCustomAction((CustomAction) it.next());
        }
        stateObj.setActiveQueueItemId(activeItemId);
        return stateObj.build();
    }
}
