/**
 * TwitterTrendingsServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package main;

public class TwitterTrendingsServiceLocator extends org.apache.axis.client.Service implements main.TwitterTrendingsService {

    public TwitterTrendingsServiceLocator() {
    }


    public TwitterTrendingsServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TwitterTrendingsServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TwitterTrendings
    private java.lang.String TwitterTrendings_address = "http://192.168.0.202/PruebaNormal/services/TwitterTrendings";

    public java.lang.String getTwitterTrendingsAddress() {
        return TwitterTrendings_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TwitterTrendingsWSDDServiceName = "TwitterTrendings";

    public java.lang.String getTwitterTrendingsWSDDServiceName() {
        return TwitterTrendingsWSDDServiceName;
    }

    public void setTwitterTrendingsWSDDServiceName(java.lang.String name) {
        TwitterTrendingsWSDDServiceName = name;
    }

    public main.TwitterTrendings getTwitterTrendings() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TwitterTrendings_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTwitterTrendings(endpoint);
    }

    public main.TwitterTrendings getTwitterTrendings(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            main.TwitterTrendingsSoapBindingStub _stub = new main.TwitterTrendingsSoapBindingStub(portAddress, this);
            _stub.setPortName(getTwitterTrendingsWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTwitterTrendingsEndpointAddress(java.lang.String address) {
        TwitterTrendings_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (main.TwitterTrendings.class.isAssignableFrom(serviceEndpointInterface)) {
                main.TwitterTrendingsSoapBindingStub _stub = new main.TwitterTrendingsSoapBindingStub(new java.net.URL(TwitterTrendings_address), this);
                _stub.setPortName(getTwitterTrendingsWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("TwitterTrendings".equals(inputPortName)) {
            return getTwitterTrendings();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://main", "TwitterTrendingsService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://main", "TwitterTrendings"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TwitterTrendings".equals(portName)) {
            setTwitterTrendingsEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
