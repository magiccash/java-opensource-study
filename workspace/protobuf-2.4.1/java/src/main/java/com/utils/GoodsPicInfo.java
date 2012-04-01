// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PicInfo.proto

package com.utils;

public final class GoodsPicInfo {

    private GoodsPicInfo () {

    }

    public static void registerAllExtensions (com.google.protobuf.ExtensionRegistry registry) {

    }

    public interface PicInfoOrBuilder extends com.google.protobuf.MessageOrBuilder {

        // required int32 ID = 1;
        boolean hasID ();

        int getID ();

        // required int64 GoodID = 2;
        boolean hasGoodID ();

        long getGoodID ();

        // required string Url = 3;
        boolean hasUrl ();

        String getUrl ();

        // required string Guid = 4;
        boolean hasGuid ();

        String getGuid ();

        // required string Type = 5;
        boolean hasType ();

        String getType ();

        // required int32 Order = 6;
        boolean hasOrder ();

        int getOrder ();
    }

    public static final class PicInfo extends com.google.protobuf.GeneratedMessage implements PicInfoOrBuilder {

        // Use PicInfo.newBuilder() to construct.
        private PicInfo (Builder builder) {

            super (builder);
        }

        private PicInfo (boolean noInit) {

        }

        private static final PicInfo defaultInstance;

        public static PicInfo getDefaultInstance () {

            return defaultInstance;
        }

