// Generated by the protocol buffer compiler.  DO NOT EDIT!

package protobuf_unittest;

public interface MessageWithNoOuterOrBuilder
    extends com.google.protobuf.MessageOrBuilder {
  
  // optional .protobuf_unittest.MessageWithNoOuter.NestedMessage nested = 1;
  boolean hasNested();
  protobuf_unittest.MessageWithNoOuter.NestedMessage getNested();
  protobuf_unittest.MessageWithNoOuter.NestedMessageOrBuilder getNestedOrBuilder();
  
  // repeated .protobuf_unittest.TestAllTypes foreign = 2;
  java.util.List<protobuf_unittest.UnittestProto.TestAllTypes> 
      getForeignList();
  protobuf_unittest.UnittestProto.TestAllTypes getForeign(int index);
  int getForeignCount();
  java.util.List<? extends protobuf_unittest.UnittestProto.TestAllTypesOrBuilder> 
      getForeignOrBuilderList();
  protobuf_unittest.UnittestProto.TestAllTypesOrBuilder getForeignOrBuilder(
      int index);
  
  // optional .protobuf_unittest.MessageWithNoOuter.NestedEnum nested_enum = 3;
  boolean hasNestedEnum();
  protobuf_unittest.MessageWithNoOuter.NestedEnum getNestedEnum();
  
  // optional .protobuf_unittest.EnumWithNoOuter foreign_enum = 4;
  boolean hasForeignEnum();
  protobuf_unittest.EnumWithNoOuter getForeignEnum();
}