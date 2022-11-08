package okhttp3;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

public final class MediaType {
    private static final Pattern PARAMETER = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
    private static final String QUOTED = "\"([^\"]*)\"";
    private static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
    private static final Pattern TYPE_SUBTYPE = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    @Nullable
    private final String charset;
    private final String mediaType;
    private final String subtype;
    private final String type;

    private MediaType(String mediaType, String type, String subtype, @Nullable String charset) {
        this.mediaType = mediaType;
        this.type = type;
        this.subtype = subtype;
        this.charset = charset;
    }

    public static MediaType get(String string) {
        Matcher typeSubtype = TYPE_SUBTYPE.matcher(string);
        if (typeSubtype.lookingAt()) {
            String type = typeSubtype.group(1).toLowerCase(Locale.US);
            String subtype = typeSubtype.group(2).toLowerCase(Locale.US);
            String charset = null;
            Matcher parameter = PARAMETER.matcher(string);
            int s = typeSubtype.end();
            while (s < string.length()) {
                parameter.region(s, string.length());
                if (parameter.lookingAt()) {
                    String name = parameter.group(1);
                    if (name != null && name.equalsIgnoreCase("charset")) {
                        String charsetParameter;
                        String token = parameter.group(2);
                        if (token == null) {
                            charsetParameter = parameter.group(3);
                        } else if (token.startsWith("'") && token.endsWith("'") && token.length() > 2) {
                            charsetParameter = token.substring(1, token.length() - 1);
                        } else {
                            charsetParameter = token;
                        }
                        if (charset == null || charsetParameter.equalsIgnoreCase(charset)) {
                            charset = charsetParameter;
                        } else {
                            throw new IllegalArgumentException("Multiple charsets defined: \"" + charset + "\" and: \"" + charsetParameter + "\" for: \"" + string + '\"');
                        }
                    }
                    s = parameter.end();
                } else {
                    throw new IllegalArgumentException("Parameter is not formatted correctly: \"" + string.substring(s) + "\" for: \"" + string + '\"');
                }
            }
            return new MediaType(string, type, subtype, charset);
        }
        throw new IllegalArgumentException("No subtype found for: \"" + string + '\"');
    }

    @Nullable
    public static MediaType parse(String string) {
        try {
            return get(string);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public final String type() {
        return this.type;
    }

    public final String subtype() {
        return this.subtype;
    }

    @Nullable
    public final Charset charset() {
        return charset(null);
    }

    @Nullable
    public final Charset charset(@Nullable Charset defaultValue) {
        try {
            if (this.charset != null) {
                return Charset.forName(this.charset);
            }
            return defaultValue;
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }

    public final String toString() {
        return this.mediaType;
    }

    public final boolean equals(@Nullable Object other) {
        return (other instanceof MediaType) && ((MediaType) other).mediaType.equals(this.mediaType);
    }

    public final int hashCode() {
        return this.mediaType.hashCode();
    }
}
