package com.skype.android.video.hw.codec;

import android.os.IBinder;
import com.skype.android.jipc.Enumerable;
import com.skype.android.jipc.Struct.IntField;
import com.skype.android.jipc.inout.OutBoolean;
import com.skype.android.jipc.inout.OutInt32;
import com.skype.android.jipc.inout.OutMayBe;
import com.skype.android.jipc.inout.OutStatus;
import com.skype.android.jipc.omx.OmxFacade;
import com.skype.android.jipc.omx.OmxInventory;
import com.skype.android.jipc.omx.OmxObserver;
import com.skype.android.jipc.omx.data.ExtendedIndex;
import com.skype.android.jipc.omx.data.OmxStruct;
import com.skype.android.jipc.omx.data.SkypeExtendedIndex;
import com.skype.android.jipc.omx.data.config.BaseLayerPidConfig;
import com.skype.android.jipc.omx.data.config.FrameRateConfig;
import com.skype.android.jipc.omx.data.config.MarkLtrFrameConfig;
import com.skype.android.jipc.omx.data.config.QpConfig;
import com.skype.android.jipc.omx.data.config.TemporalLayerCountConfig;
import com.skype.android.jipc.omx.data.config.UseLtrFrameConfig;
import com.skype.android.jipc.omx.data.embedded.EncoderCap;
import com.skype.android.jipc.omx.data.embedded.EncoderSetting;
import com.skype.android.jipc.omx.data.param.AVCParam;
import com.skype.android.jipc.omx.data.param.BitrateParam;
import com.skype.android.jipc.omx.data.param.DecoderCapParam;
import com.skype.android.jipc.omx.data.param.DecoderSettingParam;
import com.skype.android.jipc.omx.data.param.DriverVerParam;
import com.skype.android.jipc.omx.data.param.EncoderCapParam;
import com.skype.android.jipc.omx.data.param.EncoderSettingParam;
import com.skype.android.jipc.omx.data.param.PortDefinitionParam;
import com.skype.android.jipc.omx.data.param.VideoPortFormatParam;
import com.skype.android.jipc.omx.enums.OmxVideo.ANDROID_RC;
import com.skype.android.jipc.omx.enums.OmxVideo.OMX_COLOR_FORMATTYPE;
import com.skype.android.jipc.omx.enums.OmxVideo.OMX_VIDEO_AVCLOOPFILTERTYPE;
import com.skype.android.jipc.omx.enums.OmxVideo.OMX_VIDEO_AVCPROFILETYPE;
import com.skype.android.jipc.omx.enums.OmxVideo.OMX_VIDEO_CODINGTYPE;
import com.skype.android.jipc.omx.enums.OmxVideo.OMX_VIDEO_CONTROLRATETYPE;
import com.skype.android.jipc.omx.enums.OmxVideo.OMX_VIDEO_EXTENSION_AVCPROFILETYPE;
import com.skype.android.jipc.omx.enums.OmxVideo.OMX_VIDEO_HierarType;
import com.skype.android.jipc.omx.enums.OmxVideo.OMX_VIDEO_SliceControlMode;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.HWFeatureSelectiveFields;
import com.skype.android.video.hw.utils.CodecUtils;
import com.skype.android.video.hw.utils.Log;
import java.io.IOException;
import java.nio.Buffer;

public class IpcOmxWrapper extends AbstractOmxWrapper {
    static final OutStatus UNKNOWN_ERROR;
    static final boolean checkConfigWritten = true;
    private boolean hasEncCapability;
    private final OmxObserver observer = OmxInventory.b();
    private final IBinder omx = facade().a();
    private int ownNodeId = -1;
    private final ThreadLocal<Locals> tls = new ThreadLocal<Locals>() {
        protected Locals initialValue() {
            return new Locals(IpcOmxWrapper.this.facade().b());
        }
    };

