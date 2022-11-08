package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Parcelable;

public interface z {
    boolean collapseItemActionView(l lVar, p pVar);

    boolean expandItemActionView(l lVar, p pVar);

    boolean flagActionItems();

    int getId();

    void initForMenu(Context context, l lVar);

    void onCloseMenu(l lVar, boolean z);

    void onRestoreInstanceState(Parcelable parcelable);

    Parcelable onSaveInstanceState();

    boolean onSubMenuSelected(ah ahVar);

    void setCallback(aa aaVar);

    void updateMenuView(boolean z);
}
