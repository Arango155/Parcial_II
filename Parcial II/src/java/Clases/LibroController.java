package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JP
 */
public class LibroController {
      Libro[] tablaLibro;
    int indiceArray;
    
    private Connection conexion;
    private PreparedStatement statement = null;
    private ResultSet result = null;
    
    public LibroController(){
        this.tablaLibro = new Libro [100];
        this.indiceArray=0;
    }
    
     public void guardarAlumno(Libro libro){
        this.tablaLibro[this.indiceArray]=libro;  
        this.indiceArray=this.indiceArray+1;
        // procedimiento para almacenar en la Base de Datos
    }
    
    public Libro [] getLibro(){
        return this.tablaLibro;
    }
    
    public void abrirConexion(){
        
        
    }    
   
    
    public String guardarLibro2(Libro libro){        
        String sql = "INSERT INTO universidad.alumno(numero_carne, nombre, correo, direccion, genero_idgenero) ";
             sql += " VALUES(?,?,?,?,?)";              
       try{     
            abrirConexion();
            statement = conexion.prepareStatement(sql); 
            statement.setInt(1, libro.getCodigo());
            statement.setString(2, libro.getNombre_libro());
            statement.setString(3, libro.getTipo_de_pasta());
            statement.setString(4, libro.getEditorial());
            statement.setInt(5, libro.getAnio_publicacion());
                int resultado = statement.executeUpdate(); 
                if(resultado > 0){
                    return String.valueOf(resultado);
                }else{
                    return String.valueOf(resultado);
                }
        }catch(SQLException e){ 
            return e.getMessage();
        }
    }
    
    public void getLibro2(StringBuffer respuesta){   
        String sql="select * from universidad.alumno";
        try{
        abrirConexion();
        respuesta.setLength(0);
        
        statement= conexion.prepareStatement(sql);                        
        result = statement.executeQuery();            
            if (result!=null){
                while (result.next()){
               respuesta.append("<tr>");
                respuesta.append("<td >").append(result.getString("numero_carne")).append("</td>");
                respuesta.append("<td >").append(result.getString("nombre")).append("</td>");
                respuesta.append("<td >").append(result.getString("direccion")).append("</td>");
                respuesta.append("<td >").append(result.getString("correo")).append("</td>");
                respuesta.append("<td id=\"").append(result.getString("numero_carne"))
                        .append("\"  onclick=\"eliminarAlumno(this.id);\">") 
                        //.append("\"  onclick=\"eliminarAlumno("+result.getString("numero_carne")+");\">") 
                        .append(" <a class=\"btn btn-warning\"'><i class=\"fas fa-edit\"></i>  </a>"
                                +" <a class=\"btn btn-danger\"'> <i class=\"fas fa-trash-alt\"></i> </a>"
                                + " <td></tr>");
                }
            }else{
                respuesta.append("error al consultar");
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public String eliminarLibro(int carne){        
        String sql = "DELETE FROM alumno WHERE numero_carne="+carne;              
       try{     
            abrirConexion();
            statement = conexion.prepareStatement(sql); 
            int resultado = statement.executeUpdate();
            if(resultado > 0){
                return String.valueOf(resultado);
            }else{
                return String.valueOf(resultado);
            }
        }catch(SQLException e){ 
            return e.getMessage();
        }
    }
    
    
}