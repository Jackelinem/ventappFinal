package ec.edu.ups.Services;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.Dao.PropiedadDao;
import ec.edu.ups.Model.Propiedad;
@WebServlet("/Imagen")
public class MostrarImagen extends HttpServlet {
	@Inject 
	PropiedadDao pdao;
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		int id=Integer.parseInt(request.getParameter("ID"));
		System.out.println("Este es el id del servlet que le acaba de llegar "+id);
		
		
		try {
			
			Propiedad p=pdao.leer(id);
			response.setContentType("image/jpg");
			if(p.getImagenes()!=null) {
				response.setContentLength(p.getImagenes().get(0).getImg().length);
				response.getOutputStream().write(p.getImagenes().get(0).getImg());

			}
//			if(rs.next()){
//				System.out.println(rs.getBlob("tv_icon"));
//				System.out.println(rs.getString("tv_icon"));
//				System.out.println(rs.getObject("tv_icon"));
//				byte[] imagen=rs.getBytes("tv_icon");
//				response.setContentType("image/jpg");
//				System.out.println("imagen: "+imagen);
//				response.setContentLength(imagen.length);
//				ServletOutputStream o=response.getOutputStream();
//				o.write(imagen);
//				System.out.println();
//			}
		 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
