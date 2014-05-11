/**
 * TwitterTrendings.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package main;

public interface TwitterTrendings extends java.rmi.Remote {
    public void start() throws java.rmi.RemoteException;
    public main.TrendingEntry[] getYoutubeTrendings(java.lang.String country) throws java.rmi.RemoteException;
}
