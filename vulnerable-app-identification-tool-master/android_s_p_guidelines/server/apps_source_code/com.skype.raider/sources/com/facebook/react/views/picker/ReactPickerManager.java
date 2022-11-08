package com.facebook.react.views.picker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.c;
import javax.annotation.Nullable;

public abstract class ReactPickerManager extends SimpleViewManager<ReactPicker> {

    private static class a implements com.facebook.react.views.picker.ReactPicker.a {
        private final ReactPicker a;
        private final c b;

        public a(ReactPicker reactPicker, c eventDispatcher) {
            this.a = reactPicker;
            this.b = eventDispatcher;
        }

        public final void a(int position) {
            this.b.a(new com.facebook.react.views.picker.a.a(this.a.getId(), position));
        }
    }

    private static class b extends ArrayAdapter<am> {
        private final LayoutInflater a;
        @Nullable
        private Integer b;

        public b(Context context, am[] data) {
            super(context, 0, data);
            this.a = (LayoutInflater) com.facebook.infer.annotation.a.a(context.getSystemService("layout_inflater"));
        }

        public final View getView(int position, View convertView, ViewGroup parent) {
            return a(position, convertView, parent, false);
        }

        public final View getDropDownView(int position, View convertView, ViewGroup parent) {
            return a(position, convertView, parent, true);
        }

        private View a(int position, View convertView, ViewGroup parent, boolean isDropdown) {
            am item = (am) getItem(position);
            if (convertView == null) {
                convertView = this.a.inflate(isDropdown ? 17367049 : 17367048, parent, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(item.getString("label"));
            if (!isDropdown && this.b != null) {
                textView.setTextColor(this.b.intValue());
            } else if (item.hasKey("color") && !item.isNull("color")) {
                textView.setTextColor(item.getInt("color"));
            }
            return convertView;
        }

        public final void a(@Nullable Integer primaryTextColor) {
            this.b = primaryTextColor;
            notifyDataSetChanged();
        }
    }

    @ReactProp(name = "items")
    public void setItems(ReactPicker view, @Nullable al items) {
        if (items != null) {
            am[] data = new am[items.size()];
            for (int i = 0; i < items.size(); i++) {
                data[i] = items.getMap(i);
            }
            b adapter = new b(view.getContext(), data);
            adapter.a(view.b());
            view.setAdapter(adapter);
            return;
        }
        view.setAdapter(null);
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ReactPicker view, @Nullable Integer color) {
        view.setPrimaryColor(color);
        b adapter = (b) view.getAdapter();
        if (adapter != null) {
            adapter.a(color);
        }
    }

    @ReactProp(name = "prompt")
    public void setPrompt(ReactPicker view, @Nullable String prompt) {
        view.setPrompt(prompt);
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactPicker view, boolean enabled) {
        view.setEnabled(enabled);
    }

    @ReactProp(name = "selected")
    public void setSelected(ReactPicker view, int selected) {
        view.setStagedSelection(selected);
    }

    protected void onAfterUpdateTransaction(ReactPicker view) {
        super.onAfterUpdateTransaction(view);
        view.a();
    }

    protected void addEventEmitters(ae reactContext, ReactPicker picker) {
        picker.setOnSelectListener(new a(picker, ((UIManagerModule) reactContext.b(UIManagerModule.class)).getEventDispatcher()));
    }
}
