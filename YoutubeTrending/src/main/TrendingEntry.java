/**
 * TrendingEntry.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package main;

public class TrendingEntry  implements java.io.Serializable {
    private int count;

    private java.lang.String entry;

    private long lastOcurrence;

    private int puntuation;

    public TrendingEntry() {
    }

    public TrendingEntry(
           int count,
           java.lang.String entry,
           long lastOcurrence,
           int puntuation) {
           this.count = count;
           this.entry = entry;
           this.lastOcurrence = lastOcurrence;
           this.puntuation = puntuation;
    }


    /**
     * Gets the count value for this TrendingEntry.
     * 
     * @return count
     */
    public int getCount() {
        return count;
    }


    /**
     * Sets the count value for this TrendingEntry.
     * 
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }


    /**
     * Gets the entry value for this TrendingEntry.
     * 
     * @return entry
     */
    public java.lang.String getEntry() {
        return entry;
    }


    /**
     * Sets the entry value for this TrendingEntry.
     * 
     * @param entry
     */
    public void setEntry(java.lang.String entry) {
        this.entry = entry;
    }


    /**
     * Gets the lastOcurrence value for this TrendingEntry.
     * 
     * @return lastOcurrence
     */
    public long getLastOcurrence() {
        return lastOcurrence;
    }


    /**
     * Sets the lastOcurrence value for this TrendingEntry.
     * 
     * @param lastOcurrence
     */
    public void setLastOcurrence(long lastOcurrence) {
        this.lastOcurrence = lastOcurrence;
    }


    /**
     * Gets the puntuation value for this TrendingEntry.
     * 
     * @return puntuation
     */
    public int getPuntuation() {
        return puntuation;
    }


    /**
     * Sets the puntuation value for this TrendingEntry.
     * 
     * @param puntuation
     */
    public void setPuntuation(int puntuation) {
        this.puntuation = puntuation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TrendingEntry)) return false;
        TrendingEntry other = (TrendingEntry) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.count == other.getCount() &&
            ((this.entry==null && other.getEntry()==null) || 
             (this.entry!=null &&
              this.entry.equals(other.getEntry()))) &&
            this.lastOcurrence == other.getLastOcurrence() &&
            this.puntuation == other.getPuntuation();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getCount();
        if (getEntry() != null) {
            _hashCode += getEntry().hashCode();
        }
        _hashCode += new Long(getLastOcurrence()).hashCode();
        _hashCode += getPuntuation();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TrendingEntry.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://main", "TrendingEntry"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("count");
        elemField.setXmlName(new javax.xml.namespace.QName("http://main", "count"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entry");
        elemField.setXmlName(new javax.xml.namespace.QName("http://main", "entry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastOcurrence");
        elemField.setXmlName(new javax.xml.namespace.QName("http://main", "lastOcurrence"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("puntuation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://main", "puntuation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
