package kotlin.reflect.jvm.internal.impl.metadata.jvm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractParser;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.GeneratedExtension;
import kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder;
import kotlin.reflect.jvm.internal.impl.protobuf.Parser;
import kotlin.reflect.jvm.internal.impl.protobuf.WireFormat.FieldType;

public final class JvmProtoBuf {
    public static final GeneratedExtension<Class, Integer> anonymousObjectOriginName = GeneratedMessageLite.newSingularGeneratedExtension(Class.getDefaultInstance(), Integer.valueOf(0), null, null, 103, FieldType.INT32, Integer.class);
    public static final GeneratedExtension<Class, List<Property>> classLocalVariable = GeneratedMessageLite.newRepeatedGeneratedExtension(Class.getDefaultInstance(), Property.getDefaultInstance(), null, 102, FieldType.MESSAGE, false, Property.class);
    public static final GeneratedExtension<Class, Integer> classModuleName = GeneratedMessageLite.newSingularGeneratedExtension(Class.getDefaultInstance(), Integer.valueOf(0), null, null, 101, FieldType.INT32, Integer.class);
    public static final GeneratedExtension<Constructor, JvmMethodSignature> constructorSignature = GeneratedMessageLite.newSingularGeneratedExtension(Constructor.getDefaultInstance(), JvmMethodSignature.getDefaultInstance(), JvmMethodSignature.getDefaultInstance(), null, 100, FieldType.MESSAGE, JvmMethodSignature.class);
    public static final GeneratedExtension<Property, Integer> flags = GeneratedMessageLite.newSingularGeneratedExtension(Property.getDefaultInstance(), Integer.valueOf(0), null, null, 101, FieldType.INT32, Integer.class);
    public static final GeneratedExtension<Type, Boolean> isRaw = GeneratedMessageLite.newSingularGeneratedExtension(Type.getDefaultInstance(), Boolean.FALSE, null, null, 101, FieldType.BOOL, Boolean.class);
    public static final GeneratedExtension<Function, Integer> lambdaClassOriginName = GeneratedMessageLite.newSingularGeneratedExtension(Function.getDefaultInstance(), Integer.valueOf(0), null, null, 101, FieldType.INT32, Integer.class);
    public static final GeneratedExtension<Function, JvmMethodSignature> methodSignature = GeneratedMessageLite.newSingularGeneratedExtension(Function.getDefaultInstance(), JvmMethodSignature.getDefaultInstance(), JvmMethodSignature.getDefaultInstance(), null, 100, FieldType.MESSAGE, JvmMethodSignature.class);
    public static final GeneratedExtension<Package, List<Property>> packageLocalVariable = GeneratedMessageLite.newRepeatedGeneratedExtension(Package.getDefaultInstance(), Property.getDefaultInstance(), null, 102, FieldType.MESSAGE, false, Property.class);
    public static final GeneratedExtension<Package, Integer> packageModuleName = GeneratedMessageLite.newSingularGeneratedExtension(Package.getDefaultInstance(), Integer.valueOf(0), null, null, 101, FieldType.INT32, Integer.class);
    public static final GeneratedExtension<Property, JvmPropertySignature> propertySignature = GeneratedMessageLite.newSingularGeneratedExtension(Property.getDefaultInstance(), JvmPropertySignature.getDefaultInstance(), JvmPropertySignature.getDefaultInstance(), null, 100, FieldType.MESSAGE, JvmPropertySignature.class);
    public static final GeneratedExtension<Type, List<Annotation>> typeAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(Type.getDefaultInstance(), Annotation.getDefaultInstance(), null, 100, FieldType.MESSAGE, false, Annotation.class);
    public static final GeneratedExtension<TypeParameter, List<Annotation>> typeParameterAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(TypeParameter.getDefaultInstance(), Annotation.getDefaultInstance(), null, 100, FieldType.MESSAGE, false, Annotation.class);

    public interface JvmFieldSignatureOrBuilder extends MessageLiteOrBuilder {
    }

    public interface JvmMethodSignatureOrBuilder extends MessageLiteOrBuilder {
    }

    public interface JvmPropertySignatureOrBuilder extends MessageLiteOrBuilder {
    }

    public interface StringTableTypesOrBuilder extends MessageLiteOrBuilder {
    }

    public final class JvmFieldSignature extends GeneratedMessageLite implements JvmFieldSignatureOrBuilder {
        public static Parser<JvmFieldSignature> PARSER = new AbstractParser<JvmFieldSignature>() {
            public final JvmFieldSignature parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new JvmFieldSignature(codedInputStream, extensionRegistryLite);
            }
        };
        private static final JvmFieldSignature defaultInstance;
        private int bitField0_;
        private int desc_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int name_;
        private final ByteString unknownFields;

