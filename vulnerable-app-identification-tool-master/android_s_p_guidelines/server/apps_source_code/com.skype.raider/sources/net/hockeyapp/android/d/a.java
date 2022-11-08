package net.hockeyapp.android.d;

import java.io.Serializable;
import java.util.ArrayList;

public final class a implements Serializable {
    private String a;
    private String b;
    private int c;
    private String d;
    private ArrayList<c> e;

    public final void a(String name) {
        this.a = name;
    }

    public final void b(String email) {
        this.b = email;
    }

    public final void a(int id) {
        this.c = id;
    }

    public final void c(String createdAt) {
        this.d = createdAt;
    }

    public final ArrayList<c> a() {
        return this.e;
    }

    public final void a(ArrayList<c> messages) {
        this.e = messages;
    }
}
