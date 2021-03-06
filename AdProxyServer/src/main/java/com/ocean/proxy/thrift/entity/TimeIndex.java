/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.ocean.proxy.thrift.entity;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2017-1-13")
public class TimeIndex implements org.apache.thrift.TBase<TimeIndex, TimeIndex._Fields>, java.io.Serializable, Cloneable, Comparable<TimeIndex> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TimeIndex");

  private static final org.apache.thrift.protocol.TField FIRST_FIELD_DESC = new org.apache.thrift.protocol.TField("first", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField SEC_FIELD_DESC = new org.apache.thrift.protocol.TField("sec", org.apache.thrift.protocol.TType.I64, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TimeIndexStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TimeIndexTupleSchemeFactory());
  }

  public int first; // required
  public long sec; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    FIRST((short)1, "first"),
    SEC((short)2, "sec");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // FIRST
          return FIRST;
        case 2: // SEC
          return SEC;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __FIRST_ISSET_ID = 0;
  private static final int __SEC_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.FIRST, new org.apache.thrift.meta_data.FieldMetaData("first", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.SEC, new org.apache.thrift.meta_data.FieldMetaData("sec", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TimeIndex.class, metaDataMap);
  }

  public TimeIndex() {
  }

  public TimeIndex(
    int first,
    long sec)
  {
    this();
    this.first = first;
    setFirstIsSet(true);
    this.sec = sec;
    setSecIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TimeIndex(TimeIndex other) {
    __isset_bitfield = other.__isset_bitfield;
    this.first = other.first;
    this.sec = other.sec;
  }

  public TimeIndex deepCopy() {
    return new TimeIndex(this);
  }

  
  public void clear() {
    setFirstIsSet(false);
    this.first = 0;
    setSecIsSet(false);
    this.sec = 0;
  }

  public int getFirst() {
    return this.first;
  }

  public TimeIndex setFirst(int first) {
    this.first = first;
    setFirstIsSet(true);
    return this;
  }

  public void unsetFirst() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __FIRST_ISSET_ID);
  }

  /** Returns true if field first is set (has been assigned a value) and false otherwise */
  public boolean isSetFirst() {
    return EncodingUtils.testBit(__isset_bitfield, __FIRST_ISSET_ID);
  }

  public void setFirstIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __FIRST_ISSET_ID, value);
  }

  public long getSec() {
    return this.sec;
  }

  public TimeIndex setSec(long sec) {
    this.sec = sec;
    setSecIsSet(true);
    return this;
  }

  public void unsetSec() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SEC_ISSET_ID);
  }

  /** Returns true if field sec is set (has been assigned a value) and false otherwise */
  public boolean isSetSec() {
    return EncodingUtils.testBit(__isset_bitfield, __SEC_ISSET_ID);
  }

  public void setSecIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SEC_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case FIRST:
      if (value == null) {
        unsetFirst();
      } else {
        setFirst((Integer)value);
      }
      break;

    case SEC:
      if (value == null) {
        unsetSec();
      } else {
        setSec((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case FIRST:
      return Integer.valueOf(getFirst());

    case SEC:
      return Long.valueOf(getSec());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case FIRST:
      return isSetFirst();
    case SEC:
      return isSetSec();
    }
    throw new IllegalStateException();
  }

  
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TimeIndex)
      return this.equals((TimeIndex)that);
    return false;
  }

  public boolean equals(TimeIndex that) {
    if (that == null)
      return false;

    boolean this_present_first = true;
    boolean that_present_first = true;
    if (this_present_first || that_present_first) {
      if (!(this_present_first && that_present_first))
        return false;
      if (this.first != that.first)
        return false;
    }

    boolean this_present_sec = true;
    boolean that_present_sec = true;
    if (this_present_sec || that_present_sec) {
      if (!(this_present_sec && that_present_sec))
        return false;
      if (this.sec != that.sec)
        return false;
    }

    return true;
  }

  
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_first = true;
    list.add(present_first);
    if (present_first)
      list.add(first);

    boolean present_sec = true;
    list.add(present_sec);
    if (present_sec)
      list.add(sec);

    return list.hashCode();
  }

  
  public int compareTo(TimeIndex other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetFirst()).compareTo(other.isSetFirst());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFirst()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.first, other.first);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSec()).compareTo(other.isSetSec());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSec()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sec, other.sec);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  
  public String toString() {
    StringBuilder sb = new StringBuilder("TimeIndex(");
    boolean first = true;

    sb.append("first:");
    sb.append(this.first);
    first = false;
    if (!first) sb.append(", ");
    sb.append("sec:");
    sb.append(this.sec);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'first' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'sec' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TimeIndexStandardSchemeFactory implements SchemeFactory {
    public TimeIndexStandardScheme getScheme() {
      return new TimeIndexStandardScheme();
    }
  }

  private static class TimeIndexStandardScheme extends StandardScheme<TimeIndex> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TimeIndex struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // FIRST
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.first = iprot.readI32();
              struct.setFirstIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SEC
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.sec = iprot.readI64();
              struct.setSecIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetFirst()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'first' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetSec()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'sec' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TimeIndex struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(FIRST_FIELD_DESC);
      oprot.writeI32(struct.first);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(SEC_FIELD_DESC);
      oprot.writeI64(struct.sec);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TimeIndexTupleSchemeFactory implements SchemeFactory {
    public TimeIndexTupleScheme getScheme() {
      return new TimeIndexTupleScheme();
    }
  }

  private static class TimeIndexTupleScheme extends TupleScheme<TimeIndex> {

    
    public void write(org.apache.thrift.protocol.TProtocol prot, TimeIndex struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.first);
      oprot.writeI64(struct.sec);
    }

    
    public void read(org.apache.thrift.protocol.TProtocol prot, TimeIndex struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.first = iprot.readI32();
      struct.setFirstIsSet(true);
      struct.sec = iprot.readI64();
      struct.setSecIsSet(true);
    }
  }

}