        public final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<JvmFieldSignature, Builder> implements JvmFieldSignatureOrBuilder {
            private int bitField0_;
            private int desc_;
            private int name_;

            private void maybeForceBuilderInitialization() {
            }

            public final boolean isInitialized() {
                return true;
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            public final Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public final JvmFieldSignature getDefaultInstanceForType() {
                return JvmFieldSignature.getDefaultInstance();
            }

            public final JvmFieldSignature build() {
                MessageLite buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder.newUninitializedMessageException(buildPartial);
            }

            public final JvmFieldSignature buildPartial() {
                JvmFieldSignature jvmFieldSignature = new JvmFieldSignature((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                jvmFieldSignature.name_ = this.name_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                jvmFieldSignature.desc_ = this.desc_;
                jvmFieldSignature.bitField0_ = i2;
                return jvmFieldSignature;
            }

            public final Builder mergeFrom(JvmFieldSignature jvmFieldSignature) {
                if (jvmFieldSignature == JvmFieldSignature.getDefaultInstance()) {
                    return this;
                }
                if (jvmFieldSignature.hasName()) {
                    setName(jvmFieldSignature.getName());
                }
                if (jvmFieldSignature.hasDesc()) {
                    setDesc(jvmFieldSignature.getDesc());
                }
                setUnknownFields(getUnknownFields().concat(jvmFieldSignature.unknownFields));
                return this;
            }

            public final Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                JvmFieldSignature jvmFieldSignature;
                Throwable th;
                JvmFieldSignature jvmFieldSignature2 = null;
                try {
                    JvmFieldSignature jvmFieldSignature3 = (JvmFieldSignature) JvmFieldSignature.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (jvmFieldSignature3 != null) {
                        mergeFrom(jvmFieldSignature3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    jvmFieldSignature = (JvmFieldSignature) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                    jvmFieldSignature2 = jvmFieldSignature;
                }
                if (jvmFieldSignature2 != null) {
                    mergeFrom(jvmFieldSignature2);
                }
                throw th;
            }

            public final Builder setName(int i) {
                this.bitField0_ |= 1;
                this.name_ = i;
                return this;
            }

            public final Builder setDesc(int i) {
                this.bitField0_ |= 2;
                this.desc_ = i;
                return this;
            }
        }

        private JvmFieldSignature(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private JvmFieldSignature(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static JvmFieldSignature getDefaultInstance() {
            return defaultInstance;
        }

        public final JvmFieldSignature getDefaultInstanceForType() {
            return defaultInstance;
        }

        private JvmFieldSignature(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            OutputStream newOutput = ByteString.newOutput();
            CodedOutputStream newInstance = CodedOutputStream.newInstance(newOutput, 1);
            Object obj = null;
            while (obj == null) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 8) {
                            this.bitField0_ |= 1;
                            this.name_ = codedInputStream.readInt32();
                        } else if (readTag == 16) {
                            this.bitField0_ |= 2;
                            this.desc_ = codedInputStream.readInt32();
                        } else if (parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        }
                    }
                    obj = 1;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    try {
                        newInstance.flush();
                        this.unknownFields = newOutput.toByteString();
                        makeExtensionsImmutable();
                    } catch (Throwable th2) {
                        this.unknownFields = newOutput.toByteString();
                    }
                }
            }
            try {
                newInstance.flush();
                this.unknownFields = newOutput.toByteString();
                makeExtensionsImmutable();
            } catch (Throwable th3) {
                this.unknownFields = newOutput.toByteString();
            }
        }

        static {
            JvmFieldSignature jvmFieldSignature = new JvmFieldSignature(true);
            defaultInstance = jvmFieldSignature;
            jvmFieldSignature.initFields();
        }

        public final Parser<JvmFieldSignature> getParserForType() {
            return PARSER;
        }

        public final boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        public final int getName() {
            return this.name_;
        }

        public final boolean hasDesc() {
            return (this.bitField0_ & 2) == 2;
        }

        public final int getDesc() {
            return this.desc_;
        }

        private void initFields() {
            this.name_ = 0;
            this.desc_ = 0;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public final void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.desc_);
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public final int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeInt32Size(2, this.desc_);
            }
            i2 += this.unknownFields.size();
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public final Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(JvmFieldSignature jvmFieldSignature) {
            return newBuilder().mergeFrom(jvmFieldSignature);
        }

        public final Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public final class JvmMethodSignature extends GeneratedMessageLite implements JvmMethodSignatureOrBuilder {
        public static Parser<JvmMethodSignature> PARSER = new AbstractParser<JvmMethodSignature>() {
            public final JvmMethodSignature parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new JvmMethodSignature(codedInputStream, extensionRegistryLite);
            }
        };
        private static final JvmMethodSignature defaultInstance;
        private int bitField0_;
        private int desc_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int name_;
        private final ByteString unknownFields;

        public final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<JvmMethodSignature, Builder> implements JvmMethodSignatureOrBuilder {
            private int bitField0_;
            private int desc_;
            private int name_;

            private void maybeForceBuilderInitialization() {
            }

            public final boolean isInitialized() {
                return true;
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            public final Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public final JvmMethodSignature getDefaultInstanceForType() {
                return JvmMethodSignature.getDefaultInstance();
            }

            public final JvmMethodSignature build() {
                MessageLite buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder.newUninitializedMessageException(buildPartial);
            }

            public final JvmMethodSignature buildPartial() {
                JvmMethodSignature jvmMethodSignature = new JvmMethodSignature((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                jvmMethodSignature.name_ = this.name_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                jvmMethodSignature.desc_ = this.desc_;
                jvmMethodSignature.bitField0_ = i2;
                return jvmMethodSignature;
            }

            public final Builder mergeFrom(JvmMethodSignature jvmMethodSignature) {
                if (jvmMethodSignature == JvmMethodSignature.getDefaultInstance()) {
                    return this;
                }
                if (jvmMethodSignature.hasName()) {
                    setName(jvmMethodSignature.getName());
                }
                if (jvmMethodSignature.hasDesc()) {
                    setDesc(jvmMethodSignature.getDesc());
                }
                setUnknownFields(getUnknownFields().concat(jvmMethodSignature.unknownFields));
                return this;
            }

            public final Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                JvmMethodSignature jvmMethodSignature;
                Throwable th;
                JvmMethodSignature jvmMethodSignature2 = null;
                try {
                    JvmMethodSignature jvmMethodSignature3 = (JvmMethodSignature) JvmMethodSignature.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (jvmMethodSignature3 != null) {
                        mergeFrom(jvmMethodSignature3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    jvmMethodSignature = (JvmMethodSignature) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                    jvmMethodSignature2 = jvmMethodSignature;
                }
                if (jvmMethodSignature2 != null) {
                    mergeFrom(jvmMethodSignature2);
                }
                throw th;
            }

            public final Builder setName(int i) {
                this.bitField0_ |= 1;
                this.name_ = i;
                return this;
            }

            public final Builder setDesc(int i) {
                this.bitField0_ |= 2;
                this.desc_ = i;
                return this;
            }
        }

        private JvmMethodSignature(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private JvmMethodSignature(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static JvmMethodSignature getDefaultInstance() {
            return defaultInstance;
        }

        public final JvmMethodSignature getDefaultInstanceForType() {
            return defaultInstance;
        }

        private JvmMethodSignature(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            OutputStream newOutput = ByteString.newOutput();
            CodedOutputStream newInstance = CodedOutputStream.newInstance(newOutput, 1);
            Object obj = null;
            while (obj == null) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 8) {
                            this.bitField0_ |= 1;
                            this.name_ = codedInputStream.readInt32();
                        } else if (readTag == 16) {
                            this.bitField0_ |= 2;
                            this.desc_ = codedInputStream.readInt32();
                        } else if (parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        }
                    }
                    obj = 1;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    try {
                        newInstance.flush();
                        this.unknownFields = newOutput.toByteString();
                        makeExtensionsImmutable();
                    } catch (Throwable th2) {
                        this.unknownFields = newOutput.toByteString();
                    }
                }
            }
            try {
                newInstance.flush();
                this.unknownFields = newOutput.toByteString();
                makeExtensionsImmutable();
            } catch (Throwable th3) {
                this.unknownFields = newOutput.toByteString();
            }
        }

        static {
            JvmMethodSignature jvmMethodSignature = new JvmMethodSignature(true);
            defaultInstance = jvmMethodSignature;
            jvmMethodSignature.initFields();
        }

        public final Parser<JvmMethodSignature> getParserForType() {
            return PARSER;
        }

        public final boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        public final int getName() {
            return this.name_;
        }

        public final boolean hasDesc() {
            return (this.bitField0_ & 2) == 2;
        }

        public final int getDesc() {
            return this.desc_;
        }

        private void initFields() {
            this.name_ = 0;
            this.desc_ = 0;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public final void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.desc_);
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public final int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeInt32Size(2, this.desc_);
            }
            i2 += this.unknownFields.size();
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public final Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(JvmMethodSignature jvmMethodSignature) {
            return newBuilder().mergeFrom(jvmMethodSignature);
        }

        public final Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public final class JvmPropertySignature extends GeneratedMessageLite implements JvmPropertySignatureOrBuilder {
        public static Parser<JvmPropertySignature> PARSER = new AbstractParser<JvmPropertySignature>() {
            public final JvmPropertySignature parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new JvmPropertySignature(codedInputStream, extensionRegistryLite);
            }
        };
        private static final JvmPropertySignature defaultInstance;
        private int bitField0_;
        private JvmFieldSignature field_;
        private JvmMethodSignature getter_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private JvmMethodSignature setter_;
        private JvmMethodSignature syntheticMethod_;
        private final ByteString unknownFields;

        public final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<JvmPropertySignature, Builder> implements JvmPropertySignatureOrBuilder {
            private int bitField0_;
            private JvmFieldSignature field_ = JvmFieldSignature.getDefaultInstance();
            private JvmMethodSignature getter_ = JvmMethodSignature.getDefaultInstance();
            private JvmMethodSignature setter_ = JvmMethodSignature.getDefaultInstance();
            private JvmMethodSignature syntheticMethod_ = JvmMethodSignature.getDefaultInstance();

            private void maybeForceBuilderInitialization() {
            }

            public final boolean isInitialized() {
                return true;
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            public final Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public final JvmPropertySignature getDefaultInstanceForType() {
                return JvmPropertySignature.getDefaultInstance();
            }

            public final JvmPropertySignature build() {
                MessageLite buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder.newUninitializedMessageException(buildPartial);
            }

            public final JvmPropertySignature buildPartial() {
                JvmPropertySignature jvmPropertySignature = new JvmPropertySignature((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                jvmPropertySignature.field_ = this.field_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                jvmPropertySignature.syntheticMethod_ = this.syntheticMethod_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                jvmPropertySignature.getter_ = this.getter_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                jvmPropertySignature.setter_ = this.setter_;
                jvmPropertySignature.bitField0_ = i2;
                return jvmPropertySignature;
            }

            public final Builder mergeFrom(JvmPropertySignature jvmPropertySignature) {
                if (jvmPropertySignature == JvmPropertySignature.getDefaultInstance()) {
                    return this;
                }
                if (jvmPropertySignature.hasField()) {
                    mergeField(jvmPropertySignature.getField());
                }
                if (jvmPropertySignature.hasSyntheticMethod()) {
                    mergeSyntheticMethod(jvmPropertySignature.getSyntheticMethod());
                }
                if (jvmPropertySignature.hasGetter()) {
                    mergeGetter(jvmPropertySignature.getGetter());
                }
                if (jvmPropertySignature.hasSetter()) {
                    mergeSetter(jvmPropertySignature.getSetter());
                }
                setUnknownFields(getUnknownFields().concat(jvmPropertySignature.unknownFields));
                return this;
            }

            public final Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                JvmPropertySignature jvmPropertySignature;
                Throwable th;
                JvmPropertySignature jvmPropertySignature2 = null;
                try {
                    JvmPropertySignature jvmPropertySignature3 = (JvmPropertySignature) JvmPropertySignature.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (jvmPropertySignature3 != null) {
                        mergeFrom(jvmPropertySignature3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    jvmPropertySignature = (JvmPropertySignature) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                    jvmPropertySignature2 = jvmPropertySignature;
                }
                if (jvmPropertySignature2 != null) {
                    mergeFrom(jvmPropertySignature2);
                }
                throw th;
            }

            public final Builder mergeField(JvmFieldSignature jvmFieldSignature) {
                if ((this.bitField0_ & 1) != 1 || this.field_ == JvmFieldSignature.getDefaultInstance()) {
                    this.field_ = jvmFieldSignature;
                } else {
                    this.field_ = JvmFieldSignature.newBuilder(this.field_).mergeFrom(jvmFieldSignature).buildPartial();
                }
                this.bitField0_ |= 1;
                return this;
            }

            public final Builder mergeSyntheticMethod(JvmMethodSignature jvmMethodSignature) {
                if ((this.bitField0_ & 2) != 2 || this.syntheticMethod_ == JvmMethodSignature.getDefaultInstance()) {
                    this.syntheticMethod_ = jvmMethodSignature;
                } else {
                    this.syntheticMethod_ = JvmMethodSignature.newBuilder(this.syntheticMethod_).mergeFrom(jvmMethodSignature).buildPartial();
                }
                this.bitField0_ |= 2;
                return this;
            }

            public final Builder mergeGetter(JvmMethodSignature jvmMethodSignature) {
                if ((this.bitField0_ & 4) != 4 || this.getter_ == JvmMethodSignature.getDefaultInstance()) {
                    this.getter_ = jvmMethodSignature;
                } else {
                    this.getter_ = JvmMethodSignature.newBuilder(this.getter_).mergeFrom(jvmMethodSignature).buildPartial();
                }
                this.bitField0_ |= 4;
                return this;
            }

            public final Builder mergeSetter(JvmMethodSignature jvmMethodSignature) {
                if ((this.bitField0_ & 8) != 8 || this.setter_ == JvmMethodSignature.getDefaultInstance()) {
                    this.setter_ = jvmMethodSignature;
                } else {
                    this.setter_ = JvmMethodSignature.newBuilder(this.setter_).mergeFrom(jvmMethodSignature).buildPartial();
                }
                this.bitField0_ |= 8;
                return this;
            }
        }

        private JvmPropertySignature(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private JvmPropertySignature(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static JvmPropertySignature getDefaultInstance() {
            return defaultInstance;
        }

        public final JvmPropertySignature getDefaultInstanceForType() {
            return defaultInstance;
        }

        private JvmPropertySignature(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            OutputStream newOutput = ByteString.newOutput();
            CodedOutputStream newInstance = CodedOutputStream.newInstance(newOutput, 1);
            Object obj = null;
            while (obj == null) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        Builder builder = null;
                        Builder toBuilder;
                        if (readTag == 10) {
                            if ((this.bitField0_ & 1) == 1) {
                                builder = this.field_.toBuilder();
                            }
                            this.field_ = (JvmFieldSignature) codedInputStream.readMessage(JvmFieldSignature.PARSER, extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.field_);
                                this.field_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (readTag == 18) {
                            if ((this.bitField0_ & 2) == 2) {
                                toBuilder = this.syntheticMethod_.toBuilder();
                            }
                            this.syntheticMethod_ = (JvmMethodSignature) codedInputStream.readMessage(JvmMethodSignature.PARSER, extensionRegistryLite);
                            if (toBuilder != null) {
                                toBuilder.mergeFrom(this.syntheticMethod_);
                                this.syntheticMethod_ = toBuilder.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (readTag == 26) {
                            if ((this.bitField0_ & 4) == 4) {
                                toBuilder = this.getter_.toBuilder();
                            }
                            this.getter_ = (JvmMethodSignature) codedInputStream.readMessage(JvmMethodSignature.PARSER, extensionRegistryLite);
                            if (toBuilder != null) {
                                toBuilder.mergeFrom(this.getter_);
                                this.getter_ = toBuilder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (readTag == 34) {
                            if ((this.bitField0_ & 8) == 8) {
                                toBuilder = this.setter_.toBuilder();
                            }
                            this.setter_ = (JvmMethodSignature) codedInputStream.readMessage(JvmMethodSignature.PARSER, extensionRegistryLite);
                            if (toBuilder != null) {
                                toBuilder.mergeFrom(this.setter_);
                                this.setter_ = toBuilder.buildPartial();
                            }
                            this.bitField0_ |= 8;
                        } else if (parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        }
                    }
                    obj = 1;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    try {
                        newInstance.flush();
                        this.unknownFields = newOutput.toByteString();
                        makeExtensionsImmutable();
                    } catch (Throwable th2) {
                        this.unknownFields = newOutput.toByteString();
                    }
                }
            }
            try {
                newInstance.flush();
                this.unknownFields = newOutput.toByteString();
                makeExtensionsImmutable();
            } catch (Throwable th3) {
                this.unknownFields = newOutput.toByteString();
            }
        }

        static {
            JvmPropertySignature jvmPropertySignature = new JvmPropertySignature(true);
            defaultInstance = jvmPropertySignature;
            jvmPropertySignature.initFields();
        }

        public final Parser<JvmPropertySignature> getParserForType() {
            return PARSER;
        }

        public final boolean hasField() {
            return (this.bitField0_ & 1) == 1;
        }

        public final JvmFieldSignature getField() {
            return this.field_;
        }

        public final boolean hasSyntheticMethod() {
            return (this.bitField0_ & 2) == 2;
        }

        public final JvmMethodSignature getSyntheticMethod() {
            return this.syntheticMethod_;
        }

        public final boolean hasGetter() {
            return (this.bitField0_ & 4) == 4;
        }

        public final JvmMethodSignature getGetter() {
            return this.getter_;
        }

        public final boolean hasSetter() {
            return (this.bitField0_ & 8) == 8;
        }

        public final JvmMethodSignature getSetter() {
            return this.setter_;
        }

        private void initFields() {
            this.field_ = JvmFieldSignature.getDefaultInstance();
            this.syntheticMethod_ = JvmMethodSignature.getDefaultInstance();
            this.getter_ = JvmMethodSignature.getDefaultInstance();
            this.setter_ = JvmMethodSignature.getDefaultInstance();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public final void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeMessage(1, this.field_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(2, this.syntheticMethod_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeMessage(3, this.getter_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeMessage(4, this.setter_);
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public final int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, this.field_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeMessageSize(2, this.syntheticMethod_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeMessageSize(3, this.getter_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeMessageSize(4, this.setter_);
            }
            i2 += this.unknownFields.size();
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public final Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(JvmPropertySignature jvmPropertySignature) {
            return newBuilder().mergeFrom(jvmPropertySignature);
        }

        public final Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public final class StringTableTypes extends GeneratedMessageLite implements StringTableTypesOrBuilder {
        public static Parser<StringTableTypes> PARSER = new AbstractParser<StringTableTypes>() {
            public final StringTableTypes parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new StringTableTypes(codedInputStream, extensionRegistryLite);
            }
        };
        private static final StringTableTypes defaultInstance;
        private int localNameMemoizedSerializedSize;
        private List<Integer> localName_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List<Record> record_;
        private final ByteString unknownFields;

        public interface RecordOrBuilder extends MessageLiteOrBuilder {
        }

        public final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<StringTableTypes, Builder> implements StringTableTypesOrBuilder {
            private int bitField0_;
            private List<Integer> localName_ = Collections.emptyList();
            private List<Record> record_ = Collections.emptyList();

            private void maybeForceBuilderInitialization() {
            }

            public final boolean isInitialized() {
                return true;
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            public final Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public final StringTableTypes getDefaultInstanceForType() {
                return StringTableTypes.getDefaultInstance();
            }

            public final StringTableTypes build() {
                MessageLite buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder.newUninitializedMessageException(buildPartial);
            }

            public final StringTableTypes buildPartial() {
                StringTableTypes stringTableTypes = new StringTableTypes((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                if ((this.bitField0_ & 1) == 1) {
                    this.record_ = Collections.unmodifiableList(this.record_);
                    this.bitField0_ &= -2;
                }
                stringTableTypes.record_ = this.record_;
                if ((this.bitField0_ & 2) == 2) {
                    this.localName_ = Collections.unmodifiableList(this.localName_);
                    this.bitField0_ &= -3;
                }
                stringTableTypes.localName_ = this.localName_;
                return stringTableTypes;
            }

            public final Builder mergeFrom(StringTableTypes stringTableTypes) {
                if (stringTableTypes == StringTableTypes.getDefaultInstance()) {
                    return this;
                }
                if (!stringTableTypes.record_.isEmpty()) {
                    if (this.record_.isEmpty()) {
                        this.record_ = stringTableTypes.record_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureRecordIsMutable();
                        this.record_.addAll(stringTableTypes.record_);
                    }
                }
                if (!stringTableTypes.localName_.isEmpty()) {
                    if (this.localName_.isEmpty()) {
                        this.localName_ = stringTableTypes.localName_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureLocalNameIsMutable();
                        this.localName_.addAll(stringTableTypes.localName_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(stringTableTypes.unknownFields));
                return this;
            }

            public final Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                StringTableTypes stringTableTypes;
                Throwable th;
                StringTableTypes stringTableTypes2 = null;
                try {
                    StringTableTypes stringTableTypes3 = (StringTableTypes) StringTableTypes.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (stringTableTypes3 != null) {
                        mergeFrom(stringTableTypes3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    stringTableTypes = (StringTableTypes) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                    stringTableTypes2 = stringTableTypes;
                }
                if (stringTableTypes2 != null) {
                    mergeFrom(stringTableTypes2);
                }
                throw th;
            }

            private void ensureRecordIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.record_ = new ArrayList(this.record_);
                    this.bitField0_ |= 1;
                }
            }

            private void ensureLocalNameIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.localName_ = new ArrayList(this.localName_);
                    this.bitField0_ |= 2;
                }
            }
        }

        public final class Record extends GeneratedMessageLite implements RecordOrBuilder {
            public static Parser<Record> PARSER = new AbstractParser<Record>() {
                public final Record parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Record(codedInputStream, extensionRegistryLite);
                }
            };
            private static final Record defaultInstance;
            private int bitField0_;
            private byte memoizedIsInitialized;
            private int memoizedSerializedSize;
            private Operation operation_;
            private int predefinedIndex_;
            private int range_;
            private int replaceCharMemoizedSerializedSize;
            private List<Integer> replaceChar_;
            private Object string_;
            private int substringIndexMemoizedSerializedSize;
            private List<Integer> substringIndex_;
            private final ByteString unknownFields;

            public enum Operation implements EnumLite {
                NONE(0, 0),
                INTERNAL_TO_CLASS_ID(1, 1),
                DESC_TO_CLASS_ID(2, 2);
                
                private static EnumLiteMap<Operation> internalValueMap;
                private final int value;

                static {
                    internalValueMap = new EnumLiteMap<Operation>() {
                        public final Operation findValueByNumber(int i) {
                            return Operation.valueOf(i);
                        }
                    };
                }

                public final int getNumber() {
                    return this.value;
                }

                public static Operation valueOf(int i) {
                    switch (i) {
                        case 0:
                            return NONE;
                        case 1:
                            return INTERNAL_TO_CLASS_ID;
                        case 2:
                            return DESC_TO_CLASS_ID;
                        default:
                            return null;
                    }
                }

                private Operation(int i, int i2) {
                    this.value = i2;
                }
            }

            public final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<Record, Builder> implements RecordOrBuilder {
                private int bitField0_;
                private Operation operation_ = Operation.NONE;
                private int predefinedIndex_;
                private int range_ = 1;
                private List<Integer> replaceChar_ = Collections.emptyList();
                private Object string_ = "";
                private List<Integer> substringIndex_ = Collections.emptyList();

                private void maybeForceBuilderInitialization() {
                }

                public final boolean isInitialized() {
                    return true;
                }

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private static Builder create() {
                    return new Builder();
                }

                public final Builder clone() {
                    return create().mergeFrom(buildPartial());
                }

                public final Record getDefaultInstanceForType() {
                    return Record.getDefaultInstance();
                }

                public final Record build() {
                    MessageLite buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder.newUninitializedMessageException(buildPartial);
                }

                public final Record buildPartial() {
                    Record record = new Record((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                    int i = this.bitField0_;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    record.range_ = this.range_;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    record.predefinedIndex_ = this.predefinedIndex_;
                    if ((i & 4) == 4) {
                        i2 |= 4;
                    }
                    record.string_ = this.string_;
                    if ((i & 8) == 8) {
                        i2 |= 8;
                    }
                    record.operation_ = this.operation_;
                    if ((this.bitField0_ & 16) == 16) {
                        this.substringIndex_ = Collections.unmodifiableList(this.substringIndex_);
                        this.bitField0_ &= -17;
                    }
                    record.substringIndex_ = this.substringIndex_;
                    if ((this.bitField0_ & 32) == 32) {
                        this.replaceChar_ = Collections.unmodifiableList(this.replaceChar_);
                        this.bitField0_ &= -33;
                    }
                    record.replaceChar_ = this.replaceChar_;
                    record.bitField0_ = i2;
                    return record;
                }

                public final Builder mergeFrom(Record record) {
                    if (record == Record.getDefaultInstance()) {
                        return this;
                    }
                    if (record.hasRange()) {
                        setRange(record.getRange());
                    }
                    if (record.hasPredefinedIndex()) {
                        setPredefinedIndex(record.getPredefinedIndex());
                    }
                    if (record.hasString()) {
                        this.bitField0_ |= 4;
                        this.string_ = record.string_;
                    }
                    if (record.hasOperation()) {
                        setOperation(record.getOperation());
                    }
                    if (!record.substringIndex_.isEmpty()) {
                        if (this.substringIndex_.isEmpty()) {
                            this.substringIndex_ = record.substringIndex_;
                            this.bitField0_ &= -17;
                        } else {
                            ensureSubstringIndexIsMutable();
                            this.substringIndex_.addAll(record.substringIndex_);
                        }
                    }
                    if (!record.replaceChar_.isEmpty()) {
                        if (this.replaceChar_.isEmpty()) {
                            this.replaceChar_ = record.replaceChar_;
                            this.bitField0_ &= -33;
                        } else {
                            ensureReplaceCharIsMutable();
                            this.replaceChar_.addAll(record.replaceChar_);
                        }
                    }
                    setUnknownFields(getUnknownFields().concat(record.unknownFields));
                    return this;
                }

                public final Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    Record record;
                    Throwable th;
                    Record record2 = null;
                    try {
                        Record record3 = (Record) Record.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (record3 != null) {
                            mergeFrom(record3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        record = (Record) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                        record2 = record;
                    }
                    if (record2 != null) {
                        mergeFrom(record2);
                    }
                    throw th;
                }

                public final Builder setRange(int i) {
                    this.bitField0_ |= 1;
                    this.range_ = i;
                    return this;
                }

                public final Builder setPredefinedIndex(int i) {
                    this.bitField0_ |= 2;
                    this.predefinedIndex_ = i;
                    return this;
                }

                public final Builder setOperation(Operation operation) {
                    if (operation != null) {
                        this.bitField0_ |= 8;
                        this.operation_ = operation;
                        return this;
                    }
                    throw new NullPointerException();
                }

                private void ensureSubstringIndexIsMutable() {
                    if ((this.bitField0_ & 16) != 16) {
                        this.substringIndex_ = new ArrayList(this.substringIndex_);
                        this.bitField0_ |= 16;
                    }
                }

                private void ensureReplaceCharIsMutable() {
                    if ((this.bitField0_ & 32) != 32) {
                        this.replaceChar_ = new ArrayList(this.replaceChar_);
                        this.bitField0_ |= 32;
                    }
                }
            }

            private Record(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
                super(builder);
                this.substringIndexMemoizedSerializedSize = -1;
                this.replaceCharMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = builder.getUnknownFields();
            }

            private Record(boolean z) {
                this.substringIndexMemoizedSerializedSize = -1;
                this.replaceCharMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = ByteString.EMPTY;
            }

            public static Record getDefaultInstance() {
                return defaultInstance;
            }

            public final Record getDefaultInstanceForType() {
                return defaultInstance;
            }

            private Record(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this.substringIndexMemoizedSerializedSize = -1;
                this.replaceCharMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                initFields();
                OutputStream newOutput = ByteString.newOutput();
                CodedOutputStream newInstance = CodedOutputStream.newInstance(newOutput, 1);
                Object obj = null;
                int i = 0;
                while (obj == null) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.bitField0_ |= 1;
                                this.range_ = codedInputStream.readInt32();
                            } else if (readTag == 16) {
                                this.bitField0_ |= 2;
                                this.predefinedIndex_ = codedInputStream.readInt32();
                            } else if (readTag == 24) {
                                int readEnum = codedInputStream.readEnum();
                                Operation valueOf = Operation.valueOf(readEnum);
                                if (valueOf == null) {
                                    newInstance.writeRawVarint32(readTag);
                                    newInstance.writeRawVarint32(readEnum);
                                } else {
                                    this.bitField0_ |= 8;
                                    this.operation_ = valueOf;
                                }
                            } else if (readTag == 32) {
                                if ((i & 16) != 16) {
                                    this.substringIndex_ = new ArrayList();
                                    i |= 16;
                                }
                                this.substringIndex_.add(Integer.valueOf(codedInputStream.readInt32()));
                            } else if (readTag == 34) {
                                readTag = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if ((i & 16) != 16 && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.substringIndex_ = new ArrayList();
                                    i |= 16;
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.substringIndex_.add(Integer.valueOf(codedInputStream.readInt32()));
                                }
                                codedInputStream.popLimit(readTag);
                            } else if (readTag == 40) {
                                if ((i & 32) != 32) {
                                    this.replaceChar_ = new ArrayList();
                                    i |= 32;
                                }
                                this.replaceChar_.add(Integer.valueOf(codedInputStream.readInt32()));
                            } else if (readTag == 42) {
                                readTag = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if ((i & 32) != 32 && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.replaceChar_ = new ArrayList();
                                    i |= 32;
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.replaceChar_.add(Integer.valueOf(codedInputStream.readInt32()));
                                }
                                codedInputStream.popLimit(readTag);
                            } else if (readTag == 50) {
                                ByteString readBytes = codedInputStream.readBytes();
                                this.bitField0_ |= 4;
                                this.string_ = readBytes;
                            } else if (parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                            }
                        }
                        obj = 1;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if ((i & 16) == 16) {
                            this.substringIndex_ = Collections.unmodifiableList(this.substringIndex_);
                        }
                        if ((i & 32) == 32) {
                            this.replaceChar_ = Collections.unmodifiableList(this.replaceChar_);
                        }
                        try {
                            newInstance.flush();
                            this.unknownFields = newOutput.toByteString();
                            makeExtensionsImmutable();
                        } catch (Throwable th2) {
                            this.unknownFields = newOutput.toByteString();
                        }
                    }
                }
                if ((i & 16) == 16) {
                    this.substringIndex_ = Collections.unmodifiableList(this.substringIndex_);
                }
                if ((i & 32) == 32) {
                    this.replaceChar_ = Collections.unmodifiableList(this.replaceChar_);
                }
                try {
                    newInstance.flush();
                    this.unknownFields = newOutput.toByteString();
                    makeExtensionsImmutable();
                } catch (Throwable th3) {
                    this.unknownFields = newOutput.toByteString();
                }
            }

            static {
                Record record = new Record(true);
                defaultInstance = record;
                record.initFields();
            }

            public final Parser<Record> getParserForType() {
                return PARSER;
            }

            public final boolean hasRange() {
                return (this.bitField0_ & 1) == 1;
            }

            public final int getRange() {
                return this.range_;
            }

            public final boolean hasPredefinedIndex() {
                return (this.bitField0_ & 2) == 2;
            }

            public final int getPredefinedIndex() {
                return this.predefinedIndex_;
            }

            public final boolean hasString() {
                return (this.bitField0_ & 4) == 4;
            }

            public final String getString() {
                Object obj = this.string_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.string_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public final ByteString getStringBytes() {
                Object obj = this.string_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.string_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public final boolean hasOperation() {
                return (this.bitField0_ & 8) == 8;
            }

            public final Operation getOperation() {
                return this.operation_;
            }

            public final List<Integer> getSubstringIndexList() {
                return this.substringIndex_;
            }

            public final int getSubstringIndexCount() {
                return this.substringIndex_.size();
            }

            public final List<Integer> getReplaceCharList() {
                return this.replaceChar_;
            }

            public final int getReplaceCharCount() {
                return this.replaceChar_.size();
            }

            private void initFields() {
                this.range_ = 1;
                this.predefinedIndex_ = 0;
                this.string_ = "";
                this.operation_ = Operation.NONE;
                this.substringIndex_ = Collections.emptyList();
                this.replaceChar_ = Collections.emptyList();
            }

            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == (byte) 1) {
                    return true;
                }
                if (b == (byte) 0) {
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            public final void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                getSerializedSize();
                if ((this.bitField0_ & 1) == 1) {
                    codedOutputStream.writeInt32(1, this.range_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    codedOutputStream.writeInt32(2, this.predefinedIndex_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    codedOutputStream.writeEnum(3, this.operation_.getNumber());
                }
                if (getSubstringIndexList().size() > 0) {
                    codedOutputStream.writeRawVarint32(34);
                    codedOutputStream.writeRawVarint32(this.substringIndexMemoizedSerializedSize);
                }
                int i = 0;
                for (int i2 = 0; i2 < this.substringIndex_.size(); i2++) {
                    codedOutputStream.writeInt32NoTag(((Integer) this.substringIndex_.get(i2)).intValue());
                }
                if (getReplaceCharList().size() > 0) {
                    codedOutputStream.writeRawVarint32(42);
                    codedOutputStream.writeRawVarint32(this.replaceCharMemoizedSerializedSize);
                }
                while (i < this.replaceChar_.size()) {
                    codedOutputStream.writeInt32NoTag(((Integer) this.replaceChar_.get(i)).intValue());
                    i++;
                }
                if ((this.bitField0_ & 4) == 4) {
                    codedOutputStream.writeBytes(6, getStringBytes());
                }
                codedOutputStream.writeRawBytes(this.unknownFields);
            }

            public final int getSerializedSize() {
                int i = this.memoizedSerializedSize;
                if (i != -1) {
                    return i;
                }
                int i2;
                int i3 = 0;
                i = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.range_) + 0 : 0;
                if ((this.bitField0_ & 2) == 2) {
                    i += CodedOutputStream.computeInt32Size(2, this.predefinedIndex_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    i += CodedOutputStream.computeEnumSize(3, this.operation_.getNumber());
                }
                int i4 = 0;
                for (i2 = 0; i2 < this.substringIndex_.size(); i2++) {
                    i4 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.substringIndex_.get(i2)).intValue());
                }
                i += i4;
                if (!getSubstringIndexList().isEmpty()) {
                    i = (i + 1) + CodedOutputStream.computeInt32SizeNoTag(i4);
                }
                this.substringIndexMemoizedSerializedSize = i4;
                i2 = 0;
                while (i3 < this.replaceChar_.size()) {
                    i2 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.replaceChar_.get(i3)).intValue());
                    i3++;
                }
                i += i2;
                if (!getReplaceCharList().isEmpty()) {
                    i = (i + 1) + CodedOutputStream.computeInt32SizeNoTag(i2);
                }
                this.replaceCharMemoizedSerializedSize = i2;
                if ((this.bitField0_ & 4) == 4) {
                    i += CodedOutputStream.computeBytesSize(6, getStringBytes());
                }
                i += this.unknownFields.size();
                this.memoizedSerializedSize = i;
                return i;
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            public final Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder(Record record) {
                return newBuilder().mergeFrom(record);
            }

            public final Builder toBuilder() {
                return newBuilder(this);
            }
        }

        private StringTableTypes(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
            super(builder);
            this.localNameMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private StringTableTypes(boolean z) {
            this.localNameMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static StringTableTypes getDefaultInstance() {
            return defaultInstance;
        }

        public final StringTableTypes getDefaultInstanceForType() {
            return defaultInstance;
        }

        private StringTableTypes(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.localNameMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            OutputStream newOutput = ByteString.newOutput();
            CodedOutputStream newInstance = CodedOutputStream.newInstance(newOutput, 1);
            Object obj = null;
            int i = 0;
            while (obj == null) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            if ((i & 1) != 1) {
                                this.record_ = new ArrayList();
                                i |= 1;
                            }
                            this.record_.add(codedInputStream.readMessage(Record.PARSER, extensionRegistryLite));
                        } else if (readTag == 40) {
                            if ((i & 2) != 2) {
                                this.localName_ = new ArrayList();
                                i |= 2;
                            }
                            this.localName_.add(Integer.valueOf(codedInputStream.readInt32()));
                        } else if (readTag == 42) {
                            readTag = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if ((i & 2) != 2 && codedInputStream.getBytesUntilLimit() > 0) {
                                this.localName_ = new ArrayList();
                                i |= 2;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.localName_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream.popLimit(readTag);
                        } else if (parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        }
                    }
                    obj = 1;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if ((i & 1) == 1) {
                        this.record_ = Collections.unmodifiableList(this.record_);
                    }
                    if ((i & 2) == 2) {
                        this.localName_ = Collections.unmodifiableList(this.localName_);
                    }
                    try {
                        newInstance.flush();
                        this.unknownFields = newOutput.toByteString();
                        makeExtensionsImmutable();
                    } catch (Throwable th2) {
                        this.unknownFields = newOutput.toByteString();
                    }
                }
            }
            if ((i & 1) == 1) {
                this.record_ = Collections.unmodifiableList(this.record_);
            }
            if ((i & 2) == 2) {
                this.localName_ = Collections.unmodifiableList(this.localName_);
            }
            try {
                newInstance.flush();
                this.unknownFields = newOutput.toByteString();
                makeExtensionsImmutable();
            } catch (Throwable th3) {
                this.unknownFields = newOutput.toByteString();
            }
        }

        static {
            StringTableTypes stringTableTypes = new StringTableTypes(true);
            defaultInstance = stringTableTypes;
            stringTableTypes.initFields();
        }

        public final Parser<StringTableTypes> getParserForType() {
            return PARSER;
        }

        public final List<Record> getRecordList() {
            return this.record_;
        }

        public final List<Integer> getLocalNameList() {
            return this.localName_;
        }

        private void initFields() {
            this.record_ = Collections.emptyList();
            this.localName_ = Collections.emptyList();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public final void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            int i = 0;
            for (int i2 = 0; i2 < this.record_.size(); i2++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.record_.get(i2));
            }
            if (getLocalNameList().size() > 0) {
                codedOutputStream.writeRawVarint32(42);
                codedOutputStream.writeRawVarint32(this.localNameMemoizedSerializedSize);
            }
            while (i < this.localName_.size()) {
                codedOutputStream.writeInt32NoTag(((Integer) this.localName_.get(i)).intValue());
                i++;
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public final int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2;
            int i3 = 0;
            for (i2 = 0; i2 < this.record_.size(); i2++) {
                i3 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.record_.get(i2));
            }
            i2 = 0;
            for (i = 0; i < this.localName_.size(); i++) {
                i2 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.localName_.get(i)).intValue());
            }
            i3 += i2;
            if (!getLocalNameList().isEmpty()) {
                i3 = (i3 + 1) + CodedOutputStream.computeInt32SizeNoTag(i2);
            }
            this.localNameMemoizedSerializedSize = i2;
            i3 += this.unknownFields.size();
            this.memoizedSerializedSize = i3;
            return i3;
        }

        public static StringTableTypes parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StringTableTypes) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public final Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(StringTableTypes stringTableTypes) {
            return newBuilder().mergeFrom(stringTableTypes);
        }

        public final Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.add(constructorSignature);
        extensionRegistryLite.add(methodSignature);
        extensionRegistryLite.add(lambdaClassOriginName);
        extensionRegistryLite.add(propertySignature);
        extensionRegistryLite.add(flags);
        extensionRegistryLite.add(typeAnnotation);
        extensionRegistryLite.add(isRaw);
        extensionRegistryLite.add(typeParameterAnnotation);
        extensionRegistryLite.add(classModuleName);
        extensionRegistryLite.add(classLocalVariable);
        extensionRegistryLite.add(anonymousObjectOriginName);
        extensionRegistryLite.add(packageModuleName);
        extensionRegistryLite.add(packageLocalVariable);
    }
}
