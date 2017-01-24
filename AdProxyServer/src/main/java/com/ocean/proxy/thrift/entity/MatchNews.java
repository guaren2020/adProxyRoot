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
public class MatchNews implements org.apache.thrift.TBase<MatchNews, MatchNews._Fields>, java.io.Serializable, Cloneable, Comparable<MatchNews> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("MatchNews");

  private static final org.apache.thrift.protocol.TField INFOID_FIELD_DESC = new org.apache.thrift.protocol.TField("infoid", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField TITLE_FIELD_DESC = new org.apache.thrift.protocol.TField("title", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField RSSID_FIELD_DESC = new org.apache.thrift.protocol.TField("rssid", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField TAGS_FIELD_DESC = new org.apache.thrift.protocol.TField("tags", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new MatchNewsStandardSchemeFactory());
    schemes.put(TupleScheme.class, new MatchNewsTupleSchemeFactory());
  }

  public long infoid; // required
  public String title; // required
  public int rssid; // required
  public String tags; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    INFOID((short)1, "infoid"),
    TITLE((short)2, "title"),
    RSSID((short)3, "rssid"),
    TAGS((short)4, "tags");

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
        case 1: // INFOID
          return INFOID;
        case 2: // TITLE
          return TITLE;
        case 3: // RSSID
          return RSSID;
        case 4: // TAGS
          return TAGS;
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
  private static final int __INFOID_ISSET_ID = 0;
  private static final int __RSSID_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.INFOID, new org.apache.thrift.meta_data.FieldMetaData("infoid", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TITLE, new org.apache.thrift.meta_data.FieldMetaData("title", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.RSSID, new org.apache.thrift.meta_data.FieldMetaData("rssid", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TAGS, new org.apache.thrift.meta_data.FieldMetaData("tags", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(MatchNews.class, metaDataMap);
  }

  public MatchNews() {
  }

  public MatchNews(
    long infoid,
    String title,
    int rssid,
    String tags)
  {
    this();
    this.infoid = infoid;
    setInfoidIsSet(true);
    this.title = title;
    this.rssid = rssid;
    setRssidIsSet(true);
    this.tags = tags;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public MatchNews(MatchNews other) {
    __isset_bitfield = other.__isset_bitfield;
    this.infoid = other.infoid;
    if (other.isSetTitle()) {
      this.title = other.title;
    }
    this.rssid = other.rssid;
    if (other.isSetTags()) {
      this.tags = other.tags;
    }
  }

  public MatchNews deepCopy() {
    return new MatchNews(this);
  }

  
  public void clear() {
    setInfoidIsSet(false);
    this.infoid = 0;
    this.title = null;
    setRssidIsSet(false);
    this.rssid = 0;
    this.tags = null;
  }

  public long getInfoid() {
    return this.infoid;
  }

  public MatchNews setInfoid(long infoid) {
    this.infoid = infoid;
    setInfoidIsSet(true);
    return this;
  }

  public void unsetInfoid() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __INFOID_ISSET_ID);
  }

  /** Returns true if field infoid is set (has been assigned a value) and false otherwise */
  public boolean isSetInfoid() {
    return EncodingUtils.testBit(__isset_bitfield, __INFOID_ISSET_ID);
  }

  public void setInfoidIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __INFOID_ISSET_ID, value);
  }

  public String getTitle() {
    return this.title;
  }

  public MatchNews setTitle(String title) {
    this.title = title;
    return this;
  }

  public void unsetTitle() {
    this.title = null;
  }

  /** Returns true if field title is set (has been assigned a value) and false otherwise */
  public boolean isSetTitle() {
    return this.title != null;
  }

  public void setTitleIsSet(boolean value) {
    if (!value) {
      this.title = null;
    }
  }

  public int getRssid() {
    return this.rssid;
  }

  public MatchNews setRssid(int rssid) {
    this.rssid = rssid;
    setRssidIsSet(true);
    return this;
  }

  public void unsetRssid() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __RSSID_ISSET_ID);
  }

  /** Returns true if field rssid is set (has been assigned a value) and false otherwise */
  public boolean isSetRssid() {
    return EncodingUtils.testBit(__isset_bitfield, __RSSID_ISSET_ID);
  }

  public void setRssidIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __RSSID_ISSET_ID, value);
  }

  public String getTags() {
    return this.tags;
  }

  public MatchNews setTags(String tags) {
    this.tags = tags;
    return this;
  }

  public void unsetTags() {
    this.tags = null;
  }

  /** Returns true if field tags is set (has been assigned a value) and false otherwise */
  public boolean isSetTags() {
    return this.tags != null;
  }

  public void setTagsIsSet(boolean value) {
    if (!value) {
      this.tags = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case INFOID:
      if (value == null) {
        unsetInfoid();
      } else {
        setInfoid((Long)value);
      }
      break;

    case TITLE:
      if (value == null) {
        unsetTitle();
      } else {
        setTitle((String)value);
      }
      break;

    case RSSID:
      if (value == null) {
        unsetRssid();
      } else {
        setRssid((Integer)value);
      }
      break;

    case TAGS:
      if (value == null) {
        unsetTags();
      } else {
        setTags((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case INFOID:
      return Long.valueOf(getInfoid());

    case TITLE:
      return getTitle();

    case RSSID:
      return Integer.valueOf(getRssid());

    case TAGS:
      return getTags();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case INFOID:
      return isSetInfoid();
    case TITLE:
      return isSetTitle();
    case RSSID:
      return isSetRssid();
    case TAGS:
      return isSetTags();
    }
    throw new IllegalStateException();
  }

  
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof MatchNews)
      return this.equals((MatchNews)that);
    return false;
  }

  public boolean equals(MatchNews that) {
    if (that == null)
      return false;

    boolean this_present_infoid = true;
    boolean that_present_infoid = true;
    if (this_present_infoid || that_present_infoid) {
      if (!(this_present_infoid && that_present_infoid))
        return false;
      if (this.infoid != that.infoid)
        return false;
    }

    boolean this_present_title = true && this.isSetTitle();
    boolean that_present_title = true && that.isSetTitle();
    if (this_present_title || that_present_title) {
      if (!(this_present_title && that_present_title))
        return false;
      if (!this.title.equals(that.title))
        return false;
    }

    boolean this_present_rssid = true;
    boolean that_present_rssid = true;
    if (this_present_rssid || that_present_rssid) {
      if (!(this_present_rssid && that_present_rssid))
        return false;
      if (this.rssid != that.rssid)
        return false;
    }

    boolean this_present_tags = true && this.isSetTags();
    boolean that_present_tags = true && that.isSetTags();
    if (this_present_tags || that_present_tags) {
      if (!(this_present_tags && that_present_tags))
        return false;
      if (!this.tags.equals(that.tags))
        return false;
    }

    return true;
  }

  
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_infoid = true;
    list.add(present_infoid);
    if (present_infoid)
      list.add(infoid);

    boolean present_title = true && (isSetTitle());
    list.add(present_title);
    if (present_title)
      list.add(title);

    boolean present_rssid = true;
    list.add(present_rssid);
    if (present_rssid)
      list.add(rssid);

    boolean present_tags = true && (isSetTags());
    list.add(present_tags);
    if (present_tags)
      list.add(tags);

    return list.hashCode();
  }

  
  public int compareTo(MatchNews other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetInfoid()).compareTo(other.isSetInfoid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetInfoid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.infoid, other.infoid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTitle()).compareTo(other.isSetTitle());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTitle()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.title, other.title);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRssid()).compareTo(other.isSetRssid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRssid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.rssid, other.rssid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTags()).compareTo(other.isSetTags());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTags()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tags, other.tags);
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
    StringBuilder sb = new StringBuilder("MatchNews(");
    boolean first = true;

    sb.append("infoid:");
    sb.append(this.infoid);
    first = false;
    if (!first) sb.append(", ");
    sb.append("title:");
    if (this.title == null) {
      sb.append("null");
    } else {
      sb.append(this.title);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("rssid:");
    sb.append(this.rssid);
    first = false;
    if (!first) sb.append(", ");
    sb.append("tags:");
    if (this.tags == null) {
      sb.append("null");
    } else {
      sb.append(this.tags);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'infoid' because it's a primitive and you chose the non-beans generator.
    if (title == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'title' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'rssid' because it's a primitive and you chose the non-beans generator.
    if (tags == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'tags' was not present! Struct: " + toString());
    }
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

  private static class MatchNewsStandardSchemeFactory implements SchemeFactory {
    public MatchNewsStandardScheme getScheme() {
      return new MatchNewsStandardScheme();
    }
  }

  private static class MatchNewsStandardScheme extends StandardScheme<MatchNews> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, MatchNews struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // INFOID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.infoid = iprot.readI64();
              struct.setInfoidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TITLE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.title = iprot.readString();
              struct.setTitleIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // RSSID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.rssid = iprot.readI32();
              struct.setRssidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // TAGS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.tags = iprot.readString();
              struct.setTagsIsSet(true);
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
      if (!struct.isSetInfoid()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'infoid' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetRssid()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'rssid' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, MatchNews struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(INFOID_FIELD_DESC);
      oprot.writeI64(struct.infoid);
      oprot.writeFieldEnd();
      if (struct.title != null) {
        oprot.writeFieldBegin(TITLE_FIELD_DESC);
        oprot.writeString(struct.title);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(RSSID_FIELD_DESC);
      oprot.writeI32(struct.rssid);
      oprot.writeFieldEnd();
      if (struct.tags != null) {
        oprot.writeFieldBegin(TAGS_FIELD_DESC);
        oprot.writeString(struct.tags);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class MatchNewsTupleSchemeFactory implements SchemeFactory {
    public MatchNewsTupleScheme getScheme() {
      return new MatchNewsTupleScheme();
    }
  }

  private static class MatchNewsTupleScheme extends TupleScheme<MatchNews> {

    
    public void write(org.apache.thrift.protocol.TProtocol prot, MatchNews struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI64(struct.infoid);
      oprot.writeString(struct.title);
      oprot.writeI32(struct.rssid);
      oprot.writeString(struct.tags);
    }

    
    public void read(org.apache.thrift.protocol.TProtocol prot, MatchNews struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.infoid = iprot.readI64();
      struct.setInfoidIsSet(true);
      struct.title = iprot.readString();
      struct.setTitleIsSet(true);
      struct.rssid = iprot.readI32();
      struct.setRssidIsSet(true);
      struct.tags = iprot.readString();
      struct.setTagsIsSet(true);
    }
  }

}