    private static class Locals {
        final AVCParam avcParam = new AVCParam();
        final BitrateParam bitrateParam = new BitrateParam();
        final BaseLayerPidConfig blpConfig = new BaseLayerPidConfig(this.blpIndex);
        final ExtendedIndex blpIndex = new SkypeExtendedIndex("OMX.microsoft.skype.index.basepid");
        final OutBoolean check = new OutBoolean();
        final OutMayBe<Void, OutBoolean> checkResult;
        final ExtendedIndex decCapIndex = new SkypeExtendedIndex("OMX.microsoft.skype.index.decodercapability");
        final DecoderCapParam decCapParam = new DecoderCapParam(this.decCapIndex);
        final ExtendedIndex decSetIndex = new SkypeExtendedIndex("OMX.microsoft.skype.index.decodersetting");
        final DriverVerParam driverVerParam = new DriverVerParam(this.drvVerIndex);
        final ExtendedIndex drvVerIndex = new SkypeExtendedIndex("OMX.microsoft.skype.index.driverversion");
        final DecoderSettingParam dsCheck = new DecoderSettingParam(this.decSetIndex);
        final DecoderSettingParam dsParam = new DecoderSettingParam(this.decSetIndex);
        final ExtendedIndex encCapIndex = new SkypeExtendedIndex("OMX.microsoft.skype.index.encodercapability");
        final EncoderCapParam encCapParam = new EncoderCapParam(this.encCapIndex);
        final ExtendedIndex encSetIndex = new SkypeExtendedIndex("OMX.microsoft.skype.index.encodersetting");
        final EncoderSettingParam esCheck = new EncoderSettingParam(this.encSetIndex);
        final EncoderSettingParam esParam = new EncoderSettingParam(this.encSetIndex);
        final ExtendedIndex lcConfig = new SkypeExtendedIndex("OMX.microsoft.skype.index.temporallayercount");
        final MarkLtrFrameConfig markLtr = new MarkLtrFrameConfig(this.markLtrIndex);
        final ExtendedIndex markLtrIndex = new SkypeExtendedIndex("OMX.microsoft.skype.index.markltrframe");
        final TemporalLayerCountConfig numTempLayers = new TemporalLayerCountConfig(this.lcConfig);
        final PortDefinitionParam pdParam = new PortDefinitionParam();
        final VideoPortFormatParam pfParam = new VideoPortFormatParam();
        final ExtendedIndex qParamIndex = new SkypeExtendedIndex("OMX.microsoft.skype.index.qp");
        final QpConfig qpConfig = new QpConfig(this.qParamIndex);
        final FrameRateConfig rcConfig = new FrameRateConfig();
        final OutStatus status;
        final UseLtrFrameConfig useLtr = new UseLtrFrameConfig(this.useLtrIndex);
        final ExtendedIndex useLtrIndex = new SkypeExtendedIndex("OMX.microsoft.skype.index.useltrframe");

        Locals(OutStatus status) {
            this.status = status;
            this.checkResult = new OutMayBe(this.status, this.check);
        }
    }

    static {
        OutStatus outStatus = new OutStatus();
        UNKNOWN_ERROR = outStatus;
        outStatus.a(8388608);
    }

    private OmxFacade facade() {
        return OmxInventory.a();
    }

    private Locals locals() {
        return (Locals) this.tls.get();
    }

    public IpcOmxWrapper(String codecName, String dummyComponentName) {
        super(codecName, dummyComponentName);
    }

    protected int incrementAndGetNodeId() {
        int nodeId = allocateNode();
        if (nodeId != -1) {
            facade().a(this.omx, nodeId, this.observer);
        }
        return nodeId;
    }

    public int connectForQueriesOnly() {
        if (isNodeIdKnown()) {
            throw new IllegalStateException("Node already owned!");
        }
        int allocateNode = allocateNode();
        this.ownNodeId = allocateNode;
        return allocateNode;
    }

