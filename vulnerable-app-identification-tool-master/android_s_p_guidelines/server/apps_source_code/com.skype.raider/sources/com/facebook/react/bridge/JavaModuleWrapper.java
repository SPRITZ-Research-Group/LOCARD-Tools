package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.animated.NativeAnimatedModule;
import com.facebook.react.devsupport.JSCHeapCapture;
import com.facebook.react.devsupport.JSCSamplingProfiler;
import com.facebook.react.modules.accessibilityinfo.AccessibilityInfoModule;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.modules.camera.CameraRollManager;
import com.facebook.react.modules.camera.ImageEditingManager;
import com.facebook.react.modules.camera.ImageStoreManager;
import com.facebook.react.modules.clipboard.ClipboardModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ExceptionsManagerModule;
import com.facebook.react.modules.core.HeadlessJsTaskSupportModule;
import com.facebook.react.modules.core.Timing;
import com.facebook.react.modules.datepicker.DatePickerDialogModule;
import com.facebook.react.modules.debug.AnimationsDebugModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.modules.i18nmanager.I18nManagerModule;
import com.facebook.react.modules.image.ImageLoaderModule;
import com.facebook.react.modules.intent.IntentModule;
import com.facebook.react.modules.location.LocationModule;
import com.facebook.react.modules.netinfo.NetInfoModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.modules.permissions.PermissionsModule;
import com.facebook.react.modules.share.ShareModule;
import com.facebook.react.modules.statusbar.StatusBarModule;
import com.facebook.react.modules.storage.AsyncStorageModule;
import com.facebook.react.modules.timepicker.TimePickerDialogModule;
import com.facebook.react.modules.toast.ToastModule;
import com.facebook.react.modules.vibration.VibrationModule;
import com.facebook.react.modules.websocket.WebSocketModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.debug.DebugComponentOwnershipModule;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@DoNotStrip
class JavaModuleWrapper {
    private static d sCoreModuleProvider;
    private static d sCustomModuleProvider;
    private final p mJSInstance;
    private final a mModuleHelper;
    private final ModuleHolder mModuleHolder;

    public interface c {
        List<MethodDescriptor> getMethodDescriptors();

        void invoke(p pVar, int i, ReadableNativeArray readableNativeArray);
    }

    static abstract class a implements c {
        a() {
        }

        protected e createCallback(p jsInstance, int index, ReadableNativeArray jsArguments) {
            if (jsArguments.isNull(index)) {
                return null;
            }
            return new e(jsInstance, (int) jsArguments.getDouble(index));
        }

