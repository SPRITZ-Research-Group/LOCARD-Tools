package com.skype.android.jipc.omx.enums;

import com.skype.android.jipc.Enumerable;

public interface OmxCore {

    public enum BufferUsageType {
        kBufferUsageTypeAllocate,
        kBufferUsageTypeUse
    }

    public enum Command implements Enumerable {
        OMX_CommandStateSet,
        OMX_CommandFlush,
        OMX_CommandPortDisable,
        OMX_CommandPortEnable,
        OMX_CommandMarkBuffer;

        public final int a() {
            return ordinal();
        }
    }

    public enum Error implements Enumerable {
        OMX_ErrorNone(0),
        OMX_ErrorInsufficientResources(-2147479552),
        OMX_ErrorUndefined(-2147479551),
        OMX_ErrorInvalidComponentName(-2147479550),
        OMX_ErrorComponentNotFound(-2147479549),
        OMX_ErrorInvalidComponent(-2147479548),
        OMX_ErrorBadParameter(-2147479547),
        OMX_ErrorNotImplemented(-2147479546),
        OMX_ErrorUnderflow(-2147479545),
        OMX_ErrorOverflow(-2147479544),
        OMX_ErrorHardware(-2147479543),
        OMX_ErrorInvalidState(-2147479542),
        OMX_ErrorStreamCorrupt(-2147479541),
        OMX_ErrorPortsNotCompatible(-2147479540),
        OMX_ErrorResourcesLost(-2147479539),
        OMX_ErrorNoMore(-2147479538),
        OMX_ErrorVersionMismatch(-2147479537),
        OMX_ErrorNotReady(-2147479536),
        OMX_ErrorTimeout(-2147479535),
        OMX_ErrorSameState(-2147479534),
        OMX_ErrorResourcesPreempted(-2147479533),
        OMX_ErrorPortUnresponsiveDuringAllocation(-2147479532),
        OMX_ErrorPortUnresponsiveDuringDeallocation(-2147479531),
        OMX_ErrorPortUnresponsiveDuringStop(-2147479530),
        OMX_ErrorIncorrectStateTransition(-2147479529),
        OMX_ErrorIncorrectStateOperation(-2147479528),
        OMX_ErrorUnsupportedSetting(-2147479527),
        OMX_ErrorUnsupportedIndex(-2147479526),
        OMX_ErrorBadPortIndex(-2147479525),
        OMX_ErrorPortUnpopulated(-2147479524),
        OMX_ErrorComponentSuspended(-2147479523),
        OMX_ErrorDynamicResourcesUnavailable(-2147479522),
        OMX_ErrorMbErrorsInFrame(-2147479521),
        OMX_ErrorFormatNotDetected(-2147479520),
        OMX_ErrorContentPipeOpenFailed(-2147479519),
        OMX_ErrorContentPipeCreationFailed(-2147479518),
        OMX_ErrorSeperateTablesUsed(-2147479517),
        OMX_ErrorTunnelingUnsupported(-2147479516),
        OMX_ErrorMax(Integer.MAX_VALUE);
        
        public final int N;

        public final int a() {
            return this.N;
        }

        private Error(int value) {
            this.N = value;
        }
    }

    public interface Port {
    }

    public enum State implements Enumerable {
        OMX_StateInvalid,
        OMX_StateLoaded,
        OMX_StateIdle,
        OMX_StateExecuting,
        OMX_StatePause,
        OMX_StateWaitForResources;

        public final int a() {
            return ordinal();
        }
    }
}
