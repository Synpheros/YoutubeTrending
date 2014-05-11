package servlets;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.TrendingEntry;
import main.TwitterTrendings;
import main.TwitterTrendingsProxy;

/**
 * Servlet implementation class GetTrending
 */
@WebServlet("/GetTrending")
public class GetTrending extends HttpServlet {
	
	private String[] videodata;
	private int terminados;
	
	private class VideoData implements Runnable {
		private String entry;
		private int place;
		
		public VideoData(String entry, int place) {
			super();
			this.entry = entry;
			this.place = place;
		}
		
		@Override
		public void run() {
			try{
				HttpURLConnection con = (HttpURLConnection) new URL("http://gdata.youtube.com/feeds/api/videos/"+this.entry+"?v=2&alt=json").openConnection();
				con.setRequestMethod("GET");
				con.setDoOutput(true);
				int responsecode = con.getResponseCode();
				if(responsecode==200){
					StringBuilder sb = new StringBuilder();
					
					BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF8"));
					String line;
					while((line=bf.readLine())!=null)
						sb.append(line);
					
					videodata[this.place] = sb.toString();
				}
			}catch(Exception ex){}
			terminados++;
		}
		
	}
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTrending() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF8");
		PrintWriter out = response.getWriter();
		TwitterTrendings entry = new TwitterTrendingsProxy();
		
		String country = request.getParameter("Country");
		
		if(country !=null)
			country = country.toLowerCase();
		
		TrendingEntry[] array = entry.getYoutubeTrendings(country);
		
		if(array==null){
			out.println("<p>No se han obtenido resultados</p>");
		}else{
			this.terminados = 0;
			int max = (array.length>10)?10:array.length;
			this.videodata = new String[max];
			
			out.println("{\"videolist\": [");
			boolean primero = true;
			for(int i=0; i<max; i++){
				Thread t = new Thread(new VideoData(array[i].getEntry(),i));
				t.start();
			}
			
			while(this.terminados<max){
				try{Thread.sleep(20);}catch(Exception ex){}
			}
			
			for(int i=0; i<max; i++){
				if(!primero) out.print(",");
				else primero = false;				
				out.print("{\"entry\": \""+array[i].getEntry()+"\",\"puntuation\": \""+array[i].getPuntuation()+"\",\"count\": \""+array[i].getCount()+"\",\"Data\":");
				out.print(videodata[i]);
				out.print("}");
			}
			out.println("]}");
		}
	}

}
