package com.projectseptember.RNGL;

public final class j extends RuntimeException {
    public final String a;
    public final String b;

    public j(String shaderName, String compileError) {
        super("Shader '" + shaderName + "': " + compileError);
        this.b = compileError;
        this.a = shaderName;
    }
}
