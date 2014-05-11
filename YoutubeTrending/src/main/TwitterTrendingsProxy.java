package main;

public class TwitterTrendingsProxy implements main.TwitterTrendings {
  private String _endpoint = null;
  private main.TwitterTrendings twitterTrendings = null;
  
  public TwitterTrendingsProxy() {
    _initTwitterTrendingsProxy();
  }
  
  public TwitterTrendingsProxy(String endpoint) {
    _endpoint = endpoint;
    _initTwitterTrendingsProxy();
  }
  
  private void _initTwitterTrendingsProxy() {
    try {
      twitterTrendings = (new main.TwitterTrendingsServiceLocator()).getTwitterTrendings();
      if (twitterTrendings != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)twitterTrendings)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)twitterTrendings)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (twitterTrendings != null)
      ((javax.xml.rpc.Stub)twitterTrendings)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public main.TwitterTrendings getTwitterTrendings() {
    if (twitterTrendings == null)
      _initTwitterTrendingsProxy();
    return twitterTrendings;
  }
  
  public void start() throws java.rmi.RemoteException{
    if (twitterTrendings == null)
      _initTwitterTrendingsProxy();
    twitterTrendings.start();
  }
  
  public main.TrendingEntry[] getYoutubeTrendings(java.lang.String country) throws java.rmi.RemoteException{
    if (twitterTrendings == null)
      _initTwitterTrendingsProxy();
    return twitterTrendings.getYoutubeTrendings(country);
  }
  
  
}