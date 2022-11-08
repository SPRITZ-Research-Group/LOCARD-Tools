package com.projectseptember.RNGL;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.d;
import java.util.HashMap;
import java.util.Map;

public class RNGLContext extends ReactContextBaseJavaModule {
    private static String STATIC_VERT = "attribute vec2 position;varying vec2 uv;void main() {gl_Position = vec4(position,0.0,1.0);uv = vec2(0.5, 0.5) * (position+vec2(1.0, 1.0));}";
    private Map<Integer, f> fbos = new HashMap();
    private Map<Integer, d> onCompileCallbacks = new HashMap();
    private Map<Integer, k> shaders = new HashMap();

    public RNGLContext(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "RNGLContext";
    }

    public k getShader(Integer id) {
        return (k) this.shaders.get(id);
    }

    @ReactMethod
    public void addShader(Integer id, am config, d onCompile) {
        String frag = config.getString("frag");
        this.shaders.put(id, new k(config.getString("name"), STATIC_VERT, frag));
        if (onCompile != null) {
            this.onCompileCallbacks.put(id, onCompile);
        }
    }

    @ReactMethod
    public void removeShader(Integer id) {
        if (((k) this.shaders.remove(id)) == null) {
            throw new Error("removeShader(" + id + "): shader does not exist");
        }
    }

    public void shaderFailedToCompile(Integer id, j e) {
        d onCompile = (d) this.onCompileCallbacks.get(id);
        if (onCompile == null) {
            e.getMessage();
            return;
        }
        onCompile.invoke(e.b);
    }

    public void shaderSucceedToCompile(Integer id, Map<String, Integer> uniformTypes) {
        d onCompile = (d) this.onCompileCallbacks.get(id);
        this.onCompileCallbacks.remove(id);
        if (onCompile != null) {
            ar res = new WritableNativeMap();
            ar uniforms = new WritableNativeMap();
            for (String key : uniformTypes.keySet()) {
                uniforms.putString(key, glTypeString(((Integer) uniformTypes.get(key)).intValue()));
            }
            res.putMap("uniforms", uniforms);
            onCompile.invoke(null, res);
        }
    }

    static String glTypeString(int type) {
        switch (type) {
            case 5124:
                return "int";
            case 5126:
                return "float";
            case 35664:
                return "vec2";
            case 35665:
                return "vec3";
            case 35666:
                return "vec4";
            case 35667:
                return "ivec2";
            case 35668:
                return "ivec3";
            case 35669:
                return "ivec4";
            case 35670:
                return "bool";
            case 35671:
                return "bvec2";
            case 35672:
                return "bvec3";
            case 35673:
                return "bvec4";
            case 35674:
                return "mat2";
            case 35675:
                return "mat3";
            case 35676:
                return "mat4";
            case 35678:
                return "sampler2D";
            case 35680:
                return "samplerCube";
            default:
                return "";
        }
    }
}