    private int allocateNode() {
        OutMayBe<Void, OutInt32> result = facade().a(this.omx, this.observer, this.dummyName);
        return OutStatus.c(result.a.b()) ? ((OutInt32) result.b).b() : -1;
    }

    protected int doSetQp(int nodeId, int qp) {
        QpConfig qpConfig = locals().qpConfig;
        qpConfig.g.a(1);
        qpConfig.h.a(qp);
        return facade().c(this.omx, nodeId, qpConfig).b();
    }

    protected int doSetNumTempLayers(int nodeId, int numTempLayers) {
        TemporalLayerCountConfig tlc = locals().numTempLayers;
        tlc.g.a(1);
        tlc.h.a(numTempLayers);
        return facade().c(this.omx, nodeId, tlc).b();
    }

    protected int doSetBaseLayerPID(int nodeId, int baseLayerPID) {
        BaseLayerPidConfig blp = locals().blpConfig;
        blp.g.a(1);
        blp.h.a(baseLayerPID);
        return facade().c(this.omx, nodeId, blp).b();
    }

    protected int doMarkLtrFrame(int nodeId, int longTermFrameIdx) {
        MarkLtrFrameConfig blp = locals().markLtr;
        blp.g.a(1);
        blp.h.a(longTermFrameIdx);
        return facade().c(this.omx, nodeId, blp).b();
    }

    protected int doUseLTRFrame(int nodeId, int useLTRFrameIdxBitMap) {
        UseLtrFrameConfig blp = locals().useLtr;
        blp.g.a(1);
        blp.h.a((short) useLTRFrameIdxBitMap);
        return facade().c(this.omx, nodeId, blp).b();
    }

    public int setRcFrameRate(float fps) {
        FrameRateConfig rc = locals().rcConfig;
        rc.g.a(0);
        rc.h.a((int) (65536.0f * fps));
        return facade().c(this.omx, getNodeId(), rc).b();
    }

    public int configureDecoderLowLatency() {
        DecoderSettingParam setting = locals().dsParam;
        setting.g.a(0);
        setting.h.a((boolean) checkConfigWritten);
        return setAndGetOmxParam(getNodeId(), setting, locals().dsCheck).a.b();
    }

    private <P extends OmxStruct> OutMayBe<Void, OutBoolean> setAndGetOmxParam(int nodeId, P value, P check) {
        OutMayBe<Void, OutBoolean> checkResult = locals().checkResult;
        OutStatus status = facade().b(this.omx, nodeId, value);
        if (OutStatus.c(status.b())) {
            check.b();
            facade().a(this.omx, nodeId, (OmxStruct) check);
            if (OutStatus.c(status.b())) {
                int i;
                boolean valid = value.equals(check);
                OutBoolean outBoolean = (OutBoolean) checkResult.b;
                if (valid) {
                    i = 1;
                } else {
                    i = 0;
                }
                outBoolean.a(i);
                if (valid) {
                    info("SUCCESS: getParameter return OK, and setting did take effect");
                } else {
                    info("FAIL: getParameter return OK, but setting did not take effect");
                }
            } else {
                info("FAIL: getParameter returned " + status);
            }
        } else {
            info("FAIL: setParameter returned " + status);
        }
        return checkResult;
    }

