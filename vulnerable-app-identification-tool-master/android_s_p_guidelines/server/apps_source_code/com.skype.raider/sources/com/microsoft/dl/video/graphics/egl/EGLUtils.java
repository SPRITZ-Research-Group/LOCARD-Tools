package com.microsoft.dl.video.graphics.egl;

import android.util.SparseIntArray;

final class EGLUtils {
    private static final ValueParser a = new EnumValueParser(RenderBufferAttribute.values());
    private static final ValueParser b = new EnumValueParser(BooleanAttribute.values());
    private static final ValueParser c = new ValueParser() {
        public final String parse(int value) {
            if (value == -1) {
                return "DONT_CARE";
            }
            return Integer.toString(value);
        }
    };

    private interface ValueParser {
        String parse(int i);
    }

    private static abstract class AbstractAttributesBuilder {
        private final SparseIntArray a;

        private AbstractAttributesBuilder() {
            this.a = new SparseIntArray();
        }

        /* synthetic */ AbstractAttributesBuilder(byte b) {
            this();
        }

        protected final void a(Attribute attr, int value) {
            this.a.put(attr.getCode(), value);
        }

        protected final void a(Attribute attr, Attribute value) {
            a(attr, value.getCode());
        }

        protected final void a(Attribute attr, Attribute[] values) {
            int bitmask = 0;
            for (Attribute value : values) {
                bitmask |= value.getCode();
            }
            a(attr, bitmask);
        }

        public int[] build() {
            int[] arr = new int[((this.a.size() * 2) + 1)];
            for (int i = 0; i < this.a.size(); i++) {
                arr[i * 2] = this.a.keyAt(i);
                arr[(i * 2) + 1] = this.a.valueAt(i);
            }
            arr[arr.length - 1] = 12344;
            return arr;
        }
    }

    public interface Attribute {
        int getCode();
    }

    private static class BitmapValueParser<T extends Attribute> implements ValueParser {
        private final T[] a;

        public BitmapValueParser(T[] attributes) {
            this.a = attributes;
        }

        public String parse(int value) {
            StringBuilder str = new StringBuilder();
            for (T attr : this.a) {
                if ((attr.getCode() & value) > 0) {
                    if (str.length() > 0) {
                        str.append('|');
                    }
                    str.append(attr.toString());
                    value &= attr.getCode() ^ -1;
                }
            }
            if (value > 0) {
                if (str.length() > 0) {
                    str.append('|');
                }
                str.append("0x").append(Integer.toHexString(value));
            }
            return str.toString();
        }
    }

    public enum BooleanAttribute implements Attribute {
        FALSE(0),
        TRUE(1),
        DONT_CARE(-1);
        
        private final int a;

