package android.support.v7.widget;

import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParserException;

class ag extends Resources {
    private final Resources a;

    public ag(Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.a = resources;
    }

    public CharSequence getText(int id) throws NotFoundException {
        return this.a.getText(id);
    }

    public CharSequence getQuantityText(int id, int quantity) throws NotFoundException {
        return this.a.getQuantityText(id, quantity);
    }

    public String getString(int id) throws NotFoundException {
        return this.a.getString(id);
    }

    public String getString(int id, Object... formatArgs) throws NotFoundException {
        return this.a.getString(id, formatArgs);
    }

    public String getQuantityString(int id, int quantity, Object... formatArgs) throws NotFoundException {
        return this.a.getQuantityString(id, quantity, formatArgs);
    }

    public String getQuantityString(int id, int quantity) throws NotFoundException {
        return this.a.getQuantityString(id, quantity);
    }

    public CharSequence getText(int id, CharSequence def) {
        return this.a.getText(id, def);
    }

    public CharSequence[] getTextArray(int id) throws NotFoundException {
        return this.a.getTextArray(id);
    }

    public String[] getStringArray(int id) throws NotFoundException {
        return this.a.getStringArray(id);
    }

    public int[] getIntArray(int id) throws NotFoundException {
        return this.a.getIntArray(id);
    }

    public TypedArray obtainTypedArray(int id) throws NotFoundException {
        return this.a.obtainTypedArray(id);
    }

    public float getDimension(int id) throws NotFoundException {
        return this.a.getDimension(id);
    }

    public int getDimensionPixelOffset(int id) throws NotFoundException {
        return this.a.getDimensionPixelOffset(id);
    }

    public int getDimensionPixelSize(int id) throws NotFoundException {
        return this.a.getDimensionPixelSize(id);
    }

    public float getFraction(int id, int base, int pbase) {
        return this.a.getFraction(id, base, pbase);
    }

    public Drawable getDrawable(int id) throws NotFoundException {
        return this.a.getDrawable(id);
    }

    @RequiresApi(21)
    public Drawable getDrawable(int id, Theme theme) throws NotFoundException {
        return this.a.getDrawable(id, theme);
    }

    @RequiresApi(15)
    public Drawable getDrawableForDensity(int id, int density) throws NotFoundException {
        return this.a.getDrawableForDensity(id, density);
    }

    @RequiresApi(21)
    public Drawable getDrawableForDensity(int id, int density, Theme theme) {
        return this.a.getDrawableForDensity(id, density, theme);
    }

    public Movie getMovie(int id) throws NotFoundException {
        return this.a.getMovie(id);
    }

    public int getColor(int id) throws NotFoundException {
        return this.a.getColor(id);
    }

    public ColorStateList getColorStateList(int id) throws NotFoundException {
        return this.a.getColorStateList(id);
    }

    public boolean getBoolean(int id) throws NotFoundException {
        return this.a.getBoolean(id);
    }

    public int getInteger(int id) throws NotFoundException {
        return this.a.getInteger(id);
    }

    public XmlResourceParser getLayout(int id) throws NotFoundException {
        return this.a.getLayout(id);
    }

    public XmlResourceParser getAnimation(int id) throws NotFoundException {
        return this.a.getAnimation(id);
    }

    public XmlResourceParser getXml(int id) throws NotFoundException {
        return this.a.getXml(id);
    }

    public InputStream openRawResource(int id) throws NotFoundException {
        return this.a.openRawResource(id);
    }

    public InputStream openRawResource(int id, TypedValue value) throws NotFoundException {
        return this.a.openRawResource(id, value);
    }

    public AssetFileDescriptor openRawResourceFd(int id) throws NotFoundException {
        return this.a.openRawResourceFd(id);
    }

    public void getValue(int id, TypedValue outValue, boolean resolveRefs) throws NotFoundException {
        this.a.getValue(id, outValue, resolveRefs);
    }

    @RequiresApi(15)
    public void getValueForDensity(int id, int density, TypedValue outValue, boolean resolveRefs) throws NotFoundException {
        this.a.getValueForDensity(id, density, outValue, resolveRefs);
    }

    public void getValue(String name, TypedValue outValue, boolean resolveRefs) throws NotFoundException {
        this.a.getValue(name, outValue, resolveRefs);
    }

    public TypedArray obtainAttributes(AttributeSet set, int[] attrs) {
        return this.a.obtainAttributes(set, attrs);
    }

    public void updateConfiguration(Configuration config, DisplayMetrics metrics) {
        super.updateConfiguration(config, metrics);
        if (this.a != null) {
            this.a.updateConfiguration(config, metrics);
        }
    }

    public DisplayMetrics getDisplayMetrics() {
        return this.a.getDisplayMetrics();
    }

    public Configuration getConfiguration() {
        return this.a.getConfiguration();
    }

    public int getIdentifier(String name, String defType, String defPackage) {
        return this.a.getIdentifier(name, defType, defPackage);
    }

    public String getResourceName(int resid) throws NotFoundException {
        return this.a.getResourceName(resid);
    }

    public String getResourcePackageName(int resid) throws NotFoundException {
        return this.a.getResourcePackageName(resid);
    }

    public String getResourceTypeName(int resid) throws NotFoundException {
        return this.a.getResourceTypeName(resid);
    }

    public String getResourceEntryName(int resid) throws NotFoundException {
        return this.a.getResourceEntryName(resid);
    }

    public void parseBundleExtras(XmlResourceParser parser, Bundle outBundle) throws XmlPullParserException, IOException {
        this.a.parseBundleExtras(parser, outBundle);
    }

    public void parseBundleExtra(String tagName, AttributeSet attrs, Bundle outBundle) throws XmlPullParserException {
        this.a.parseBundleExtra(tagName, attrs, outBundle);
    }
}
