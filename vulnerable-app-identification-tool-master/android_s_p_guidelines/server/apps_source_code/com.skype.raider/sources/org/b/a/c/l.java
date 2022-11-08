package org.b.a.c;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.b.a.d;
import org.b.a.e;
import org.b.a.f;

public class l implements e {
    protected static final Member a;
    protected static final Map<Class<?>, Map<String, Member>> b = new HashMap();

    static {
        Member invalidMember;
        try {
            invalidMember = l.class.getDeclaredField("a");
        } catch (NoSuchFieldException e) {
            invalidMember = null;
        } catch (SecurityException e2) {
            invalidMember = null;
        }
        a = invalidMember;
    }

    public final synchronized Object a(d interp, f self, Object o, Object property, String propertyName) throws r {
        Object a;
        if (o == null) {
            throw new NullPointerException("o");
        }
        Class<?> c = o.getClass();
        if (property == null) {
            a = a(c, propertyName, null);
        } else {
            Member member = a(c, propertyName);
            if (member != null) {
                try {
                    if (member instanceof Method) {
                        a = ((Method) member).invoke(o, new Object[0]);
                    } else if (member instanceof Field) {
                        a = ((Field) member).get(o);
                    }
                } catch (Exception e) {
                    a(c, propertyName, e);
                }
            }
            a = a(c, propertyName, null);
        }
        return a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static Member a(Class<?> clazz, String memberName) {
        if (clazz == null) {
            throw new NullPointerException("clazz");
        } else if (memberName == null) {
            throw new NullPointerException("memberName");
        } else {
            synchronized (b) {
                Member member;
                Map<String, Member> members = (Map) b.get(clazz);
                if (members != null) {
                    member = (Member) members.get(memberName);
                    if (member != null) {
                        Member member2;
                        if (member != a) {
                            member2 = member;
                        } else {
                            member2 = null;
                        }
                    }
                } else {
                    members = new HashMap();
                    b.put(clazz, members);
                }
                String methodSuffix = Character.toUpperCase(memberName.charAt(0)) + memberName.substring(1, memberName.length());
                member = b(clazz, "get" + methodSuffix);
                if (member == null) {
                    member = b(clazz, "is" + methodSuffix);
                    if (member == null) {
                        member = b(clazz, "has" + methodSuffix);
                    }
                }
                if (member == null) {
                    member = c(clazz, memberName);
                }
                members.put(memberName, member != null ? member : a);
                return member;
            }
        }
    }

    private static Method b(Class<?> clazz, String methodName) {
        try {
            Method method = clazz.getMethod(methodName, new Class[0]);
            if (method == null) {
                return method;
            }
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            return null;
        } catch (SecurityException e2) {
            return null;
        }
    }

    private static Field c(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getField(fieldName);
            if (field == null) {
                return field;
            }
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            return null;
        } catch (SecurityException e2) {
            return null;
        }
    }

    private static Object a(Class<?> clazz, String propertyName, Exception cause) {
        throw new r(cause, clazz.getName() + "." + propertyName);
    }
}