        protected af createPromise(p jsInstance, int index, ReadableNativeArray jsArguments) {
            return new af(createCallback(jsInstance, index, jsArguments), createCallback(jsInstance, index + 1, jsArguments));
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$0 */
    public class AnonymousClass0 extends a {
        private static final String MODULE_NAME = "StatusBarModule";
        private StatusBarModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass0(StatusBarModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor setColor = new MethodDescriptor();
            setColor.name = "setColor";
            setColor.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(setColor);
            MethodDescriptor setTranslucent = new MethodDescriptor();
            setTranslucent.name = "setTranslucent";
            setTranslucent.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(setTranslucent);
            MethodDescriptor setHidden = new MethodDescriptor();
            setHidden.name = "setHidden";
            setHidden.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(setHidden);
            MethodDescriptor setStyle = new MethodDescriptor();
            setStyle.name = "setStyle";
            setStyle.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(setStyle);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("StatusBarModule.setColor got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.setColor((int) parameters.getDouble(0), parameters.getBoolean(1));
                        this.mWatchdog.a(MODULE_NAME, "setColor");
                        return;
                    } catch (Exception e) {
                        throw new z("StatusBarModule.setColor got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("StatusBarModule.setTranslucent got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.setTranslucent(parameters.getBoolean(0));
                        this.mWatchdog.a(MODULE_NAME, "setTranslucent");
                        return;
                    } catch (Exception e2) {
                        throw new z("StatusBarModule.setTranslucent got " + parameters.toString(), e2);
                    }
                case 2:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("StatusBarModule.setHidden got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.setHidden(parameters.getBoolean(0));
                        this.mWatchdog.a(MODULE_NAME, "setHidden");
                        return;
                    } catch (Exception e22) {
                        throw new z("StatusBarModule.setHidden got " + parameters.toString(), e22);
                    }
                case 3:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("StatusBarModule.setStyle got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.setStyle(parameters.getString(0));
                        this.mWatchdog.a(MODULE_NAME, "setStyle");
                        return;
                    } catch (Exception e222) {
                        throw new z("StatusBarModule.setStyle got " + parameters.toString(), e222);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$10 */
    public class AnonymousClass10 extends a {
        private static final String MODULE_NAME = "DeviceEventManagerModule";
        private DeviceEventManagerModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass10(DeviceEventManagerModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor invokeDefaultBackPressHandler = new MethodDescriptor();
            invokeDefaultBackPressHandler.name = "invokeDefaultBackPressHandler";
            invokeDefaultBackPressHandler.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(invokeDefaultBackPressHandler);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (parameters.size() != 0) {
                        throw new z("DeviceEventManagerModule.invokeDefaultBackPressHandler got " + parameters.toString() + "arguments, expected 0");
                    }
                    try {
                        this.mNativeModule.invokeDefaultBackPressHandler();
                        this.mWatchdog.a(MODULE_NAME, "invokeDefaultBackPressHandler");
                        return;
                    } catch (Exception e) {
                        throw new z("DeviceEventManagerModule.invokeDefaultBackPressHandler got " + parameters.toString(), e);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$11 */
    public class AnonymousClass11 extends a {
        private static final String MODULE_NAME = "AsyncStorageModule";
        private AsyncStorageModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass11(AsyncStorageModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor multiGet = new MethodDescriptor();
            multiGet.name = "multiGet";
            multiGet.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(multiGet);
            MethodDescriptor multiSet = new MethodDescriptor();
            multiSet.name = "multiSet";
            multiSet.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(multiSet);
            MethodDescriptor multiRemove = new MethodDescriptor();
            multiRemove.name = "multiRemove";
            multiRemove.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(multiRemove);
            MethodDescriptor multiMerge = new MethodDescriptor();
            multiMerge.name = "multiMerge";
            multiMerge.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(multiMerge);
            MethodDescriptor clear = new MethodDescriptor();
            clear.name = "clear";
            clear.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(clear);
            MethodDescriptor getAllKeys = new MethodDescriptor();
            getAllKeys.name = "getAllKeys";
            getAllKeys.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(getAllKeys);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("AsyncStorageModule.multiGet got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.multiGet(parameters.getArray(0), createCallback(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "multiGet");
                        return;
                    } catch (Exception e) {
                        throw new z("AsyncStorageModule.multiGet got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("AsyncStorageModule.multiSet got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.multiSet(parameters.getArray(0), createCallback(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "multiSet");
                        return;
                    } catch (Exception e2) {
                        throw new z("AsyncStorageModule.multiSet got " + parameters.toString(), e2);
                    }
                case 2:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("AsyncStorageModule.multiRemove got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.multiRemove(parameters.getArray(0), createCallback(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "multiRemove");
                        return;
                    } catch (Exception e22) {
                        throw new z("AsyncStorageModule.multiRemove got " + parameters.toString(), e22);
                    }
                case 3:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("AsyncStorageModule.multiMerge got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.multiMerge(parameters.getArray(0), createCallback(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "multiMerge");
                        return;
                    } catch (Exception e222) {
                        throw new z("AsyncStorageModule.multiMerge got " + parameters.toString(), e222);
                    }
                case 4:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("AsyncStorageModule.clear got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.clear(createCallback(catalystInstance, 0, parameters));
                        this.mWatchdog.a(MODULE_NAME, "clear");
                        return;
                    } catch (Exception e2222) {
                        throw new z("AsyncStorageModule.clear got " + parameters.toString(), e2222);
                    }
                case 5:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("AsyncStorageModule.getAllKeys got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.getAllKeys(createCallback(catalystInstance, 0, parameters));
                        this.mWatchdog.a(MODULE_NAME, "getAllKeys");
                        return;
                    } catch (Exception e22222) {
                        throw new z("AsyncStorageModule.getAllKeys got " + parameters.toString(), e22222);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$12 */
    public class AnonymousClass12 extends a {
        private static final String MODULE_NAME = "ClipboardModule";
        private ClipboardModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass12(ClipboardModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor getString = new MethodDescriptor();
            getString.name = "getString";
            getString.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(getString);
            MethodDescriptor setString = new MethodDescriptor();
            setString.name = "setString";
            setString.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(setString);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("ClipboardModule.getString got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.getString(createPromise(catalystInstance, 0, parameters));
                        this.mWatchdog.a(MODULE_NAME, "getString");
                        return;
                    } catch (Exception e) {
                        throw new z("ClipboardModule.getString got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("ClipboardModule.setString got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.setString(parameters.getString(0));
                        this.mWatchdog.a(MODULE_NAME, "setString");
                        return;
                    } catch (Exception e2) {
                        throw new z("ClipboardModule.setString got " + parameters.toString(), e2);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$13 */
    public class AnonymousClass13 extends a {
        private static final String MODULE_NAME = "HeadlessJsTaskSupportModule";
        private HeadlessJsTaskSupportModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass13(HeadlessJsTaskSupportModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor notifyTaskFinished = new MethodDescriptor();
            notifyTaskFinished.name = "notifyTaskFinished";
            notifyTaskFinished.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(notifyTaskFinished);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("HeadlessJsTaskSupportModule.notifyTaskFinished got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.notifyTaskFinished((int) parameters.getDouble(0));
                        this.mWatchdog.a(MODULE_NAME, "notifyTaskFinished");
                        return;
                    } catch (Exception e) {
                        throw new z("HeadlessJsTaskSupportModule.notifyTaskFinished got " + parameters.toString(), e);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$14 */
    public class AnonymousClass14 extends a {
        private static final String MODULE_NAME = "DialogModule";
        private DialogModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass14(DialogModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor showAlert = new MethodDescriptor();
            showAlert.name = "showAlert";
            showAlert.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(showAlert);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("DialogModule.showAlert got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.showAlert(parameters.getMap(0), createCallback(catalystInstance, 1, parameters), createCallback(catalystInstance, 2, parameters));
                        this.mWatchdog.a(MODULE_NAME, "showAlert");
                        return;
                    } catch (Exception e) {
                        throw new z("DialogModule.showAlert got " + parameters.toString(), e);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$15 */
    public class AnonymousClass15 extends a {
        private static final String MODULE_NAME = "IntentModule";
        private IntentModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass15(IntentModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor getInitialURL = new MethodDescriptor();
            getInitialURL.name = "getInitialURL";
            getInitialURL.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(getInitialURL);
            MethodDescriptor openURL = new MethodDescriptor();
            openURL.name = "openURL";
            openURL.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(openURL);
            MethodDescriptor canOpenURL = new MethodDescriptor();
            canOpenURL.name = "canOpenURL";
            canOpenURL.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(canOpenURL);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("IntentModule.getInitialURL got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.getInitialURL(createPromise(catalystInstance, 0, parameters));
                        this.mWatchdog.a(MODULE_NAME, "getInitialURL");
                        return;
                    } catch (Exception e) {
                        throw new z("IntentModule.getInitialURL got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("IntentModule.openURL got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.openURL(parameters.getString(0), createPromise(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "openURL");
                        return;
                    } catch (Exception e2) {
                        throw new z("IntentModule.openURL got " + parameters.toString(), e2);
                    }
                case 2:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("IntentModule.canOpenURL got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.canOpenURL(parameters.getString(0), createPromise(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "canOpenURL");
                        return;
                    } catch (Exception e22) {
                        throw new z("IntentModule.canOpenURL got " + parameters.toString(), e22);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$16 */
    public class AnonymousClass16 extends a {
        private static final String MODULE_NAME = "AccessibilityInfoModule";
        private AccessibilityInfoModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass16(AccessibilityInfoModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor isTouchExplorationEnabled = new MethodDescriptor();
            isTouchExplorationEnabled.name = "isTouchExplorationEnabled";
            isTouchExplorationEnabled.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(isTouchExplorationEnabled);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("AccessibilityInfoModule.isTouchExplorationEnabled got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.isTouchExplorationEnabled(createCallback(catalystInstance, 0, parameters));
                        this.mWatchdog.a(MODULE_NAME, "isTouchExplorationEnabled");
                        return;
                    } catch (Exception e) {
                        throw new z("AccessibilityInfoModule.isTouchExplorationEnabled got " + parameters.toString(), e);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$17 */
    public class AnonymousClass17 extends a {
        private static final String MODULE_NAME = "NativeAnimatedModule";
        private NativeAnimatedModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass17(NativeAnimatedModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor createAnimatedNode = new MethodDescriptor();
            createAnimatedNode.name = "createAnimatedNode";
            createAnimatedNode.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(createAnimatedNode);
            MethodDescriptor startListeningToAnimatedNodeValue = new MethodDescriptor();
            startListeningToAnimatedNodeValue.name = "startListeningToAnimatedNodeValue";
            startListeningToAnimatedNodeValue.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(startListeningToAnimatedNodeValue);
            MethodDescriptor stopListeningToAnimatedNodeValue = new MethodDescriptor();
            stopListeningToAnimatedNodeValue.name = "stopListeningToAnimatedNodeValue";
            stopListeningToAnimatedNodeValue.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(stopListeningToAnimatedNodeValue);
            MethodDescriptor dropAnimatedNode = new MethodDescriptor();
            dropAnimatedNode.name = "dropAnimatedNode";
            dropAnimatedNode.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(dropAnimatedNode);
            MethodDescriptor setAnimatedNodeValue = new MethodDescriptor();
            setAnimatedNodeValue.name = "setAnimatedNodeValue";
            setAnimatedNodeValue.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(setAnimatedNodeValue);
            MethodDescriptor setAnimatedNodeOffset = new MethodDescriptor();
            setAnimatedNodeOffset.name = "setAnimatedNodeOffset";
            setAnimatedNodeOffset.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(setAnimatedNodeOffset);
            MethodDescriptor flattenAnimatedNodeOffset = new MethodDescriptor();
            flattenAnimatedNodeOffset.name = "flattenAnimatedNodeOffset";
            flattenAnimatedNodeOffset.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(flattenAnimatedNodeOffset);
            MethodDescriptor extractAnimatedNodeOffset = new MethodDescriptor();
            extractAnimatedNodeOffset.name = "extractAnimatedNodeOffset";
            extractAnimatedNodeOffset.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(extractAnimatedNodeOffset);
            MethodDescriptor startAnimatingNode = new MethodDescriptor();
            startAnimatingNode.name = "startAnimatingNode";
            startAnimatingNode.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(startAnimatingNode);
            MethodDescriptor stopAnimation = new MethodDescriptor();
            stopAnimation.name = "stopAnimation";
            stopAnimation.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(stopAnimation);
            MethodDescriptor connectAnimatedNodes = new MethodDescriptor();
            connectAnimatedNodes.name = "connectAnimatedNodes";
            connectAnimatedNodes.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(connectAnimatedNodes);
            MethodDescriptor disconnectAnimatedNodes = new MethodDescriptor();
            disconnectAnimatedNodes.name = "disconnectAnimatedNodes";
            disconnectAnimatedNodes.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(disconnectAnimatedNodes);
            MethodDescriptor connectAnimatedNodeToView = new MethodDescriptor();
            connectAnimatedNodeToView.name = "connectAnimatedNodeToView";
            connectAnimatedNodeToView.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(connectAnimatedNodeToView);
            MethodDescriptor disconnectAnimatedNodeFromView = new MethodDescriptor();
            disconnectAnimatedNodeFromView.name = "disconnectAnimatedNodeFromView";
            disconnectAnimatedNodeFromView.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(disconnectAnimatedNodeFromView);
            MethodDescriptor addAnimatedEventToView = new MethodDescriptor();
            addAnimatedEventToView.name = "addAnimatedEventToView";
            addAnimatedEventToView.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(addAnimatedEventToView);
            MethodDescriptor removeAnimatedEventFromView = new MethodDescriptor();
            removeAnimatedEventFromView.name = "removeAnimatedEventFromView";
            removeAnimatedEventFromView.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(removeAnimatedEventFromView);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("NativeAnimatedModule.createAnimatedNode got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.createAnimatedNode((int) parameters.getDouble(0), parameters.getMap(1));
                        this.mWatchdog.a(MODULE_NAME, "createAnimatedNode");
                        return;
                    } catch (Exception e) {
                        throw new z("NativeAnimatedModule.createAnimatedNode got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("NativeAnimatedModule.startListeningToAnimatedNodeValue got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.startListeningToAnimatedNodeValue((int) parameters.getDouble(0));
                        this.mWatchdog.a(MODULE_NAME, "startListeningToAnimatedNodeValue");
                        return;
                    } catch (Exception e2) {
                        throw new z("NativeAnimatedModule.startListeningToAnimatedNodeValue got " + parameters.toString(), e2);
                    }
                case 2:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("NativeAnimatedModule.stopListeningToAnimatedNodeValue got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.stopListeningToAnimatedNodeValue((int) parameters.getDouble(0));
                        this.mWatchdog.a(MODULE_NAME, "stopListeningToAnimatedNodeValue");
                        return;
                    } catch (Exception e22) {
                        throw new z("NativeAnimatedModule.stopListeningToAnimatedNodeValue got " + parameters.toString(), e22);
                    }
                case 3:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("NativeAnimatedModule.dropAnimatedNode got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.dropAnimatedNode((int) parameters.getDouble(0));
                        this.mWatchdog.a(MODULE_NAME, "dropAnimatedNode");
                        return;
                    } catch (Exception e222) {
                        throw new z("NativeAnimatedModule.dropAnimatedNode got " + parameters.toString(), e222);
                    }
                case 4:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("NativeAnimatedModule.setAnimatedNodeValue got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.setAnimatedNodeValue((int) parameters.getDouble(0), parameters.getDouble(1));
                        this.mWatchdog.a(MODULE_NAME, "setAnimatedNodeValue");
                        return;
                    } catch (Exception e2222) {
                        throw new z("NativeAnimatedModule.setAnimatedNodeValue got " + parameters.toString(), e2222);
                    }
                case 5:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("NativeAnimatedModule.setAnimatedNodeOffset got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.setAnimatedNodeOffset((int) parameters.getDouble(0), parameters.getDouble(1));
                        this.mWatchdog.a(MODULE_NAME, "setAnimatedNodeOffset");
                        return;
                    } catch (Exception e22222) {
                        throw new z("NativeAnimatedModule.setAnimatedNodeOffset got " + parameters.toString(), e22222);
                    }
                case 6:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("NativeAnimatedModule.flattenAnimatedNodeOffset got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.flattenAnimatedNodeOffset((int) parameters.getDouble(0));
                        this.mWatchdog.a(MODULE_NAME, "flattenAnimatedNodeOffset");
                        return;
                    } catch (Exception e222222) {
                        throw new z("NativeAnimatedModule.flattenAnimatedNodeOffset got " + parameters.toString(), e222222);
                    }
                case 7:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("NativeAnimatedModule.extractAnimatedNodeOffset got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.extractAnimatedNodeOffset((int) parameters.getDouble(0));
                        this.mWatchdog.a(MODULE_NAME, "extractAnimatedNodeOffset");
                        return;
                    } catch (Exception e2222222) {
                        throw new z("NativeAnimatedModule.extractAnimatedNodeOffset got " + parameters.toString(), e2222222);
                    }
                case 8:
                    this.mWatchdog.b();
                    if (4 != parameters.size()) {
                        throw new z("NativeAnimatedModule.startAnimatingNode got " + parameters.toString() + "arguments, expected 4");
                    }
                    try {
                        this.mNativeModule.startAnimatingNode((int) parameters.getDouble(0), (int) parameters.getDouble(1), parameters.getMap(2), createCallback(catalystInstance, 3, parameters));
                        this.mWatchdog.a(MODULE_NAME, "startAnimatingNode");
                        return;
                    } catch (Exception e22222222) {
                        throw new z("NativeAnimatedModule.startAnimatingNode got " + parameters.toString(), e22222222);
                    }
                case 9:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("NativeAnimatedModule.stopAnimation got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.stopAnimation((int) parameters.getDouble(0));
                        this.mWatchdog.a(MODULE_NAME, "stopAnimation");
                        return;
                    } catch (Exception e222222222) {
                        throw new z("NativeAnimatedModule.stopAnimation got " + parameters.toString(), e222222222);
                    }
                case 10:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("NativeAnimatedModule.connectAnimatedNodes got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.connectAnimatedNodes((int) parameters.getDouble(0), (int) parameters.getDouble(1));
                        this.mWatchdog.a(MODULE_NAME, "connectAnimatedNodes");
                        return;
                    } catch (Exception e2222222222) {
                        throw new z("NativeAnimatedModule.connectAnimatedNodes got " + parameters.toString(), e2222222222);
                    }
                case 11:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("NativeAnimatedModule.disconnectAnimatedNodes got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.disconnectAnimatedNodes((int) parameters.getDouble(0), (int) parameters.getDouble(1));
                        this.mWatchdog.a(MODULE_NAME, "disconnectAnimatedNodes");
                        return;
                    } catch (Exception e22222222222) {
                        throw new z("NativeAnimatedModule.disconnectAnimatedNodes got " + parameters.toString(), e22222222222);
                    }
                case 12:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("NativeAnimatedModule.connectAnimatedNodeToView got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.connectAnimatedNodeToView((int) parameters.getDouble(0), (int) parameters.getDouble(1));
                        this.mWatchdog.a(MODULE_NAME, "connectAnimatedNodeToView");
                        return;
                    } catch (Exception e222222222222) {
                        throw new z("NativeAnimatedModule.connectAnimatedNodeToView got " + parameters.toString(), e222222222222);
                    }
                case 13:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("NativeAnimatedModule.disconnectAnimatedNodeFromView got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.disconnectAnimatedNodeFromView((int) parameters.getDouble(0), (int) parameters.getDouble(1));
                        this.mWatchdog.a(MODULE_NAME, "disconnectAnimatedNodeFromView");
                        return;
                    } catch (Exception e2222222222222) {
                        throw new z("NativeAnimatedModule.disconnectAnimatedNodeFromView got " + parameters.toString(), e2222222222222);
                    }
                case 14:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("NativeAnimatedModule.addAnimatedEventToView got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.addAnimatedEventToView((int) parameters.getDouble(0), parameters.getString(1), parameters.getMap(2));
                        this.mWatchdog.a(MODULE_NAME, "addAnimatedEventToView");
                        return;
                    } catch (Exception e22222222222222) {
                        throw new z("NativeAnimatedModule.addAnimatedEventToView got " + parameters.toString(), e22222222222222);
                    }
                case 15:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("NativeAnimatedModule.removeAnimatedEventFromView got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.removeAnimatedEventFromView((int) parameters.getDouble(0), parameters.getString(1), (int) parameters.getDouble(2));
                        this.mWatchdog.a(MODULE_NAME, "removeAnimatedEventFromView");
                        return;
                    } catch (Exception e222222222222222) {
                        throw new z("NativeAnimatedModule.removeAnimatedEventFromView got " + parameters.toString(), e222222222222222);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$18 */
    public class AnonymousClass18 extends a {
        private static final String MODULE_NAME = "WebSocketModule";
        private WebSocketModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass18(WebSocketModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor connect = new MethodDescriptor();
            connect.name = "connect";
            connect.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(connect);
            MethodDescriptor close = new MethodDescriptor();
            close.name = "close";
            close.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(close);
            MethodDescriptor send = new MethodDescriptor();
            send.name = "send";
            send.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(send);
            MethodDescriptor sendBinary = new MethodDescriptor();
            sendBinary.name = "sendBinary";
            sendBinary.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(sendBinary);
            MethodDescriptor ping = new MethodDescriptor();
            ping.name = "ping";
            ping.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(ping);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (4 != parameters.size()) {
                        throw new z("WebSocketModule.connect got " + parameters.toString() + "arguments, expected 4");
                    }
                    try {
                        this.mNativeModule.connect(parameters.getString(0), parameters.getArray(1), parameters.getMap(2), (int) parameters.getDouble(3));
                        this.mWatchdog.a(MODULE_NAME, "connect");
                        return;
                    } catch (Exception e) {
                        throw new z("WebSocketModule.connect got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("WebSocketModule.close got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.close((int) parameters.getDouble(0), parameters.getString(1), (int) parameters.getDouble(2));
                        this.mWatchdog.a(MODULE_NAME, "close");
                        return;
                    } catch (Exception e2) {
                        throw new z("WebSocketModule.close got " + parameters.toString(), e2);
                    }
                case 2:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("WebSocketModule.send got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.send(parameters.getString(0), (int) parameters.getDouble(1));
                        this.mWatchdog.a(MODULE_NAME, "send");
                        return;
                    } catch (Exception e22) {
                        throw new z("WebSocketModule.send got " + parameters.toString(), e22);
                    }
                case 3:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("WebSocketModule.sendBinary got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.sendBinary(parameters.getString(0), (int) parameters.getDouble(1));
                        this.mWatchdog.a(MODULE_NAME, "sendBinary");
                        return;
                    } catch (Exception e222) {
                        throw new z("WebSocketModule.sendBinary got " + parameters.toString(), e222);
                    }
                case 4:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("WebSocketModule.ping got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.ping((int) parameters.getDouble(0));
                        this.mWatchdog.a(MODULE_NAME, "ping");
                        return;
                    } catch (Exception e2222) {
                        throw new z("WebSocketModule.ping got " + parameters.toString(), e2222);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$19 */
    public class AnonymousClass19 extends a {
        private static final String MODULE_NAME = "TimePickerDialogModule";
        private TimePickerDialogModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass19(TimePickerDialogModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor open = new MethodDescriptor();
            open.name = "open";
            open.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(open);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("TimePickerDialogModule.open got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.open(parameters.getMap(0), createPromise(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "open");
                        return;
                    } catch (Exception e) {
                        throw new z("TimePickerDialogModule.open got " + parameters.toString(), e);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$1 */
    public class AnonymousClass1 extends a {
        private static final String MODULE_NAME = "ExceptionsManagerModule";
        private ExceptionsManagerModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass1(ExceptionsManagerModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor reportFatalException = new MethodDescriptor();
            reportFatalException.name = "reportFatalException";
            reportFatalException.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(reportFatalException);
            MethodDescriptor reportSoftException = new MethodDescriptor();
            reportSoftException.name = "reportSoftException";
            reportSoftException.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(reportSoftException);
            MethodDescriptor updateExceptionMessage = new MethodDescriptor();
            updateExceptionMessage.name = "updateExceptionMessage";
            updateExceptionMessage.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(updateExceptionMessage);
            MethodDescriptor dismissRedbox = new MethodDescriptor();
            dismissRedbox.name = "dismissRedbox";
            dismissRedbox.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(dismissRedbox);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("ExceptionsManagerModule.reportFatalException got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.reportFatalException(parameters.getString(0), parameters.getArray(1), (int) parameters.getDouble(2));
                        this.mWatchdog.a(MODULE_NAME, "reportFatalException");
                        return;
                    } catch (Exception e) {
                        throw new z("ExceptionsManagerModule.reportFatalException got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("ExceptionsManagerModule.reportSoftException got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.reportSoftException(parameters.getString(0), parameters.getArray(1), (int) parameters.getDouble(2));
                        this.mWatchdog.a(MODULE_NAME, "reportSoftException");
                        return;
                    } catch (Exception e2) {
                        throw new z("ExceptionsManagerModule.reportSoftException got " + parameters.toString(), e2);
                    }
                case 2:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("ExceptionsManagerModule.updateExceptionMessage got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.updateExceptionMessage(parameters.getString(0), parameters.getArray(1), (int) parameters.getDouble(2));
                        this.mWatchdog.a(MODULE_NAME, "updateExceptionMessage");
                        return;
                    } catch (Exception e22) {
                        throw new z("ExceptionsManagerModule.updateExceptionMessage got " + parameters.toString(), e22);
                    }
                case 3:
                    this.mWatchdog.b();
                    if (parameters.size() != 0) {
                        throw new z("ExceptionsManagerModule.dismissRedbox got " + parameters.toString() + "arguments, expected 0");
                    }
                    try {
                        this.mNativeModule.dismissRedbox();
                        this.mWatchdog.a(MODULE_NAME, "dismissRedbox");
                        return;
                    } catch (Exception e222) {
                        throw new z("ExceptionsManagerModule.dismissRedbox got " + parameters.toString(), e222);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$20 */
    public class AnonymousClass20 extends a {
        private static final String MODULE_NAME = "ImageStoreManager";
        private ImageStoreManager mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass20(ImageStoreManager module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor getBase64ForTag = new MethodDescriptor();
            getBase64ForTag.name = "getBase64ForTag";
            getBase64ForTag.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(getBase64ForTag);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("ImageStoreManager.getBase64ForTag got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.getBase64ForTag(parameters.getString(0), createCallback(catalystInstance, 1, parameters), createCallback(catalystInstance, 2, parameters));
                        this.mWatchdog.a(MODULE_NAME, "getBase64ForTag");
                        return;
                    } catch (Exception e) {
                        throw new z("ImageStoreManager.getBase64ForTag got " + parameters.toString(), e);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$21 */
    public class AnonymousClass21 extends a {
        private static final String MODULE_NAME = "DatePickerDialogModule";
        private DatePickerDialogModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass21(DatePickerDialogModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor open = new MethodDescriptor();
            open.name = "open";
            open.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(open);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("DatePickerDialogModule.open got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.open(parameters.getMap(0), createPromise(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "open");
                        return;
                    } catch (Exception e) {
                        throw new z("DatePickerDialogModule.open got " + parameters.toString(), e);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$22 */
    public class AnonymousClass22 extends a {
        private static final String MODULE_NAME = "CameraRollManager";
        private CameraRollManager mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass22(CameraRollManager module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor saveToCameraRoll = new MethodDescriptor();
            saveToCameraRoll.name = "saveToCameraRoll";
            saveToCameraRoll.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(saveToCameraRoll);
            MethodDescriptor getPhotos = new MethodDescriptor();
            getPhotos.name = "getPhotos";
            getPhotos.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(getPhotos);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (4 != parameters.size()) {
                        throw new z("CameraRollManager.saveToCameraRoll got " + parameters.toString() + "arguments, expected 4");
                    }
                    try {
                        this.mNativeModule.saveToCameraRoll(parameters.getString(0), parameters.getString(1), createPromise(catalystInstance, 2, parameters));
                        this.mWatchdog.a(MODULE_NAME, "saveToCameraRoll");
                        return;
                    } catch (Exception e) {
                        throw new z("CameraRollManager.saveToCameraRoll got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("CameraRollManager.getPhotos got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.getPhotos(parameters.getMap(0), createPromise(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "getPhotos");
                        return;
                    } catch (Exception e2) {
                        throw new z("CameraRollManager.getPhotos got " + parameters.toString(), e2);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$23 */
    public class AnonymousClass23 extends a {
        private static final String MODULE_NAME = "NetInfoModule";
        private NetInfoModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass23(NetInfoModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor getCurrentConnectivity = new MethodDescriptor();
            getCurrentConnectivity.name = "getCurrentConnectivity";
            getCurrentConnectivity.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(getCurrentConnectivity);
            MethodDescriptor isConnectionMetered = new MethodDescriptor();
            isConnectionMetered.name = "isConnectionMetered";
            isConnectionMetered.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(isConnectionMetered);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("NetInfoModule.getCurrentConnectivity got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.getCurrentConnectivity(createPromise(catalystInstance, 0, parameters));
                        this.mWatchdog.a(MODULE_NAME, "getCurrentConnectivity");
                        return;
                    } catch (Exception e) {
                        throw new z("NetInfoModule.getCurrentConnectivity got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("NetInfoModule.isConnectionMetered got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.isConnectionMetered(createPromise(catalystInstance, 0, parameters));
                        this.mWatchdog.a(MODULE_NAME, "isConnectionMetered");
                        return;
                    } catch (Exception e2) {
                        throw new z("NetInfoModule.isConnectionMetered got " + parameters.toString(), e2);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$24 */
    public class AnonymousClass24 extends a {
        private static final String MODULE_NAME = "AnimationsDebugModule";
        private AnimationsDebugModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass24(AnimationsDebugModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor startRecordingFps = new MethodDescriptor();
            startRecordingFps.name = "startRecordingFps";
            startRecordingFps.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(startRecordingFps);
            MethodDescriptor stopRecordingFps = new MethodDescriptor();
            stopRecordingFps.name = "stopRecordingFps";
            stopRecordingFps.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(stopRecordingFps);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (parameters.size() != 0) {
                        throw new z("AnimationsDebugModule.startRecordingFps got " + parameters.toString() + "arguments, expected 0");
                    }
                    try {
                        this.mNativeModule.startRecordingFps();
                        this.mWatchdog.a(MODULE_NAME, "startRecordingFps");
                        return;
                    } catch (Exception e) {
                        throw new z("AnimationsDebugModule.startRecordingFps got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("AnimationsDebugModule.stopRecordingFps got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.stopRecordingFps(parameters.getDouble(0));
                        this.mWatchdog.a(MODULE_NAME, "stopRecordingFps");
                        return;
                    } catch (Exception e2) {
                        throw new z("AnimationsDebugModule.stopRecordingFps got " + parameters.toString(), e2);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$25 */
    public class AnonymousClass25 extends a {
        private static final String MODULE_NAME = "NetworkingModule";
        private NetworkingModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass25(NetworkingModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor sendRequest = new MethodDescriptor();
            sendRequest.name = "sendRequest";
            sendRequest.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(sendRequest);
            MethodDescriptor abortRequest = new MethodDescriptor();
            abortRequest.name = "abortRequest";
            abortRequest.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(abortRequest);
            MethodDescriptor clearCookies = new MethodDescriptor();
            clearCookies.name = "clearCookies";
            clearCookies.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(clearCookies);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (9 != parameters.size()) {
                        throw new z("NetworkingModule.sendRequest got " + parameters.toString() + "arguments, expected 9");
                    }
                    try {
                        this.mNativeModule.sendRequest(parameters.getString(0), parameters.getString(1), (int) parameters.getDouble(2), parameters.getArray(3), parameters.getMap(4), parameters.getString(5), parameters.getBoolean(6), (int) parameters.getDouble(7), parameters.getBoolean(8));
                        this.mWatchdog.a(MODULE_NAME, "sendRequest");
                        return;
                    } catch (Exception e) {
                        throw new z("NetworkingModule.sendRequest got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("NetworkingModule.abortRequest got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.abortRequest((int) parameters.getDouble(0));
                        this.mWatchdog.a(MODULE_NAME, "abortRequest");
                        return;
                    } catch (Exception e2) {
                        throw new z("NetworkingModule.abortRequest got " + parameters.toString(), e2);
                    }
                case 2:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("NetworkingModule.clearCookies got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.clearCookies(createCallback(catalystInstance, 0, parameters));
                        this.mWatchdog.a(MODULE_NAME, "clearCookies");
                        return;
                    } catch (Exception e22) {
                        throw new z("NetworkingModule.clearCookies got " + parameters.toString(), e22);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$26 */
    public class AnonymousClass26 extends a {
        private static final String MODULE_NAME = "I18nManagerModule";
        private I18nManagerModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass26(I18nManagerModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor allowRTL = new MethodDescriptor();
            allowRTL.name = "allowRTL";
            allowRTL.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(allowRTL);
            MethodDescriptor forceRTL = new MethodDescriptor();
            forceRTL.name = "forceRTL";
            forceRTL.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(forceRTL);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("I18nManagerModule.allowRTL got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.allowRTL(parameters.getBoolean(0));
                        this.mWatchdog.a(MODULE_NAME, "allowRTL");
                        return;
                    } catch (Exception e) {
                        throw new z("I18nManagerModule.allowRTL got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("I18nManagerModule.forceRTL got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.forceRTL(parameters.getBoolean(0));
                        this.mWatchdog.a(MODULE_NAME, "forceRTL");
                        return;
                    } catch (Exception e2) {
                        throw new z("I18nManagerModule.forceRTL got " + parameters.toString(), e2);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$27 */
    public class AnonymousClass27 extends a {
        private static final String MODULE_NAME = "ImageLoaderModule";
        private ImageLoaderModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass27(ImageLoaderModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor getSize = new MethodDescriptor();
            getSize.name = "getSize";
            getSize.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(getSize);
            MethodDescriptor prefetchImage = new MethodDescriptor();
            prefetchImage.name = "prefetchImage";
            prefetchImage.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(prefetchImage);
            MethodDescriptor abortRequest = new MethodDescriptor();
            abortRequest.name = "abortRequest";
            abortRequest.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(abortRequest);
            MethodDescriptor queryCache = new MethodDescriptor();
            queryCache.name = "queryCache";
            queryCache.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(queryCache);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("ImageLoaderModule.getSize got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.getSize(parameters.getString(0), createPromise(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "getSize");
                        return;
                    } catch (Exception e) {
                        throw new z("ImageLoaderModule.getSize got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (4 != parameters.size()) {
                        throw new z("ImageLoaderModule.prefetchImage got " + parameters.toString() + "arguments, expected 4");
                    }
                    try {
                        this.mNativeModule.prefetchImage(parameters.getString(0), (int) parameters.getDouble(1), createPromise(catalystInstance, 2, parameters));
                        this.mWatchdog.a(MODULE_NAME, "prefetchImage");
                        return;
                    } catch (Exception e2) {
                        throw new z("ImageLoaderModule.prefetchImage got " + parameters.toString(), e2);
                    }
                case 2:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("ImageLoaderModule.abortRequest got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.abortRequest((int) parameters.getDouble(0));
                        this.mWatchdog.a(MODULE_NAME, "abortRequest");
                        return;
                    } catch (Exception e22) {
                        throw new z("ImageLoaderModule.abortRequest got " + parameters.toString(), e22);
                    }
                case 3:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("ImageLoaderModule.queryCache got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.queryCache(parameters.getArray(0), createPromise(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "queryCache");
                        return;
                    } catch (Exception e222) {
                        throw new z("ImageLoaderModule.queryCache got " + parameters.toString(), e222);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$28 */
    public class AnonymousClass28 extends a {
        private static final String MODULE_NAME = "ShareModule";
        private ShareModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass28(ShareModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor share = new MethodDescriptor();
            share.name = "share";
            share.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(share);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (4 != parameters.size()) {
                        throw new z("ShareModule.share got " + parameters.toString() + "arguments, expected 4");
                    }
                    try {
                        this.mNativeModule.share(parameters.getMap(0), parameters.getString(1), createPromise(catalystInstance, 2, parameters));
                        this.mWatchdog.a(MODULE_NAME, "share");
                        return;
                    } catch (Exception e) {
                        throw new z("ShareModule.share got " + parameters.toString(), e);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$29 */
    public class AnonymousClass29 extends a {
        private static final String MODULE_NAME = "JSCHeapCapture";
        private JSCHeapCapture mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass29(JSCHeapCapture module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor captureComplete = new MethodDescriptor();
            captureComplete.name = "captureComplete";
            captureComplete.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(captureComplete);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("JSCHeapCapture.captureComplete got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.captureComplete(parameters.getString(0), parameters.getString(1));
                        this.mWatchdog.a(MODULE_NAME, "captureComplete");
                        return;
                    } catch (Exception e) {
                        throw new z("JSCHeapCapture.captureComplete got " + parameters.toString(), e);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$2 */
    public class AnonymousClass2 extends a {
        private static final String MODULE_NAME = "JSCSamplingProfiler";
        private JSCSamplingProfiler mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass2(JSCSamplingProfiler module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor operationComplete = new MethodDescriptor();
            operationComplete.name = "operationComplete";
            operationComplete.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(operationComplete);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("JSCSamplingProfiler.operationComplete got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.operationComplete((int) parameters.getDouble(0), parameters.getString(1), parameters.getString(2));
                        this.mWatchdog.a(MODULE_NAME, "operationComplete");
                        return;
                    } catch (Exception e) {
                        throw new z("JSCSamplingProfiler.operationComplete got " + parameters.toString(), e);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$30 */
    public class AnonymousClass30 extends a {
        private static final String MODULE_NAME = "ToastModule";
        private ToastModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass30(ToastModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor show = new MethodDescriptor();
            show.name = "show";
            show.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(show);
            MethodDescriptor showWithGravity = new MethodDescriptor();
            showWithGravity.name = "showWithGravity";
            showWithGravity.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(showWithGravity);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("ToastModule.show got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.show(parameters.getString(0), (int) parameters.getDouble(1));
                        this.mWatchdog.a(MODULE_NAME, "show");
                        return;
                    } catch (Exception e) {
                        throw new z("ToastModule.show got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("ToastModule.showWithGravity got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.showWithGravity(parameters.getString(0), (int) parameters.getDouble(1), (int) parameters.getDouble(2));
                        this.mWatchdog.a(MODULE_NAME, "showWithGravity");
                        return;
                    } catch (Exception e2) {
                        throw new z("ToastModule.showWithGravity got " + parameters.toString(), e2);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$31 */
    public class AnonymousClass31 extends a {
        private static final String MODULE_NAME = "UIManagerModule";
        private UIManagerModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass31(UIManagerModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor removeRootView = new MethodDescriptor();
            removeRootView.name = "removeRootView";
            removeRootView.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(removeRootView);
            MethodDescriptor createView = new MethodDescriptor();
            createView.name = "createView";
            createView.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(createView);
            MethodDescriptor updateView = new MethodDescriptor();
            updateView.name = "updateView";
            updateView.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(updateView);
            MethodDescriptor manageChildren = new MethodDescriptor();
            manageChildren.name = "manageChildren";
            manageChildren.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(manageChildren);
            MethodDescriptor setChildren = new MethodDescriptor();
            setChildren.name = "setChildren";
            setChildren.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(setChildren);
            MethodDescriptor replaceExistingNonRootView = new MethodDescriptor();
            replaceExistingNonRootView.name = "replaceExistingNonRootView";
            replaceExistingNonRootView.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(replaceExistingNonRootView);
            MethodDescriptor removeSubviewsFromContainerWithID = new MethodDescriptor();
            removeSubviewsFromContainerWithID.name = "removeSubviewsFromContainerWithID";
            removeSubviewsFromContainerWithID.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(removeSubviewsFromContainerWithID);
            MethodDescriptor measure = new MethodDescriptor();
            measure.name = "measure";
            measure.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(measure);
            MethodDescriptor measureInWindow = new MethodDescriptor();
            measureInWindow.name = "measureInWindow";
            measureInWindow.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(measureInWindow);
            MethodDescriptor measureLayout = new MethodDescriptor();
            measureLayout.name = "measureLayout";
            measureLayout.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(measureLayout);
            MethodDescriptor measureLayoutRelativeToParent = new MethodDescriptor();
            measureLayoutRelativeToParent.name = "measureLayoutRelativeToParent";
            measureLayoutRelativeToParent.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(measureLayoutRelativeToParent);
            MethodDescriptor findSubviewIn = new MethodDescriptor();
            findSubviewIn.name = "findSubviewIn";
            findSubviewIn.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(findSubviewIn);
            MethodDescriptor viewIsDescendantOf = new MethodDescriptor();
            viewIsDescendantOf.name = "viewIsDescendantOf";
            viewIsDescendantOf.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(viewIsDescendantOf);
            MethodDescriptor setJSResponder = new MethodDescriptor();
            setJSResponder.name = "setJSResponder";
            setJSResponder.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(setJSResponder);
            MethodDescriptor clearJSResponder = new MethodDescriptor();
            clearJSResponder.name = "clearJSResponder";
            clearJSResponder.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(clearJSResponder);
            MethodDescriptor dispatchViewManagerCommand = new MethodDescriptor();
            dispatchViewManagerCommand.name = "dispatchViewManagerCommand";
            dispatchViewManagerCommand.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(dispatchViewManagerCommand);
            MethodDescriptor showPopupMenu = new MethodDescriptor();
            showPopupMenu.name = "showPopupMenu";
            showPopupMenu.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(showPopupMenu);
            MethodDescriptor setLayoutAnimationEnabledExperimental = new MethodDescriptor();
            setLayoutAnimationEnabledExperimental.name = "setLayoutAnimationEnabledExperimental";
            setLayoutAnimationEnabledExperimental.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(setLayoutAnimationEnabledExperimental);
            MethodDescriptor configureNextLayoutAnimation = new MethodDescriptor();
            configureNextLayoutAnimation.name = "configureNextLayoutAnimation";
            configureNextLayoutAnimation.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(configureNextLayoutAnimation);
            MethodDescriptor sendAccessibilityEvent = new MethodDescriptor();
            sendAccessibilityEvent.name = "sendAccessibilityEvent";
            sendAccessibilityEvent.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(sendAccessibilityEvent);
            MethodDescriptor getContentSizeMultiplier = new MethodDescriptor();
            getContentSizeMultiplier.name = "getContentSizeMultiplier";
            getContentSizeMultiplier.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(getContentSizeMultiplier);
            MethodDescriptor getMaxContentSizeMultiplier = new MethodDescriptor();
            getMaxContentSizeMultiplier.name = "getMaxContentSizeMultiplier";
            getMaxContentSizeMultiplier.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(getMaxContentSizeMultiplier);
            MethodDescriptor setMaxContentSizeMultiplier = new MethodDescriptor();
            setMaxContentSizeMultiplier.name = "setMaxContentSizeMultiplier";
            setMaxContentSizeMultiplier.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(setMaxContentSizeMultiplier);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("UIManagerModule.removeRootView got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.removeRootView((int) parameters.getDouble(0));
                        this.mWatchdog.a(MODULE_NAME, "removeRootView");
                        return;
                    } catch (Exception e) {
                        throw new z("UIManagerModule.removeRootView got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (4 != parameters.size()) {
                        throw new z("UIManagerModule.createView got " + parameters.toString() + "arguments, expected 4");
                    }
                    try {
                        this.mNativeModule.createView((int) parameters.getDouble(0), parameters.getString(1), (int) parameters.getDouble(2), parameters.getMap(3));
                        this.mWatchdog.a(MODULE_NAME, "createView");
                        return;
                    } catch (Exception e2) {
                        throw new z("UIManagerModule.createView got " + parameters.toString(), e2);
                    }
                case 2:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("UIManagerModule.updateView got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.updateView((int) parameters.getDouble(0), parameters.getString(1), parameters.getMap(2));
                        this.mWatchdog.a(MODULE_NAME, "updateView");
                        return;
                    } catch (Exception e22) {
                        throw new z("UIManagerModule.updateView got " + parameters.toString(), e22);
                    }
                case 3:
                    this.mWatchdog.b();
                    if (6 != parameters.size()) {
                        throw new z("UIManagerModule.manageChildren got " + parameters.toString() + "arguments, expected 6");
                    }
                    try {
                        this.mNativeModule.manageChildren((int) parameters.getDouble(0), parameters.getArray(1), parameters.getArray(2), parameters.getArray(3), parameters.getArray(4), parameters.getArray(5));
                        this.mWatchdog.a(MODULE_NAME, "manageChildren");
                        return;
                    } catch (Exception e222) {
                        throw new z("UIManagerModule.manageChildren got " + parameters.toString(), e222);
                    }
                case 4:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("UIManagerModule.setChildren got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.setChildren((int) parameters.getDouble(0), parameters.getArray(1));
                        this.mWatchdog.a(MODULE_NAME, "setChildren");
                        return;
                    } catch (Exception e2222) {
                        throw new z("UIManagerModule.setChildren got " + parameters.toString(), e2222);
                    }
                case 5:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("UIManagerModule.replaceExistingNonRootView got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.replaceExistingNonRootView((int) parameters.getDouble(0), (int) parameters.getDouble(1));
                        this.mWatchdog.a(MODULE_NAME, "replaceExistingNonRootView");
                        return;
                    } catch (Exception e22222) {
                        throw new z("UIManagerModule.replaceExistingNonRootView got " + parameters.toString(), e22222);
                    }
                case 6:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("UIManagerModule.removeSubviewsFromContainerWithID got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.removeSubviewsFromContainerWithID((int) parameters.getDouble(0));
                        this.mWatchdog.a(MODULE_NAME, "removeSubviewsFromContainerWithID");
                        return;
                    } catch (Exception e222222) {
                        throw new z("UIManagerModule.removeSubviewsFromContainerWithID got " + parameters.toString(), e222222);
                    }
                case 7:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("UIManagerModule.measure got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.measure((int) parameters.getDouble(0), createCallback(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "measure");
                        return;
                    } catch (Exception e2222222) {
                        throw new z("UIManagerModule.measure got " + parameters.toString(), e2222222);
                    }
                case 8:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("UIManagerModule.measureInWindow got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.measureInWindow((int) parameters.getDouble(0), createCallback(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "measureInWindow");
                        return;
                    } catch (Exception e22222222) {
                        throw new z("UIManagerModule.measureInWindow got " + parameters.toString(), e22222222);
                    }
                case 9:
                    this.mWatchdog.b();
                    if (4 != parameters.size()) {
                        throw new z("UIManagerModule.measureLayout got " + parameters.toString() + "arguments, expected 4");
                    }
                    try {
                        this.mNativeModule.measureLayout((int) parameters.getDouble(0), (int) parameters.getDouble(1), createCallback(catalystInstance, 2, parameters), createCallback(catalystInstance, 3, parameters));
                        this.mWatchdog.a(MODULE_NAME, "measureLayout");
                        return;
                    } catch (Exception e222222222) {
                        throw new z("UIManagerModule.measureLayout got " + parameters.toString(), e222222222);
                    }
                case 10:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("UIManagerModule.measureLayoutRelativeToParent got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.measureLayoutRelativeToParent((int) parameters.getDouble(0), createCallback(catalystInstance, 1, parameters), createCallback(catalystInstance, 2, parameters));
                        this.mWatchdog.a(MODULE_NAME, "measureLayoutRelativeToParent");
                        return;
                    } catch (Exception e2222222222) {
                        throw new z("UIManagerModule.measureLayoutRelativeToParent got " + parameters.toString(), e2222222222);
                    }
                case 11:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("UIManagerModule.findSubviewIn got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.findSubviewIn((int) parameters.getDouble(0), parameters.getArray(1), createCallback(catalystInstance, 2, parameters));
                        this.mWatchdog.a(MODULE_NAME, "findSubviewIn");
                        return;
                    } catch (Exception e22222222222) {
                        throw new z("UIManagerModule.findSubviewIn got " + parameters.toString(), e22222222222);
                    }
                case 12:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("UIManagerModule.viewIsDescendantOf got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.viewIsDescendantOf((int) parameters.getDouble(0), (int) parameters.getDouble(1), createCallback(catalystInstance, 2, parameters));
                        this.mWatchdog.a(MODULE_NAME, "viewIsDescendantOf");
                        return;
                    } catch (Exception e222222222222) {
                        throw new z("UIManagerModule.viewIsDescendantOf got " + parameters.toString(), e222222222222);
                    }
                case 13:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("UIManagerModule.setJSResponder got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.setJSResponder((int) parameters.getDouble(0), parameters.getBoolean(1));
                        this.mWatchdog.a(MODULE_NAME, "setJSResponder");
                        return;
                    } catch (Exception e2222222222222) {
                        throw new z("UIManagerModule.setJSResponder got " + parameters.toString(), e2222222222222);
                    }
                case 14:
                    this.mWatchdog.b();
                    if (parameters.size() != 0) {
                        throw new z("UIManagerModule.clearJSResponder got " + parameters.toString() + "arguments, expected 0");
                    }
                    try {
                        this.mNativeModule.clearJSResponder();
                        this.mWatchdog.a(MODULE_NAME, "clearJSResponder");
                        return;
                    } catch (Exception e22222222222222) {
                        throw new z("UIManagerModule.clearJSResponder got " + parameters.toString(), e22222222222222);
                    }
                case 15:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("UIManagerModule.dispatchViewManagerCommand got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.dispatchViewManagerCommand((int) parameters.getDouble(0), (int) parameters.getDouble(1), parameters.getArray(2));
                        this.mWatchdog.a(MODULE_NAME, "dispatchViewManagerCommand");
                        return;
                    } catch (Exception e222222222222222) {
                        throw new z("UIManagerModule.dispatchViewManagerCommand got " + parameters.toString(), e222222222222222);
                    }
                case 16:
                    this.mWatchdog.b();
                    if (4 != parameters.size()) {
                        throw new z("UIManagerModule.showPopupMenu got " + parameters.toString() + "arguments, expected 4");
                    }
                    try {
                        this.mNativeModule.showPopupMenu((int) parameters.getDouble(0), parameters.getArray(1), createCallback(catalystInstance, 2, parameters), createCallback(catalystInstance, 3, parameters));
                        this.mWatchdog.a(MODULE_NAME, "showPopupMenu");
                        return;
                    } catch (Exception e2222222222222222) {
                        throw new z("UIManagerModule.showPopupMenu got " + parameters.toString(), e2222222222222222);
                    }
                case 17:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("UIManagerModule.setLayoutAnimationEnabledExperimental got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.setLayoutAnimationEnabledExperimental(parameters.getBoolean(0));
                        this.mWatchdog.a(MODULE_NAME, "setLayoutAnimationEnabledExperimental");
                        return;
                    } catch (Exception e22222222222222222) {
                        throw new z("UIManagerModule.setLayoutAnimationEnabledExperimental got " + parameters.toString(), e22222222222222222);
                    }
                case 18:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("UIManagerModule.configureNextLayoutAnimation got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.configureNextLayoutAnimation(parameters.getMap(0), createCallback(catalystInstance, 1, parameters), createCallback(catalystInstance, 2, parameters));
                        this.mWatchdog.a(MODULE_NAME, "configureNextLayoutAnimation");
                        return;
                    } catch (Exception e222222222222222222) {
                        throw new z("UIManagerModule.configureNextLayoutAnimation got " + parameters.toString(), e222222222222222222);
                    }
                case 19:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("UIManagerModule.sendAccessibilityEvent got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.sendAccessibilityEvent((int) parameters.getDouble(0), (int) parameters.getDouble(1));
                        this.mWatchdog.a(MODULE_NAME, "sendAccessibilityEvent");
                        return;
                    } catch (Exception e2222222222222222222) {
                        throw new z("UIManagerModule.sendAccessibilityEvent got " + parameters.toString(), e2222222222222222222);
                    }
                case 20:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("UIManagerModule.getContentSizeMultiplier got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.getContentSizeMultiplier(createCallback(catalystInstance, 0, parameters));
                        this.mWatchdog.a(MODULE_NAME, "getContentSizeMultiplier");
                        return;
                    } catch (Exception e22222222222222222222) {
                        throw new z("UIManagerModule.getContentSizeMultiplier got " + parameters.toString(), e22222222222222222222);
                    }
                case 21:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("UIManagerModule.getMaxContentSizeMultiplier got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.getMaxContentSizeMultiplier(createCallback(catalystInstance, 0, parameters));
                        this.mWatchdog.a(MODULE_NAME, "getMaxContentSizeMultiplier");
                        return;
                    } catch (Exception e222222222222222222222) {
                        throw new z("UIManagerModule.getMaxContentSizeMultiplier got " + parameters.toString(), e222222222222222222222);
                    }
                case 22:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("UIManagerModule.setMaxContentSizeMultiplier got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.setMaxContentSizeMultiplier((float) parameters.getDouble(0));
                        this.mWatchdog.a(MODULE_NAME, "setMaxContentSizeMultiplier");
                        return;
                    } catch (Exception e2222222222222222222222) {
                        throw new z("UIManagerModule.setMaxContentSizeMultiplier got " + parameters.toString(), e2222222222222222222222);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$3 */
    public class AnonymousClass3 extends a {
        private static final String MODULE_NAME = "DebugComponentOwnershipModule";
        private DebugComponentOwnershipModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass3(DebugComponentOwnershipModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor receiveOwnershipHierarchy = new MethodDescriptor();
            receiveOwnershipHierarchy.name = "receiveOwnershipHierarchy";
            receiveOwnershipHierarchy.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(receiveOwnershipHierarchy);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("DebugComponentOwnershipModule.receiveOwnershipHierarchy got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.receiveOwnershipHierarchy((int) parameters.getDouble(0), (int) parameters.getDouble(1), parameters.getArray(2));
                        this.mWatchdog.a(MODULE_NAME, "receiveOwnershipHierarchy");
                        return;
                    } catch (Exception e) {
                        throw new z("DebugComponentOwnershipModule.receiveOwnershipHierarchy got " + parameters.toString(), e);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$4 */
    public class AnonymousClass4 extends a {
        private static final String MODULE_NAME = "LocationModule";
        private LocationModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass4(LocationModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor setRNConfiguration = new MethodDescriptor();
            setRNConfiguration.name = "setRNConfiguration";
            setRNConfiguration.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(setRNConfiguration);
            MethodDescriptor getCurrentPosition = new MethodDescriptor();
            getCurrentPosition.name = "getCurrentPosition";
            getCurrentPosition.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(getCurrentPosition);
            MethodDescriptor startObserving = new MethodDescriptor();
            startObserving.name = "startObserving";
            startObserving.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(startObserving);
            MethodDescriptor stopObserving = new MethodDescriptor();
            stopObserving.name = "stopObserving";
            stopObserving.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(stopObserving);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("LocationModule.setRNConfiguration got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.setRNConfiguration(parameters.getMap(0));
                        this.mWatchdog.a(MODULE_NAME, "setRNConfiguration");
                        return;
                    } catch (Exception e) {
                        throw new z("LocationModule.setRNConfiguration got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("LocationModule.getCurrentPosition got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.getCurrentPosition(parameters.getMap(0), createCallback(catalystInstance, 1, parameters), createCallback(catalystInstance, 2, parameters));
                        this.mWatchdog.a(MODULE_NAME, "getCurrentPosition");
                        return;
                    } catch (Exception e2) {
                        throw new z("LocationModule.getCurrentPosition got " + parameters.toString(), e2);
                    }
                case 2:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("LocationModule.startObserving got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.startObserving(parameters.getMap(0));
                        this.mWatchdog.a(MODULE_NAME, "startObserving");
                        return;
                    } catch (Exception e22) {
                        throw new z("LocationModule.startObserving got " + parameters.toString(), e22);
                    }
                case 3:
                    this.mWatchdog.b();
                    if (parameters.size() != 0) {
                        throw new z("LocationModule.stopObserving got " + parameters.toString() + "arguments, expected 0");
                    }
                    try {
                        this.mNativeModule.stopObserving();
                        this.mWatchdog.a(MODULE_NAME, "stopObserving");
                        return;
                    } catch (Exception e222) {
                        throw new z("LocationModule.stopObserving got " + parameters.toString(), e222);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$5 */
    public class AnonymousClass5 extends a {
        private static final String MODULE_NAME = "AppStateModule";
        private AppStateModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass5(AppStateModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor getCurrentAppState = new MethodDescriptor();
            getCurrentAppState.name = "getCurrentAppState";
            getCurrentAppState.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(getCurrentAppState);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("AppStateModule.getCurrentAppState got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.getCurrentAppState(createCallback(catalystInstance, 0, parameters), createCallback(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "getCurrentAppState");
                        return;
                    } catch (Exception e) {
                        throw new z("AppStateModule.getCurrentAppState got " + parameters.toString(), e);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$6 */
    public class AnonymousClass6 extends a {
        private static final String MODULE_NAME = "Timing";
        private Timing mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass6(Timing module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor createTimer = new MethodDescriptor();
            createTimer.name = "createTimer";
            createTimer.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(createTimer);
            MethodDescriptor deleteTimer = new MethodDescriptor();
            deleteTimer.name = "deleteTimer";
            deleteTimer.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(deleteTimer);
            MethodDescriptor setSendIdleEvents = new MethodDescriptor();
            setSendIdleEvents.name = "setSendIdleEvents";
            setSendIdleEvents.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(setSendIdleEvents);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (4 != parameters.size()) {
                        throw new z("Timing.createTimer got " + parameters.toString() + "arguments, expected 4");
                    }
                    try {
                        this.mNativeModule.createTimer((int) parameters.getDouble(0), (int) parameters.getDouble(1), parameters.getDouble(2), parameters.getBoolean(3));
                        this.mWatchdog.a(MODULE_NAME, "createTimer");
                        return;
                    } catch (Exception e) {
                        throw new z("Timing.createTimer got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("Timing.deleteTimer got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.deleteTimer((int) parameters.getDouble(0));
                        this.mWatchdog.a(MODULE_NAME, "deleteTimer");
                        return;
                    } catch (Exception e2) {
                        throw new z("Timing.deleteTimer got " + parameters.toString(), e2);
                    }
                case 2:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("Timing.setSendIdleEvents got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.setSendIdleEvents(parameters.getBoolean(0));
                        this.mWatchdog.a(MODULE_NAME, "setSendIdleEvents");
                        return;
                    } catch (Exception e22) {
                        throw new z("Timing.setSendIdleEvents got " + parameters.toString(), e22);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$7 */
    public class AnonymousClass7 extends a {
        private static final String MODULE_NAME = "ImageEditingManager";
        private ImageEditingManager mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass7(ImageEditingManager module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor cropImage = new MethodDescriptor();
            cropImage.name = "cropImage";
            cropImage.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(cropImage);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (4 != parameters.size()) {
                        throw new z("ImageEditingManager.cropImage got " + parameters.toString() + "arguments, expected 4");
                    }
                    try {
                        this.mNativeModule.cropImage(parameters.getString(0), parameters.getMap(1), createCallback(catalystInstance, 2, parameters), createCallback(catalystInstance, 3, parameters));
                        this.mWatchdog.a(MODULE_NAME, "cropImage");
                        return;
                    } catch (Exception e) {
                        throw new z("ImageEditingManager.cropImage got " + parameters.toString(), e);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$8 */
    public class AnonymousClass8 extends a {
        private static final String MODULE_NAME = "VibrationModule";
        private VibrationModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass8(VibrationModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor vibrate = new MethodDescriptor();
            vibrate.name = "vibrate";
            vibrate.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(vibrate);
            MethodDescriptor vibrateByPattern = new MethodDescriptor();
            vibrateByPattern.name = "vibrateByPattern";
            vibrateByPattern.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(vibrateByPattern);
            MethodDescriptor cancel = new MethodDescriptor();
            cancel.name = "cancel";
            cancel.type = BaseJavaModule.METHOD_TYPE_ASYNC;
            list.add(cancel);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (1 != parameters.size()) {
                        throw new z("VibrationModule.vibrate got " + parameters.toString() + "arguments, expected 1");
                    }
                    try {
                        this.mNativeModule.vibrate((int) parameters.getDouble(0));
                        this.mWatchdog.a(MODULE_NAME, "vibrate");
                        return;
                    } catch (Exception e) {
                        throw new z("VibrationModule.vibrate got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (2 != parameters.size()) {
                        throw new z("VibrationModule.vibrateByPattern got " + parameters.toString() + "arguments, expected 2");
                    }
                    try {
                        this.mNativeModule.vibrateByPattern(parameters.getArray(0), (int) parameters.getDouble(1));
                        this.mWatchdog.a(MODULE_NAME, "vibrateByPattern");
                        return;
                    } catch (Exception e2) {
                        throw new z("VibrationModule.vibrateByPattern got " + parameters.toString(), e2);
                    }
                case 2:
                    this.mWatchdog.b();
                    if (parameters.size() != 0) {
                        throw new z("VibrationModule.cancel got " + parameters.toString() + "arguments, expected 0");
                    }
                    try {
                        this.mNativeModule.cancel();
                        this.mWatchdog.a(MODULE_NAME, "cancel");
                        return;
                    } catch (Exception e22) {
                        throw new z("VibrationModule.cancel got " + parameters.toString(), e22);
                    }
                default:
                    return;
            }
        }
    }

    @DoNotStrip
    /* renamed from: com.facebook.react.bridge.JavaModuleWrapper$9 */
    public class AnonymousClass9 extends a {
        private static final String MODULE_NAME = "PermissionsModule";
        private PermissionsModule mNativeModule;
        private ak mWatchdog = new ak();

        public AnonymousClass9(PermissionsModule module) {
            this.mNativeModule = module;
        }

        public List<MethodDescriptor> getMethodDescriptors() {
            List<MethodDescriptor> list = new ArrayList();
            MethodDescriptor checkPermission = new MethodDescriptor();
            checkPermission.name = "checkPermission";
            checkPermission.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(checkPermission);
            MethodDescriptor shouldShowRequestPermissionRationale = new MethodDescriptor();
            shouldShowRequestPermissionRationale.name = "shouldShowRequestPermissionRationale";
            shouldShowRequestPermissionRationale.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(shouldShowRequestPermissionRationale);
            MethodDescriptor requestPermission = new MethodDescriptor();
            requestPermission.name = "requestPermission";
            requestPermission.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(requestPermission);
            MethodDescriptor requestMultiplePermissions = new MethodDescriptor();
            requestMultiplePermissions.name = "requestMultiplePermissions";
            requestMultiplePermissions.type = BaseJavaModule.METHOD_TYPE_PROMISE;
            list.add(requestMultiplePermissions);
            return list;
        }

        public void invoke(p catalystInstance, int methodId, ReadableNativeArray parameters) {
            switch (methodId) {
                case 0:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("PermissionsModule.checkPermission got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.checkPermission(parameters.getString(0), createPromise(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "checkPermission");
                        return;
                    } catch (Exception e) {
                        throw new z("PermissionsModule.checkPermission got " + parameters.toString(), e);
                    }
                case 1:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("PermissionsModule.shouldShowRequestPermissionRationale got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.shouldShowRequestPermissionRationale(parameters.getString(0), createPromise(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "shouldShowRequestPermissionRationale");
                        return;
                    } catch (Exception e2) {
                        throw new z("PermissionsModule.shouldShowRequestPermissionRationale got " + parameters.toString(), e2);
                    }
                case 2:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("PermissionsModule.requestPermission got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.requestPermission(parameters.getString(0), createPromise(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "requestPermission");
                        return;
                    } catch (Exception e22) {
                        throw new z("PermissionsModule.requestPermission got " + parameters.toString(), e22);
                    }
                case 3:
                    this.mWatchdog.b();
                    if (3 != parameters.size()) {
                        throw new z("PermissionsModule.requestMultiplePermissions got " + parameters.toString() + "arguments, expected 3");
                    }
                    try {
                        this.mNativeModule.requestMultiplePermissions(parameters.getArray(0), createPromise(catalystInstance, 1, parameters));
                        this.mWatchdog.a(MODULE_NAME, "requestMultiplePermissions");
                        return;
                    } catch (Exception e222) {
                        throw new z("PermissionsModule.requestMultiplePermissions got " + parameters.toString(), e222);
                    }
                default:
                    return;
            }
        }
    }

    interface d {
        a getModuleHelper(BaseJavaModule baseJavaModule);
    }

    @DoNotStrip
    class CoreModuleProvider implements d {
        CoreModuleProvider() {
        }

        public a getModuleHelper(BaseJavaModule module) {
            if (module instanceof StatusBarModule) {
                return new AnonymousClass0((StatusBarModule) module);
            }
            if (module instanceof ExceptionsManagerModule) {
                return new AnonymousClass1((ExceptionsManagerModule) module);
            }
            if (module instanceof JSCSamplingProfiler) {
                return new AnonymousClass2((JSCSamplingProfiler) module);
            }
            if (module instanceof DebugComponentOwnershipModule) {
                return new AnonymousClass3((DebugComponentOwnershipModule) module);
            }
            if (module instanceof LocationModule) {
                return new AnonymousClass4((LocationModule) module);
            }
            if (module instanceof AppStateModule) {
                return new AnonymousClass5((AppStateModule) module);
            }
            if (module instanceof Timing) {
                return new AnonymousClass6((Timing) module);
            }
            if (module instanceof ImageEditingManager) {
                return new AnonymousClass7((ImageEditingManager) module);
            }
            if (module instanceof VibrationModule) {
                return new AnonymousClass8((VibrationModule) module);
            }
            if (module instanceof PermissionsModule) {
                return new AnonymousClass9((PermissionsModule) module);
            }
            if (module instanceof DeviceEventManagerModule) {
                return new AnonymousClass10((DeviceEventManagerModule) module);
            }
            if (module instanceof AsyncStorageModule) {
                return new AnonymousClass11((AsyncStorageModule) module);
            }
            if (module instanceof ClipboardModule) {
                return new AnonymousClass12((ClipboardModule) module);
            }
            if (module instanceof HeadlessJsTaskSupportModule) {
                return new AnonymousClass13((HeadlessJsTaskSupportModule) module);
            }
            if (module instanceof DialogModule) {
                return new AnonymousClass14((DialogModule) module);
            }
            if (module instanceof IntentModule) {
                return new AnonymousClass15((IntentModule) module);
            }
            if (module instanceof AccessibilityInfoModule) {
                return new AnonymousClass16((AccessibilityInfoModule) module);
            }
            if (module instanceof NativeAnimatedModule) {
                return new AnonymousClass17((NativeAnimatedModule) module);
            }
            if (module instanceof WebSocketModule) {
                return new AnonymousClass18((WebSocketModule) module);
            }
            if (module instanceof TimePickerDialogModule) {
                return new AnonymousClass19((TimePickerDialogModule) module);
            }
            if (module instanceof ImageStoreManager) {
                return new AnonymousClass20((ImageStoreManager) module);
            }
            if (module instanceof DatePickerDialogModule) {
                return new AnonymousClass21((DatePickerDialogModule) module);
            }
            if (module instanceof CameraRollManager) {
                return new AnonymousClass22((CameraRollManager) module);
            }
            if (module instanceof NetInfoModule) {
                return new AnonymousClass23((NetInfoModule) module);
            }
            if (module instanceof AnimationsDebugModule) {
                return new AnonymousClass24((AnimationsDebugModule) module);
            }
            if (module instanceof NetworkingModule) {
                return new AnonymousClass25((NetworkingModule) module);
            }
            if (module instanceof I18nManagerModule) {
                return new AnonymousClass26((I18nManagerModule) module);
            }
            if (module instanceof ImageLoaderModule) {
                return new AnonymousClass27((ImageLoaderModule) module);
            }
            if (module instanceof ShareModule) {
                return new AnonymousClass28((ShareModule) module);
            }
            if (module instanceof JSCHeapCapture) {
                return new AnonymousClass29((JSCHeapCapture) module);
            }
            if (module instanceof ToastModule) {
                return new AnonymousClass30((ToastModule) module);
            }
            if (module instanceof UIManagerModule) {
                return new AnonymousClass31((UIManagerModule) module);
            }
            return null;
        }
    }

    @DoNotStrip
    public static class MethodDescriptor {
        @DoNotStrip
        Method method;
        @DoNotStrip
        String name;
        @DoNotStrip
        String signature;
        @DoNotStrip
        String type;
    }

    static class b extends a {
        private JavaModuleWrapper a;
        private final ArrayList<com.facebook.react.bridge.NativeModule.a> b = new ArrayList();
        private final ArrayList<MethodDescriptor> c = new ArrayList();

        public b(JavaModuleWrapper module) {
            this.a = module;
        }

        @DoNotStrip
        private void findMethods() {
            com.facebook.systrace.a.a("findMethods");
            Set<String> methodNames = new HashSet();
            Class<? extends NativeModule> classForMethods = this.a.getModule().getClass();
            Class<? extends NativeModule> superClass = classForMethods.getSuperclass();
            if (ReactModuleWithSpec.class.isAssignableFrom(superClass)) {
                classForMethods = superClass;
            }
            for (Method targetMethod : classForMethods.getDeclaredMethods()) {
                ReactMethod annotation = (ReactMethod) targetMethod.getAnnotation(ReactMethod.class);
                if (annotation != null) {
                    String methodName = targetMethod.getName();
                    if (methodNames.contains(methodName)) {
                        throw new IllegalArgumentException("Java Module " + this.a.getName() + " method name already registered: " + methodName);
                    }
                    MethodDescriptor md = new MethodDescriptor();
                    q method = new q(this.a, targetMethod, annotation.isBlockingSynchronousMethod());
                    md.name = methodName;
                    md.type = method.b();
                    if (md.type == BaseJavaModule.METHOD_TYPE_SYNC) {
                        md.signature = method.a();
                        md.method = targetMethod;
                    }
                    this.b.add(method);
                    this.c.add(md);
                }
            }
            com.facebook.systrace.a.a();
        }

        public final List<MethodDescriptor> getMethodDescriptors() {
            if (this.c.isEmpty()) {
                findMethods();
            }
            return this.c;
        }

        public final void invoke(p jsInstance, int methodId, ReadableNativeArray parameters) {
            if (this.b != null && methodId < this.b.size()) {
                ((com.facebook.react.bridge.NativeModule.a) this.b.get(methodId)).a(jsInstance, parameters);
            }
        }
    }

    static {
        String clsName = JavaModuleWrapper.class.getName();
        try {
            sCoreModuleProvider = (d) Class.forName(clsName + "$CoreModuleProvider").newInstance();
        } catch (ClassNotFoundException e) {
            FLog.w("JavaModuleHelper", "Could not find generated core module provider");
        } catch (InstantiationException e2) {
            throw new RuntimeException("Unable to instantiate core module provider ", e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("Unable to instantiate core module provider ", e3);
        }
        try {
            sCustomModuleProvider = (d) Class.forName(clsName + "$CustomModuleProvider").newInstance();
        } catch (ClassNotFoundException e4) {
            FLog.w("JavaModuleHelper", "Could not find generated custom module provider");
        } catch (InstantiationException e22) {
            throw new RuntimeException("Unable to instantiate custom module provider ", e22);
        } catch (IllegalAccessException e32) {
            throw new RuntimeException("Unable to instantiate custom module provider ", e32);
        }
    }

    public JavaModuleWrapper(p jsInstance, ModuleHolder moduleHolder) {
        this.mJSInstance = jsInstance;
        this.mModuleHolder = moduleHolder;
        a helper = null;
        if (sCoreModuleProvider != null) {
            helper = sCoreModuleProvider.getModuleHelper((BaseJavaModule) moduleHolder.getModule());
        }
        if (helper == null && sCustomModuleProvider != null) {
            helper = sCustomModuleProvider.getModuleHelper((BaseJavaModule) moduleHolder.getModule());
        }
        if (helper == null) {
            FLog.w("React", "There is no generated module helper for module: " + getName() + " using FallbackModuleHelper which works way slower. Consider enabling apt generation for this module in order to improve startup performance");
            helper = new b(this);
        }
        this.mModuleHelper = helper;
    }

    @DoNotStrip
    public BaseJavaModule getModule() {
        return (BaseJavaModule) this.mModuleHolder.getModule();
    }

    @DoNotStrip
    public String getName() {
        return this.mModuleHolder.getName();
    }

    @DoNotStrip
    public List<MethodDescriptor> getMethodDescriptors() {
        return this.mModuleHelper.getMethodDescriptors();
    }

    @DoNotStrip
    @Nullable
    public NativeArray getConstants() {
        if (!this.mModuleHolder.getHasConstants()) {
            return null;
        }
        String moduleName = getName();
        com.facebook.systrace.b.a();
        ReactMarker.logMarker(aj.GET_CONSTANTS_START, moduleName);
        BaseJavaModule baseJavaModule = getModule();
        com.facebook.systrace.a.a("module.getConstants");
        Map map = baseJavaModule.getConstants();
        com.facebook.systrace.a.a();
        com.facebook.systrace.a.a("create WritableNativeMap");
        ReactMarker.logMarker(aj.CONVERT_CONSTANTS_START, moduleName);
        try {
            NativeArray array = new WritableNativeArray();
            array.pushMap(b.a(map));
            return array;
        } finally {
            ReactMarker.logMarker(aj.CONVERT_CONSTANTS_END);
            com.facebook.systrace.a.a();
            ReactMarker.logMarker(aj.GET_CONSTANTS_END);
            com.facebook.systrace.b.b();
        }
    }

    @DoNotStrip
    public void invoke(int methodId, ReadableNativeArray parameters) {
        this.mModuleHelper.invoke(this.mJSInstance, methodId, parameters);
    }
}
