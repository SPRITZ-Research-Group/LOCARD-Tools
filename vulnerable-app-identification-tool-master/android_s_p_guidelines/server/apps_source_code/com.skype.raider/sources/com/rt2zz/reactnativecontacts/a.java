package com.rt2zz.reactnativecontacts;

import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.d;
import com.facebook.react.bridge.s;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public final class a {
    ContactsManager a;
    Integer b = Integer.valueOf(0);
    long c = 0;
    String d;
    boolean e = false;

    /* renamed from: com.rt2zz.reactnativecontacts.a$1 */
    class AnonymousClass1 extends TimerTask {
        final /* synthetic */ d a;
        final /* synthetic */ a b;

        AnonymousClass1(a this$0, d dVar) {
            this.b = this$0;
            this.a = dVar;
        }

        public final void run() {
            this.b.a.checkPermission(new d(this) {
                final /* synthetic */ AnonymousClass1 a;

                {
                    this.a = this$1;
                }

                public final void invoke(Object... args) {
                    boolean z = false;
                    if (args[1].equals("denied")) {
                        this.a.a.invoke(null, Boolean.valueOf(true));
                        return;
                    }
                    boolean testDelete = this.a.b.c();
                    boolean testAdd = this.a.b.d();
                    boolean testTimeout = this.a.b.b();
                    d dVar = this.a.a;
                    Object[] objArr = new Object[2];
                    objArr[0] = null;
                    if (testAdd && testDelete && testTimeout) {
                        z = true;
                    }
                    objArr[1] = Boolean.valueOf(z);
                    dVar.invoke(objArr);
                }
            });
        }
    }

    public a(ContactsManager contactsManager) {
        this.a = contactsManager;
    }

    private String a(String givenName, String familyName) {
        final Semaphore lock = new Semaphore(1);
        d callback = new d(this) {
            final /* synthetic */ a b;

            public final void invoke(Object... args) {
                if (args.length == 2) {
                    WritableNativeArray contacts = args[1];
                    Integer i = Integer.valueOf(0);
                    if (i.intValue() < contacts.size()) {
                        ReadableNativeMap map = contacts.getMap(i.intValue());
                        this.b.d = map.hasKey("recordId") ? map.getString("recordId") : null;
                    }
                    lock.release();
                    return;
                }
                throw new AssertionError("Invalid result");
            }
        };
        try {
            lock.acquire();
            this.a.getContactsMatchingString(givenName + " " + familyName, callback);
            if (lock.availablePermits() == 0) {
                lock.acquire();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.d;
    }

    private void b(String givenName, String familyName) {
        String contactRecordId = a(givenName, familyName);
        if (contactRecordId != null) {
            final Semaphore lock = new Semaphore(1);
            d callback = new d(this) {
                final /* synthetic */ a b;

                public final void invoke(Object... args) {
                    lock.release();
                }
            };
            try {
                lock.acquire();
                s record = new s();
                record.putString("recordId", contactRecordId);
                this.a.deleteContact(record, callback);
                if (lock.availablePermits() == 0) {
                    lock.acquire();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void c(String givenName, String familyName) {
        final Semaphore lock = new Semaphore(1);
        d callback = new d(this) {
            final /* synthetic */ a b;

            public final void invoke(Object... args) {
                lock.release();
            }
        };
        try {
            lock.acquire();
            s map = new s();
            map.putString("givenName", givenName);
            map.putString("familyName", familyName);
            this.a.addContact(map, callback);
            if (lock.availablePermits() == 0) {
                lock.acquire();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void a() {
        b("givenName1", "familyName1");
        b("givenName1", "familyName2");
        b("givenName1", "familyName3");
    }

    private boolean b() {
        final Semaphore lock = new Semaphore(1);
        d callback = new d(this) {
            final /* synthetic */ a b;

            public final void invoke(Object... args) {
                if (System.currentTimeMillis() - this.b.c < ((long) this.b.a.UpdateTimeoutMs)) {
                    System.out.println("ContactsManagerTests ERROR onContactsUpdateEvent fired before the expected timeout!");
                } else {
                    this.b.e = true;
                }
                lock.release();
                this.b.a();
                this.b.a.setTestContactsUpdateEventCallback(null);
            }
        };
        try {
            lock.acquire();
            this.a.setTestContactsUpdateEventCallback(callback);
            a();
            this.a.registerForContactChangeEvent();
            this.c = System.currentTimeMillis();
            c("givenName1", "familyName1");
            c("givenName1", "familyName2");
            c("givenName1", "familyName3");
            if (lock.availablePermits() == 0) {
                lock.acquire();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.e;
    }

    private boolean c() {
        c("givenName1", "familyName1");
        Integer countBeforeAdd = e();
        b("givenName1", "familyName1");
        if (e().intValue() - countBeforeAdd.intValue() == -1) {
            return true;
        }
        System.out.println("ContactsManagerTests ERROR Test Delete Contacts Failed!");
        return false;
    }

    private boolean d() {
        Integer countBeforeAdd = e();
        c("givenName1", "familyName1");
        if (e().intValue() - countBeforeAdd.intValue() == 1) {
            return true;
        }
        System.out.println("ContactsManagerTests ERROR Test Add Contacts Failed!");
        return false;
    }

    private Integer e() {
        final Semaphore lock = new Semaphore(1);
        d callback = new d(this) {
            final /* synthetic */ a b;

            public final void invoke(Object... args) {
                if (args.length == 2) {
                    this.b.b = (Integer) args[1];
                } else {
                    System.out.println("ContactsManagerTests ERROR getContactsCount changed implementation!");
                }
                lock.release();
            }
        };
        try {
            lock.acquire();
            this.a.getContactsCount(callback);
            if (lock.availablePermits() == 0) {
                lock.acquire();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.b;
    }
}