        private BooleanAttribute(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    public enum ClientContextTypeAttribute implements Attribute {
        OPENGL(12450),
        OPENGL_ES(12448),
        OPENVG(12449);
        
        private final int a;

        private ClientContextTypeAttribute(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    public enum ColorBufferTypeAttribute implements Attribute {
        RGB(12430),
        LUMINANCE(12431);
        
        private final int a;

        private ColorBufferTypeAttribute(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    public enum ConfigAttribute implements Attribute {
        ALPHA_SIZE(12321, EGLUtils.c),
        ALPHA_MASK_SIZE(12350, EGLUtils.c),
        BIND_TO_TEXTURE_RGB(12345, EGLUtils.b),
        BIND_TO_TEXTURE_RGBA(12346, EGLUtils.b),
        BLUE_SIZE(12322, EGLUtils.c),
        BUFFER_SIZE(12320, EGLUtils.c),
        COLOR_BUFFER_TYPE(12351, new EnumValueParser(ColorBufferTypeAttribute.values())),
        CONFIG_CAVEAT(12327, new EnumValueParser(ConfigCaveatAttribute.values())),
        CONFIG_ID(12328, EGLUtils.c),
        CONFORMANT(12354, new BitmapValueParser(ConformantAttribute.values())),
        DEPTH_SIZE(12325, EGLUtils.c),
        GREEN_SIZE(12323, EGLUtils.c),
        LEVEL(12329, EGLUtils.c),
        LUMINANCE_SIZE(12349, EGLUtils.c),
        MATCH_NATIVE_PIXMAP(12353, EGLUtils.b),
        MAX_PBUFFER_WIDTH(12332, EGLUtils.c),
        MAX_PBUFFER_HEIGHT(12330, EGLUtils.c),
        MAX_PBUFFER_PIXELS(12331, EGLUtils.c),
        MAX_SWAP_INTERVAL(12348, EGLUtils.c),
        MIN_SWAP_INTERVAL(12347, EGLUtils.c),
        NATIVE_RENDERABLE(12333, EGLUtils.b),
        NATIVE_VISUAL_ID(12334, EGLUtils.c),
        NATIVE_VISUAL_TYPE(12335, EGLUtils.c),
        RED_SIZE(12324, EGLUtils.c),
        RENDERABLE_TYPE(12352, new BitmapValueParser(RenderableTypeAttribute.values())),
        SAMPLE_BUFFERS(12338, EGLUtils.c),
        SAMPLES(12337, EGLUtils.c),
        STENCIL_SIZE(12326, EGLUtils.c),
        SURFACE_TYPE(12339, new BitmapValueParser(SurfaceTypeAttribute.values())),
        TRANSPARENT_RED_VALUE(12343, EGLUtils.c),
        TRANSPARENT_GREEN_VALUE(12342, EGLUtils.c),
        TRANSPARENT_BLUE_VALUE(12341, EGLUtils.c),
        TRANSPARENT_TYPE(12340, new EnumValueParser(TransparentTypeAttribute.values()));
        
        private final int a;
        private final ValueParser b;

        private ConfigAttribute(int code, ValueParser valueParser) {
            this.a = code;
            this.b = valueParser;
        }

        public final int getCode() {
            return this.a;
        }

        public final String parseValue(int value) {
            return this.b.parse(value);
        }
    }

    public static class ConfigAttributesBuilder extends AbstractAttributesBuilder {
        public ConfigAttributesBuilder() {
            super();
        }

        public /* bridge */ /* synthetic */ int[] build() {
            return super.build();
        }

        public ConfigAttributesBuilder alphaMaskSize(int value) {
            a(ConfigAttribute.ALPHA_MASK_SIZE, value);
            return this;
        }

        public ConfigAttributesBuilder alphaSize(int value) {
            a(ConfigAttribute.ALPHA_SIZE, value);
            return this;
        }

        public ConfigAttributesBuilder bindToTextureRgb(BooleanAttribute value) {
            a(ConfigAttribute.BIND_TO_TEXTURE_RGB, (Attribute) value);
            return this;
        }

        public ConfigAttributesBuilder bindToTextureRgba(BooleanAttribute value) {
            a(ConfigAttribute.BIND_TO_TEXTURE_RGBA, (Attribute) value);
            return this;
        }

        public ConfigAttributesBuilder blueSize(int value) {
            a(ConfigAttribute.BLUE_SIZE, value);
            return this;
        }

        public ConfigAttributesBuilder bufferSize(int value) {
            a(ConfigAttribute.BUFFER_SIZE, value);
            return this;
        }

        public ConfigAttributesBuilder colorBufferType(ColorBufferTypeAttribute value) {
            a(ConfigAttribute.COLOR_BUFFER_TYPE, (Attribute) value);
            return this;
        }

        public ConfigAttributesBuilder configCaveat(ConfigCaveatAttribute value) {
            a(ConfigAttribute.CONFIG_CAVEAT, (Attribute) value);
            return this;
        }

        public ConfigAttributesBuilder configId(int value) {
            a(ConfigAttribute.CONFIG_ID, value);
            return this;
        }

        public ConfigAttributesBuilder conformant(ConformantAttribute value) {
            a(ConfigAttribute.CONFORMANT, (Attribute) value);
            return this;
        }

        public ConfigAttributesBuilder depthSize(int value) {
            a(ConfigAttribute.DEPTH_SIZE, value);
            return this;
        }

        public ConfigAttributesBuilder greenSize(int value) {
            a(ConfigAttribute.GREEN_SIZE, value);
            return this;
        }

        public ConfigAttributesBuilder level(int value) {
            a(ConfigAttribute.LEVEL, value);
            return this;
        }

        public ConfigAttributesBuilder luminanceSize(int value) {
            a(ConfigAttribute.LUMINANCE_SIZE, value);
            return this;
        }

        public ConfigAttributesBuilder maxPBufferHeight(BooleanAttribute value) {
            a(ConfigAttribute.MATCH_NATIVE_PIXMAP, (Attribute) value);
            return this;
        }

        public ConfigAttributesBuilder maxPBufferHeight(int value) {
            a(ConfigAttribute.MAX_PBUFFER_HEIGHT, value);
            return this;
        }

        public ConfigAttributesBuilder maxPBufferPixels(int value) {
            a(ConfigAttribute.MAX_PBUFFER_PIXELS, value);
            return this;
        }

        public ConfigAttributesBuilder maxPBufferWidth(int value) {
            a(ConfigAttribute.MAX_PBUFFER_WIDTH, value);
            return this;
        }

        public ConfigAttributesBuilder maxSwapInterval(int value) {
            a(ConfigAttribute.MAX_SWAP_INTERVAL, value);
            return this;
        }

        public ConfigAttributesBuilder minSwapInterval(int value) {
            a(ConfigAttribute.MIN_SWAP_INTERVAL, value);
            return this;
        }

        public ConfigAttributesBuilder nativeRenderable(BooleanAttribute value) {
            a(ConfigAttribute.NATIVE_RENDERABLE, (Attribute) value);
            return this;
        }

        public ConfigAttributesBuilder redSize(int value) {
            a(ConfigAttribute.RED_SIZE, value);
            return this;
        }

        public ConfigAttributesBuilder renderableType(RenderableTypeAttribute... values) {
            a(ConfigAttribute.RENDERABLE_TYPE, (Attribute[]) values);
            return this;
        }

        public ConfigAttributesBuilder sampleBuffers(int value) {
            a(ConfigAttribute.SAMPLE_BUFFERS, value);
            return this;
        }

        public ConfigAttributesBuilder samples(int value) {
            a(ConfigAttribute.SAMPLES, value);
            return this;
        }

        public ConfigAttributesBuilder stencilSize(int value) {
            a(ConfigAttribute.STENCIL_SIZE, value);
            return this;
        }

        public ConfigAttributesBuilder surfaceType(SurfaceTypeAttribute... values) {
            a(ConfigAttribute.SURFACE_TYPE, (Attribute[]) values);
            return this;
        }

        public ConfigAttributesBuilder transparentBlueValue(int value) {
            a(ConfigAttribute.TRANSPARENT_BLUE_VALUE, value);
            return this;
        }

        public ConfigAttributesBuilder transparentGreenValue(int value) {
            a(ConfigAttribute.TRANSPARENT_GREEN_VALUE, value);
            return this;
        }

        public ConfigAttributesBuilder transparentredValue(int value) {
            a(ConfigAttribute.TRANSPARENT_RED_VALUE, value);
            return this;
        }

        public ConfigAttributesBuilder transparentType(TransparentTypeAttribute value) {
            a(ConfigAttribute.TRANSPARENT_TYPE, (Attribute) value);
            return this;
        }
    }

    public enum ConfigCaveatAttribute implements Attribute {
        NONE(12344),
        DONT_CARE(-1),
        SLOW_CONFIG(12368),
        NON_CONFORMANT_CONFIG(12369);
        
        private final int a;

        private ConfigCaveatAttribute(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    public enum ConformantAttribute implements Attribute {
        OPENGL(8),
        OPENGL_ES(1),
        OPENGL_ES2(4),
        OPENVG(2);
        
        private final int a;

        private ConformantAttribute(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    public enum ContextAttribute implements Attribute {
        CONFIG_ID(12328, EGLUtils.c),
        CONTEXT_CLIENT_TYPE(12439, new EnumValueParser(ClientContextTypeAttribute.values())),
        CONTEXT_CLIENT_VERSION(12440, EGLUtils.c),
        RENDER_BUFFER(12422, EGLUtils.a);
        
        private final int a;
        private final ValueParser b;

        private ContextAttribute(int code, ValueParser valueParser) {
            this.a = code;
            this.b = valueParser;
        }

        public final int getCode() {
            return this.a;
        }

        public final String parseValue(int value) {
            return this.b.parse(value);
        }
    }

    public static class ContextAttributesBuilder extends AbstractAttributesBuilder {
        public ContextAttributesBuilder() {
            super();
        }

        public /* bridge */ /* synthetic */ int[] build() {
            return super.build();
        }

        public ContextAttributesBuilder contextClientVersion(int value) {
            a(ContextAttribute.CONTEXT_CLIENT_VERSION, value);
            return this;
        }
    }

    public enum DisplayAttribute implements Attribute {
        VENDOR(12371),
        VERSION(12372),
        EXTENSIONS(12373);
        
        private final int a;

        private DisplayAttribute(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    private static class EnumValueParser<T extends Attribute> implements ValueParser {
        private final T[] a;

        public EnumValueParser(T[] attributes) {
            this.a = attributes;
        }

        public String parse(int value) {
            for (T attr : this.a) {
                if (value == attr.getCode()) {
                    return attr.toString();
                }
            }
            return "0x" + Integer.toHexString(value);
        }
    }

    public enum MultisampleResolveAttribute implements Attribute {
        MULTISAMPLE_RESOLVE_DEFAULT(12442),
        MULTISAMPLE_RESOLVE_BOX(12443);
        
        private final int a;

        private MultisampleResolveAttribute(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    public static class PBufferSurfaceAttributeBuilder extends AbstractAttributesBuilder {
        public PBufferSurfaceAttributeBuilder() {
            super();
        }

        public /* bridge */ /* synthetic */ int[] build() {
            return super.build();
        }

        public PBufferSurfaceAttributeBuilder height(int value) {
            a(SurfaceAttribute.HEIGHT, value);
            return this;
        }

        public PBufferSurfaceAttributeBuilder largestPbuffer(BooleanAttribute value) {
            a(SurfaceAttribute.LARGEST_PBUFFER, (Attribute) value);
            return this;
        }

        public PBufferSurfaceAttributeBuilder mipmapTexture(BooleanAttribute value) {
            a(SurfaceAttribute.MIPMAP_TEXTURE, (Attribute) value);
            return this;
        }

        public PBufferSurfaceAttributeBuilder testureFormat(TextureFormatAttribute value) {
            a(SurfaceAttribute.TEXTURE_FORMAT, (Attribute) value);
            return this;
        }

        public PBufferSurfaceAttributeBuilder textureTarget(TextureTargetAttribute value) {
            a(SurfaceAttribute.TEXTURE_TARGET, (Attribute) value);
            return this;
        }

        public PBufferSurfaceAttributeBuilder vgAlphaFormat(VgAlphaFormatAttribute value) {
            a(SurfaceAttribute.VG_ALPHA_FORMAT, (Attribute) value);
            return this;
        }

        public PBufferSurfaceAttributeBuilder width(int value) {
            a(SurfaceAttribute.WIDTH, value);
            return this;
        }
    }

    public enum RenderBufferAttribute implements Attribute {
        NONE(12344),
        SINGLE_BUFFER(12421),
        BACK_BUFFER(12420);
        
        private final int a;

        private RenderBufferAttribute(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    public enum RenderableTypeAttribute implements Attribute {
        OPENGL_ES(1),
        OPENVG(2),
        OPENGL_ES2(4),
        OPENGL(8);
        
        private final int a;

        private RenderableTypeAttribute(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    public enum SurfaceAttribute implements Attribute {
        CONFIG_ID(12328, EGLUtils.c),
        HEIGHT(12374, EGLUtils.c),
        HORIZONTAL_RESOLUTION(12432, EGLUtils.c),
        LARGEST_PBUFFER(12376, EGLUtils.b),
        MIPMAP_LEVEL(12419, EGLUtils.c),
        MIPMAP_TEXTURE(12418, EGLUtils.b),
        MULTISAMPLE_RESOLVE(12441, new EnumValueParser(MultisampleResolveAttribute.values())),
        PIXEL_ASPECT_RATIO(12434, EGLUtils.c),
        RENDER_BUFFER(12422, EGLUtils.a),
        SWAP_BEHAVIOR(12435, new EnumValueParser(SwapBehaviorAttribute.values())),
        TEXTURE_FORMAT(12416, new EnumValueParser(TextureFormatAttribute.values())),
        TEXTURE_TARGET(12417, new EnumValueParser(TextureTargetAttribute.values())),
        VERTICAL_RESOLUTION(12433, EGLUtils.c),
        VG_ALPHA_FORMAT(12424, new EnumValueParser(VgAlphaFormatAttribute.values())),
        VG_COLORSPACE(12423, new EnumValueParser(VgColorspaceAttribute.values())),
        WIDTH(12375, EGLUtils.c);
        
        private final int a;
        private final ValueParser b;

        private SurfaceAttribute(int code, ValueParser valueParser) {
            this.a = code;
            this.b = valueParser;
        }

        public final int getCode() {
            return this.a;
        }

        public final String parseValue(int value) {
            return this.b.parse(value);
        }
    }

    public enum SurfaceTypeAttribute implements Attribute {
        PBUFFER(1),
        PIXMAP(2),
        WINDOW(4),
        VG_COLORSPACE_LINEAR(32),
        VG_ALPHA_FORMAT_PRE(64),
        MULTISAMPLE_RESOLVE_BOX(512),
        SWAP_BEHAVIOR_PRESERVED(1024);
        
        private final int a;

        private SurfaceTypeAttribute(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    public enum SwapBehaviorAttribute implements Attribute {
        BUFFER_PRESERVED(12436),
        BUFFER_DESTROYED(12437);
        
        private final int a;

        private SwapBehaviorAttribute(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    public enum TextureFormatAttribute implements Attribute {
        NO_TEXTURE(12380),
        TEXTURE_2D(12383);
        
        private final int a;

        private TextureFormatAttribute(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    public enum TextureTargetAttribute implements Attribute {
        NO_TEXTURE(12380),
        TEXTURE_RGB(12381),
        TEXTURE_RGBA(12382);
        
        private final int a;

        private TextureTargetAttribute(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    public enum TransparentTypeAttribute implements Attribute {
        NONE(12344),
        RGB(12370);
        
        private final int a;

        private TransparentTypeAttribute(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    public enum VgAlphaFormatAttribute implements Attribute {
        VG_ALPHA_FORMAT_NONPRE(12427),
        VG_ALPHA_FORMAT_PRE(12428);
        
        private final int a;

        private VgAlphaFormatAttribute(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    public enum VgColorspaceAttribute implements Attribute {
        VG_COLORSPACE_sRGB(12425),
        VG_COLORSPACE_LINEAR(12426);
        
        private final int a;

        private VgColorspaceAttribute(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    public static class WindowSurfaceAttributeBuilder extends AbstractAttributesBuilder {
        public WindowSurfaceAttributeBuilder() {
            super();
        }

        public /* bridge */ /* synthetic */ int[] build() {
            return super.build();
        }

        public WindowSurfaceAttributeBuilder renderBuffer(RenderBufferAttribute value) {
            a(SurfaceAttribute.RENDER_BUFFER, (Attribute) value);
            return this;
        }

        public WindowSurfaceAttributeBuilder vgAlphaFormat(VgAlphaFormatAttribute value) {
            a(SurfaceAttribute.VG_ALPHA_FORMAT, (Attribute) value);
            return this;
        }

        public WindowSurfaceAttributeBuilder vgColorspace(VgColorspaceAttribute value) {
            a(SurfaceAttribute.VG_COLORSPACE, (Attribute) value);
            return this;
        }
    }

    private EGLUtils() {
    }
}