        public PicInfo getDefaultInstanceForType () {

            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor () {

            return com.utils.GoodsPicInfo.internal_static_com_utils_PicInfo_descriptor;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable () {

            return com.utils.GoodsPicInfo.internal_static_com_utils_PicInfo_fieldAccessorTable;
        }

        private int             bitField0_;
        // required int32 ID = 1;
        public static final int ID_FIELD_NUMBER = 1;
        private int             iD_;

        public boolean hasID () {

            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        public int getID () {

            return iD_;
        }

        // required int64 GoodID = 2;
        public static final int GOODID_FIELD_NUMBER = 2;
        private long            goodID_;

        public boolean hasGoodID () {

            return ((bitField0_ & 0x00000002) == 0x00000002);
        }

        public long getGoodID () {

            return goodID_;
        }

        // required string Url = 3;
        public static final int  URL_FIELD_NUMBER = 3;
        private java.lang.Object url_;

        public boolean hasUrl () {

            return ((bitField0_ & 0x00000004) == 0x00000004);
        }

        public String getUrl () {

            java.lang.Object ref = url_;
            if (ref instanceof String) {
                return (String) ref;
            }
            else {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                String s = bs.toStringUtf8 ();
                if (com.google.protobuf.Internal.isValidUtf8 (bs)) {
                    url_ = s;
                }
                return s;
            }
        }

        private com.google.protobuf.ByteString getUrlBytes () {

            java.lang.Object ref = url_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8 ((String) ref);
                url_ = b;
                return b;
            }
            else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        // required string Guid = 4;
        public static final int  GUID_FIELD_NUMBER = 4;
        private java.lang.Object guid_;

        public boolean hasGuid () {

            return ((bitField0_ & 0x00000008) == 0x00000008);
        }

        public String getGuid () {

            java.lang.Object ref = guid_;
            if (ref instanceof String) {
                return (String) ref;
            }
            else {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                String s = bs.toStringUtf8 ();
                if (com.google.protobuf.Internal.isValidUtf8 (bs)) {
                    guid_ = s;
                }
                return s;
            }
        }

        private com.google.protobuf.ByteString getGuidBytes () {

            java.lang.Object ref = guid_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8 ((String) ref);
                guid_ = b;
                return b;
            }
            else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        // required string Type = 5;
        public static final int  TYPE_FIELD_NUMBER = 5;
        private java.lang.Object type_;

        public boolean hasType () {

            return ((bitField0_ & 0x00000010) == 0x00000010);
        }

        public String getType () {

            java.lang.Object ref = type_;
            if (ref instanceof String) {
                return (String) ref;
            }
            else {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                String s = bs.toStringUtf8 ();
                if (com.google.protobuf.Internal.isValidUtf8 (bs)) {
                    type_ = s;
                }
                return s;
            }
        }

        private com.google.protobuf.ByteString getTypeBytes () {

            java.lang.Object ref = type_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8 ((String) ref);
                type_ = b;
                return b;
            }
            else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        // required int32 Order = 6;
        public static final int ORDER_FIELD_NUMBER = 6;
        private int             order_;

        public boolean hasOrder () {

            return ((bitField0_ & 0x00000020) == 0x00000020);
        }

        public int getOrder () {

            return order_;
        }

        private void initFields () {

            iD_ = 0;
            goodID_ = 0L;
            url_ = "";
            guid_ = "";
            type_ = "";
            order_ = 0;
        }
        private byte memoizedIsInitialized = -1;

        public final boolean isInitialized () {

            byte isInitialized = memoizedIsInitialized;
            if (isInitialized != -1)
                return isInitialized == 1;

            if (!hasID ()) {
                memoizedIsInitialized = 0;
                return false;
            }
            if (!hasGoodID ()) {
                memoizedIsInitialized = 0;
                return false;
            }
            if (!hasUrl ()) {
                memoizedIsInitialized = 0;
                return false;
            }
            if (!hasGuid ()) {
                memoizedIsInitialized = 0;
                return false;
            }
            if (!hasType ()) {
                memoizedIsInitialized = 0;
                return false;
            }
            if (!hasOrder ()) {
                memoizedIsInitialized = 0;
                return false;
            }
            memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo (com.google.protobuf.CodedOutputStream output) throws java.io.IOException {

            getSerializedSize ();
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                output.writeInt32 (1, iD_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                output.writeInt64 (2, goodID_);
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                output.writeBytes (3, getUrlBytes ());
            }
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
                output.writeBytes (4, getGuidBytes ());
            }
            if (((bitField0_ & 0x00000010) == 0x00000010)) {
                output.writeBytes (5, getTypeBytes ());
            }
            if (((bitField0_ & 0x00000020) == 0x00000020)) {
                output.writeInt32 (6, order_);
            }
            getUnknownFields ().writeTo (output);
        }

        private int memoizedSerializedSize = -1;

        public int getSerializedSize () {

            int size = memoizedSerializedSize;
            if (size != -1)
                return size;

            size = 0;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                size += com.google.protobuf.CodedOutputStream.computeInt32Size (1, iD_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                size += com.google.protobuf.CodedOutputStream.computeInt64Size (2, goodID_);
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                size += com.google.protobuf.CodedOutputStream.computeBytesSize (3, getUrlBytes ());
            }
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
                size += com.google.protobuf.CodedOutputStream.computeBytesSize (4, getGuidBytes ());
            }
            if (((bitField0_ & 0x00000010) == 0x00000010)) {
                size += com.google.protobuf.CodedOutputStream.computeBytesSize (5, getTypeBytes ());
            }
            if (((bitField0_ & 0x00000020) == 0x00000020)) {
                size += com.google.protobuf.CodedOutputStream.computeInt32Size (6, order_);
            }
            size += getUnknownFields ().getSerializedSize ();
            memoizedSerializedSize = size;
            return size;
        }

        private static final long serialVersionUID = 0L;

        @java.lang.Override
        protected java.lang.Object writeReplace () throws java.io.ObjectStreamException {

            return super.writeReplace ();
        }

        public static com.utils.GoodsPicInfo.PicInfo parseFrom (com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {

            return newBuilder ().mergeFrom (data).buildParsed ();
        }

        public static com.utils.GoodsPicInfo.PicInfo parseFrom (com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {

            return newBuilder ().mergeFrom (data, extensionRegistry).buildParsed ();
        }

        public static com.utils.GoodsPicInfo.PicInfo parseFrom (byte [] data)
                throws com.google.protobuf.InvalidProtocolBufferException {

            return newBuilder ().mergeFrom (data).buildParsed ();
        }

        public static com.utils.GoodsPicInfo.PicInfo parseFrom (byte [] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {

            return newBuilder ().mergeFrom (data, extensionRegistry).buildParsed ();
        }

        public static com.utils.GoodsPicInfo.PicInfo parseFrom (java.io.InputStream input) throws java.io.IOException {

            return newBuilder ().mergeFrom (input).buildParsed ();
        }

        public static com.utils.GoodsPicInfo.PicInfo parseFrom (java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {

            return newBuilder ().mergeFrom (input, extensionRegistry).buildParsed ();
        }

        public static com.utils.GoodsPicInfo.PicInfo parseDelimitedFrom (java.io.InputStream input)
                throws java.io.IOException {

            Builder builder = newBuilder ();
            if (builder.mergeDelimitedFrom (input)) {
                return builder.buildParsed ();
            }
            else {
                return null;
            }
        }

        public static com.utils.GoodsPicInfo.PicInfo parseDelimitedFrom (java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {

            Builder builder = newBuilder ();
            if (builder.mergeDelimitedFrom (input, extensionRegistry)) {
                return builder.buildParsed ();
            }
            else {
                return null;
            }
        }

        public static com.utils.GoodsPicInfo.PicInfo parseFrom (com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {

            return newBuilder ().mergeFrom (input).buildParsed ();
        }

        public static com.utils.GoodsPicInfo.PicInfo parseFrom (com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {

            return newBuilder ().mergeFrom (input, extensionRegistry).buildParsed ();
        }

        public static Builder newBuilder () {

            return Builder.create ();
        }

        public Builder newBuilderForType () {

            return newBuilder ();
        }

        public static Builder newBuilder (com.utils.GoodsPicInfo.PicInfo prototype) {

            return newBuilder ().mergeFrom (prototype);
        }

        public Builder toBuilder () {

            return newBuilder (this);
        }

        @java.lang.Override
        protected Builder newBuilderForType (com.google.protobuf.GeneratedMessage.BuilderParent parent) {

            Builder builder = new Builder (parent);
            return builder;
        }

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder <Builder> implements
                com.utils.GoodsPicInfo.PicInfoOrBuilder {

            public static final com.google.protobuf.Descriptors.Descriptor getDescriptor () {

                return com.utils.GoodsPicInfo.internal_static_com_utils_PicInfo_descriptor;
            }

            protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable () {

                return com.utils.GoodsPicInfo.internal_static_com_utils_PicInfo_fieldAccessorTable;
            }

            // Construct using com.utils.GoodsPicInfo.PicInfo.newBuilder()
            private Builder () {

                maybeForceBuilderInitialization ();
            }

            private Builder (BuilderParent parent) {

                super (parent);
                maybeForceBuilderInitialization ();
            }

            private void maybeForceBuilderInitialization () {

                if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                }
            }

            private static Builder create () {

                return new Builder ();
            }

            public Builder clear () {

                super.clear ();
                iD_ = 0;
                bitField0_ = (bitField0_ & ~0x00000001);
                goodID_ = 0L;
                bitField0_ = (bitField0_ & ~0x00000002);
                url_ = "";
                bitField0_ = (bitField0_ & ~0x00000004);
                guid_ = "";
                bitField0_ = (bitField0_ & ~0x00000008);
                type_ = "";
                bitField0_ = (bitField0_ & ~0x00000010);
                order_ = 0;
                bitField0_ = (bitField0_ & ~0x00000020);
                return this;
            }

            public Builder clone () {

                return create ().mergeFrom (buildPartial ());
            }

            public com.google.protobuf.Descriptors.Descriptor getDescriptorForType () {

                return com.utils.GoodsPicInfo.PicInfo.getDescriptor ();
            }

            public com.utils.GoodsPicInfo.PicInfo getDefaultInstanceForType () {

                return com.utils.GoodsPicInfo.PicInfo.getDefaultInstance ();
            }

            public com.utils.GoodsPicInfo.PicInfo build () {

                com.utils.GoodsPicInfo.PicInfo result = buildPartial ();
                if (!result.isInitialized ()) {
                    throw newUninitializedMessageException (result);
                }
                return result;
            }

            private com.utils.GoodsPicInfo.PicInfo buildParsed ()
                    throws com.google.protobuf.InvalidProtocolBufferException {

                com.utils.GoodsPicInfo.PicInfo result = buildPartial ();
                if (!result.isInitialized ()) {
                    throw newUninitializedMessageException (result).asInvalidProtocolBufferException ();
                }
                return result;
            }

            public com.utils.GoodsPicInfo.PicInfo buildPartial () {

                com.utils.GoodsPicInfo.PicInfo result = new com.utils.GoodsPicInfo.PicInfo (this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.iD_ = iD_;
                if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
                    to_bitField0_ |= 0x00000002;
                }
                result.goodID_ = goodID_;
                if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
                    to_bitField0_ |= 0x00000004;
                }
                result.url_ = url_;
                if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
                    to_bitField0_ |= 0x00000008;
                }
                result.guid_ = guid_;
                if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
                    to_bitField0_ |= 0x00000010;
                }
                result.type_ = type_;
                if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
                    to_bitField0_ |= 0x00000020;
                }
                result.order_ = order_;
                result.bitField0_ = to_bitField0_;
                onBuilt ();
                return result;
            }

            public Builder mergeFrom (com.google.protobuf.Message other) {

                if (other instanceof com.utils.GoodsPicInfo.PicInfo) {
                    return mergeFrom ((com.utils.GoodsPicInfo.PicInfo) other);
                }
                else {
                    super.mergeFrom (other);
                    return this;
                }
            }

            public Builder mergeFrom (com.utils.GoodsPicInfo.PicInfo other) {

                if (other == com.utils.GoodsPicInfo.PicInfo.getDefaultInstance ())
                    return this;
                if (other.hasID ()) {
                    setID (other.getID ());
                }
                if (other.hasGoodID ()) {
                    setGoodID (other.getGoodID ());
                }
                if (other.hasUrl ()) {
                    setUrl (other.getUrl ());
                }
                if (other.hasGuid ()) {
                    setGuid (other.getGuid ());
                }
                if (other.hasType ()) {
                    setType (other.getType ());
                }
                if (other.hasOrder ()) {
                    setOrder (other.getOrder ());
                }
                this.mergeUnknownFields (other.getUnknownFields ());
                return this;
            }

            public final boolean isInitialized () {

                if (!hasID ()) {

                    return false;
                }
                if (!hasGoodID ()) {

                    return false;
                }
                if (!hasUrl ()) {

                    return false;
                }
                if (!hasGuid ()) {

                    return false;
                }
                if (!hasType ()) {

                    return false;
                }
                if (!hasOrder ()) {

                    return false;
                }
                return true;
            }

            public Builder mergeFrom (com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {

                com.google.protobuf.UnknownFieldSet.Builder unknownFields = com.google.protobuf.UnknownFieldSet
                        .newBuilder (this.getUnknownFields ());
                while (true) {
                    int tag = input.readTag ();
                    switch (tag) {
                    case 0:
                        this.setUnknownFields (unknownFields.build ());
                        onChanged ();
                        return this;
                    default: {
                        if (!parseUnknownField (input, unknownFields, extensionRegistry, tag)) {
                            this.setUnknownFields (unknownFields.build ());
                            onChanged ();
                            return this;
                        }
                        break;
                    }
                    case 8: {
                        bitField0_ |= 0x00000001;
                        iD_ = input.readInt32 ();
                        break;
                    }
                    case 16: {
                        bitField0_ |= 0x00000002;
                        goodID_ = input.readInt64 ();
                        break;
                    }
                    case 26: {
                        bitField0_ |= 0x00000004;
                        url_ = input.readBytes ();
                        break;
                    }
                    case 34: {
                        bitField0_ |= 0x00000008;
                        guid_ = input.readBytes ();
                        break;
                    }
                    case 42: {
                        bitField0_ |= 0x00000010;
                        type_ = input.readBytes ();
                        break;
                    }
                    case 48: {
                        bitField0_ |= 0x00000020;
                        order_ = input.readInt32 ();
                        break;
                    }
                    }
                }
            }

            private int bitField0_;

            // required int32 ID = 1;
            private int iD_;

            public boolean hasID () {

                return ((bitField0_ & 0x00000001) == 0x00000001);
            }

            public int getID () {

                return iD_;
            }

            public Builder setID (int value) {

                bitField0_ |= 0x00000001;
                iD_ = value;
                onChanged ();
                return this;
            }

            public Builder clearID () {

                bitField0_ = (bitField0_ & ~0x00000001);
                iD_ = 0;
                onChanged ();
                return this;
            }

            // required int64 GoodID = 2;
            private long goodID_;

            public boolean hasGoodID () {

                return ((bitField0_ & 0x00000002) == 0x00000002);
            }

            public long getGoodID () {

                return goodID_;
            }

            public Builder setGoodID (long value) {

                bitField0_ |= 0x00000002;
                goodID_ = value;
                onChanged ();
                return this;
            }

            public Builder clearGoodID () {

                bitField0_ = (bitField0_ & ~0x00000002);
                goodID_ = 0L;
                onChanged ();
                return this;
            }

            // required string Url = 3;
            private java.lang.Object url_ = "";

            public boolean hasUrl () {

                return ((bitField0_ & 0x00000004) == 0x00000004);
            }

            public String getUrl () {

                java.lang.Object ref = url_;
                if (!(ref instanceof String)) {
                    String s = ((com.google.protobuf.ByteString) ref).toStringUtf8 ();
                    url_ = s;
                    return s;
                }
                else {
                    return (String) ref;
                }
            }

            public Builder setUrl (String value) {

                if (value == null) {
                    throw new NullPointerException ();
                }
                bitField0_ |= 0x00000004;
                url_ = value;
                onChanged ();
                return this;
            }

            public Builder clearUrl () {

                bitField0_ = (bitField0_ & ~0x00000004);
                url_ = getDefaultInstance ().getUrl ();
                onChanged ();
                return this;
            }

            void setUrl (com.google.protobuf.ByteString value) {

                bitField0_ |= 0x00000004;
                url_ = value;
                onChanged ();
            }

            // required string Guid = 4;
            private java.lang.Object guid_ = "";

            public boolean hasGuid () {

                return ((bitField0_ & 0x00000008) == 0x00000008);
            }

            public String getGuid () {

                java.lang.Object ref = guid_;
                if (!(ref instanceof String)) {
                    String s = ((com.google.protobuf.ByteString) ref).toStringUtf8 ();
                    guid_ = s;
                    return s;
                }
                else {
                    return (String) ref;
                }
            }

            public Builder setGuid (String value) {

                if (value == null) {
                    throw new NullPointerException ();
                }
                bitField0_ |= 0x00000008;
                guid_ = value;
                onChanged ();
                return this;
            }

            public Builder clearGuid () {

                bitField0_ = (bitField0_ & ~0x00000008);
                guid_ = getDefaultInstance ().getGuid ();
                onChanged ();
                return this;
            }

            void setGuid (com.google.protobuf.ByteString value) {

                bitField0_ |= 0x00000008;
                guid_ = value;
                onChanged ();
            }

            // required string Type = 5;
            private java.lang.Object type_ = "";

            public boolean hasType () {

                return ((bitField0_ & 0x00000010) == 0x00000010);
            }

            public String getType () {

                java.lang.Object ref = type_;
                if (!(ref instanceof String)) {
                    String s = ((com.google.protobuf.ByteString) ref).toStringUtf8 ();
                    type_ = s;
                    return s;
                }
                else {
                    return (String) ref;
                }
            }

            public Builder setType (String value) {

                if (value == null) {
                    throw new NullPointerException ();
                }
                bitField0_ |= 0x00000010;
                type_ = value;
                onChanged ();
                return this;
            }

            public Builder clearType () {

                bitField0_ = (bitField0_ & ~0x00000010);
                type_ = getDefaultInstance ().getType ();
                onChanged ();
                return this;
            }

            void setType (com.google.protobuf.ByteString value) {

                bitField0_ |= 0x00000010;
                type_ = value;
                onChanged ();
            }

            // required int32 Order = 6;
            private int order_;

            public boolean hasOrder () {

                return ((bitField0_ & 0x00000020) == 0x00000020);
            }

            public int getOrder () {

                return order_;
            }

            public Builder setOrder (int value) {

                bitField0_ |= 0x00000020;
                order_ = value;
                onChanged ();
                return this;
            }

            public Builder clearOrder () {

                bitField0_ = (bitField0_ & ~0x00000020);
                order_ = 0;
                onChanged ();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:com.utils.PicInfo)
        }

        static {
            defaultInstance = new PicInfo (true);
            defaultInstance.initFields ();
        }

        // @@protoc_insertion_point(class_scope:com.utils.PicInfo)
    }

    private static com.google.protobuf.Descriptors.Descriptor              internal_static_com_utils_PicInfo_descriptor;
    private static com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_com_utils_PicInfo_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor () {

        return descriptor;
    }
    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {
            "\n\rPicInfo.proto\022\tcom.utils\"]\n\007PicInfo\022\n\n"
                    + "\002ID\030\001 \002(\005\022\016\n\006GoodID\030\002 \002(\003\022\013\n\003Url\030\003 \002(\t\022\014"
                    + "\n\004Guid\030\004 \002(\t\022\014\n\004Type\030\005 \002(\t\022\r\n\005Order\030\006 \002("
                    + "\005B\031\n\tcom.utilsB\014GoodsPicInfo"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner () {

            public com.google.protobuf.ExtensionRegistry assignDescriptors (
                    com.google.protobuf.Descriptors.FileDescriptor root) {

                descriptor = root;
                internal_static_com_utils_PicInfo_descriptor = getDescriptor ().getMessageTypes ().get (0);
                internal_static_com_utils_PicInfo_fieldAccessorTable = new com.google.protobuf.GeneratedMessage.FieldAccessorTable (
                        internal_static_com_utils_PicInfo_descriptor, new java.lang.String [] {
            "ID", "GoodID", "Url", "Guid", "Type", "Order",
                        }, com.utils.GoodsPicInfo.PicInfo.class, com.utils.GoodsPicInfo.PicInfo.Builder.class);
                return null;
            }
        };
        com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom (descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor [] {}, assigner);
    }

    // @@protoc_insertion_point(outer_class_scope)
}