    private static void info(String log) {
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, log);
        }
    }

    private static void warn(String log) {
        if (Log.isLoggable(Commons.TAG, 5)) {
            Log.w(Commons.TAG, log);
        }
    }

    private static void error(String log) {
        if (Log.isLoggable(Commons.TAG, 6)) {
            Log.e(Commons.TAG, log);
        }
    }

    OutStatus configureVideoPortFormat(int nodeId, String codecName, int port, int colorFormat, OMX_VIDEO_CODINGTYPE compressionFormat) {
        OmxStruct format = locals().pfParam;
        format.h.a(0);
        format.g.a(port);
        boolean foundFormat = false;
        info(String.format("nodeId %d, codecName %s", new Object[]{Integer.valueOf(nodeId), codecName}));
        int i = 0;
        while (!foundFormat && i <= 100) {
            format.h.a(i);
            if (OutStatus.c(facade().a(this.omx, nodeId, format).b())) {
                info(String.format("colorFormat %d, format.eColorFormat %d, compressionFormat %s, format.eCompressionFormat %d", new Object[]{Integer.valueOf(colorFormat), Integer.valueOf(format.j.a()), compressionFormat, Integer.valueOf(format.i.a())}));
                if ("OMX.TI.Video.encoder".equals(codecName)) {
                    if (colorFormat == format.j.a() && port == 0) {
                        foundFormat = checkConfigWritten;
                    } else if (format.i.a() == compressionFormat.a() && 1 == port) {
                        foundFormat = checkConfigWritten;
                    }
                } else if (colorFormat == format.j.a() && compressionFormat.a() == format.i.a()) {
                    foundFormat = checkConfigWritten;
                }
                i++;
            } else {
                warn(String.format("Error %s while querying OMX_IndexParamVideoPortFormat", new Object[]{facade().a(this.omx, nodeId, format)}));
                return UNKNOWN_ERROR;
            }
        }
        if (foundFormat) {
            return facade().b(this.omx, nodeId, format);
        }
        warn(String.format("Encoder doesn't support colorFormat %d, compressionFormat %s", new Object[]{Integer.valueOf(colorFormat), compressionFormat}));
        return UNKNOWN_ERROR;
    }

    OutStatus configurePort(int nodeId, int portId, int width, int height, float frameRate, int colorFormat, OMX_VIDEO_CODINGTYPE compressionFormat) {
        OmxStruct portDefinition = locals().pdParam;
        portDefinition.g.a(portId);
        OutStatus status = facade().a(this.omx, nodeId, portDefinition);
        if (OutStatus.c(status.b())) {
            portDefinition.o.a.a(width);
            portDefinition.o.b.a(height);
            portDefinition.o.c.a(width);
            portDefinition.o.d.a(height);
            portDefinition.o.f.a(portId == 0 ? (int) (65536.0f * frameRate) : 0);
            portDefinition.o.h.a((Enumerable) compressionFormat);
            portDefinition.o.i.a(colorFormat);
            if (portId == 0) {
                portDefinition.k.a(((portDefinition.o.c.a() * portDefinition.o.d.a()) * 3) / 2);
            }
            status = facade().b(this.omx, nodeId, portDefinition);
        }
        if (!OutStatus.c(status.b())) {
            String str = "configurePort (%s) failed! Error %s";
            Object[] objArr = new Object[2];
            objArr[0] = portId == 0 ? "input" : "output";
            objArr[1] = status;
            error(String.format(str, objArr));
        }
        return status;
    }

    OutStatus setH264CodingTools(int nodeId, int profile, int level, float frameRate, int iFrameInterval, boolean useCabac, boolean useWeightedPrediction, int sliceSize) {
        OmxStruct configH264 = locals().avcParam;
        configH264.g.a(1);
        OutStatus status = facade().a(this.omx, nodeId, configH264);
        if (!OutStatus.c(status.b())) {
            return status;
        }
        int pFrames = iFrameInterval < 0 ? -1 : iFrameInterval == 0 ? 0 : (int) (((float) iFrameInterval) * frameRate);
        configH264.h.a(sliceSize);
        configH264.k.a((boolean) checkConfigWritten);
        configH264.l.a(1);
        configH264.j.a(0);
        configH264.i.a(pFrames);
        configH264.m.a(0);
        configH264.n.a(0);
        configH264.x.a(useCabac);
        configH264.y.a(useWeightedPrediction);
        configH264.A.a(false);
        configH264.B.a(false);
        configH264.C.a(false);
        configH264.D.a(0);
        configH264.o.a(false);
        configH264.p.a(false);
        configH264.q.a(false);
        configH264.r.a(false);
        configH264.v.a((boolean) checkConfigWritten);
        configH264.w.a(false);
        configH264.E.a(OMX_VIDEO_AVCLOOPFILTERTYPE.OMX_VIDEO_AVCLoopFilterEnable);
        configH264.u.a(configH264.i.a() == 0 ? 1 : 3);
        configH264.s.a(profile);
        configH264.t.a(level);
        return facade().b(this.omx, nodeId, configH264);
    }

    OutStatus setEncodingMode(int nodeId, int refCount, int profile, int numTempLayers, int baseLayerPriorityId, OMX_VIDEO_SliceControlMode sliceControlMode, boolean svcAlways) {
        int nLtrFrames = 0;
        if (this.hasEncCapability) {
            EncoderCapParam encCapability = locals().encCapParam;
            int maxLtrFramesCap = encCapability.h.g.a();
            if (encCapability.h.f.a() > maxLtrFramesCap && maxLtrFramesCap > 0) {
                nLtrFrames = CodecUtils.clip(maxLtrFramesCap, 0, refCount - 1);
            }
        }
        EncoderSettingParam encSetting = locals().esParam;
        EncoderSettingParam encSettingCheck = locals().esCheck;
        EncoderSetting param = encSetting.h;
        EncoderSetting paramCheck = encSettingCheck.h;
        param.b.a(false);
        if (profile == OMX_VIDEO_AVCPROFILETYPE.OMX_VIDEO_AVCProfileBaseline.a()) {
            param.b.a((boolean) checkConfigWritten);
            param.d.a(OMX_VIDEO_EXTENSION_AVCPROFILETYPE.OMX_VIDEO_EXT_AVCProfileConstrainedBaseline);
        }
        if (profile == OMX_VIDEO_AVCPROFILETYPE.OMX_VIDEO_AVCProfileHigh.a()) {
            param.b.a((boolean) checkConfigWritten);
            param.d.a(OMX_VIDEO_EXTENSION_AVCPROFILETYPE.OMX_VIDEO_EXT_AVCProfileConstrainedHigh);
        }
        param.a.a((boolean) checkConfigWritten);
        param.e.a(nLtrFrames);
        param.h.a((Enumerable) sliceControlMode);
        param.i.a(0);
        param.j.a(0);
        param.k.a(0);
        IntField intField = param.g;
        int i = numTempLayers > 1 ? numTempLayers : svcAlways ? 2 : 0;
        intField.a(i);
        if (numTempLayers > 1 || svcAlways) {
            param.f.a(OMX_VIDEO_HierarType.OMX_VIDEO_HierarType_P);
        }
        OutMayBe<Void, OutBoolean> result = setAndGetOmxParam(nodeId, encSetting, encSetting);
        OutStatus status = result.a;
        if (!((OutBoolean) result.b).a()) {
            info(String.format("Tried to set: \nbLowLatency %d, bUseExtendedProfile %d, eProfile %d, nLTRFrames %d, eSliceControlMode %d, nSarIndex %d, nSarWidth %d, nSarHight %d, nMaxTemporalLayerCount %d, eHierarType %d, bSequenceHeaderWithIDR %d", new Object[]{Integer.valueOf(param.a.a()), Integer.valueOf(param.b.a()), Integer.valueOf(param.d.a()), Integer.valueOf(param.e.a()), Integer.valueOf(param.h.a()), Integer.valueOf(param.i.a()), Integer.valueOf(param.j.a()), Integer.valueOf(param.k.a()), Integer.valueOf(param.g.a()), Integer.valueOf(param.f.a()), Integer.valueOf(param.c.a())}));
        }
        info(String.format("setting return code: %s | nSize %d, nVersion %d, nPortIndex %d \nbLowLatency %d, bUseExtendedProfile %d, eProfile %d, nLTRFrames %d, eSliceControlMode %d, nSarIndex %d, nSarWidth %d, nSarHight %d, nMaxTemporalLayerCount %d, eHierarType %d, bSequenceHeaderWithIDR %d", new Object[]{status, Integer.valueOf(encSetting.e.a()), Integer.valueOf(encSetting.f.a()), Integer.valueOf(encSetting.g.a()), Integer.valueOf(paramCheck.a.a()), Integer.valueOf(paramCheck.b.a()), Integer.valueOf(paramCheck.d.a()), Integer.valueOf(paramCheck.e.a()), Integer.valueOf(paramCheck.h.a()), Integer.valueOf(paramCheck.i.a()), Integer.valueOf(paramCheck.j.a()), Integer.valueOf(paramCheck.k.a()), Integer.valueOf(paramCheck.g.a()), Integer.valueOf(paramCheck.f.a()), Integer.valueOf(paramCheck.c.a())}));
        if (numTempLayers > 1 || svcAlways) {
            setTemporalLayout(nodeId, numTempLayers, baseLayerPriorityId);
        }
        return status;
    }

    OutStatus setTemporalLayout(int nodeId, int numTempLayers, int baseLayerPriorityId) {
        info(String.format("setTemporalLayout(totalLayers %d, baseLayerPID %d)", new Object[]{Integer.valueOf(numTempLayers), Integer.valueOf(baseLayerPriorityId)}));
        OutStatus refStatus = locals().status;
        refStatus.a(0);
        doSetNumTempLayers(nodeId, numTempLayers);
        if (OutStatus.c(refStatus.b())) {
            doSetBaseLayerPID(nodeId, baseLayerPriorityId);
        }
        return refStatus;
    }

    private OutStatus setBitrateAndRcMode(int nodeId, int bitrate, OMX_VIDEO_CONTROLRATETYPE bitrateMode) {
        OmxStruct bitrateType = locals().bitrateParam;
        bitrateType.g.a(1);
        OutStatus status = facade().a(this.omx, nodeId, bitrateType);
        if (!OutStatus.c(status.b())) {
            return status;
        }
        bitrateType.h.a((Enumerable) bitrateMode);
        bitrateType.i.a(bitrate);
        return facade().b(this.omx, nodeId, bitrateType);
    }

    protected int configureEncoder(int rcMode, int refCount, int numChannels, int baseLayerPriorityId, int minNumSlices, boolean svcAlways, int colorFormat, int width, int height, int bitrate, int frameRate, int iFrameInterval, int profile, int level, int hwMode) {
        int sliceSize;
        OutStatus status = locals().status;
        status.a(0);
        int nodeId = getNodeId();
        boolean useCabac = profile == OMX_VIDEO_AVCPROFILETYPE.OMX_VIDEO_AVCProfileHigh.a() ? checkConfigWritten : false;
        boolean hwModeSkypeExtension = HWFeatureSelectiveFields.Skype_OMX_Extension.isSet(hwMode);
        if (hwModeSkypeExtension) {
            getEncCapabilityBuffer();
            if (!this.hasEncCapability) {
                status.a(0);
            }
        }
        OMX_VIDEO_CONTROLRATETYPE controlRateType = OMX_VIDEO_CONTROLRATETYPE.OMX_Video_ControlRateConstantSkipFrames;
        if (rcMode == ANDROID_RC.AR_AUTO.a() || rcMode == ANDROID_RC.AR_SLIQ.a()) {
            if (!hwModeSkypeExtension) {
                controlRateType = OMX_VIDEO_CONTROLRATETYPE.OMX_Video_ControlRateConstant;
                info("Generic RC, set OMX_Video_ControlRateConstant");
            } else if (locals().drvVerIndex.b() == nodeId && isQpSupported()) {
                controlRateType = OMX_VIDEO_CONTROLRATETYPE.OMX_Video_ControlRateDisable;
                info("Sliq RC, set OMX_Video_ControlRateDisable");
            } else {
                controlRateType = OMX_VIDEO_CONTROLRATETYPE.OMX_Video_ControlRateConstant;
                info("Generic RC, set OMX_Video_ControlRateConstant");
            }
        } else if (rcMode == ANDROID_RC.AR_INTERNAL.a()) {
            controlRateType = OMX_VIDEO_CONTROLRATETYPE.OMX_Video_ControlRateConstantSkipFrames;
            info("HW internal RC, set OMX_Video_ControlRateConstantSkipFrames");
        } else if (rcMode == ANDROID_RC.AR_GENERIC.a()) {
            controlRateType = OMX_VIDEO_CONTROLRATETYPE.OMX_Video_ControlRateConstant;
            info("Generic RC, set OMX_Video_ControlRateConstant");
        } else {
            warn("invalid RC type");
        }
        minNumSlices = CodecUtils.clip(minNumSlices, 1, 15);
        EncoderCap capability = this.hasEncCapability ? locals().encCapParam.h : null;
        OMX_VIDEO_SliceControlMode sliceControlMode = (minNumSlices <= 1 || capability == null || (capability.i.a() & OMX_VIDEO_SliceControlMode.OMX_VIDEO_SliceControlModeMB.a()) == 0) ? OMX_VIDEO_SliceControlMode.OMX_VIDEO_SliceControlModeNone : OMX_VIDEO_SliceControlMode.OMX_VIDEO_SliceControlModeMB;
        if (minNumSlices <= 1 || sliceControlMode != OMX_VIDEO_SliceControlMode.OMX_VIDEO_SliceControlModeMB) {
            sliceSize = 0;
        } else {
            sliceSize = (((((width + 15) >> 4) * ((height + 15) >> 4)) + minNumSlices) - 1) / minNumSlices;
        }
        if (OutStatus.c(status.b())) {
            status = configureVideoPortFormat(nodeId, this.hwCodecName, 0, colorFormat, OMX_VIDEO_CODINGTYPE.OMX_VIDEO_CodingUnused);
            if (OutStatus.c(status.b())) {
                status = configurePort(nodeId, 0, width, height, (float) frameRate, colorFormat, OMX_VIDEO_CODINGTYPE.OMX_VIDEO_CodingUnused);
            }
        }
        if (OutStatus.c(status.b())) {
            status = configureVideoPortFormat(nodeId, this.hwCodecName, 1, OMX_COLOR_FORMATTYPE.OMX_COLOR_FormatUnused.a(), OMX_VIDEO_CODINGTYPE.OMX_VIDEO_CodingAVC);
            if (OutStatus.c(status.b())) {
                status = configurePort(nodeId, 1, width, height, (float) frameRate, OMX_COLOR_FORMATTYPE.OMX_COLOR_FormatUnused.a(), OMX_VIDEO_CODINGTYPE.OMX_VIDEO_CodingAVC);
            }
        }
        if (OutStatus.c(status.b())) {
            status = setH264CodingTools(nodeId, profile, level, (float) frameRate, iFrameInterval, useCabac, false, sliceSize);
            if (OutStatus.c(status.b())) {
                status = setBitrateAndRcMode(nodeId, bitrate, controlRateType);
            }
        }
        if (this.hasEncCapability && OutStatus.c(status.b()) && hwModeSkypeExtension) {
            setEncodingMode(nodeId, refCount, profile, numChannels, baseLayerPriorityId, sliceControlMode, svcAlways);
        }
        return status.b();
    }

    protected int doQueryDriverVersion(int nodeId) {
        return doQueryDriverVersion(nodeId, false);
    }

    protected int doQueryDriverVersionAndCheckVerSystem(int nodeId) {
        return doQueryDriverVersion(nodeId, checkConfigWritten);
    }

    private int doQueryDriverVersion(int nodeId, boolean checkVersionSystem) {
        ExtendedIndex drvVerIndex = locals().drvVerIndex;
        drvVerIndex.a(facade(), this.omx, nodeId);
        if (drvVerIndex.e()) {
            OmxStruct driverParam = locals().driverVerParam;
            if (checkVersionSystem) {
                testOMXVersionControlOnDriverVersion(nodeId, driverParam);
            }
            driverParam.f();
            driverParam.g.a(1);
            if (OutStatus.c(facade().a(this.omx, nodeId, driverParam).b())) {
                setDriverVersion(driverParam.h.a());
                return 1;
            }
        }
        return 0;
    }

    private void testOMXVersionControlOnDriverVersion(int nodeId, DriverVerParam driverParam) {
        String str;
        Object[] objArr;
        driverParam.f();
        int supportCount = 0;
        boolean trueVerSupported = false;
        for (int majorV = 1; majorV < 4; majorV++) {
            int minorV = 0;
            while (minorV < 10) {
                driverParam.a(majorV, minorV);
                OutStatus status = facade().a(this.omx, nodeId, (OmxStruct) driverParam);
                supportCount += OutStatus.c(status.b()) ? 1 : 0;
                if (1 == majorV && minorV == 0) {
                    trueVerSupported = OutStatus.c(status.b());
                }
                str = "OMX version %d.%d is%s supported, return status:%s";
                objArr = new Object[4];
                objArr[0] = Integer.valueOf(driverParam.x_());
                objArr[1] = Integer.valueOf(driverParam.d());
                objArr[2] = OutStatus.c(status.b()) ? " " : " not";
                objArr[3] = status;
                info(String.format(str, objArr));
                minorV++;
            }
        }
        boolean success = (trueVerSupported && 1 == supportCount) ? checkConfigWritten : false;
        str = "Device supports %d OMX version(s), expected single version %d.%d, which is%s supported";
        objArr = new Object[4];
        objArr[0] = Integer.valueOf(supportCount);
        objArr[1] = Integer.valueOf(1);
        objArr[2] = Integer.valueOf(0);
        objArr[3] = trueVerSupported ? " " : " not";
        String diagnostic = String.format(str, objArr);
        if (success) {
            info(diagnostic);
        } else {
            error(diagnostic);
        }
        driverParam.f();
    }

    public boolean isQpSupported() {
        ExtendedIndex qpIndex = locals().qParamIndex;
        qpIndex.a(facade(), this.omx, getNodeId());
        return qpIndex.e();
    }

    public long getEncCapability() {
        throw new UnsupportedOperationException("This implementation returns a direct buffer");
    }

    public long getDecCapability() {
        throw new UnsupportedOperationException("This implementation returns a direct buffer");
    }

    public Buffer getEncCapabilityBuffer() {
        if (hasSkypeOmxExtension()) {
            OmxStruct encoderCapParam = locals().encCapParam;
            boolean result = OutStatus.c(facade().a(this.omx, getNodeId(), encoderCapParam).b());
            this.hasEncCapability = result;
            if (result) {
                return encoderCapParam.c();
            }
            return null;
        }
        this.hasEncCapability = false;
        return null;
    }

    public Buffer getDecCapabilityBuffer() {
        OmxStruct decoderCapParam = locals().decCapParam;
        return OutStatus.c(facade().a(this.omx, getNodeId(), decoderCapParam).b()) ? decoderCapParam.c() : null;
    }

    public boolean isNodeIdKnown() {
        return (this.ownNodeId != -1 || super.isNodeIdKnown()) ? checkConfigWritten : false;
    }

    public int getNodeId() {
        return this.ownNodeId != -1 ? this.ownNodeId : super.getNodeId();
    }

    public void close() throws IOException {
        if (this.ownNodeId != -1) {
            facade().a(this.omx, getNodeId(), this.observer);
            this.ownNodeId = -1;
        }
    }
}
