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
public class TransportValue implements org.apache.thrift.TBase<TransportValue, TransportValue._Fields>, java.io.Serializable, Cloneable, Comparable<TransportValue> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TransportValue");

  private static final org.apache.thrift.protocol.TField APP_FIELD_DESC = new org.apache.thrift.protocol.TField("app", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField SERVER_FIELD_DESC = new org.apache.thrift.protocol.TField("server", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField FUN_FIELD_DESC = new org.apache.thrift.protocol.TField("fun", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField PARAM_FIELD_DESC = new org.apache.thrift.protocol.TField("param", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TransportValueStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TransportValueTupleSchemeFactory());
  }

  public String app; // required
  public String server; // required
  public String fun; // required
  public String param; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    APP((short)1, "app"),
    SERVER((short)2, "server"),
    FUN((short)3, "fun"),
    PARAM((short)4, "param");

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
        case 1: // APP
          return APP;
        case 2: // SERVER
          return SERVER;
        case 3: // FUN
          return FUN;
        case 4: // PARAM
          return PARAM;
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
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.APP, new org.apache.thrift.meta_data.FieldMetaData("app", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SERVER, new org.apache.thrift.meta_data.FieldMetaData("server", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.FUN, new org.apache.thrift.meta_data.FieldMetaData("fun", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PARAM, new org.apache.thrift.meta_data.FieldMetaData("param", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TransportValue.class, metaDataMap);
  }

  public TransportValue() {
  }

  public TransportValue(
    String app,
    String server,
    String fun,
    String param)
  {
    this();
    this.app = app;
    this.server = server;
    this.fun = fun;
    this.param = param;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TransportValue(TransportValue other) {
    if (other.isSetApp()) {
      this.app = other.app;
    }
    if (other.isSetServer()) {
      this.server = other.server;
    }
    if (other.isSetFun()) {
      this.fun = other.fun;
    }
    if (other.isSetParam()) {
      this.param = other.param;
    }
  }

  public TransportValue deepCopy() {
    return new TransportValue(this);
  }

  
  public void clear() {
    this.app = null;
    this.server = null;
    this.fun = null;
    this.param = null;
  }

  public String getApp() {
    return this.app;
  }

  public TransportValue setApp(String app) {
    this.app = app;
    return this;
  }

  public void unsetApp() {
    this.app = null;
  }

  /** Returns true if field app is set (has been assigned a value) and false otherwise */
  public boolean isSetApp() {
    return this.app != null;
  }

  public void setAppIsSet(boolean value) {
    if (!value) {
      this.app = null;
    }
  }

  public String getServer() {
    return this.server;
  }

  public TransportValue setServer(String server) {
    this.server = server;
    return this;
  }

  public void unsetServer() {
    this.server = null;
  }

  /** Returns true if field server is set (has been assigned a value) and false otherwise */
  public boolean isSetServer() {
    return this.server != null;
  }

  public void setServerIsSet(boolean value) {
    if (!value) {
      this.server = null;
    }
  }

  public String getFun() {
    return this.fun;
  }

  public TransportValue setFun(String fun) {
    this.fun = fun;
    return this;
  }

  public void unsetFun() {
    this.fun = null;
  }

  /** Returns true if field fun is set (has been assigned a value) and false otherwise */
  public boolean isSetFun() {
    return this.fun != null;
  }

  public void setFunIsSet(boolean value) {
    if (!value) {
      this.fun = null;
    }
  }

  public String getParam() {
    return this.param;
  }

  public TransportValue setParam(String param) {
    this.param = param;
    return this;
  }

  public void unsetParam() {
    this.param = null;
  }

  /** Returns true if field param is set (has been assigned a value) and false otherwise */
  public boolean isSetParam() {
    return this.param != null;
  }

  public void setParamIsSet(boolean value) {
    if (!value) {
      this.param = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case APP:
      if (value == null) {
        unsetApp();
      } else {
        setApp((String)value);
      }
      break;

    case SERVER:
      if (value == null) {
        unsetServer();
      } else {
        setServer((String)value);
      }
      break;

    case FUN:
      if (value == null) {
        unsetFun();
      } else {
        setFun((String)value);
      }
      break;

    case PARAM:
      if (value == null) {
        unsetParam();
      } else {
        setParam((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case APP:
      return getApp();

    case SERVER:
      return getServer();

    case FUN:
      return getFun();

    case PARAM:
      return getParam();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case APP:
      return isSetApp();
    case SERVER:
      return isSetServer();
    case FUN:
      return isSetFun();
    case PARAM:
      return isSetParam();
    }
    throw new IllegalStateException();
  }

  
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TransportValue)
      return this.equals((TransportValue)that);
    return false;
  }

  public boolean equals(TransportValue that) {
    if (that == null)
      return false;

    boolean this_present_app = true && this.isSetApp();
    boolean that_present_app = true && that.isSetApp();
    if (this_present_app || that_present_app) {
      if (!(this_present_app && that_present_app))
        return false;
      if (!this.app.equals(that.app))
        return false;
    }

    boolean this_present_server = true && this.isSetServer();
    boolean that_present_server = true && that.isSetServer();
    if (this_present_server || that_present_server) {
      if (!(this_present_server && that_present_server))
        return false;
      if (!this.server.equals(that.server))
        return false;
    }

    boolean this_present_fun = true && this.isSetFun();
    boolean that_present_fun = true && that.isSetFun();
    if (this_present_fun || that_present_fun) {
      if (!(this_present_fun && that_present_fun))
        return false;
      if (!this.fun.equals(that.fun))
        return false;
    }

    boolean this_present_param = true && this.isSetParam();
    boolean that_present_param = true && that.isSetParam();
    if (this_present_param || that_present_param) {
      if (!(this_present_param && that_present_param))
        return false;
      if (!this.param.equals(that.param))
        return false;
    }

    return true;
  }

  
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_app = true && (isSetApp());
    list.add(present_app);
    if (present_app)
      list.add(app);

    boolean present_server = true && (isSetServer());
    list.add(present_server);
    if (present_server)
      list.add(server);

    boolean present_fun = true && (isSetFun());
    list.add(present_fun);
    if (present_fun)
      list.add(fun);

    boolean present_param = true && (isSetParam());
    list.add(present_param);
    if (present_param)
      list.add(param);

    return list.hashCode();
  }

  
  public int compareTo(TransportValue other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetApp()).compareTo(other.isSetApp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetApp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.app, other.app);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetServer()).compareTo(other.isSetServer());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetServer()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.server, other.server);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFun()).compareTo(other.isSetFun());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFun()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.fun, other.fun);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetParam()).compareTo(other.isSetParam());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParam()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.param, other.param);
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
    StringBuilder sb = new StringBuilder("TransportValue(");
    boolean first = true;

    sb.append("app:");
    if (this.app == null) {
      sb.append("null");
    } else {
      sb.append(this.app);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("server:");
    if (this.server == null) {
      sb.append("null");
    } else {
      sb.append(this.server);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("fun:");
    if (this.fun == null) {
      sb.append("null");
    } else {
      sb.append(this.fun);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("param:");
    if (this.param == null) {
      sb.append("null");
    } else {
      sb.append(this.param);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (app == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'app' was not present! Struct: " + toString());
    }
    if (server == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'server' was not present! Struct: " + toString());
    }
    if (fun == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'fun' was not present! Struct: " + toString());
    }
    if (param == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'param' was not present! Struct: " + toString());
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TransportValueStandardSchemeFactory implements SchemeFactory {
    public TransportValueStandardScheme getScheme() {
      return new TransportValueStandardScheme();
    }
  }

  private static class TransportValueStandardScheme extends StandardScheme<TransportValue> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TransportValue struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // APP
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.app = iprot.readString();
              struct.setAppIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SERVER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.server = iprot.readString();
              struct.setServerIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // FUN
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.fun = iprot.readString();
              struct.setFunIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PARAM
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.param = iprot.readString();
              struct.setParamIsSet(true);
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
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TransportValue struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.app != null) {
        oprot.writeFieldBegin(APP_FIELD_DESC);
        oprot.writeString(struct.app);
        oprot.writeFieldEnd();
      }
      if (struct.server != null) {
        oprot.writeFieldBegin(SERVER_FIELD_DESC);
        oprot.writeString(struct.server);
        oprot.writeFieldEnd();
      }
      if (struct.fun != null) {
        oprot.writeFieldBegin(FUN_FIELD_DESC);
        oprot.writeString(struct.fun);
        oprot.writeFieldEnd();
      }
      if (struct.param != null) {
        oprot.writeFieldBegin(PARAM_FIELD_DESC);
        oprot.writeString(struct.param);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TransportValueTupleSchemeFactory implements SchemeFactory {
    public TransportValueTupleScheme getScheme() {
      return new TransportValueTupleScheme();
    }
  }

  private static class TransportValueTupleScheme extends TupleScheme<TransportValue> {

    
    public void write(org.apache.thrift.protocol.TProtocol prot, TransportValue struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.app);
      oprot.writeString(struct.server);
      oprot.writeString(struct.fun);
      oprot.writeString(struct.param);
    }

    
    public void read(org.apache.thrift.protocol.TProtocol prot, TransportValue struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.app = iprot.readString();
      struct.setAppIsSet(true);
      struct.server = iprot.readString();
      struct.setServerIsSet(true);
      struct.fun = iprot.readString();
      struct.setFunIsSet(true);
      struct.param = iprot.readString();
      struct.setParamIsSet(true);
    }
  }

}

